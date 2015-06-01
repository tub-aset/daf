/**
 */
package de.jpwinkler.daf.fap5.model.cockpit.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitModel;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping;
import de.jpwinkler.daf.fap5.model.srs.FunctionContribution;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Contribution Progress</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.impl.FunctionContributionProgressImpl#getFunctionContribution <em>Function Contribution</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.impl.FunctionContributionProgressImpl#getCockpitModel <em>Cockpit Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionContributionProgressImpl extends ModelObjectImpl implements FunctionContributionProgress {
	/**
     * The cached value of the '{@link #getFunctionContribution() <em>Function Contribution</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFunctionContribution()
     * @generated
     * @ordered
     */
	protected FunctionContribution functionContribution;

	/**
     * The cached value of the '{@link #getCockpitModel() <em>Cockpit Model</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCockpitModel()
     * @generated
     * @ordered
     */
	protected CockpitModel cockpitModel;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected FunctionContributionProgressImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return CockpitPackage.Literals.FUNCTION_CONTRIBUTION_PROGRESS;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public FunctionContribution getFunctionContribution() {
        if (functionContribution != null && functionContribution.eIsProxy()) {
            InternalEObject oldFunctionContribution = (InternalEObject)functionContribution;
            functionContribution = (FunctionContribution)eResolveProxy(oldFunctionContribution);
            if (functionContribution != oldFunctionContribution) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS__FUNCTION_CONTRIBUTION, oldFunctionContribution, functionContribution));
            }
        }
        return functionContribution;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FunctionContribution basicGetFunctionContribution() {
        return functionContribution;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setFunctionContribution(FunctionContribution newFunctionContribution) {
        FunctionContribution oldFunctionContribution = functionContribution;
        functionContribution = newFunctionContribution;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS__FUNCTION_CONTRIBUTION, oldFunctionContribution, functionContribution));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public CockpitModel getCockpitModel() {
        if (cockpitModel != null && cockpitModel.eIsProxy()) {
            InternalEObject oldCockpitModel = (InternalEObject)cockpitModel;
            cockpitModel = (CockpitModel)eResolveProxy(oldCockpitModel);
            if (cockpitModel != oldCockpitModel) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS__COCKPIT_MODEL, oldCockpitModel, cockpitModel));
            }
        }
        return cockpitModel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CockpitModel basicGetCockpitModel() {
        return cockpitModel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setCockpitModel(CockpitModel newCockpitModel) {
        CockpitModel oldCockpitModel = cockpitModel;
        cockpitModel = newCockpitModel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS__COCKPIT_MODEL, oldCockpitModel, cockpitModel));
    }

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public boolean isMapped() {
		return getFunctionContribution().getTarget() != null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public boolean isSpecified() {
		if (!isMapped()) {
			return false;
		}
		final FunctionContributionTargetMapping mapping = getCockpitModel().findMapping(getFunctionContribution().getTarget());

		return mapping != null && mapping.getDocuments().size() > 0 && mapping.getDocuments().stream().allMatch((cbd) -> cbd.isSpecified());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public double getExstimatedRemainingWork() {
		final FunctionContributionTargetMapping mapping = getCockpitModel().findMapping(getFunctionContribution().getTarget());

		if (mapping != null) {
			return (float) mapping.getDocuments().stream().mapToDouble((cbd) -> cbd.getEstimatedRemainingWork()).sum();
		} else {
			return 0;
		}
	}

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS__FUNCTION_CONTRIBUTION:
                if (resolve) return getFunctionContribution();
                return basicGetFunctionContribution();
            case CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS__COCKPIT_MODEL:
                if (resolve) return getCockpitModel();
                return basicGetCockpitModel();
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
            case CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS__FUNCTION_CONTRIBUTION:
                setFunctionContribution((FunctionContribution)newValue);
                return;
            case CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS__COCKPIT_MODEL:
                setCockpitModel((CockpitModel)newValue);
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
            case CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS__FUNCTION_CONTRIBUTION:
                setFunctionContribution((FunctionContribution)null);
                return;
            case CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS__COCKPIT_MODEL:
                setCockpitModel((CockpitModel)null);
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
            case CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS__FUNCTION_CONTRIBUTION:
                return functionContribution != null;
            case CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS__COCKPIT_MODEL:
                return cockpitModel != null;
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
        switch (operationID) {
            case CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS___IS_MAPPED:
                return isMapped();
            case CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS___IS_SPECIFIED:
                return isSpecified();
            case CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS___GET_EXSTIMATED_REMAINING_WORK:
                return getExstimatedRemainingWork();
        }
        return super.eInvoke(operationID, arguments);
    }

} //FunctionContributionProgressImpl
