package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.Marker;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.RuleContext;

public class ModuleCSVWriter extends DoorsTreeNodeVisitor {

    private CSVPrinter csvPrinter;
    private List<String> attributes;

    private RuleContext context;

    private static final CSVFormat FORMAT = CSVFormat.DEFAULT.withRecordSeparator("\n").withQuote('\"');

    public void writeWithMarkers(final DoorsModule module, final RuleContext context, final String filename) throws IOException {
        this.context = context;
        csvPrinter = new CSVPrinter(new FileWriter(new File(filename)), FORMAT);
        if (!module.getChildren().isEmpty()) {
            attributes = new ArrayList<>();
            for (final String attribute : module.getChildren().get(0).getAttributes().keySet()) {
                attributes.add(attribute);
            }
            final List<String> header = new ArrayList<>(attributes);
            header.add("srstp-markers");
            csvPrinter.printRecord(header);
            module.accept(this);
        }
        csvPrinter.close();
    }

    @Override
    public boolean visitPreTraverse(final DoorsObject object) {
        final List<String> record = new ArrayList<>();
        for (final String attribute : attributes) {
            record.add(object.getAttributes().get(attribute));
        }
        String markers = "";
        for (final Marker marker : context.getMarkers(object)) {
            markers += marker.toString() + ", ";
        }
        record.add(markers);
        try {
            csvPrinter.printRecord(record);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
