/**
 */
package de.jpwinkler.daf.fap5.model.srs.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.jpwinkler.daf.fap5.model.srs.Description;
import de.jpwinkler.daf.fap5.model.srs.EndCondition;
import de.jpwinkler.daf.fap5.model.srs.FunctionContribution;
import de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer;
import de.jpwinkler.daf.fap5.model.srs.Heading;
import de.jpwinkler.daf.fap5.model.srs.Precondition;
import de.jpwinkler.daf.fap5.model.srs.SRSModel;
import de.jpwinkler.daf.fap5.model.srs.SrsFactory;
import de.jpwinkler.daf.fap5.model.srs.SrsPackage;
import de.jpwinkler.daf.fap5.model.srs.Trigger;
import de.jpwinkler.daf.fap5.model.srs.VehicleFunction;
import de.jpwinkler.daf.fap5.model.srs.VehicleFunctionType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SrsFactoryImpl extends EFactoryImpl implements SrsFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static SrsFactory init() {
        try {
            SrsFactory theSrsFactory = (SrsFactory)EPackage.Registry.INSTANCE.getEFactory(SrsPackage.eNS_URI);
            if (theSrsFactory != null) {
                return theSrsFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new SrsFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SrsFactoryImpl() {
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
            case SrsPackage.SRS_MODEL: return createSRSModel();
            case SrsPackage.VEHICLE_FUNCTION: return createVehicleFunction();
            case SrsPackage.FUNCTION_CONTRIBUTION: return createFunctionContribution();
            case SrsPackage.FUNCTIONAL_SPECIFICATION_CONTAINER: return createFunctionalSpecificationContainer();
            case SrsPackage.PRECONDITION: return createPrecondition();
            case SrsPackage.END_CONDITION: return createEndCondition();
            case SrsPackage.TRIGGER: return createTrigger();
            case SrsPackage.HEADING: return createHeading();
            case SrsPackage.DESCRIPTION: return createDescription();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case SrsPackage.VEHICLE_FUNCTION_TYPE:
                return createVehicleFunctionTypeFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case SrsPackage.VEHICLE_FUNCTION_TYPE:
                return convertVehicleFunctionTypeToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SRSModel createSRSModel() {
        SRSModelImpl srsModel = new SRSModelImpl();
        return srsModel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public VehicleFunction createVehicleFunction() {
        VehicleFunctionImpl vehicleFunction = new VehicleFunctionImpl();
        return vehicleFunction;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FunctionContribution createFunctionContribution() {
        FunctionContributionImpl functionContribution = new FunctionContributionImpl();
        return functionContribution;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FunctionalSpecificationContainer createFunctionalSpecificationContainer() {
        FunctionalSpecificationContainerImpl functionalSpecificationContainer = new FunctionalSpecificationContainerImpl();
        return functionalSpecificationContainer;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Precondition createPrecondition() {
        PreconditionImpl precondition = new PreconditionImpl();
        return precondition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EndCondition createEndCondition() {
        EndConditionImpl endCondition = new EndConditionImpl();
        return endCondition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Trigger createTrigger() {
        TriggerImpl trigger = new TriggerImpl();
        return trigger;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Heading createHeading() {
        HeadingImpl heading = new HeadingImpl();
        return heading;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Description createDescription() {
        DescriptionImpl description = new DescriptionImpl();
        return description;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public VehicleFunctionType createVehicleFunctionTypeFromString(EDataType eDataType, String initialValue) {
        VehicleFunctionType result = VehicleFunctionType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertVehicleFunctionTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SrsPackage getSrsPackage() {
        return (SrsPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static SrsPackage getPackage() {
        return SrsPackage.eINSTANCE;
    }

} //SrsFactoryImpl
