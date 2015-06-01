/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl.util;

import de.jpwinkler.daf.workflowdsl.workflowDsl.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage
 * @generated
 */
public class WorkflowDslSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static WorkflowDslPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WorkflowDslSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = WorkflowDslPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case WorkflowDslPackage.WORKFLOW_MODEL:
      {
        WorkflowModel workflowModel = (WorkflowModel)theEObject;
        T result = caseWorkflowModel(workflowModel);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.WORKFLOW_ELEMENT:
      {
        WorkflowElement workflowElement = (WorkflowElement)theEObject;
        T result = caseWorkflowElement(workflowElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.TARGET:
      {
        Target target = (Target)theEObject;
        T result = caseTarget(target);
        if (result == null) result = caseWorkflowElement(target);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.MODULE_SET:
      {
        ModuleSet moduleSet = (ModuleSet)theEObject;
        T result = caseModuleSet(moduleSet);
        if (result == null) result = caseWorkflowElement(moduleSet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.MODULE_SET_ENTRY:
      {
        ModuleSetEntry moduleSetEntry = (ModuleSetEntry)theEObject;
        T result = caseModuleSetEntry(moduleSetEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.VARIABLE:
      {
        Variable variable = (Variable)theEObject;
        T result = caseVariable(variable);
        if (result == null) result = caseWorkflowElement(variable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.SIMPLE_VARIABLE:
      {
        SimpleVariable simpleVariable = (SimpleVariable)theEObject;
        T result = caseSimpleVariable(simpleVariable);
        if (result == null) result = caseVariable(simpleVariable);
        if (result == null) result = caseWorkflowElement(simpleVariable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.ARRAY_VARIABLE:
      {
        ArrayVariable arrayVariable = (ArrayVariable)theEObject;
        T result = caseArrayVariable(arrayVariable);
        if (result == null) result = caseVariable(arrayVariable);
        if (result == null) result = caseWorkflowElement(arrayVariable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.STEP:
      {
        Step step = (Step)theEObject;
        T result = caseStep(step);
        if (result == null) result = caseWorkflowElement(step);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.MODEL_CONSTRUCTOR_STEP:
      {
        ModelConstructorStep modelConstructorStep = (ModelConstructorStep)theEObject;
        T result = caseModelConstructorStep(modelConstructorStep);
        if (result == null) result = caseStep(modelConstructorStep);
        if (result == null) result = caseWorkflowElement(modelConstructorStep);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.MODEL_OPERATION_STEP:
      {
        ModelOperationStep modelOperationStep = (ModelOperationStep)theEObject;
        T result = caseModelOperationStep(modelOperationStep);
        if (result == null) result = caseStep(modelOperationStep);
        if (result == null) result = caseWorkflowElement(modelOperationStep);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.OPERATION_FEATURE:
      {
        OperationFeature operationFeature = (OperationFeature)theEObject;
        T result = caseOperationFeature(operationFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.SOURCE_FEATURE:
      {
        SourceFeature sourceFeature = (SourceFeature)theEObject;
        T result = caseSourceFeature(sourceFeature);
        if (result == null) result = caseOperationFeature(sourceFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.FOR_FEATURE:
      {
        ForFeature forFeature = (ForFeature)theEObject;
        T result = caseForFeature(forFeature);
        if (result == null) result = caseOperationFeature(forFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.DEPENDENCY_FEATURE:
      {
        DependencyFeature dependencyFeature = (DependencyFeature)theEObject;
        T result = caseDependencyFeature(dependencyFeature);
        if (result == null) result = caseOperationFeature(dependencyFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case WorkflowDslPackage.IMPLEMENTATION_FEATURE:
      {
        ImplementationFeature implementationFeature = (ImplementationFeature)theEObject;
        T result = caseImplementationFeature(implementationFeature);
        if (result == null) result = caseOperationFeature(implementationFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Workflow Model</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Workflow Model</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWorkflowModel(WorkflowModel object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Workflow Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Workflow Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWorkflowElement(WorkflowElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Target</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Target</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTarget(Target object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Module Set</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Module Set</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModuleSet(ModuleSet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Module Set Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Module Set Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModuleSetEntry(ModuleSetEntry object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Variable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVariable(Variable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Simple Variable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Simple Variable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSimpleVariable(SimpleVariable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Array Variable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Array Variable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseArrayVariable(ArrayVariable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Step</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Step</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStep(Step object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model Constructor Step</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Constructor Step</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModelConstructorStep(ModelConstructorStep object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model Operation Step</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Operation Step</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModelOperationStep(ModelOperationStep object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperationFeature(OperationFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Source Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Source Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSourceFeature(SourceFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>For Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>For Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseForFeature(ForFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Dependency Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Dependency Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDependencyFeature(DependencyFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Implementation Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Implementation Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseImplementationFeature(ImplementationFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //WorkflowDslSwitch
