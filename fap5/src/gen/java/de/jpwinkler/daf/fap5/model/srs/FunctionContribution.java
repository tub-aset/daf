/**
 */
package de.jpwinkler.daf.fap5.model.srs;

import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Contribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.FunctionContribution#getTarget <em>Target</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.FunctionContribution#getText <em>Text</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage#getFunctionContribution()
 * @model
 * @generated
 */
public interface FunctionContribution extends FunctionalSpecificationElement {
	/**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getContributions <em>Contributions</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(FunctionContributionTarget)
     * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage#getFunctionContribution_Target()
     * @see de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getContributions
     * @model opposite="contributions"
     * @generated
     */
	FunctionContributionTarget getTarget();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.srs.FunctionContribution#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
	void setTarget(FunctionContributionTarget value);

	/**
     * Returns the value of the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' attribute.
     * @see #setText(String)
     * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage#getFunctionContribution_Text()
     * @model
     * @generated
     */
	String getText();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.srs.FunctionContribution#getText <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' attribute.
     * @see #getText()
     * @generated
     */
	void setText(String value);

} // FunctionContribution
