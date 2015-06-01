package de.jpwinkler.daf.fap5;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.common.util.EList;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.workflow.AbstractStepImpl;
import de.jpwinkler.daf.dafcore.workflow.ModelOperationImpl;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitModel;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping;
import de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;
import de.jpwinkler.daf.fap5.model.componentssystems.Component;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;
import de.jpwinkler.daf.fap5.model.componentssystems.Functionality;
import de.jpwinkler.daf.fap5.model.componentssystems.LogicalComponent;
import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel;
import de.jpwinkler.daf.fap5.model.issuehistory.Version;

public class ExportCockpitOperation extends AbstractStepImpl implements ModelOperationImpl {

    private static final Logger LOGGER = Logger.getLogger(ExportCockpitOperation.class.getName());

    private Workbook workbook;
    private Sheet currentSheet;

    private CellStyle percentStyle;

    private CockpitModel getCockpitModel() {
        return getSingleParameter("cockpit");
    }

    private IssueHistoryModel getIssueHistoryModel() {
        return getSingleParameter("history");
    }

    @Override
    public ModelObject execute() {

        workbook = new XSSFWorkbook();
        percentStyle = workbook.createCellStyle();
        percentStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00 %"));
        exportVehicleFunctionList();
        exportFunctionContributionList();
        exportSystemsComponentsList();
        exportDocuments();
        exportIssueSummary();
        exportIssues();
        if (getIssueHistoryModel() != null) {
            exportIssueHistory();
            exprotIssueTypeHistory();
        }
        final File file = new File(getStringVariable("outFile"));
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
            Desktop.getDesktop().open(file);
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, String.format("Error while saving/opening exported document %s.", file.getAbsolutePath()), e);
        }
        return null;
    }

    private void exprotIssueTypeHistory() {
        currentSheet = workbook.createSheet("Problemtypverlauf");
        final Row headerRow = currentSheet.createRow(0);
        headerRow.createCell(0).setCellValue("Problemtyp");

        final EList<Version> versions = getIssueHistoryModel().getVersions();
        for (int versionIndex = 0; versionIndex < versions.size(); versionIndex++) {
            final Version version = versions.get(versionIndex);
            headerRow.createCell(versionIndex + 1).setCellValue(version.getVersionNumber());
        }

        final List<String> issueTypes = getIssueHistoryModel().getIssueTypes();
        Collections.sort(issueTypes);

        for (int issueTypeIndex = 0; issueTypeIndex < issueTypes.size(); issueTypeIndex++) {
            final String issueType = issueTypes.get(issueTypeIndex);
            final Row issueTypeRow = currentSheet.createRow(issueTypeIndex + 1);
            issueTypeRow.createCell(0).setCellValue(issueType);
            for (int versionIndex = 0; versionIndex < versions.size(); versionIndex++) {
                final Version version = versions.get(versionIndex);
                final EList<Issue> issues = version.getIssues(issueType);

                if (issues != null) {
                    issueTypeRow.createCell(versionIndex + 1).setCellValue(issues.stream().mapToLong(Issue::getSeverity).sum());
                }
            }
        }

        currentSheet.createFreezePane(0, 1);
        currentSheet.setAutoFilter(new CellRangeAddress(0, 0, 0, versions.size()));
        currentSheet.setColumnWidth(0, 256 * 40);
    }

    private void exportIssueSummary() {
        currentSheet = workbook.createSheet("Zusammenfassung Probleme");

        final Row headerRow = currentSheet.createRow(0);
        headerRow.createCell(0).setCellValue("Problemtyp");
        headerRow.createCell(1).setCellValue("Anzahl");
        headerRow.createCell(2).setCellValue("Gewichtet");

        for (final String issueType : getCockpitModel().getIssueTypes()) {
            final EList<Issue> issues = getCockpitModel().getIssues(issueType);

            final Row issueRow = currentSheet.createRow(currentSheet.getLastRowNum() + 1);
            issueRow.createCell(0).setCellValue(issueType);
            issueRow.createCell(1).setCellValue(issues.size());
            issueRow.createCell(2).setCellValue(issues.stream().mapToLong(Issue::getSeverity).sum());
        }

        currentSheet.createFreezePane(0, 1);
        currentSheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 2));
        currentSheet.setColumnWidth(0, 256 * 40);
        currentSheet.setColumnWidth(1, 256 * 15);
        currentSheet.setColumnWidth(2, 256 * 15);
    }

    private void exportIssueHistory() {
        currentSheet = workbook.createSheet("Problemverlauf");
        final Row headerRow = currentSheet.createRow(0);
        headerRow.createCell(0).setCellValue("Dokument");

        final EList<Version> versions = getIssueHistoryModel().getVersions();
        for (int versionIndex = 0; versionIndex < versions.size(); versionIndex++) {
            final Version version = versions.get(versionIndex);
            headerRow.createCell(versionIndex + 1).setCellValue(version.getVersionNumber());
        }

        final List<String> documentNames = getIssueHistoryModel().getDocumentNames();
        Collections.sort(documentNames);

        for (int documentIndex = 0; documentIndex < documentNames.size(); documentIndex++) {
            final String documentName = documentNames.get(documentIndex);
            final Row documentRow = currentSheet.createRow(documentIndex + 1);
            documentRow.createCell(0).setCellValue(documentName);
            for (int versionIndex = 0; versionIndex < versions.size(); versionIndex++) {
                final Version version = versions.get(versionIndex);
                final CodeBeamerModel documentModel = version.getDocument(documentName);

                if (documentModel != null) {
                    documentRow.createCell(versionIndex + 1).setCellValue(documentModel.getEstimatedRemainingWork());
                }
            }
        }

        currentSheet.createFreezePane(0, 1);
        currentSheet.setAutoFilter(new CellRangeAddress(0, 0, 0, versions.size()));
        currentSheet.setColumnWidth(0, 256 * 40);
    }

    private void exportDocuments() {
        currentSheet = workbook.createSheet("Dokumente");
        final Row headerRow = currentSheet.createRow(0);
        headerRow.createCell(0).setCellValue("Dokument");
        headerRow.createCell(1).setCellValue("Umfang");
        headerRow.createCell(2).setCellValue("Probleme (Gewichtet)");
        headerRow.createCell(3).setCellValue("Probleme (Anzahl)");
        for (final CodeBeamerModel cbm : getCockpitModel().getDocuments()) {
            final Row row = currentSheet.createRow(currentSheet.getLastRowNum() + 1);
            row.createCell(0).setCellValue(cbm.getName());
            row.createCell(1).setCellValue(cbm.getSize());
            row.createCell(2).setCellValue(cbm.getEstimatedRemainingWork());
            row.createCell(3).setCellValue(cbm.getIssues().size());
        }
        currentSheet.createFreezePane(0, 1);
        currentSheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 3));
        currentSheet.setColumnWidth(0, 256 * 40);
        currentSheet.setColumnWidth(1, 256 * 15);
        currentSheet.setColumnWidth(2, 256 * 15);
        currentSheet.setColumnWidth(3, 256 * 15);
    }

    private void exportIssues() {
        currentSheet = workbook.createSheet("Probleme");
        final Row headerRow = currentSheet.createRow(0);
        headerRow.createCell(0).setCellValue("Dokument");
        headerRow.createCell(1).setCellValue("Objekt");
        headerRow.createCell(2).setCellValue("Problem");
        for (final CodeBeamerModel cbm : getCockpitModel().getDocuments()) {
            if (!cbm.getIssues().isEmpty()) {

                for (final Issue issue : cbm.getIssues()) {
                    final Row row = currentSheet.createRow(currentSheet.getLastRowNum() + 1);
                    row.createCell(0).setCellValue(cbm.getName());
                    if (issue.getSource() != null) {
                        row.createCell(1).setCellValue(issue.getSource().getObjectNumber() + " / " + issue.getSource().getObjectIdentifier());
                    }
                    row.createCell(2).setCellValue(issue.getIssueType());
                    row.createCell(3).setCellValue(issue.getSource().getText());
                }

            }
        }
        currentSheet.createFreezePane(0, 1);
        currentSheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 2));
        currentSheet.setColumnWidth(0, 256 * 40);
        currentSheet.setColumnWidth(1, 256 * 20);
        currentSheet.setColumnWidth(2, 256 * 20);
    }

    private void exportVehicleFunctionList() {
        currentSheet = workbook.createSheet("Fahrzeugfunktionen");
        final Row heading = currentSheet.createRow(0);
        heading.createCell(0).setCellValue("Fahrzeugfunktion");
        heading.createCell(1).setCellValue("Quelle");
        heading.createCell(2).setCellValue("FS");
        heading.createCell(3).setCellValue("MP");
        heading.createCell(4).setCellValue("Beitr�ge identifiziert");
        heading.createCell(5).setCellValue("Beitr�ge spezifiziert");
        heading.createCell(6).setCellValue("%");
        for (final VehicleFunctionProgress vfp : getCockpitModel().getVehicleFunctionProgress()) {
            exportVehicleFunctionProgress(vfp, "");
        }
        currentSheet.createFreezePane(0, 1);
        currentSheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 6));
        currentSheet.setColumnWidth(0, 256 * 40);
        currentSheet.setColumnWidth(1, 256 * 15);
        currentSheet.setColumnWidth(2, 256 * 10);
        currentSheet.setColumnWidth(3, 256 * 10);
        currentSheet.setColumnWidth(4, 256 * 10);
        currentSheet.setColumnWidth(5, 256 * 10);
        currentSheet.setColumnWidth(6, 256 * 10);
    }

    private void exportVehicleFunctionProgress(final VehicleFunctionProgress vfp, final String indentation) {
        final Row row = currentSheet.createRow(currentSheet.getLastRowNum() + 1);
        row.createCell(0).setCellValue(indentation + vfp.getVehicleFunction().getName());
        row.createCell(1).setCellValue(vfp.getVehicleFunction().getSource().getObjectIdentifier());
        row.createCell(2).setCellValue(vfp.isSpecified() ? "ja" : "nein");
        row.createCell(3).setCellValue(vfp.isMapped() ? "ja" : "nein");
        row.createCell(4).setCellValue(vfp.getSpecifiedFunctionContributionCount());
        row.createCell(5).setCellValue(vfp.getTotalFunctionContributionCount());
        row.createCell(6).setCellValue(vfp.getProgress());
        row.getCell(6).setCellStyle(percentStyle);
        for (final VehicleFunctionProgress subFunctionProgress : vfp.getSubFunctionProgress()) {
            exportVehicleFunctionProgress(subFunctionProgress, indentation + "  ");
        }
    }

    private void exportSystemsComponentsList() {
        currentSheet = workbook.createSheet("Systeme und Komponenten");

        final Row heading = currentSheet.createRow(0);
        heading.createCell(0).setCellValue("Typ");
        heading.createCell(1).setCellValue("System / Komponente");
        heading.createCell(2).setCellValue("Dokument(e)");
        heading.createCell(3).setCellValue("Dokument(e) spezifiziert");
        heading.createCell(4).setCellValue("Anzahl Funktionsbeitr�ge");

        for (final FunctionContributionTargetMapping mapping : getCockpitModel().getMappings()) {
            final Row row = currentSheet.createRow(currentSheet.getLastRowNum() + 1);
            row.createCell(0).setCellValue(extracted(mapping.getFunctionContributionTarget()));
            row.createCell(1).setCellValue(mapping.getFunctionContributionTarget().getName());
            final String documents = StringUtils.join(mapping.getDocuments().stream().map(d -> d.getName()).toArray(), ", ");
            row.createCell(2).setCellValue(documents);
            final boolean specified = !mapping.getDocuments().isEmpty() && mapping.getDocuments().stream().allMatch(CodeBeamerModel::isSpecified);
            row.createCell(3).setCellValue(specified ? "ja" : "nein");
            row.createCell(4).setCellValue(mapping.getFunctionContributionTarget().getContributions().size());
        }
        currentSheet.createFreezePane(0, 1);
        currentSheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 4));
        currentSheet.setColumnWidth(0, 256 * 25);
        currentSheet.setColumnWidth(1, 256 * 40);
        currentSheet.setColumnWidth(2, 256 * 30);
        currentSheet.setColumnWidth(3, 256 * 10);
        currentSheet.setColumnWidth(4, 256 * 10);
    }

    private String extracted(final FunctionContributionTarget functionContributionTarget) {
        if (functionContributionTarget instanceof de.jpwinkler.daf.fap5.model.componentssystems.System) {
            return "System";
        } else if (functionContributionTarget instanceof Component) {
            return "Komponente";
        } else if (functionContributionTarget instanceof Functionality) {
            return "Funktionalit�t";
        } else if (functionContributionTarget instanceof LogicalComponent) {
            return "Logische Komponente";
        } else {
            // TODO: Exception handling!
            throw new RuntimeException("Unknown function contribution target.");
        }
    }

    private void exportFunctionContributionList() {
        currentSheet = workbook.createSheet("Funktionsbeitr�ge");
        final Row heading = currentSheet.createRow(0);
        heading.createCell(0).setCellValue("Fahrzeugfunktion");
        heading.createCell(1).setCellValue("Funktionsbeitrag");
        heading.createCell(2).setCellValue("Ziel");
        heading.createCell(3).setCellValue("identifiziert");
        heading.createCell(4).setCellValue("spezifiziert");

        for (final VehicleFunctionProgress vfp : getCockpitModel().getVehicleFunctionProgress()) {
            exportFunctionContributionList(vfp);
        }

        currentSheet.createFreezePane(0, 1);
        currentSheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 4));
        currentSheet.setColumnWidth(0, 256 * 10);
        currentSheet.setColumnWidth(1, 256 * 40);
        currentSheet.setColumnWidth(2, 256 * 20);
        currentSheet.setColumnWidth(3, 256 * 10);
        currentSheet.setColumnWidth(4, 256 * 10);
    }

    private void exportFunctionContributionList(final VehicleFunctionProgress vfp) {
        Row row = currentSheet.createRow(currentSheet.getLastRowNum() + 1);
        row.createCell(0).setCellValue(vfp.getVehicleFunction().getName());
        for (final FunctionContributionProgress fcp : vfp.getFunctionContributionProgress()) {
            row = currentSheet.createRow(currentSheet.getLastRowNum() + 1);
            row.createCell(1).setCellValue(fcp.getFunctionContribution().getText());
            if (fcp.isMapped()) {
                row.createCell(2).setCellValue(fcp.getFunctionContribution().getTarget().getName());
            }
            row.createCell(3).setCellValue(fcp.isMapped() ? "ja" : "nein");
            row.createCell(4).setCellValue(fcp.isSpecified() ? "ja" : "nein");
        }

        for (final VehicleFunctionProgress subFunctionProgress : vfp.getSubFunctionProgress()) {
            exportFunctionContributionList(subFunctionProgress);
        }
    }

}
