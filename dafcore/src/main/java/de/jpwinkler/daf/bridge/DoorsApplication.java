/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.bridge;

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
    String runScript(final Consumer<DoorsScriptBuilder> prepareScriptBuilder);

}
