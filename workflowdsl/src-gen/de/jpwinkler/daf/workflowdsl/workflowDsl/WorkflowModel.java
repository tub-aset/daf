/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workflow Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowModel#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getWorkflowModel()
 * @model
 * @generated
 */
public interface WorkflowModel extends EObject
{
  /**
   * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
   * The list contents are of type {@link de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowElement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elements</em>' containment reference list.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getWorkflowModel_Elements()
   * @model containment="true"
   * @generated
   */
  EList<WorkflowElement> getElements();

} // WorkflowModel
