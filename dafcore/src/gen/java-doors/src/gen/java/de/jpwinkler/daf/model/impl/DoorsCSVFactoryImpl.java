/**
 */
package de.jpwinkler.daf.model.impl;

import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;

import de.jpwinkler.daf.model.*;

import java.util.Map;

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
public class DoorsCSVFactoryImpl extends EFactoryImpl implements DoorsCSVFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DoorsCSVFactory init() {
		try {
			DoorsCSVFactory theDoorsCSVFactory = (DoorsCSVFactory)EPackage.Registry.INSTANCE.getEFactory(DoorsCSVPackage.eNS_URI);
			if (theDoorsCSVFactory != null) {
				return theDoorsCSVFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DoorsCSVFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoorsCSVFactoryImpl() {
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
			case DoorsCSVPackage.DOORS_TREE_NODE: return createDoorsTreeNode();
			case DoorsCSVPackage.DOORS_DB: return createDoorsDB();
			case DoorsCSVPackage.DOORS_FOLDER: return createDoorsFolder();
			case DoorsCSVPackage.DOORS_DATABASE_VERSION: return createDoorsDatabaseVersion();
			case DoorsCSVPackage.DOORS_MODULE: return createDoorsModule();
			case DoorsCSVPackage.DOORS_OBJECT: return createDoorsObject();
			case DoorsCSVPackage.STRING_TO_STRING_MAP: return (EObject)createStringToStringMap();
			case DoorsCSVPackage.RESOLVED_LINK: return createResolvedLink();
			case DoorsCSVPackage.UNRESOLVED_LINK: return createUnresolvedLink();
			case DoorsCSVPackage.ATTRIBUTE_DEFINITION: return createAttributeDefinition();
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
			case DoorsCSVPackage.DOORS_TREE_NODE_VISITOR:
				return createDoorsTreeNodeVisitorFromString(eDataType, initialValue);
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
			case DoorsCSVPackage.DOORS_TREE_NODE_VISITOR:
				return convertDoorsTreeNodeVisitorToString(eDataType, instanceValue);
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
	public Map.Entry<String, String> createStringToStringMap() {
		StringToStringMapImpl stringToStringMap = new StringToStringMapImpl();
		return stringToStringMap;
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
	@Override
	public DoorsTreeNode createDoorsTreeNode() {
		DoorsTreeNodeImpl doorsTreeNode = new DoorsTreeNodeImpl();
		return doorsTreeNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsDB createDoorsDB() {
		DoorsDBImpl doorsDB = new DoorsDBImpl();
		return doorsDB;
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
	public DoorsDatabaseVersion createDoorsDatabaseVersion() {
		DoorsDatabaseVersionImpl doorsDatabaseVersion = new DoorsDatabaseVersionImpl();
		return doorsDatabaseVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AttributeDefinition createAttributeDefinition() {
		AttributeDefinitionImpl attributeDefinition = new AttributeDefinitionImpl();
		return attributeDefinition;
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
	@Override
	public DoorsCSVPackage getDoorsCSVPackage() {
		return (DoorsCSVPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DoorsCSVPackage getPackage() {
		return DoorsCSVPackage.eINSTANCE;
	}

} //DoorsCSVFactoryImpl
