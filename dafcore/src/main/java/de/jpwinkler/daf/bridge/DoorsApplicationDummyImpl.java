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
import java.net.URI;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author fwiesweg
 */
public class DoorsApplicationDummyImpl implements DoorsApplication {

    private final HashMap<Predicate<URI>, Function<DoorsScriptBuilder, String>> dataFakers = new HashMap<>();

    private static final Random ERROR_RANDOM = new Random(0);
    private final double errorProbability;

    public DoorsApplicationDummyImpl(double errorProbability) {
        this.errorProbability = errorProbability;
        dataFakers.put(uri -> uri.toString().endsWith("dxl/get_children.dxl"), b -> {

            switch (b.getVariables().get("folder")) {
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
        dataFakers.put(uri -> uri.toString().endsWith("dxl/get_module_attributes.dxl"), b -> "\"__view__\",\"" + DoorsApplication.STANDARD_VIEW + "\"\r\n\"Description\",\"Some random module\"");
        dataFakers.put(uri -> uri.toString().endsWith("dxl/export_csv_single.dxl"), b -> {
            try (FileOutputStream fos = new FileOutputStream(b.getVariables().get("file"));
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
    public String runScript(Consumer<DoorsScriptBuilder> prepareScriptBuilder) {
        if (errorProbability > 0 && ERROR_RANDOM.nextDouble() < errorProbability) {
            throw new RuntimeException();
        }

        DoorsScriptBuilder builder = new DoorsScriptBuilder();
        prepareScriptBuilder.accept(builder);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            throw new RuntimeException();
        }

        return dataFakers.entrySet().stream()
                .flatMap(entry -> builder.getScripts().stream()
                .map(sc -> sc.getSource())
                .filter(entry.getKey())
                .peek(uri -> System.out.println("Faking " + uri.toString()))
                .map(uri -> entry.getValue().apply(builder)))
                .findAny().orElse("");
    }

}
