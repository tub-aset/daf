package de.jpwinkler.daf.fap5gui.gui;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import de.jpwinkler.daf.doorsbridge.DoorsApplication;
import de.jpwinkler.daf.doorsbridge.DoorsApplicationFactory;
import de.jpwinkler.daf.doorsbridge.DoorsException;
import de.jpwinkler.daf.fap5.codebeamerrules.CodeBeamerConstants;
import de.jpwinkler.daf.fap5gui.model.AnalysisResults;
import de.jpwinkler.daf.fap5gui.model.DocumentSnapshot;
import de.jpwinkler.daf.fap5gui.model.Issue;
import de.jpwinkler.daf.fap5gui.model.Version;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private PieChart currentProgressChart;

    @FXML
    private StackedAreaChart<Date, Double> progressOverTimeChart;

    @FXML
    private LineChart<Date, Integer> openTasksOverTimeChart;

    @FXML
    private VBox progressContainer;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;

    @FXML
    private MenuItem runMenuItem;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private RadioButton allDocumentsRadioButton;

    @FXML
    private RadioButton singleDocumentRadioButton;

    @FXML
    private ChoiceBox<String> documentFilterChoiceBox;

    @FXML
    private TableView<Issue> issueTable;

    @FXML
    private LineChart<Date, Integer> requirementCountChart;

    @FXML
    private LineChart<Date, Double> remainingWorkChart;

    private Fap5GuiApplication mainApp;
    private Stage stage;

    private AnalysisResults analysisResults;
    private String currentFilter;

    private final DoorsApplication doorsApplication = DoorsApplicationFactory.getDoorsApplication();

    @FXML
    public void initialize() {
        documentFilterChoiceBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            updateFilter(newValue);
        });

        issueTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("objectId"));
        issueTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("objectNumber"));
        issueTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("issueMessage"));

    }

    @FXML
    public void runMenuItemClicked(final ActionEvent event) {
        mainApp.showAnalysisDialog();
    }

    @FXML
    public void closeMenuItemClicked(final ActionEvent event) {
        stage.close();
    }

    private void updateFilter(final String newValue) {
        if (currentFilter != newValue) {
            currentFilter = newValue;
            updateDataArea();
        }
    }

    public void setMainApp(final Fap5GuiApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void setStage(final Stage stage) {
        this.stage = stage;
    }

    public void startAnalysis() {
        progressBar.setProgress(0);
        progressLabel.setText("");
        runMenuItem.setDisable(true);
    }

    public void updateAnalysis(final double progress, final String progressText) {
        progressBar.setProgress(progress);
        progressLabel.setText(progressText);
    }

    public void stopAnalysis() {
        progressBar.setProgress(1);
        runMenuItem.setDisable(false);
    }

    public void setResults(final AnalysisResults results) {
        analysisResults = results;
        documentFilterChoiceBox.getItems().clear();
        documentFilterChoiceBox.getItems().addAll(results.getLatestVersion().getDocumentNames().stream().sorted().collect(Collectors.toList()));
        updateDataArea();
    }

    private void updateDataArea() {
        updateCurrentProgressChart();
        updateProgressOverTimeChart();
        updateOpenTasksOverTimeChart();
        updateRequirementCountChart();
        updateRemainingWorkChart();
        updateIssueTable();
    }

    private void updateRequirementCountChart() {
        final XYChart.Series<Date, Integer> requirementsSeries = new XYChart.Series<>("requirements", FXCollections.observableArrayList());
        for (final Version version : analysisResults.getVersions()) {
            final DocumentSnapshot results = currentFilter == null ? version.accumulateAllResults() : version.getDocumentSnapshots().get(currentFilter);
            if (results != null) {
                requirementsSeries.getData().add(new XYChart.Data<>(version.getDate(), results.getMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT)));
            }
        }
        requirementCountChart.getData().clear();
        requirementCountChart.getData().add(requirementsSeries);
    }

    private void updateRemainingWorkChart() {
        final XYChart.Series<Date, Double> remainingWorkSeries = new XYChart.Series<>("remaining work", FXCollections.observableArrayList());
        for (final Version version : analysisResults.getVersions()) {
            final DocumentSnapshot results = currentFilter == null ? version.accumulateAllResults() : version.getDocumentSnapshots().get(currentFilter);
            if (results != null) {
                final int reqCount = results.getMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT);
                final double yValue = (double) results.getMetric(CodeBeamerConstants.METRIC_ESTIMATED_REMAINING_WORK) / reqCount;
                remainingWorkSeries.getData().add(new XYChart.Data<>(version.getDate(), yValue));
            }
        }
        remainingWorkChart.getData().clear();
        remainingWorkChart.getData().add(remainingWorkSeries);
    }

    private void updateIssueTable() {

        if (currentFilter != null) {
            issueTable.setItems(FXCollections.observableArrayList(analysisResults.getLatestVersion().getDocumentSnapshots().get(currentFilter).getIssues()));
        } else {
            issueTable.setItems(FXCollections.observableArrayList(analysisResults.getLatestVersion().accumulateAllResults().getIssues()));
        }

    }

    private void updateOpenTasksOverTimeChart() {
        final XYChart.Series<Date, Integer> tasksSeries = new XYChart.Series<>("tasks", FXCollections.observableArrayList());
        for (final Version version : analysisResults.getVersions()) {
            final DocumentSnapshot results = currentFilter == null ? version.accumulateAllResults() : version.getDocumentSnapshots().get(currentFilter);
            if (results != null) {
                tasksSeries.getData().add(new XYChart.Data<>(version.getDate(), results.getMetric(CodeBeamerConstants.METRIC_OPEN_TODOS)));
            }
        }
        openTasksOverTimeChart.getData().clear();
        openTasksOverTimeChart.getData().add(tasksSeries);
    }

    private void updateProgressOverTimeChart() {
        final XYChart.Series<Date, Double> openSeries = new XYChart.Series<>("open", FXCollections.observableArrayList());
        final XYChart.Series<Date, Double> specifiedSeries = new XYChart.Series<>("specified", FXCollections.observableArrayList());
        final XYChart.Series<Date, Double> followUpSeries = new XYChart.Series<>("follow up", FXCollections.observableArrayList());
        final XYChart.Series<Date, Double> followUpHashtagsSeries = new XYChart.Series<>("follow up ###", FXCollections.observableArrayList());
        final XYChart.Series<Date, Double> agreedSeries = new XYChart.Series<>("agreed", FXCollections.observableArrayList());

        for (final Version version : analysisResults.getVersions()) {
            final DocumentSnapshot results = currentFilter == null ? version.accumulateAllResults() : version.getDocumentSnapshots().get(currentFilter);
            if (results != null) {
                final Integer reqCount = results.getMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT);
                if (reqCount > 0) {
                    final double maturityOpen = (double) results.getMetric(CodeBeamerConstants.METRIC_MATURITY_OPEN_COUNT) / reqCount;
                    final double maturitySpecified = (double) results.getMetric(CodeBeamerConstants.METRIC_MATURITY_SPECIFIED_COUNT) / reqCount;
                    final double maturityFollowUp = (double) results.getMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_COUNT) / reqCount;
                    final double maturityFollowUpHashtags = (double) results.getMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_HASHTAGS_COUNT) / reqCount;
                    final double maturityAgreed = (double) results.getMetric(CodeBeamerConstants.METRIC_MATURITY_AGREED_COUNT) / reqCount;

                    openSeries.getData().add(new XYChart.Data<>(version.getDate(), maturityOpen));
                    specifiedSeries.getData().add(new XYChart.Data<>(version.getDate(), maturitySpecified));
                    followUpSeries.getData().add(new XYChart.Data<>(version.getDate(), maturityFollowUp));
                    followUpHashtagsSeries.getData().add(new XYChart.Data<>(version.getDate(), maturityFollowUpHashtags));
                    agreedSeries.getData().add(new XYChart.Data<>(version.getDate(), maturityAgreed));
                }
            }
        }

        progressOverTimeChart.getData().clear();
        progressOverTimeChart.getData().add(openSeries);
        progressOverTimeChart.getData().add(specifiedSeries);
        progressOverTimeChart.getData().add(followUpSeries);
        progressOverTimeChart.getData().add(followUpHashtagsSeries);
        progressOverTimeChart.getData().add(agreedSeries);
    }

    private void updateCurrentProgressChart() {
        final DocumentSnapshot allResults = (currentFilter == null) ? analysisResults.getLatestVersion().accumulateAllResults() : analysisResults.getLatestVersion().getDocumentSnapshots().get(currentFilter);
        final ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        if (allResults != null) {
            data.add(new PieChart.Data("open", allResults.getMetric(CodeBeamerConstants.METRIC_MATURITY_OPEN_COUNT)));
            data.add(new PieChart.Data("specified", allResults.getMetric(CodeBeamerConstants.METRIC_MATURITY_SPECIFIED_COUNT)));
            data.add(new PieChart.Data("follow up", allResults.getMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_COUNT)));
            data.add(new PieChart.Data("follow up ###", allResults.getMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_HASHTAGS_COUNT)));
            data.add(new PieChart.Data("agreed", allResults.getMetric(CodeBeamerConstants.METRIC_MATURITY_AGREED_COUNT)));
        }
        currentProgressChart.setData(data);
    }

    @FXML
    public void allDocumentsRadioButtonSelected(final ActionEvent event) {
        updateFilter(null);
        documentFilterChoiceBox.setDisable(true);
    }

    @FXML
    public void singleDocumentRadioButtonSelected(final ActionEvent event) {
        updateFilter(documentFilterChoiceBox.getSelectionModel().getSelectedItem());
        documentFilterChoiceBox.setDisable(false);
    }

    @FXML
    public void issueTableMousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 2) {
            final Issue selectedItem = issueTable.getSelectionModel().getSelectedItem();
            try {
                doorsApplication.gotoObject(selectedItem.getAbsoluteModuleName(), selectedItem.getObjectAbsoluteNumber());
            } catch (DoorsException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
