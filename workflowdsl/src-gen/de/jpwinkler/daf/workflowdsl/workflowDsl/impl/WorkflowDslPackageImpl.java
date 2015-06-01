/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl.impl;

import de.jpwinkler.daf.workflowdsl.workflowDsl.ArrayVariable;
import de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ImplementationFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModelConstructorStep;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModelOperationStep;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry;
import de.jpwinkler.daf.workflowdsl.workflowDsl.OperationFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.SimpleVariable;
import de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Step;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Target;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Variable;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslFactory;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowElement;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowModel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WorkflowDslPackageImpl extends EPackageImpl implements WorkflowDslPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass workflowModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass workflowElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass targetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass moduleSetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass moduleSetEntryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass simpleVariableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass arrayVariableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stepEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelConstructorStepEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelOperationStepEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass operationFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sourceFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass forFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dependencyFeatureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass implementationFeatureEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private WorkflowDslPackageImpl()
  {
    super(eNS_URI, WorkflowDslFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link WorkflowDslPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static WorkflowDslPackage init()
  {
    if (isInited) return (WorkflowDslPackage)EPackage.Registry.INSTANCE.getEPackage(WorkflowDslPackage.eNS_URI);

    // Obtain or create and register package
    WorkflowDslPackageImpl theWorkflowDslPackage = (WorkflowDslPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof WorkflowDslPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new WorkflowDslPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theWorkflowDslPackage.createPackageContents();

    // Initialize created meta-data
    theWorkflowDslPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theWorkflowDslPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(WorkflowDslPackage.eNS_URI, theWorkflowDslPackage);
    return theWorkflowDslPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWorkflowModel()
  {
    return workflowModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getWorkflowModel_Elements()
  {
    return (EReference)workflowModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWorkflowElement()
  {
    return workflowElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTarget()
  {
    return targetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTarget_Step()
  {
    return (EReference)targetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModuleSet()
  {
    return moduleSetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getModuleSet_Name()
  {
    return (EAttribute)moduleSetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModuleSet_ModuleSetEntries()
  {
    return (EReference)moduleSetEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModuleSetEntry()
  {
    return moduleSetEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getModuleSetEntry_Type()
  {
    return (EAttribute)moduleSetEntryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getModuleSetEntry_Reference()
  {
    return (EAttribute)moduleSetEntryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariable()
  {
    return variableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVariable_Name()
  {
    return (EAttribute)variableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSimpleVariable()
  {
    return simpleVariableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSimpleVariable_Value()
  {
    return (EAttribute)simpleVariableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getArrayVariable()
  {
    return arrayVariableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getArrayVariable_Items()
  {
    return (EAttribute)arrayVariableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStep()
  {
    return stepEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getStep_Name()
  {
    return (EAttribute)stepEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getStep_Features()
  {
    return (EReference)stepEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModelConstructorStep()
  {
    return modelConstructorStepEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModelOperationStep()
  {
    return modelOperationStepEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOperationFeature()
  {
    return operationFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSourceFeature()
  {
    return sourceFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSourceFeature_ModuleSet()
  {
    return (EReference)sourceFeatureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getForFeature()
  {
    return forFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getForFeature_LoopVar()
  {
    return (EAttribute)forFeatureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getForFeature_ArrayVar()
  {
    return (EAttribute)forFeatureEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getForFeature_Features()
  {
    return (EReference)forFeatureEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDependencyFeature()
  {
    return dependencyFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDependencyFeature_Step()
  {
    return (EReference)dependencyFeatureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDependencyFeature_Variables()
  {
    return (EReference)dependencyFeatureEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDependencyFeature_Name()
  {
    return (EAttribute)dependencyFeatureEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImplementationFeature()
  {
    return implementationFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImplementationFeature_Implementation()
  {
    return (EAttribute)implementationFeatureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WorkflowDslFactory getWorkflowDslFactory()
  {
    return (WorkflowDslFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    workflowModelEClass = createEClass(WORKFLOW_MODEL);
    createEReference(workflowModelEClass, WORKFLOW_MODEL__ELEMENTS);

    workflowElementEClass = createEClass(WORKFLOW_ELEMENT);

    targetEClass = createEClass(TARGET);
    createEReference(targetEClass, TARGET__STEP);

    moduleSetEClass = createEClass(MODULE_SET);
    createEAttribute(moduleSetEClass, MODULE_SET__NAME);
    createEReference(moduleSetEClass, MODULE_SET__MODULE_SET_ENTRIES);

    moduleSetEntryEClass = createEClass(MODULE_SET_ENTRY);
    createEAttribute(moduleSetEntryEClass, MODULE_SET_ENTRY__TYPE);
    createEAttribute(moduleSetEntryEClass, MODULE_SET_ENTRY__REFERENCE);

    variableEClass = createEClass(VARIABLE);
    createEAttribute(variableEClass, VARIABLE__NAME);

    simpleVariableEClass = createEClass(SIMPLE_VARIABLE);
    createEAttribute(simpleVariableEClass, SIMPLE_VARIABLE__VALUE);

    arrayVariableEClass = createEClass(ARRAY_VARIABLE);
    createEAttribute(arrayVariableEClass, ARRAY_VARIABLE__ITEMS);

    stepEClass = createEClass(STEP);
    createEAttribute(stepEClass, STEP__NAME);
    createEReference(stepEClass, STEP__FEATURES);

    modelConstructorStepEClass = createEClass(MODEL_CONSTRUCTOR_STEP);

    modelOperationStepEClass = createEClass(MODEL_OPERATION_STEP);

    operationFeatureEClass = createEClass(OPERATION_FEATURE);

    sourceFeatureEClass = createEClass(SOURCE_FEATURE);
    createEReference(sourceFeatureEClass, SOURCE_FEATURE__MODULE_SET);

    forFeatureEClass = createEClass(FOR_FEATURE);
    createEAttribute(forFeatureEClass, FOR_FEATURE__LOOP_VAR);
    createEAttribute(forFeatureEClass, FOR_FEATURE__ARRAY_VAR);
    createEReference(forFeatureEClass, FOR_FEATURE__FEATURES);

    dependencyFeatureEClass = createEClass(DEPENDENCY_FEATURE);
    createEReference(dependencyFeatureEClass, DEPENDENCY_FEATURE__STEP);
    createEReference(dependencyFeatureEClass, DEPENDENCY_FEATURE__VARIABLES);
    createEAttribute(dependencyFeatureEClass, DEPENDENCY_FEATURE__NAME);

    implementationFeatureEClass = createEClass(IMPLEMENTATION_FEATURE);
    createEAttribute(implementationFeatureEClass, IMPLEMENTATION_FEATURE__IMPLEMENTATION);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    targetEClass.getESuperTypes().add(this.getWorkflowElement());
    moduleSetEClass.getESuperTypes().add(this.getWorkflowElement());
    variableEClass.getESuperTypes().add(this.getWorkflowElement());
    simpleVariableEClass.getESuperTypes().add(this.getVariable());
    arrayVariableEClass.getESuperTypes().add(this.getVariable());
    stepEClass.getESuperTypes().add(this.getWorkflowElement());
    modelConstructorStepEClass.getESuperTypes().add(this.getStep());
    modelOperationStepEClass.getESuperTypes().add(this.getStep());
    sourceFeatureEClass.getESuperTypes().add(this.getOperationFeature());
    forFeatureEClass.getESuperTypes().add(this.getOperationFeature());
    dependencyFeatureEClass.getESuperTypes().add(this.getOperationFeature());
    implementationFeatureEClass.getESuperTypes().add(this.getOperationFeature());

    // Initialize classes and features; add operations and parameters
    initEClass(workflowModelEClass, WorkflowModel.class, "WorkflowModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getWorkflowModel_Elements(), this.getWorkflowElement(), null, "elements", null, 0, -1, WorkflowModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(workflowElementEClass, WorkflowElement.class, "WorkflowElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(targetEClass, Target.class, "Target", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTarget_Step(), this.getStep(), null, "step", null, 0, 1, Target.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(moduleSetEClass, ModuleSet.class, "ModuleSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getModuleSet_Name(), ecorePackage.getEString(), "name", null, 0, 1, ModuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModuleSet_ModuleSetEntries(), this.getModuleSetEntry(), null, "moduleSetEntries", null, 0, -1, ModuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(moduleSetEntryEClass, ModuleSetEntry.class, "ModuleSetEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getModuleSetEntry_Type(), ecorePackage.getEString(), "type", null, 0, 1, ModuleSetEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getModuleSetEntry_Reference(), ecorePackage.getEString(), "reference", null, 0, 1, ModuleSetEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variableEClass, Variable.class, "Variable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getVariable_Name(), ecorePackage.getEString(), "name", null, 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(simpleVariableEClass, SimpleVariable.class, "SimpleVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSimpleVariable_Value(), ecorePackage.getEString(), "value", null, 0, 1, SimpleVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(arrayVariableEClass, ArrayVariable.class, "ArrayVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getArrayVariable_Items(), ecorePackage.getEString(), "items", null, 0, -1, ArrayVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(stepEClass, Step.class, "Step", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getStep_Name(), ecorePackage.getEString(), "name", null, 0, 1, Step.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getStep_Features(), this.getOperationFeature(), null, "features", null, 0, -1, Step.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(modelConstructorStepEClass, ModelConstructorStep.class, "ModelConstructorStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(modelOperationStepEClass, ModelOperationStep.class, "ModelOperationStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(operationFeatureEClass, OperationFeature.class, "OperationFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(sourceFeatureEClass, SourceFeature.class, "SourceFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSourceFeature_ModuleSet(), this.getModuleSet(), null, "moduleSet", null, 0, 1, SourceFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(forFeatureEClass, ForFeature.class, "ForFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getForFeature_LoopVar(), ecorePackage.getEString(), "loopVar", null, 0, 1, ForFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getForFeature_ArrayVar(), ecorePackage.getEString(), "arrayVar", null, 0, 1, ForFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getForFeature_Features(), this.getOperationFeature(), null, "features", null, 0, -1, ForFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(dependencyFeatureEClass, DependencyFeature.class, "DependencyFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDependencyFeature_Step(), this.getStep(), null, "step", null, 0, 1, DependencyFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDependencyFeature_Variables(), this.getVariable(), null, "variables", null, 0, -1, DependencyFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDependencyFeature_Name(), ecorePackage.getEString(), "name", null, 0, 1, DependencyFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(implementationFeatureEClass, ImplementationFeature.class, "ImplementationFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getImplementationFeature_Implementation(), ecorePackage.getEString(), "implementation", null, 0, 1, ImplementationFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //WorkflowDslPackageImpl
