/**
 */
package de.jpwinkler.daf.fap5.model.cockpit.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl;
import de.jpwinkler.daf.dafcore.util.ECollectors;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitModel;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping;
import de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitModelImpl#getMappings <em>Mappings</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitModelImpl#getVehicleFunctionProgress <em>Vehicle Function Progress</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitModelImpl#getDocuments <em>Documents</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CockpitModelImpl extends ModelObjectImpl implements CockpitModel {
	/**
     * The cached value of the '{@link #getMappings() <em>Mappings</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMappings()
     * @generated
     * @ordered
     */
	protected EList<FunctionContributionTargetMapping> mappings;

	/**
     * The cached value of the '{@link #getVehicleFunctionProgress() <em>Vehicle Function Progress</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVehicleFunctionProgress()
     * @generated
     * @ordered
     */
	protected EList<VehicleFunctionProgress> vehicleFunctionProgress;

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
	protected CockpitModelImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return CockpitPackage.Literals.COCKPIT_MODEL;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<VehicleFunctionProgress> getVehicleFunctionProgress() {
        if (vehicleFunctionProgress == null) {
            vehicleFunctionProgress = new EObjectContainmentEList<VehicleFunctionProgress>(VehicleFunctionProgress.class, this, CockpitPackage.COCKPIT_MODEL__VEHICLE_FUNCTION_PROGRESS);
        }
        return vehicleFunctionProgress;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<CodeBeamerModel> getDocuments() {
        if (documents == null) {
            documents = new EObjectResolvingEList<CodeBeamerModel>(CodeBeamerModel.class, this, CockpitPackage.COCKPIT_MODEL__DOCUMENTS);
        }
        return documents;
    }

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public FunctionContributionTargetMapping findMapping(final FunctionContributionTarget fct) {
		for (final FunctionContributionTargetMapping mapping : getMappings()) {
			if (mapping.getFunctionContributionTarget().equals(fct)) {
				return mapping;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EList<FunctionContributionTarget> findFunctionContributionTargets(final CodeBeamerModel cbm) {
		return getMappings().stream().filter((m) -> m.getDocuments().contains(cbm)).map((m) -> m.getFunctionContributionTarget()).distinct().collect(ECollectors.toEList());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EList<String> getIssueTypes() {
		return getDocuments().stream().map(cbm -> cbm.getIssueTypes()).flatMap(List::stream).distinct().collect(ECollectors.toEList());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EList<Issue> getIssues(final String issueType) {
		return getDocuments().stream().map(cbm -> cbm.getIssues(issueType)).flatMap(List::stream).collect(ECollectors.toEList());
	}

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<FunctionContributionTargetMapping> getMappings() {
        if (mappings == null) {
            mappings = new EObjectContainmentEList<FunctionContributionTargetMapping>(FunctionContributionTargetMapping.class, this, CockpitPackage.COCKPIT_MODEL__MAPPINGS);
        }
        return mappings;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CockpitPackage.COCKPIT_MODEL__MAPPINGS:
                return ((InternalEList<?>)getMappings()).basicRemove(otherEnd, msgs);
            case CockpitPackage.COCKPIT_MODEL__VEHICLE_FUNCTION_PROGRESS:
                return ((InternalEList<?>)getVehicleFunctionProgress()).basicRemove(otherEnd, msgs);
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
            case CockpitPackage.COCKPIT_MODEL__MAPPINGS:
                return getMappings();
            case CockpitPackage.COCKPIT_MODEL__VEHICLE_FUNCTION_PROGRESS:
                return getVehicleFunctionProgress();
            case CockpitPackage.COCKPIT_MODEL__DOCUMENTS:
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
            case CockpitPackage.COCKPIT_MODEL__MAPPINGS:
                getMappings().clear();
                getMappings().addAll((Collection<? extends FunctionContributionTargetMapping>)newValue);
                return;
            case CockpitPackage.COCKPIT_MODEL__VEHICLE_FUNCTION_PROGRESS:
                getVehicleFunctionProgress().clear();
                getVehicleFunctionProgress().addAll((Collection<? extends VehicleFunctionProgress>)newValue);
                return;
            case CockpitPackage.COCKPIT_MODEL__DOCUMENTS:
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
            case CockpitPackage.COCKPIT_MODEL__MAPPINGS:
                getMappings().clear();
                return;
            case CockpitPackage.COCKPIT_MODEL__VEHICLE_FUNCTION_PROGRESS:
                getVehicleFunctionProgress().clear();
                return;
            case CockpitPackage.COCKPIT_MODEL__DOCUMENTS:
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
            case CockpitPackage.COCKPIT_MODEL__MAPPINGS:
                return mappings != null && !mappings.isEmpty();
            case CockpitPackage.COCKPIT_MODEL__VEHICLE_FUNCTION_PROGRESS:
                return vehicleFunctionProgress != null && !vehicleFunctionProgress.isEmpty();
            case CockpitPackage.COCKPIT_MODEL__DOCUMENTS:
                return documents != null && !documents.isEmpty();
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
            case CockpitPackage.COCKPIT_MODEL___FIND_MAPPING__FUNCTIONCONTRIBUTIONTARGET:
                return findMapping((FunctionContributionTarget)arguments.get(0));
            case CockpitPackage.COCKPIT_MODEL___FIND_FUNCTION_CONTRIBUTION_TARGETS__CODEBEAMERMODEL:
                return findFunctionContributionTargets((CodeBeamerModel)arguments.get(0));
            case CockpitPackage.COCKPIT_MODEL___GET_ISSUE_TYPES:
                return getIssueTypes();
            case CockpitPackage.COCKPIT_MODEL___GET_ISSUES__STRING:
                return getIssues((String)arguments.get(0));
        }
        return super.eInvoke(operationID, arguments);
    }

} //CockpitModelImpl
