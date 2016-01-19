/**
 */
package de.jpwinkler.daf.fap5.model.cockpit.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Contribution Target Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.impl.FunctionContributionTargetMappingImpl#getFunctionContributionTarget <em>Function Contribution Target</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.impl.FunctionContributionTargetMappingImpl#getDocuments <em>Documents</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FunctionContributionTargetMappingImpl extends ModelObjectImpl implements FunctionContributionTargetMapping {
	/**
     * The cached value of the '{@link #getFunctionContributionTarget() <em>Function Contribution Target</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFunctionContributionTarget()
     * @generated
     * @ordered
     */
	protected FunctionContributionTarget functionContributionTarget;

	/**
     * The cached value of the '{@link #getDocuments() <em>Documents</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDocuments()
     * @generated
     * @ordered
     */
	protected EList<CodeBeamerModel> documents;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected FunctionContributionTargetMappingImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return CockpitPackage.Literals.FUNCTION_CONTRIBUTION_TARGET_MAPPING;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FunctionContributionTarget getFunctionContributionTarget() {
        if (functionContributionTarget != null && functionContributionTarget.eIsProxy()) {
            InternalEObject oldFunctionContributionTarget = (InternalEObject)functionContributionTarget;
            functionContributionTarget = (FunctionContributionTarget)eResolveProxy(oldFunctionContributionTarget);
            if (functionContributionTarget != oldFunctionContributionTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CockpitPackage.FUNCTION_CONTRIBUTION_TARGET_MAPPING__FUNCTION_CONTRIBUTION_TARGET, oldFunctionContributionTarget, functionContributionTarget));
            }
        }
        return functionContributionTarget;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FunctionContributionTarget basicGetFunctionContributionTarget() {
        return functionContributionTarget;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setFunctionContributionTarget(FunctionContributionTarget newFunctionContributionTarget) {
        FunctionContributionTarget oldFunctionContributionTarget = functionContributionTarget;
        functionContributionTarget = newFunctionContributionTarget;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CockpitPackage.FUNCTION_CONTRIBUTION_TARGET_MAPPING__FUNCTION_CONTRIBUTION_TARGET, oldFunctionContributionTarget, functionContributionTarget));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<CodeBeamerModel> getDocuments() {
        if (documents == null) {
            documents = new EObjectResolvingEList<CodeBeamerModel>(CodeBeamerModel.class, this, CockpitPackage.FUNCTION_CONTRIBUTION_TARGET_MAPPING__DOCUMENTS);
        }
        return documents;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case CockpitPackage.FUNCTION_CONTRIBUTION_TARGET_MAPPING__FUNCTION_CONTRIBUTION_TARGET:
                if (resolve) return getFunctionContributionTarget();
                return basicGetFunctionContributionTarget();
            case CockpitPackage.FUNCTION_CONTRIBUTION_TARGET_MAPPING__DOCUMENTS:
                return getDocuments();
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
            case CockpitPackage.FUNCTION_CONTRIBUTION_TARGET_MAPPING__FUNCTION_CONTRIBUTION_TARGET:
                setFunctionContributionTarget((FunctionContributionTarget)newValue);
                return;
            case CockpitPackage.FUNCTION_CONTRIBUTION_TARGET_MAPPING__DOCUMENTS:
                getDocuments().clear();
                getDocuments().addAll((Collection<? extends CodeBeamerModel>)newValue);
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
            case CockpitPackage.FUNCTION_CONTRIBUTION_TARGET_MAPPING__FUNCTION_CONTRIBUTION_TARGET:
                setFunctionContributionTarget((FunctionContributionTarget)null);
                return;
            case CockpitPackage.FUNCTION_CONTRIBUTION_TARGET_MAPPING__DOCUMENTS:
                getDocuments().clear();
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
            case CockpitPackage.FUNCTION_CONTRIBUTION_TARGET_MAPPING__FUNCTION_CONTRIBUTION_TARGET:
                return functionContributionTarget != null;
            case CockpitPackage.FUNCTION_CONTRIBUTION_TARGET_MAPPING__DOCUMENTS:
                return documents != null && !documents.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //FunctionContributionTargetMappingImpl
