/**
 */
package de.jpwinkler.daf.fap5.model.srs.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import de.jpwinkler.daf.dafcore.model.common.CommonPackage;
import de.jpwinkler.daf.dafcore.model.csv.CSVPackage;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage;
import de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitPackageImpl;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage;
import de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage;
import de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl;
import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryPackage;
import de.jpwinkler.daf.fap5.model.issuehistory.impl.IssueHistoryPackageImpl;
import de.jpwinkler.daf.fap5.model.srs.Description;
import de.jpwinkler.daf.fap5.model.srs.EndCondition;
import de.jpwinkler.daf.fap5.model.srs.FunctionContribution;
import de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer;
import de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationElement;
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
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SrsPackageImpl extends EPackageImpl implements SrsPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass srsModelEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass vehicleFunctionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass functionContributionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass functionalSpecificationContainerEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass functionalSpecificationElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass preconditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass endConditionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass triggerEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass headingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass descriptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum vehicleFunctionTypeEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private SrsPackageImpl() {
        super(eNS_URI, SrsFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link SrsPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static SrsPackage init() {
        if (isInited) return (SrsPackage)EPackage.Registry.INSTANCE.getEPackage(SrsPackage.eNS_URI);

        // Obtain or create and register package
        SrsPackageImpl theSrsPackage = (SrsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SrsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SrsPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        CSVPackage.eINSTANCE.eClass();
        CommonPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        CockpitPackageImpl theCockpitPackage = (CockpitPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CockpitPackage.eNS_URI) instanceof CockpitPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CockpitPackage.eNS_URI) : CockpitPackage.eINSTANCE);
        CodebeamerPackageImpl theCodebeamerPackage = (CodebeamerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CodebeamerPackage.eNS_URI) instanceof CodebeamerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CodebeamerPackage.eNS_URI) : CodebeamerPackage.eINSTANCE);
        ComponentsSystemsPackageImpl theComponentsSystemsPackage = (ComponentsSystemsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentsSystemsPackage.eNS_URI) instanceof ComponentsSystemsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentsSystemsPackage.eNS_URI) : ComponentsSystemsPackage.eINSTANCE);
        IssueHistoryPackageImpl theIssueHistoryPackage = (IssueHistoryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(IssueHistoryPackage.eNS_URI) instanceof IssueHistoryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(IssueHistoryPackage.eNS_URI) : IssueHistoryPackage.eINSTANCE);

        // Create package meta-data objects
        theSrsPackage.createPackageContents();
        theCockpitPackage.createPackageContents();
        theCodebeamerPackage.createPackageContents();
        theComponentsSystemsPackage.createPackageContents();
        theIssueHistoryPackage.createPackageContents();

        // Initialize created meta-data
        theSrsPackage.initializePackageContents();
        theCockpitPackage.initializePackageContents();
        theCodebeamerPackage.initializePackageContents();
        theComponentsSystemsPackage.initializePackageContents();
        theIssueHistoryPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theSrsPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(SrsPackage.eNS_URI, theSrsPackage);
        return theSrsPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getSRSModel() {
        return srsModelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSRSModel_VehicleFunctions() {
        return (EReference)srsModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getVehicleFunction() {
        return vehicleFunctionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getVehicleFunction_Name() {
        return (EAttribute)vehicleFunctionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getVehicleFunction_Type() {
        return (EAttribute)vehicleFunctionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getVehicleFunction_SubFunctions() {
        return (EReference)vehicleFunctionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getVehicleFunction_Parent() {
        return (EReference)vehicleFunctionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFunctionContribution() {
        return functionContributionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFunctionContribution_Target() {
        return (EReference)functionContributionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFunctionContribution_Text() {
        return (EAttribute)functionContributionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFunctionalSpecificationContainer() {
        return functionalSpecificationContainerEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFunctionalSpecificationContainer_Elements() {
        return (EReference)functionalSpecificationContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getFunctionalSpecificationContainer__GetFunctionContributions() {
        return functionalSpecificationContainerEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFunctionalSpecificationElement() {
        return functionalSpecificationElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getPrecondition() {
        return preconditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getPrecondition_Name() {
        return (EAttribute)preconditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getEndCondition() {
        return endConditionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getEndCondition_Name() {
        return (EAttribute)endConditionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTrigger() {
        return triggerEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTrigger_Name() {
        return (EAttribute)triggerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getHeading() {
        return headingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getHeading_Name() {
        return (EAttribute)headingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getDescription() {
        return descriptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDescription_Text() {
        return (EAttribute)descriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getVehicleFunctionType() {
        return vehicleFunctionTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SrsFactory getSrsFactory() {
        return (SrsFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        srsModelEClass = createEClass(SRS_MODEL);
        createEReference(srsModelEClass, SRS_MODEL__VEHICLE_FUNCTIONS);

        vehicleFunctionEClass = createEClass(VEHICLE_FUNCTION);
        createEAttribute(vehicleFunctionEClass, VEHICLE_FUNCTION__NAME);
        createEAttribute(vehicleFunctionEClass, VEHICLE_FUNCTION__TYPE);
        createEReference(vehicleFunctionEClass, VEHICLE_FUNCTION__SUB_FUNCTIONS);
        createEReference(vehicleFunctionEClass, VEHICLE_FUNCTION__PARENT);

        functionContributionEClass = createEClass(FUNCTION_CONTRIBUTION);
        createEReference(functionContributionEClass, FUNCTION_CONTRIBUTION__TARGET);
        createEAttribute(functionContributionEClass, FUNCTION_CONTRIBUTION__TEXT);

        functionalSpecificationContainerEClass = createEClass(FUNCTIONAL_SPECIFICATION_CONTAINER);
        createEReference(functionalSpecificationContainerEClass, FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS);
        createEOperation(functionalSpecificationContainerEClass, FUNCTIONAL_SPECIFICATION_CONTAINER___GET_FUNCTION_CONTRIBUTIONS);

        functionalSpecificationElementEClass = createEClass(FUNCTIONAL_SPECIFICATION_ELEMENT);

        preconditionEClass = createEClass(PRECONDITION);
        createEAttribute(preconditionEClass, PRECONDITION__NAME);

        endConditionEClass = createEClass(END_CONDITION);
        createEAttribute(endConditionEClass, END_CONDITION__NAME);

        triggerEClass = createEClass(TRIGGER);
        createEAttribute(triggerEClass, TRIGGER__NAME);

        headingEClass = createEClass(HEADING);
        createEAttribute(headingEClass, HEADING__NAME);

        descriptionEClass = createEClass(DESCRIPTION);
        createEAttribute(descriptionEClass, DESCRIPTION__TEXT);

        // Create enums
        vehicleFunctionTypeEEnum = createEEnum(VEHICLE_FUNCTION_TYPE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        CommonPackage theCommonPackage = (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);
        ComponentsSystemsPackage theComponentsSystemsPackage = (ComponentsSystemsPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentsSystemsPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        srsModelEClass.getESuperTypes().add(theCommonPackage.getModelObject());
        vehicleFunctionEClass.getESuperTypes().add(this.getFunctionalSpecificationContainer());
        functionContributionEClass.getESuperTypes().add(this.getFunctionalSpecificationElement());
        functionalSpecificationContainerEClass.getESuperTypes().add(this.getFunctionalSpecificationElement());
        functionalSpecificationElementEClass.getESuperTypes().add(theCommonPackage.getModelObject());
        preconditionEClass.getESuperTypes().add(this.getFunctionalSpecificationContainer());
        endConditionEClass.getESuperTypes().add(this.getFunctionalSpecificationContainer());
        triggerEClass.getESuperTypes().add(this.getFunctionalSpecificationContainer());
        headingEClass.getESuperTypes().add(this.getFunctionalSpecificationContainer());
        descriptionEClass.getESuperTypes().add(this.getFunctionalSpecificationElement());

        // Initialize classes, features, and operations; add parameters
        initEClass(srsModelEClass, SRSModel.class, "SRSModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSRSModel_VehicleFunctions(), this.getVehicleFunction(), null, "vehicleFunctions", null, 0, -1, SRSModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(vehicleFunctionEClass, VehicleFunction.class, "VehicleFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getVehicleFunction_Name(), ecorePackage.getEString(), "name", null, 0, 1, VehicleFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVehicleFunction_Type(), this.getVehicleFunctionType(), "type", null, 0, 1, VehicleFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVehicleFunction_SubFunctions(), this.getVehicleFunction(), this.getVehicleFunction_Parent(), "subFunctions", null, 0, -1, VehicleFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVehicleFunction_Parent(), this.getVehicleFunction(), this.getVehicleFunction_SubFunctions(), "parent", null, 0, 1, VehicleFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(functionContributionEClass, FunctionContribution.class, "FunctionContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFunctionContribution_Target(), theComponentsSystemsPackage.getFunctionContributionTarget(), theComponentsSystemsPackage.getFunctionContributionTarget_Contributions(), "target", null, 0, 1, FunctionContribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFunctionContribution_Text(), ecorePackage.getEString(), "text", null, 0, 1, FunctionContribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(functionalSpecificationContainerEClass, FunctionalSpecificationContainer.class, "FunctionalSpecificationContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFunctionalSpecificationContainer_Elements(), this.getFunctionalSpecificationElement(), null, "elements", null, 0, -1, FunctionalSpecificationContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEOperation(getFunctionalSpecificationContainer__GetFunctionContributions(), this.getFunctionContribution(), "getFunctionContributions", 0, -1, IS_UNIQUE, IS_ORDERED);

        initEClass(functionalSpecificationElementEClass, FunctionalSpecificationElement.class, "FunctionalSpecificationElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(preconditionEClass, Precondition.class, "Precondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPrecondition_Name(), ecorePackage.getEString(), "name", null, 0, 1, Precondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(endConditionEClass, EndCondition.class, "EndCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEndCondition_Name(), ecorePackage.getEString(), "name", null, 0, 1, EndCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(triggerEClass, Trigger.class, "Trigger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTrigger_Name(), ecorePackage.getEString(), "name", null, 0, 1, Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(headingEClass, Heading.class, "Heading", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getHeading_Name(), ecorePackage.getEString(), "name", null, 0, 1, Heading.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(descriptionEClass, Description.class, "Description", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDescription_Text(), ecorePackage.getEString(), "text", null, 0, 1, Description.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(vehicleFunctionTypeEEnum, VehicleFunctionType.class, "VehicleFunctionType");
        addEEnumLiteral(vehicleFunctionTypeEEnum, VehicleFunctionType.CUSTOMER_FUNCTION);
        addEEnumLiteral(vehicleFunctionTypeEEnum, VehicleFunctionType.SUPPORTING_FUNCTION);

        // Create resource
        createResource(eNS_URI);
    }

} //SrsPackageImpl
