/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>For Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature#getLoopVar <em>Loop Var</em>}</li>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature#getArrayVar <em>Array Var</em>}</li>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getForFeature()
 * @model
 * @generated
 */
public interface ForFeature extends OperationFeature
{
  /**
   * Returns the value of the '<em><b>Loop Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Loop Var</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Loop Var</em>' attribute.
   * @see #setLoopVar(String)
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getForFeature_LoopVar()
   * @model
   * @generated
   */
  String getLoopVar();

  /**
   * Sets the value of the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature#getLoopVar <em>Loop Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Loop Var</em>' attribute.
   * @see #getLoopVar()
   * @generated
   */
  void setLoopVar(String value);

  /**
   * Returns the value of the '<em><b>Array Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array Var</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array Var</em>' attribute.
   * @see #setArrayVar(String)
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getForFeature_ArrayVar()
   * @model
   * @generated
   */
  String getArrayVar();

  /**
   * Sets the value of the '{@link de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature#getArrayVar <em>Array Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array Var</em>' attribute.
   * @see #getArrayVar()
   * @generated
   */
  void setArrayVar(String value);

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
   * @see de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage#getForFeature_Features()
   * @model containment="true"
   * @generated
   */
  EList<OperationFeature> getFeatures();

} // ForFeature
