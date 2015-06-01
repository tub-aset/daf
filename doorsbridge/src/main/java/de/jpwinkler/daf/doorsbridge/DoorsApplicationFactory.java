package de.jpwinkler.daf.doorsbridge;

import de.jpwinkler.daf.doorsbridge.internal.DoorsApplicationImpl;

public class DoorsApplicationFactory {

    public static DoorsApplication getDoorsApplication() {
        return new DoorsApplicationImpl();
    }

    public static DoorsApplication getBatchModeDoorsApplication(final String doorsServer, final String project, final String user, final String password) {
        final DoorsApplicationImpl doorsApplicationImpl = new DoorsApplicationImpl();
        doorsApplicationImpl.initBatchMode(doorsServer, project, user, password);
        return doorsApplicationImpl;
    }

}
