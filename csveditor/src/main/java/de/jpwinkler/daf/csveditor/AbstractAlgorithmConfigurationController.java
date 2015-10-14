package de.jpwinkler.daf.csveditor;

import java.util.List;

import de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode;
import de.jpwinkler.daf.documenttagging.DocumentAccessor;
import de.jpwinkler.daf.documenttagging.DocumentTaggingAlgorithm;

public abstract class AbstractAlgorithmConfigurationController {

    public abstract DocumentTaggingAlgorithm<DoorsTreeNode, String> getTrainedAlgorithm(List<DocumentAccessor<DoorsTreeNode>> trainingDocuments);

}
