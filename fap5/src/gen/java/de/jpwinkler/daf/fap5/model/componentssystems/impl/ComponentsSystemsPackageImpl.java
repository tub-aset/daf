/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import de.jpwinkler.daf.dafcore.model.common.CommonPackage;
import de.jpwinkler.daf.dafcore.model.csv.CSVPackage;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage;
import de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitPackageImpl;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage;
import de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl;
import de.jpwinkler.daf.fap5.model.componentssystems.Component;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsFactory;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;
import de.jpwinkler.daf.fap5.model.componentssystems.Functionality;
import de.jpwinkler.daf.fap5.model.componentssystems.LogicalComponent;
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
public class ComponentsSystemsPackageImpl extends EPackageImpl implements ComponentsSystemsPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass componentsSystemsModelEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass functionContributionTargetEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass systemEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass componentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass logicalComponentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass functionalityEClass = null;

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
     * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ComponentsSystemsPackageImpl() {
        super(eNS_URI, ComponentsSystemsFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link ComponentsSystemsPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ComponentsSystemsPackage init() {
        if (isInited) return (ComponentsSystemsPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentsSystemsPackage.eNS_URI);

        // Obtain or create and register package
        ComponentsSystemsPackageImpl theComponentsSystemsPackage = (ComponentsSystemsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ComponentsSystemsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ComponentsSystemsPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        CSVPackage.eINSTANCE.eClass();
        CommonPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        CockpitPackageImpl theCockpitPackage = (CockpitPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CockpitPackage.eNS_URI) instanceof CockpitPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CockpitPackage.eNS_URI) : CockpitPackage.eINSTANCE);
        CodebeamerPackageImpl theCodebeamerPackage = (CodebeamerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CodebeamerPackage.eNS_URI) instanceof CodebeamerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CodebeamerPackage.eNS_URI) : CodebeamerPackage.eINSTANCE);
        IssueHistoryPackageImpl theIssueHistoryPackage = (IssueHistoryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(IssueHistoryPackage.eNS_URI) instanceof IssueHistoryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(IssueHistoryPackage.eNS_URI) : IssueHistoryPackage.eINSTANCE);
        SrsPackageImpl theSrsPackage = (SrsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SrsPackage.eNS_URI) instanceof SrsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SrsPackage.eNS_URI) : SrsPackage.eINSTANCE);

        // Create package meta-data objects
        theComponentsSystemsPackage.createPackageContents();
        theCockpitPackage.createPackageContents();
        theCodebeamerPackage.createPackageContents();
        theIssueHistoryPackage.createPackageContents();
        theSrsPackage.createPackageContents();

        // Initialize created meta-data
        theComponentsSystemsPackage.initializePackageContents();
        theCockpitPackage.initializePackageContents();
        theCodebeamerPackage.initializePackageContents();
        theIssueHistoryPackage.initializePackageContents();
        theSrsPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theComponentsSystemsPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ComponentsSystemsPackage.eNS_URI, theComponentsSystemsPackage);
        return theComponentsSystemsPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getComponentsSystemsModel() {
        return componentsSystemsModelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getComponentsSystemsModel_FunctionContributionTargets() {
        return (EReference)componentsSystemsModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFunctionContributionTarget() {
        return functionContributionTargetEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFunctionContributionTarget_Name() {
        return (EAttribute)functionContributionTargetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFunctionContributionTarget_Acronym() {
        return (EAttribute)functionContributionTargetEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFunctionContributionTarget_Responsibility() {
        return (EAttribute)functionContributionTargetEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getFunctionContributionTarget_Department() {
        return (EAttribute)functionContributionTargetEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFunctionContributionTarget_Contributions() {
        return (EReference)functionContributionTargetEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getSystem() {
        return systemEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getSystem_Functionalities() {
        return (EReference)systemEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getComponent() {
        return componentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getLogicalComponent() {
        return logicalComponentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getFunctionality() {
        return functionalityEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getFunctionality_System() {
        return (EReference)functionalityEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ComponentsSystemsFactory getComponentsSystemsFactory() {
        return (ComponentsSystemsFactory)getEFactoryInstance();
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
        componentsSystemsModelEClass = createEClass(COMPONENTS_SYSTEMS_MODEL);
        createEReference(componentsSystemsModelEClass, COMPONENTS_SYSTEMS_MODEL__FUNCTION_CONTRIBUTION_TARGETS);

        functionContributionTargetEClass = createEClass(FUNCTION_CONTRIBUTION_TARGET);
        createEAttribute(functionContributionTargetEClass, FUNCTION_CONTRIBUTION_TARGET__NAME);
        createEAttribute(functionContributionTargetEClass, FUNCTION_CONTRIBUTION_TARGET__ACRONYM);
        createEAttribute(functionContributionTargetEClass, FUNCTION_CONTRIBUTION_TARGET__RESPONSIBILITY);
        createEAttribute(functionContributionTargetEClass, FUNCTION_CONTRIBUTION_TARGET__DEPARTMENT);
        createEReference(functionContributionTargetEClass, FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS);

        systemEClass = createEClass(SYSTEM);
        createEReference(systemEClass, SYSTEM__FUNCTIONALITIES);

        componentEClass = createEClass(COMPONENT);

        logicalComponentEClass = createEClass(LOGICAL_COMPONENT);

        functionalityEClass = createEClass(FUNCTIONALITY);
        createEReference(functionalityEClass, FUNCTIONALITY__SYSTEM);
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
        SrsPackage theSrsPackage = (SrsPackage)EPackage.Registry.INSTANCE.getEPackage(SrsPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        componentsSystemsModelEClass.getESuperTypes().add(theCommonPackage.getModelObject());
        functionContributionTargetEClass.getESuperTypes().add(theCommonPackage.getModelObject());
        systemEClass.getESuperTypes().add(this.getFunctionContributionTarget());
        componentEClass.getESuperTypes().add(this.getFunctionContributionTarget());
        logicalComponentEClass.getESuperTypes().add(this.getFunctionContributionTarget());
        functionalityEClass.getESuperTypes().add(this.getFunctionContributionTarget());

        // Initialize classes, features, and operations; add parameters
        initEClass(componentsSystemsModelEClass, ComponentsSystemsModel.class, "ComponentsSystemsModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getComponentsSystemsModel_FunctionContributionTargets(), this.getFunctionContributionTarget(), null, "functionContributionTargets", null, 0, -1, ComponentsSystemsModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(functionContributionTargetEClass, FunctionContributionTarget.class, "FunctionContributionTarget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFunctionContributionTarget_Name(), ecorePackage.getEString(), "name", null, 0, 1, FunctionContributionTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFunctionContributionTarget_Acronym(), ecorePackage.getEString(), "acronym", null, 0, 1, FunctionContributionTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFunctionContributionTarget_Responsibility(), ecorePackage.getEString(), "responsibility", null, 0, 1, FunctionContributionTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFunctionContributionTarget_Department(), ecorePackage.getEString(), "department", null, 0, 1, FunctionContributionTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFunctionContributionTarget_Contributions(), theSrsPackage.getFunctionContribution(), theSrsPackage.getFunctionContribution_Target(), "contributions", null, 0, -1, FunctionContributionTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(systemEClass, de.jpwinkler.daf.fap5.model.componentssystems.System.class, "System", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSystem_Functionalities(), this.getFunctionality(), this.getFunctionality_System(), "functionalities", null, 0, -1, de.jpwinkler.daf.fap5.model.componentssystems.System.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(logicalComponentEClass, LogicalComponent.class, "LogicalComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(functionalityEClass, Functionality.class, "Functionality", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFunctionality_System(), this.getSystem(), this.getSystem_Functionalities(), "system", null, 0, 1, Functionality.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //ComponentsSystemsPackageImpl
