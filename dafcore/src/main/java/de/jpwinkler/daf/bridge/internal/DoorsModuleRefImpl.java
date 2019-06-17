/*
 * doorsbridge - A library for Java to Doors interaction.
 * Copyright (C) 2016 Jonas Winkler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.jpwinkler.daf.bridge.internal;

import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.bridge.DoorsRuntimeException;
import de.jpwinkler.daf.bridge.DoorsTreeNodeRef;
import de.jpwinkler.daf.db.ModuleCSV;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.FindObjectVisitor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

class DoorsModuleRefImpl extends DoorsTreeNodeRefImpl implements DoorsTreeNodeRef, DoorsModule {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withRecordSeparator("\r\n");

    private Map<String, String> moduleAttributes = null;
    private List<DoorsTreeNode> children;
    private List<String> objectAttributes;

    public DoorsModuleRefImpl(final DoorsApplicationImpl doorsApplicationImpl, final DoorsTreeNodeRef parent, final String name) {
        super(doorsApplicationImpl, DoorsItemType.FORMAL, parent, name);
    }

    @Override
    public Map<String, String> getAttributes() {
        if (moduleAttributes == null) {
            String result = doorsApplicationImpl.buildAndRunCommand(builder -> {
                builder.addLibrary(new InternalDXLScript("lib/utils.dxl"));
                builder.addLibrary(new InternalDXLScript("lib/export_mmd.dxl"));
                builder.addScript(new InternalDXLScript("get_module_attributes.dxl"));
                builder.setVariable("url", null);
                builder.setVariable("name", this.getFullName());
            });

            try (CSVParser parser = CSVParser.parse(result, FORMAT)) {
                this.moduleAttributes = parser.getRecords().stream().collect(Collectors.toMap(record -> record.get(0), record -> record.get(1)));
            } catch (final IOException e) {
                throw new DoorsRuntimeException(e);
            }
        }

        return this.moduleAttributes;
    }

    @Override
    public List<DoorsTreeNode> getChildren() {
        if (children == null) {
            String view = this.getAttributes().get("__view__");

            try {
                Path tempFile = Files.createTempFile(null, null);

                doorsApplicationImpl.buildAndRunCommand(builder -> {
                    builder.addLibrary(new InternalDXLScript("lib/utils.dxl"));
                    builder.addLibrary(new InternalDXLScript("lib/export_csv.dxl"));
                    builder.addLibrary(new InternalDXLScript("lib/export_mmd.dxl"));
                    builder.addScript(new InternalDXLScript("export_csv_single.dxl"));
                    builder.setVariable("url", null);
                    builder.setVariable("name", this.getFullName());
                    builder.setVariable("view", view);
                    builder.setVariable("file", tempFile.toAbsolutePath().toString());
                });

                DoorsModule loadedModule = ModuleCSV.readModule(tempFile.toFile());
                this.children = loadedModule.getChildren().stream().map(c -> DoorsModelUtil.createCopy(c, this))
                        .collect(Collectors.toList());
                this.objectAttributes = loadedModule.getObjectAttributes();

                doorsApplicationImpl.buildAndRunCommand(builder -> {
                    builder.addLibrary(new InternalDXLScript("lib/utils.dxl"));
                    builder.addScript(new InternalDXLScript("close_module.dxl"));
                    builder.setVariable("url", null);
                    builder.setVariable("name", this.getFullName());
                });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        return children;
    }

    @Override
    public String getView() {
        return getAttributes().get("__view__");
    }

    @Override
    public DoorsObject findObject(String objectIdentifier) {
        final FindObjectVisitor visitor = new FindObjectVisitor(objectIdentifier);
        accept(visitor);
        return visitor.getObject();
    }

    @Override
    public List<String> getObjectAttributes() {
        if (this.objectAttributes == null) {
            // objectAttributes is loaded by getChildren
            getChildren();
        }
        return objectAttributes;
    }

    @Override
    public void setObjectAttributes(List<String> attrs) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
    }

}
