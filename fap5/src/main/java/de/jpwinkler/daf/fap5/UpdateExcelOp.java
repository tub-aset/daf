package de.jpwinkler.daf.fap5;

import static de.jpwinkler.daf.fap5.util.Fap5ProgressTrackingDocument.*;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.workflow.AbstractStepImpl;
import de.jpwinkler.daf.dafcore.workflow.ModelOperationImpl;
import de.jpwinkler.daf.fap5.codebeamerrules.CodeBeamerConstants;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.util.DoorsModuleMap;
import de.jpwinkler.daf.fap5.util.Fap5ProgressTrackingDocument;
import de.jpwinkler.daf.fap5.util.Fap5ProgressTrackingRow;

public class UpdateExcelOp extends AbstractStepImpl implements ModelOperationImpl {

    private static final Logger LOGGER = Logger.getLogger(UpdateExcelOp.class.getName());

    @Override
    public ModelObject execute() {

        // final Map<String, CodeBeamerModel> models = new HashMap<>();
        final DoorsModuleMap models = new DoorsModuleMap();

        for (final CodeBeamerModel model : getModels()) {
            models.put(model);
        }

        try {
            final String sourceExcelFile = getStringVariable("sourceExcelFile");
            final String targetExcelFile = getStringVariable("targetExcelFile");
            final String backupExcelFile = getStringVariable("backupExcelFile");

            if (backupExcelFile != null && new File(backupExcelFile).exists()) {
                throw new RuntimeException("Backup file already exists.");
            }

            if (sourceExcelFile == null || !new File(sourceExcelFile).exists()) {
                throw new RuntimeException("Source file does not exist or no source file given.");
            }

            if (targetExcelFile == null) {
                throw new RuntimeException("No target file given.");
            }

            if (sourceExcelFile.equalsIgnoreCase(targetExcelFile) && backupExcelFile == null) {
                throw new RuntimeException("Source file and target file are the same, but no backup file given.");
            }

            if (backupExcelFile != null) {
                FileUtils.copyFile(new File(sourceExcelFile), new File(backupExcelFile));
            }

            final Fap5ProgressTrackingDocument progressTrackingDocument = new Fap5ProgressTrackingDocument(sourceExcelFile);

            for (final Fap5ProgressTrackingRow row : progressTrackingDocument.getRows()) {
                if (row.hasColumn(COLUMN_SOURCE_PATH) && row.hasColumn(COLUMN_SOURCE_MODULE)) {
                    final CodeBeamerModel sourceModel = models.get(row.get(COLUMN_SOURCE_PATH), row.get(COLUMN_SOURCE_MODULE), null);
                    if (sourceModel != null) {
                        final Integer reqCount = sourceModel.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT);
                        row.update(COLUMN_REQ_COUNT, reqCount);
                        row.update(COLUMN_TODO_COUNT, sourceModel.getIntMetric(CodeBeamerConstants.METRIC_OPEN_TODOS));
                        if (sourceModel.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT) > 0) {
                            row.updatePercent(COLUMN_MATURITY_OPEN, (double) sourceModel.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_OPEN_COUNT) / reqCount);
                            row.updatePercent(COLUMN_MATURITY_SPECIFIED, (double) sourceModel.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_SPECIFIED_COUNT) / reqCount);
                            row.updatePercent(COLUMN_MATURITY_FOLLOW_UP, (double) sourceModel.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_COUNT) / reqCount);
                            row.updatePercent(COLUMN_MATURITY_FOLLOW_UP_HASHTAGS, (double) sourceModel.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_HASHTAGS_COUNT) / reqCount);
                            row.updatePercent(COLUMN_MATURITY_AGREED, (double) sourceModel.getIntMetric(CodeBeamerConstants.METRIC_MATURITY_AGREED_COUNT) / reqCount);
                        }
                        final Integer emptyObjectType = sourceModel.getIntMetric(CodeBeamerConstants.METRIC_EMPTY_OBJECT_TYPE);
                        final Integer requirementAsHeading = sourceModel.getIntMetric(CodeBeamerConstants.METRIC_HEADING_AS_REQUIREMENT_COUNT);
                        final Integer informationWithLink = sourceModel.getIntMetric(CodeBeamerConstants.METRIC_INFORMATION_WITH_LINK);
                        final Integer requirementWithoutLink = sourceModel.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_WITHOUT_LINK);
                        row.update(COLUMN_EMPTY_OBJECT_TYPE_COUNT, emptyObjectType);
                        row.update(COLUMN_REQUIREMENT_AS_HEADING_COUNT, requirementAsHeading);
                        row.update(COLUMN_INFORMATION_WITH_LINK_COUNT, informationWithLink);
                        row.update(COLUMN_REQUIREMENT_WITHOUT_LINK_COUNT, requirementWithoutLink);

                        row.update(COLUMN_SUM, emptyObjectType + requirementAsHeading + informationWithLink + requirementWithoutLink);
                    }
                }

                if (row.hasColumn(COLUMN_INBOX_MODULE_EXISTS) && row.get(COLUMN_INBOX_MODULE_EXISTS).toLowerCase().equals("ja") && row.hasColumn(COLUMN_TARGET_PATH) && row.hasColumn(COLUMN_TARGET_MODULE)) {
                    final CodeBeamerModel inboxTargetModel = models.get(row.get(COLUMN_TARGET_PATH), row.get(COLUMN_TARGET_MODULE), row.get(COLUMN_INBOX_VIEW));
                    if (inboxTargetModel != null) {
                        final Integer reqCount = inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT);

                        row.update(COLUMN_INBOX_REQ_COUNT, inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT));
                        row.updatePercent(COLUMN_INBOX_ACCEPTANCE_EMPTY, (double) inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_NONE_COUNT) / reqCount);
                        row.updatePercent(COLUMN_INBOX_ACCEPTANCE_DELETED, (double) inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_DELETED_REQ_COUNT) / reqCount);
                        row.updatePercent(COLUMN_INBOX_ACCEPTANCE_CHANGED, (double) inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_CHANGED_REQ_COUNT) / reqCount);
                        row.updatePercent(COLUMN_INBOX_ACCEPTANCE_TO_CLARIFY, (double) inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_TO_CLARIFY_COUNT) / reqCount);
                        row.updatePercent(COLUMN_INBOX_ACCEPTANCE_CONFLICT, (double) inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_CONFLICT_COUNT) / reqCount);
                        row.updatePercent(COLUMN_INBOX_ACCEPTANCE_PARTLY_AGREED, (double) inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_PARTLY_AGREED_COUNT) / reqCount);
                        row.updatePercent(COLUMN_INBOX_ACCEPTANCE_AGREED, (double) inboxTargetModel.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_AGREED_COUNT) / reqCount);
                    }

                    if (row.get(COLUMN_TARGET_PATH).contains("/Inbox/")) {

                        final Map<String, CodeBeamerModel> verifiedTargetModels = models.get(row.get(COLUMN_TARGET_PATH).replace("/Inbox/", "/Verified/"), row.get(COLUMN_TARGET_MODULE));
                        row.update(COLUMN_VERIFIED_MODULE_EXISTS, verifiedTargetModels != null ? "Ja" : "Nein");
                        if (verifiedTargetModels != null && row.hasColumn(COLUMN_VERIFIED_VIEW)) {
                            final String[] views = row.get(COLUMN_VERIFIED_VIEW).split("\n");

                            row.update(COLUMN_VERIFIED_REQ_COUNT, manyInts(views, verifiedTargetModels, m -> m.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT)));

                            row.update(COLUMN_VERIFIED_ACCEPTANCE_EMPTY, manyPercent(views, verifiedTargetModels, m -> (double) m.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_NONE_COUNT)));
                            row.update(COLUMN_VERIFIED_ACCEPTANCE_DELETED, manyPercent(views, verifiedTargetModels, m -> (double) m.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_DELETED_REQ_COUNT)));
                            row.update(COLUMN_VERIFIED_ACCEPTANCE_CHANGED, manyPercent(views, verifiedTargetModels, m -> (double) m.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_CHANGED_REQ_COUNT)));
                            row.update(COLUMN_VERIFIED_ACCEPTANCE_TO_CLARIFY, manyPercent(views, verifiedTargetModels, m -> (double) m.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_TO_CLARIFY_COUNT)));
                            row.update(COLUMN_VERIFIED_ACCEPTANCE_CONFLICT, manyPercent(views, verifiedTargetModels, m -> (double) m.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_CONFLICT_COUNT)));
                            row.update(COLUMN_VERIFIED_ACCEPTANCE_PARTLY_AGREED, manyPercent(views, verifiedTargetModels, m -> (double) m.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_PARTLY_AGREED_COUNT)));
                            row.update(COLUMN_VERIFIED_ACCEPTANCE_AGREED, manyPercent(views, verifiedTargetModels, m -> (double) m.getIntMetric(CodeBeamerConstants.METRIC_ACCEPTANCE_AGREED_COUNT)));
                        }
                    }

                }
            }

            progressTrackingDocument.saveAs(targetExcelFile);

            progressTrackingDocument.close();

        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        return null;
    }

    private List<String> manyInts(final String[] views, final Map<String, CodeBeamerModel> models, final Function<CodeBeamerModel, Integer> f) {
        return Arrays.asList(views).stream().map(view -> models.get(view)).map(m -> m != null ? f.apply(m) : null).map(i -> i != null ? String.valueOf(i) : "-").collect(Collectors.toList());
    }

    private List<String> manyPercent(final String[] views, final Map<String, CodeBeamerModel> models, final Function<CodeBeamerModel, Double> f) {
        final DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Arrays.asList(views).stream().map(view -> models.get(view)).map(m -> m != null ? f.apply(m) / m.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT) : null).map(d -> d != null ? decimalFormat.format(d * 100) + " %" : "-").collect(Collectors.toList());
    }

    private List<CodeBeamerModel> getModels() {
        return getParameter("codeBeamerModels");
    }

}
