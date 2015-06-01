/**
 */
package de.jpwinkler.daf.fap5.model.srs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl;
import de.jpwinkler.daf.fap5.model.srs.SRSModel;
import de.jpwinkler.daf.fap5.model.srs.SrsPackage;
import de.jpwinkler.daf.fap5.model.srs.VehicleFunction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SRS Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.impl.SRSModelImpl#getVehicleFunctions <em>Vehicle Functions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SRSModelImpl extends ModelObjectImpl implements SRSModel {
	/**
     * The cached value of the '{@link #getVehicleFunctions() <em>Vehicle Functions</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVehicleFunctions()
     * @generated
     * @ordered
     */
	protected EList<VehicleFunction> vehicleFunctions;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SRSModelImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return SrsPackage.Literals.SRS_MODEL;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<VehicleFunction> getVehicleFunctions() {
        if (vehicleFunctions == null) {
            vehicleFunctions = new EObjectContainmentEList<VehicleFunction>(VehicleFunction.class, this, SrsPackage.SRS_MODEL__VEHICLE_FUNCTIONS);
        }
        return vehicleFunctions;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SrsPackage.SRS_MODEL__VEHICLE_FUNCTIONS:
                return ((InternalEList<?>)getVehicleFunctions()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SrsPackage.SRS_MODEL__VEHICLE_FUNCTIONS:
                return getVehicleFunctions();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SrsPackage.SRS_MODEL__VEHICLE_FUNCTIONS:
                getVehicleFunctions().clear();
                getVehicleFunctions().addAll((Collection<? extends VehicleFunction>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eUnset(int featureID) {
        switch (featureID) {
            case SrsPackage.SRS_MODEL__VEHICLE_FUNCTIONS:
                getVehicleFunctions().clear();
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SrsPackage.SRS_MODEL__VEHICLE_FUNCTIONS:
                return vehicleFunctions != null && !vehicleFunctions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //SRSModelImpl
