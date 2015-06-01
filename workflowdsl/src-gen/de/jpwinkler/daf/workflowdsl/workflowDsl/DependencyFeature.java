/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dependency Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature#getStep <em>Step</em>}</li>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature#getVariables <em>Variables</em>}</li>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getDependencyFeature()
 * @model
 * @generated
 */
public interface DependencyFeature extends OperationFeature
{
  /**
   * Returns the value of the '<em><b>Step</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Step</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Step</em>' reference.
   * @see #setStep(Step)
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getDependencyFeature_Step()
   * @model
   * @generated
   */
  Step getStep();

  /**
   * Sets the value of the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature#getStep <em>Step</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Step</em>' reference.
   * @see #getStep()
   * @generated
   */
  void setStep(Step value);

  /**
   * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
   * The list contents are of type {@link de.jpwinkler.daf.workflowdsl.workflowDsl.Variable}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variables</em>' containment reference list.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getDependencyFeature_Variables()
   * @model containment="true"
   * @generated
   */
  EList<Variable> getVariables();

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getDependencyFeature_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // DependencyFeature
