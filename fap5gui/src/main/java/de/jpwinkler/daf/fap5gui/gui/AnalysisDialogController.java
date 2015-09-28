package de.jpwinkler.daf.fap5gui.gui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import de.jpwinkler.daf.fap5gui.model.AnalysisSettings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AnalysisDialogController {

    private static final Logger LOGGER = Logger.getLogger(AnalysisDialogController.class.getName());

    private static final File SETTINGS_FILE = new File("temp/settings.json");

    @FXML
    private TextField csvDirectoryText;
    @FXML
    private TextField moduleListText;
    @FXML
    private CheckBox reuseCachedResultsCheckBox;
    @FXML
    private CheckBox runNewDoorsAnalysisCheckBox;
    @FXML
    private CheckBox updateProgressReportCheckBox;
    @FXML
    private TextField progressReportSourceText;
    @FXML
    private TextField progressReportTargetText;
    @FXML
    private CheckBox generateIssueListsCheckBox;
    @FXML
    private TextField issueListsDirectoryText;
    @FXML
    private Button moduleListSelectButton;
    @FXML
    private Button progressReportSourceSelectButton;
    @FXML
    private Button progressReportTargetSelectButton;
    @FXML
    private Button issueListsDirectorySelectButton;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField usernameText;

    @FXML
    private TextField databaseText;

    private AnalysisSettings settings;
    private Stage dialogStage;
    private Fap5GuiApplication mainApp;

    @FXML
    public void runNewDoorsAnalysisCheckBoxClicked() {
        updateUI();
    }

    public void updateUI() {
        moduleListText.setDisable(!runNewDoorsAnalysisCheckBox.isSelected());
        moduleListSelectButton.setDisable(!runNewDoorsAnalysisCheckBox.isSelected());
        databaseText.setDisable(!runNewDoorsAnalysisCheckBox.isSelected());
        usernameText.setDisable(!runNewDoorsAnalysisCheckBox.isSelected());
        passwordText.setDisable(!runNewDoorsAnalysisCheckBox.isSelected());
        progressReportSourceText.setDisable(!updateProgressReportCheckBox.isSelected());
        progressReportSourceSelectButton.setDisable(!updateProgressReportCheckBox.isSelected());
        progressReportTargetText.setDisable(!updateProgressReportCheckBox.isSelected());
        progressReportTargetSelectButton.setDisable(!updateProgressReportCheckBox.isSelected());
        issueListsDirectoryText.setDisable(!generateIssueListsCheckBox.isSelected());
        issueListsDirectorySelectButton.setDisable(!generateIssueListsCheckBox.isSelected());
    }

    // Event Listener on CheckBox[#updateProgressReportCheckBox].onAction
    @FXML
    public void updateProgressReportCheckBoxClicked() {
        updateUI();
    }

    // Event Listener on CheckBox[#generateIssueListsCheckBox].onAction
    @FXML
    public void generateIssueListsCheckBoxClicked() {
        updateUI();
    }

    // Event Listener on Button[#csvDirectorySelectButton].onAction
    @FXML
    public void csvDirectorySelectButtonClicked() {
        final DirectoryChooser chooser = new DirectoryChooser();
        if (csvDirectoryText.getText() != null && new File(csvDirectoryText.getText()).exists()) {
            chooser.setInitialDirectory(new File(csvDirectoryText.getText()));
        }
        final File file = chooser.showDialog(dialogStage);
        if (file != null) {
            csvDirectoryText.setText(file.toString());
        }
    }

    // Event Listener on Button[#moduleListSelectButton].onAction
    @FXML
    public void moduleListSelectButtonClicked() {
        final FileChooser chooser = new FileChooser();
        if (moduleListText.getText() != null && new File(moduleListText.getText()).exists()) {
            chooser.setInitialDirectory(new File(moduleListText.getText()).getParentFile());
        }
        final File file = chooser.showOpenDialog(dialogStage);
        if (file != null) {
            moduleListText.setText(file.toString());
        }
    }

    // Event Listener on Button[#progressReportSourceSelectButton].onAction
    @FXML
    public void progressReportSourceSelectButtonClicked() {
        final FileChooser chooser = new FileChooser();
        if (progressReportSourceText.getText() != null && new File(progressReportSourceText.getText()).exists()) {
            chooser.setInitialDirectory(new File(progressReportSourceText.getText()).getParentFile());
        }
        final File file = chooser.showOpenDialog(dialogStage);
        if (file != null) {
            progressReportSourceText.setText(file.toString());
        }
    }

    // Event Listener on Button[#progressReportTargetSelectButton].onAction
    @FXML
    public void progressReportTargetSelectButtonClicked() {
        final FileChooser chooser = new FileChooser();
        if (progressReportTargetText.getText() != null && new File(progressReportTargetText.getText()).exists()) {
            chooser.setInitialDirectory(new File(progressReportTargetText.getText()).getParentFile());
        }
        final File file = chooser.showOpenDialog(dialogStage);
        if (file != null) {
            progressReportTargetText.setText(file.toString());
        }
    }

    // Event Listener on Button[#issueListsDirectorySelectButton].onAction
    @FXML
    public void issueListsDirectorySelectButtonClicked() {
        final DirectoryChooser chooser = new DirectoryChooser();
        if (issueListsDirectoryText.getText() != null && new File(issueListsDirectoryText.getText()).exists()) {
            chooser.setInitialDirectory(new File(issueListsDirectoryText.getText()));
        }
        final File file = chooser.showDialog(dialogStage);
        if (file != null) {
            issueListsDirectoryText.setText(file.toString());
        }
    }

    // Event Listener on Button[#runButton].onAction
    @FXML
    public void runButtonClicked() {
        saveSettings();

        mainApp.runAnalysis(settings);
        dialogStage.close();
    }

    private void saveSettings() {
        settings = new AnalysisSettings();

        settings.setCsvDirectory(csvDirectoryText.getText());
        settings.setModuleListFile(moduleListText.getText());
        settings.setReuseCache(reuseCachedResultsCheckBox.isSelected());
        settings.setRunNewDoorsAnalysis(runNewDoorsAnalysisCheckBox.isSelected());
        settings.setUpdateProgressReportExcelSheet(updateProgressReportCheckBox.isSelected());
        settings.setProgressReportSourceFile(progressReportSourceText.getText());
        settings.setProgressReportTargetFile(progressReportTargetText.getText());
        settings.setGenerateIssueLists(generateIssueListsCheckBox.isSelected());
        settings.setIssueListsDirectory(issueListsDirectoryText.getText());
        settings.setDoorsDatabase(databaseText.getText());
        settings.setDoorsUsername(usernameText.getText());
        settings.setDoorsPassword(passwordText.getText());

        final String settingsJson = new GsonBuilder().setPrettyPrinting().create().toJson(settings);
        try {
            FileUtils.write(SETTINGS_FILE, settingsJson);
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    // Event Listener on Button[#cancelButton].onAction
    @FXML
    public void cancelButtonClicked() {
        saveSettings();
        dialogStage.close();
    }

    public void setDialogStage(final Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(final Fap5GuiApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void loadSettings() {
        if (SETTINGS_FILE.exists()) {
            try {
                settings = new Gson().fromJson(FileUtils.readFileToString(SETTINGS_FILE), AnalysisSettings.class);
                csvDirectoryText.setText(settings.getCsvDirectory());
                moduleListText.setText(settings.getModuleListFile());
                reuseCachedResultsCheckBox.setSelected(settings.isReuseCache());
                runNewDoorsAnalysisCheckBox.setSelected(settings.isRunNewDoorsAnalysis());
                updateProgressReportCheckBox.setSelected(settings.isUpdateProgressReportExcelSheet());
                progressReportSourceText.setText(settings.getProgressReportSourceFile());
                progressReportTargetText.setText(settings.getProgressReportTargetFile());
                generateIssueListsCheckBox.setSelected(settings.isGenerateIssueLists());
                issueListsDirectoryText.setText(settings.getIssueListsDirectory());
                databaseText.setText(settings.getDoorsDatabase());
                usernameText.setText(settings.getDoorsUsername());
            } catch (JsonSyntaxException | IOException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                settings = new AnalysisSettings();
            }
        }
    }
}
