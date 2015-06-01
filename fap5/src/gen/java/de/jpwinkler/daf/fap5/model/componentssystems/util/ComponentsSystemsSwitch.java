/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.fap5.model.componentssystems.Component;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;
import de.jpwinkler.daf.fap5.model.componentssystems.Functionality;
import de.jpwinkler.daf.fap5.model.componentssystems.LogicalComponent;

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
 * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage
 * @generated
 */
public class ComponentsSystemsSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ComponentsSystemsPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentsSystemsSwitch() {
        if (modelPackage == null) {
            modelPackage = ComponentsSystemsPackage.eINSTANCE;
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
        case ComponentsSystemsPackage.COMPONENTS_SYSTEMS_MODEL: {
            final ComponentsSystemsModel componentsSystemsModel = (ComponentsSystemsModel)theEObject;
            T result = caseComponentsSystemsModel(componentsSystemsModel);
            if (result == null) {
                result = caseModelObject(componentsSystemsModel);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET: {
            final FunctionContributionTarget functionContributionTarget = (FunctionContributionTarget)theEObject;
            T result = caseFunctionContributionTarget(functionContributionTarget);
            if (result == null) {
                result = caseModelObject(functionContributionTarget);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ComponentsSystemsPackage.SYSTEM: {
            final de.jpwinkler.daf.fap5.model.componentssystems.System system = (de.jpwinkler.daf.fap5.model.componentssystems.System)theEObject;
            T result = caseSystem(system);
            if (result == null) {
                result = caseFunctionContributionTarget(system);
            }
            if (result == null) {
                result = caseModelObject(system);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ComponentsSystemsPackage.COMPONENT: {
            final Component component = (Component)theEObject;
            T result = caseComponent(component);
            if (result == null) {
                result = caseFunctionContributionTarget(component);
            }
            if (result == null) {
                result = caseModelObject(component);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ComponentsSystemsPackage.LOGICAL_COMPONENT: {
            final LogicalComponent logicalComponent = (LogicalComponent)theEObject;
            T result = caseLogicalComponent(logicalComponent);
            if (result == null) {
                result = caseFunctionContributionTarget(logicalComponent);
            }
            if (result == null) {
                result = caseModelObject(logicalComponent);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ComponentsSystemsPackage.FUNCTIONALITY: {
            final Functionality functionality = (Functionality)theEObject;
            T result = caseFunctionality(functionality);
            if (result == null) {
                result = caseFunctionContributionTarget(functionality);
            }
            if (result == null) {
                result = caseModelObject(functionality);
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
     * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Model</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComponentsSystemsModel(final ComponentsSystemsModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Function Contribution Target</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Function Contribution Target</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFunctionContributionTarget(final FunctionContributionTarget object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>System</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>System</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSystem(final de.jpwinkler.daf.fap5.model.componentssystems.System object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Component</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComponent(final Component object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Logical Component</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Logical Component</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLogicalComponent(final LogicalComponent object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Functionality</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Functionality</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFunctionality(final Functionality object) {
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

} //ComponentsSystemsSwitch
