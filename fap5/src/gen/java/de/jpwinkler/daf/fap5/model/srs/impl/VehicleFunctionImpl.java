/**
 */
package de.jpwinkler.daf.fap5.model.srs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.fap5.model.srs.SrsPackage;
import de.jpwinkler.daf.fap5.model.srs.VehicleFunction;
import de.jpwinkler.daf.fap5.model.srs.VehicleFunctionType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vehicle Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.impl.VehicleFunctionImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.impl.VehicleFunctionImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.impl.VehicleFunctionImpl#getSubFunctions <em>Sub Functions</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.impl.VehicleFunctionImpl#getParent <em>Parent</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VehicleFunctionImpl extends FunctionalSpecificationContainerImpl implements VehicleFunction {
	/**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected static final String NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected String name = NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
	protected static final VehicleFunctionType TYPE_EDEFAULT = VehicleFunctionType.CUSTOMER_FUNCTION;

	/**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
	protected VehicleFunctionType type = TYPE_EDEFAULT;

	/**
     * The cached value of the '{@link #getSubFunctions() <em>Sub Functions</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSubFunctions()
     * @generated
     * @ordered
     */
	protected EList<VehicleFunction> subFunctions;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected VehicleFunctionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return SrsPackage.Literals.VEHICLE_FUNCTION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getName() {
        return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SrsPackage.VEHICLE_FUNCTION__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public VehicleFunctionType getType() {
        return type;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setType(VehicleFunctionType newType) {
        VehicleFunctionType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SrsPackage.VEHICLE_FUNCTION__TYPE, oldType, type));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<VehicleFunction> getSubFunctions() {
        if (subFunctions == null) {
            subFunctions = new EObjectContainmentWithInverseEList<VehicleFunction>(VehicleFunction.class, this, SrsPackage.VEHICLE_FUNCTION__SUB_FUNCTIONS, SrsPackage.VEHICLE_FUNCTION__PARENT);
        }
        return subFunctions;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public VehicleFunction getParent() {
        if (eContainerFeatureID() != SrsPackage.VEHICLE_FUNCTION__PARENT) return null;
        return (VehicleFunction)eInternalContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetParent(VehicleFunction newParent, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newParent, SrsPackage.VEHICLE_FUNCTION__PARENT, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setParent(VehicleFunction newParent) {
        if (newParent != eInternalContainer() || (eContainerFeatureID() != SrsPackage.VEHICLE_FUNCTION__PARENT && newParent != null)) {
            if (EcoreUtil.isAncestor(this, newParent))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newParent != null)
                msgs = ((InternalEObject)newParent).eInverseAdd(this, SrsPackage.VEHICLE_FUNCTION__SUB_FUNCTIONS, VehicleFunction.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SrsPackage.VEHICLE_FUNCTION__PARENT, newParent, newParent));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SrsPackage.VEHICLE_FUNCTION__SUB_FUNCTIONS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubFunctions()).basicAdd(otherEnd, msgs);
            case SrsPackage.VEHICLE_FUNCTION__PARENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetParent((VehicleFunction)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SrsPackage.VEHICLE_FUNCTION__SUB_FUNCTIONS:
                return ((InternalEList<?>)getSubFunctions()).basicRemove(otherEnd, msgs);
            case SrsPackage.VEHICLE_FUNCTION__PARENT:
                return basicSetParent(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case SrsPackage.VEHICLE_FUNCTION__PARENT:
                return eInternalContainer().eInverseRemove(this, SrsPackage.VEHICLE_FUNCTION__SUB_FUNCTIONS, VehicleFunction.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SrsPackage.VEHICLE_FUNCTION__NAME:
                return getName();
            case SrsPackage.VEHICLE_FUNCTION__TYPE:
                return getType();
            case SrsPackage.VEHICLE_FUNCTION__SUB_FUNCTIONS:
                return getSubFunctions();
            case SrsPackage.VEHICLE_FUNCTION__PARENT:
                return getParent();
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
            case SrsPackage.VEHICLE_FUNCTION__NAME:
                setName((String)newValue);
                return;
            case SrsPackage.VEHICLE_FUNCTION__TYPE:
                setType((VehicleFunctionType)newValue);
                return;
            case SrsPackage.VEHICLE_FUNCTION__SUB_FUNCTIONS:
                getSubFunctions().clear();
                getSubFunctions().addAll((Collection<? extends VehicleFunction>)newValue);
                return;
            case SrsPackage.VEHICLE_FUNCTION__PARENT:
                setParent((VehicleFunction)newValue);
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
            case SrsPackage.VEHICLE_FUNCTION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case SrsPackage.VEHICLE_FUNCTION__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case SrsPackage.VEHICLE_FUNCTION__SUB_FUNCTIONS:
                getSubFunctions().clear();
                return;
            case SrsPackage.VEHICLE_FUNCTION__PARENT:
                setParent((VehicleFunction)null);
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
            case SrsPackage.VEHICLE_FUNCTION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case SrsPackage.VEHICLE_FUNCTION__TYPE:
                return type != TYPE_EDEFAULT;
            case SrsPackage.VEHICLE_FUNCTION__SUB_FUNCTIONS:
                return subFunctions != null && !subFunctions.isEmpty();
            case SrsPackage.VEHICLE_FUNCTION__PARENT:
                return getParent() != null;
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(", type: ");
        result.append(type);
        result.append(')');
        return result.toString();
    }

} //VehicleFunctionImpl
