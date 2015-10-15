package de.jpwinkler.daf.csveditor;

import java.io.IOException;
import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.documenttagging.DocumentAccessor;
import de.jpwinkler.daf.documenttagging.DocumentTaggingAlgorithm;
import de.jpwinkler.daf.documenttagging.doors.maxent.DoorsMaxEntPredicateGenerator;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.GrowRateFunction;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.AbsoluteDiscountingSmoothing;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.AbstractSmoothingTechnique;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.AddKSmoothing;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.KatzBackoff;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.KneserNeySmoothing;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.NoSmoothing;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.SmoothingTechnique;
import de.jpwinkler.daf.documenttagging.hypermarkovchain.smoothing.VeryStupidBackoff;
import de.jpwinkler.daf.documenttagging.maxent.MaxEntRecursiveViterbiAlgorithm;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class RecursiveViterbiConfigurationController extends AbstractAlgorithmConfigurationController {

    @FXML
    private TextField iterationsTextField;

    @FXML
    private TextField cutoffTextField;

    @FXML
    private ChoiceBox<SmoothingTechnique> smoothingMethodChoiceBox;

    @FXML
    private TextField smoothingDTextField;

    @FXML
    private TextField smoothingKTextField;

    @FXML
    private ChoiceBox<GrowRateFunction> growRateChoiceBox;

    @Override
    public DocumentTaggingAlgorithm<DoorsTreeNode, String> getTrainedAlgorithm(final List<DocumentAccessor<DoorsTreeNode>> trainingDocuments) {
        try {
            AbstractSmoothingTechnique<String> smoothingTechnique;

            switch (smoothingMethodChoiceBox.getSelectionModel().getSelectedItem()) {
            case ABSOLUTE_DISCOUNTING:
                smoothingTechnique = new AbsoluteDiscountingSmoothing<String>(Double.parseDouble(smoothingDTextField.getText()));
                break;
            case ADD_K_SMOOTHING:
                smoothingTechnique = new AddKSmoothing<>(Double.parseDouble(smoothingKTextField.getText()));
                break;
            case KATZ_BACKOFF:
                smoothingTechnique = new KatzBackoff<>(Double.parseDouble(smoothingKTextField.getText()), Double.parseDouble(smoothingDTextField.getText()));
                break;
            case KNESER_NEY_SMOOTHING:
                smoothingTechnique = new KneserNeySmoothing<>(Double.parseDouble(smoothingDTextField.getText()));
                break;
            case NO_SMOOTHING:
                smoothingTechnique = new NoSmoothing<>();
                break;
            case VERY_STUPID_BACKOFF:
                smoothingTechnique = new VeryStupidBackoff<>(Double.parseDouble(smoothingKTextField.getText()), Double.parseDouble(smoothingDTextField.getText()));
                break;
            default:
                smoothingTechnique = new NoSmoothing<>();
                break;
            }

            return new MaxEntRecursiveViterbiAlgorithm<>(DoorsMaxEntPredicateGenerator.getDefaultGenerator("pod_tag"), trainingDocuments, smoothingTechnique, growRateChoiceBox.getSelectionModel().getSelectedItem(), Integer.parseInt(iterationsTextField.getText()), Integer.parseInt(cutoffTextField.getText()));
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void initialize() {

        smoothingMethodChoiceBox.getItems().addAll(SmoothingTechnique.values());
        growRateChoiceBox.getItems().addAll(GrowRateFunction.values());

        smoothingMethodChoiceBox.getSelectionModel().select(SmoothingTechnique.NO_SMOOTHING);
        growRateChoiceBox.getSelectionModel().select(GrowRateFunction.LINEAR);

    }

}
