/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Step#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Step#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getStep()
 * @model
 * @generated
 */
public interface Step extends WorkflowElement
{
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
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getStep_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Step#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Features</b></em>' containment reference list.
   * The list contents are of type {@link de.jpwinkler.daf.workflowdsl.workflowDsl.OperationFeature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Features</em>' containment reference list.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getStep_Features()
   * @model containment="true"
   * @generated
   */
  EList<OperationFeature> getFeatures();

} // Step
