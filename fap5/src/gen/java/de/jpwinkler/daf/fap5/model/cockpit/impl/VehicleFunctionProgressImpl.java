/**
 */
package de.jpwinkler.daf.fap5.model.cockpit.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitModel;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress;
import de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress;
import de.jpwinkler.daf.fap5.model.srs.VehicleFunction;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Vehicle Function Progress</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.impl.VehicleFunctionProgressImpl#getVehicleFunction <em>Vehicle Function</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.impl.VehicleFunctionProgressImpl#getCockpitModel <em>Cockpit Model</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.impl.VehicleFunctionProgressImpl#getSubFunctionProgress <em>Sub Function Progress</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.impl.VehicleFunctionProgressImpl#getParentProgress <em>Parent Progress</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.impl.VehicleFunctionProgressImpl#getFunctionContributionProgress <em>Function Contribution Progress</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VehicleFunctionProgressImpl extends ModelObjectImpl implements VehicleFunctionProgress {
	/**
     * The cached value of the '{@link #getVehicleFunction() <em>Vehicle Function</em>}' reference.
     * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
     * @see #getVehicleFunction()
     * @generated
     * @ordered
     */
	protected VehicleFunction vehicleFunction;

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
     * The cached value of the '{@link #getSubFunctionProgress() <em>Sub Function Progress</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSubFunctionProgress()
     * @generated
     * @ordered
     */
	protected EList<VehicleFunctionProgress> subFunctionProgress;

	/**
     * The cached value of the '{@link #getFunctionContributionProgress() <em>Function Contribution Progress</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFunctionContributionProgress()
     * @generated
     * @ordered
     */
	protected EList<FunctionContributionProgress> functionContributionProgress;

	/**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
	protected VehicleFunctionProgressImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return CockpitPackage.Literals.VEHICLE_FUNCTION_PROGRESS;
    }

	/**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
	@Override
	public VehicleFunction getVehicleFunction() {
        if (vehicleFunction != null && vehicleFunction.eIsProxy()) {
            InternalEObject oldVehicleFunction = (InternalEObject)vehicleFunction;
            vehicleFunction = (VehicleFunction)eResolveProxy(oldVehicleFunction);
            if (vehicleFunction != oldVehicleFunction) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CockpitPackage.VEHICLE_FUNCTION_PROGRESS__VEHICLE_FUNCTION, oldVehicleFunction, vehicleFunction));
            }
        }
        return vehicleFunction;
    }

	/**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
	public VehicleFunction basicGetVehicleFunction() {
        return vehicleFunction;
    }

	/**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setVehicleFunction(VehicleFunction newVehicleFunction) {
        VehicleFunction oldVehicleFunction = vehicleFunction;
        vehicleFunction = newVehicleFunction;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CockpitPackage.VEHICLE_FUNCTION_PROGRESS__VEHICLE_FUNCTION, oldVehicleFunction, vehicleFunction));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CockpitPackage.VEHICLE_FUNCTION_PROGRESS__COCKPIT_MODEL, oldCockpitModel, cockpitModel));
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
            eNotify(new ENotificationImpl(this, Notification.SET, CockpitPackage.VEHICLE_FUNCTION_PROGRESS__COCKPIT_MODEL, oldCockpitModel, cockpitModel));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<VehicleFunctionProgress> getSubFunctionProgress() {
        if (subFunctionProgress == null) {
            subFunctionProgress = new EObjectContainmentWithInverseEList<VehicleFunctionProgress>(VehicleFunctionProgress.class, this, CockpitPackage.VEHICLE_FUNCTION_PROGRESS__SUB_FUNCTION_PROGRESS, CockpitPackage.VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS);
        }
        return subFunctionProgress;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public VehicleFunctionProgress getParentProgress() {
        if (eContainerFeatureID() != CockpitPackage.VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS) return null;
        return (VehicleFunctionProgress)eInternalContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetParentProgress(VehicleFunctionProgress newParentProgress, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newParentProgress, CockpitPackage.VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setParentProgress(VehicleFunctionProgress newParentProgress) {
        if (newParentProgress != eInternalContainer() || (eContainerFeatureID() != CockpitPackage.VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS && newParentProgress != null)) {
            if (EcoreUtil.isAncestor(this, newParentProgress))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newParentProgress != null)
                msgs = ((InternalEObject)newParentProgress).eInverseAdd(this, CockpitPackage.VEHICLE_FUNCTION_PROGRESS__SUB_FUNCTION_PROGRESS, VehicleFunctionProgress.class, msgs);
            msgs = basicSetParentProgress(newParentProgress, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CockpitPackage.VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS, newParentProgress, newParentProgress));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<FunctionContributionProgress> getFunctionContributionProgress() {
        if (functionContributionProgress == null) {
            functionContributionProgress = new EObjectContainmentEList<FunctionContributionProgress>(FunctionContributionProgress.class, this, CockpitPackage.VEHICLE_FUNCTION_PROGRESS__FUNCTION_CONTRIBUTION_PROGRESS);
        }
        return functionContributionProgress;
    }

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public boolean isSpecified() {
		return getVehicleFunction().getElements().size() > 0
				|| (getSubFunctionProgress().size() > 0 && getSubFunctionProgress().stream().allMatch((vfp) -> vfp.isSpecified()));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public boolean isMapped() {
		return (getFunctionContributionProgress().size() > 0 && getFunctionContributionProgress().stream().allMatch((fcp) -> fcp.isMapped()))
				|| (getSubFunctionProgress().size() > 0 && getSubFunctionProgress().stream().allMatch((vfp) -> vfp.isMapped()));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public double getProgress() {
		return (double) getSpecifiedFunctionContributionCount() / (double) getTotalFunctionContributionCount();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public double getEstimatedRemainingWork() {
		return getFunctionContributionProgress().stream().mapToDouble(FunctionContributionProgress::getExstimatedRemainingWork).sum() +
				getSubFunctionProgress().stream().mapToDouble(VehicleFunctionProgress::getEstimatedRemainingWork).sum();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public long getSpecifiedFunctionContributionCount() {
		return getFunctionContributionProgress().stream().filter(FunctionContributionProgress::isSpecified).count() +
				getSubFunctionProgress().stream().mapToLong(VehicleFunctionProgress::getSpecifiedFunctionContributionCount).sum();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public long getTotalFunctionContributionCount() {
		return getFunctionContributionProgress().size() +
				getSubFunctionProgress().stream().mapToLong(VehicleFunctionProgress::getTotalFunctionContributionCount).sum();
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
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__SUB_FUNCTION_PROGRESS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubFunctionProgress()).basicAdd(otherEnd, msgs);
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetParentProgress((VehicleFunctionProgress)otherEnd, msgs);
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
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__SUB_FUNCTION_PROGRESS:
                return ((InternalEList<?>)getSubFunctionProgress()).basicRemove(otherEnd, msgs);
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS:
                return basicSetParentProgress(null, msgs);
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__FUNCTION_CONTRIBUTION_PROGRESS:
                return ((InternalEList<?>)getFunctionContributionProgress()).basicRemove(otherEnd, msgs);
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
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS:
                return eInternalContainer().eInverseRemove(this, CockpitPackage.VEHICLE_FUNCTION_PROGRESS__SUB_FUNCTION_PROGRESS, VehicleFunctionProgress.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__VEHICLE_FUNCTION:
                if (resolve) return getVehicleFunction();
                return basicGetVehicleFunction();
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__COCKPIT_MODEL:
                if (resolve) return getCockpitModel();
                return basicGetCockpitModel();
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__SUB_FUNCTION_PROGRESS:
                return getSubFunctionProgress();
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS:
                return getParentProgress();
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__FUNCTION_CONTRIBUTION_PROGRESS:
                return getFunctionContributionProgress();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__VEHICLE_FUNCTION:
                setVehicleFunction((VehicleFunction)newValue);
                return;
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__COCKPIT_MODEL:
                setCockpitModel((CockpitModel)newValue);
                return;
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__SUB_FUNCTION_PROGRESS:
                getSubFunctionProgress().clear();
                getSubFunctionProgress().addAll((Collection<? extends VehicleFunctionProgress>)newValue);
                return;
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS:
                setParentProgress((VehicleFunctionProgress)newValue);
                return;
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__FUNCTION_CONTRIBUTION_PROGRESS:
                getFunctionContributionProgress().clear();
                getFunctionContributionProgress().addAll((Collection<? extends FunctionContributionProgress>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eUnset(int featureID) {
        switch (featureID) {
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__VEHICLE_FUNCTION:
                setVehicleFunction((VehicleFunction)null);
                return;
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__COCKPIT_MODEL:
                setCockpitModel((CockpitModel)null);
                return;
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__SUB_FUNCTION_PROGRESS:
                getSubFunctionProgress().clear();
                return;
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS:
                setParentProgress((VehicleFunctionProgress)null);
                return;
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__FUNCTION_CONTRIBUTION_PROGRESS:
                getFunctionContributionProgress().clear();
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__VEHICLE_FUNCTION:
                return vehicleFunction != null;
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__COCKPIT_MODEL:
                return cockpitModel != null;
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__SUB_FUNCTION_PROGRESS:
                return subFunctionProgress != null && !subFunctionProgress.isEmpty();
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS:
                return getParentProgress() != null;
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS__FUNCTION_CONTRIBUTION_PROGRESS:
                return functionContributionProgress != null && !functionContributionProgress.isEmpty();
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
        switch (operationID) {
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS___IS_SPECIFIED:
                return isSpecified();
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS___IS_MAPPED:
                return isMapped();
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS___GET_PROGRESS:
                return getProgress();
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS___GET_ESTIMATED_REMAINING_WORK:
                return getEstimatedRemainingWork();
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS___GET_SPECIFIED_FUNCTION_CONTRIBUTION_COUNT:
                return getSpecifiedFunctionContributionCount();
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS___GET_TOTAL_FUNCTION_CONTRIBUTION_COUNT:
                return getTotalFunctionContributionCount();
        }
        return super.eInvoke(operationID, arguments);
    }

} // VehicleFunctionProgressImpl
