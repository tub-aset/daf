/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.jpwinkler.daf.fap5.model.componentssystems.Component;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsFactory;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;
import de.jpwinkler.daf.fap5.model.componentssystems.Functionality;
import de.jpwinkler.daf.fap5.model.componentssystems.LogicalComponent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentsSystemsFactoryImpl extends EFactoryImpl implements ComponentsSystemsFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static ComponentsSystemsFactory init() {
        try {
            ComponentsSystemsFactory theComponentsSystemsFactory = (ComponentsSystemsFactory)EPackage.Registry.INSTANCE.getEFactory(ComponentsSystemsPackage.eNS_URI);
            if (theComponentsSystemsFactory != null) {
                return theComponentsSystemsFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ComponentsSystemsFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ComponentsSystemsFactoryImpl() {
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
            case ComponentsSystemsPackage.COMPONENTS_SYSTEMS_MODEL: return createComponentsSystemsModel();
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET: return createFunctionContributionTarget();
            case ComponentsSystemsPackage.SYSTEM: return createSystem();
            case ComponentsSystemsPackage.COMPONENT: return createComponent();
            case ComponentsSystemsPackage.LOGICAL_COMPONENT: return createLogicalComponent();
            case ComponentsSystemsPackage.FUNCTIONALITY: return createFunctionality();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ComponentsSystemsModel createComponentsSystemsModel() {
        ComponentsSystemsModelImpl componentsSystemsModel = new ComponentsSystemsModelImpl();
        return componentsSystemsModel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FunctionContributionTarget createFunctionContributionTarget() {
        FunctionContributionTargetImpl functionContributionTarget = new FunctionContributionTargetImpl();
        return functionContributionTarget;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public de.jpwinkler.daf.fap5.model.componentssystems.System createSystem() {
        SystemImpl system = new SystemImpl();
        return system;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Component createComponent() {
        ComponentImpl component = new ComponentImpl();
        return component;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public LogicalComponent createLogicalComponent() {
        LogicalComponentImpl logicalComponent = new LogicalComponentImpl();
        return logicalComponent;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Functionality createFunctionality() {
        FunctionalityImpl functionality = new FunctionalityImpl();
        return functionality;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ComponentsSystemsPackage getComponentsSystemsPackage() {
        return (ComponentsSystemsPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static ComponentsSystemsPackage getPackage() {
        return ComponentsSystemsPackage.eINSTANCE;
    }

} //ComponentsSystemsFactoryImpl
