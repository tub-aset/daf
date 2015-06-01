/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsModelImpl#getFunctionContributionTargets <em>Function Contribution Targets</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentsSystemsModelImpl extends ModelObjectImpl implements ComponentsSystemsModel {
	/**
     * The cached value of the '{@link #getFunctionContributionTargets() <em>Function Contribution Targets</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFunctionContributionTargets()
     * @generated
     * @ordered
     */
	protected EList<FunctionContributionTarget> functionContributionTargets;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ComponentsSystemsModelImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ComponentsSystemsPackage.Literals.COMPONENTS_SYSTEMS_MODEL;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<FunctionContributionTarget> getFunctionContributionTargets() {
        if (functionContributionTargets == null) {
            functionContributionTargets = new EObjectContainmentEList<FunctionContributionTarget>(FunctionContributionTarget.class, this, ComponentsSystemsPackage.COMPONENTS_SYSTEMS_MODEL__FUNCTION_CONTRIBUTION_TARGETS);
        }
        return functionContributionTargets;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ComponentsSystemsPackage.COMPONENTS_SYSTEMS_MODEL__FUNCTION_CONTRIBUTION_TARGETS:
                return ((InternalEList<?>)getFunctionContributionTargets()).basicRemove(otherEnd, msgs);
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
            case ComponentsSystemsPackage.COMPONENTS_SYSTEMS_MODEL__FUNCTION_CONTRIBUTION_TARGETS:
                return getFunctionContributionTargets();
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
            case ComponentsSystemsPackage.COMPONENTS_SYSTEMS_MODEL__FUNCTION_CONTRIBUTION_TARGETS:
                getFunctionContributionTargets().clear();
                getFunctionContributionTargets().addAll((Collection<? extends FunctionContributionTarget>)newValue);
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
            case ComponentsSystemsPackage.COMPONENTS_SYSTEMS_MODEL__FUNCTION_CONTRIBUTION_TARGETS:
                getFunctionContributionTargets().clear();
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
            case ComponentsSystemsPackage.COMPONENTS_SYSTEMS_MODEL__FUNCTION_CONTRIBUTION_TARGETS:
                return functionContributionTargets != null && !functionContributionTargets.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //ComponentsSystemsModelImpl
