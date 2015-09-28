package de.jpwinkler.daf.dafcore.csv;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

public class SimpleModuleWriter extends ModuleWriter {

    private static final Logger LOGGER = Logger.getLogger(SimpleModuleWriter.class.getName());

    public SimpleModuleWriter(final OutputStream out, final Charset cs) {
        super(out, cs);
    }

    public SimpleModuleWriter(final OutputStream out, final CharsetEncoder enc) {
        super(out, enc);
    }

    public SimpleModuleWriter(final OutputStream out, final String charsetName) throws UnsupportedEncodingException {
        super(out, charsetName);
    }

    public SimpleModuleWriter(final OutputStream out) {
        super(out);
    }

    private Function<DoorsObject, String> objectAnnotationFunction = null;

    @Override
    public void writeModule(final DoorsModule module) {

        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                try {
                    writeObject(object);
                } catch (final IOException e) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                    return false;
                }
                return true;
            }
        });

    }

    public void setObjectAnnotationFunction(final Function<DoorsObject, String> objectAnnotationFunction) {
        this.objectAnnotationFunction = objectAnnotationFunction;
    }

    private void writeObject(final DoorsObject object) throws IOException {
        final StringBuilder builder = new StringBuilder();
        for (int level = 1; level < object.getObjectLevel(); level++) {
            builder.append("  ");
        }
        if (object.isHeading()) {
            builder.append(object.getObjectNumber());
            builder.append(" ");
            builder.append(object.getObjectHeading());
        } else {
            builder.append(object.getObjectText());
        }

        if (objectAnnotationFunction != null) {
            builder.append(" ");
            builder.append(objectAnnotationFunction.apply(object));
        }
        builder.append("\n");

        append(builder.toString());
        flush();

    }

}
