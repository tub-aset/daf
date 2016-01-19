/**
 */
package de.jpwinkler.daf.fap5.model.srs.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.fap5.model.srs.Description;
import de.jpwinkler.daf.fap5.model.srs.EndCondition;
import de.jpwinkler.daf.fap5.model.srs.FunctionContribution;
import de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer;
import de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationElement;
import de.jpwinkler.daf.fap5.model.srs.Heading;
import de.jpwinkler.daf.fap5.model.srs.Precondition;
import de.jpwinkler.daf.fap5.model.srs.SRSModel;
import de.jpwinkler.daf.fap5.model.srs.SrsPackage;
import de.jpwinkler.daf.fap5.model.srs.Trigger;
import de.jpwinkler.daf.fap5.model.srs.VehicleFunction;

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
 * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage
 * @generated
 */
public class SrsSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static SrsPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SrsSwitch() {
        if (modelPackage == null) {
            modelPackage = SrsPackage.eINSTANCE;
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
            case SrsPackage.SRS_MODEL: {
                SRSModel srsModel = (SRSModel)theEObject;
                T result = caseSRSModel(srsModel);
                if (result == null) result = caseModelObject(srsModel);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SrsPackage.VEHICLE_FUNCTION: {
                VehicleFunction vehicleFunction = (VehicleFunction)theEObject;
                T result = caseVehicleFunction(vehicleFunction);
                if (result == null) result = caseFunctionalSpecificationContainer(vehicleFunction);
                if (result == null) result = caseFunctionalSpecificationElement(vehicleFunction);
                if (result == null) result = caseModelObject(vehicleFunction);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SrsPackage.FUNCTION_CONTRIBUTION: {
                FunctionContribution functionContribution = (FunctionContribution)theEObject;
                T result = caseFunctionContribution(functionContribution);
                if (result == null) result = caseFunctionalSpecificationElement(functionContribution);
                if (result == null) result = caseModelObject(functionContribution);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SrsPackage.FUNCTIONAL_SPECIFICATION_CONTAINER: {
                FunctionalSpecificationContainer functionalSpecificationContainer = (FunctionalSpecificationContainer)theEObject;
                T result = caseFunctionalSpecificationContainer(functionalSpecificationContainer);
                if (result == null) result = caseFunctionalSpecificationElement(functionalSpecificationContainer);
                if (result == null) result = caseModelObject(functionalSpecificationContainer);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SrsPackage.FUNCTIONAL_SPECIFICATION_ELEMENT: {
                FunctionalSpecificationElement functionalSpecificationElement = (FunctionalSpecificationElement)theEObject;
                T result = caseFunctionalSpecificationElement(functionalSpecificationElement);
                if (result == null) result = caseModelObject(functionalSpecificationElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SrsPackage.PRECONDITION: {
                Precondition precondition = (Precondition)theEObject;
                T result = casePrecondition(precondition);
                if (result == null) result = caseFunctionalSpecificationContainer(precondition);
                if (result == null) result = caseFunctionalSpecificationElement(precondition);
                if (result == null) result = caseModelObject(precondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SrsPackage.END_CONDITION: {
                EndCondition endCondition = (EndCondition)theEObject;
                T result = caseEndCondition(endCondition);
                if (result == null) result = caseFunctionalSpecificationContainer(endCondition);
                if (result == null) result = caseFunctionalSpecificationElement(endCondition);
                if (result == null) result = caseModelObject(endCondition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SrsPackage.TRIGGER: {
                Trigger trigger = (Trigger)theEObject;
                T result = caseTrigger(trigger);
                if (result == null) result = caseFunctionalSpecificationContainer(trigger);
                if (result == null) result = caseFunctionalSpecificationElement(trigger);
                if (result == null) result = caseModelObject(trigger);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SrsPackage.HEADING: {
                Heading heading = (Heading)theEObject;
                T result = caseHeading(heading);
                if (result == null) result = caseFunctionalSpecificationContainer(heading);
                if (result == null) result = caseFunctionalSpecificationElement(heading);
                if (result == null) result = caseModelObject(heading);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SrsPackage.DESCRIPTION: {
                Description description = (Description)theEObject;
                T result = caseDescription(description);
                if (result == null) result = caseFunctionalSpecificationElement(description);
                if (result == null) result = caseModelObject(description);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>SRS Model</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SRS Model</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSRSModel(SRSModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Vehicle Function</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Vehicle Function</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVehicleFunction(VehicleFunction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Function Contribution</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Function Contribution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFunctionContribution(FunctionContribution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Functional Specification Container</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Functional Specification Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFunctionalSpecificationContainer(FunctionalSpecificationContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Functional Specification Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Functional Specification Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFunctionalSpecificationElement(FunctionalSpecificationElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Precondition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Precondition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePrecondition(Precondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End Condition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndCondition(EndCondition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Trigger</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Trigger</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTrigger(Trigger object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Heading</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Heading</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHeading(Heading object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Description</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDescription(Description object) {
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

} //SrsSwitch
