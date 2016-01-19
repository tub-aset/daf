/**
 */
package de.jpwinkler.daf.fap5.model.cockpit;

import org.eclipse.emf.common.util.EList;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#getMappings <em>Mappings</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#getVehicleFunctionProgress <em>Vehicle Function Progress</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#getDocuments <em>Documents</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getCockpitModel()
 * @model
 * @generated
 */
public interface CockpitModel extends ModelObject {
	/**
     * Returns the value of the '<em><b>Vehicle Function Progress</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vehicle Function Progress</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Vehicle Function Progress</em>' containment reference list.
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getCockpitModel_VehicleFunctionProgress()
     * @model containment="true"
     * @generated
     */
	EList<VehicleFunctionProgress> getVehicleFunctionProgress();

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
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getCockpitModel_Documents()
     * @model
     * @generated
     */
	EList<CodeBeamerModel> getDocuments();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model
     * @generated
     */
	FunctionContributionTargetMapping findMapping(FunctionContributionTarget fct);

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model
     * @generated
     */
	EList<FunctionContributionTarget> findFunctionContributionTargets(CodeBeamerModel cbm);

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	EList<String> getIssueTypes();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model
     * @generated
     */
	EList<Issue> getIssues(String issueType);

	/**
     * Returns the value of the '<em><b>Mappings</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Mappings</em>' containment reference list.
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getCockpitModel_Mappings()
     * @model containment="true"
     * @generated
     */
	EList<FunctionContributionTargetMapping> getMappings();

} // CockpitModel
