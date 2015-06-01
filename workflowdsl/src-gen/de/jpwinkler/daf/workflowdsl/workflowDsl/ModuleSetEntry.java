/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Set Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry#getType <em>Type</em>}</li>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getModuleSetEntry()
 * @model
 * @generated
 */
public interface ModuleSetEntry extends EObject
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute.
   * @see #setType(String)
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getModuleSetEntry_Type()
   * @model
   * @generated
   */
  String getType();

  /**
   * Sets the value of the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see #getType()
   * @generated
   */
  void setType(String value);

  /**
   * Returns the value of the '<em><b>Reference</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference</em>' attribute.
   * @see #setReference(String)
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getModuleSetEntry_Reference()
   * @model
   * @generated
   */
  String getReference();

  /**
   * Sets the value of the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry#getReference <em>Reference</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference</em>' attribute.
   * @see #getReference()
   * @generated
   */
  void setReference(String value);

} // ModuleSetEntry
