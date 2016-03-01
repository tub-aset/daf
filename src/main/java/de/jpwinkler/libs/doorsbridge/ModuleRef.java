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
