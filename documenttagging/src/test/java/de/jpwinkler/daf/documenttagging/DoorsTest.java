package de.jpwinkler.daf.documenttagging;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.documenttagging.doors.DoorsDocumentAccessor;

public class DoorsTest {

    @Test
    public void test() throws IOException, CSVParseException {

        final DoorsModule module = new ModuleCSVParser().parseCSV(new File("../maxent/testdata/slh-wwc.csv"));

        final DoorsDocumentAccessor accessor = new DoorsDocumentAccessor(module);


    }

}
