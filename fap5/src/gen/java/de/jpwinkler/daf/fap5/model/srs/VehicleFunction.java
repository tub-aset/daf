/**
 */
package de.jpwinkler.daf.fap5.model.srs;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vehicle Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getType <em>Type</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getSubFunctions <em>Sub Functions</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage#getVehicleFunction()
 * @model
 * @generated
 */
public interface VehicleFunction extends FunctionalSpecificationContainer {
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
     * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage#getVehicleFunction_Name()
     * @model
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The literals are from the enumeration {@link de.jpwinkler.daf.fap5.model.srs.VehicleFunctionType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see de.jpwinkler.daf.fap5.model.srs.VehicleFunctionType
     * @see #setType(VehicleFunctionType)
     * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage#getVehicleFunction_Type()
     * @model
     * @generated
     */
	VehicleFunctionType getType();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see de.jpwinkler.daf.fap5.model.srs.VehicleFunctionType
     * @see #getType()
     * @generated
     */
	void setType(VehicleFunctionType value);

	/**
     * Returns the value of the '<em><b>Sub Functions</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction}.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Functions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Sub Functions</em>' containment reference list.
     * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage#getVehicleFunction_SubFunctions()
     * @see de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getParent
     * @model opposite="parent" containment="true"
     * @generated
     */
	EList<VehicleFunction> getSubFunctions();

	/**
     * Returns the value of the '<em><b>Parent</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getSubFunctions <em>Sub Functions</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' container reference.
     * @see #setParent(VehicleFunction)
     * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage#getVehicleFunction_Parent()
     * @see de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getSubFunctions
     * @model opposite="subFunctions" transient="false"
     * @generated
     */
	VehicleFunction getParent();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getParent <em>Parent</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' container reference.
     * @see #getParent()
     * @generated
     */
	void setParent(VehicleFunction value);

} // VehicleFunction
