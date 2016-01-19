/**
 */
package de.jpwinkler.daf.fap5.model.cockpit;

import org.eclipse.emf.common.util.EList;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Contribution Target Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping#getFunctionContributionTarget <em>Function Contribution Target</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping#getDocuments <em>Documents</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getFunctionContributionTargetMapping()
 * @model
 * @generated
 */
public interface FunctionContributionTargetMapping extends ModelObject {
	/**
     * Returns the value of the '<em><b>Function Contribution Target</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Contribution Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Function Contribution Target</em>' reference.
     * @see #setFunctionContributionTarget(FunctionContributionTarget)
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getFunctionContributionTargetMapping_FunctionContributionTarget()
     * @model
     * @generated
     */
	FunctionContributionTarget getFunctionContributionTarget();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping#getFunctionContributionTarget <em>Function Contribution Target</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Function Contribution Target</em>' reference.
     * @see #getFunctionContributionTarget()
     * @generated
     */
	void setFunctionContributionTarget(FunctionContributionTarget value);

	/**
     * Returns the value of the '<em><b>Documents</b></em>' reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documents</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Documents</em>' reference list.
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getFunctionContributionTargetMapping_Documents()
     * @model
     * @generated
     */
	EList<CodeBeamerModel> getDocuments();

} // FunctionContributionTargetMapping
