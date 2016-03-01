package de.jpwinkler.libs.doorsbridge;

import de.jpwinkler.libs.doorsbridge.internal.DoorsApplicationImpl;

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
