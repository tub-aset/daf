/**
 */
package de.jpwinkler.daf.fap5.model.codebeamer.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage;
import de.jpwinkler.daf.fap5.model.codebeamer.DoubleMetric;
import de.jpwinkler.daf.fap5.model.codebeamer.IntMetric;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;
import de.jpwinkler.daf.fap5.model.codebeamer.Metric;

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
 * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage
 * @generated
 */
public class CodebeamerSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static CodebeamerPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CodebeamerSwitch() {
        if (modelPackage == null) {
            modelPackage = CodebeamerPackage.eINSTANCE;
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
            case CodebeamerPackage.CODE_BEAMER_MODEL: {
                CodeBeamerModel codeBeamerModel = (CodeBeamerModel)theEObject;
                T result = caseCodeBeamerModel(codeBeamerModel);
                if (result == null) result = caseModelObject(codeBeamerModel);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CodebeamerPackage.ISSUE: {
                Issue issue = (Issue)theEObject;
                T result = caseIssue(issue);
                if (result == null) result = caseModelObject(issue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CodebeamerPackage.METRIC: {
                Metric metric = (Metric)theEObject;
                T result = caseMetric(metric);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CodebeamerPackage.INT_METRIC: {
                IntMetric intMetric = (IntMetric)theEObject;
                T result = caseIntMetric(intMetric);
                if (result == null) result = caseMetric(intMetric);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CodebeamerPackage.DOUBLE_METRIC: {
                DoubleMetric doubleMetric = (DoubleMetric)theEObject;
                T result = caseDoubleMetric(doubleMetric);
                if (result == null) result = caseMetric(doubleMetric);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Code Beamer Model</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Code Beamer Model</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCodeBeamerModel(CodeBeamerModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Issue</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Issue</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIssue(Issue object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Metric</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Metric</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMetric(Metric object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Int Metric</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Int Metric</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIntMetric(IntMetric object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Double Metric</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Double Metric</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDoubleMetric(DoubleMetric object) {
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
    public T caseModelObject(ModelObject object) {
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

} //CodebeamerSwitch
