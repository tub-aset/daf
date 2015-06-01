/**
 */
package de.jpwinkler.daf.fap5.model.cockpit;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.fap5.model.srs.FunctionContribution;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Contribution Progress</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#getFunctionContribution <em>Function Contribution</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#getCockpitModel <em>Cockpit Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getFunctionContributionProgress()
 * @model
 * @generated
 */
public interface FunctionContributionProgress extends ModelObject {
	/**
     * Returns the value of the '<em><b>Function Contribution</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Contribution</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Function Contribution</em>' reference.
     * @see #setFunctionContribution(FunctionContribution)
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getFunctionContributionProgress_FunctionContribution()
     * @model
     * @generated
     */
	FunctionContribution getFunctionContribution();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#getFunctionContribution <em>Function Contribution</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Function Contribution</em>' reference.
     * @see #getFunctionContribution()
     * @generated
     */
	void setFunctionContribution(FunctionContribution value);

	/**
     * Returns the value of the '<em><b>Cockpit Model</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cockpit Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Cockpit Model</em>' reference.
     * @see #setCockpitModel(CockpitModel)
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getFunctionContributionProgress_CockpitModel()
     * @model
     * @generated
     */
	CockpitModel getCockpitModel();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#getCockpitModel <em>Cockpit Model</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Cockpit Model</em>' reference.
     * @see #getCockpitModel()
     * @generated
     */
	void setCockpitModel(CockpitModel value);

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	boolean isMapped();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	boolean isSpecified();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	double getExstimatedRemainingWork();

} // FunctionContributionProgress
