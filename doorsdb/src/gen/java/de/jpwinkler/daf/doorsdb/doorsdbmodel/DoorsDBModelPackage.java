/**
 */
package de.jpwinkler.daf.doorsdb.doorsdbmodel;

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
 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelFactory
 * @model kind="package"
 * @generated
 */
public interface DoorsDBModelPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "doorsdbmodel";

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
    String eNS_PREFIX = "doorsdbmodel";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    DoorsDBModelPackage eINSTANCE = de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl.init();

    /**
     * The meta object id for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBItemImpl <em>DB Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBItemImpl
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDBItem()
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
     * The meta object id for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBFolderImpl <em>DB Folder</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBFolderImpl
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDBFolder()
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
     * The meta object id for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBModuleImpl <em>DB Module</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBModuleImpl
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDBModule()
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
     * The number of operations of the '<em>DB Module</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DB_MODULE_OPERATION_COUNT = DB_ITEM_OPERATION_COUNT + 1;

    /**
     * The meta object id for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBVersionImpl <em>DB Version</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBVersionImpl
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDBVersion()
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
     * The feature id for the '<em><b>Csv Location</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DB_VERSION__CSV_LOCATION = 1;

    /**
     * The feature id for the '<em><b>Date</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DB_VERSION__DATE = 2;

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
     * The meta object id for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBImpl <em>Doors DB</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBImpl
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDoorsDB()
     * @generated
     */
    int DOORS_DB = 4;

    /**
     * The feature id for the '<em><b>Db Location</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_DB__DB_LOCATION = 0;

    /**
     * The feature id for the '<em><b>Tags</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_DB__TAGS = 1;

    /**
     * The feature id for the '<em><b>Root</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_DB__ROOT = 2;

    /**
     * The number of structural features of the '<em>Doors DB</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_DB_FEATURE_COUNT = 3;

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
     * The meta object id for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBTagImpl <em>DB Tag</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBTagImpl
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDBTag()
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
     * The meta object id for the '<em>Doors DB Visitor</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.doors_db.util.DoorsDBVisitor
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDoorsDBVisitor()
     * @generated
     */
    int DOORS_DB_VISITOR = 6;


    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder <em>DB Folder</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>DB Folder</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder
     * @generated
     */
    EClass getDBFolder();

    /**
     * Returns the meta object for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder#getFolder(java.lang.String) <em>Get Folder</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Folder</em>' operation.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder#getFolder(java.lang.String)
     * @generated
     */
    EOperation getDBFolder__GetFolder__String();

    /**
     * Returns the meta object for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder#getModule(java.lang.String) <em>Get Module</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Module</em>' operation.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder#getModule(java.lang.String)
     * @generated
     */
    EOperation getDBFolder__GetModule__String();

    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule <em>DB Module</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>DB Module</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule
     * @generated
     */
    EClass getDBModule();

    /**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule#getVersions <em>Versions</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Versions</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule#getVersions()
     * @see #getDBModule()
     * @generated
     */
    EReference getDBModule_Versions();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule#getUrl <em>Url</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Url</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule#getUrl()
     * @see #getDBModule()
     * @generated
     */
    EAttribute getDBModule_Url();

    /**
     * Returns the meta object for the reference list '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule#getTags <em>Tags</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Tags</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule#getTags()
     * @see #getDBModule()
     * @generated
     */
    EReference getDBModule_Tags();

    /**
     * Returns the meta object for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule#getLatestVersion() <em>Get Latest Version</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Latest Version</em>' operation.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule#getLatestVersion()
     * @generated
     */
    EOperation getDBModule__GetLatestVersion();

    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion <em>DB Version</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>DB Version</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion
     * @generated
     */
    EClass getDBVersion();

    /**
     * Returns the meta object for the container reference '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion#getModule <em>Module</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Module</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion#getModule()
     * @see #getDBVersion()
     * @generated
     */
    EReference getDBVersion_Module();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion#getCsvLocation <em>Csv Location</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Csv Location</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion#getCsvLocation()
     * @see #getDBVersion()
     * @generated
     */
    EAttribute getDBVersion_CsvLocation();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion#getDate <em>Date</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Date</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion#getDate()
     * @see #getDBVersion()
     * @generated
     */
    EAttribute getDBVersion_Date();

    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB <em>Doors DB</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Doors DB</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB
     * @generated
     */
    EClass getDoorsDB();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getDbLocation <em>Db Location</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Db Location</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getDbLocation()
     * @see #getDoorsDB()
     * @generated
     */
    EAttribute getDoorsDB_DbLocation();

    /**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getTags <em>Tags</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Tags</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getTags()
     * @see #getDoorsDB()
     * @generated
     */
    EReference getDoorsDB_Tags();

    /**
     * Returns the meta object for the containment reference '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getRoot <em>Root</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Root</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getRoot()
     * @see #getDoorsDB()
     * @generated
     */
    EReference getDoorsDB_Root();

    /**
     * Returns the meta object for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#accept(de.jpwinkler.doors_db.util.DoorsDBVisitor) <em>Accept</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Accept</em>' operation.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#accept(de.jpwinkler.doors_db.util.DoorsDBVisitor)
     * @generated
     */
    EOperation getDoorsDB__Accept__DoorsDBVisitor();

    /**
     * Returns the meta object for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getTag(java.lang.String) <em>Get Tag</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Tag</em>' operation.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getTag(java.lang.String)
     * @generated
     */
    EOperation getDoorsDB__GetTag__String();

    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag <em>DB Tag</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>DB Tag</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag
     * @generated
     */
    EClass getDBTag();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag#getName()
     * @see #getDBTag()
     * @generated
     */
    EAttribute getDBTag_Name();

    /**
     * Returns the meta object for the reference list '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag#getModules <em>Modules</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Modules</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag#getModules()
     * @see #getDBTag()
     * @generated
     */
    EReference getDBTag_Modules();

    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem <em>DB Item</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>DB Item</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem
     * @generated
     */
    EClass getDBItem();

    /**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem#getChildren()
     * @see #getDBItem()
     * @generated
     */
    EReference getDBItem_Children();

    /**
     * Returns the meta object for the container reference '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem#getParent()
     * @see #getDBItem()
     * @generated
     */
    EReference getDBItem_Parent();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem#getName()
     * @see #getDBItem()
     * @generated
     */
    EAttribute getDBItem_Name();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem#getFullName <em>Full Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Full Name</em>'.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem#getFullName()
     * @see #getDBItem()
     * @generated
     */
    EAttribute getDBItem_FullName();

    /**
     * Returns the meta object for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem#accept(de.jpwinkler.doors_db.util.DoorsDBVisitor) <em>Accept</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Accept</em>' operation.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem#accept(de.jpwinkler.doors_db.util.DoorsDBVisitor)
     * @generated
     */
    EOperation getDBItem__Accept__DoorsDBVisitor();

    /**
     * Returns the meta object for data type '{@link de.jpwinkler.doors_db.util.DoorsDBVisitor <em>Doors DB Visitor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Doors DB Visitor</em>'.
     * @see de.jpwinkler.doors_db.util.DoorsDBVisitor
     * @model instanceClass="de.jpwinkler.doors_db.util.DoorsDBVisitor"
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
    DoorsDBModelFactory getDoorsDBModelFactory();

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
         * The meta object literal for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBFolderImpl <em>DB Folder</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBFolderImpl
         * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDBFolder()
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
         * The meta object literal for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBModuleImpl <em>DB Module</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBModuleImpl
         * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDBModule()
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
         * The meta object literal for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBVersionImpl <em>DB Version</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBVersionImpl
         * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDBVersion()
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
         * The meta object literal for the '<em><b>Csv Location</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DB_VERSION__CSV_LOCATION = eINSTANCE.getDBVersion_CsvLocation();

        /**
         * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DB_VERSION__DATE = eINSTANCE.getDBVersion_Date();

        /**
         * The meta object literal for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBImpl <em>Doors DB</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBImpl
         * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDoorsDB()
         * @generated
         */
        EClass DOORS_DB = eINSTANCE.getDoorsDB();

        /**
         * The meta object literal for the '<em><b>Db Location</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOORS_DB__DB_LOCATION = eINSTANCE.getDoorsDB_DbLocation();

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
         * The meta object literal for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBTagImpl <em>DB Tag</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBTagImpl
         * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDBTag()
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
         * The meta object literal for the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBItemImpl <em>DB Item</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBItemImpl
         * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDBItem()
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
         * The meta object literal for the '<em>Doors DB Visitor</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.doors_db.util.DoorsDBVisitor
         * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBModelPackageImpl#getDoorsDBVisitor()
         * @generated
         */
        EDataType DOORS_DB_VISITOR = eINSTANCE.getDoorsDBVisitor();

    }

} //DoorsDBModelPackage
