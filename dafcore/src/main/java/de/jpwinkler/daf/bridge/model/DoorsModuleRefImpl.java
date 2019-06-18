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
package de.jpwinkler.daf.bridge.model;

import de.jpwinkler.daf.bridge.DXLScript;
import de.jpwinkler.daf.bridge.DoorsApplication;
import de.jpwinkler.daf.bridge.DoorsItemType;
import de.jpwinkler.daf.bridge.DoorsRuntimeException;
import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import de.jpwinkler.daf.db.ModuleCSV;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.FindObjectVisitor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

class DoorsModuleRefImpl extends DoorsTreeNodeRefImpl implements DoorsModule {

    private static final CSVFormat FORMAT = CSVFormat.newFormat(',')
            .withQuote('"')
            .withEscape('\\')
            .withIgnoreSurroundingSpaces()
            .withRecordSeparator("\r\n");

    private Map<String, String> moduleAttributes = null;
    private List<DoorsTreeNode> children;
    private List<String> objectAttributes;

    public DoorsModuleRefImpl(final DoorsApplication doorsApplicationImpl, final DoorsTreeNode parent, final String name) {
        super(doorsApplicationImpl, DoorsItemType.FORMAL, parent, name);
    }

    @Override
    public synchronized Map<String, String> getAttributes() {
        if (moduleAttributes == null) {
            String result = doorsApplication.runScript(builder -> {
                builder.addLibrary(DXLScript.fromResource("lib/utils.dxl"));
                builder.addLibrary(DXLScript.fromResource("lib/export_mmd.dxl"));
                builder.addScript(DXLScript.fromResource("get_module_attributes.dxl"));
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
    public CompletableFuture<Map<String, String>> getAttributesAsync(BackgroundTaskExecutor executor) {
        return executor.runBackgroundTask("Load attributes", i -> this.getAttributes());
    }

    @Override
    public List<DoorsTreeNode> getChildren() {
        if (children == null) {
            String view = this.getAttributes().get("__view__");

            try {
                Path tempFile = Files.createTempFile(null, null);

                doorsApplication.runScript(builder -> {
                    builder.addLibrary(DXLScript.fromResource("lib/utils.dxl"));
                    builder.addLibrary(DXLScript.fromResource("lib/export_csv.dxl"));
                    builder.addLibrary(DXLScript.fromResource("lib/export_mmd.dxl"));
                    builder.addScript(DXLScript.fromResource("export_csv_single.dxl"));
                    builder.setVariable("url", null);
                    builder.setVariable("name", this.getFullName());
                    builder.setVariable("view", view);
                    builder.setVariable("file", tempFile.toAbsolutePath().toString());
                });

                DoorsModule loadedModule = ModuleCSV.readModule(doorsApplication.getDatabaseFactory(), tempFile.toFile());
                this.children = loadedModule.getChildren();
                this.children.forEach(c -> c.setParent(this));
                this.objectAttributes = loadedModule.getObjectAttributes();

                doorsApplication.runScript(builder -> {
                    builder.addLibrary(DXLScript.fromResource("lib/utils.dxl"));
                    builder.addScript(DXLScript.fromResource("close_module.dxl"));
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
    public CompletableFuture<List<String>> getObjectAttributesAsync(BackgroundTaskExecutor executor) {
        return executor.runBackgroundTask("Load object attributes", i -> this.getObjectAttributes());
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
