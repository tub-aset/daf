/**
 */
package de.jpwinkler.daf.dafcore.model.csv;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import de.jpwinkler.daf.dafcore.model.common.CommonPackage;

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
 * @see de.jpwinkler.daf.dafcore.model.csv.CSVFactory
 * @model kind="package"
 * @generated
 */
public interface CSVPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "csv";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "com.daimler.jonwink.srstp.model.csv";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "csv";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    CSVPackage eINSTANCE = de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl.init();

    /**
     * The meta object id for the '{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsModuleImpl <em>Doors Module</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.dafcore.model.csv.impl.DoorsModuleImpl
     * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getDoorsModule()
     * @generated
     */
    int DOORS_MODULE = 0;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_MODULE__SOURCE = CommonPackage.MODEL_OBJECT__SOURCE;

    /**
     * The feature id for the '<em><b>Objects</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_MODULE__OBJECTS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Attributes</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_MODULE__ATTRIBUTES = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_MODULE__NAME = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_MODULE__PATH = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Url</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_MODULE__URL = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Doors Module</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_MODULE_FEATURE_COUNT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 5;

    /**
     * The operation id for the '<em>Accept</em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_MODULE___ACCEPT__DOORSMODULEVISITOR = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 0;

    /**
     * The number of operations of the '<em>Doors Module</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_MODULE_OPERATION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 1;

    /**
     * The meta object id for the '{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl <em>Doors Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl
     * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getDoorsObject()
     * @generated
     */
    int DOORS_OBJECT = 1;

    /**
     * The feature id for the '<em><b>Module</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__MODULE = 0;

    /**
     * The feature id for the '<em><b>Attributes</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__ATTRIBUTES = 1;

    /**
     * The feature id for the '<em><b>Objects</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__OBJECTS = 2;

    /**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__PARENT = 3;

    /**
     * The feature id for the '<em><b>Object Identifier</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__OBJECT_IDENTIFIER = 4;

    /**
     * The feature id for the '<em><b>Object Level</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__OBJECT_LEVEL = 5;

    /**
     * The feature id for the '<em><b>Object Number</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__OBJECT_NUMBER = 6;

    /**
     * The feature id for the '<em><b>Created By</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__CREATED_BY = 7;

    /**
     * The feature id for the '<em><b>Created Thru</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__CREATED_THRU = 8;

    /**
     * The feature id for the '<em><b>Created On</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__CREATED_ON = 9;

    /**
     * The feature id for the '<em><b>Absolute Number</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__ABSOLUTE_NUMBER = 10;

    /**
     * The feature id for the '<em><b>Last Modified On</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__LAST_MODIFIED_ON = 11;

    /**
     * The feature id for the '<em><b>Last Modified By</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__LAST_MODIFIED_BY = 12;

    /**
     * The feature id for the '<em><b>Object Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__OBJECT_TEXT = 13;

    /**
     * The feature id for the '<em><b>Object Short Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__OBJECT_SHORT_TEXT = 14;

    /**
     * The feature id for the '<em><b>Object Heading</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__OBJECT_HEADING = 15;

    /**
     * The feature id for the '<em><b>Outgoing Links</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__OUTGOING_LINKS = 16;

    /**
     * The feature id for the '<em><b>Incoming Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT__INCOMING_LINKS = 17;

    /**
     * The number of structural features of the '<em>Doors Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT_FEATURE_COUNT = 18;

    /**
     * The operation id for the '<em>Accept</em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT___ACCEPT__DOORSMODULEVISITOR = 0;

    /**
     * The operation id for the '<em>Is Heading</em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT___IS_HEADING = 1;

    /**
     * The operation id for the '<em>Get Text</em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT___GET_TEXT = 2;

    /**
     * The number of operations of the '<em>Doors Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOORS_OBJECT_OPERATION_COUNT = 3;

    /**
     * The meta object id for the '{@link de.jpwinkler.daf.dafcore.model.csv.impl.StringToStringMapImpl <em>String To String Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.dafcore.model.csv.impl.StringToStringMapImpl
     * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getStringToStringMap()
     * @generated
     */
    int STRING_TO_STRING_MAP = 2;

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
     * The meta object id for the '{@link de.jpwinkler.daf.dafcore.model.csv.impl.LinkImpl <em>Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.dafcore.model.csv.impl.LinkImpl
     * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getLink()
     * @generated
     */
    int LINK = 3;

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
     * The meta object id for the '{@link de.jpwinkler.daf.dafcore.model.csv.impl.ResolvedLinkImpl <em>Resolved Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.dafcore.model.csv.impl.ResolvedLinkImpl
     * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getResolvedLink()
     * @generated
     */
    int RESOLVED_LINK = 4;

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
     * The meta object id for the '{@link de.jpwinkler.daf.dafcore.model.csv.impl.UnresolvedLinkImpl <em>Unresolved Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.dafcore.model.csv.impl.UnresolvedLinkImpl
     * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getUnresolvedLink()
     * @generated
     */
    int UNRESOLVED_LINK = 5;

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
     * The meta object id for the '<em>Doors Module Visitor</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.daimler.jonwink.srstp.core.csv.DoorsModuleVisitor
     * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getDoorsModuleVisitor()
     * @generated
     */
    int DOORS_MODULE_VISITOR = 6;


    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule <em>Doors Module</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Doors Module</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsModule
     * @generated
     */
    EClass getDoorsModule();

    /**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getObjects <em>Objects</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Objects</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getObjects()
     * @see #getDoorsModule()
     * @generated
     */
    EReference getDoorsModule_Objects();

    /**
     * Returns the meta object for the map '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getAttributes <em>Attributes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Attributes</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getAttributes()
     * @see #getDoorsModule()
     * @generated
     */
    EReference getDoorsModule_Attributes();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getName()
     * @see #getDoorsModule()
     * @generated
     */
    EAttribute getDoorsModule_Name();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getPath <em>Path</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Path</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getPath()
     * @see #getDoorsModule()
     * @generated
     */
    EAttribute getDoorsModule_Path();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getUrl <em>Url</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Url</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getUrl()
     * @see #getDoorsModule()
     * @generated
     */
    EAttribute getDoorsModule_Url();

    /**
     * Returns the meta object for the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#accept(com.daimler.jonwink.srstp.core.csv.DoorsModuleVisitor) <em>Accept</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Accept</em>' operation.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsModule#accept(com.daimler.jonwink.srstp.core.csv.DoorsModuleVisitor)
     * @generated
     */
    EOperation getDoorsModule__Accept__DoorsModuleVisitor();

    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject <em>Doors Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Doors Object</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject
     * @generated
     */
    EClass getDoorsObject();

    /**
     * Returns the meta object for the reference '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getModule <em>Module</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Module</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getModule()
     * @see #getDoorsObject()
     * @generated
     */
    EReference getDoorsObject_Module();

    /**
     * Returns the meta object for the map '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getAttributes <em>Attributes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Attributes</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getAttributes()
     * @see #getDoorsObject()
     * @generated
     */
    EReference getDoorsObject_Attributes();

    /**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjects <em>Objects</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Objects</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjects()
     * @see #getDoorsObject()
     * @generated
     */
    EReference getDoorsObject_Objects();

    /**
     * Returns the meta object for the container reference '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getParent()
     * @see #getDoorsObject()
     * @generated
     */
    EReference getDoorsObject_Parent();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectIdentifier <em>Object Identifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Object Identifier</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectIdentifier()
     * @see #getDoorsObject()
     * @generated
     */
    EAttribute getDoorsObject_ObjectIdentifier();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectLevel <em>Object Level</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Object Level</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectLevel()
     * @see #getDoorsObject()
     * @generated
     */
    EAttribute getDoorsObject_ObjectLevel();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectNumber <em>Object Number</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Object Number</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectNumber()
     * @see #getDoorsObject()
     * @generated
     */
    EAttribute getDoorsObject_ObjectNumber();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getCreatedBy <em>Created By</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Created By</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getCreatedBy()
     * @see #getDoorsObject()
     * @generated
     */
    EAttribute getDoorsObject_CreatedBy();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getCreatedThru <em>Created Thru</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Created Thru</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getCreatedThru()
     * @see #getDoorsObject()
     * @generated
     */
    EAttribute getDoorsObject_CreatedThru();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getCreatedOn <em>Created On</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Created On</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getCreatedOn()
     * @see #getDoorsObject()
     * @generated
     */
    EAttribute getDoorsObject_CreatedOn();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getAbsoluteNumber <em>Absolute Number</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Absolute Number</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getAbsoluteNumber()
     * @see #getDoorsObject()
     * @generated
     */
    EAttribute getDoorsObject_AbsoluteNumber();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getLastModifiedOn <em>Last Modified On</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Last Modified On</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getLastModifiedOn()
     * @see #getDoorsObject()
     * @generated
     */
    EAttribute getDoorsObject_LastModifiedOn();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getLastModifiedBy <em>Last Modified By</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Last Modified By</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getLastModifiedBy()
     * @see #getDoorsObject()
     * @generated
     */
    EAttribute getDoorsObject_LastModifiedBy();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectText <em>Object Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Object Text</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectText()
     * @see #getDoorsObject()
     * @generated
     */
    EAttribute getDoorsObject_ObjectText();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectShortText <em>Object Short Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Object Short Text</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectShortText()
     * @see #getDoorsObject()
     * @generated
     */
    EAttribute getDoorsObject_ObjectShortText();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectHeading <em>Object Heading</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Object Heading</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectHeading()
     * @see #getDoorsObject()
     * @generated
     */
    EAttribute getDoorsObject_ObjectHeading();

    /**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getOutgoingLinks <em>Outgoing Links</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Outgoing Links</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getOutgoingLinks()
     * @see #getDoorsObject()
     * @generated
     */
    EReference getDoorsObject_OutgoingLinks();

    /**
     * Returns the meta object for the reference list '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getIncomingLinks <em>Incoming Links</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Incoming Links</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getIncomingLinks()
     * @see #getDoorsObject()
     * @generated
     */
    EReference getDoorsObject_IncomingLinks();

    /**
     * Returns the meta object for the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#accept(com.daimler.jonwink.srstp.core.csv.DoorsModuleVisitor) <em>Accept</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Accept</em>' operation.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#accept(com.daimler.jonwink.srstp.core.csv.DoorsModuleVisitor)
     * @generated
     */
    EOperation getDoorsObject__Accept__DoorsModuleVisitor();

    /**
     * Returns the meta object for the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#isHeading() <em>Is Heading</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Is Heading</em>' operation.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#isHeading()
     * @generated
     */
    EOperation getDoorsObject__IsHeading();

    /**
     * Returns the meta object for the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getText() <em>Get Text</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Text</em>' operation.
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getText()
     * @generated
     */
    EOperation getDoorsObject__GetText();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>String To String Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>String To String Map</em>'.
     * @see java.util.Map.Entry
     * @model keyUnique="false" keyDataType="org.eclipse.emf.ecore.EString"
     *        valueUnique="false" valueDataType="org.eclipse.emf.ecore.EString"
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
     * Returns the meta object for class '{@link de.jpwinkler.daf.dafcore.model.csv.Link <em>Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Link</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.Link
     * @generated
     */
    EClass getLink();

    /**
     * Returns the meta object for the container reference '{@link de.jpwinkler.daf.dafcore.model.csv.Link#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Source</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.Link#getSource()
     * @see #getLink()
     * @generated
     */
    EReference getLink_Source();

    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.dafcore.model.csv.ResolvedLink <em>Resolved Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Resolved Link</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.ResolvedLink
     * @generated
     */
    EClass getResolvedLink();

    /**
     * Returns the meta object for the reference '{@link de.jpwinkler.daf.dafcore.model.csv.ResolvedLink#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.ResolvedLink#getTarget()
     * @see #getResolvedLink()
     * @generated
     */
    EReference getResolvedLink_Target();

    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink <em>Unresolved Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Unresolved Link</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink
     * @generated
     */
    EClass getUnresolvedLink();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink#getTargetModule <em>Target Module</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Target Module</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink#getTargetModule()
     * @see #getUnresolvedLink()
     * @generated
     */
    EAttribute getUnresolvedLink_TargetModule();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink#getTargetObject <em>Target Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Target Object</em>'.
     * @see de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink#getTargetObject()
     * @see #getUnresolvedLink()
     * @generated
     */
    EAttribute getUnresolvedLink_TargetObject();

    /**
     * Returns the meta object for data type '{@link com.daimler.jonwink.srstp.core.csv.DoorsModuleVisitor <em>Doors Module Visitor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Doors Module Visitor</em>'.
     * @see com.daimler.jonwink.srstp.core.csv.DoorsModuleVisitor
     * @model instanceClass="com.daimler.jonwink.srstp.core.csv.DoorsModuleVisitor"
     * @generated
     */
    EDataType getDoorsModuleVisitor();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    CSVFactory getCSVFactory();

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
         * The meta object literal for the '{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsModuleImpl <em>Doors Module</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.dafcore.model.csv.impl.DoorsModuleImpl
         * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getDoorsModule()
         * @generated
         */
        EClass DOORS_MODULE = eINSTANCE.getDoorsModule();

        /**
         * The meta object literal for the '<em><b>Objects</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOORS_MODULE__OBJECTS = eINSTANCE.getDoorsModule_Objects();

        /**
         * The meta object literal for the '<em><b>Attributes</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOORS_MODULE__ATTRIBUTES = eINSTANCE.getDoorsModule_Attributes();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOORS_MODULE__NAME = eINSTANCE.getDoorsModule_Name();

        /**
         * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOORS_MODULE__PATH = eINSTANCE.getDoorsModule_Path();

        /**
         * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOORS_MODULE__URL = eINSTANCE.getDoorsModule_Url();

        /**
         * The meta object literal for the '<em><b>Accept</b></em>' operation.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EOperation DOORS_MODULE___ACCEPT__DOORSMODULEVISITOR = eINSTANCE.getDoorsModule__Accept__DoorsModuleVisitor();

        /**
         * The meta object literal for the '{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl <em>Doors Object</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl
         * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getDoorsObject()
         * @generated
         */
        EClass DOORS_OBJECT = eINSTANCE.getDoorsObject();

        /**
         * The meta object literal for the '<em><b>Module</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOORS_OBJECT__MODULE = eINSTANCE.getDoorsObject_Module();

        /**
         * The meta object literal for the '<em><b>Attributes</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOORS_OBJECT__ATTRIBUTES = eINSTANCE.getDoorsObject_Attributes();

        /**
         * The meta object literal for the '<em><b>Objects</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOORS_OBJECT__OBJECTS = eINSTANCE.getDoorsObject_Objects();

        /**
         * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference DOORS_OBJECT__PARENT = eINSTANCE.getDoorsObject_Parent();

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
         * The meta object literal for the '<em><b>Created By</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOORS_OBJECT__CREATED_BY = eINSTANCE.getDoorsObject_CreatedBy();

        /**
         * The meta object literal for the '<em><b>Created Thru</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOORS_OBJECT__CREATED_THRU = eINSTANCE.getDoorsObject_CreatedThru();

        /**
         * The meta object literal for the '<em><b>Created On</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOORS_OBJECT__CREATED_ON = eINSTANCE.getDoorsObject_CreatedOn();

        /**
         * The meta object literal for the '<em><b>Absolute Number</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOORS_OBJECT__ABSOLUTE_NUMBER = eINSTANCE.getDoorsObject_AbsoluteNumber();

        /**
         * The meta object literal for the '<em><b>Last Modified On</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOORS_OBJECT__LAST_MODIFIED_ON = eINSTANCE.getDoorsObject_LastModifiedOn();

        /**
         * The meta object literal for the '<em><b>Last Modified By</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOORS_OBJECT__LAST_MODIFIED_BY = eINSTANCE.getDoorsObject_LastModifiedBy();

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
         * The meta object literal for the '<em><b>Accept</b></em>' operation.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EOperation DOORS_OBJECT___ACCEPT__DOORSMODULEVISITOR = eINSTANCE.getDoorsObject__Accept__DoorsModuleVisitor();

        /**
         * The meta object literal for the '<em><b>Is Heading</b></em>' operation.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EOperation DOORS_OBJECT___IS_HEADING = eINSTANCE.getDoorsObject__IsHeading();

        /**
         * The meta object literal for the '<em><b>Get Text</b></em>' operation.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EOperation DOORS_OBJECT___GET_TEXT = eINSTANCE.getDoorsObject__GetText();

        /**
         * The meta object literal for the '{@link de.jpwinkler.daf.dafcore.model.csv.impl.StringToStringMapImpl <em>String To String Map</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.dafcore.model.csv.impl.StringToStringMapImpl
         * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getStringToStringMap()
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
         * The meta object literal for the '{@link de.jpwinkler.daf.dafcore.model.csv.impl.LinkImpl <em>Link</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.dafcore.model.csv.impl.LinkImpl
         * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getLink()
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
         * The meta object literal for the '{@link de.jpwinkler.daf.dafcore.model.csv.impl.ResolvedLinkImpl <em>Resolved Link</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.dafcore.model.csv.impl.ResolvedLinkImpl
         * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getResolvedLink()
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
         * The meta object literal for the '{@link de.jpwinkler.daf.dafcore.model.csv.impl.UnresolvedLinkImpl <em>Unresolved Link</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.dafcore.model.csv.impl.UnresolvedLinkImpl
         * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getUnresolvedLink()
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
         * The meta object literal for the '<em>Doors Module Visitor</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see com.daimler.jonwink.srstp.core.csv.DoorsModuleVisitor
         * @see de.jpwinkler.daf.dafcore.model.csv.impl.CSVPackageImpl#getDoorsModuleVisitor()
         * @generated
         */
        EDataType DOORS_MODULE_VISITOR = eINSTANCE.getDoorsModuleVisitor();

    }

} //CSVPackage
