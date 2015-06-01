/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage
 * @generated
 */
public interface WorkflowDslFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  WorkflowDslFactory eINSTANCE = de.jpwinkler.daf.workflowdsl.workflowDsl.impl.WorkflowDslFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Workflow Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Workflow Model</em>'.
   * @generated
   */
  WorkflowModel createWorkflowModel();

  /**
   * Returns a new object of class '<em>Workflow Element</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Workflow Element</em>'.
   * @generated
   */
  WorkflowElement createWorkflowElement();

  /**
   * Returns a new object of class '<em>Target</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Target</em>'.
   * @generated
   */
  Target createTarget();

  /**
   * Returns a new object of class '<em>Module Set</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Module Set</em>'.
   * @generated
   */
  ModuleSet createModuleSet();

  /**
   * Returns a new object of class '<em>Module Set Entry</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Module Set Entry</em>'.
   * @generated
   */
  ModuleSetEntry createModuleSetEntry();

  /**
   * Returns a new object of class '<em>Variable</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Variable</em>'.
   * @generated
   */
  Variable createVariable();

  /**
   * Returns a new object of class '<em>Simple Variable</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Simple Variable</em>'.
   * @generated
   */
  SimpleVariable createSimpleVariable();

  /**
   * Returns a new object of class '<em>Array Variable</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Array Variable</em>'.
   * @generated
   */
  ArrayVariable createArrayVariable();

  /**
   * Returns a new object of class '<em>Step</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Step</em>'.
   * @generated
   */
  Step createStep();

  /**
   * Returns a new object of class '<em>Model Constructor Step</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Constructor Step</em>'.
   * @generated
   */
  ModelConstructorStep createModelConstructorStep();

  /**
   * Returns a new object of class '<em>Model Operation Step</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Operation Step</em>'.
   * @generated
   */
  ModelOperationStep createModelOperationStep();

  /**
   * Returns a new object of class '<em>Operation Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation Feature</em>'.
   * @generated
   */
  OperationFeature createOperationFeature();

  /**
   * Returns a new object of class '<em>Source Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Source Feature</em>'.
   * @generated
   */
  SourceFeature createSourceFeature();

  /**
   * Returns a new object of class '<em>For Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>For Feature</em>'.
   * @generated
   */
  ForFeature createForFeature();

  /**
   * Returns a new object of class '<em>Dependency Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Dependency Feature</em>'.
   * @generated
   */
  DependencyFeature createDependencyFeature();

  /**
   * Returns a new object of class '<em>Implementation Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Implementation Feature</em>'.
   * @generated
   */
  ImplementationFeature createImplementationFeature();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  WorkflowDslPackage getWorkflowDslPackage();

} //WorkflowDslFactory
