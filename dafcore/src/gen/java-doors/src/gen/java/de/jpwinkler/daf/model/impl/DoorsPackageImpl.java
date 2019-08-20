/**
 */
package de.jpwinkler.daf.model.impl;

/*-
 * #%L
 * dafcore
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsLink;
import de.jpwinkler.daf.model.DoorsLinkResolveException;
import de.jpwinkler.daf.model.DoorsLinkStatus;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.model.DoorsTableRow;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;

import java.util.Collection;
import java.util.Map;

import java.util.function.Predicate;

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
	private EClass doorsFolderEClass = null;

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
	private EClass doorsTableRowEClass = null;

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
	private EClass doorsLinkEClass = null;

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
	private EDataType doorsLinkStatusEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType doorsLinkResolveExceptionEDataType = null;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType nodeFilterEDataType = null;

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
	public EOperation getDoorsTreeNode__GetTags() {
		return doorsTreeNodeEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsTreeNode__SetTag__String() {
		return doorsTreeNodeEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsTreeNode__RemoveTag__String() {
		return doorsTreeNodeEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsTreeNode__RemoveTag__Pattern() {
		return doorsTreeNodeEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsTreeNode__CanCopyFrom__DoorsTreeNode() {
		return doorsTreeNodeEClass.getEOperations().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsTreeNode__GetChild__String() {
		return doorsTreeNodeEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsTreeNode__ToString() {
		return doorsTreeNodeEClass.getEOperations().get(8);
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
	public EAttribute getDoorsFolder_Project() {
		return (EAttribute)doorsFolderEClass.getEStructuralFeatures().get(0);
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
	public EOperation getDoorsModule__GetView() {
		return doorsModuleEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsModule__GetObjectAttributes() {
		return doorsModuleEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsModule__SetObjectAttributes__List() {
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
	public EAttribute getDoorsObject_ObjectNumber() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsObject_AbsoluteNumber() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsObject_ObjectText() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsObject_ObjectShortText() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsObject_ObjectHeading() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsObject_Text() {
		return (EAttribute)doorsObjectEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsObject_OutgoingLinks() {
		return (EReference)doorsObjectEClass.getEStructuralFeatures().get(7);
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
	public EClass getDoorsTableRow() {
		return doorsTableRowEClass;
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
	public EClass getDoorsLink() {
		return doorsLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsLink_TargetModule() {
		return (EAttribute)doorsLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoorsLink_TargetObject() {
		return (EAttribute)doorsLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDoorsLink_Source() {
		return (EReference)doorsLinkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsLink__GetLinkStatus() {
		return doorsLinkEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsLink__Resolve() {
		return doorsLinkEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getDoorsLink__Resolve__DoorsTreeNode() {
		return doorsLinkEClass.getEOperations().get(2);
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
	public EDataType getDoorsLinkStatus() {
		return doorsLinkStatusEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getDoorsLinkResolveException() {
		return doorsLinkResolveExceptionEDataType;
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
	public EDataType getNodeFilter() {
		return nodeFilterEDataType;
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
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___HAS_TAG__STRING);
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___HAS_TAG__PATTERN);
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___GET_TAGS);
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___SET_TAG__STRING);
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___REMOVE_TAG__STRING);
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___REMOVE_TAG__PATTERN);
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___CAN_COPY_FROM__DOORSTREENODE);
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___GET_CHILD__STRING);
		createEOperation(doorsTreeNodeEClass, DOORS_TREE_NODE___TO_STRING);

		doorsFolderEClass = createEClass(DOORS_FOLDER);
		createEAttribute(doorsFolderEClass, DOORS_FOLDER__PROJECT);

		doorsModuleEClass = createEClass(DOORS_MODULE);
		createEOperation(doorsModuleEClass, DOORS_MODULE___GET_VIEW);
		createEOperation(doorsModuleEClass, DOORS_MODULE___GET_OBJECT_ATTRIBUTES);
		createEOperation(doorsModuleEClass, DOORS_MODULE___SET_OBJECT_ATTRIBUTES__LIST);

		doorsObjectEClass = createEClass(DOORS_OBJECT);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_IDENTIFIER);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_NUMBER);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__ABSOLUTE_NUMBER);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_TEXT);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_SHORT_TEXT);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__OBJECT_HEADING);
		createEAttribute(doorsObjectEClass, DOORS_OBJECT__TEXT);
		createEReference(doorsObjectEClass, DOORS_OBJECT__OUTGOING_LINKS);
		createEOperation(doorsObjectEClass, DOORS_OBJECT___IS_HEADING);

		doorsTableRowEClass = createEClass(DOORS_TABLE_ROW);

		attributeMapEClass = createEClass(ATTRIBUTE_MAP);
		createEAttribute(attributeMapEClass, ATTRIBUTE_MAP__KEY);
		createEAttribute(attributeMapEClass, ATTRIBUTE_MAP__VALUE);

		doorsLinkEClass = createEClass(DOORS_LINK);
		createEAttribute(doorsLinkEClass, DOORS_LINK__TARGET_MODULE);
		createEAttribute(doorsLinkEClass, DOORS_LINK__TARGET_OBJECT);
		createEReference(doorsLinkEClass, DOORS_LINK__SOURCE);
		createEOperation(doorsLinkEClass, DOORS_LINK___GET_LINK_STATUS);
		createEOperation(doorsLinkEClass, DOORS_LINK___RESOLVE);
		createEOperation(doorsLinkEClass, DOORS_LINK___RESOLVE__DOORSTREENODE);

		// Create data types
		doorsTreeNodeVisitorEDataType = createEDataType(DOORS_TREE_NODE_VISITOR);
		doorsLinkStatusEDataType = createEDataType(DOORS_LINK_STATUS);
		doorsLinkResolveExceptionEDataType = createEDataType(DOORS_LINK_RESOLVE_EXCEPTION);
		patternEDataType = createEDataType(PATTERN);
		collectionEDataType = createEDataType(COLLECTION);
		nodeFilterEDataType = createEDataType(NODE_FILTER);
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
		doorsTableRowEClass.getESuperTypes().add(this.getDoorsObject());

		// Initialize classes, features, and operations; add parameters
		initEClass(doorsTreeNodeEClass, DoorsTreeNode.class, "DoorsTreeNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDoorsTreeNode_Children(), this.getDoorsTreeNode(), this.getDoorsTreeNode_Parent(), "children", null, 0, -1, DoorsTreeNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsTreeNode_Parent(), this.getDoorsTreeNode(), this.getDoorsTreeNode_Children(), "parent", null, 0, 1, DoorsTreeNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsTreeNode_Attributes(), this.getAttributeMap(), null, "attributes", null, 0, -1, DoorsTreeNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getDoorsTreeNode_Name(), ecorePackage.getEString(), "name", null, 0, 1, DoorsTreeNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsTreeNode_FullName(), ecorePackage.getEString(), "fullName", null, 0, 1, DoorsTreeNode.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsTreeNode_FullNameSegments(), ecorePackage.getEString(), "fullNameSegments", null, 0, -1, DoorsTreeNode.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getDoorsTreeNode__HasTag__String(), ecorePackage.getEBoolean(), "hasTag", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "tag", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsTreeNode__HasTag__Pattern(), ecorePackage.getEBoolean(), "hasTag", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getPattern(), "pattern", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getDoorsTreeNode__GetTags(), ecorePackage.getEString(), "getTags", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsTreeNode__SetTag__String(), null, "setTag", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "tag", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsTreeNode__RemoveTag__String(), null, "removeTag", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "tag", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsTreeNode__RemoveTag__Pattern(), null, "removeTag", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getPattern(), "pattern", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsTreeNode__CanCopyFrom__DoorsTreeNode(), ecorePackage.getEBoolean(), "canCopyFrom", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getDoorsTreeNode(), "node", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsTreeNode__GetChild__String(), this.getDoorsTreeNode(), "getChild", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getDoorsTreeNode__ToString(), ecorePackage.getEString(), "toString", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(doorsFolderEClass, DoorsFolder.class, "DoorsFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoorsFolder_Project(), ecorePackage.getEBoolean(), "project", null, 0, 1, DoorsFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(doorsModuleEClass, DoorsModule.class, "DoorsModule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEOperation(getDoorsModule__GetView(), ecorePackage.getEString(), "getView", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getDoorsModule__GetObjectAttributes(), ecorePackage.getEString(), "getObjectAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsModule__SetObjectAttributes__List(), null, "setObjectAttributes", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "attrs", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(doorsObjectEClass, DoorsObject.class, "DoorsObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoorsObject_ObjectIdentifier(), ecorePackage.getEString(), "objectIdentifier", "", 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsObject_ObjectNumber(), ecorePackage.getEString(), "objectNumber", null, 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsObject_AbsoluteNumber(), ecorePackage.getEInt(), "absoluteNumber", null, 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsObject_ObjectText(), ecorePackage.getEString(), "objectText", null, 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsObject_ObjectShortText(), ecorePackage.getEString(), "objectShortText", null, 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsObject_ObjectHeading(), ecorePackage.getEString(), "objectHeading", null, 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsObject_Text(), ecorePackage.getEString(), "text", "", 0, 1, DoorsObject.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsObject_OutgoingLinks(), this.getDoorsLink(), this.getDoorsLink_Source(), "outgoingLinks", null, 0, -1, DoorsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getDoorsObject__IsHeading(), ecorePackage.getEBoolean(), "isHeading", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(doorsTableRowEClass, DoorsTableRow.class, "DoorsTableRow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(attributeMapEClass, Map.Entry.class, "AttributeMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttributeMap_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributeMap_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(doorsLinkEClass, DoorsLink.class, "DoorsLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoorsLink_TargetModule(), ecorePackage.getEString(), "targetModule", null, 0, 1, DoorsLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDoorsLink_TargetObject(), ecorePackage.getEString(), "targetObject", null, 0, 1, DoorsLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDoorsLink_Source(), this.getDoorsObject(), this.getDoorsObject_OutgoingLinks(), "source", null, 0, 1, DoorsLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getDoorsLink__GetLinkStatus(), this.getDoorsLinkStatus(), "getLinkStatus", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDoorsLink__Resolve(), this.getDoorsObject(), "resolve", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getDoorsLinkResolveException());

		op = initEOperation(getDoorsLink__Resolve__DoorsTreeNode(), this.getDoorsObject(), "resolve", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getDoorsTreeNode(), "sourceOverride", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getDoorsLinkResolveException());

		// Initialize data types
		initEDataType(doorsTreeNodeVisitorEDataType, DoorsTreeNodeVisitor.class, "DoorsTreeNodeVisitor", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(doorsLinkStatusEDataType, DoorsLinkStatus.class, "DoorsLinkStatus", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(doorsLinkResolveExceptionEDataType, DoorsLinkResolveException.class, "DoorsLinkResolveException", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(patternEDataType, Pattern.class, "Pattern", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(collectionEDataType, Collection.class, "Collection", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(nodeFilterEDataType, Predicate.class, "NodeFilter", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS, "java.util.function.Predicate<de.jpwinkler.daf.model.DoorsTreeNode>");

		// Create resource
		createResource(eNS_URI);
	}

} //DoorsPackageImpl
