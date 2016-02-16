package de.jpwinkler.libs.doorsbridge;

import java.io.File;
import java.io.IOException;

public interface ModuleRef {

    void exportToCSV(File file) throws DoorsException, IOException;

    void exportToCSV(File file, String view) throws DoorsException, IOException;

    void gotoObject(int absoluteNumber) throws DoorsException, IOException;

    void close() throws IOException, DoorsException;

}
