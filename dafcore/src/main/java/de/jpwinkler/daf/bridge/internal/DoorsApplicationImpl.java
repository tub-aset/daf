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
import de.jpwinkler.daf.bridge.DoorsException;
import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.bridge.DoorsNotRunningException;
import de.jpwinkler.daf.bridge.DoorsRuntimeException;
import de.jpwinkler.daf.bridge.DoorsURL;
import de.jpwinkler.daf.bridge.ItemRef;
import de.jpwinkler.daf.bridge.ModuleRef;
import de.jpwinkler.daf.bridge.user32.Window;
import de.jpwinkler.daf.bridge.user32.WindowManager;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

public class DoorsApplicationImpl implements DoorsApplication {

    private static final Logger LOGGER = Logger.getLogger(DoorsApplicationImpl.class.getName());

    // ActiveXComponent seems to be bound to the current thread, thus we store
    // one ActiveXComponent per thread.
    private final Map<Thread, ActiveXComponent> doorsApplications = new HashMap<Thread, ActiveXComponent>();

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
    public void endBatchMode() throws DoorsException {
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
            throw new DoorsNotRunningException();
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
        try {
            buildAndRunCommand(builder -> {
                builder.addScript(new InternalDXLScript("ack.dxl"));
                builder.setVariable("message", message);
            });
        } catch (final DoorsException e) {
            // This should never happen, because the script never calls 'throw'.
            throw new DoorsRuntimeException();
        }
    }

    @Override
    public ModuleRef openModule(final DoorsURL url) throws DoorsException {
        buildAndRunCommand(builder -> {
            builder.addLibrary(new InternalDXLScript("lib/utils.dxl"));
            builder.addScript(new InternalDXLScript("open_module.dxl"));
            builder.setVariable("url", url.getUrl());
        });
        return new ModuleRefImpl(this, url);
    }

    @Override
    public ModuleRef openModule(final String name) throws DoorsException {
        buildAndRunCommand(builder -> {
            builder.addLibrary(new InternalDXLScript("lib/utils.dxl"));
            builder.addScript(new InternalDXLScript("open_module.dxl"));
            builder.setVariable("name", name);
        });
        return new ModuleRefImpl(this, name);
    }

    @Override
    public void runScript(final File scriptFile) throws DoorsException {
        buildAndRunCommand(builder -> {
            builder.addScript(new ExternalDXLScript(scriptFile));
        });
    }

    @Override
    public void runScript(final String dxlCode) throws DoorsException {
        buildAndRunCommand(builder -> {
            builder.addScript(new InMemoryDXLScript(dxlCode));
        });
    }

    @Override
    public void print(final String message) {
        try {
            buildAndRunCommand(builder -> {
                builder.addScript(new InternalDXLScript("print.dxl"));
                builder.setVariable("message", message);
            });
        } catch (final DoorsException e) {
            // This should never happen, because the script never calls 'throw'.
            throw new DoorsRuntimeException();
        }
    }

    @Override
    public ItemRef getRoot() {
        return new ItemRefImpl(this, "/", DoorsItemType.FOLDER);
    }

    @Override
    public ItemRef getItem(final String path) {
        return new ItemRefImpl(this, path, null);
    }

    public String buildAndRunCommand(final Consumer<DoorsScriptBuilder> prepareScriptBuilder) throws DoorsException {
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

    private String runCommand() throws IOException, DoorsException {

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

        MyTailer t = null;
        if (redirectOutput) {
            scriptBuilder.addScript(new InternalDXLScript("lib/redirect_output_post.dxl"));

            final File outputFile = getTempFile();
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
            final String message = FileUtils.readFileToString(exceptionFile, Charset.forName("Cp1252"));
            if (!message.isEmpty()) {
                throw new DoorsException(message);
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

        final String[] cmdLine = new String[] { doorsPath, "-b", f.getAbsolutePath(), "-d", doorsServer, "-u", user, "-P", "xxxx" };

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
