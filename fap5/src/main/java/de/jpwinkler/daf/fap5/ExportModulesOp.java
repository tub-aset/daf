package de.jpwinkler.daf.fap5;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.workflow.AbstractStepImpl;
import de.jpwinkler.daf.dafcore.workflow.ModelOperationImpl;
import de.jpwinkler.daf.doorsbridge.DoorsApplication;
import de.jpwinkler.daf.doorsbridge.DoorsApplicationFactory;
import de.jpwinkler.daf.doorsbridge.DoorsException;
import de.jpwinkler.daf.fap5.util.LogOutputStream;

public class ExportModulesOp extends AbstractStepImpl implements ModelOperationImpl {

    private static final Logger LOGGER = Logger.getLogger(ExportModulesOp.class.getName());

    @Override
    public ModelObject execute() {

        final DoorsApplication app = DoorsApplicationFactory.getBatchModeDoorsApplication(getStringVariable("database"), getStringVariable("username"), getStringVariable("password"));
        app.redirectOutput(new LogOutputStream(LOGGER, Level.INFO));
        try {
            final File folder = new File(getStringVariable("exportFolder"));
            if (!folder.exists()) {
                FileUtils.forceMkdir(folder);
            }
            app.exportModulesToCSV(new File(getStringVariable("moduleList")), folder);
        } catch (IOException | DoorsException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
