/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Target#getStep <em>Step</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getTarget()
 * @model
 * @generated
 */
public interface Target extends WorkflowElement
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
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getTarget_Step()
   * @model
   * @generated
   */
  Step getStep();

  /**
   * Sets the value of the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.Target#getStep <em>Step</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Step</em>' reference.
   * @see #getStep()
   * @generated
   */
  void setStep(Step value);

} // Target
