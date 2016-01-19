/**
 */
package de.jpwinkler.daf.fap5.model.codebeamer.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import de.jpwinkler.daf.dafcore.model.common.CommonPackage;
import de.jpwinkler.daf.dafcore.model.csv.CSVPackage;
import de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage;
import de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitPackageImpl;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerFactory;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage;
import de.jpwinkler.daf.fap5.model.codebeamer.DoubleMetric;
import de.jpwinkler.daf.fap5.model.codebeamer.IntMetric;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;
import de.jpwinkler.daf.fap5.model.codebeamer.Metric;
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
public class CodebeamerPackageImpl extends EPackageImpl implements CodebeamerPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass codeBeamerModelEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass issueEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass metricEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass intMetricEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass doubleMetricEClass = null;
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
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private CodebeamerPackageImpl() {
        super(eNS_URI, CodebeamerFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link CodebeamerPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static CodebeamerPackage init() {
        if (isInited) return (CodebeamerPackage)EPackage.Registry.INSTANCE.getEPackage(CodebeamerPackage.eNS_URI);

        // Obtain or create and register package
        CodebeamerPackageImpl theCodebeamerPackage = (CodebeamerPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CodebeamerPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CodebeamerPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        CSVPackage.eINSTANCE.eClass();
        CommonPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        CockpitPackageImpl theCockpitPackage = (CockpitPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CockpitPackage.eNS_URI) instanceof CockpitPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CockpitPackage.eNS_URI) : CockpitPackage.eINSTANCE);
        ComponentsSystemsPackageImpl theComponentsSystemsPackage = (ComponentsSystemsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentsSystemsPackage.eNS_URI) instanceof ComponentsSystemsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentsSystemsPackage.eNS_URI) : ComponentsSystemsPackage.eINSTANCE);
        IssueHistoryPackageImpl theIssueHistoryPackage = (IssueHistoryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(IssueHistoryPackage.eNS_URI) instanceof IssueHistoryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(IssueHistoryPackage.eNS_URI) : IssueHistoryPackage.eINSTANCE);
        SrsPackageImpl theSrsPackage = (SrsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SrsPackage.eNS_URI) instanceof SrsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SrsPackage.eNS_URI) : SrsPackage.eINSTANCE);

        // Create package meta-data objects
        theCodebeamerPackage.createPackageContents();
        theCockpitPackage.createPackageContents();
        theComponentsSystemsPackage.createPackageContents();
        theIssueHistoryPackage.createPackageContents();
        theSrsPackage.createPackageContents();

        // Initialize created meta-data
        theCodebeamerPackage.initializePackageContents();
        theCockpitPackage.initializePackageContents();
        theComponentsSystemsPackage.initializePackageContents();
        theIssueHistoryPackage.initializePackageContents();
        theSrsPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theCodebeamerPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(CodebeamerPackage.eNS_URI, theCodebeamerPackage);
        return theCodebeamerPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getCodeBeamerModel() {
        return codeBeamerModelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getCodeBeamerModel_Name() {
        return (EAttribute)codeBeamerModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getCodeBeamerModel_Specified() {
        return (EAttribute)codeBeamerModelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getCodeBeamerModel_Size() {
        return (EAttribute)codeBeamerModelEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getCodeBeamerModel_Issues() {
        return (EReference)codeBeamerModelEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getCodeBeamerModel_VersionNumber() {
        return (EAttribute)codeBeamerModelEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getCodeBeamerModel_Metrics() {
        return (EReference)codeBeamerModelEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getCodeBeamerModel_Module() {
        return (EReference)codeBeamerModelEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getCodeBeamerModel_Path() {
        return (EAttribute)codeBeamerModelEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getCodeBeamerModel__GetEstimatedRemainingWork() {
        return codeBeamerModelEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getCodeBeamerModel__GetIssueTypes() {
        return codeBeamerModelEClass.getEOperations().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getCodeBeamerModel__GetIssues__String() {
        return codeBeamerModelEClass.getEOperations().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getCodeBeamerModel__GetDoubleMetric__String() {
        return codeBeamerModelEClass.getEOperations().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getCodeBeamerModel__GetIntMetric__String() {
        return codeBeamerModelEClass.getEOperations().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EOperation getCodeBeamerModel__GetFullName() {
        return codeBeamerModelEClass.getEOperations().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getIssue() {
        return issueEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getIssue_Severity() {
        return (EAttribute)issueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getIssue_Description() {
        return (EAttribute)issueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getIssue_IssueType() {
        return (EAttribute)issueEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getMetric() {
        return metricEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getMetric_Name() {
        return (EAttribute)metricEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getIntMetric() {
        return intMetricEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getIntMetric_Value() {
        return (EAttribute)intMetricEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getDoubleMetric() {
        return doubleMetricEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoubleMetric_Value() {
        return (EAttribute)doubleMetricEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CodebeamerFactory getCodebeamerFactory() {
        return (CodebeamerFactory)getEFactoryInstance();
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
        codeBeamerModelEClass = createEClass(CODE_BEAMER_MODEL);
        createEAttribute(codeBeamerModelEClass, CODE_BEAMER_MODEL__NAME);
        createEAttribute(codeBeamerModelEClass, CODE_BEAMER_MODEL__SPECIFIED);
        createEAttribute(codeBeamerModelEClass, CODE_BEAMER_MODEL__SIZE);
        createEReference(codeBeamerModelEClass, CODE_BEAMER_MODEL__ISSUES);
        createEAttribute(codeBeamerModelEClass, CODE_BEAMER_MODEL__VERSION_NUMBER);
        createEReference(codeBeamerModelEClass, CODE_BEAMER_MODEL__METRICS);
        createEReference(codeBeamerModelEClass, CODE_BEAMER_MODEL__MODULE);
        createEAttribute(codeBeamerModelEClass, CODE_BEAMER_MODEL__PATH);
        createEOperation(codeBeamerModelEClass, CODE_BEAMER_MODEL___GET_ESTIMATED_REMAINING_WORK);
        createEOperation(codeBeamerModelEClass, CODE_BEAMER_MODEL___GET_ISSUE_TYPES);
        createEOperation(codeBeamerModelEClass, CODE_BEAMER_MODEL___GET_ISSUES__STRING);
        createEOperation(codeBeamerModelEClass, CODE_BEAMER_MODEL___GET_DOUBLE_METRIC__STRING);
        createEOperation(codeBeamerModelEClass, CODE_BEAMER_MODEL___GET_INT_METRIC__STRING);
        createEOperation(codeBeamerModelEClass, CODE_BEAMER_MODEL___GET_FULL_NAME);

        issueEClass = createEClass(ISSUE);
        createEAttribute(issueEClass, ISSUE__SEVERITY);
        createEAttribute(issueEClass, ISSUE__DESCRIPTION);
        createEAttribute(issueEClass, ISSUE__ISSUE_TYPE);

        metricEClass = createEClass(METRIC);
        createEAttribute(metricEClass, METRIC__NAME);

        intMetricEClass = createEClass(INT_METRIC);
        createEAttribute(intMetricEClass, INT_METRIC__VALUE);

        doubleMetricEClass = createEClass(DOUBLE_METRIC);
        createEAttribute(doubleMetricEClass, DOUBLE_METRIC__VALUE);
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
        CSVPackage theCSVPackage = (CSVPackage)EPackage.Registry.INSTANCE.getEPackage(CSVPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        codeBeamerModelEClass.getESuperTypes().add(theCommonPackage.getModelObject());
        issueEClass.getESuperTypes().add(theCommonPackage.getModelObject());
        intMetricEClass.getESuperTypes().add(this.getMetric());
        doubleMetricEClass.getESuperTypes().add(this.getMetric());

        // Initialize classes, features, and operations; add parameters
        initEClass(codeBeamerModelEClass, CodeBeamerModel.class, "CodeBeamerModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCodeBeamerModel_Name(), ecorePackage.getEString(), "name", null, 0, 1, CodeBeamerModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCodeBeamerModel_Specified(), ecorePackage.getEBoolean(), "specified", null, 0, 1, CodeBeamerModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCodeBeamerModel_Size(), ecorePackage.getEFloat(), "size", "0", 0, 1, CodeBeamerModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCodeBeamerModel_Issues(), this.getIssue(), null, "issues", null, 0, -1, CodeBeamerModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCodeBeamerModel_VersionNumber(), ecorePackage.getEString(), "versionNumber", null, 0, 1, CodeBeamerModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCodeBeamerModel_Metrics(), this.getMetric(), null, "metrics", null, 0, -1, CodeBeamerModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCodeBeamerModel_Module(), theCSVPackage.getDoorsModule(), null, "module", null, 0, 1, CodeBeamerModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCodeBeamerModel_Path(), ecorePackage.getEString(), "path", null, 0, 1, CodeBeamerModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEOperation(getCodeBeamerModel__GetEstimatedRemainingWork(), ecorePackage.getELong(), "getEstimatedRemainingWork", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getCodeBeamerModel__GetIssueTypes(), ecorePackage.getEString(), "getIssueTypes", 0, -1, IS_UNIQUE, IS_ORDERED);

        EOperation op = initEOperation(getCodeBeamerModel__GetIssues__String(), this.getIssue(), "getIssues", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "issueType", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = initEOperation(getCodeBeamerModel__GetDoubleMetric__String(), ecorePackage.getEDoubleObject(), "getDoubleMetric", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = initEOperation(getCodeBeamerModel__GetIntMetric__String(), ecorePackage.getEIntegerObject(), "getIntMetric", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getCodeBeamerModel__GetFullName(), ecorePackage.getEString(), "getFullName", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(issueEClass, Issue.class, "Issue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIssue_Severity(), ecorePackage.getELong(), "severity", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIssue_Description(), ecorePackage.getEString(), "description", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIssue_IssueType(), ecorePackage.getEString(), "issueType", null, 0, 1, Issue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(metricEClass, Metric.class, "Metric", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMetric_Name(), ecorePackage.getEString(), "name", null, 0, 1, Metric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(intMetricEClass, IntMetric.class, "IntMetric", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIntMetric_Value(), ecorePackage.getEInt(), "value", null, 0, 1, IntMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(doubleMetricEClass, DoubleMetric.class, "DoubleMetric", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDoubleMetric_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, DoubleMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //CodebeamerPackageImpl
