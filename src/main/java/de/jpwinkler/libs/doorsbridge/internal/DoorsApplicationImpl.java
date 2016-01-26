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
import de.jpwinkler.libs.doorsbridge.user32.Window;
import de.jpwinkler.libs.doorsbridge.user32.WindowManager;

public class DoorsApplicationImpl implements DoorsApplication {

    private static final String STANDARD_VIEW = "Standard view";

    private static final Logger LOGGER = Logger.getLogger(DoorsApplicationImpl.class.getName());

    private final Map<Thread, ActiveXComponent> doorsApplications;
    private final DoorsScriptCache cache = new DoorsScriptCache();
    private final WindowManager windowManager = new WindowManager();
    private OutputStream outputStream;

    private boolean batchMode;
    private String doorsServer;
    private String user;
    private String password;

    public DoorsApplicationImpl() {
        doorsApplications = new HashMap<Thread, ActiveXComponent>();
    }

    public void initBatchMode(final String doorsServer, final String user, final String password) {
        batchMode = true;
        this.doorsServer = doorsServer;
        this.user = user;
        this.password = password;
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
    synchronized private void runFile(final String filename) throws DoorsNotRunningException {
        if (!isDoorsRunning()) {
            throw new DoorsNotRunningException();
        }
        final ActiveXComponent doorsApplication = getDoorsApplication();
        Dispatch.call(doorsApplication, "runFile", filename);
    }

    private File getTempFile() throws IOException {
        final File outFile = File.createTempFile("doors_temp", "");
        outFile.deleteOnExit();
        return outFile;
    }

    @Override
    public void ack(final String message) throws DoorsException, IOException {
        buildAndRunCommand(builder -> {
            builder.addScript("ack.dxl");
            builder.setVariable("message", message);
        });
    }

    @Override
    public void exportModuleToCSV(final String modulePath, final File file) throws DoorsException, IOException {
        exportModuleToCSV(modulePath, file, STANDARD_VIEW);
    }

    @Override
    public void exportModuleToCSV(final DoorsURL url, final File file) throws DoorsException, IOException {
        exportModuleToCSV(url, file, STANDARD_VIEW);
    }

    @Override
    public void exportModuleToCSV(final DoorsURL url, final File file, final String view) throws DoorsException, IOException {
        buildAndRunCommand(builder -> {
            builder.addScript("utils.dxl");
            builder.addScript("export_csv.dxl");
            builder.addScript("export_csv_url.dxl");
            builder.setVariable("moduleUrl", url.getUrl());
            builder.setVariable("view", view);
            builder.setVariable("file", file.getAbsolutePath());
        });
    }

    @Override
    public void exportModuleToCSV(final String modulePath, final File file, final String view) throws DoorsException, IOException {
        buildAndRunCommand(builder -> {
            builder.addScript("utils.dxl");
            builder.addScript("export_csv.dxl");
            builder.addScript("export_csv_path.dxl");
            builder.setVariable("modulePath", modulePath);
            builder.setVariable("view", view);
            builder.setVariable("file", file.getAbsolutePath());
        });
    }

    @Override
    public void exportModulesToCSV(final File moduleListFile, final File targetFolder) throws IOException, DoorsException {
        buildAndRunCommand(builder -> {
            builder.addScript("utils.dxl");
            builder.addScript("export_csv.dxl");
            builder.addScript("export_many.dxl");
            builder.setVariable("moduleListFile", moduleListFile.getAbsolutePath());
            builder.setVariable("targetFolder", targetFolder.getAbsolutePath());
        });
    }

    @Override
    public void gotoObject(final DoorsURL url, final int absoluteNumber) throws DoorsException, IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    @Override
    public void gotoObject(final String modulePath, final int absoluteNumber) throws DoorsException, IOException {
        buildAndRunCommand(builder -> {
            builder.addScript("goto_object.dxl");
            builder.setVariable("modulePath", modulePath);
            builder.setVariable("absoluteNumber", String.valueOf(absoluteNumber));
        });
    }

    private void buildAndRunCommand(final Consumer<DoorsScriptBuilder> prepareScriptBuilder) throws IOException, DoorsException {
        final DoorsScriptBuilder builder = new DoorsScriptBuilder(cache);
        final boolean redirectOutput = outputStream != null;

        final File exceptionFile = getTempFile();

        builder.addScript("exception_handling_pre.dxl");
        builder.setVariable("exceptionFilename", exceptionFile.getAbsolutePath());

        if (redirectOutput) {
            builder.addScript("redirect_output_pre.dxl");
        }

        prepareScriptBuilder.accept(builder);

        MyTailer t = null;
        if (redirectOutput) {
            builder.addScript("redirect_output_post.dxl");

            final File outputFile = File.createTempFile("doorsOutput", "");
            // file.deleteOnExit();
            builder.setVariable("outputRedirectFilename", outputFile.getAbsolutePath());
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            t = MyTailer.create(outputFile, outputStream);
        }

        if (batchMode) {
            runInBatchMode(builder.build());
        } else {
            runStr(builder.build());
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

    private void runInBatchMode(final String dxl) throws IOException {
        final File f = getTempFile();
        FileUtils.write(f, dxl);

        final String[] cmdLine = new String[] { "C:\\Program Files (x86)\\DOORS 9.5.2.0\\bin\\doors.exe", "-b", f.getAbsolutePath(), "-d", doorsServer, "-u", user, "-P", "xxxx" };

        LOGGER.info(String.format("Running DOORS in batch mode. Command line: %s", String.join(" ", cmdLine)));

        cmdLine[cmdLine.length - 1] = password;

        final Process exec = Runtime.getRuntime().exec(cmdLine);
        try {
            exec.waitFor();
        } catch (final InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
