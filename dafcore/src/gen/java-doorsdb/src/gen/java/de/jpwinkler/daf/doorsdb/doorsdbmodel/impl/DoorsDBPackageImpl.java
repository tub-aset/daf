/**
 */
package de.jpwinkler.daf.doorsdb.doorsdbmodel.impl;

import de.jpwinkler.daf.doorsdb.DoorsDBVisitor;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBFactory;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBPackage;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DoorsDBPackageImpl extends EPackageImpl implements DoorsDBPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dbItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dbFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dbModuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dbVersionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doorsDBEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dbTagEClass = null;

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
	private EDataType doorsDBVisitorEDataType = null;

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
	 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DoorsDBPackageImpl() {
		super(eNS_URI, DoorsDBFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DoorsDBPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DoorsDBPackage init() {
		if (isInited) return (DoorsDBPackage)EPackage.Registry.INSTANCE.getEPackage(DoorsDBPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredDoorsDBPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		DoorsDBPackageImpl theDoorsDBPackage = registeredDoorsDBPackage instanceof DoorsDBPackageImpl ? (DoorsDBPackageImpl)registeredDoorsDBPackage : new DoorsDBPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theDoorsDBPackage.createPackageContents();

		// Initialize created meta-data
		theDoorsDBPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDoorsDBPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DoorsDBPackage.eNS_URI, theDoorsDBPackage);
		return theDoorsDBPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDBItem() {
		return dbItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDBItem_Children() {
		return (EReference)dbItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDBItem_Parent() {
		return (EReference)dbItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDBItem_Name() {
		return (EAttribute)dbItemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDBItem_FullName() {
		return (EAttribute)dbItemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDBItem__Accept__DoorsDBVisitor() {
		return dbItemEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDBFolder() {
		return dbFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDBFolder__GetFolder__String() {
		return dbFolderEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDBFolder__GetModule__String() {
		return dbFolderEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDBModule() {
		return dbModuleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDBModule_Versions() {
		return (EReference)dbModuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDBModule_Url() {
		return (EAttribute)dbModuleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDBModule_Tags() {
		return (EReference)dbModuleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDBModule__GetLatestVersion() {
		return dbModuleEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDBModule__HasTag__String() {
		return dbModuleEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDBVersion() {
		return dbVersionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDBVersion_Module() {
		return (EReference)dbVersionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDBVersion_Date() {
		return (EAttribute)dbVersionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDBVersion_Attributes() {
		return (EReference)dbVersionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDoorsDB() {
		return doorsDBEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsDB_Tags() {
		return (EReference)doorsDBEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsDB_Root() {
		return (EReference)doorsDBEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsDB__Accept__DoorsDBVisitor() {
		return doorsDBEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsDB__GetTag__String() {
		return doorsDBEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDBTag() {
		return dbTagEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDBTag_Name() {
		return (EAttribute)dbTagEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDBTag_Modules() {
		return (EReference)dbTagEClass.getEStructuralFeatures().get(1);
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
	public EDataType getDoorsDBVisitor() {
		return doorsDBVisitorEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsDBFactory getDoorsDBFactory() {
		return (DoorsDBFactory)getEFactoryInstance();
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
		dbItemEClass = createEClass(DB_ITEM);
		createEReference(dbItemEClass, DB_ITEM__CHILDREN);
		createEReference(dbItemEClass, DB_ITEM__PARENT);
		createEAttribute(dbItemEClass, DB_ITEM__NAME);
		createEAttribute(dbItemEClass, DB_ITEM__FULL_NAME);
		createEOperation(dbItemEClass, DB_ITEM___ACCEPT__DOORSDBVISITOR);

		dbFolderEClass = createEClass(DB_FOLDER);
		createEOperation(dbFolderEClass, DB_FOLDER___GET_FOLDER__STRING);
		createEOperation(dbFolderEClass, DB_FOLDER___GET_MODULE__STRING);

		dbModuleEClass = createEClass(DB_MODULE);
		createEReference(dbModuleEClass, DB_MODULE__VERSIONS);
		createEAttribute(dbModuleEClass, DB_MODULE__URL);
		createEReference(dbModuleEClass, DB_MODULE__TAGS);
		createEOperation(dbModuleEClass, DB_MODULE___GET_LATEST_VERSION);
		createEOperation(dbModuleEClass, DB_MODULE___HAS_TAG__STRING);

		dbVersionEClass = createEClass(DB_VERSION);
		createEReference(dbVersionEClass, DB_VERSION__MODULE);
		createEAttribute(dbVersionEClass, DB_VERSION__DATE);
		createEReference(dbVersionEClass, DB_VERSION__ATTRIBUTES);

		doorsDBEClass = createEClass(DOORS_DB);
		createEReference(doorsDBEClass, DOORS_DB__TAGS);
		createEReference(doorsDBEClass, DOORS_DB__ROOT);
		createEOperation(doorsDBEClass, DOORS_DB___ACCEPT__DOORSDBVISITOR);
		createEOperation(doorsDBEClass, DOORS_DB___GET_TAG__STRING);

		dbTagEClass = createEClass(DB_TAG);
		createEAttribute(dbTagEClass, DB_TAG__NAME);
		createEReference(dbTagEClass, DB_TAG__MODULES);

		stringToStringMapEClass = createEClass(STRING_TO_STRING_MAP);
		createEAttribute(stringToStringMapEClass, STRING_TO_STRING_MAP__KEY);
		createEAttribute(stringToStringMapEClass, STRING_TO_STRING_MAP__VALUE);

		// Create data types
		doorsDBVisitorEDataType = createEDataType(DOORS_DB_VISITOR);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		dbFolderEClass.getESuperTypes().add(this.getDBItem());
		dbModuleEClass.getESuperTypes().add(this.getDBItem());

		// Initialize classes, features, and operations; add parameters
		initEClass(dbItemEClass, DBItem.class, "DBItem", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDBItem_Children(), this.getDBItem(), this.getDBItem_Parent(), "children", null, 0, -1, DBItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDBItem_Parent(), this.getDBItem(), this.getDBItem_Children(), "parent", null, 0, 1, DBItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDBItem_Name(), ecorePackage.getEString(), "name", null, 0, 1, DBItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDBItem_FullName(), ecorePackage.getEString(), "fullName", null, 0, 1, DBItem.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getDBItem__Accept__DoorsDBVisitor(), null, "accept", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getDoorsDBVisitor(), "visitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(dbFolderEClass, DBFolder.class, "DBFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = initEOperation(getDBFolder__GetFolder__String(), this.getDBFolder(), "getFolder", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDBFolder__GetModule__String(), this.getDBModule(), "getModule", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(dbModuleEClass, DBModule.class, "DBModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDBModule_Versions(), this.getDBVersion(), this.getDBVersion_Module(), "versions", null, 0, -1, DBModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDBModule_Url(), ecorePackage.getEString(), "url", null, 0, 1, DBModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDBModule_Tags(), this.getDBTag(), this.getDBTag_Modules(), "tags", null, 0, -1, DBModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getDBModule__GetLatestVersion(), this.getDBVersion(), "getLatestVersion", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDBModule__HasTag__String(), ecorePackage.getEBoolean(), "hasTag", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "tag", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(dbVersionEClass, DBVersion.class, "DBVersion", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDBVersion_Module(), this.getDBModule(), this.getDBModule_Versions(), "module", null, 0, 1, DBVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDBVersion_Date(), ecorePackage.getEDate(), "date", null, 0, 1, DBVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDBVersion_Attributes(), this.getStringToStringMap(), null, "attributes", null, 0, -1, DBVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(doorsDBEClass, DoorsDB.class, "DoorsDB", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDoorsDB_Tags(), this.getDBTag(), null, "tags", null, 0, -1, DoorsDB.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsDB_Root(), this.getDBFolder(), null, "root", null, 0, 1, DoorsDB.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = initEOperation(getDoorsDB__Accept__DoorsDBVisitor(), null, "accept", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getDoorsDBVisitor(), "visitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsDB__GetTag__String(), this.getDBTag(), "getTag", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(dbTagEClass, DBTag.class, "DBTag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDBTag_Name(), ecorePackage.getEString(), "name", null, 0, 1, DBTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDBTag_Modules(), this.getDBModule(), this.getDBModule_Tags(), "modules", null, 0, -1, DBTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToStringMapEClass, Map.Entry.class, "StringToStringMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToStringMap_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringToStringMap_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(doorsDBVisitorEDataType, DoorsDBVisitor.class, "DoorsDBVisitor", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //DoorsDBPackageImpl
