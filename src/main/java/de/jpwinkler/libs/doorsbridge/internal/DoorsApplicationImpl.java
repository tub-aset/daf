package de.jpwinkler.libs.doorsbridge.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import de.jpwinkler.libs.doorsbridge.DoorsApplication;
import de.jpwinkler.libs.doorsbridge.DoorsException;
import de.jpwinkler.libs.doorsbridge.DoorsNotRunningException;
import de.jpwinkler.libs.doorsbridge.DoorsURL;
import de.jpwinkler.libs.doorsbridge.ModuleRef;
import de.jpwinkler.libs.doorsbridge.user32.Window;
import de.jpwinkler.libs.doorsbridge.user32.WindowManager;

public class DoorsApplicationImpl implements DoorsApplication {

    private static final Logger LOGGER = Logger.getLogger(DoorsApplicationImpl.class.getName());

    private final Map<Thread, ActiveXComponent> doorsApplications;
    private final WindowManager windowManager = new WindowManager();
    private OutputStream outputStream;

    private boolean batchMode = false;
    private boolean silentMode = false;
    private DoorsScriptBuilder scriptBuilder;

    private String doorsServer;

    private String user;

    private String password;

    public DoorsApplicationImpl() {
        doorsApplications = new HashMap<Thread, ActiveXComponent>();
    }

    @Override
    public void beginBatchMode() {
        if (batchMode) {
            throw new RuntimeException("Already in batch mode.");
        }

        batchMode = true;
        scriptBuilder = new DoorsScriptBuilder();
    }

    @Override
    public void endBatchMode() throws DoorsException, IOException {
        if (!batchMode) {
            throw new RuntimeException("Not in batch mode.");
        }

        batchMode = false;

        runCommand();
    }

    private ActiveXComponent getDoorsApplication() {
        if (doorsApplications.containsKey(Thread.currentThread())) {
            return doorsApplications.get(Thread.currentThread());
        } else {
            final ActiveXComponent newDoorsApplication = new ActiveXComponent("Doors.Application");
            doorsApplications.put(Thread.currentThread(), newDoorsApplication);
            return newDoorsApplication;
        }
    }

    @Override
    public boolean isDoorsRunning() {
        return findDoorsWindow() && canExecuteDxl();
    }

    @Override
    public void redirectOutput(final OutputStream stream) {
        outputStream = stream;
    }

    synchronized private boolean canExecuteDxl() {
        try {
            final ActiveXComponent doorsApplication = getDoorsApplication();
            Dispatch.call(doorsApplication, "runStr", " ");
            return true;
        } catch (final Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

    private boolean findDoorsWindow() {
        for (final Window window : windowManager.listWindows()) {
            if (window.isVisible() && window.getClassName().equals("DOORSWindow")) {
                return true;
            }
        }
        return false;
    }

    synchronized private void runStr(final String dxl) throws DoorsNotRunningException {
        if (!isDoorsRunning()) {
            throw new DoorsNotRunningException();
        }
        final ActiveXComponent doorsApplication = getDoorsApplication();
        Dispatch.call(doorsApplication, "runStr", dxl);
    }

    @SuppressWarnings("unused")
    synchronized private void runFile(final String fileName) throws DoorsNotRunningException {
        if (!isDoorsRunning()) {
            throw new DoorsNotRunningException();
        }
        final ActiveXComponent doorsApplication = getDoorsApplication();
        Dispatch.call(doorsApplication, "runFile", fileName);
    }

    private File getTempFile() throws IOException {
        final File outFile = File.createTempFile("doors_temp", "");
        outFile.deleteOnExit();
        return outFile;
    }

    @Override
    public void ack(final String message) throws DoorsException, IOException {
        buildAndRunCommand(builder -> {
            builder.addScript(new InternalDXLScript("ack.dxl"));
            builder.setVariable("message", message);
        });
    }

    @Override
    public ModuleRef openModule(final DoorsURL url) throws IOException, DoorsException {
        buildAndRunCommand(builder -> {
            builder.addLibrary(new InternalDXLScript("lib/utils.dxl"));
            builder.addScript(new InternalDXLScript("open_module.dxl"));
            builder.setVariable("url", url.getUrl());
        });
        return new ModuleRefImpl(this, url);
    }

    @Override
    public ModuleRef openModule(final String name) throws IOException, DoorsException {
        buildAndRunCommand(builder -> {
            builder.addLibrary(new InternalDXLScript("lib/utils.dxl"));
            builder.addScript(new InternalDXLScript("open_module.dxl"));
            builder.setVariable("name", name);
        });
        return new ModuleRefImpl(this, name);
    }

    @Override
    public void runScript(final File scriptFile) throws DoorsException, IOException {
        buildAndRunCommand(builder -> {
            builder.addScript(new ExternalDXLScript(scriptFile));
        });
    }

    @Override
    public void runScript(final String dxlCode) throws DoorsException, IOException {
        buildAndRunCommand(builder -> {
            builder.addScript(new InMemoryDXLScript(dxlCode));
        });
    }

    @Override
    public void print(final String message) throws DoorsException, IOException {
        buildAndRunCommand(builder -> {
            builder.addScript(new InternalDXLScript("print.dxl"));
            builder.setVariable("message", message);
        });
    }

    public void buildAndRunCommand(final Consumer<DoorsScriptBuilder> prepareScriptBuilder) throws IOException, DoorsException {
        if (!batchMode) {
            scriptBuilder = new DoorsScriptBuilder();
        }

        scriptBuilder.beginScope();
        prepareScriptBuilder.accept(scriptBuilder);
        scriptBuilder.endScope();

        if (!batchMode) {
            runCommand();
        }
    }

    private void runCommand() throws DoorsException, IOException {

        final boolean redirectOutput = outputStream != null;

        final File exceptionFile = getTempFile();

        scriptBuilder.addPreamble(new InternalDXLScript("pre/exception_handling.dxl"));
        scriptBuilder.setVariable("exceptionFilename", exceptionFile.getAbsolutePath());

        if (redirectOutput) {
            scriptBuilder.addPreamble(new InternalDXLScript("pre/redirect_output.dxl"));
        }

        MyTailer t = null;
        if (redirectOutput) {
            scriptBuilder.addScript(new InternalDXLScript("lib/redirect_output_post.dxl"));

            final File outputFile = File.createTempFile("doorsOutput", "");
            // outputFile.deleteOnExit();
            scriptBuilder.setVariable("outputRedirectFilename", outputFile.getAbsolutePath());
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            t = MyTailer.create(outputFile, outputStream);
        }

        final String dxl = scriptBuilder.build();
        if (silentMode) {
            runInSilentMode(dxl);
        } else {
            runStr(dxl);
        }

        if (redirectOutput) {
            t.stop();
        }

        if (exceptionFile.exists()) {
            final String message = IOUtils.toString(new FileInputStream(exceptionFile));
            if (!message.isEmpty()) {
                throw new DoorsException(message);
            }
        }

    }

    public void initSilentMode(final String doorsServer, final String user, final String password) {
        this.doorsServer = doorsServer;
        this.user = user;
        this.password = password;
        silentMode = true;
    }

    private void runInSilentMode(final String dxl) throws IOException {
        final File f = getTempFile();
        FileUtils.write(f, dxl);

        // TODO: this might change!
        final String[] cmdLine = new String[] { "C:\\Program Files (x86)\\DOORS 9.5.2.0\\bin\\doors.exe", "-b", f.getAbsolutePath(), "-d", doorsServer, "-u", user, "-P", "xxxx" };

        LOGGER.info(String.format("Running DOORS in silent mode. Command line: %s", String.join(" ", cmdLine)));

        cmdLine[cmdLine.length - 1] = password;

        final Process exec = Runtime.getRuntime().exec(cmdLine);
        try {
            exec.waitFor();
        } catch (final InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
