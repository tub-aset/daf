/**
 */
package de.jpwinkler.daf.fap5.model.cockpit.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import de.jpwinkler.daf.dafcore.model.common.CommonPackage;
import de.jpwinkler.daf.dafcore.model.csv.CSVPackage;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitFactory;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitModel;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress;
import de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping;
import de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage;
import de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage;
import de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl;
import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryPackage;
import de.jpwinkler.daf.fap5.model.issuehistory.impl.IssueHistoryPackageImpl;
import de.jpwinkler.daf.fap5.model.srs.SrsPackage;
import de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CockpitPackageImpl extends EPackageImpl implements CockpitPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass cockpitModelEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass vehicleFunctionProgressEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass functionContributionTargetMappingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass functionContributionProgressEClass = null;

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
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private CockpitPackageImpl() {
        super(eNS_URI, CockpitFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link CockpitPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static CockpitPackage init() {
        if (isInited) return (CockpitPackage)EPackage.Registry.INSTANCE.getEPackage(CockpitPackage.eNS_URI);

        // Obtain or create and register package
        CockpitPackageImpl theCockpitPackage = (CockpitPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CockpitPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CockpitPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        CSVPackage.eINSTANCE.eClass();
        CommonPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        CodebeamerPackageImpl theCodebeamerPackage = (CodebeamerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CodebeamerPackage.eNS_URI) instanceof CodebeamerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CodebeamerPackage.eNS_URI) : CodebeamerPackage.eINSTANCE);
        ComponentsSystemsPackageImpl theComponentsSystemsPackage = (ComponentsSystemsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentsSystemsPackage.eNS_URI) instanceof ComponentsSystemsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentsSystemsPackage.eNS_URI) : ComponentsSystemsPackage.eINSTANCE);
        IssueHistoryPackageImpl theIssueHistoryPackage = (IssueHistoryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(IssueHistoryPackage.eNS_URI) instanceof IssueHistoryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(IssueHistoryPackage.eNS_URI) : IssueHistoryPackage.eINSTANCE);
        SrsPackageImpl theSrsPackage = (SrsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SrsPackage.eNS_URI) instanceof SrsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SrsPackage.eNS_URI) : SrsPackage.eINSTANCE);

        // Create package meta-data objects
        theCockpitPackage.createPackageContents();
        theCodebeamerPackage.createPackageContents();
        theComponentsSystemsPackage.createPackageContents();
        theIssueHistoryPackage.createPackageContents();
        theSrsPackage.createPackageContents();

        // Initialize created meta-data
        theCockpitPackage.initializePackageContents();
        theCodebeamerPackage.initializePackageContents();
        theComponentsSystemsPackage.initializePackageContents();
        theIssueHistoryPackage.initializePackageContents();
        theSrsPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theCockpitPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(CockpitPackage.eNS_URI, theCockpitPackage);
        return theCockpitPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getCockpitModel() {
        return cockpitModelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getCockpitModel_VehicleFunctionProgress() {
        return (EReference)cockpitModelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getCockpitModel_Documents() {
        return (EReference)cockpitModelEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getCockpitModel__FindMapping__FunctionContributionTarget() {
        return cockpitModelEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getCockpitModel__FindFunctionContributionTargets__CodeBeamerModel() {
        return cockpitModelEClass.getEOperations().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getCockpitModel__GetIssueTypes() {
        return cockpitModelEClass.getEOperations().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getCockpitModel__GetIssues__String() {
        return cockpitModelEClass.getEOperations().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getCockpitModel_Mappings() {
        return (EReference)cockpitModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getVehicleFunctionProgress() {
        return vehicleFunctionProgressEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getVehicleFunctionProgress_VehicleFunction() {
        return (EReference)vehicleFunctionProgressEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getVehicleFunctionProgress_CockpitModel() {
        return (EReference)vehicleFunctionProgressEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getVehicleFunctionProgress_SubFunctionProgress() {
        return (EReference)vehicleFunctionProgressEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getVehicleFunctionProgress_ParentProgress() {
        return (EReference)vehicleFunctionProgressEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getVehicleFunctionProgress_FunctionContributionProgress() {
        return (EReference)vehicleFunctionProgressEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getVehicleFunctionProgress__IsSpecified() {
        return vehicleFunctionProgressEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getVehicleFunctionProgress__IsMapped() {
        return vehicleFunctionProgressEClass.getEOperations().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getVehicleFunctionProgress__GetProgress() {
        return vehicleFunctionProgressEClass.getEOperations().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getVehicleFunctionProgress__GetEstimatedRemainingWork() {
        return vehicleFunctionProgressEClass.getEOperations().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getVehicleFunctionProgress__GetSpecifiedFunctionContributionCount() {
        return vehicleFunctionProgressEClass.getEOperations().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getVehicleFunctionProgress__GetTotalFunctionContributionCount() {
        return vehicleFunctionProgressEClass.getEOperations().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFunctionContributionTargetMapping() {
        return functionContributionTargetMappingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFunctionContributionTargetMapping_FunctionContributionTarget() {
        return (EReference)functionContributionTargetMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFunctionContributionTargetMapping_Documents() {
        return (EReference)functionContributionTargetMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFunctionContributionProgress() {
        return functionContributionProgressEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFunctionContributionProgress_FunctionContribution() {
        return (EReference)functionContributionProgressEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFunctionContributionProgress_CockpitModel() {
        return (EReference)functionContributionProgressEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getFunctionContributionProgress__IsMapped() {
        return functionContributionProgressEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getFunctionContributionProgress__IsSpecified() {
        return functionContributionProgressEClass.getEOperations().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getFunctionContributionProgress__GetExstimatedRemainingWork() {
        return functionContributionProgressEClass.getEOperations().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CockpitFactory getCockpitFactory() {
        return (CockpitFactory)getEFactoryInstance();
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
        cockpitModelEClass = createEClass(COCKPIT_MODEL);
        createEReference(cockpitModelEClass, COCKPIT_MODEL__MAPPINGS);
        createEReference(cockpitModelEClass, COCKPIT_MODEL__VEHICLE_FUNCTION_PROGRESS);
        createEReference(cockpitModelEClass, COCKPIT_MODEL__DOCUMENTS);
        createEOperation(cockpitModelEClass, COCKPIT_MODEL___FIND_MAPPING__FUNCTIONCONTRIBUTIONTARGET);
        createEOperation(cockpitModelEClass, COCKPIT_MODEL___FIND_FUNCTION_CONTRIBUTION_TARGETS__CODEBEAMERMODEL);
        createEOperation(cockpitModelEClass, COCKPIT_MODEL___GET_ISSUE_TYPES);
        createEOperation(cockpitModelEClass, COCKPIT_MODEL___GET_ISSUES__STRING);

        vehicleFunctionProgressEClass = createEClass(VEHICLE_FUNCTION_PROGRESS);
        createEReference(vehicleFunctionProgressEClass, VEHICLE_FUNCTION_PROGRESS__VEHICLE_FUNCTION);
        createEReference(vehicleFunctionProgressEClass, VEHICLE_FUNCTION_PROGRESS__COCKPIT_MODEL);
        createEReference(vehicleFunctionProgressEClass, VEHICLE_FUNCTION_PROGRESS__SUB_FUNCTION_PROGRESS);
        createEReference(vehicleFunctionProgressEClass, VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS);
        createEReference(vehicleFunctionProgressEClass, VEHICLE_FUNCTION_PROGRESS__FUNCTION_CONTRIBUTION_PROGRESS);
        createEOperation(vehicleFunctionProgressEClass, VEHICLE_FUNCTION_PROGRESS___IS_SPECIFIED);
        createEOperation(vehicleFunctionProgressEClass, VEHICLE_FUNCTION_PROGRESS___IS_MAPPED);
        createEOperation(vehicleFunctionProgressEClass, VEHICLE_FUNCTION_PROGRESS___GET_PROGRESS);
        createEOperation(vehicleFunctionProgressEClass, VEHICLE_FUNCTION_PROGRESS___GET_ESTIMATED_REMAINING_WORK);
        createEOperation(vehicleFunctionProgressEClass, VEHICLE_FUNCTION_PROGRESS___GET_SPECIFIED_FUNCTION_CONTRIBUTION_COUNT);
        createEOperation(vehicleFunctionProgressEClass, VEHICLE_FUNCTION_PROGRESS___GET_TOTAL_FUNCTION_CONTRIBUTION_COUNT);

        functionContributionTargetMappingEClass = createEClass(FUNCTION_CONTRIBUTION_TARGET_MAPPING);
        createEReference(functionContributionTargetMappingEClass, FUNCTION_CONTRIBUTION_TARGET_MAPPING__FUNCTION_CONTRIBUTION_TARGET);
        createEReference(functionContributionTargetMappingEClass, FUNCTION_CONTRIBUTION_TARGET_MAPPING__DOCUMENTS);

        functionContributionProgressEClass = createEClass(FUNCTION_CONTRIBUTION_PROGRESS);
        createEReference(functionContributionProgressEClass, FUNCTION_CONTRIBUTION_PROGRESS__FUNCTION_CONTRIBUTION);
        createEReference(functionContributionProgressEClass, FUNCTION_CONTRIBUTION_PROGRESS__COCKPIT_MODEL);
        createEOperation(functionContributionProgressEClass, FUNCTION_CONTRIBUTION_PROGRESS___IS_MAPPED);
        createEOperation(functionContributionProgressEClass, FUNCTION_CONTRIBUTION_PROGRESS___IS_SPECIFIED);
        createEOperation(functionContributionProgressEClass, FUNCTION_CONTRIBUTION_PROGRESS___GET_EXSTIMATED_REMAINING_WORK);
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
        CodebeamerPackage theCodebeamerPackage = (CodebeamerPackage)EPackage.Registry.INSTANCE.getEPackage(CodebeamerPackage.eNS_URI);
        ComponentsSystemsPackage theComponentsSystemsPackage = (ComponentsSystemsPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentsSystemsPackage.eNS_URI);
        SrsPackage theSrsPackage = (SrsPackage)EPackage.Registry.INSTANCE.getEPackage(SrsPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        cockpitModelEClass.getESuperTypes().add(theCommonPackage.getModelObject());
        vehicleFunctionProgressEClass.getESuperTypes().add(theCommonPackage.getModelObject());
        functionContributionTargetMappingEClass.getESuperTypes().add(theCommonPackage.getModelObject());
        functionContributionProgressEClass.getESuperTypes().add(theCommonPackage.getModelObject());

        // Initialize classes, features, and operations; add parameters
        initEClass(cockpitModelEClass, CockpitModel.class, "CockpitModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCockpitModel_Mappings(), this.getFunctionContributionTargetMapping(), null, "mappings", null, 0, -1, CockpitModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCockpitModel_VehicleFunctionProgress(), this.getVehicleFunctionProgress(), null, "vehicleFunctionProgress", null, 0, -1, CockpitModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCockpitModel_Documents(), theCodebeamerPackage.getCodeBeamerModel(), null, "documents", null, 0, -1, CockpitModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        EOperation op = initEOperation(getCockpitModel__FindMapping__FunctionContributionTarget(), this.getFunctionContributionTargetMapping(), "findMapping", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theComponentsSystemsPackage.getFunctionContributionTarget(), "fct", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = initEOperation(getCockpitModel__FindFunctionContributionTargets__CodeBeamerModel(), theComponentsSystemsPackage.getFunctionContributionTarget(), "findFunctionContributionTargets", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theCodebeamerPackage.getCodeBeamerModel(), "cbm", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getCockpitModel__GetIssueTypes(), ecorePackage.getEString(), "getIssueTypes", 0, -1, IS_UNIQUE, IS_ORDERED);

        op = initEOperation(getCockpitModel__GetIssues__String(), theCodebeamerPackage.getIssue(), "getIssues", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "issueType", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(vehicleFunctionProgressEClass, VehicleFunctionProgress.class, "VehicleFunctionProgress", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVehicleFunctionProgress_VehicleFunction(), theSrsPackage.getVehicleFunction(), null, "vehicleFunction", null, 0, 1, VehicleFunctionProgress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVehicleFunctionProgress_CockpitModel(), this.getCockpitModel(), null, "cockpitModel", null, 0, 1, VehicleFunctionProgress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVehicleFunctionProgress_SubFunctionProgress(), this.getVehicleFunctionProgress(), this.getVehicleFunctionProgress_ParentProgress(), "subFunctionProgress", null, 0, -1, VehicleFunctionProgress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVehicleFunctionProgress_ParentProgress(), this.getVehicleFunctionProgress(), this.getVehicleFunctionProgress_SubFunctionProgress(), "parentProgress", null, 0, 1, VehicleFunctionProgress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getVehicleFunctionProgress_FunctionContributionProgress(), this.getFunctionContributionProgress(), null, "functionContributionProgress", null, 0, -1, VehicleFunctionProgress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEOperation(getVehicleFunctionProgress__IsSpecified(), ecorePackage.getEBoolean(), "isSpecified", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getVehicleFunctionProgress__IsMapped(), ecorePackage.getEBoolean(), "isMapped", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getVehicleFunctionProgress__GetProgress(), ecorePackage.getEDouble(), "getProgress", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getVehicleFunctionProgress__GetEstimatedRemainingWork(), ecorePackage.getEDouble(), "getEstimatedRemainingWork", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getVehicleFunctionProgress__GetSpecifiedFunctionContributionCount(), ecorePackage.getELong(), "getSpecifiedFunctionContributionCount", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getVehicleFunctionProgress__GetTotalFunctionContributionCount(), ecorePackage.getELong(), "getTotalFunctionContributionCount", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(functionContributionTargetMappingEClass, FunctionContributionTargetMapping.class, "FunctionContributionTargetMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFunctionContributionTargetMapping_FunctionContributionTarget(), theComponentsSystemsPackage.getFunctionContributionTarget(), null, "functionContributionTarget", null, 0, 1, FunctionContributionTargetMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFunctionContributionTargetMapping_Documents(), theCodebeamerPackage.getCodeBeamerModel(), null, "documents", null, 0, -1, FunctionContributionTargetMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(functionContributionProgressEClass, FunctionContributionProgress.class, "FunctionContributionProgress", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFunctionContributionProgress_FunctionContribution(), theSrsPackage.getFunctionContribution(), null, "functionContribution", null, 0, 1, FunctionContributionProgress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFunctionContributionProgress_CockpitModel(), this.getCockpitModel(), null, "cockpitModel", null, 0, 1, FunctionContributionProgress.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEOperation(getFunctionContributionProgress__IsMapped(), ecorePackage.getEBoolean(), "isMapped", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getFunctionContributionProgress__IsSpecified(), ecorePackage.getEBoolean(), "isSpecified", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getFunctionContributionProgress__GetExstimatedRemainingWork(), ecorePackage.getEDouble(), "getExstimatedRemainingWork", 0, 1, IS_UNIQUE, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //CockpitPackageImpl
