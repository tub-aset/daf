package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.FileNotFoundException;
import java.io.IOException;

import de.jpwinkler.daf.doorsdb.DoorsDBInterface;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.libs.doorsbridge.DoorsApplication;
import de.jpwinkler.libs.doorsbridge.DoorsApplicationFactory;
import de.jpwinkler.libs.doorsbridge.DoorsException;
import de.jpwinkler.libs.doorsbridge.ItemRef;

public class DownloadAllTask {

    private static DoorsDBInterface db;
    private static DoorsApplication app;

    public static void main(final String[] args) throws FileNotFoundException, IOException, DoorsException {

        db = DoorsDBInterface.getDefaultDatabase();

        app = DoorsApplicationFactory.getDoorsApplication();

        final ItemRef root = app.getItem("/Chassis");

        download(root);

    }

    private static void download(final ItemRef i) throws DoorsException, IOException {


        if (i.getItemName().getName().startsWith("Z - ") || i.getItemName().getName().startsWith("T - ") || i.getItemName().getName().startsWith("Old - ") || i.getItemName().getName().startsWith("H - ") || i.getItemName().getName().equals("_Archiv")) {
            return;
        }

        for (final ItemRef child : i.getChildren()) {
            switch (child.getType()) {
            case FOLDER:
            case PROJECT:
                download(child);
                break;
            case FORMAL:
                System.out.println(child.getItemName().getFullName().length());
                if (child.getItemName().getFullName().length() > 220) {
                    return;
                }
                final DBModule module = db.getModule(child.getItemName().getFullName());
                if (module == null) {
                    System.out.println("download " + child.getItemName().getFullName());
                    db.addModule(child.getItemName().getFullName());
                } else {
                    System.out.println("skip     " + child.getItemName().getFullName());
                }
                break;
            default:
                break;
            }
        }

    }

}
