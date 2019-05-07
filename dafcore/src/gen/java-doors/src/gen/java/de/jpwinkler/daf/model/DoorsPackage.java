/**
 */
package de.jpwinkler.daf.model;

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
 * @see de.jpwinkler.daf.model.DoorsFactory
 * @model kind="package"
 * @generated
 */
public interface DoorsPackage extends EPackage {
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
	String eNS_URI = "de.jpwinkler.daf.model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Doors";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DoorsPackage eINSTANCE = de.jpwinkler.daf.model.impl.DoorsPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.model.impl.DoorsTreeNodeImpl <em>Tree Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.model.impl.DoorsTreeNodeImpl
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsTreeNode()
	 * @generated
	 */
	int DOORS_TREE_NODE = 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE__CHILDREN = 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE__PARENT = 1;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE__ATTRIBUTES = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE__NAME = 3;

	/**
	 * The feature id for the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE__FULL_NAME = 4;

	/**
	 * The feature id for the '<em><b>Full Name Segments</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE__FULL_NAME_SEGMENTS = 5;

	/**
	 * The number of structural features of the '<em>Tree Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE_FEATURE_COUNT = 6;

	/**
	 * The operation id for the '<em>Accept</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE___ACCEPT__DOORSTREENODEVISITOR = 0;

	/**
	 * The operation id for the '<em>Has Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE___HAS_TAG__STRING = 1;

	/**
	 * The operation id for the '<em>Has Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE___HAS_TAG__PATTERN = 2;

	/**
	 * The operation id for the '<em>Can Copy From</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE___CAN_COPY_FROM__DOORSTREENODE = 3;

	/**
	 * The operation id for the '<em>Copy From</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE___COPY_FROM__DOORSTREENODE_DOORSTREENODE = 4;

	/**
	 * The operation id for the '<em>Get Child</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE___GET_CHILD__STRING = 5;

	/**
	 * The number of operations of the '<em>Tree Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE_OPERATION_COUNT = 6;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.model.impl.DoorsDatabaseImpl <em>Database</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.model.impl.DoorsDatabaseImpl
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsDatabase()
	 * @generated
	 */
	int DOORS_DATABASE = 1;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_DATABASE__ROOT = 0;

	/**
	 * The number of structural features of the '<em>Database</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_DATABASE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Database</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_DATABASE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.model.impl.DoorsModuleVersionImpl <em>Module Version</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.model.impl.DoorsModuleVersionImpl
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsModuleVersion()
	 * @generated
	 */
	int DOORS_MODULE_VERSION = 2;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE_VERSION__MODULE = 0;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE_VERSION__DATE = 1;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE_VERSION__ATTRIBUTES = 2;

	/**
	 * The feature id for the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE_VERSION__FULL_NAME = 3;

	/**
	 * The number of structural features of the '<em>Module Version</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE_VERSION_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Module Version</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE_VERSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.model.impl.DoorsModuleImpl <em>Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.model.impl.DoorsModuleImpl
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsModule()
	 * @generated
	 */
	int DOORS_MODULE = 3;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE__CHILDREN = DOORS_TREE_NODE__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE__PARENT = DOORS_TREE_NODE__PARENT;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE__ATTRIBUTES = DOORS_TREE_NODE__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE__NAME = DOORS_TREE_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE__FULL_NAME = DOORS_TREE_NODE__FULL_NAME;

	/**
	 * The feature id for the '<em><b>Full Name Segments</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE__FULL_NAME_SEGMENTS = DOORS_TREE_NODE__FULL_NAME_SEGMENTS;

	/**
	 * The feature id for the '<em><b>View</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE__VIEW = DOORS_TREE_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Versions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE__VERSIONS = DOORS_TREE_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE_FEATURE_COUNT = DOORS_TREE_NODE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Accept</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___ACCEPT__DOORSTREENODEVISITOR = DOORS_TREE_NODE___ACCEPT__DOORSTREENODEVISITOR;

	/**
	 * The operation id for the '<em>Has Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___HAS_TAG__STRING = DOORS_TREE_NODE___HAS_TAG__STRING;

	/**
	 * The operation id for the '<em>Has Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___HAS_TAG__PATTERN = DOORS_TREE_NODE___HAS_TAG__PATTERN;

	/**
	 * The operation id for the '<em>Can Copy From</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___CAN_COPY_FROM__DOORSTREENODE = DOORS_TREE_NODE___CAN_COPY_FROM__DOORSTREENODE;

	/**
	 * The operation id for the '<em>Copy From</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___COPY_FROM__DOORSTREENODE_DOORSTREENODE = DOORS_TREE_NODE___COPY_FROM__DOORSTREENODE_DOORSTREENODE;

	/**
	 * The operation id for the '<em>Get Child</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___GET_CHILD__STRING = DOORS_TREE_NODE___GET_CHILD__STRING;

	/**
	 * The operation id for the '<em>Find Object</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___FIND_OBJECT__STRING = DOORS_TREE_NODE_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Object Attributes</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___GET_OBJECT_ATTRIBUTES = DOORS_TREE_NODE_OPERATION_COUNT + 1;

	/**
	 * The operation id for the '<em>Set Object Attributes</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___SET_OBJECT_ATTRIBUTES__COLLECTION = DOORS_TREE_NODE_OPERATION_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Latest Version</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___GET_LATEST_VERSION = DOORS_TREE_NODE_OPERATION_COUNT + 3;

	/**
	 * The number of operations of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE_OPERATION_COUNT = DOORS_TREE_NODE_OPERATION_COUNT + 4;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.model.impl.DoorsObjectImpl <em>Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.model.impl.DoorsObjectImpl
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsObject()
	 * @generated
	 */
	int DOORS_OBJECT = 4;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__CHILDREN = DOORS_TREE_NODE__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__PARENT = DOORS_TREE_NODE__PARENT;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__ATTRIBUTES = DOORS_TREE_NODE__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__NAME = DOORS_TREE_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__FULL_NAME = DOORS_TREE_NODE__FULL_NAME;

	/**
	 * The feature id for the '<em><b>Full Name Segments</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__FULL_NAME_SEGMENTS = DOORS_TREE_NODE__FULL_NAME_SEGMENTS;

	/**
	 * The feature id for the '<em><b>Object Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__OBJECT_IDENTIFIER = DOORS_TREE_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Object Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__OBJECT_LEVEL = DOORS_TREE_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Object Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__OBJECT_NUMBER = DOORS_TREE_NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Absolute Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__ABSOLUTE_NUMBER = DOORS_TREE_NODE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Object Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__OBJECT_TEXT = DOORS_TREE_NODE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Object Short Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__OBJECT_SHORT_TEXT = DOORS_TREE_NODE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Object Heading</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__OBJECT_HEADING = DOORS_TREE_NODE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__TEXT = DOORS_TREE_NODE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Outgoing Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__OUTGOING_LINKS = DOORS_TREE_NODE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Incoming Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__INCOMING_LINKS = DOORS_TREE_NODE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Module</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT__MODULE = DOORS_TREE_NODE_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT_FEATURE_COUNT = DOORS_TREE_NODE_FEATURE_COUNT + 11;

	/**
	 * The operation id for the '<em>Accept</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___ACCEPT__DOORSTREENODEVISITOR = DOORS_TREE_NODE___ACCEPT__DOORSTREENODEVISITOR;

	/**
	 * The operation id for the '<em>Has Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___HAS_TAG__STRING = DOORS_TREE_NODE___HAS_TAG__STRING;

	/**
	 * The operation id for the '<em>Has Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___HAS_TAG__PATTERN = DOORS_TREE_NODE___HAS_TAG__PATTERN;

	/**
	 * The operation id for the '<em>Can Copy From</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___CAN_COPY_FROM__DOORSTREENODE = DOORS_TREE_NODE___CAN_COPY_FROM__DOORSTREENODE;

	/**
	 * The operation id for the '<em>Copy From</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___COPY_FROM__DOORSTREENODE_DOORSTREENODE = DOORS_TREE_NODE___COPY_FROM__DOORSTREENODE_DOORSTREENODE;

	/**
	 * The operation id for the '<em>Get Child</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___GET_CHILD__STRING = DOORS_TREE_NODE___GET_CHILD__STRING;

	/**
	 * The operation id for the '<em>Is Heading</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___IS_HEADING = DOORS_TREE_NODE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT_OPERATION_COUNT = DOORS_TREE_NODE_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.model.impl.AttributeMapImpl <em>Attribute Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.model.impl.AttributeMapImpl
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getAttributeMap()
	 * @generated
	 */
	int ATTRIBUTE_MAP = 5;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_MAP__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_MAP__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Attribute Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_MAP_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Attribute Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_MAP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.model.impl.LinkImpl <em>Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.model.impl.LinkImpl
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getLink()
	 * @generated
	 */
	int LINK = 6;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__SOURCE = 0;

	/**
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.model.impl.ResolvedLinkImpl <em>Resolved Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.model.impl.ResolvedLinkImpl
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getResolvedLink()
	 * @generated
	 */
	int RESOLVED_LINK = 7;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_LINK__SOURCE = LINK__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_LINK__TARGET = LINK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Resolved Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_LINK_FEATURE_COUNT = LINK_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Resolved Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_LINK_OPERATION_COUNT = LINK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.model.impl.UnresolvedLinkImpl <em>Unresolved Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.model.impl.UnresolvedLinkImpl
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getUnresolvedLink()
	 * @generated
	 */
	int UNRESOLVED_LINK = 8;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_LINK__SOURCE = LINK__SOURCE;

	/**
	 * The feature id for the '<em><b>Target Module</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_LINK__TARGET_MODULE = LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Object</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_LINK__TARGET_OBJECT = LINK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Unresolved Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_LINK_FEATURE_COUNT = LINK_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Unresolved Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_LINK_OPERATION_COUNT = LINK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '<em>Tree Node Visitor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.model.DoorsTreeNodeVisitor
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsTreeNodeVisitor()
	 * @generated
	 */
	int DOORS_TREE_NODE_VISITOR = 9;


	/**
	 * The meta object id for the '<em>Pattern</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.regex.Pattern
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getPattern()
	 * @generated
	 */
	int PATTERN = 10;


	/**
	 * The meta object id for the '<em>Collection</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Collection
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getCollection()
	 * @generated
	 */
	int COLLECTION = 11;


	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.model.DoorsTreeNode <em>Tree Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tree Node</em>'.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode
	 * @generated
	 */
	EClass getDoorsTreeNode();

	/**
	 * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.model.DoorsTreeNode#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#getChildren()
	 * @see #getDoorsTreeNode()
	 * @generated
	 */
	EReference getDoorsTreeNode_Children();

	/**
	 * Returns the meta object for the container reference '{@link de.jpwinkler.daf.model.DoorsTreeNode#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#getParent()
	 * @see #getDoorsTreeNode()
	 * @generated
	 */
	EReference getDoorsTreeNode_Parent();

	/**
	 * Returns the meta object for the map '{@link de.jpwinkler.daf.model.DoorsTreeNode#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Attributes</em>'.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#getAttributes()
	 * @see #getDoorsTreeNode()
	 * @generated
	 */
	EReference getDoorsTreeNode_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.DoorsTreeNode#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#getName()
	 * @see #getDoorsTreeNode()
	 * @generated
	 */
	EAttribute getDoorsTreeNode_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.DoorsTreeNode#getFullName <em>Full Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Full Name</em>'.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#getFullName()
	 * @see #getDoorsTreeNode()
	 * @generated
	 */
	EAttribute getDoorsTreeNode_FullName();

	/**
	 * Returns the meta object for the attribute list '{@link de.jpwinkler.daf.model.DoorsTreeNode#getFullNameSegments <em>Full Name Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Full Name Segments</em>'.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#getFullNameSegments()
	 * @see #getDoorsTreeNode()
	 * @generated
	 */
	EAttribute getDoorsTreeNode_FullNameSegments();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsTreeNode#accept(de.jpwinkler.daf.model.DoorsTreeNodeVisitor) <em>Accept</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Accept</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#accept(de.jpwinkler.daf.model.DoorsTreeNodeVisitor)
	 * @generated
	 */
	EOperation getDoorsTreeNode__Accept__DoorsTreeNodeVisitor();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsTreeNode#hasTag(java.lang.String) <em>Has Tag</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Has Tag</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#hasTag(java.lang.String)
	 * @generated
	 */
	EOperation getDoorsTreeNode__HasTag__String();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsTreeNode#hasTag(java.util.regex.Pattern) <em>Has Tag</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Has Tag</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#hasTag(java.util.regex.Pattern)
	 * @generated
	 */
	EOperation getDoorsTreeNode__HasTag__Pattern();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsTreeNode#canCopyFrom(de.jpwinkler.daf.model.DoorsTreeNode) <em>Can Copy From</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Can Copy From</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#canCopyFrom(de.jpwinkler.daf.model.DoorsTreeNode)
	 * @generated
	 */
	EOperation getDoorsTreeNode__CanCopyFrom__DoorsTreeNode();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsTreeNode#copyFrom(de.jpwinkler.daf.model.DoorsTreeNode, de.jpwinkler.daf.model.DoorsTreeNode) <em>Copy From</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Copy From</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#copyFrom(de.jpwinkler.daf.model.DoorsTreeNode, de.jpwinkler.daf.model.DoorsTreeNode)
	 * @generated
	 */
	EOperation getDoorsTreeNode__CopyFrom__DoorsTreeNode_DoorsTreeNode();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsTreeNode#getChild(java.lang.String) <em>Get Child</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Child</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#getChild(java.lang.String)
	 * @generated
	 */
	EOperation getDoorsTreeNode__GetChild__String();

	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.model.DoorsDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Database</em>'.
	 * @see de.jpwinkler.daf.model.DoorsDatabase
	 * @generated
	 */
	EClass getDoorsDatabase();

	/**
	 * Returns the meta object for the containment reference '{@link de.jpwinkler.daf.model.DoorsDatabase#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root</em>'.
	 * @see de.jpwinkler.daf.model.DoorsDatabase#getRoot()
	 * @see #getDoorsDatabase()
	 * @generated
	 */
	EReference getDoorsDatabase_Root();

	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.model.DoorsModuleVersion <em>Module Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Version</em>'.
	 * @see de.jpwinkler.daf.model.DoorsModuleVersion
	 * @generated
	 */
	EClass getDoorsModuleVersion();

	/**
	 * Returns the meta object for the container reference '{@link de.jpwinkler.daf.model.DoorsModuleVersion#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Module</em>'.
	 * @see de.jpwinkler.daf.model.DoorsModuleVersion#getModule()
	 * @see #getDoorsModuleVersion()
	 * @generated
	 */
	EReference getDoorsModuleVersion_Module();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.DoorsModuleVersion#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see de.jpwinkler.daf.model.DoorsModuleVersion#getDate()
	 * @see #getDoorsModuleVersion()
	 * @generated
	 */
	EAttribute getDoorsModuleVersion_Date();

	/**
	 * Returns the meta object for the map '{@link de.jpwinkler.daf.model.DoorsModuleVersion#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Attributes</em>'.
	 * @see de.jpwinkler.daf.model.DoorsModuleVersion#getAttributes()
	 * @see #getDoorsModuleVersion()
	 * @generated
	 */
	EReference getDoorsModuleVersion_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.DoorsModuleVersion#getFullName <em>Full Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Full Name</em>'.
	 * @see de.jpwinkler.daf.model.DoorsModuleVersion#getFullName()
	 * @see #getDoorsModuleVersion()
	 * @generated
	 */
	EAttribute getDoorsModuleVersion_FullName();

	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.model.DoorsModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module</em>'.
	 * @see de.jpwinkler.daf.model.DoorsModule
	 * @generated
	 */
	EClass getDoorsModule();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.DoorsModule#getView <em>View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>View</em>'.
	 * @see de.jpwinkler.daf.model.DoorsModule#getView()
	 * @see #getDoorsModule()
	 * @generated
	 */
	EAttribute getDoorsModule_View();

	/**
	 * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.model.DoorsModule#getVersions <em>Versions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Versions</em>'.
	 * @see de.jpwinkler.daf.model.DoorsModule#getVersions()
	 * @see #getDoorsModule()
	 * @generated
	 */
	EReference getDoorsModule_Versions();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsModule#findObject(java.lang.String) <em>Find Object</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Find Object</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsModule#findObject(java.lang.String)
	 * @generated
	 */
	EOperation getDoorsModule__FindObject__String();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsModule#getObjectAttributes() <em>Get Object Attributes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Object Attributes</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsModule#getObjectAttributes()
	 * @generated
	 */
	EOperation getDoorsModule__GetObjectAttributes();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsModule#setObjectAttributes(java.util.Collection) <em>Set Object Attributes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Set Object Attributes</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsModule#setObjectAttributes(java.util.Collection)
	 * @generated
	 */
	EOperation getDoorsModule__SetObjectAttributes__Collection();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsModule#getLatestVersion() <em>Get Latest Version</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Latest Version</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsModule#getLatestVersion()
	 * @generated
	 */
	EOperation getDoorsModule__GetLatestVersion();

	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.model.DoorsObject <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object</em>'.
	 * @see de.jpwinkler.daf.model.DoorsObject
	 * @generated
	 */
	EClass getDoorsObject();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.DoorsObject#getObjectIdentifier <em>Object Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Identifier</em>'.
	 * @see de.jpwinkler.daf.model.DoorsObject#getObjectIdentifier()
	 * @see #getDoorsObject()
	 * @generated
	 */
	EAttribute getDoorsObject_ObjectIdentifier();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.DoorsObject#getObjectLevel <em>Object Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Level</em>'.
	 * @see de.jpwinkler.daf.model.DoorsObject#getObjectLevel()
	 * @see #getDoorsObject()
	 * @generated
	 */
	EAttribute getDoorsObject_ObjectLevel();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.DoorsObject#getObjectNumber <em>Object Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Number</em>'.
	 * @see de.jpwinkler.daf.model.DoorsObject#getObjectNumber()
	 * @see #getDoorsObject()
	 * @generated
	 */
	EAttribute getDoorsObject_ObjectNumber();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.DoorsObject#getAbsoluteNumber <em>Absolute Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Absolute Number</em>'.
	 * @see de.jpwinkler.daf.model.DoorsObject#getAbsoluteNumber()
	 * @see #getDoorsObject()
	 * @generated
	 */
	EAttribute getDoorsObject_AbsoluteNumber();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.DoorsObject#getObjectText <em>Object Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Text</em>'.
	 * @see de.jpwinkler.daf.model.DoorsObject#getObjectText()
	 * @see #getDoorsObject()
	 * @generated
	 */
	EAttribute getDoorsObject_ObjectText();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.DoorsObject#getObjectShortText <em>Object Short Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Short Text</em>'.
	 * @see de.jpwinkler.daf.model.DoorsObject#getObjectShortText()
	 * @see #getDoorsObject()
	 * @generated
	 */
	EAttribute getDoorsObject_ObjectShortText();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.DoorsObject#getObjectHeading <em>Object Heading</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Heading</em>'.
	 * @see de.jpwinkler.daf.model.DoorsObject#getObjectHeading()
	 * @see #getDoorsObject()
	 * @generated
	 */
	EAttribute getDoorsObject_ObjectHeading();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.DoorsObject#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see de.jpwinkler.daf.model.DoorsObject#getText()
	 * @see #getDoorsObject()
	 * @generated
	 */
	EAttribute getDoorsObject_Text();

	/**
	 * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.model.DoorsObject#getOutgoingLinks <em>Outgoing Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outgoing Links</em>'.
	 * @see de.jpwinkler.daf.model.DoorsObject#getOutgoingLinks()
	 * @see #getDoorsObject()
	 * @generated
	 */
	EReference getDoorsObject_OutgoingLinks();

	/**
	 * Returns the meta object for the reference list '{@link de.jpwinkler.daf.model.DoorsObject#getIncomingLinks <em>Incoming Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Links</em>'.
	 * @see de.jpwinkler.daf.model.DoorsObject#getIncomingLinks()
	 * @see #getDoorsObject()
	 * @generated
	 */
	EReference getDoorsObject_IncomingLinks();

	/**
	 * Returns the meta object for the reference '{@link de.jpwinkler.daf.model.DoorsObject#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Module</em>'.
	 * @see de.jpwinkler.daf.model.DoorsObject#getModule()
	 * @see #getDoorsObject()
	 * @generated
	 */
	EReference getDoorsObject_Module();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsObject#isHeading() <em>Is Heading</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Is Heading</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsObject#isHeading()
	 * @generated
	 */
	EOperation getDoorsObject__IsHeading();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Attribute Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Map</em>'.
	 * @see java.util.Map.Entry
	 * @model keyUnique="false" keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueUnique="false" valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getAttributeMap();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAttributeMap()
	 * @generated
	 */
	EAttribute getAttributeMap_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAttributeMap()
	 * @generated
	 */
	EAttribute getAttributeMap_Value();

	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.model.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link</em>'.
	 * @see de.jpwinkler.daf.model.Link
	 * @generated
	 */
	EClass getLink();

	/**
	 * Returns the meta object for the container reference '{@link de.jpwinkler.daf.model.Link#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see de.jpwinkler.daf.model.Link#getSource()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Source();

	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.model.ResolvedLink <em>Resolved Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resolved Link</em>'.
	 * @see de.jpwinkler.daf.model.ResolvedLink
	 * @generated
	 */
	EClass getResolvedLink();

	/**
	 * Returns the meta object for the reference '{@link de.jpwinkler.daf.model.ResolvedLink#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see de.jpwinkler.daf.model.ResolvedLink#getTarget()
	 * @see #getResolvedLink()
	 * @generated
	 */
	EReference getResolvedLink_Target();

	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.model.UnresolvedLink <em>Unresolved Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unresolved Link</em>'.
	 * @see de.jpwinkler.daf.model.UnresolvedLink
	 * @generated
	 */
	EClass getUnresolvedLink();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.UnresolvedLink#getTargetModule <em>Target Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Module</em>'.
	 * @see de.jpwinkler.daf.model.UnresolvedLink#getTargetModule()
	 * @see #getUnresolvedLink()
	 * @generated
	 */
	EAttribute getUnresolvedLink_TargetModule();

	/**
	 * Returns the meta object for the attribute '{@link de.jpwinkler.daf.model.UnresolvedLink#getTargetObject <em>Target Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Object</em>'.
	 * @see de.jpwinkler.daf.model.UnresolvedLink#getTargetObject()
	 * @see #getUnresolvedLink()
	 * @generated
	 */
	EAttribute getUnresolvedLink_TargetObject();

	/**
	 * Returns the meta object for data type '{@link de.jpwinkler.daf.model.DoorsTreeNodeVisitor <em>Tree Node Visitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Tree Node Visitor</em>'.
	 * @see de.jpwinkler.daf.model.DoorsTreeNodeVisitor
	 * @model instanceClass="de.jpwinkler.daf.model.DoorsTreeNodeVisitor"
	 * @generated
	 */
	EDataType getDoorsTreeNodeVisitor();

	/**
	 * Returns the meta object for data type '{@link java.util.regex.Pattern <em>Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Pattern</em>'.
	 * @see java.util.regex.Pattern
	 * @model instanceClass="java.util.regex.Pattern"
	 * @generated
	 */
	EDataType getPattern();

	/**
	 * Returns the meta object for data type '{@link java.util.Collection <em>Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Collection</em>'.
	 * @see java.util.Collection
	 * @model instanceClass="java.util.Collection"
	 * @generated
	 */
	EDataType getCollection();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DoorsFactory getDoorsFactory();

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
		 * The meta object literal for the '{@link de.jpwinkler.daf.model.impl.DoorsTreeNodeImpl <em>Tree Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.model.impl.DoorsTreeNodeImpl
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsTreeNode()
		 * @generated
		 */
		EClass DOORS_TREE_NODE = eINSTANCE.getDoorsTreeNode();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOORS_TREE_NODE__CHILDREN = eINSTANCE.getDoorsTreeNode_Children();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOORS_TREE_NODE__PARENT = eINSTANCE.getDoorsTreeNode_Parent();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOORS_TREE_NODE__ATTRIBUTES = eINSTANCE.getDoorsTreeNode_Attributes();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_TREE_NODE__NAME = eINSTANCE.getDoorsTreeNode_Name();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_TREE_NODE__FULL_NAME = eINSTANCE.getDoorsTreeNode_FullName();

		/**
		 * The meta object literal for the '<em><b>Full Name Segments</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_TREE_NODE__FULL_NAME_SEGMENTS = eINSTANCE.getDoorsTreeNode_FullNameSegments();

		/**
		 * The meta object literal for the '<em><b>Accept</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_TREE_NODE___ACCEPT__DOORSTREENODEVISITOR = eINSTANCE.getDoorsTreeNode__Accept__DoorsTreeNodeVisitor();

		/**
		 * The meta object literal for the '<em><b>Has Tag</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_TREE_NODE___HAS_TAG__STRING = eINSTANCE.getDoorsTreeNode__HasTag__String();

		/**
		 * The meta object literal for the '<em><b>Has Tag</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_TREE_NODE___HAS_TAG__PATTERN = eINSTANCE.getDoorsTreeNode__HasTag__Pattern();

		/**
		 * The meta object literal for the '<em><b>Can Copy From</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_TREE_NODE___CAN_COPY_FROM__DOORSTREENODE = eINSTANCE.getDoorsTreeNode__CanCopyFrom__DoorsTreeNode();

		/**
		 * The meta object literal for the '<em><b>Copy From</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_TREE_NODE___COPY_FROM__DOORSTREENODE_DOORSTREENODE = eINSTANCE.getDoorsTreeNode__CopyFrom__DoorsTreeNode_DoorsTreeNode();

		/**
		 * The meta object literal for the '<em><b>Get Child</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_TREE_NODE___GET_CHILD__STRING = eINSTANCE.getDoorsTreeNode__GetChild__String();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.model.impl.DoorsDatabaseImpl <em>Database</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.model.impl.DoorsDatabaseImpl
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsDatabase()
		 * @generated
		 */
		EClass DOORS_DATABASE = eINSTANCE.getDoorsDatabase();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOORS_DATABASE__ROOT = eINSTANCE.getDoorsDatabase_Root();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.model.impl.DoorsModuleVersionImpl <em>Module Version</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.model.impl.DoorsModuleVersionImpl
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsModuleVersion()
		 * @generated
		 */
		EClass DOORS_MODULE_VERSION = eINSTANCE.getDoorsModuleVersion();

		/**
		 * The meta object literal for the '<em><b>Module</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOORS_MODULE_VERSION__MODULE = eINSTANCE.getDoorsModuleVersion_Module();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_MODULE_VERSION__DATE = eINSTANCE.getDoorsModuleVersion_Date();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOORS_MODULE_VERSION__ATTRIBUTES = eINSTANCE.getDoorsModuleVersion_Attributes();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_MODULE_VERSION__FULL_NAME = eINSTANCE.getDoorsModuleVersion_FullName();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.model.impl.DoorsModuleImpl <em>Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.model.impl.DoorsModuleImpl
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsModule()
		 * @generated
		 */
		EClass DOORS_MODULE = eINSTANCE.getDoorsModule();

		/**
		 * The meta object literal for the '<em><b>View</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_MODULE__VIEW = eINSTANCE.getDoorsModule_View();

		/**
		 * The meta object literal for the '<em><b>Versions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOORS_MODULE__VERSIONS = eINSTANCE.getDoorsModule_Versions();

		/**
		 * The meta object literal for the '<em><b>Find Object</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_MODULE___FIND_OBJECT__STRING = eINSTANCE.getDoorsModule__FindObject__String();

		/**
		 * The meta object literal for the '<em><b>Get Object Attributes</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_MODULE___GET_OBJECT_ATTRIBUTES = eINSTANCE.getDoorsModule__GetObjectAttributes();

		/**
		 * The meta object literal for the '<em><b>Set Object Attributes</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_MODULE___SET_OBJECT_ATTRIBUTES__COLLECTION = eINSTANCE.getDoorsModule__SetObjectAttributes__Collection();

		/**
		 * The meta object literal for the '<em><b>Get Latest Version</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_MODULE___GET_LATEST_VERSION = eINSTANCE.getDoorsModule__GetLatestVersion();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.model.impl.DoorsObjectImpl <em>Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.model.impl.DoorsObjectImpl
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsObject()
		 * @generated
		 */
		EClass DOORS_OBJECT = eINSTANCE.getDoorsObject();

		/**
		 * The meta object literal for the '<em><b>Object Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_OBJECT__OBJECT_IDENTIFIER = eINSTANCE.getDoorsObject_ObjectIdentifier();

		/**
		 * The meta object literal for the '<em><b>Object Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_OBJECT__OBJECT_LEVEL = eINSTANCE.getDoorsObject_ObjectLevel();

		/**
		 * The meta object literal for the '<em><b>Object Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_OBJECT__OBJECT_NUMBER = eINSTANCE.getDoorsObject_ObjectNumber();

		/**
		 * The meta object literal for the '<em><b>Absolute Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_OBJECT__ABSOLUTE_NUMBER = eINSTANCE.getDoorsObject_AbsoluteNumber();

		/**
		 * The meta object literal for the '<em><b>Object Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_OBJECT__OBJECT_TEXT = eINSTANCE.getDoorsObject_ObjectText();

		/**
		 * The meta object literal for the '<em><b>Object Short Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_OBJECT__OBJECT_SHORT_TEXT = eINSTANCE.getDoorsObject_ObjectShortText();

		/**
		 * The meta object literal for the '<em><b>Object Heading</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_OBJECT__OBJECT_HEADING = eINSTANCE.getDoorsObject_ObjectHeading();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOORS_OBJECT__TEXT = eINSTANCE.getDoorsObject_Text();

		/**
		 * The meta object literal for the '<em><b>Outgoing Links</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOORS_OBJECT__OUTGOING_LINKS = eINSTANCE.getDoorsObject_OutgoingLinks();

		/**
		 * The meta object literal for the '<em><b>Incoming Links</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOORS_OBJECT__INCOMING_LINKS = eINSTANCE.getDoorsObject_IncomingLinks();

		/**
		 * The meta object literal for the '<em><b>Module</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOORS_OBJECT__MODULE = eINSTANCE.getDoorsObject_Module();

		/**
		 * The meta object literal for the '<em><b>Is Heading</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_OBJECT___IS_HEADING = eINSTANCE.getDoorsObject__IsHeading();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.model.impl.AttributeMapImpl <em>Attribute Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.model.impl.AttributeMapImpl
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getAttributeMap()
		 * @generated
		 */
		EClass ATTRIBUTE_MAP = eINSTANCE.getAttributeMap();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_MAP__KEY = eINSTANCE.getAttributeMap_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_MAP__VALUE = eINSTANCE.getAttributeMap_Value();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.model.impl.LinkImpl <em>Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.model.impl.LinkImpl
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getLink()
		 * @generated
		 */
		EClass LINK = eINSTANCE.getLink();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__SOURCE = eINSTANCE.getLink_Source();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.model.impl.ResolvedLinkImpl <em>Resolved Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.model.impl.ResolvedLinkImpl
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getResolvedLink()
		 * @generated
		 */
		EClass RESOLVED_LINK = eINSTANCE.getResolvedLink();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOLVED_LINK__TARGET = eINSTANCE.getResolvedLink_Target();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.model.impl.UnresolvedLinkImpl <em>Unresolved Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.model.impl.UnresolvedLinkImpl
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getUnresolvedLink()
		 * @generated
		 */
		EClass UNRESOLVED_LINK = eINSTANCE.getUnresolvedLink();

		/**
		 * The meta object literal for the '<em><b>Target Module</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNRESOLVED_LINK__TARGET_MODULE = eINSTANCE.getUnresolvedLink_TargetModule();

		/**
		 * The meta object literal for the '<em><b>Target Object</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNRESOLVED_LINK__TARGET_OBJECT = eINSTANCE.getUnresolvedLink_TargetObject();

		/**
		 * The meta object literal for the '<em>Tree Node Visitor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.model.DoorsTreeNodeVisitor
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsTreeNodeVisitor()
		 * @generated
		 */
		EDataType DOORS_TREE_NODE_VISITOR = eINSTANCE.getDoorsTreeNodeVisitor();

		/**
		 * The meta object literal for the '<em>Pattern</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.regex.Pattern
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getPattern()
		 * @generated
		 */
		EDataType PATTERN = eINSTANCE.getPattern();

		/**
		 * The meta object literal for the '<em>Collection</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Collection
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getCollection()
		 * @generated
		 */
		EDataType COLLECTION = eINSTANCE.getCollection();

	}

} //DoorsPackage
