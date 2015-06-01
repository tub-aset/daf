/**
 */
package de.jpwinkler.daf.fap5.model.srs;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Functional Specification Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage#getFunctionalSpecificationContainer()
 * @model
 * @generated
 */
public interface FunctionalSpecificationContainer extends FunctionalSpecificationElement {
	/**
     * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationElement}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Elements</em>' containment reference list.
     * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage#getFunctionalSpecificationContainer_Elements()
     * @model containment="true"
     * @generated
     */
	EList<FunctionalSpecificationElement> getElements();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	EList<FunctionContribution> getFunctionContributions();

} // FunctionalSpecificationContainer
