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
package de.jpwinkler.daf.doorsdb.bridge;

import de.jpwinkler.daf.doorsdb.bridge.internal.DoorsApplicationImpl;

public class DoorsApplicationFactory {

    /**
     * Returns a DOORS application instance for interacting with DOORS.
     * Executing commands using this instance requires a running DOORS session.
     *
     * @return
     */
    public static DoorsApplication getDoorsApplication() {
        return new DoorsApplicationImpl();
    }

    /**
     * Returns a DOORS application instance for interacting with DOORS. Each
     * command is executed separately using the doors '-b' command line switch.
     *
     * This method requires the DOORS executable to be on the system path.
     *
     * @param doorsServer
     *            Server and port of the DOORS database server in conventional
     *            DOORS format (port@server).
     * @param user
     *            Username used for authentication.
     * @param password
     *            Password used for authentication. This password is stored as a
     *            string as long as the returned DOORS application instance
     *            exists.
     * @return
     */
    public static DoorsApplication getSilentModeDoorsApplication(final String doorsServer, final String user, final String password) {
        final DoorsApplicationImpl doorsApplicationImpl = new DoorsApplicationImpl();
        doorsApplicationImpl.initSilentMode("doors.exe", doorsServer, user, password);
        return doorsApplicationImpl;
    }

    /**
     * Returns a DOORS application instance for interacting with DOORS. Each
     * command is executed separately using the doors '-b' command line switch.
     *
     * @param doorsPath
     *            Full path to the DOORS executable.
     * @param doorsServer
     *            Server and port of the DOORS database server in conventional
     *            DOORS format (port@server).
     * @param user
     *            Username used for authentication.
     * @param password
     *            Password used for authentication. This password is stored as a
     *            string as long as the returned DOORS application instance
     *            exists.
     * @return
     */
    public static DoorsApplication getSilentModeDoorsApplication(final String doorsPath, final String doorsServer, final String user, final String password) {
        final DoorsApplicationImpl doorsApplicationImpl = new DoorsApplicationImpl();
        doorsApplicationImpl.initSilentMode(doorsPath, doorsServer, user, password);
        return doorsApplicationImpl;
    }

}
