package de.jpwinkler.daf.doorsbridge.internal;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import de.jpwinkler.daf.doorsbridge.DoorsApplication;
import de.jpwinkler.daf.doorsbridge.DoorsException;
import de.jpwinkler.daf.doorsbridge.DoorsNotRunningException;
import de.jpwinkler.daf.doorsbridge.DoorsURL;
import de.jpwinkler.daf.doorsbridge.user32.Window;
import de.jpwinkler.daf.doorsbridge.user32.WindowManager;

public class DoorsApplicationImpl implements DoorsApplication {

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
            e.printStackTrace();
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
        buildAndRunCommand(builder -> {
            builder.addScript("utils.dxl");
            builder.addScript("export_csv.dxl");
            builder.addScript("export_csv_path.dxl");
            builder.setVariable("modulePath", modulePath);
            builder.setVariable("file", file.getAbsolutePath());
        });
    }

    @Override
    public void exportModuleToCSV(final DoorsURL url, final File file) throws DoorsException, IOException {
        buildAndRunCommand(builder -> {
            builder.addScript("utils.dxl");
            builder.addScript("export_csv.dxl");
            builder.addScript("export_csv_url.dxl");
            builder.setVariable("moduleUrl", url.getUrl());
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

    private void buildAndRunCommand(final Consumer<DoorsScriptBuilder> prepareScriptBuilder) throws IOException, DoorsException {
        final DoorsScriptBuilder builder = new DoorsScriptBuilder(cache);
        final boolean redirectOutput = outputStream != null;

        if (redirectOutput) {
            builder.addScript("redirect_output.dxl");
        }

        prepareScriptBuilder.accept(builder);

        MyTailer t = null;
        if (redirectOutput) {
            builder.addScript("post.dxl");

            final File file = File.createTempFile("doorsOutput", "");
            // file.deleteOnExit();
            builder.setVariable("outputRedirectFilename", file.getAbsolutePath());
            if (!file.exists()) {
                file.createNewFile();
            }
            t = MyTailer.create(file, outputStream);
        }

        if (batchMode) {
            runInBatchMode(builder.build());
        } else {
            runStr(builder.build());
        }

        if (redirectOutput) {
            t.stop();
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
