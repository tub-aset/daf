package de.jpwinkler.daf.reqinfclassifier.headingclassifier;

import de.jpwinkler.daf.reqinfclassifier.ClassificationReliability;
import de.jpwinkler.daf.reqinfclassifier.ClassificationResult;
import de.jpwinkler.daf.reqinfclassifier.ClassifiedBy;
import de.jpwinkler.daf.reqinfclassifier.Classifier;
import de.jpwinkler.daf.reqinfclassifier.ClassifierContext;
import de.jpwinkler.daf.reqinfclassifier.ExampleContext;

public class HeadingClassifier extends Classifier<ClassificationResult> {

	public HeadingClassifier(ClassifierContext context) {
		super(context);
	}

	@Override
	protected ClassificationResult run(ExampleContext exampleContext) {
		
		if (exampleContext.getExample().isHeading()) {
			return new ClassificationResult("heading", ClassifiedBy.HEADING_CLASSIFIER, ClassificationReliability.DEFINITELY_CORRECT);
		} else {
			return null;
		}
		
	}

}
