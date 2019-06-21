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

import de.jpwinkler.daf.model.*;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DoorsFactoryImpl extends EFactoryImpl implements DoorsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DoorsFactory init() {
		try {
			DoorsFactory theDoorsFactory = (DoorsFactory)EPackage.Registry.INSTANCE.getEFactory(DoorsPackage.eNS_URI);
			if (theDoorsFactory != null) {
				return theDoorsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DoorsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoorsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case DoorsPackage.DOORS_FOLDER: return (EObject)createDoorsFolder();
			case DoorsPackage.DOORS_MODULE: return (EObject)createDoorsModule();
			case DoorsPackage.DOORS_OBJECT: return (EObject)createDoorsObject();
			case DoorsPackage.ATTRIBUTE_MAP: return (EObject)createAttributeMap();
			case DoorsPackage.RESOLVED_LINK: return (EObject)createResolvedLink();
			case DoorsPackage.UNRESOLVED_LINK: return (EObject)createUnresolvedLink();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case DoorsPackage.DOORS_TREE_NODE_VISITOR:
				return createDoorsTreeNodeVisitorFromString(eDataType, initialValue);
			case DoorsPackage.PATTERN:
				return createPatternFromString(eDataType, initialValue);
			case DoorsPackage.COLLECTION:
				return createCollectionFromString(eDataType, initialValue);
			case DoorsPackage.NODE_FILTER:
				return createNodeFilterFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case DoorsPackage.DOORS_TREE_NODE_VISITOR:
				return convertDoorsTreeNodeVisitorToString(eDataType, instanceValue);
			case DoorsPackage.PATTERN:
				return convertPatternToString(eDataType, instanceValue);
			case DoorsPackage.COLLECTION:
				return convertCollectionToString(eDataType, instanceValue);
			case DoorsPackage.NODE_FILTER:
				return convertNodeFilterToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsFolder createDoorsFolder() {
		DoorsFolderImpl doorsFolder = new DoorsFolderImpl();
		return doorsFolder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsModule createDoorsModule() {
		DoorsModuleImpl doorsModule = new DoorsModuleImpl();
		return doorsModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsObject createDoorsObject() {
		DoorsObjectImpl doorsObject = new DoorsObjectImpl();
		return doorsObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createAttributeMap() {
		AttributeMapImpl attributeMap = new AttributeMapImpl();
		return attributeMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResolvedLink createResolvedLink() {
		ResolvedLinkImpl resolvedLink = new ResolvedLinkImpl();
		return resolvedLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UnresolvedLink createUnresolvedLink() {
		UnresolvedLinkImpl unresolvedLink = new UnresolvedLinkImpl();
		return unresolvedLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoorsTreeNodeVisitor createDoorsTreeNodeVisitorFromString(EDataType eDataType, String initialValue) {
		return (DoorsTreeNodeVisitor)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDoorsTreeNodeVisitorToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Pattern createPatternFromString(EDataType eDataType, String initialValue) {
		return (Pattern)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPatternToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collection createCollectionFromString(EDataType eDataType, String initialValue) {
		return (Collection)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCollectionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public Predicate<DoorsTreeNode> createNodeFilterFromString(EDataType eDataType, String initialValue) {
		return (Predicate<DoorsTreeNode>)super.createFromString(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNodeFilterToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsPackage getDoorsPackage() {
		return (DoorsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DoorsPackage getPackage() {
		return DoorsPackage.eINSTANCE;
	}

} //DoorsFactoryImpl
