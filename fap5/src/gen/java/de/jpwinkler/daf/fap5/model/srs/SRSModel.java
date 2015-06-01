/**
 */
package de.jpwinkler.daf.fap5.model.srs;

import org.eclipse.emf.common.util.EList;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SRS Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.SRSModel#getVehicleFunctions <em>Vehicle Functions</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage#getSRSModel()
 * @model
 * @generated
 */
public interface SRSModel extends ModelObject {
	/**
     * Returns the value of the '<em><b>Vehicle Functions</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vehicle Functions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Vehicle Functions</em>' containment reference list.
     * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage#getSRSModel_VehicleFunctions()
     * @model containment="true"
     * @generated
     */
	EList<VehicleFunction> getVehicleFunctions();

} // SRSModel
