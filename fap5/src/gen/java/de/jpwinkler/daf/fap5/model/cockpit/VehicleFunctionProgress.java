/**
 */
package de.jpwinkler.daf.fap5.model.cockpit;

import org.eclipse.emf.common.util.EList;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.fap5.model.srs.VehicleFunction;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vehicle Function Progress</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getVehicleFunction <em>Vehicle Function</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getCockpitModel <em>Cockpit Model</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getSubFunctionProgress <em>Sub Function Progress</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getParentProgress <em>Parent Progress</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getFunctionContributionProgress <em>Function Contribution Progress</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getVehicleFunctionProgress()
 * @model
 * @generated
 */
public interface VehicleFunctionProgress extends ModelObject {
	/**
     * Returns the value of the '<em><b>Vehicle Function</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vehicle Function</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Vehicle Function</em>' reference.
     * @see #setVehicleFunction(VehicleFunction)
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getVehicleFunctionProgress_VehicleFunction()
     * @model
     * @generated
     */
	VehicleFunction getVehicleFunction();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getVehicleFunction <em>Vehicle Function</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Vehicle Function</em>' reference.
     * @see #getVehicleFunction()
     * @generated
     */
	void setVehicleFunction(VehicleFunction value);

	/**
     * Returns the value of the '<em><b>Cockpit Model</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cockpit Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Cockpit Model</em>' reference.
     * @see #setCockpitModel(CockpitModel)
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getVehicleFunctionProgress_CockpitModel()
     * @model
     * @generated
     */
	CockpitModel getCockpitModel();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getCockpitModel <em>Cockpit Model</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Cockpit Model</em>' reference.
     * @see #getCockpitModel()
     * @generated
     */
	void setCockpitModel(CockpitModel value);

	/**
     * Returns the value of the '<em><b>Sub Function Progress</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress}.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getParentProgress <em>Parent Progress</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Function Progress</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Sub Function Progress</em>' containment reference list.
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getVehicleFunctionProgress_SubFunctionProgress()
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getParentProgress
     * @model opposite="parentProgress" containment="true"
     * @generated
     */
	EList<VehicleFunctionProgress> getSubFunctionProgress();

	/**
     * Returns the value of the '<em><b>Parent Progress</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getSubFunctionProgress <em>Sub Function Progress</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Progress</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parent Progress</em>' container reference.
     * @see #setParentProgress(VehicleFunctionProgress)
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getVehicleFunctionProgress_ParentProgress()
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getSubFunctionProgress
     * @model opposite="subFunctionProgress" transient="false"
     * @generated
     */
	VehicleFunctionProgress getParentProgress();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getParentProgress <em>Parent Progress</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent Progress</em>' container reference.
     * @see #getParentProgress()
     * @generated
     */
	void setParentProgress(VehicleFunctionProgress value);

	/**
     * Returns the value of the '<em><b>Function Contribution Progress</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Contribution Progress</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Function Contribution Progress</em>' containment reference list.
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#getVehicleFunctionProgress_FunctionContributionProgress()
     * @model containment="true"
     * @generated
     */
	EList<FunctionContributionProgress> getFunctionContributionProgress();

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
	boolean isMapped();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	double getProgress();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	double getEstimatedRemainingWork();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	long getSpecifiedFunctionContributionCount();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	long getTotalFunctionContributionCount();

} // VehicleFunctionProgress
