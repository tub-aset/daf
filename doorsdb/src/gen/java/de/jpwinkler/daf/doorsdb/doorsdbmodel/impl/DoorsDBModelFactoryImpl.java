/**
 */
package de.jpwinkler.daf.doorsdb.doorsdbmodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelFactory;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage;
import de.jpwinkler.daf.doorsdb.util.DoorsDBVisitor;
import java.nio.file.Path;
import java.util.Map;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DoorsDBModelFactoryImpl extends EFactoryImpl implements DoorsDBModelFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static DoorsDBModelFactory init() {
        try {
            DoorsDBModelFactory theDoorsDBModelFactory = (DoorsDBModelFactory)EPackage.Registry.INSTANCE.getEFactory(DoorsDBModelPackage.eNS_URI);
            if (theDoorsDBModelFactory != null) {
                return theDoorsDBModelFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new DoorsDBModelFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoorsDBModelFactoryImpl() {
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
            case DoorsDBModelPackage.DB_FOLDER: return createDBFolder();
            case DoorsDBModelPackage.DB_MODULE: return createDBModule();
            case DoorsDBModelPackage.DB_VERSION: return createDBVersion();
            case DoorsDBModelPackage.DOORS_DB: return createDoorsDB();
            case DoorsDBModelPackage.DB_TAG: return createDBTag();
            case DoorsDBModelPackage.STRING_TO_STRING_MAP: return (EObject)createStringToStringMap();
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
            case DoorsDBModelPackage.DOORS_DB_VISITOR:
                return createDoorsDBVisitorFromString(eDataType, initialValue);
            case DoorsDBModelPackage.PATH:
                return createPathFromString(eDataType, initialValue);
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
            case DoorsDBModelPackage.DOORS_DB_VISITOR:
                return convertDoorsDBVisitorToString(eDataType, instanceValue);
            case DoorsDBModelPackage.PATH:
                return convertPathToString(eDataType, instanceValue);
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
    public Path createPathFromString(EDataType eDataType, String initialValue) {
        return (Path)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertPathToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DoorsDBModelPackage getDoorsDBModelPackage() {
        return (DoorsDBModelPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static DoorsDBModelPackage getPackage() {
        return DoorsDBModelPackage.eINSTANCE;
    }

} //DoorsDBModelFactoryImpl
