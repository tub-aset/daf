/**
 */
package de.jpwinkler.daf.fap5.model.cockpit.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitModel;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping;
import de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage
 * @generated
 */
public class CockpitSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static CockpitPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CockpitSwitch() {
        if (modelPackage == null) {
            modelPackage = CockpitPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case CockpitPackage.COCKPIT_MODEL: {
                CockpitModel cockpitModel = (CockpitModel)theEObject;
                T result = caseCockpitModel(cockpitModel);
                if (result == null) result = caseModelObject(cockpitModel);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS: {
                VehicleFunctionProgress vehicleFunctionProgress = (VehicleFunctionProgress)theEObject;
                T result = caseVehicleFunctionProgress(vehicleFunctionProgress);
                if (result == null) result = caseModelObject(vehicleFunctionProgress);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CockpitPackage.FUNCTION_CONTRIBUTION_TARGET_MAPPING: {
                FunctionContributionTargetMapping functionContributionTargetMapping = (FunctionContributionTargetMapping)theEObject;
                T result = caseFunctionContributionTargetMapping(functionContributionTargetMapping);
                if (result == null) result = caseModelObject(functionContributionTargetMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS: {
                FunctionContributionProgress functionContributionProgress = (FunctionContributionProgress)theEObject;
                T result = caseFunctionContributionProgress(functionContributionProgress);
                if (result == null) result = caseModelObject(functionContributionProgress);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Model</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCockpitModel(CockpitModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Vehicle Function Progress</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Vehicle Function Progress</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVehicleFunctionProgress(VehicleFunctionProgress object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Function Contribution Target Mapping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Function Contribution Target Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFunctionContributionTargetMapping(FunctionContributionTargetMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Function Contribution Progress</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Function Contribution Progress</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFunctionContributionProgress(FunctionContributionProgress object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Model Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Model Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModelObject(ModelObject object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} //CockpitSwitch
