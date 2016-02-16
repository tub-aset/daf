package de.jpwinkler.libs.doorsbridge;

import de.jpwinkler.libs.doorsbridge.internal.DoorsApplicationImpl;

public class DoorsApplicationFactory {

    public static DoorsApplication getDoorsApplication() {
        return new DoorsApplicationImpl();
    }

    // public static DoorsApplication getBatchModeDoorsApplication(final String
    // doorsServer, final String user, final String password) {
    // final DoorsApplicationImpl doorsApplicationImpl = new
    // DoorsApplicationImpl();
    // doorsApplicationImpl.initBatchMode(doorsServer, user, password);
    // return doorsApplicationImpl;
    // }

}
