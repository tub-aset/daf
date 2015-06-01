/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet#getModuleSetEntries <em>Module Set Entries</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getModuleSet()
 * @model
 * @generated
 */
public interface ModuleSet extends WorkflowElement
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
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getModuleSet_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Module Set Entries</b></em>' containment reference list.
   * The list contents are of type {@link de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Module Set Entries</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Module Set Entries</em>' containment reference list.
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getModuleSet_ModuleSetEntries()
   * @model containment="true"
   * @generated
   */
  EList<ModuleSetEntry> getModuleSetEntries();

} // ModuleSet
