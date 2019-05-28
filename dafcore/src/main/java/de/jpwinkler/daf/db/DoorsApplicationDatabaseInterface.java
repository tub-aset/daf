/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.db;

import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsApplicationFactory;
import de.jpwinkler.daf.model.DoorsDatabase;
import de.jpwinkler.daf.model.DoorsFactory;

/**
 *
 * @author fwiesweg
 */
public class DoorsApplicationDatabaseInterface implements DatabaseInterface {

    private final DoorsApplication doorsApplication = DoorsApplicationFactory.getDoorsApplication();
    private final DoorsDatabase db;

    DoorsApplicationDatabaseInterface() {
        this.db = DoorsFactory.eINSTANCE.createDoorsDatabase();
        this.db.setRoot(doorsApplication.getRoot());
    }

    @Override
    public DoorsDatabase getDatabaseObject() {
        return db;
    }

}
