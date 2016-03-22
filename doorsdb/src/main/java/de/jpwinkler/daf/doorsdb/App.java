package de.jpwinkler.daf.doorsdb;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.libs.doorsbridge.DoorsException;

public class App {

    public static void main(final String[] args) throws IOException, DoorsException, CSVParseException, ParseException {
        final DoorsDBInterface dbInterface = DoorsDBInterface.createOrOpenDB(new File("C:/WORK/DoorsDB/db.doorsdbmodel"));

        // dbInterface.saveDB();

        // final DoorsApplication app =
        // DoorsApplicationFactory.getDoorsApplication();

        // traverse(app.getRoot(), dbInterface);

        dbInterface.updateAllModules();
    }

}