package de.jpwinkler.daf.doorsdb;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;

public class App {

    public static void main(final String[] args) throws IOException, ParseException {

        final DoorsDBInterface db = DoorsDBInterface.createOrOpenDB(new File("C:/WORK/DoorsDB/db.doorsdbmodel"));

        for (final Document doc : db.findObjects("Signal", 10)) {
            System.out.println(doc.get("type") + ": " + doc.get("text") + "  -  " + doc.get("source"));
        }
    }

}
