package de.jpwinkler.daf.csveditor;

import java.io.IOException;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.documenttagging.DocumentAccessor;
import de.jpwinkler.daf.documenttagging.DocumentTaggingAlgorithm;
import de.jpwinkler.daf.documenttagging.doors.maxent.DoorsMaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.maxent.SimpleMaxEntAlgorithm;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MaxEntConfigurationController extends AbstractAlgorithmConfigurationController {

    @FXML
    TextField iterationsTextField;
    @FXML
    TextField cutoffTextField;

    @Override
    public DocumentTaggingAlgorithm<DoorsTreeNode, String> getTrainedAlgorithm(final List<DocumentAccessor<DoorsTreeNode>> trainingDocuments) {
        try {
            return new SimpleMaxEntAlgorithm<>(DoorsMaxEntPredicateGenerator.getDefaultGenerator("pod_tag"), trainingDocuments, Integer.parseInt(iterationsTextField.getText()), Integer.parseInt(cutoffTextField.getText()));
        } catch (NumberFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}
