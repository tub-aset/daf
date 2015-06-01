/**
 */
package de.jpwinkler.daf.dafcore.model.csv.util;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.model.csv.CSVPackage;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.Link;
import de.jpwinkler.daf.dafcore.model.csv.ResolvedLink;
import de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink;

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
 * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage
 * @generated
 */
public class CSVSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static CSVPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CSVSwitch() {
        if (modelPackage == null) {
            modelPackage = CSVPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(final EPackage ePackage) {
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
    protected T doSwitch(final int classifierID, final EObject theEObject) {
        switch (classifierID) {
        case CSVPackage.DOORS_MODULE: {
            final DoorsModule doorsModule = (DoorsModule)theEObject;
            T result = caseDoorsModule(doorsModule);
            if (result == null) {
                result = caseModelObject(doorsModule);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case CSVPackage.DOORS_OBJECT: {
            final DoorsObject doorsObject = (DoorsObject)theEObject;
            T result = caseDoorsObject(doorsObject);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case CSVPackage.STRING_TO_STRING_MAP: {
            @SuppressWarnings("unchecked")
            final Map.Entry<String, String> stringToStringMap = (Map.Entry<String, String>)theEObject;
            T result = caseStringToStringMap(stringToStringMap);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case CSVPackage.LINK: {
            final Link link = (Link)theEObject;
            T result = caseLink(link);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case CSVPackage.RESOLVED_LINK: {
            final ResolvedLink resolvedLink = (ResolvedLink)theEObject;
            T result = caseResolvedLink(resolvedLink);
            if (result == null) {
                result = caseLink(resolvedLink);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case CSVPackage.UNRESOLVED_LINK: {
            final UnresolvedLink unresolvedLink = (UnresolvedLink)theEObject;
            T result = caseUnresolvedLink(unresolvedLink);
            if (result == null) {
                result = caseLink(unresolvedLink);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Doors Module</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Doors Module</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDoorsModule(final DoorsModule object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Doors Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Doors Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDoorsObject(final DoorsObject object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>String To String Map</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>String To String Map</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStringToStringMap(final Map.Entry<String, String> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Link</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLink(final Link object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Resolved Link</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Resolved Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseResolvedLink(final ResolvedLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Unresolved Link</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Unresolved Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUnresolvedLink(final UnresolvedLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Model Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Model Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModelObject(final ModelObject object) {
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
    public T defaultCase(final EObject object) {
        return null;
    }

} //CSVSwitch
