/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Source Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature#getModuleSet <em>Module Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getSourceFeature()
 * @model
 * @generated
 */
public interface SourceFeature extends OperationFeature
{
  /**
   * Returns the value of the '<em><b>Module Set</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Module Set</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Module Set</em>' reference.
   * @see #setModuleSet(ModuleSet)
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getSourceFeature_ModuleSet()
   * @model
   * @generated
   */
  ModuleSet getModuleSet();

  /**
   * Sets the value of the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature#getModuleSet <em>Module Set</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Module Set</em>' reference.
   * @see #getModuleSet()
   * @generated
   */
  void setModuleSet(ModuleSet value);

} // SourceFeature
