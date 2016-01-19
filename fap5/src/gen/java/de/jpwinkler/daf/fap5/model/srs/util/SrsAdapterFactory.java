/**
 */
package de.jpwinkler.daf.fap5.model.srs.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage
 * @generated
 */
public class SrsAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static SrsPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SrsAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = SrsPackage.eINSTANCE;
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
    public boolean isFactoryForType(Object object) {
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
    protected SrsSwitch<Adapter> modelSwitch =
            new SrsSwitch<Adapter>() {
            @Override
            public Adapter caseSRSModel(SRSModel object) {
                return createSRSModelAdapter();
            }
            @Override
            public Adapter caseVehicleFunction(VehicleFunction object) {
                return createVehicleFunctionAdapter();
            }
            @Override
            public Adapter caseFunctionContribution(FunctionContribution object) {
                return createFunctionContributionAdapter();
            }
            @Override
            public Adapter caseFunctionalSpecificationContainer(FunctionalSpecificationContainer object) {
                return createFunctionalSpecificationContainerAdapter();
            }
            @Override
            public Adapter caseFunctionalSpecificationElement(FunctionalSpecificationElement object) {
                return createFunctionalSpecificationElementAdapter();
            }
            @Override
            public Adapter casePrecondition(Precondition object) {
                return createPreconditionAdapter();
            }
            @Override
            public Adapter caseEndCondition(EndCondition object) {
                return createEndConditionAdapter();
            }
            @Override
            public Adapter caseTrigger(Trigger object) {
                return createTriggerAdapter();
            }
            @Override
            public Adapter caseHeading(Heading object) {
                return createHeadingAdapter();
            }
            @Override
            public Adapter caseDescription(Description object) {
                return createDescriptionAdapter();
            }
            @Override
            public Adapter caseModelObject(ModelObject object) {
                return createModelObjectAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
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
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.srs.SRSModel <em>SRS Model</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.srs.SRSModel
     * @generated
     */
    public Adapter createSRSModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction <em>Vehicle Function</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.srs.VehicleFunction
     * @generated
     */
    public Adapter createVehicleFunctionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.srs.FunctionContribution <em>Function Contribution</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.srs.FunctionContribution
     * @generated
     */
    public Adapter createFunctionContributionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer <em>Functional Specification Container</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer
     * @generated
     */
    public Adapter createFunctionalSpecificationContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationElement <em>Functional Specification Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationElement
     * @generated
     */
    public Adapter createFunctionalSpecificationElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.srs.Precondition <em>Precondition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.srs.Precondition
     * @generated
     */
    public Adapter createPreconditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.srs.EndCondition <em>End Condition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.srs.EndCondition
     * @generated
     */
    public Adapter createEndConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.srs.Trigger <em>Trigger</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.srs.Trigger
     * @generated
     */
    public Adapter createTriggerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.srs.Heading <em>Heading</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.srs.Heading
     * @generated
     */
    public Adapter createHeadingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.srs.Description <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.srs.Description
     * @generated
     */
    public Adapter createDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.dafcore.model.common.ModelObject <em>Model Object</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.dafcore.model.common.ModelObject
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

} //SrsAdapterFactory
