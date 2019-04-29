/**
 */
package de.jpwinkler.daf.doorscsv.model.impl;

import de.jpwinkler.daf.doorscsv.DoorsTreeNodeVisitor;

import de.jpwinkler.daf.doorscsv.model.AttributeDefinition;
import de.jpwinkler.daf.doorscsv.model.DoorsCSVFactory;
import de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage;
import de.jpwinkler.daf.doorscsv.model.DoorsModule;
import de.jpwinkler.daf.doorscsv.model.DoorsObject;
import de.jpwinkler.daf.doorscsv.model.DoorsTreeNode;
import de.jpwinkler.daf.doorscsv.model.Link;
import de.jpwinkler.daf.doorscsv.model.ResolvedLink;
import de.jpwinkler.daf.doorscsv.model.UnresolvedLink;

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
public class DoorsCSVPackageImpl extends EPackageImpl implements DoorsCSVPackage {
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
	private EClass doorsTreeNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType doorsTreeNodeVisitorEDataType = null;

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
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DoorsCSVPackageImpl() {
		super(eNS_URI, DoorsCSVFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DoorsCSVPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DoorsCSVPackage init() {
		if (isInited) return (DoorsCSVPackage)EPackage.Registry.INSTANCE.getEPackage(DoorsCSVPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredDoorsCSVPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		DoorsCSVPackageImpl theDoorsCSVPackage = registeredDoorsCSVPackage instanceof DoorsCSVPackageImpl ? (DoorsCSVPackageImpl)registeredDoorsCSVPackage : new DoorsCSVPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theDoorsCSVPackage.createPackageContents();

		// Initialize created meta-data
		theDoorsCSVPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDoorsCSVPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DoorsCSVPackage.eNS_URI, theDoorsCSVPackage);
		return theDoorsCSVPackage;
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
	public EAttribute getDoorsModule_Name() {
		return (EAttribute)doorsModuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsModule_Path() {
		return (EAttribute)doorsModuleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsModule_Url() {
		return (EAttribute)doorsModuleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsModule_AttributeDefinitions() {
		return (EReference)doorsModuleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsModule_View() {
		return (EAttribute)doorsModuleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsModule__FindAttributeDefinition__String() {
		return doorsModuleEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsModule__FindObject__String() {
		return doorsModuleEClass.getEOperations().get(1);
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
	public EAttribute getDoorsObject_ObjectIdentifier() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsObject_ObjectLevel() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsObject_ObjectNumber() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsObject_AbsoluteNumber() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsObject_ObjectText() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsObject_ObjectShortText() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsObject_ObjectHeading() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsObject_Text() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsObject_OutgoingLinks() {
		return (EReference)doorsObjectEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsObject_IncomingLinks() {
		return (EReference)doorsObjectEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsObject_Module() {
		return (EReference)doorsObjectEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsObject__IsHeading() {
		return doorsObjectEClass.getEOperations().get(0);
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
	public EClass getDoorsTreeNode() {
		return doorsTreeNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsTreeNode_Children() {
		return (EReference)doorsTreeNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsTreeNode_Parent() {
		return (EReference)doorsTreeNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsTreeNode_Attributes() {
		return (EReference)doorsTreeNodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsTreeNode__Accept__DoorsTreeNodeVisitor() {
		return doorsTreeNodeEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAttributeDefinition() {
		return attributeDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttributeDefinition_Name() {
		return (EAttribute)attributeDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getDoorsTreeNodeVisitor() {
		return doorsTreeNodeVisitorEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsCSVFactory getDoorsCSVFactory() {
		return (DoorsCSVFactory)getEFactoryInstance();
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
		doorsModuleEClass = createEClass(DOORS_MODULE);
		createEAttribute(doorsModuleEClass, DOORS_MODULE__NAME);
		createEAttribute(doorsModuleEClass, DOORS_MODULE__PATH);
		createEAttribute(doorsModuleEClass, DOORS_MODULE__URL);
		createEReference(doorsModuleEClass, DOORS_MODULE__ATTRIBUTE_DEFINITIONS);
		createEAttribute(doorsModuleEClass, DOORS_MODULE__VIEW);
		createEOperation(doorsModuleEClass, DOORS_MODULE___FIND_ATTRIBUTE_DEFINITION__STRING);
		createEOperation(doorsModuleEClass, DOORS_MODULE___FIND_OBJECT__STRING);

		doorsObjectEClass = createEClass(DOORS_OBJECT);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_IDENTIFIER);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_LEVEL);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_NUMBER);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__ABSOLUTE_NUMBER);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_TEXT);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_SHORT_TEXT);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_HEADING);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__TEXT);
		createEReference(doorsObjectEClass, DOORS_OBJECT__OUTGOING_LINKS);
		createEReference(doorsObjectEClass, DOORS_OBJECT__INCOMING_LINKS);
		createEReference(doorsObjectEClass, DOORS_OBJECT__MODULE);
		createEOperation(doorsObjectEClass, DOORS_OBJECT___IS_HEADING);

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

		doorsTreeNodeEClass = createEClass(DOORS_TREE_NODE);
		createEReference(doorsTreeNodeEClass, DOORS_TREE_NODE__CHILDREN);
		createEReference(doorsTreeNodeEClass, DOORS_TREE_NODE__PARENT);
		createEReference(doorsTreeNodeEClass, DOORS_TREE_NODE__ATTRIBUTES);
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___ACCEPT__DOORSTREENODEVISITOR);

		attributeDefinitionEClass = createEClass(ATTRIBUTE_DEFINITION);
		createEAttribute(attributeDefinitionEClass, ATTRIBUTE_DEFINITION__NAME);

		// Create data types
		doorsTreeNodeVisitorEDataType = createEDataType(DOORS_TREE_NODE_VISITOR);
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
		doorsModuleEClass.getESuperTypes().add(this.getDoorsTreeNode());
		doorsObjectEClass.getESuperTypes().add(this.getDoorsTreeNode());
		resolvedLinkEClass.getESuperTypes().add(this.getLink());
		unresolvedLinkEClass.getESuperTypes().add(this.getLink());

		// Initialize classes, features, and operations; add parameters
		initEClass(doorsModuleEClass, DoorsModule.class, "DoorsModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoorsModule_Name(), ecorePackage.getEString(), "name", null, 0, 1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsModule_Path(), ecorePackage.getEString(), "path", null, 0, 1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsModule_Url(), ecorePackage.getEString(), "url", null, 0, 1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsModule_AttributeDefinitions(), this.getAttributeDefinition(), null, "attributeDefinitions", null, 0, -1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsModule_View(), ecorePackage.getEString(), "view", null, 0, 1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getDoorsModule__FindAttributeDefinition__String(), this.getAttributeDefinition(), "findAttributeDefinition", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsModule__FindObject__String(), this.getDoorsObject(), "findObject", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "objectIdentifier", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(doorsObjectEClass, DoorsObject.class, "DoorsObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoorsObject_ObjectIdentifier(), ecorePackage.getEString(), "objectIdentifier", "", 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsObject_ObjectLevel(), ecorePackage.getEInt(), "objectLevel", null, 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsObject_ObjectNumber(), ecorePackage.getEString(), "objectNumber", null, 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsObject_AbsoluteNumber(), ecorePackage.getEInt(), "absoluteNumber", null, 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsObject_ObjectText(), ecorePackage.getEString(), "objectText", null, 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsObject_ObjectShortText(), ecorePackage.getEString(), "objectShortText", null, 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsObject_ObjectHeading(), ecorePackage.getEString(), "objectHeading", null, 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsObject_Text(), ecorePackage.getEString(), "text", "", 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsObject_OutgoingLinks(), this.getLink(), this.getLink_Source(), "outgoingLinks", null, 0, -1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsObject_IncomingLinks(), this.getResolvedLink(), this.getResolvedLink_Target(), "incomingLinks", null, 0, -1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsObject_Module(), this.getDoorsModule(), null, "module", null, 0, 1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getDoorsObject__IsHeading(), ecorePackage.getEBoolean(), "isHeading", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(stringToStringMapEClass, Map.Entry.class, "StringToStringMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToStringMap_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringToStringMap_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(linkEClass, Link.class, "Link", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLink_Source(), this.getDoorsObject(), this.getDoorsObject_OutgoingLinks(), "source", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resolvedLinkEClass, ResolvedLink.class, "ResolvedLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResolvedLink_Target(), this.getDoorsObject(), this.getDoorsObject_IncomingLinks(), "target", null, 0, 1, ResolvedLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unresolvedLinkEClass, UnresolvedLink.class, "UnresolvedLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnresolvedLink_TargetModule(), ecorePackage.getEString(), "targetModule", null, 0, 1, UnresolvedLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnresolvedLink_TargetObject(), ecorePackage.getEString(), "targetObject", null, 0, 1, UnresolvedLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(doorsTreeNodeEClass, DoorsTreeNode.class, "DoorsTreeNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDoorsTreeNode_Children(), this.getDoorsTreeNode(), this.getDoorsTreeNode_Parent(), "children", null, 0, -1, DoorsTreeNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsTreeNode_Parent(), this.getDoorsTreeNode(), this.getDoorsTreeNode_Children(), "parent", null, 0, 1, DoorsTreeNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsTreeNode_Attributes(), this.getStringToStringMap(), null, "attributes", null, 0, -1, DoorsTreeNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		op = initEOperation(getDoorsTreeNode__Accept__DoorsTreeNodeVisitor(), null, "accept", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getDoorsTreeNodeVisitor(), "visitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(attributeDefinitionEClass, AttributeDefinition.class, "AttributeDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttributeDefinition_Name(), ecorePackage.getEString(), "name", null, 0, 1, AttributeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(doorsTreeNodeVisitorEDataType, DoorsTreeNodeVisitor.class, "DoorsTreeNodeVisitor", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //DoorsCSVPackageImpl
