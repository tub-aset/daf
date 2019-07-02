package de.jpwinkler.daf.bridge;

/*-
 * #%L
 * dafcore
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
import de.jpwinkler.daf.bridge.model.DoorsBridgeDatabaseFactory;
import de.jpwinkler.daf.db.DatabaseFactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author fwiesweg
 */
public class DoorsApplicationDummyImpl implements DoorsApplication {

    private final ArrayList<Function<DXLScriptBuilder, String>> dataFakers = new ArrayList<>();

    private static final Random ERROR_RANDOM = new Random(0);
    private final double errorProbability;

    public DoorsApplicationDummyImpl(double errorProbability) {
        this.errorProbability = errorProbability;
        dataFakers.add(bld -> {
            if (bld.getSource() == null || !bld.getSource().toString().endsWith("dxl/get_children.dxl")) {
                return null;
            }

            switch (bld.getVariable("folder").findFirst().map(p -> p.getRight()).orElse(null)) {
                case "/":
                    return "Project:test\r\nProject:test 2";
                case "/test":
                    return "Formal:My module\r\nFolder:subtest";
                case "/test/subtest":
                    return "Formal:My sub module\r\nFormal:My submodule 2";
                default:
                    return "";

            }
        });
        dataFakers.add(bld -> {
            if (bld.getSource() == null || !bld.getSource().toString().endsWith("dxl/get_module_attributes.dxl")) {
                return null;
            }

            return "\"__view__\",\"" + DoorsApplication.STANDARD_VIEW + "\"\r\n\"Description\",\"Some random module\"";
        });
        dataFakers.add(bld -> {
            if (bld.getSource() == null || !bld.getSource().toString().endsWith("dxl/export_csv_single.dxl")) {
                return null;
            }

            try (FileOutputStream fos = new FileOutputStream(bld.getVariable("file").findFirst().map(p -> p.getRight()).orElse(null));
                    InputStream is = DoorsApplicationDummyImpl.class.getClassLoader().getResourceAsStream("dummy.csv")) {

                IOUtils.copy(is, fos);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            return "";
        });
    }

    @Override
    public void ack(String message) {
    }

    @Override
    public void close() {
    }

    @Override
    public DatabaseFactory getDatabaseFactory() {
        return new DoorsBridgeDatabaseFactory(this);
    }

    @Override
    public boolean isSilentMode() {
        return false;
    }

    @Override
    public void print(String message) {
    }

    @Override
    public String runScript(Consumer<DXLScriptBuilder> prepareScriptBuilder) {
        if (errorProbability > 0 && ERROR_RANDOM.nextDouble() < errorProbability) {
            throw new DoorsRuntimeException("Random failure injection");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            throw new RuntimeException();
        }

        DXLScriptBuilder scriptBuilder = new DXLScriptBuilder();
        prepareScriptBuilder.accept(scriptBuilder);

        return dataFakers.stream()
                .map(f -> f.apply(scriptBuilder))
                .filter(m -> m != null)
                .findAny()
                .orElseThrow(() -> new UnsupportedOperationException("Script " + Objects.toString(scriptBuilder.getSource()) + " not implemented"));
    }

}
