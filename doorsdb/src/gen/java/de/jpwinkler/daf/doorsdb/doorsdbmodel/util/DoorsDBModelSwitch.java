/**
 */
package de.jpwinkler.daf.doorsdb.doorsdbmodel.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage
 * @generated
 */
public class DoorsDBModelSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static DoorsDBModelPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoorsDBModelSwitch() {
        if (modelPackage == null) {
            modelPackage = DoorsDBModelPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case DoorsDBModelPackage.DB_ITEM: {
                DBItem dbItem = (DBItem)theEObject;
                T result = caseDBItem(dbItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DoorsDBModelPackage.DB_FOLDER: {
                DBFolder dbFolder = (DBFolder)theEObject;
                T result = caseDBFolder(dbFolder);
                if (result == null) result = caseDBItem(dbFolder);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DoorsDBModelPackage.DB_MODULE: {
                DBModule dbModule = (DBModule)theEObject;
                T result = caseDBModule(dbModule);
                if (result == null) result = caseDBItem(dbModule);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DoorsDBModelPackage.DB_VERSION: {
                DBVersion dbVersion = (DBVersion)theEObject;
                T result = caseDBVersion(dbVersion);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DoorsDBModelPackage.DOORS_DB: {
                DoorsDB doorsDB = (DoorsDB)theEObject;
                T result = caseDoorsDB(doorsDB);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case DoorsDBModelPackage.DB_TAG: {
                DBTag dbTag = (DBTag)theEObject;
                T result = caseDBTag(dbTag);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DB Folder</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DB Folder</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDBFolder(DBFolder object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DB Module</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DB Module</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDBModule(DBModule object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DB Version</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DB Version</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDBVersion(DBVersion object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Doors DB</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Doors DB</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDoorsDB(DoorsDB object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DB Tag</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DB Tag</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDBTag(DBTag object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DB Item</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DB Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDBItem(DBItem object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} //DoorsDBModelSwitch
