/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslFactory
 * @model kind="package"
 * @generated
 */
public interface WorkflowDslPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "workflowDsl";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.jpwinkler.de/daf/workflowdsl/WorkflowDsl";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "workflowDsl";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  WorkflowDslPackage eINSTANCE = de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl.init();

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowModelImpl <em>Workflow Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowModelImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getWorkflowModel()
   * @generated
   */
  int WORKFLOW_MODEL = 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WORKFLOW_MODEL__ELEMENTS = 0;

  /**
   * The number of structural features of the '<em>Workflow Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WORKFLOW_MODEL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowElementImpl <em>Workflow Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowElementImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getWorkflowElement()
   * @generated
   */
  int WORKFLOW_ELEMENT = 1;

  /**
   * The number of structural features of the '<em>Workflow Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WORKFLOW_ELEMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.TargetImpl <em>Target</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.TargetImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getTarget()
   * @generated
   */
  int TARGET = 2;

  /**
   * The feature id for the '<em><b>Step</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TARGET__STEP = WORKFLOW_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Target</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TARGET_FEATURE_COUNT = WORKFLOW_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModuleSetImpl <em>Module Set</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModuleSetImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getModuleSet()
   * @generated
   */
  int MODULE_SET = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE_SET__NAME = WORKFLOW_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Module Set Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE_SET__MODULE_SET_ENTRIES = WORKFLOW_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Module Set</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE_SET_FEATURE_COUNT = WORKFLOW_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModuleSetEntryImpl <em>Module Set Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModuleSetEntryImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getModuleSetEntry()
   * @generated
   */
  int MODULE_SET_ENTRY = 4;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE_SET_ENTRY__TYPE = 0;

  /**
   * The feature id for the '<em><b>Reference</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE_SET_ENTRY__REFERENCE = 1;

  /**
   * The number of structural features of the '<em>Module Set Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODULE_SET_ENTRY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.VariableImpl <em>Variable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.VariableImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getVariable()
   * @generated
   */
  int VARIABLE = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE__NAME = WORKFLOW_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Variable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_FEATURE_COUNT = WORKFLOW_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.SimpleVariableImpl <em>Simple Variable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.SimpleVariableImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getSimpleVariable()
   * @generated
   */
  int SIMPLE_VARIABLE = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_VARIABLE__NAME = VARIABLE__NAME;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_VARIABLE__VALUE = VARIABLE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Simple Variable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_VARIABLE_FEATURE_COUNT = VARIABLE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ArrayVariableImpl <em>Array Variable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ArrayVariableImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getArrayVariable()
   * @generated
   */
  int ARRAY_VARIABLE = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_VARIABLE__NAME = VARIABLE__NAME;

  /**
   * The feature id for the '<em><b>Items</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_VARIABLE__ITEMS = VARIABLE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Array Variable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_VARIABLE_FEATURE_COUNT = VARIABLE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.StepImpl <em>Step</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.StepImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getStep()
   * @generated
   */
  int STEP = 8;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STEP__NAME = WORKFLOW_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STEP__FEATURES = WORKFLOW_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Step</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STEP_FEATURE_COUNT = WORKFLOW_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModelConstructorStepImpl <em>Model Constructor Step</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModelConstructorStepImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getModelConstructorStep()
   * @generated
   */
  int MODEL_CONSTRUCTOR_STEP = 9;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_CONSTRUCTOR_STEP__NAME = STEP__NAME;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_CONSTRUCTOR_STEP__FEATURES = STEP__FEATURES;

  /**
   * The number of structural features of the '<em>Model Constructor Step</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_CONSTRUCTOR_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModelOperationStepImpl <em>Model Operation Step</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModelOperationStepImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getModelOperationStep()
   * @generated
   */
  int MODEL_OPERATION_STEP = 10;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OPERATION_STEP__NAME = STEP__NAME;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OPERATION_STEP__FEATURES = STEP__FEATURES;

  /**
   * The number of structural features of the '<em>Model Operation Step</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OPERATION_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.OperationFeatureImpl <em>Operation Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.OperationFeatureImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getOperationFeature()
   * @generated
   */
  int OPERATION_FEATURE = 11;

  /**
   * The number of structural features of the '<em>Operation Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_FEATURE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.SourceFeatureImpl <em>Source Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.SourceFeatureImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getSourceFeature()
   * @generated
   */
  int SOURCE_FEATURE = 12;

  /**
   * The feature id for the '<em><b>Module Set</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SOURCE_FEATURE__MODULE_SET = OPERATION_FEATURE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Source Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SOURCE_FEATURE_FEATURE_COUNT = OPERATION_FEATURE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ForFeatureImpl <em>For Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ForFeatureImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getForFeature()
   * @generated
   */
  int FOR_FEATURE = 13;

  /**
   * The feature id for the '<em><b>Loop Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_FEATURE__LOOP_VAR = OPERATION_FEATURE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Array Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_FEATURE__ARRAY_VAR = OPERATION_FEATURE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_FEATURE__FEATURES = OPERATION_FEATURE_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>For Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FOR_FEATURE_FEATURE_COUNT = OPERATION_FEATURE_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.DependencyFeatureImpl <em>Dependency Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.DependencyFeatureImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getDependencyFeature()
   * @generated
   */
  int DEPENDENCY_FEATURE = 14;

  /**
   * The feature id for the '<em><b>Step</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEPENDENCY_FEATURE__STEP = OPERATION_FEATURE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEPENDENCY_FEATURE__VARIABLES = OPERATION_FEATURE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEPENDENCY_FEATURE__NAME = OPERATION_FEATURE_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Dependency Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEPENDENCY_FEATURE_FEATURE_COUNT = OPERATION_FEATURE_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ImplementationFeatureImpl <em>Implementation Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ImplementationFeatureImpl
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getImplementationFeature()
   * @generated
   */
  int IMPLEMENTATION_FEATURE = 15;

  /**
   * The feature id for the '<em><b>Implementation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTATION_FEATURE__IMPLEMENTATION = OPERATION_FEATURE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Implementation Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTATION_FEATURE_FEATURE_COUNT = OPERATION_FEATURE_FEATURE_COUNT + 1;


  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowModel <em>Workflow Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Workflow Model</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowModel
   * @generated
   */
  EClass getWorkflowModel();

  /**
   * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowModel#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowModel#getElements()
   * @see #getWorkflowModel()
   * @generated
   */
  EReference getWorkflowModel_Elements();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowElement <em>Workflow Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Workflow Element</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowElement
   * @generated
   */
  EClass getWorkflowElement();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Target <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Target</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.Target
   * @generated
   */
  EClass getTarget();

  /**
   * Returns the meta object for the reference '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Target#getStep <em>Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Step</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.Target#getStep()
   * @see #getTarget()
   * @generated
   */
  EReference getTarget_Step();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet <em>Module Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Module Set</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet
   * @generated
   */
  EClass getModuleSet();

  /**
   * Returns the meta object for the attribute '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet#getName()
   * @see #getModuleSet()
   * @generated
   */
  EAttribute getModuleSet_Name();

  /**
   * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet#getModuleSetEntries <em>Module Set Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Module Set Entries</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet#getModuleSetEntries()
   * @see #getModuleSet()
   * @generated
   */
  EReference getModuleSet_ModuleSetEntries();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry <em>Module Set Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Module Set Entry</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry
   * @generated
   */
  EClass getModuleSetEntry();

  /**
   * Returns the meta object for the attribute '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry#getType()
   * @see #getModuleSetEntry()
   * @generated
   */
  EAttribute getModuleSetEntry_Type();

  /**
   * Returns the meta object for the attribute '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry#getReference <em>Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Reference</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry#getReference()
   * @see #getModuleSetEntry()
   * @generated
   */
  EAttribute getModuleSetEntry_Reference();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Variable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variable</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.Variable
   * @generated
   */
  EClass getVariable();

  /**
   * Returns the meta object for the attribute '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Variable#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.Variable#getName()
   * @see #getVariable()
   * @generated
   */
  EAttribute getVariable_Name();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.SimpleVariable <em>Simple Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Simple Variable</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.SimpleVariable
   * @generated
   */
  EClass getSimpleVariable();

  /**
   * Returns the meta object for the attribute '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.SimpleVariable#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.SimpleVariable#getValue()
   * @see #getSimpleVariable()
   * @generated
   */
  EAttribute getSimpleVariable_Value();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ArrayVariable <em>Array Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Array Variable</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ArrayVariable
   * @generated
   */
  EClass getArrayVariable();

  /**
   * Returns the meta object for the attribute list '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ArrayVariable#getItems <em>Items</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Items</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ArrayVariable#getItems()
   * @see #getArrayVariable()
   * @generated
   */
  EAttribute getArrayVariable_Items();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Step <em>Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Step</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.Step
   * @generated
   */
  EClass getStep();

  /**
   * Returns the meta object for the attribute '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Step#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.Step#getName()
   * @see #getStep()
   * @generated
   */
  EAttribute getStep_Name();

  /**
   * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Step#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.Step#getFeatures()
   * @see #getStep()
   * @generated
   */
  EReference getStep_Features();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModelConstructorStep <em>Model Constructor Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model Constructor Step</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ModelConstructorStep
   * @generated
   */
  EClass getModelConstructorStep();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModelOperationStep <em>Model Operation Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model Operation Step</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ModelOperationStep
   * @generated
   */
  EClass getModelOperationStep();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.OperationFeature <em>Operation Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Feature</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.OperationFeature
   * @generated
   */
  EClass getOperationFeature();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature <em>Source Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Source Feature</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature
   * @generated
   */
  EClass getSourceFeature();

  /**
   * Returns the meta object for the reference '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature#getModuleSet <em>Module Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Module Set</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature#getModuleSet()
   * @see #getSourceFeature()
   * @generated
   */
  EReference getSourceFeature_ModuleSet();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature <em>For Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>For Feature</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature
   * @generated
   */
  EClass getForFeature();

  /**
   * Returns the meta object for the attribute '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature#getLoopVar <em>Loop Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Loop Var</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature#getLoopVar()
   * @see #getForFeature()
   * @generated
   */
  EAttribute getForFeature_LoopVar();

  /**
   * Returns the meta object for the attribute '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature#getArrayVar <em>Array Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Array Var</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature#getArrayVar()
   * @see #getForFeature()
   * @generated
   */
  EAttribute getForFeature_ArrayVar();

  /**
   * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature#getFeatures()
   * @see #getForFeature()
   * @generated
   */
  EReference getForFeature_Features();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature <em>Dependency Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Dependency Feature</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature
   * @generated
   */
  EClass getDependencyFeature();

  /**
   * Returns the meta object for the reference '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature#getStep <em>Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Step</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature#getStep()
   * @see #getDependencyFeature()
   * @generated
   */
  EReference getDependencyFeature_Step();

  /**
   * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature#getVariables()
   * @see #getDependencyFeature()
   * @generated
   */
  EReference getDependencyFeature_Variables();

  /**
   * Returns the meta object for the attribute '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature#getName()
   * @see #getDependencyFeature()
   * @generated
   */
  EAttribute getDependencyFeature_Name();

  /**
   * Returns the meta object for class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ImplementationFeature <em>Implementation Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Implementation Feature</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ImplementationFeature
   * @generated
   */
  EClass getImplementationFeature();

  /**
   * Returns the meta object for the attribute '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ImplementationFeature#getImplementation <em>Implementation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Implementation</em>'.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ImplementationFeature#getImplementation()
   * @see #getImplementationFeature()
   * @generated
   */
  EAttribute getImplementationFeature_Implementation();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  WorkflowDslFactory getWorkflowDslFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowModelImpl <em>Workflow Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowModelImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getWorkflowModel()
     * @generated
     */
    EClass WORKFLOW_MODEL = eINSTANCE.getWorkflowModel();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WORKFLOW_MODEL__ELEMENTS = eINSTANCE.getWorkflowModel_Elements();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowElementImpl <em>Workflow Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowElementImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getWorkflowElement()
     * @generated
     */
    EClass WORKFLOW_ELEMENT = eINSTANCE.getWorkflowElement();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.TargetImpl <em>Target</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.TargetImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getTarget()
     * @generated
     */
    EClass TARGET = eINSTANCE.getTarget();

    /**
     * The meta object literal for the '<em><b>Step</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TARGET__STEP = eINSTANCE.getTarget_Step();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModuleSetImpl <em>Module Set</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModuleSetImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getModuleSet()
     * @generated
     */
    EClass MODULE_SET = eINSTANCE.getModuleSet();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MODULE_SET__NAME = eINSTANCE.getModuleSet_Name();

    /**
     * The meta object literal for the '<em><b>Module Set Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODULE_SET__MODULE_SET_ENTRIES = eINSTANCE.getModuleSet_ModuleSetEntries();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModuleSetEntryImpl <em>Module Set Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModuleSetEntryImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getModuleSetEntry()
     * @generated
     */
    EClass MODULE_SET_ENTRY = eINSTANCE.getModuleSetEntry();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MODULE_SET_ENTRY__TYPE = eINSTANCE.getModuleSetEntry_Type();

    /**
     * The meta object literal for the '<em><b>Reference</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MODULE_SET_ENTRY__REFERENCE = eINSTANCE.getModuleSetEntry_Reference();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.VariableImpl <em>Variable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.VariableImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getVariable()
     * @generated
     */
    EClass VARIABLE = eINSTANCE.getVariable();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VARIABLE__NAME = eINSTANCE.getVariable_Name();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.SimpleVariableImpl <em>Simple Variable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.SimpleVariableImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getSimpleVariable()
     * @generated
     */
    EClass SIMPLE_VARIABLE = eINSTANCE.getSimpleVariable();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIMPLE_VARIABLE__VALUE = eINSTANCE.getSimpleVariable_Value();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ArrayVariableImpl <em>Array Variable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ArrayVariableImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getArrayVariable()
     * @generated
     */
    EClass ARRAY_VARIABLE = eINSTANCE.getArrayVariable();

    /**
     * The meta object literal for the '<em><b>Items</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ARRAY_VARIABLE__ITEMS = eINSTANCE.getArrayVariable_Items();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.StepImpl <em>Step</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.StepImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getStep()
     * @generated
     */
    EClass STEP = eINSTANCE.getStep();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute STEP__NAME = eINSTANCE.getStep_Name();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STEP__FEATURES = eINSTANCE.getStep_Features();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModelConstructorStepImpl <em>Model Constructor Step</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModelConstructorStepImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getModelConstructorStep()
     * @generated
     */
    EClass MODEL_CONSTRUCTOR_STEP = eINSTANCE.getModelConstructorStep();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModelOperationStepImpl <em>Model Operation Step</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ModelOperationStepImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getModelOperationStep()
     * @generated
     */
    EClass MODEL_OPERATION_STEP = eINSTANCE.getModelOperationStep();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.OperationFeatureImpl <em>Operation Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.OperationFeatureImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getOperationFeature()
     * @generated
     */
    EClass OPERATION_FEATURE = eINSTANCE.getOperationFeature();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.SourceFeatureImpl <em>Source Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.SourceFeatureImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getSourceFeature()
     * @generated
     */
    EClass SOURCE_FEATURE = eINSTANCE.getSourceFeature();

    /**
     * The meta object literal for the '<em><b>Module Set</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SOURCE_FEATURE__MODULE_SET = eINSTANCE.getSourceFeature_ModuleSet();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ForFeatureImpl <em>For Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ForFeatureImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getForFeature()
     * @generated
     */
    EClass FOR_FEATURE = eINSTANCE.getForFeature();

    /**
     * The meta object literal for the '<em><b>Loop Var</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FOR_FEATURE__LOOP_VAR = eINSTANCE.getForFeature_LoopVar();

    /**
     * The meta object literal for the '<em><b>Array Var</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FOR_FEATURE__ARRAY_VAR = eINSTANCE.getForFeature_ArrayVar();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FOR_FEATURE__FEATURES = eINSTANCE.getForFeature_Features();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.DependencyFeatureImpl <em>Dependency Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.DependencyFeatureImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getDependencyFeature()
     * @generated
     */
    EClass DEPENDENCY_FEATURE = eINSTANCE.getDependencyFeature();

    /**
     * The meta object literal for the '<em><b>Step</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DEPENDENCY_FEATURE__STEP = eINSTANCE.getDependencyFeature_Step();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DEPENDENCY_FEATURE__VARIABLES = eINSTANCE.getDependencyFeature_Variables();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DEPENDENCY_FEATURE__NAME = eINSTANCE.getDependencyFeature_Name();

    /**
     * The meta object literal for the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ImplementationFeatureImpl <em>Implementation Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ImplementationFeatureImpl
     * @see de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslPackageImpl#getImplementationFeature()
     * @generated
     */
    EClass IMPLEMENTATION_FEATURE = eINSTANCE.getImplementationFeature();

    /**
     * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPLEMENTATION_FEATURE__IMPLEMENTATION = eINSTANCE.getImplementationFeature_Implementation();

  }

} //WorkflowDslPackage
