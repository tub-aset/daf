/**
 */
package de.jpwinkler.daf.doorsdb.doorsdbmodel.impl;

import de.jpwinkler.daf.doorsdb.DoorsDBVisitor;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.*;

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
public class DoorsDBFactoryImpl extends EFactoryImpl implements DoorsDBFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DoorsDBFactory init() {
		try {
			DoorsDBFactory theDoorsDBFactory = (DoorsDBFactory)EPackage.Registry.INSTANCE.getEFactory(DoorsDBPackage.eNS_URI);
			if (theDoorsDBFactory != null) {
				return theDoorsDBFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DoorsDBFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoorsDBFactoryImpl() {
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
			case DoorsDBPackage.DB_FOLDER: return createDBFolder();
			case DoorsDBPackage.DB_MODULE: return createDBModule();
			case DoorsDBPackage.DB_VERSION: return createDBVersion();
			case DoorsDBPackage.DOORS_DB: return createDoorsDB();
			case DoorsDBPackage.DB_TAG: return createDBTag();
			case DoorsDBPackage.STRING_TO_STRING_MAP: return (EObject)createStringToStringMap();
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
			case DoorsDBPackage.DOORS_DB_VISITOR:
				return createDoorsDBVisitorFromString(eDataType, initialValue);
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
			case DoorsDBPackage.DOORS_DB_VISITOR:
				return convertDoorsDBVisitorToString(eDataType, instanceValue);
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
	public DBFolder createDBFolder() {
		DBFolderImpl dbFolder = new DBFolderImpl();
		return dbFolder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DBModule createDBModule() {
		DBModuleImpl dbModule = new DBModuleImpl();
		return dbModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DBVersion createDBVersion() {
		DBVersionImpl dbVersion = new DBVersionImpl();
		return dbVersion;
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
	public DBTag createDBTag() {
		DBTagImpl dbTag = new DBTagImpl();
		return dbTag;
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
	public DoorsDBVisitor createDoorsDBVisitorFromString(EDataType eDataType, String initialValue) {
		return (DoorsDBVisitor)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDoorsDBVisitorToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsDBPackage getDoorsDBPackage() {
		return (DoorsDBPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DoorsDBPackage getPackage() {
		return DoorsDBPackage.eINSTANCE;
	}

} //DoorsDBFactoryImpl
