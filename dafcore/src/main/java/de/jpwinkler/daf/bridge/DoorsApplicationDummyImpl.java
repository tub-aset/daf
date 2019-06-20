/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.jpwinkler.daf.bridge;

import de.jpwinkler.daf.bridge.model.DoorsBridgeDatabaseFactory;
import de.jpwinkler.daf.db.DatabaseFactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author fwiesweg
 */
public class DoorsApplicationDummyImpl implements DoorsApplication {

    private final HashMap<Predicate<URI>, Function<DoorsScriptBuilder, String>> dataFakers = new HashMap<>();

    public DoorsApplicationDummyImpl() {
        dataFakers.put(uri -> uri.toString().endsWith("dxl/get_children.dxl"), b -> "Folder:test\r\nFolder:test 2\r\nFormal:My module");
        dataFakers.put(uri -> uri.toString().endsWith("dxl/get_module_attributes.dxl"), b -> "\"__view__\",\"" + DoorsApplication.STANDARD_VIEW + "\"\r\n\"Description\",\"Some random module\"");
        dataFakers.put(uri -> uri.toString().endsWith("dxl/export_csv_single.dxl"), b -> {
            try (FileOutputStream fos = new FileOutputStream(b.getVariables().get("file"));
                    InputStream is = DoorsApplicationDummyImpl.class.getClassLoader().getResourceAsStream("dummy.csv")) {

                is.transferTo(fos);

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
