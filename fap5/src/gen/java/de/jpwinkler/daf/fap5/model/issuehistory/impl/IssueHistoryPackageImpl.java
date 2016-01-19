/**
 */
package de.jpwinkler.daf.fap5.model.issuehistory.impl;

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
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage;
import de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage;
import de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl;
import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryFactory;
import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel;
import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryPackage;
import de.jpwinkler.daf.fap5.model.issuehistory.Version;
import de.jpwinkler.daf.fap5.model.srs.SrsPackage;
import de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IssueHistoryPackageImpl extends EPackageImpl implements IssueHistoryPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass issueHistoryModelEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass versionEClass = null;

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
     * @see de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private IssueHistoryPackageImpl() {
        super(eNS_URI, IssueHistoryFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link IssueHistoryPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static IssueHistoryPackage init() {
        if (isInited) return (IssueHistoryPackage)EPackage.Registry.INSTANCE.getEPackage(IssueHistoryPackage.eNS_URI);

        // Obtain or create and register package
        IssueHistoryPackageImpl theIssueHistoryPackage = (IssueHistoryPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof IssueHistoryPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new IssueHistoryPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        CSVPackage.eINSTANCE.eClass();
        CommonPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        CockpitPackageImpl theCockpitPackage = (CockpitPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CockpitPackage.eNS_URI) instanceof CockpitPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CockpitPackage.eNS_URI) : CockpitPackage.eINSTANCE);
        CodebeamerPackageImpl theCodebeamerPackage = (CodebeamerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CodebeamerPackage.eNS_URI) instanceof CodebeamerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CodebeamerPackage.eNS_URI) : CodebeamerPackage.eINSTANCE);
        ComponentsSystemsPackageImpl theComponentsSystemsPackage = (ComponentsSystemsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentsSystemsPackage.eNS_URI) instanceof ComponentsSystemsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentsSystemsPackage.eNS_URI) : ComponentsSystemsPackage.eINSTANCE);
        SrsPackageImpl theSrsPackage = (SrsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SrsPackage.eNS_URI) instanceof SrsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SrsPackage.eNS_URI) : SrsPackage.eINSTANCE);

        // Create package meta-data objects
        theIssueHistoryPackage.createPackageContents();
        theCockpitPackage.createPackageContents();
        theCodebeamerPackage.createPackageContents();
        theComponentsSystemsPackage.createPackageContents();
        theSrsPackage.createPackageContents();

        // Initialize created meta-data
        theIssueHistoryPackage.initializePackageContents();
        theCockpitPackage.initializePackageContents();
        theCodebeamerPackage.initializePackageContents();
        theComponentsSystemsPackage.initializePackageContents();
        theSrsPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theIssueHistoryPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(IssueHistoryPackage.eNS_URI, theIssueHistoryPackage);
        return theIssueHistoryPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getIssueHistoryModel() {
        return issueHistoryModelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getIssueHistoryModel_Versions() {
        return (EReference)issueHistoryModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getIssueHistoryModel__GetDocumentNames() {
        return issueHistoryModelEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getIssueHistoryModel__GetIssueTypes() {
        return issueHistoryModelEClass.getEOperations().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getVersion() {
        return versionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getVersion_Documents() {
        return (EReference)versionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getVersion_VersionNumber() {
        return (EAttribute)versionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getVersion__GetDocument__String() {
        return versionEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getVersion__GetIssues__String() {
        return versionEClass.getEOperations().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public IssueHistoryFactory getIssueHistoryFactory() {
        return (IssueHistoryFactory)getEFactoryInstance();
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
        issueHistoryModelEClass = createEClass(ISSUE_HISTORY_MODEL);
        createEReference(issueHistoryModelEClass, ISSUE_HISTORY_MODEL__VERSIONS);
        createEOperation(issueHistoryModelEClass, ISSUE_HISTORY_MODEL___GET_DOCUMENT_NAMES);
        createEOperation(issueHistoryModelEClass, ISSUE_HISTORY_MODEL___GET_ISSUE_TYPES);

        versionEClass = createEClass(VERSION);
        createEReference(versionEClass, VERSION__DOCUMENTS);
        createEAttribute(versionEClass, VERSION__VERSION_NUMBER);
        createEOperation(versionEClass, VERSION___GET_DOCUMENT__STRING);
        createEOperation(versionEClass, VERSION___GET_ISSUES__STRING);
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

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        issueHistoryModelEClass.getESuperTypes().add(theCommonPackage.getModelObject());
        versionEClass.getESuperTypes().add(theCommonPackage.getModelObject());

        // Initialize classes, features, and operations; add parameters
        initEClass(issueHistoryModelEClass, IssueHistoryModel.class, "IssueHistoryModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIssueHistoryModel_Versions(), this.getVersion(), null, "versions", null, 0, -1, IssueHistoryModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEOperation(getIssueHistoryModel__GetDocumentNames(), ecorePackage.getEString(), "getDocumentNames", 0, -1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getIssueHistoryModel__GetIssueTypes(), ecorePackage.getEString(), "getIssueTypes", 0, -1, IS_UNIQUE, IS_ORDERED);

        initEClass(versionEClass, Version.class, "Version", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getVersion_Documents(), theCodebeamerPackage.getCodeBeamerModel(), null, "documents", null, 0, -1, Version.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getVersion_VersionNumber(), ecorePackage.getEString(), "versionNumber", null, 0, 1, Version.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        EOperation op = initEOperation(getVersion__GetDocument__String(), theCodebeamerPackage.getCodeBeamerModel(), "getDocument", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

        op = initEOperation(getVersion__GetIssues__String(), theCodebeamerPackage.getIssue(), "getIssues", 0, -1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, ecorePackage.getEString(), "issueType", 0, 1, IS_UNIQUE, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //IssueHistoryPackageImpl
