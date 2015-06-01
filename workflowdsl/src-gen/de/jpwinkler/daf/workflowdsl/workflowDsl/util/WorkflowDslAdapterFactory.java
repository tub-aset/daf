/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl.util;

import de.jpwinkler.daf.workflowdsl.workflowDsl.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage
 * @generated
 */
public class WorkflowDslAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static WorkflowDslPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WorkflowDslAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = WorkflowDslPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WorkflowDslSwitch<Adapter> modelSwitch =
    new WorkflowDslSwitch<Adapter>()
    {
      @Override
      public Adapter caseWorkflowModel(WorkflowModel object)
      {
        return createWorkflowModelAdapter();
      }
      @Override
      public Adapter caseWorkflowElement(WorkflowElement object)
      {
        return createWorkflowElementAdapter();
      }
      @Override
      public Adapter caseTarget(Target object)
      {
        return createTargetAdapter();
      }
      @Override
      public Adapter caseModuleSet(ModuleSet object)
      {
        return createModuleSetAdapter();
      }
      @Override
      public Adapter caseModuleSetEntry(ModuleSetEntry object)
      {
        return createModuleSetEntryAdapter();
      }
      @Override
      public Adapter caseVariable(Variable object)
      {
        return createVariableAdapter();
      }
      @Override
      public Adapter caseSimpleVariable(SimpleVariable object)
      {
        return createSimpleVariableAdapter();
      }
      @Override
      public Adapter caseArrayVariable(ArrayVariable object)
      {
        return createArrayVariableAdapter();
      }
      @Override
      public Adapter caseStep(Step object)
      {
        return createStepAdapter();
      }
      @Override
      public Adapter caseModelConstructorStep(ModelConstructorStep object)
      {
        return createModelConstructorStepAdapter();
      }
      @Override
      public Adapter caseModelOperationStep(ModelOperationStep object)
      {
        return createModelOperationStepAdapter();
      }
      @Override
      public Adapter caseOperationFeature(OperationFeature object)
      {
        return createOperationFeatureAdapter();
      }
      @Override
      public Adapter caseSourceFeature(SourceFeature object)
      {
        return createSourceFeatureAdapter();
      }
      @Override
      public Adapter caseForFeature(ForFeature object)
      {
        return createForFeatureAdapter();
      }
      @Override
      public Adapter caseDependencyFeature(DependencyFeature object)
      {
        return createDependencyFeatureAdapter();
      }
      @Override
      public Adapter caseImplementationFeature(ImplementationFeature object)
      {
        return createImplementationFeatureAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowModel <em>Workflow Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowModel
   * @generated
   */
  public Adapter createWorkflowModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowElement <em>Workflow Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowElement
   * @generated
   */
  public Adapter createWorkflowElementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Target <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.Target
   * @generated
   */
  public Adapter createTargetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet <em>Module Set</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet
   * @generated
   */
  public Adapter createModuleSetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry <em>Module Set Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry
   * @generated
   */
  public Adapter createModuleSetEntryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Variable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.Variable
   * @generated
   */
  public Adapter createVariableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.SimpleVariable <em>Simple Variable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.SimpleVariable
   * @generated
   */
  public Adapter createSimpleVariableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ArrayVariable <em>Array Variable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ArrayVariable
   * @generated
   */
  public Adapter createArrayVariableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Step <em>Step</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.Step
   * @generated
   */
  public Adapter createStepAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModelConstructorStep <em>Model Constructor Step</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ModelConstructorStep
   * @generated
   */
  public Adapter createModelConstructorStepAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModelOperationStep <em>Model Operation Step</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ModelOperationStep
   * @generated
   */
  public Adapter createModelOperationStepAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.OperationFeature <em>Operation Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.OperationFeature
   * @generated
   */
  public Adapter createOperationFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature <em>Source Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature
   * @generated
   */
  public Adapter createSourceFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature <em>For Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature
   * @generated
   */
  public Adapter createForFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature <em>Dependency Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature
   * @generated
   */
  public Adapter createDependencyFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ImplementationFeature <em>Implementation Feature</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.ImplementationFeature
   * @generated
   */
  public Adapter createImplementationFeatureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //WorkflowDslAdapterFactory
