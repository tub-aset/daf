package de.jpwinkler.libs.doorsbridge;

import java.io.File;

public interface ModuleRef {

    /**
     * Exports the contents of the module in csv format.
     *
     * Also exports a module metadata file (csv file + ".mmd" extension),
     * containing all module properties and the module path, url and view used
     * for exporting.
     *
     * @param file
     *            The file to write the csv records to.
     * @throws DoorsException
     *             If the default view does not exist for some reason.
     */
    void exportToCSV(File file) throws DoorsException;

    /**
     * Exports the contents of the module in csv format. Switches to the given
     * view first and activates filtering, if defined for the view.
     *
     * Also exports a module metadata file (csv file + ".mmd" extension),
     * containing all module properties and the module path, url and view used
     * for exporting.
     *
     * @param file
     *            The file to write the csv records to.
     * @param view
     *            ThE view to export.
     * @throws DoorsException
     *             IF the view does not exist.
     */
    void exportToCSV(File file, String view) throws DoorsException;

    /**
     * Selects the object with the given absolute number.
     *
     * @param absoluteNumber
     */
    void gotoObject(int absoluteNumber);

    /**
     * Closes the module.
     */
    void close();

}
