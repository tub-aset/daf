/*
 * doorsbridge - A library for Java to Doors interaction.
 * Copyright (C) 2016 Jonas Winkler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.jpwinkler.daf.bridge.internal;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.bridge.DoorsRuntimeException;
import de.jpwinkler.daf.bridge.DoorsTreeNodeRef;
import de.jpwinkler.daf.bridge.user32.Window;
import de.jpwinkler.daf.bridge.user32.WindowManager;
import de.jpwinkler.daf.db.DatabaseFactory;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.UnresolvedLink;
import de.jpwinkler.daf.model.impl.DoorsFolderImpl;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class DoorsApplicationImpl implements DoorsApplication, DatabaseFactory {

    private static final Logger LOGGER = Logger.getLogger(DoorsApplicationImpl.class.getName());
    private static Throwable loadError;

    static {
        try {
            String lib;
            switch (System.getProperty("os.arch")) {
                case "x86":
                    lib = "jacob-1.18-x86.dll";
                    break;
                case "amd64":
                    lib = "jacob-1.18-x64.dll";
                    break;
                default:
                    throw new RuntimeException("No jacob dll for architecture " + System.getProperty("os.arch"));
            }
            File dllFile = File.createTempFile("doorsbridge", ".dll");
            dllFile.deleteOnExit();
            try (InputStream is = DoorsApplication.class.getClassLoader().getResourceAsStream(lib)) {
                FileUtils.copyInputStreamToFile(is, dllFile);
            }
            System.load(dllFile.getAbsolutePath());
        } catch (Throwable t) {
            loadError = t;
            Logger.getLogger(DoorsApplicationImpl.class.getName()).log(Level.SEVERE, null, t);
        }

    }

    public DoorsApplicationImpl() {
        if (loadError != null) {
            throw new RuntimeException(loadError);
        }

    }

    // ActiveXComponent seems to be bound to the current thread, thus we store
    // one ActiveXComponent per thread.
    private final Map<Thread, ActiveXComponent> doorsApplications = new HashMap<>();

    /**
     * Used for detecting whether the DOORS window is visible.
     */
    private final WindowManager windowManager = new WindowManager();

    /**
     * If assigned, output is redirected here.
     */
    private OutputStream outputStream;

    private boolean batchMode = false;
    private boolean silentMode = false;
    private DoorsScriptBuilder scriptBuilder;

    // Database connection settings for use with silent mode.
    private String doorsPath;
    private String doorsServer;
    private String user;
    private String password;

    @Override
    public void beginBatchMode() {
        if (batchMode) {
            throw new DoorsRuntimeException("Already in batch mode.");
        }

        batchMode = true;
        scriptBuilder = new DoorsScriptBuilder();
    }

    @Override
    public void endBatchMode() {
        if (!batchMode) {
            throw new DoorsRuntimeException("Not in batch mode.");
        }

        batchMode = false;

        try {
            runCommand();
        } catch (final IOException e) {
            throw new DoorsRuntimeException(e);
        }
    }

    public boolean isBatchMode() {
        return batchMode;
    }

    public boolean isSilentMode() {
        return silentMode;
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

    synchronized private void runStr(final String dxl) {
        if (!isDoorsRunning()) {
            throw new DoorsRuntimeException("DOORS is not running");
        }
        final ActiveXComponent doorsApplication = getDoorsApplication();

        Dispatch.call(doorsApplication, "runStr", dxl);
    }

    private File getTempFile() throws IOException {
        final File outFile = File.createTempFile("doors_temp", "");
        outFile.deleteOnExit();
        return outFile;
    }

    @Override
    public void ack(final String message) {
        buildAndRunCommand(builder -> {
            builder.addScript(new InternalDXLScript("ack.dxl"));
            builder.setVariable("message", message);
        });
    }

    @Override
    public void runScript(final File scriptFile) {
        buildAndRunCommand(builder -> {
            builder.addScript(new ExternalDXLScript(scriptFile));
        });
    }

    @Override
    public void runScript(final String dxlCode) {
        buildAndRunCommand(builder -> {
            builder.addScript(new InMemoryDXLScript(dxlCode));
        });
    }

    @Override
    public void print(final String message) {
        buildAndRunCommand(builder -> {
            builder.addScript(new InternalDXLScript("print.dxl"));
            builder.setVariable("message", message);
        });
    }

    @Override
    public DoorsTreeNode getRoot() {
        return new DoorsFolderRefImpl(this, DoorsItemType.FOLDER, null, "Doors Application");
    }

    String buildAndRunCommand(final Consumer<DoorsScriptBuilder> prepareScriptBuilder) {
        if (!batchMode) {
            scriptBuilder = new DoorsScriptBuilder();
        }

        scriptBuilder.beginScope();
        prepareScriptBuilder.accept(scriptBuilder);
        scriptBuilder.endScope();

        if (!batchMode) {
            try {
                return runCommand();
            } catch (final IOException e) {
                throw new DoorsRuntimeException(e);
            }
        } else {
            return null;
        }
    }

    private String runCommand() throws IOException {

        final boolean redirectOutput = outputStream != null;

        final File exceptionFile = getTempFile();
        final File returnFile = getTempFile();

        scriptBuilder.addPreamble(new InternalDXLScript("pre/exception_handling.dxl"));
        scriptBuilder.setVariable("exceptionFilename", exceptionFile.getAbsolutePath());

        scriptBuilder.addPreamble(new InternalDXLScript("pre/return.dxl"));
        scriptBuilder.setVariable("returnFilename", returnFile.getAbsolutePath());

        if (redirectOutput) {
            scriptBuilder.addPreamble(new InternalDXLScript("pre/redirect_output.dxl"));
        }

        FileForwarder t = null;
        if (redirectOutput) {
            scriptBuilder.addScript(new InternalDXLScript("lib/redirect_output_post.dxl"));

            final File outputFile = getTempFile();
            scriptBuilder.setVariable("outputRedirectFilename", outputFile.getAbsolutePath());
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            t = FileForwarder.create(outputFile, outputStream);
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
            final String message = FileUtils.readFileToString(exceptionFile, Charset.forName("Cp1252"));
            if (!message.isEmpty()) {
                throw new DoorsRuntimeException("DXL script failed: " + message);
            }
        }

        if (returnFile.exists()) {
            return FileUtils.readFileToString(returnFile, Charset.forName("Cp1252"));
        } else {
            return null;
        }
    }

    public void initSilentMode(final String doorsPath, final String doorsServer, final String user, final String password) {
        this.doorsPath = doorsPath;
        this.doorsServer = doorsServer;
        this.user = user;
        this.password = password;
        silentMode = true;
    }

    private void runInSilentMode(final String dxl) throws IOException {
        final File f = getTempFile();
        FileUtils.write(f, dxl);

        final String[] cmdLine = new String[]{doorsPath, "-b", f.getAbsolutePath(), "-d", doorsServer, "-u", user, "-P", "xxxx"};

        LOGGER.info(String.format("Running DOORS in silent mode. Command line: %s", String.join(" ", cmdLine)));

        cmdLine[cmdLine.length - 1] = password;

        final Process exec = Runtime.getRuntime().exec(cmdLine);
        try {
            exec.waitFor();
        } catch (final InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public DoorsFolder createFolder(DoorsTreeNode parent, String name) {
        return new DoorsFolderRefImpl(this, DoorsItemType.FOLDER, (DoorsTreeNodeRef) parent, name);
    }

    @Override
    public DoorsModule createModule(DoorsTreeNode parent, String name) {
        return new DoorsModuleRefImpl(this, (DoorsTreeNodeRef) parent, name);
    }

    @Override
    public DoorsObject createObject(DoorsTreeNode parent, String objectText) {
        return new DoorsObjectRefImpl(this, (DoorsTreeNodeRef) parent);
    }

    @Override
    public UnresolvedLink createLink(DoorsObject source, String targetModule, String targetObject) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static class FileForwarder implements Runnable {

        private final OutputStream out;
        private final File file;
        private boolean run = true;

        public FileForwarder(final File file, final OutputStream out) {
            this.file = file;
            this.out = out;
        }

        @Override
        public void run() {
            try (final RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                long position = raf.length();
                raf.seek(position);

                long length = raf.length();
                while (position < length || run) {

                    while (position < length) {

                        final byte[] b = new byte[1000];
                        final int numRead = raf.read(b);

                        out.write(b, 0, numRead);
                        out.flush();
                        position = raf.getFilePointer();

                    }

                    try {
                        Thread.sleep(100);
                    } catch (final InterruptedException e) {
                    }
                    length = raf.length();

                }
            } catch (final IOException e) {
                Logger.getLogger(FileForwarder.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }

        public void stop() {
            run = false;
        }

        public static FileForwarder create(final File file, final OutputStream os) {
            final FileForwarder myTailer = new FileForwarder(file, os);

            final Thread t = new Thread(myTailer);
            t.setDaemon(true);
            t.start();

            return myTailer;
        }

    }
}
