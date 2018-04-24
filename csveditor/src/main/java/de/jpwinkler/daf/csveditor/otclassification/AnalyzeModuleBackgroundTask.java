package de.jpwinkler.daf.csveditor.otclassification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.jpwinkler.daf.csveditor.CSVEditorTabController;
import de.jpwinkler.daf.csveditor.background.BackgroundTask;
import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.reqinfclassifier.ClassificationResult;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ReqInfClassifier;
import javafx.application.Platform;

public class AnalyzeModuleBackgroundTask extends BackgroundTask {

    private final Map<DoorsObject, ClassificationResult> results;
    private final List<DoorsObject> objectsToAnalyze = new ArrayList<>();

    private final ReqInfClassifier classifier;
    private final CSVEditorTabController tabController;

    public AnalyzeModuleBackgroundTask(final CSVEditorTabController tabController, final DoorsModule module, final Map<DoorsObject, ClassificationResult> results) {
        super("Analyzing module...");
        classifier = new ReqInfClassifier(ClassifierContext.getInstance(), "slh-6.1");
        this.tabController = tabController;
        this.results = results;
        module.accept(new DoorsTreeNodeVisitor() {
            @Override
            public boolean visitPreTraverse(final DoorsObject object) {
                objectsToAnalyze.add(object);
                return true;
            }
        });
    }

    public AnalyzeModuleBackgroundTask(final CSVEditorTabController tabController, final DoorsObject object, final Map<DoorsObject, ClassificationResult> results) {
        super("Analyzing object...");
        classifier = new ReqInfClassifier(ClassifierContext.getInstance(), "slh-6.1");
        this.tabController = tabController;
        this.results = results;
        objectsToAnalyze.add(object);
    }

    @Override
    public void run() {
        startProgress();
        int i = 0;
        for (final DoorsObject o : objectsToAnalyze) {
            final ClassificationResult classificationResult = classifier.classify(new DoorsObjectExample(o));
            if (classificationResult != null) {
                results.put(o, classificationResult);
                Platform.runLater(() -> {
                    tabController.refreshTable();
                });
            }
            i++;
            updateProgress(i, objectsToAnalyze.size());
        }
        finishProgress();
    }

}
