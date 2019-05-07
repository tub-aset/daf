/**
 */
package de.jpwinkler.daf.model.impl;

import de.jpwinkler.daf.model.DoorsDatabase;
import de.jpwinkler.daf.model.DoorsModuleVersion;
import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.model.Link;
import de.jpwinkler.daf.model.ResolvedLink;
import de.jpwinkler.daf.model.UnresolvedLink;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;
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
public class DoorsPackageImpl extends EPackageImpl implements DoorsPackage {
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
	private EClass doorsDatabaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doorsFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doorsModuleVersionEClass = null;

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
	private EClass attributeMapEClass = null;

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
	private EDataType doorsTreeNodeVisitorEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType patternEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType collectionEDataType = null;

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
	 * @see de.jpwinkler.daf.model.DoorsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DoorsPackageImpl() {
		super(eNS_URI, DoorsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DoorsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DoorsPackage init() {
		if (isInited) return (DoorsPackage)EPackage.Registry.INSTANCE.getEPackage(DoorsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredDoorsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		DoorsPackageImpl theDoorsPackage = registeredDoorsPackage instanceof DoorsPackageImpl ? (DoorsPackageImpl)registeredDoorsPackage : new DoorsPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theDoorsPackage.createPackageContents();

		// Initialize created meta-data
		theDoorsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDoorsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DoorsPackage.eNS_URI, theDoorsPackage);
		return theDoorsPackage;
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
	public EAttribute getDoorsTreeNode_Name() {
		return (EAttribute)doorsTreeNodeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsTreeNode_FullName() {
		return (EAttribute)doorsTreeNodeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsTreeNode_FullNameSegments() {
		return (EAttribute)doorsTreeNodeEClass.getEStructuralFeatures().get(5);
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
	public EOperation getDoorsTreeNode__HasTag__String() {
		return doorsTreeNodeEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsTreeNode__HasTag__Pattern() {
		return doorsTreeNodeEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsTreeNode__CopyFrom__DoorsTreeNode() {
		return doorsTreeNodeEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDoorsDatabase() {
		return doorsDatabaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsDatabase_Root() {
		return (EReference)doorsDatabaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsDatabase__Accept__DoorsTreeNodeVisitor() {
		return doorsDatabaseEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDoorsFolder() {
		return doorsFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsFolder__GetFolder__String() {
		return doorsFolderEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsFolder__GetModule__String() {
		return doorsFolderEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDoorsModuleVersion() {
		return doorsModuleVersionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsModuleVersion_Module() {
		return (EReference)doorsModuleVersionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsModuleVersion_Date() {
		return (EAttribute)doorsModuleVersionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsModuleVersion_Attributes() {
		return (EReference)doorsModuleVersionEClass.getEStructuralFeatures().get(2);
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
	public EAttribute getDoorsModule_Path() {
		return (EAttribute)doorsModuleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsModule_Url() {
		return (EAttribute)doorsModuleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsModule_View() {
		return (EAttribute)doorsModuleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsModule_Versions() {
		return (EReference)doorsModuleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsModule__FindObject__String() {
		return doorsModuleEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsModule__GetObjectAttributes() {
		return doorsModuleEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsModule__SetObjectAttributes__Collection() {
		return doorsModuleEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsModule__GetLatestVersion() {
		return doorsModuleEClass.getEOperations().get(3);
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
	public EClass getAttributeMap() {
		return attributeMapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttributeMap_Key() {
		return (EAttribute)attributeMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttributeMap_Value() {
		return (EAttribute)attributeMapEClass.getEStructuralFeatures().get(1);
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
	public EDataType getDoorsTreeNodeVisitor() {
		return doorsTreeNodeVisitorEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getPattern() {
		return patternEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getCollection() {
		return collectionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsFactory getDoorsFactory() {
		return (DoorsFactory)getEFactoryInstance();
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
		doorsTreeNodeEClass = createEClass(DOORS_TREE_NODE);
		createEReference(doorsTreeNodeEClass, DOORS_TREE_NODE__CHILDREN);
		createEReference(doorsTreeNodeEClass, DOORS_TREE_NODE__PARENT);
		createEReference(doorsTreeNodeEClass, DOORS_TREE_NODE__ATTRIBUTES);
		createEAttribute(doorsTreeNodeEClass, DOORS_TREE_NODE__NAME);
		createEAttribute(doorsTreeNodeEClass, DOORS_TREE_NODE__FULL_NAME);
		createEAttribute(doorsTreeNodeEClass, DOORS_TREE_NODE__FULL_NAME_SEGMENTS);
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___ACCEPT__DOORSTREENODEVISITOR);
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___HAS_TAG__STRING);
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___HAS_TAG__PATTERN);
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___COPY_FROM__DOORSTREENODE);

		doorsDatabaseEClass = createEClass(DOORS_DATABASE);
		createEReference(doorsDatabaseEClass, DOORS_DATABASE__ROOT);
		createEOperation(doorsDatabaseEClass, DOORS_DATABASE___ACCEPT__DOORSTREENODEVISITOR);

		doorsFolderEClass = createEClass(DOORS_FOLDER);
		createEOperation(doorsFolderEClass, DOORS_FOLDER___GET_FOLDER__STRING);
		createEOperation(doorsFolderEClass, DOORS_FOLDER___GET_MODULE__STRING);

		doorsModuleVersionEClass = createEClass(DOORS_MODULE_VERSION);
		createEReference(doorsModuleVersionEClass, DOORS_MODULE_VERSION__MODULE);
		createEAttribute(doorsModuleVersionEClass, DOORS_MODULE_VERSION__DATE);
		createEReference(doorsModuleVersionEClass, DOORS_MODULE_VERSION__ATTRIBUTES);

		doorsModuleEClass = createEClass(DOORS_MODULE);
		createEAttribute(doorsModuleEClass, DOORS_MODULE__PATH);
		createEAttribute(doorsModuleEClass, DOORS_MODULE__URL);
		createEAttribute(doorsModuleEClass, DOORS_MODULE__VIEW);
		createEReference(doorsModuleEClass, DOORS_MODULE__VERSIONS);
		createEOperation(doorsModuleEClass, DOORS_MODULE___FIND_OBJECT__STRING);
		createEOperation(doorsModuleEClass, DOORS_MODULE___GET_OBJECT_ATTRIBUTES);
		createEOperation(doorsModuleEClass, DOORS_MODULE___SET_OBJECT_ATTRIBUTES__COLLECTION);
		createEOperation(doorsModuleEClass, DOORS_MODULE___GET_LATEST_VERSION);

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

		attributeMapEClass = createEClass(ATTRIBUTE_MAP);
		createEAttribute(attributeMapEClass, ATTRIBUTE_MAP__KEY);
		createEAttribute(attributeMapEClass, ATTRIBUTE_MAP__VALUE);

		linkEClass = createEClass(LINK);
		createEReference(linkEClass, LINK__SOURCE);

		resolvedLinkEClass = createEClass(RESOLVED_LINK);
		createEReference(resolvedLinkEClass, RESOLVED_LINK__TARGET);

		unresolvedLinkEClass = createEClass(UNRESOLVED_LINK);
		createEAttribute(unresolvedLinkEClass, UNRESOLVED_LINK__TARGET_MODULE);
		createEAttribute(unresolvedLinkEClass, UNRESOLVED_LINK__TARGET_OBJECT);

		// Create data types
		doorsTreeNodeVisitorEDataType = createEDataType(DOORS_TREE_NODE_VISITOR);
		patternEDataType = createEDataType(PATTERN);
		collectionEDataType = createEDataType(COLLECTION);
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
		doorsFolderEClass.getESuperTypes().add(this.getDoorsTreeNode());
		doorsModuleEClass.getESuperTypes().add(this.getDoorsTreeNode());
		doorsObjectEClass.getESuperTypes().add(this.getDoorsTreeNode());
		resolvedLinkEClass.getESuperTypes().add(this.getLink());
		unresolvedLinkEClass.getESuperTypes().add(this.getLink());

		// Initialize classes, features, and operations; add parameters
		initEClass(doorsTreeNodeEClass, DoorsTreeNode.class, "DoorsTreeNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDoorsTreeNode_Children(), this.getDoorsTreeNode(), this.getDoorsTreeNode_Parent(), "children", null, 0, -1, DoorsTreeNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsTreeNode_Parent(), this.getDoorsTreeNode(), this.getDoorsTreeNode_Children(), "parent", null, 0, 1, DoorsTreeNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsTreeNode_Attributes(), this.getAttributeMap(), null, "attributes", null, 0, -1, DoorsTreeNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getDoorsTreeNode_Name(), ecorePackage.getEString(), "name", null, 0, 1, DoorsTreeNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsTreeNode_FullName(), ecorePackage.getEString(), "fullName", null, 0, 1, DoorsTreeNode.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsTreeNode_FullNameSegments(), ecorePackage.getEString(), "fullNameSegments", null, 0, -1, DoorsTreeNode.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getDoorsTreeNode__Accept__DoorsTreeNodeVisitor(), null, "accept", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getDoorsTreeNodeVisitor(), "visitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsTreeNode__HasTag__String(), ecorePackage.getEBoolean(), "hasTag", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "tag", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsTreeNode__HasTag__Pattern(), ecorePackage.getEBoolean(), "hasTag", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getPattern(), "pattern", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsTreeNode__CopyFrom__DoorsTreeNode(), null, "copyFrom", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getDoorsTreeNode(), "node", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(doorsDatabaseEClass, DoorsDatabase.class, "DoorsDatabase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDoorsDatabase_Root(), this.getDoorsFolder(), null, "root", null, 0, 1, DoorsDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = initEOperation(getDoorsDatabase__Accept__DoorsTreeNodeVisitor(), null, "accept", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getDoorsTreeNodeVisitor(), "visitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(doorsFolderEClass, DoorsFolder.class, "DoorsFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = initEOperation(getDoorsFolder__GetFolder__String(), this.getDoorsFolder(), "getFolder", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsFolder__GetModule__String(), this.getDoorsModule(), "getModule", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(doorsModuleVersionEClass, DoorsModuleVersion.class, "DoorsModuleVersion", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDoorsModuleVersion_Module(), this.getDoorsModule(), this.getDoorsModule_Versions(), "module", null, 0, 1, DoorsModuleVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsModuleVersion_Date(), ecorePackage.getEDate(), "date", null, 0, 1, DoorsModuleVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsModuleVersion_Attributes(), this.getAttributeMap(), null, "attributes", null, 0, -1, DoorsModuleVersion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(doorsModuleEClass, DoorsModule.class, "DoorsModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoorsModule_Path(), ecorePackage.getEString(), "path", null, 0, 1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsModule_Url(), ecorePackage.getEString(), "url", null, 0, 1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsModule_View(), ecorePackage.getEString(), "view", null, 0, 1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsModule_Versions(), this.getDoorsModuleVersion(), this.getDoorsModuleVersion_Module(), "versions", null, 0, -1, DoorsModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = initEOperation(getDoorsModule__FindObject__String(), this.getDoorsObject(), "findObject", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "objectIdentifier", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getDoorsModule__GetObjectAttributes(), ecorePackage.getEString(), "getObjectAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsModule__SetObjectAttributes__Collection(), null, "setObjectAttributes", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getCollection(), "attrs", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getDoorsModule__GetLatestVersion(), this.getDoorsModuleVersion(), "getLatestVersion", 0, 1, IS_UNIQUE, IS_ORDERED);

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

		initEClass(attributeMapEClass, Map.Entry.class, "AttributeMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttributeMap_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributeMap_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(linkEClass, Link.class, "Link", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLink_Source(), this.getDoorsObject(), this.getDoorsObject_OutgoingLinks(), "source", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resolvedLinkEClass, ResolvedLink.class, "ResolvedLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResolvedLink_Target(), this.getDoorsObject(), this.getDoorsObject_IncomingLinks(), "target", null, 0, 1, ResolvedLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unresolvedLinkEClass, UnresolvedLink.class, "UnresolvedLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnresolvedLink_TargetModule(), ecorePackage.getEString(), "targetModule", null, 0, 1, UnresolvedLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnresolvedLink_TargetObject(), ecorePackage.getEString(), "targetObject", null, 0, 1, UnresolvedLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(doorsTreeNodeVisitorEDataType, DoorsTreeNodeVisitor.class, "DoorsTreeNodeVisitor", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(patternEDataType, Pattern.class, "Pattern", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(collectionEDataType, Collection.class, "Collection", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //DoorsPackageImpl
