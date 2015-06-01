/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl.impl;

import de.jpwinkler.daf.workflowdsl.workflowDsl.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WorkflowDslFactoryImpl extends EFactoryImpl implements WorkflowDslFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static WorkflowDslFactory init()
  {
    try
    {
      WorkflowDslFactory theWorkflowDslFactory = (WorkflowDslFactory)EPackage.Registry.INSTANCE.getEFactory(WorkflowDslPackage.eNS_URI);
      if (theWorkflowDslFactory != null)
      {
        return theWorkflowDslFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new WorkflowDslFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WorkflowDslFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case WorkflowDslPackage.WORKFLOW_MODEL: return createWorkflowModel();
      case WorkflowDslPackage.WORKFLOW_ELEMENT: return createWorkflowElement();
      case WorkflowDslPackage.TARGET: return createTarget();
      case WorkflowDslPackage.MODULE_SET: return createModuleSet();
      case WorkflowDslPackage.MODULE_SET_ENTRY: return createModuleSetEntry();
      case WorkflowDslPackage.VARIABLE: return createVariable();
      case WorkflowDslPackage.SIMPLE_VARIABLE: return createSimpleVariable();
      case WorkflowDslPackage.ARRAY_VARIABLE: return createArrayVariable();
      case WorkflowDslPackage.STEP: return createStep();
      case WorkflowDslPackage.MODEL_CONSTRUCTOR_STEP: return createModelConstructorStep();
      case WorkflowDslPackage.MODEL_OPERATION_STEP: return createModelOperationStep();
      case WorkflowDslPackage.OPERATION_FEATURE: return createOperationFeature();
      case WorkflowDslPackage.SOURCE_FEATURE: return createSourceFeature();
      case WorkflowDslPackage.FOR_FEATURE: return createForFeature();
      case WorkflowDslPackage.DEPENDENCY_FEATURE: return createDependencyFeature();
      case WorkflowDslPackage.IMPLEMENTATION_FEATURE: return createImplementationFeature();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WorkflowModel createWorkflowModel()
  {
    WorkflowModelImpl workflowModel = new WorkflowModelImpl();
    return workflowModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WorkflowElement createWorkflowElement()
  {
    WorkflowElementImpl workflowElement = new WorkflowElementImpl();
    return workflowElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Target createTarget()
  {
    TargetImpl target = new TargetImpl();
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModuleSet createModuleSet()
  {
    ModuleSetImpl moduleSet = new ModuleSetImpl();
    return moduleSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModuleSetEntry createModuleSetEntry()
  {
    ModuleSetEntryImpl moduleSetEntry = new ModuleSetEntryImpl();
    return moduleSetEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Variable createVariable()
  {
    VariableImpl variable = new VariableImpl();
    return variable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleVariable createSimpleVariable()
  {
    SimpleVariableImpl simpleVariable = new SimpleVariableImpl();
    return simpleVariable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ArrayVariable createArrayVariable()
  {
    ArrayVariableImpl arrayVariable = new ArrayVariableImpl();
    return arrayVariable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Step createStep()
  {
    StepImpl step = new StepImpl();
    return step;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelConstructorStep createModelConstructorStep()
  {
    ModelConstructorStepImpl modelConstructorStep = new ModelConstructorStepImpl();
    return modelConstructorStep;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelOperationStep createModelOperationStep()
  {
    ModelOperationStepImpl modelOperationStep = new ModelOperationStepImpl();
    return modelOperationStep;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OperationFeature createOperationFeature()
  {
    OperationFeatureImpl operationFeature = new OperationFeatureImpl();
    return operationFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SourceFeature createSourceFeature()
  {
    SourceFeatureImpl sourceFeature = new SourceFeatureImpl();
    return sourceFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ForFeature createForFeature()
  {
    ForFeatureImpl forFeature = new ForFeatureImpl();
    return forFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DependencyFeature createDependencyFeature()
  {
    DependencyFeatureImpl dependencyFeature = new DependencyFeatureImpl();
    return dependencyFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ImplementationFeature createImplementationFeature()
  {
    ImplementationFeatureImpl implementationFeature = new ImplementationFeatureImpl();
    return implementationFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WorkflowDslPackage getWorkflowDslPackage()
  {
    return (WorkflowDslPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static WorkflowDslPackage getPackage()
  {
    return WorkflowDslPackage.eINSTANCE;
  }

} //WorkflowDslFactoryImpl
