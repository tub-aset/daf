package de.jpwinkler.daf.workflowdsl.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.jpwinkler.daf.workflowdsl.services.WorkflowDslGrammarAccess;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ArrayVariable;
import de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ImplementationFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModelConstructorStep;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModelOperationStep;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry;
import de.jpwinkler.daf.workflowdsl.workflowDsl.SimpleVariable;
import de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Target;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowModel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class WorkflowDslSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private WorkflowDslGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == WorkflowDslPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case WorkflowDslPackage.ARRAY_VARIABLE:
				if(context == grammarAccess.getArrayVariableRule() ||
				   context == grammarAccess.getVariableRule() ||
				   context == grammarAccess.getWorkflowElementRule()) {
					sequence_ArrayVariable(context, (ArrayVariable) semanticObject); 
					return; 
				}
				else break;
			case WorkflowDslPackage.DEPENDENCY_FEATURE:
				if(context == grammarAccess.getDependencyFeatureRule() ||
				   context == grammarAccess.getOperationFeatureRule()) {
					sequence_DependencyFeature(context, (DependencyFeature) semanticObject); 
					return; 
				}
				else break;
			case WorkflowDslPackage.FOR_FEATURE:
				if(context == grammarAccess.getForFeatureRule() ||
				   context == grammarAccess.getOperationFeatureRule()) {
					sequence_ForFeature(context, (ForFeature) semanticObject); 
					return; 
				}
				else break;
			case WorkflowDslPackage.IMPLEMENTATION_FEATURE:
				if(context == grammarAccess.getImplementationFeatureRule() ||
				   context == grammarAccess.getOperationFeatureRule()) {
					sequence_ImplementationFeature(context, (ImplementationFeature) semanticObject); 
					return; 
				}
				else break;
			case WorkflowDslPackage.MODEL_CONSTRUCTOR_STEP:
				if(context == grammarAccess.getModelConstructorStepRule() ||
				   context == grammarAccess.getStepRule() ||
				   context == grammarAccess.getWorkflowElementRule()) {
					sequence_ModelConstructorStep(context, (ModelConstructorStep) semanticObject); 
					return; 
				}
				else break;
			case WorkflowDslPackage.MODEL_OPERATION_STEP:
				if(context == grammarAccess.getModelOperationStepRule() ||
				   context == grammarAccess.getStepRule() ||
				   context == grammarAccess.getWorkflowElementRule()) {
					sequence_ModelOperationStep(context, (ModelOperationStep) semanticObject); 
					return; 
				}
				else break;
			case WorkflowDslPackage.MODULE_SET:
				if(context == grammarAccess.getModuleSetRule() ||
				   context == grammarAccess.getWorkflowElementRule()) {
					sequence_ModuleSet(context, (ModuleSet) semanticObject); 
					return; 
				}
				else break;
			case WorkflowDslPackage.MODULE_SET_ENTRY:
				if(context == grammarAccess.getModuleSetEntryRule()) {
					sequence_ModuleSetEntry(context, (ModuleSetEntry) semanticObject); 
					return; 
				}
				else break;
			case WorkflowDslPackage.SIMPLE_VARIABLE:
				if(context == grammarAccess.getSimpleVariableRule() ||
				   context == grammarAccess.getVariableRule() ||
				   context == grammarAccess.getWorkflowElementRule()) {
					sequence_SimpleVariable(context, (SimpleVariable) semanticObject); 
					return; 
				}
				else break;
			case WorkflowDslPackage.SOURCE_FEATURE:
				if(context == grammarAccess.getOperationFeatureRule() ||
				   context == grammarAccess.getSourceFeatureRule()) {
					sequence_SourceFeature(context, (SourceFeature) semanticObject); 
					return; 
				}
				else break;
			case WorkflowDslPackage.TARGET:
				if(context == grammarAccess.getTargetRule() ||
				   context == grammarAccess.getWorkflowElementRule()) {
					sequence_Target(context, (Target) semanticObject); 
					return; 
				}
				else break;
			case WorkflowDslPackage.WORKFLOW_MODEL:
				if(context == grammarAccess.getWorkflowModelRule()) {
					sequence_WorkflowModel(context, (WorkflowModel) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (name=ID (items+=STRING items+=STRING*)?)
	 */
	protected void sequence_ArrayVariable(EObject context, ArrayVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (step=[Step|ID] variables+=Variable* name=ID?)
	 */
	protected void sequence_DependencyFeature(EObject context, DependencyFeature semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (loopVar=ID arrayVar=ID features+=OperationFeature*)
	 */
	protected void sequence_ForFeature(EObject context, ForFeature semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     implementation=STRING
	 */
	protected void sequence_ImplementationFeature(EObject context, ImplementationFeature semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, WorkflowDslPackage.Literals.IMPLEMENTATION_FEATURE__IMPLEMENTATION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WorkflowDslPackage.Literals.IMPLEMENTATION_FEATURE__IMPLEMENTATION));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getImplementationFeatureAccess().getImplementationSTRINGTerminalRuleCall_1_0(), semanticObject.getImplementation());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID features+=OperationFeature*)
	 */
	protected void sequence_ModelConstructorStep(EObject context, ModelConstructorStep semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID features+=OperationFeature*)
	 */
	protected void sequence_ModelOperationStep(EObject context, ModelOperationStep semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (type=MODULE_SET_ENTRY_TYPE reference=STRING)
	 */
	protected void sequence_ModuleSetEntry(EObject context, ModuleSetEntry semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, WorkflowDslPackage.Literals.MODULE_SET_ENTRY__TYPE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WorkflowDslPackage.Literals.MODULE_SET_ENTRY__TYPE));
			if(transientValues.isValueTransient(semanticObject, WorkflowDslPackage.Literals.MODULE_SET_ENTRY__REFERENCE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WorkflowDslPackage.Literals.MODULE_SET_ENTRY__REFERENCE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getModuleSetEntryAccess().getTypeMODULE_SET_ENTRY_TYPEParserRuleCall_0_0(), semanticObject.getType());
		feeder.accept(grammarAccess.getModuleSetEntryAccess().getReferenceSTRINGTerminalRuleCall_1_0(), semanticObject.getReference());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID moduleSetEntries+=ModuleSetEntry*)
	 */
	protected void sequence_ModuleSet(EObject context, ModuleSet semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID value=STRING)
	 */
	protected void sequence_SimpleVariable(EObject context, SimpleVariable semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, WorkflowDslPackage.Literals.VARIABLE__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WorkflowDslPackage.Literals.VARIABLE__NAME));
			if(transientValues.isValueTransient(semanticObject, WorkflowDslPackage.Literals.SIMPLE_VARIABLE__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WorkflowDslPackage.Literals.SIMPLE_VARIABLE__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getSimpleVariableAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getSimpleVariableAccess().getValueSTRINGTerminalRuleCall_3_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     moduleSet=[ModuleSet|ID]
	 */
	protected void sequence_SourceFeature(EObject context, SourceFeature semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, WorkflowDslPackage.Literals.SOURCE_FEATURE__MODULE_SET) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WorkflowDslPackage.Literals.SOURCE_FEATURE__MODULE_SET));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getSourceFeatureAccess().getModuleSetModuleSetIDTerminalRuleCall_1_0_1(), semanticObject.getModuleSet());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     step=[Step|ID]
	 */
	protected void sequence_Target(EObject context, Target semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, WorkflowDslPackage.Literals.TARGET__STEP) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, WorkflowDslPackage.Literals.TARGET__STEP));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getTargetAccess().getStepStepIDTerminalRuleCall_1_0_1(), semanticObject.getStep());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     elements+=WorkflowElement*
	 */
	protected void sequence_WorkflowModel(EObject context, WorkflowModel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
