package de.jpwinkler.daf.fap5;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.workflow.AbstractStepImpl;
import de.jpwinkler.daf.dafcore.workflow.ModelOperationImpl;
import de.jpwinkler.daf.fap5.util.Fap5ProgressTrackingDocument;
import de.jpwinkler.daf.fap5.util.Fap5ProgressTrackingRow;
import de.jpwinkler.daf.fap5.util.LogOutputStream;
import de.jpwinkler.libs.doorsbridge.DoorsApplication;
import de.jpwinkler.libs.doorsbridge.DoorsApplicationFactory;
import de.jpwinkler.libs.doorsbridge.DoorsException;
import de.jpwinkler.libs.doorsbridge.ModuleRef;

public class ExportModulesOp extends AbstractStepImpl implements ModelOperationImpl {

    private static final Logger LOGGER = Logger.getLogger(ExportModulesOp.class.getName());
    private DoorsApplication app;
    private String exportFolder;

    @Override
    public ModelObject execute() {

        final String sourceExcelFile = getStringVariable("sourceExcelFile");
        exportFolder = getStringVariable("exportFolder");

        if (sourceExcelFile == null || !new File(sourceExcelFile).exists()) {
            throw new RuntimeException("Source file does not exist or no source file given.");
        }

        try {
            final Fap5ProgressTrackingDocument progressTrackingDocument = new Fap5ProgressTrackingDocument(sourceExcelFile);
            app = DoorsApplicationFactory.getDoorsApplication();

            app.redirectOutput(new LogOutputStream(LOGGER, Level.INFO));

            for (final Fap5ProgressTrackingRow row : progressTrackingDocument.getRows()) {
                final String targetPath = row.get(Fap5ProgressTrackingDocument.COLUMN_TARGET_PATH);
                final String targetModule = row.get(Fap5ProgressTrackingDocument.COLUMN_TARGET_MODULE);
                final String inboxView = row.get(Fap5ProgressTrackingDocument.COLUMN_INBOX_VIEW);

                try {
                    export(targetPath, targetModule, inboxView);
                } catch (final DoorsException e) {
                    LOGGER.warning(e.getMessage());
                }

                if (targetPath != null && targetPath.contains("/Inbox/")) {

                    final String[] verifiedViews = row.hasColumn(Fap5ProgressTrackingDocument.COLUMN_VERIFIED_VIEW) ? row.get(Fap5ProgressTrackingDocument.COLUMN_VERIFIED_VIEW).split("\n") : null;
                    final String targetVerifiedPath = targetPath.replace("/Inbox/", "/Verified/");

                    try {
                        if (verifiedViews != null) {
                            export(targetVerifiedPath, targetModule, verifiedViews);
                        } else {
                            export(targetVerifiedPath, targetModule, (String) null);
                        }
                    } catch (final DoorsException e) {
                        LOGGER.warning(e.getMessage());
                    }
                }
            }

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private void export(final String path, final String name, final String... views) throws DoorsException, IOException {
        if (validateModulePath(path, name)) {
            final String fullName = path + "/" + name;
            final ModuleRef moduleRef = app.openModule(fullName);
            for (final String view : views) {
                final String id = fullName + (view != null && !view.isEmpty() ? ":" + view : "");
                final File directory = new File(exportFolder + path);
                directory.mkdirs();
                final File file = new File(directory, name + "-" + view + ".csv");
                if (file.exists()) {
                    LOGGER.warning("Module already exported: " + id);
                } else {
                    LOGGER.info("Exporting " + id);
                    if (view != null && !view.isEmpty()) {
                        moduleRef.exportToCSV(file, view);
                    } else {
                        moduleRef.exportToCSV(file);
                    }
                }
            }
            moduleRef.close();
        }
    }

    private boolean validateModulePath(final String targetPath, final String targetModule) {
        return targetPath != null && !targetPath.isEmpty() && targetPath.startsWith("/") && !targetPath.contains("\n") && targetModule != null && !targetModule.isEmpty() && !targetModule.contains("\n");
    }

}
