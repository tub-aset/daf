/**
 */
package de.jpwinkler.daf.fap5.model.codebeamer.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage;
import de.jpwinkler.daf.fap5.model.codebeamer.DoubleMetric;
import de.jpwinkler.daf.fap5.model.codebeamer.IntMetric;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;
import de.jpwinkler.daf.fap5.model.codebeamer.Metric;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage
 * @generated
 */
public class CodebeamerAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static CodebeamerPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CodebeamerAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = CodebeamerPackage.eINSTANCE;
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
    protected CodebeamerSwitch<Adapter> modelSwitch =
            new CodebeamerSwitch<Adapter>() {
        @Override
        public Adapter caseCodeBeamerModel(final CodeBeamerModel object) {
            return createCodeBeamerModelAdapter();
        }
        @Override
        public Adapter caseIssue(final Issue object) {
            return createIssueAdapter();
        }
        @Override
        public Adapter caseMetric(final Metric object) {
            return createMetricAdapter();
        }
        @Override
        public Adapter caseIntMetric(final IntMetric object) {
            return createIntMetricAdapter();
        }
        @Override
        public Adapter caseDoubleMetric(final DoubleMetric object) {
            return createDoubleMetricAdapter();
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
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel <em>Code Beamer Model</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel
     * @generated
     */
    public Adapter createCodeBeamerModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.codebeamer.Issue <em>Issue</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.Issue
     * @generated
     */
    public Adapter createIssueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.codebeamer.Metric <em>Metric</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.Metric
     * @generated
     */
    public Adapter createMetricAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.codebeamer.IntMetric <em>Int Metric</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.IntMetric
     * @generated
     */
    public Adapter createIntMetricAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.fap5.model.codebeamer.DoubleMetric <em>Double Metric</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.DoubleMetric
     * @generated
     */
    public Adapter createDoubleMetricAdapter() {
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

} //CodebeamerAdapterFactory
