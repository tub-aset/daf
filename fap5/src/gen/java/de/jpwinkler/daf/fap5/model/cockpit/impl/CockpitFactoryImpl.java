/**
 */
package de.jpwinkler.daf.fap5.model.cockpit.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.jpwinkler.daf.fap5.model.cockpit.CockpitFactory;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitModel;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping;
import de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CockpitFactoryImpl extends EFactoryImpl implements CockpitFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static CockpitFactory init() {
        try {
            CockpitFactory theCockpitFactory = (CockpitFactory)EPackage.Registry.INSTANCE.getEFactory(CockpitPackage.eNS_URI);
            if (theCockpitFactory != null) {
                return theCockpitFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new CockpitFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CockpitFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case CockpitPackage.COCKPIT_MODEL: return createCockpitModel();
            case CockpitPackage.VEHICLE_FUNCTION_PROGRESS: return createVehicleFunctionProgress();
            case CockpitPackage.FUNCTION_CONTRIBUTION_TARGET_MAPPING: return createFunctionContributionTargetMapping();
            case CockpitPackage.FUNCTION_CONTRIBUTION_PROGRESS: return createFunctionContributionProgress();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CockpitModel createCockpitModel() {
        CockpitModelImpl cockpitModel = new CockpitModelImpl();
        return cockpitModel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public VehicleFunctionProgress createVehicleFunctionProgress() {
        VehicleFunctionProgressImpl vehicleFunctionProgress = new VehicleFunctionProgressImpl();
        return vehicleFunctionProgress;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FunctionContributionTargetMapping createFunctionContributionTargetMapping() {
        FunctionContributionTargetMappingImpl functionContributionTargetMapping = new FunctionContributionTargetMappingImpl();
        return functionContributionTargetMapping;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FunctionContributionProgress createFunctionContributionProgress() {
        FunctionContributionProgressImpl functionContributionProgress = new FunctionContributionProgressImpl();
        return functionContributionProgress;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CockpitPackage getCockpitPackage() {
        return (CockpitPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static CockpitPackage getPackage() {
        return CockpitPackage.eINSTANCE;
    }

} //CockpitFactoryImpl
