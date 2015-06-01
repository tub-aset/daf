/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.fap5.model.componentssystems.Component;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;
import de.jpwinkler.daf.fap5.model.componentssystems.Functionality;
import de.jpwinkler.daf.fap5.model.componentssystems.LogicalComponent;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage
 * @generated
 */
public class ComponentsSystemsAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ComponentsSystemsPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentsSystemsAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = ComponentsSystemsPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(final Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComponentsSystemsSwitch<Adapter> modelSwitch =
            new ComponentsSystemsSwitch<Adapter>() {
        @Override
        public Adapter caseComponentsSystemsModel(final ComponentsSystemsModel object) {
            return createComponentsSystemsModelAdapter();
        }
        @Override
        public Adapter caseFunctionContributionTarget(final FunctionContributionTarget object) {
            return createFunctionContributionTargetAdapter();
        }
        @Override
        public Adapter caseSystem(final de.jpwinkler.daf.fap5.model.componentssystems.System object) {
            return createSystemAdapter();
        }
        @Override
        public Adapter caseComponent(final Component object) {
            return createComponentAdapter();
        }
        @Override
        public Adapter caseLogicalComponent(final LogicalComponent object) {
            return createLogicalComponentAdapter();
        }
        @Override
        public Adapter caseFunctionality(final Functionality object) {
            return createFunctionalityAdapter();
        }
        @Override
        public Adapter caseModelObject(final ModelObject object) {
            return createModelObjectAdapter();
        }
        @Override
        public Adapter defaultCase(final EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(final Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel <em>Model</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel
     * @generated
     */
    public Adapter createComponentsSystemsModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget <em>Function Contribution Target</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget
     * @generated
     */
    public Adapter createFunctionContributionTargetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.componentssystems.System <em>System</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.System
     * @generated
     */
    public Adapter createSystemAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.componentssystems.Component <em>Component</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.Component
     * @generated
     */
    public Adapter createComponentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.componentssystems.LogicalComponent <em>Logical Component</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.LogicalComponent
     * @generated
     */
    public Adapter createLogicalComponentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.componentssystems.Functionality <em>Functionality</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.Functionality
     * @generated
     */
    public Adapter createFunctionalityAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.daimler.jonwink.srstp.core.model.common.ModelObject <em>Model Object</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.daimler.jonwink.srstp.core.model.common.ModelObject
     * @generated
     */
    public Adapter createModelObjectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //ComponentsSystemsAdapterFactory
