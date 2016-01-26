package de.jpwinkler.libs.doorsbridge;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public interface DoorsApplication {

    void ack(String message) throws DoorsException, IOException;

    void exportModuleToCSV(String modulePath, File file) throws DoorsException, IOException;

    void exportModuleToCSV(DoorsURL url, File file) throws DoorsException, IOException;

    void exportModuleToCSV(String modulePath, File file, String view) throws DoorsException, IOException;

    void exportModuleToCSV(DoorsURL url, File file, String view) throws DoorsException, IOException;

    void exportModulesToCSV(File moduleListFile, File targetFolder) throws IOException, DoorsException;

    void gotoObject(String modulePath, int absoluteNumber) throws DoorsException, IOException;

    void gotoObject(DoorsURL url, int absoluteNumber) throws DoorsException, IOException;

    boolean isDoorsRunning();

    void redirectOutput(OutputStream stream);
}
