package de.jpwinkler.daf.bridge;

/*-
 * #%L
 * dafcore
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import de.jpwinkler.daf.db.DatabaseFactory;
import java.util.function.Consumer;

/**
 *
 * @author fwiesweg
 */
public interface DoorsApplication {
    
    public static final String STANDARD_VIEW = "Standard view";

    /**
     * Shows a message dialog in DOORS.
     *
     * @param message The message to be shown.
     */
    void ack(final String message);

    void close();

    DatabaseFactory getDatabaseFactory();

    boolean isSilentMode();

    /**
     * Prints a messaged using the DOORS 'print' command.
     *
     * @param message The message to be printed.
     */
    void print(final String message);

    /**
     * Runs a DXL script.
     *
     * @param prepareScriptBuilder Function receiving a builder to create as
     * script with.
     * @throws DoorsRuntimeException If the script fails or executes 'throw()'
     */
    String runScript(final Consumer<DXLScriptBuilder> prepareScriptBuilder);

    String getDatabaseView();

}
