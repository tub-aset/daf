package de.jpwinkler.libs.doorsbridge;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public interface DoorsApplication {

    void beginBatchMode();

    void endBatchMode() throws DoorsException, IOException;

    void ack(String message) throws DoorsException, IOException;

    ModuleRef openModule(DoorsURL url) throws IOException, DoorsException;

    ModuleRef openModule(String name) throws IOException, DoorsException;

    void exportModulesToCSV(File moduleListFile, File targetFolder) throws IOException, DoorsException;

    void runScript(File scriptFile) throws DoorsException, IOException;

    void runScript(String dxlCode) throws DoorsException, IOException;

    boolean isDoorsRunning();

    void redirectOutput(OutputStream stream);
}
