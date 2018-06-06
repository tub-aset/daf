package de.jpwinkler.daf.dafimpl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.ModuleCSVWriter;
import de.jpwinkler.daf.dafcore.model.csv.AttributeDefinition;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.util.CSVParseException;

public class MakeCsvAnonymous extends DoorsTreeNodeVisitor {

    private final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z0-9]+(_[a-zA-Z0-9]+)+");

    private int reqCounter = 1;
    private final Map<String, String> identifierMap = new HashMap<>();

    public static void main(final String[] args) throws IOException, CSVParseException {

        new MakeCsvAnonymous().run("C:\\WORK\\tubcloud\\ASE-Studie 2\\reference_data\\HandsFreeAccess.csv", "C:\\WORK\\tubcloud\\ASE-Studie 2\\experiment_data\\Gruppe A\\Runde_1_HandsFreeAccess.csv");
        System.out.println("------");
        new MakeCsvAnonymous().run("C:\\WORK\\tubcloud\\ASE-Studie 2\\reference_data\\WiperControl.csv", "C:\\WORK\\tubcloud\\ASE-Studie 2\\experiment_data\\Gruppe B\\Runde_2_WiperControl.csv");

    }

    @Override
    public boolean visitPreTraverse(final DoorsObject object) {
        final Matcher matcher = NAME_PATTERN.matcher(object.getText());

        final StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            final String identifier = matcher.group(0).toLowerCase();
            String replacement = null;
            if (!identifierMap.containsKey(identifier)) {
                identifierMap.put(identifier, "SYSSIGNAL-" + (identifierMap.size() + 1));
            }
            replacement = identifierMap.get(identifier);
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);

        object.setText(sb.toString());

        object.setObjectIdentifier("ANF-" + (reqCounter++));
        object.getAttributes().put("SourceID", object.getObjectIdentifier());

        return true;
    }

    private void run(final String in, final String out) throws IOException, CSVParseException {

        final DoorsModule module = new ModuleCSVParser().parseCSV(new File(in));

        module.accept(this);

        // final AttributeDefinition ad = module.findAttributeDefinition("Actual Object Type");
        final List<AttributeDefinition> adsToDelete = new ArrayList<>();
        for (final AttributeDefinition ad : module.getAttributeDefinitions()) {
            if (!Arrays.asList("Object Identifier", "Object Level", "Object Number", "Object Text", "Object Heading", "Object Type", "SourceID").contains(ad.getName())) {
                adsToDelete.add(ad);
            }
        }
        module.getAttributeDefinitions().removeAll(adsToDelete);

        new ModuleCSVWriter(new FileOutputStream(out)).writeModule(module);

    }

}
