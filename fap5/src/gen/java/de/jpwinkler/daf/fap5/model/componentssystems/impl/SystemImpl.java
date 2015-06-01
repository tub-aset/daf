/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage;
import de.jpwinkler.daf.fap5.model.componentssystems.Functionality;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.SystemImpl#getFunctionalities <em>Functionalities</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SystemImpl extends FunctionContributionTargetImpl implements de.jpwinkler.daf.fap5.model.componentssystems.System {
	/**
     * The cached value of the '{@link #getFunctionalities() <em>Functionalities</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFunctionalities()
     * @generated
     * @ordered
     */
	protected EList<Functionality> functionalities;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SystemImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ComponentsSystemsPackage.Literals.SYSTEM;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Functionality> getFunctionalities() {
        if (functionalities == null) {
            functionalities = new EObjectWithInverseResolvingEList<Functionality>(Functionality.class, this, ComponentsSystemsPackage.SYSTEM__FUNCTIONALITIES, ComponentsSystemsPackage.FUNCTIONALITY__SYSTEM);
        }
        return functionalities;
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
            case ComponentsSystemsPackage.SYSTEM__FUNCTIONALITIES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getFunctionalities()).basicAdd(otherEnd, msgs);
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
            case ComponentsSystemsPackage.SYSTEM__FUNCTIONALITIES:
                return ((InternalEList<?>)getFunctionalities()).basicRemove(otherEnd, msgs);
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
            case ComponentsSystemsPackage.SYSTEM__FUNCTIONALITIES:
                return getFunctionalities();
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
            case ComponentsSystemsPackage.SYSTEM__FUNCTIONALITIES:
                getFunctionalities().clear();
                getFunctionalities().addAll((Collection<? extends Functionality>)newValue);
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
            case ComponentsSystemsPackage.SYSTEM__FUNCTIONALITIES:
                getFunctionalities().clear();
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
            case ComponentsSystemsPackage.SYSTEM__FUNCTIONALITIES:
                return functionalities != null && !functionalities.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //SystemImpl
