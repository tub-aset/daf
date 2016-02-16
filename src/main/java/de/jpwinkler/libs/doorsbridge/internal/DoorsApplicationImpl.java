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

    public DoorsApplicationImpl() {
        doorsApplications = new HashMap<Thread, ActiveXComponent>();
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
            builder.addScript(new InternalDXLScript("utils.dxl"));
            builder.addScript(new InternalDXLScript("open_module.dxl"));
            builder.setVariable("url", url.getUrl());
        });
        return new ModuleRefImpl(this, url);
    }

    @Override
    public ModuleRef openModule(final String name) throws IOException, DoorsException {
        buildAndRunCommand(builder -> {
            builder.addScript(new InternalDXLScript("utils.dxl"));
            builder.addScript(new InternalDXLScript("open_module.dxl"));
            builder.setVariable("name", name);
        });
        return new ModuleRefImpl(this, name);
    }

    @Override
    public void exportModulesToCSV(final File moduleListFile, final File targetFolder) throws IOException, DoorsException {
        buildAndRunCommand(builder -> {
            builder.addScript(new InternalDXLScript("utils.dxl"));
            builder.addScript(new InternalDXLScript("export_csv.dxl"));
            builder.addScript(new InternalDXLScript("export_many.dxl"));
            builder.setVariable("moduleListFile", moduleListFile.getAbsolutePath());
            builder.setVariable("targetFolder", targetFolder.getAbsolutePath());
        });
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

    public void buildAndRunCommand(final Consumer<DoorsScriptBuilder> prepareScriptBuilder) throws IOException, DoorsException {
        final DoorsScriptBuilder builder = new DoorsScriptBuilder();
        final boolean redirectOutput = outputStream != null;

        final File exceptionFile = getTempFile();

        builder.addScript(new InternalDXLScript("exception_handling_pre.dxl"));
        builder.setVariable("exceptionFilename", exceptionFile.getAbsolutePath());

        if (redirectOutput) {
            builder.addScript(new InternalDXLScript("redirect_output_pre.dxl"));
        }

        prepareScriptBuilder.accept(builder);

        MyTailer t = null;
        if (redirectOutput) {
            builder.addScript(new InternalDXLScript("redirect_output_post.dxl"));

            final File outputFile = File.createTempFile("doorsOutput", "");
            // file.deleteOnExit();
            builder.setVariable("outputRedirectFilename", outputFile.getAbsolutePath());
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            t = MyTailer.create(outputFile, outputStream);
        }

        runStr(builder.build());

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
}
