/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage;
import de.jpwinkler.daf.fap5.model.componentssystems.Functionality;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Functionality</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionalityImpl#getSystem <em>System</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FunctionalityImpl extends FunctionContributionTargetImpl implements Functionality {
	/**
     * The cached value of the '{@link #getSystem() <em>System</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSystem()
     * @generated
     * @ordered
     */
	protected de.jpwinkler.daf.fap5.model.componentssystems.System system;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected FunctionalityImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ComponentsSystemsPackage.Literals.FUNCTIONALITY;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public de.jpwinkler.daf.fap5.model.componentssystems.System getSystem() {
        if (system != null && system.eIsProxy()) {
            InternalEObject oldSystem = (InternalEObject)system;
            system = (de.jpwinkler.daf.fap5.model.componentssystems.System)eResolveProxy(oldSystem);
            if (system != oldSystem) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentsSystemsPackage.FUNCTIONALITY__SYSTEM, oldSystem, system));
            }
        }
        return system;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public de.jpwinkler.daf.fap5.model.componentssystems.System basicGetSystem() {
        return system;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetSystem(de.jpwinkler.daf.fap5.model.componentssystems.System newSystem, NotificationChain msgs) {
        de.jpwinkler.daf.fap5.model.componentssystems.System oldSystem = system;
        system = newSystem;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentsSystemsPackage.FUNCTIONALITY__SYSTEM, oldSystem, newSystem);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSystem(de.jpwinkler.daf.fap5.model.componentssystems.System newSystem) {
        if (newSystem != system) {
            NotificationChain msgs = null;
            if (system != null)
                msgs = ((InternalEObject)system).eInverseRemove(this, ComponentsSystemsPackage.SYSTEM__FUNCTIONALITIES, de.jpwinkler.daf.fap5.model.componentssystems.System.class, msgs);
            if (newSystem != null)
                msgs = ((InternalEObject)newSystem).eInverseAdd(this, ComponentsSystemsPackage.SYSTEM__FUNCTIONALITIES, de.jpwinkler.daf.fap5.model.componentssystems.System.class, msgs);
            msgs = basicSetSystem(newSystem, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentsSystemsPackage.FUNCTIONALITY__SYSTEM, newSystem, newSystem));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ComponentsSystemsPackage.FUNCTIONALITY__SYSTEM:
                if (system != null)
                    msgs = ((InternalEObject)system).eInverseRemove(this, ComponentsSystemsPackage.SYSTEM__FUNCTIONALITIES, de.jpwinkler.daf.fap5.model.componentssystems.System.class, msgs);
                return basicSetSystem((de.jpwinkler.daf.fap5.model.componentssystems.System)otherEnd, msgs);
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
            case ComponentsSystemsPackage.FUNCTIONALITY__SYSTEM:
                return basicSetSystem(null, msgs);
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
            case ComponentsSystemsPackage.FUNCTIONALITY__SYSTEM:
                if (resolve) return getSystem();
                return basicGetSystem();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ComponentsSystemsPackage.FUNCTIONALITY__SYSTEM:
                setSystem((de.jpwinkler.daf.fap5.model.componentssystems.System)newValue);
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
            case ComponentsSystemsPackage.FUNCTIONALITY__SYSTEM:
                setSystem((de.jpwinkler.daf.fap5.model.componentssystems.System)null);
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
            case ComponentsSystemsPackage.FUNCTIONALITY__SYSTEM:
                return system != null;
        }
        return super.eIsSet(featureID);
    }

} //FunctionalityImpl
