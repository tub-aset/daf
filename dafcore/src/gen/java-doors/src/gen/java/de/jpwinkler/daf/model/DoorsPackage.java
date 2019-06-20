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
	 * The operation id for the '<em>Get Tags</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE___GET_TAGS = 3;

	/**
	 * The operation id for the '<em>Set Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE___SET_TAG__STRING = 4;

	/**
	 * The operation id for the '<em>Remove Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE___REMOVE_TAG__STRING = 5;

	/**
	 * The operation id for the '<em>Remove Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE___REMOVE_TAG__PATTERN = 6;

	/**
	 * The operation id for the '<em>Can Copy From</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE___CAN_COPY_FROM__DOORSTREENODE = 7;

	/**
	 * The operation id for the '<em>Get Child</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE___GET_CHILD__STRING = 8;

	/**
	 * The operation id for the '<em>To String</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE___TO_STRING = 9;

	/**
	 * The number of operations of the '<em>Tree Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_TREE_NODE_OPERATION_COUNT = 10;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.model.impl.DoorsFolderImpl <em>Folder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.model.impl.DoorsFolderImpl
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsFolder()
	 * @generated
	 */
	int DOORS_FOLDER = 1;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER__CHILDREN = DOORS_TREE_NODE__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER__PARENT = DOORS_TREE_NODE__PARENT;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER__ATTRIBUTES = DOORS_TREE_NODE__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER__NAME = DOORS_TREE_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER__FULL_NAME = DOORS_TREE_NODE__FULL_NAME;

	/**
	 * The feature id for the '<em><b>Full Name Segments</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER__FULL_NAME_SEGMENTS = DOORS_TREE_NODE__FULL_NAME_SEGMENTS;

	/**
	 * The number of structural features of the '<em>Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER_FEATURE_COUNT = DOORS_TREE_NODE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Accept</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER___ACCEPT__DOORSTREENODEVISITOR = DOORS_TREE_NODE___ACCEPT__DOORSTREENODEVISITOR;

	/**
	 * The operation id for the '<em>Has Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER___HAS_TAG__STRING = DOORS_TREE_NODE___HAS_TAG__STRING;

	/**
	 * The operation id for the '<em>Has Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER___HAS_TAG__PATTERN = DOORS_TREE_NODE___HAS_TAG__PATTERN;

	/**
	 * The operation id for the '<em>Get Tags</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER___GET_TAGS = DOORS_TREE_NODE___GET_TAGS;

	/**
	 * The operation id for the '<em>Set Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER___SET_TAG__STRING = DOORS_TREE_NODE___SET_TAG__STRING;

	/**
	 * The operation id for the '<em>Remove Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER___REMOVE_TAG__STRING = DOORS_TREE_NODE___REMOVE_TAG__STRING;

	/**
	 * The operation id for the '<em>Remove Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER___REMOVE_TAG__PATTERN = DOORS_TREE_NODE___REMOVE_TAG__PATTERN;

	/**
	 * The operation id for the '<em>Can Copy From</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER___CAN_COPY_FROM__DOORSTREENODE = DOORS_TREE_NODE___CAN_COPY_FROM__DOORSTREENODE;

	/**
	 * The operation id for the '<em>Get Child</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER___GET_CHILD__STRING = DOORS_TREE_NODE___GET_CHILD__STRING;

	/**
	 * The operation id for the '<em>To String</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER___TO_STRING = DOORS_TREE_NODE___TO_STRING;

	/**
	 * The number of operations of the '<em>Folder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_FOLDER_OPERATION_COUNT = DOORS_TREE_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.model.impl.DoorsModuleImpl <em>Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.model.impl.DoorsModuleImpl
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsModule()
	 * @generated
	 */
	int DOORS_MODULE = 2;

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
	 * The number of structural features of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE_FEATURE_COUNT = DOORS_TREE_NODE_FEATURE_COUNT + 0;

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
	 * The operation id for the '<em>Get Tags</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___GET_TAGS = DOORS_TREE_NODE___GET_TAGS;

	/**
	 * The operation id for the '<em>Set Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___SET_TAG__STRING = DOORS_TREE_NODE___SET_TAG__STRING;

	/**
	 * The operation id for the '<em>Remove Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___REMOVE_TAG__STRING = DOORS_TREE_NODE___REMOVE_TAG__STRING;

	/**
	 * The operation id for the '<em>Remove Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___REMOVE_TAG__PATTERN = DOORS_TREE_NODE___REMOVE_TAG__PATTERN;

	/**
	 * The operation id for the '<em>Can Copy From</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___CAN_COPY_FROM__DOORSTREENODE = DOORS_TREE_NODE___CAN_COPY_FROM__DOORSTREENODE;

	/**
	 * The operation id for the '<em>Get Child</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___GET_CHILD__STRING = DOORS_TREE_NODE___GET_CHILD__STRING;

	/**
	 * The operation id for the '<em>To String</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___TO_STRING = DOORS_TREE_NODE___TO_STRING;

	/**
	 * The operation id for the '<em>Get View</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___GET_VIEW = DOORS_TREE_NODE_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Find Object</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___FIND_OBJECT__STRING = DOORS_TREE_NODE_OPERATION_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Object Attributes</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___GET_OBJECT_ATTRIBUTES = DOORS_TREE_NODE_OPERATION_COUNT + 2;

	/**
	 * The operation id for the '<em>Set Object Attributes</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_MODULE___SET_OBJECT_ATTRIBUTES__LIST = DOORS_TREE_NODE_OPERATION_COUNT + 3;

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
	int DOORS_OBJECT = 3;

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
	 * The number of structural features of the '<em>Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT_FEATURE_COUNT = DOORS_TREE_NODE_FEATURE_COUNT + 10;

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
	 * The operation id for the '<em>Get Tags</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___GET_TAGS = DOORS_TREE_NODE___GET_TAGS;

	/**
	 * The operation id for the '<em>Set Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___SET_TAG__STRING = DOORS_TREE_NODE___SET_TAG__STRING;

	/**
	 * The operation id for the '<em>Remove Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___REMOVE_TAG__STRING = DOORS_TREE_NODE___REMOVE_TAG__STRING;

	/**
	 * The operation id for the '<em>Remove Tag</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___REMOVE_TAG__PATTERN = DOORS_TREE_NODE___REMOVE_TAG__PATTERN;

	/**
	 * The operation id for the '<em>Can Copy From</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___CAN_COPY_FROM__DOORSTREENODE = DOORS_TREE_NODE___CAN_COPY_FROM__DOORSTREENODE;

	/**
	 * The operation id for the '<em>Get Child</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___GET_CHILD__STRING = DOORS_TREE_NODE___GET_CHILD__STRING;

	/**
	 * The operation id for the '<em>To String</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOORS_OBJECT___TO_STRING = DOORS_TREE_NODE___TO_STRING;

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
	int ATTRIBUTE_MAP = 4;

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
	int LINK = 5;

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
	 * The operation id for the '<em>Get Target Module</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK___GET_TARGET_MODULE = 0;

	/**
	 * The operation id for the '<em>Get Target Object</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK___GET_TARGET_OBJECT = 1;

	/**
	 * The number of operations of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.jpwinkler.daf.model.impl.ResolvedLinkImpl <em>Resolved Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.jpwinkler.daf.model.impl.ResolvedLinkImpl
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getResolvedLink()
	 * @generated
	 */
	int RESOLVED_LINK = 6;

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
	 * The operation id for the '<em>Get Target Module</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_LINK___GET_TARGET_MODULE = LINK___GET_TARGET_MODULE;

	/**
	 * The operation id for the '<em>Get Target Object</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVED_LINK___GET_TARGET_OBJECT = LINK___GET_TARGET_OBJECT;

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
	int UNRESOLVED_LINK = 7;

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
	 * The operation id for the '<em>Get Target Module</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_LINK___GET_TARGET_MODULE = LINK___GET_TARGET_MODULE;

	/**
	 * The operation id for the '<em>Get Target Object</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNRESOLVED_LINK___GET_TARGET_OBJECT = LINK___GET_TARGET_OBJECT;

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
	int DOORS_TREE_NODE_VISITOR = 8;

	/**
	 * The meta object id for the '<em>Pattern</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.regex.Pattern
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getPattern()
	 * @generated
	 */
	int PATTERN = 9;

	/**
	 * The meta object id for the '<em>Collection</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Collection
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getCollection()
	 * @generated
	 */
	int COLLECTION = 10;


	/**
	 * The meta object id for the '<em>Node Filter</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.function.Predicate
	 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getNodeFilter()
	 * @generated
	 */
	int NODE_FILTER = 11;


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
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsTreeNode#getTags() <em>Get Tags</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Tags</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#getTags()
	 * @generated
	 */
	EOperation getDoorsTreeNode__GetTags();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsTreeNode#setTag(java.lang.String) <em>Set Tag</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Set Tag</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#setTag(java.lang.String)
	 * @generated
	 */
	EOperation getDoorsTreeNode__SetTag__String();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsTreeNode#removeTag(java.lang.String) <em>Remove Tag</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Remove Tag</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#removeTag(java.lang.String)
	 * @generated
	 */
	EOperation getDoorsTreeNode__RemoveTag__String();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsTreeNode#removeTag(java.util.regex.Pattern) <em>Remove Tag</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Remove Tag</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#removeTag(java.util.regex.Pattern)
	 * @generated
	 */
	EOperation getDoorsTreeNode__RemoveTag__Pattern();

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
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsTreeNode#getChild(java.lang.String) <em>Get Child</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Child</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#getChild(java.lang.String)
	 * @generated
	 */
	EOperation getDoorsTreeNode__GetChild__String();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsTreeNode#toString() <em>To String</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>To String</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#toString()
	 * @generated
	 */
	EOperation getDoorsTreeNode__ToString();

	/**
	 * Returns the meta object for class '{@link de.jpwinkler.daf.model.DoorsFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Folder</em>'.
	 * @see de.jpwinkler.daf.model.DoorsFolder
	 * @generated
	 */
	EClass getDoorsFolder();

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
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsModule#getView() <em>Get View</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get View</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsModule#getView()
	 * @generated
	 */
	EOperation getDoorsModule__GetView();

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
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.DoorsModule#setObjectAttributes(java.util.List) <em>Set Object Attributes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Set Object Attributes</em>' operation.
	 * @see de.jpwinkler.daf.model.DoorsModule#setObjectAttributes(java.util.List)
	 * @generated
	 */
	EOperation getDoorsModule__SetObjectAttributes__List();

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
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.Link#getTargetModule() <em>Get Target Module</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Target Module</em>' operation.
	 * @see de.jpwinkler.daf.model.Link#getTargetModule()
	 * @generated
	 */
	EOperation getLink__GetTargetModule();

	/**
	 * Returns the meta object for the '{@link de.jpwinkler.daf.model.Link#getTargetObject() <em>Get Target Object</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Target Object</em>' operation.
	 * @see de.jpwinkler.daf.model.Link#getTargetObject()
	 * @generated
	 */
	EOperation getLink__GetTargetObject();

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
	 * Returns the meta object for data type '{@link java.util.function.Predicate <em>Node Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Node Filter</em>'.
	 * @see java.util.function.Predicate
	 * @model instanceClass="java.util.function.Predicate&lt;de.jpwinkler.daf.model.DoorsTreeNode&gt;"
	 * @generated
	 */
	EDataType getNodeFilter();

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
		 * The meta object literal for the '<em><b>Get Tags</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_TREE_NODE___GET_TAGS = eINSTANCE.getDoorsTreeNode__GetTags();

		/**
		 * The meta object literal for the '<em><b>Set Tag</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_TREE_NODE___SET_TAG__STRING = eINSTANCE.getDoorsTreeNode__SetTag__String();

		/**
		 * The meta object literal for the '<em><b>Remove Tag</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_TREE_NODE___REMOVE_TAG__STRING = eINSTANCE.getDoorsTreeNode__RemoveTag__String();

		/**
		 * The meta object literal for the '<em><b>Remove Tag</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_TREE_NODE___REMOVE_TAG__PATTERN = eINSTANCE.getDoorsTreeNode__RemoveTag__Pattern();

		/**
		 * The meta object literal for the '<em><b>Can Copy From</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_TREE_NODE___CAN_COPY_FROM__DOORSTREENODE = eINSTANCE.getDoorsTreeNode__CanCopyFrom__DoorsTreeNode();

		/**
		 * The meta object literal for the '<em><b>Get Child</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_TREE_NODE___GET_CHILD__STRING = eINSTANCE.getDoorsTreeNode__GetChild__String();

		/**
		 * The meta object literal for the '<em><b>To String</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_TREE_NODE___TO_STRING = eINSTANCE.getDoorsTreeNode__ToString();

		/**
		 * The meta object literal for the '{@link de.jpwinkler.daf.model.impl.DoorsFolderImpl <em>Folder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.jpwinkler.daf.model.impl.DoorsFolderImpl
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getDoorsFolder()
		 * @generated
		 */
		EClass DOORS_FOLDER = eINSTANCE.getDoorsFolder();

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
		 * The meta object literal for the '<em><b>Get View</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOORS_MODULE___GET_VIEW = eINSTANCE.getDoorsModule__GetView();

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
		EOperation DOORS_MODULE___SET_OBJECT_ATTRIBUTES__LIST = eINSTANCE.getDoorsModule__SetObjectAttributes__List();

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
		 * The meta object literal for the '<em><b>Get Target Module</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation LINK___GET_TARGET_MODULE = eINSTANCE.getLink__GetTargetModule();

		/**
		 * The meta object literal for the '<em><b>Get Target Object</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation LINK___GET_TARGET_OBJECT = eINSTANCE.getLink__GetTargetObject();

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

		/**
		 * The meta object literal for the '<em>Node Filter</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.function.Predicate
		 * @see de.jpwinkler.daf.model.impl.DoorsPackageImpl#getNodeFilter()
		 * @generated
		 */
		EDataType NODE_FILTER = eINSTANCE.getNodeFilter();

	}

} //DoorsPackage
