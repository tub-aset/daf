/**
 */
package de.jpwinkler.daf.doorsdb.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.doorsdb.model.DoorsDBFactory
 * @model kind="package"
 * @generated
 */
public interface DoorsDBPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "de.jpwinkler.daf.doorsdb.doorsdbmodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "DoorsDB";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DoorsDBPackage eINSTANCE = de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.doorsdb.model.impl.DBItemImpl <em>DB Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DBItemImpl
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDBItem()
	 * @generated
	 */
	int DB_ITEM = 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_ITEM__CHILDREN = 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_ITEM__PARENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_ITEM__NAME = 2;

	/**
	 * The feature id for the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_ITEM__FULL_NAME = 3;

	/**
	 * The number of structural features of the '<em>DB Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_ITEM_FEATURE_COUNT = 4;

	/**
	 * The operation id for the '<em>Accept</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_ITEM___ACCEPT__DOORSDBVISITOR = 0;

	/**
	 * The number of operations of the '<em>DB Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_ITEM_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.doorsdb.model.impl.DBFolderImpl <em>DB Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DBFolderImpl
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDBFolder()
	 * @generated
	 */
	int DB_FOLDER = 1;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_FOLDER__CHILDREN = DB_ITEM__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_FOLDER__PARENT = DB_ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_FOLDER__NAME = DB_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_FOLDER__FULL_NAME = DB_ITEM__FULL_NAME;

	/**
	 * The number of structural features of the '<em>DB Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_FOLDER_FEATURE_COUNT = DB_ITEM_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Accept</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_FOLDER___ACCEPT__DOORSDBVISITOR = DB_ITEM___ACCEPT__DOORSDBVISITOR;

	/**
	 * The operation id for the '<em>Get Folder</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_FOLDER___GET_FOLDER__STRING = DB_ITEM_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Module</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_FOLDER___GET_MODULE__STRING = DB_ITEM_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>DB Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_FOLDER_OPERATION_COUNT = DB_ITEM_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.doorsdb.model.impl.DBModuleImpl <em>DB Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DBModuleImpl
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDBModule()
	 * @generated
	 */
	int DB_MODULE = 2;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE__CHILDREN = DB_ITEM__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE__PARENT = DB_ITEM__PARENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE__NAME = DB_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE__FULL_NAME = DB_ITEM__FULL_NAME;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE__VERSIONS = DB_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE__URL = DB_ITEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE__TAGS = DB_ITEM_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>DB Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE_FEATURE_COUNT = DB_ITEM_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Accept</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE___ACCEPT__DOORSDBVISITOR = DB_ITEM___ACCEPT__DOORSDBVISITOR;

	/**
	 * The operation id for the '<em>Get Latest Version</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE___GET_LATEST_VERSION = DB_ITEM_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Has Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE___HAS_TAG__STRING = DB_ITEM_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>DB Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_MODULE_OPERATION_COUNT = DB_ITEM_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.doorsdb.model.impl.DBVersionImpl <em>DB Version</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DBVersionImpl
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDBVersion()
	 * @generated
	 */
	int DB_VERSION = 3;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_VERSION__MODULE = 0;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_VERSION__DATE = 1;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_VERSION__ATTRIBUTES = 2;

	/**
	 * The number of structural features of the '<em>DB Version</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_VERSION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>DB Version</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_VERSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.doorsdb.model.impl.DoorsDBImpl <em>Doors DB</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBImpl
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDoorsDB()
	 * @generated
	 */
	int DOORS_DB = 4;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_DB__TAGS = 0;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_DB__ROOT = 1;

	/**
	 * The number of structural features of the '<em>Doors DB</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_DB_FEATURE_COUNT = 2;

	/**
	 * The operation id for the '<em>Accept</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_DB___ACCEPT__DOORSDBVISITOR = 0;

	/**
	 * The operation id for the '<em>Get Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_DB___GET_TAG__STRING = 1;

	/**
	 * The number of operations of the '<em>Doors DB</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_DB_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.doorsdb.model.impl.DBTagImpl <em>DB Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DBTagImpl
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDBTag()
	 * @generated
	 */
	int DB_TAG = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_TAG__NAME = 0;

	/**
	 * The feature id for the '<em><b>Modules</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_TAG__MODULES = 1;

	/**
	 * The number of structural features of the '<em>DB Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_TAG_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>DB Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB_TAG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.doorsdb.model.impl.StringToStringMapImpl <em>String To String Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.doorsdb.model.impl.StringToStringMapImpl
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getStringToStringMap()
	 * @generated
	 */
	int STRING_TO_STRING_MAP = 6;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_STRING_MAP__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_STRING_MAP__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To String Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_STRING_MAP_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>String To String Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_STRING_MAP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '<em>Visitor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.doorsdb.DoorsDBVisitor
	 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDoorsDBVisitor()
	 * @generated
	 */
	int DOORS_DB_VISITOR = 7;


	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.doorsdb.model.DBItem <em>DB Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB Item</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBItem
	 * @generated
	 */
	EClass getDBItem();

	/**
	 * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.doorsdb.model.DBItem#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBItem#getChildren()
	 * @see #getDBItem()
	 * @generated
	 */
	EReference getDBItem_Children();

	/**
	 * Returns the meta object for the container reference '{@link de.jpwinkler.daf.doorsdb.model.DBItem#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBItem#getParent()
	 * @see #getDBItem()
	 * @generated
	 */
	EReference getDBItem_Parent();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.doorsdb.model.DBItem#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBItem#getName()
	 * @see #getDBItem()
	 * @generated
	 */
	EAttribute getDBItem_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.doorsdb.model.DBItem#getFullName <em>Full Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Full Name</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBItem#getFullName()
	 * @see #getDBItem()
	 * @generated
	 */
	EAttribute getDBItem_FullName();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.doorsdb.model.DBItem#accept(de.jpwinkler.daf.doorsdb.DoorsDBVisitor) <em>Accept</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Accept</em>' operation.
	 * @see de.jpwinkler.daf.doorsdb.model.DBItem#accept(de.jpwinkler.daf.doorsdb.DoorsDBVisitor)
	 * @generated
	 */
	EOperation getDBItem__Accept__DoorsDBVisitor();

	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.doorsdb.model.DBFolder <em>DB Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB Folder</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBFolder
	 * @generated
	 */
	EClass getDBFolder();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.doorsdb.model.DBFolder#getFolder(java.lang.String) <em>Get Folder</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Folder</em>' operation.
	 * @see de.jpwinkler.daf.doorsdb.model.DBFolder#getFolder(java.lang.String)
	 * @generated
	 */
	EOperation getDBFolder__GetFolder__String();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.doorsdb.model.DBFolder#getModule(java.lang.String) <em>Get Module</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Module</em>' operation.
	 * @see de.jpwinkler.daf.doorsdb.model.DBFolder#getModule(java.lang.String)
	 * @generated
	 */
	EOperation getDBFolder__GetModule__String();

	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.doorsdb.model.DBModule <em>DB Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB Module</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBModule
	 * @generated
	 */
	EClass getDBModule();

	/**
	 * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.doorsdb.model.DBModule#getVersions <em>Versions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Versions</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBModule#getVersions()
	 * @see #getDBModule()
	 * @generated
	 */
	EReference getDBModule_Versions();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.doorsdb.model.DBModule#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBModule#getUrl()
	 * @see #getDBModule()
	 * @generated
	 */
	EAttribute getDBModule_Url();

	/**
	 * Returns the meta object for the reference list '{@link de.jpwinkler.daf.doorsdb.model.DBModule#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tags</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBModule#getTags()
	 * @see #getDBModule()
	 * @generated
	 */
	EReference getDBModule_Tags();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.doorsdb.model.DBModule#getLatestVersion() <em>Get Latest Version</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Latest Version</em>' operation.
	 * @see de.jpwinkler.daf.doorsdb.model.DBModule#getLatestVersion()
	 * @generated
	 */
	EOperation getDBModule__GetLatestVersion();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.doorsdb.model.DBModule#hasTag(java.lang.String) <em>Has Tag</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Has Tag</em>' operation.
	 * @see de.jpwinkler.daf.doorsdb.model.DBModule#hasTag(java.lang.String)
	 * @generated
	 */
	EOperation getDBModule__HasTag__String();

	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.doorsdb.model.DBVersion <em>DB Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB Version</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBVersion
	 * @generated
	 */
	EClass getDBVersion();

	/**
	 * Returns the meta object for the container reference '{@link de.jpwinkler.daf.doorsdb.model.DBVersion#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Module</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBVersion#getModule()
	 * @see #getDBVersion()
	 * @generated
	 */
	EReference getDBVersion_Module();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.doorsdb.model.DBVersion#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBVersion#getDate()
	 * @see #getDBVersion()
	 * @generated
	 */
	EAttribute getDBVersion_Date();

	/**
	 * Returns the meta object for the map '{@link de.jpwinkler.daf.doorsdb.model.DBVersion#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Attributes</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBVersion#getAttributes()
	 * @see #getDBVersion()
	 * @generated
	 */
	EReference getDBVersion_Attributes();

	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.doorsdb.model.DoorsDB <em>Doors DB</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Doors DB</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DoorsDB
	 * @generated
	 */
	EClass getDoorsDB();

	/**
	 * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.doorsdb.model.DoorsDB#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tags</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DoorsDB#getTags()
	 * @see #getDoorsDB()
	 * @generated
	 */
	EReference getDoorsDB_Tags();

	/**
	 * Returns the meta object for the containment reference '{@link de.jpwinkler.daf.doorsdb.model.DoorsDB#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DoorsDB#getRoot()
	 * @see #getDoorsDB()
	 * @generated
	 */
	EReference getDoorsDB_Root();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.doorsdb.model.DoorsDB#accept(de.jpwinkler.daf.doorsdb.DoorsDBVisitor) <em>Accept</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Accept</em>' operation.
	 * @see de.jpwinkler.daf.doorsdb.model.DoorsDB#accept(de.jpwinkler.daf.doorsdb.DoorsDBVisitor)
	 * @generated
	 */
	EOperation getDoorsDB__Accept__DoorsDBVisitor();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.doorsdb.model.DoorsDB#getTag(java.lang.String) <em>Get Tag</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Tag</em>' operation.
	 * @see de.jpwinkler.daf.doorsdb.model.DoorsDB#getTag(java.lang.String)
	 * @generated
	 */
	EOperation getDoorsDB__GetTag__String();

	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.doorsdb.model.DBTag <em>DB Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB Tag</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBTag
	 * @generated
	 */
	EClass getDBTag();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.doorsdb.model.DBTag#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBTag#getName()
	 * @see #getDBTag()
	 * @generated
	 */
	EAttribute getDBTag_Name();

	/**
	 * Returns the meta object for the reference list '{@link de.jpwinkler.daf.doorsdb.model.DBTag#getModules <em>Modules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Modules</em>'.
	 * @see de.jpwinkler.daf.doorsdb.model.DBTag#getModules()
	 * @see #getDBTag()
	 * @generated
	 */
	EReference getDBTag_Modules();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To String Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To String Map</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getStringToStringMap();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToStringMap()
	 * @generated
	 */
	EAttribute getStringToStringMap_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToStringMap()
	 * @generated
	 */
	EAttribute getStringToStringMap_Value();

	/**
	 * Returns the meta object for data type '{@link de.jpwinkler.daf.doorsdb.DoorsDBVisitor <em>Visitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Visitor</em>'.
	 * @see de.jpwinkler.daf.doorsdb.DoorsDBVisitor
	 * @model instanceClass="de.jpwinkler.daf.doorsdb.DoorsDBVisitor"
	 * @generated
	 */
	EDataType getDoorsDBVisitor();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DoorsDBFactory getDoorsDBFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.doorsdb.model.impl.DBItemImpl <em>DB Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DBItemImpl
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDBItem()
		 * @generated
		 */
		EClass DB_ITEM = eINSTANCE.getDBItem();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB_ITEM__CHILDREN = eINSTANCE.getDBItem_Children();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB_ITEM__PARENT = eINSTANCE.getDBItem_Parent();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB_ITEM__NAME = eINSTANCE.getDBItem_Name();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB_ITEM__FULL_NAME = eINSTANCE.getDBItem_FullName();

		/**
		 * The meta object literal for the '<em><b>Accept</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DB_ITEM___ACCEPT__DOORSDBVISITOR = eINSTANCE.getDBItem__Accept__DoorsDBVisitor();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.doorsdb.model.impl.DBFolderImpl <em>DB Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DBFolderImpl
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDBFolder()
		 * @generated
		 */
		EClass DB_FOLDER = eINSTANCE.getDBFolder();

		/**
		 * The meta object literal for the '<em><b>Get Folder</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DB_FOLDER___GET_FOLDER__STRING = eINSTANCE.getDBFolder__GetFolder__String();

		/**
		 * The meta object literal for the '<em><b>Get Module</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DB_FOLDER___GET_MODULE__STRING = eINSTANCE.getDBFolder__GetModule__String();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.doorsdb.model.impl.DBModuleImpl <em>DB Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DBModuleImpl
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDBModule()
		 * @generated
		 */
		EClass DB_MODULE = eINSTANCE.getDBModule();

		/**
		 * The meta object literal for the '<em><b>Versions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB_MODULE__VERSIONS = eINSTANCE.getDBModule_Versions();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB_MODULE__URL = eINSTANCE.getDBModule_Url();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB_MODULE__TAGS = eINSTANCE.getDBModule_Tags();

		/**
		 * The meta object literal for the '<em><b>Get Latest Version</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DB_MODULE___GET_LATEST_VERSION = eINSTANCE.getDBModule__GetLatestVersion();

		/**
		 * The meta object literal for the '<em><b>Has Tag</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DB_MODULE___HAS_TAG__STRING = eINSTANCE.getDBModule__HasTag__String();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.doorsdb.model.impl.DBVersionImpl <em>DB Version</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DBVersionImpl
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDBVersion()
		 * @generated
		 */
		EClass DB_VERSION = eINSTANCE.getDBVersion();

		/**
		 * The meta object literal for the '<em><b>Module</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB_VERSION__MODULE = eINSTANCE.getDBVersion_Module();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB_VERSION__DATE = eINSTANCE.getDBVersion_Date();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB_VERSION__ATTRIBUTES = eINSTANCE.getDBVersion_Attributes();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.doorsdb.model.impl.DoorsDBImpl <em>Doors DB</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBImpl
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDoorsDB()
		 * @generated
		 */
		EClass DOORS_DB = eINSTANCE.getDoorsDB();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOORS_DB__TAGS = eINSTANCE.getDoorsDB_Tags();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOORS_DB__ROOT = eINSTANCE.getDoorsDB_Root();

		/**
		 * The meta object literal for the '<em><b>Accept</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_DB___ACCEPT__DOORSDBVISITOR = eINSTANCE.getDoorsDB__Accept__DoorsDBVisitor();

		/**
		 * The meta object literal for the '<em><b>Get Tag</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_DB___GET_TAG__STRING = eINSTANCE.getDoorsDB__GetTag__String();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.doorsdb.model.impl.DBTagImpl <em>DB Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DBTagImpl
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDBTag()
		 * @generated
		 */
		EClass DB_TAG = eINSTANCE.getDBTag();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB_TAG__NAME = eINSTANCE.getDBTag_Name();

		/**
		 * The meta object literal for the '<em><b>Modules</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB_TAG__MODULES = eINSTANCE.getDBTag_Modules();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.doorsdb.model.impl.StringToStringMapImpl <em>String To String Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.doorsdb.model.impl.StringToStringMapImpl
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getStringToStringMap()
		 * @generated
		 */
		EClass STRING_TO_STRING_MAP = eINSTANCE.getStringToStringMap();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_STRING_MAP__KEY = eINSTANCE.getStringToStringMap_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_STRING_MAP__VALUE = eINSTANCE.getStringToStringMap_Value();

		/**
		 * The meta object literal for the '<em>Visitor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.doorsdb.DoorsDBVisitor
		 * @see de.jpwinkler.daf.doorsdb.model.impl.DoorsDBPackageImpl#getDoorsDBVisitor()
		 * @generated
		 */
		EDataType DOORS_DB_VISITOR = eINSTANCE.getDoorsDBVisitor();

	}

} //DoorsDBPackage
