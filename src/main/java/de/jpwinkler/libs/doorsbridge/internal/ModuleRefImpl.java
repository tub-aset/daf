package de.jpwinkler.libs.doorsbridge.internal;

import java.io.File;
import java.io.IOException;

import de.jpwinkler.libs.doorsbridge.DoorsException;
import de.jpwinkler.libs.doorsbridge.ModuleRef;
import de.jpwinkler.libs.doorsbridge.DoorsURL;

public class ModuleRefImpl implements ModuleRef {

    private static final String STANDARD_VIEW = "Standard view";

    private final DoorsApplicationImpl doorsApplicationImpl;
    private DoorsURL url;
    private String name;

    private boolean closed = false;

    public ModuleRefImpl(final DoorsApplicationImpl doorsApplicationImpl, final DoorsURL url) {
        this.doorsApplicationImpl = doorsApplicationImpl;
        this.url = url;
    }

    public ModuleRefImpl(final DoorsApplicationImpl doorsApplicationImpl, final String name) {
        this.doorsApplicationImpl = doorsApplicationImpl;
        this.name = name;
    }

    @Override
    public void exportToCSV(final File file) throws DoorsException, IOException {
        exportToCSV(file, STANDARD_VIEW);
    }

    @Override
    public void exportToCSV(final File file, final String view) throws DoorsException, IOException {
        if (closed) {
            throw new DoorsException("Module is closed.");
        }
        doorsApplicationImpl.buildAndRunCommand(builder -> {
            builder.addScript(new InternalDXLScript("utils.dxl"));
            builder.addScript(new InternalDXLScript("export_csv.dxl"));
            builder.setVariable("url", url != null ? url.getUrl() : null);
            builder.setVariable("name", name);
            builder.setVariable("view", view);
            builder.setVariable("file", file.getAbsolutePath());
        });
    }

    @Override
    public void gotoObject(final int absoluteNumber) throws DoorsException, IOException {
        if (closed) {
            throw new DoorsException("Module is closed.");
        }
        doorsApplicationImpl.buildAndRunCommand(builder -> {
            builder.addScript(new InternalDXLScript("utils.dxl"));
            builder.addScript(new InternalDXLScript("goto_object.dxl"));
            builder.setVariable("url", url != null ? url.getUrl() : null);
            builder.setVariable("name", name);
            builder.setVariable("absoluteNumber", String.valueOf(absoluteNumber));
        });
    }

    @Override
    public void close() throws IOException, DoorsException {
        if (closed) {
            throw new DoorsException("Module is closed.");
        }
        doorsApplicationImpl.buildAndRunCommand(builder -> {
            builder.addScript(new InternalDXLScript("utils.dxl"));
            builder.addScript(new InternalDXLScript("close_module.dxl"));
            builder.setVariable("url", url != null ? url.getUrl() : null);
            builder.setVariable("name", name);
        });
        closed = true;
    }

}
