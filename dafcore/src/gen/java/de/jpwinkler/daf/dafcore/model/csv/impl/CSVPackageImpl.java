/**
 */
package de.jpwinkler.daf.dafcore.model.csv.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import de.jpwinkler.daf.dafcore.csv.DoorsModuleVisitor;
import de.jpwinkler.daf.dafcore.model.common.CommonPackage;
import de.jpwinkler.daf.dafcore.model.common.impl.CommonPackageImpl;
import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.CSVPackage;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.Link;
import de.jpwinkler.daf.dafcore.model.csv.ResolvedLink;
import de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CSVPackageImpl extends EPackageImpl implements CSVPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass doorsModuleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass doorsObjectEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass stringToStringMapEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass linkEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass resolvedLinkEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass unresolvedLinkEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType doorsModuleVisitorEDataType = null;

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
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private CSVPackageImpl() {
        super(eNS_URI, CSVFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link CSVPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static CSVPackage init() {
        if (isInited) {
            return (CSVPackage)EPackage.Registry.INSTANCE.getEPackage(CSVPackage.eNS_URI);
        }

        // Obtain or create and register package
        final CSVPackageImpl theCSVPackage = (CSVPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CSVPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CSVPackageImpl());

        isInited = true;

        // Obtain or create and register interdependencies
        final CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);

        // Create package meta-data objects
        theCSVPackage.createPackageContents();
        theCommonPackage.createPackageContents();

        // Initialize created meta-data
        theCSVPackage.initializePackageContents();
        theCommonPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theCSVPackage.freeze();


        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(CSVPackage.eNS_URI, theCSVPackage);
        return theCSVPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getDoorsModule() {
        return doorsModuleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDoorsModule_Objects() {
        return (EReference)doorsModuleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDoorsModule_Attributes() {
        return (EReference)doorsModuleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsModule_Name() {
        return (EAttribute)doorsModuleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsModule_Path() {
        return (EAttribute)doorsModuleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsModule_Url() {
        return (EAttribute)doorsModuleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getDoorsModule__Accept__DoorsModuleVisitor() {
        return doorsModuleEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getDoorsObject() {
        return doorsObjectEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDoorsObject_Module() {
        return (EReference)doorsObjectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDoorsObject_Attributes() {
        return (EReference)doorsObjectEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDoorsObject_Objects() {
        return (EReference)doorsObjectEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDoorsObject_Parent() {
        return (EReference)doorsObjectEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsObject_ObjectIdentifier() {
        return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsObject_ObjectLevel() {
        return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsObject_ObjectNumber() {
        return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsObject_CreatedBy() {
        return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsObject_CreatedThru() {
        return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsObject_CreatedOn() {
        return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsObject_AbsoluteNumber() {
        return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsObject_LastModifiedOn() {
        return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsObject_LastModifiedBy() {
        return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsObject_ObjectText() {
        return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsObject_ObjectShortText() {
        return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDoorsObject_ObjectHeading() {
        return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDoorsObject_OutgoingLinks() {
        return (EReference)doorsObjectEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDoorsObject_IncomingLinks() {
        return (EReference)doorsObjectEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getDoorsObject__Accept__DoorsModuleVisitor() {
        return doorsObjectEClass.getEOperations().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getDoorsObject__IsHeading() {
        return doorsObjectEClass.getEOperations().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EOperation getDoorsObject__GetText() {
        return doorsObjectEClass.getEOperations().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getStringToStringMap() {
        return stringToStringMapEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getStringToStringMap_Key() {
        return (EAttribute)stringToStringMapEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getStringToStringMap_Value() {
        return (EAttribute)stringToStringMapEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getLink() {
        return linkEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getLink_Source() {
        return (EReference)linkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getResolvedLink() {
        return resolvedLinkEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getResolvedLink_Target() {
        return (EReference)resolvedLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getUnresolvedLink() {
        return unresolvedLinkEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getUnresolvedLink_TargetModule() {
        return (EAttribute)unresolvedLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getUnresolvedLink_TargetObject() {
        return (EAttribute)unresolvedLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EDataType getDoorsModuleVisitor() {
        return doorsModuleVisitorEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CSVFactory getCSVFactory() {
        return (CSVFactory)getEFactoryInstance();
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
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        doorsModuleEClass = createEClass(DOORS_MODULE);
        createEReference(doorsModuleEClass, DOORS_MODULE__OBJECTS);
        createEReference(doorsModuleEClass, DOORS_MODULE__ATTRIBUTES);
        createEAttribute(doorsModuleEClass, DOORS_MODULE__NAME);
        createEAttribute(doorsModuleEClass, DOORS_MODULE__PATH);
        createEAttribute(doorsModuleEClass, DOORS_MODULE__URL);
        createEOperation(doorsModuleEClass, DOORS_MODULE___ACCEPT__DOORSMODULEVISITOR);

        doorsObjectEClass = createEClass(DOORS_OBJECT);
        createEReference(doorsObjectEClass, DOORS_OBJECT__MODULE);
        createEReference(doorsObjectEClass, DOORS_OBJECT__ATTRIBUTES);
        createEReference(doorsObjectEClass, DOORS_OBJECT__OBJECTS);
        createEReference(doorsObjectEClass, DOORS_OBJECT__PARENT);
        createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_IDENTIFIER);
        createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_LEVEL);
        createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_NUMBER);
        createEAttribute(doorsObjectEClass, DOORS_OBJECT__CREATED_BY);
        createEAttribute(doorsObjectEClass, DOORS_OBJECT__CREATED_THRU);
        createEAttribute(doorsObjectEClass, DOORS_OBJECT__CREATED_ON);
        createEAttribute(doorsObjectEClass, DOORS_OBJECT__ABSOLUTE_NUMBER);
        createEAttribute(doorsObjectEClass, DOORS_OBJECT__LAST_MODIFIED_ON);
        createEAttribute(doorsObjectEClass, DOORS_OBJECT__LAST_MODIFIED_BY);
        createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_TEXT);
        createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_SHORT_TEXT);
        createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_HEADING);
        createEReference(doorsObjectEClass, DOORS_OBJECT__OUTGOING_LINKS);
        createEReference(doorsObjectEClass, DOORS_OBJECT__INCOMING_LINKS);
        createEOperation(doorsObjectEClass, DOORS_OBJECT___ACCEPT__DOORSMODULEVISITOR);
        createEOperation(doorsObjectEClass, DOORS_OBJECT___IS_HEADING);
        createEOperation(doorsObjectEClass, DOORS_OBJECT___GET_TEXT);

        stringToStringMapEClass = createEClass(STRING_TO_STRING_MAP);
        createEAttribute(stringToStringMapEClass, STRING_TO_STRING_MAP__KEY);
        createEAttribute(stringToStringMapEClass, STRING_TO_STRING_MAP__VALUE);

        linkEClass = createEClass(LINK);
        createEReference(linkEClass, LINK__SOURCE);

        resolvedLinkEClass = createEClass(RESOLVED_LINK);
        createEReference(resolvedLinkEClass, RESOLVED_LINK__TARGET);

        unresolvedLinkEClass = createEClass(UNRESOLVED_LINK);
        createEAttribute(unresolvedLinkEClass, UNRESOLVED_LINK__TARGET_MODULE);
        createEAttribute(unresolvedLinkEClass, UNRESOLVED_LINK__TARGET_OBJECT);

        // Create data types
        doorsModuleVisitorEDataType = createEDataType(DOORS_MODULE_VISITOR);
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
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        final CommonPackage theCommonPackage = (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        doorsModuleEClass.getESuperTypes().add(theCommonPackage.getModelObject());
        resolvedLinkEClass.getESuperTypes().add(getLink());
        unresolvedLinkEClass.getESuperTypes().add(getLink());

        // Initialize classes, features, and operations; add parameters
        initEClass(doorsModuleEClass, DoorsModule.class, "DoorsModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDoorsModule_Objects(), getDoorsObject(), null, "objects", null, 0, -1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDoorsModule_Attributes(), getStringToStringMap(), null, "attributes", null, 0, -1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEAttribute(getDoorsModule_Name(), ecorePackage.getEString(), "name", null, 0, 1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsModule_Path(), ecorePackage.getEString(), "path", null, 0, 1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsModule_Url(), ecorePackage.getEString(), "url", null, 0, 1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        EOperation op = initEOperation(getDoorsModule__Accept__DoorsModuleVisitor(), null, "accept", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, getDoorsModuleVisitor(), "visitor", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(doorsObjectEClass, DoorsObject.class, "DoorsObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDoorsObject_Module(), getDoorsModule(), null, "module", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDoorsObject_Attributes(), getStringToStringMap(), null, "attributes", null, 0, -1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getDoorsObject_Objects(), getDoorsObject(), getDoorsObject_Parent(), "objects", null, 0, -1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDoorsObject_Parent(), getDoorsObject(), getDoorsObject_Objects(), "parent", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsObject_ObjectIdentifier(), ecorePackage.getEString(), "objectIdentifier", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsObject_ObjectLevel(), ecorePackage.getEInt(), "objectLevel", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsObject_ObjectNumber(), ecorePackage.getEString(), "objectNumber", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsObject_CreatedBy(), ecorePackage.getEString(), "createdBy", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsObject_CreatedThru(), ecorePackage.getEString(), "createdThru", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsObject_CreatedOn(), ecorePackage.getEDate(), "createdOn", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsObject_AbsoluteNumber(), ecorePackage.getEInt(), "absoluteNumber", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsObject_LastModifiedOn(), ecorePackage.getEDate(), "lastModifiedOn", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsObject_LastModifiedBy(), ecorePackage.getEString(), "lastModifiedBy", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsObject_ObjectText(), ecorePackage.getEString(), "objectText", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsObject_ObjectShortText(), ecorePackage.getEString(), "objectShortText", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDoorsObject_ObjectHeading(), ecorePackage.getEString(), "objectHeading", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDoorsObject_OutgoingLinks(), getLink(), getLink_Source(), "outgoingLinks", null, 0, -1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDoorsObject_IncomingLinks(), getResolvedLink(), getResolvedLink_Target(), "incomingLinks", null, 0, -1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = initEOperation(getDoorsObject__Accept__DoorsModuleVisitor(), null, "accept", 0, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, getDoorsModuleVisitor(), "visitor", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getDoorsObject__IsHeading(), ecorePackage.getEBoolean(), "isHeading", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEOperation(getDoorsObject__GetText(), ecorePackage.getEString(), "getText", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(stringToStringMapEClass, Map.Entry.class, "StringToStringMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStringToStringMap_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getStringToStringMap_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(linkEClass, Link.class, "Link", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLink_Source(), getDoorsObject(), getDoorsObject_OutgoingLinks(), "source", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(resolvedLinkEClass, ResolvedLink.class, "ResolvedLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getResolvedLink_Target(), getDoorsObject(), getDoorsObject_IncomingLinks(), "target", null, 0, 1, ResolvedLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(unresolvedLinkEClass, UnresolvedLink.class, "UnresolvedLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUnresolvedLink_TargetModule(), ecorePackage.getEString(), "targetModule", null, 0, 1, UnresolvedLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getUnresolvedLink_TargetObject(), ecorePackage.getEString(), "targetObject", null, 0, 1, UnresolvedLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize data types
        initEDataType(doorsModuleVisitorEDataType, DoorsModuleVisitor.class, "DoorsModuleVisitor", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);
    }

} //CSVPackageImpl
