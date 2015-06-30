package de.jpwinkler.daf.fap5gui;

import de.jpwinkler.daf.fap5gui.model.AnalysisResults;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private PieChart currentProgressChart;

    @FXML
    private StackedAreaChart progressOverTimeChart;

    @FXML
    private LineChart openTasksOverTimeChart;

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

    private Fap5GuiApplication mainApp;

    private Stage stage;

    @FXML
    public void runMenuItemClicked(final ActionEvent event) {
        mainApp.showAnalysisDialog();
    }

    @FXML
    public void closeMenuItemClicked(final ActionEvent event) {
        stage.close();
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
    }

    public void updateAnalysis(final double progress, final String progressText) {
        progressBar.setProgress(progress);
        progressLabel.setText(progressText);
    }

    public void stopAnalysis() {
        progressBar.setProgress(1);
        progressLabel.setText("done.");
    }

    public void setResults(final AnalysisResults results) {

    }
}
