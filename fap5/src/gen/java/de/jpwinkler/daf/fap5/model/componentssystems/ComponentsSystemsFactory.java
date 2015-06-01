/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage
 * @generated
 */
public interface ComponentsSystemsFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	ComponentsSystemsFactory eINSTANCE = de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Model</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Model</em>'.
     * @generated
     */
	ComponentsSystemsModel createComponentsSystemsModel();

	/**
     * Returns a new object of class '<em>Function Contribution Target</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Function Contribution Target</em>'.
     * @generated
     */
	FunctionContributionTarget createFunctionContributionTarget();

	/**
     * Returns a new object of class '<em>System</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>System</em>'.
     * @generated
     */
	System createSystem();

	/**
     * Returns a new object of class '<em>Component</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Component</em>'.
     * @generated
     */
	Component createComponent();

	/**
     * Returns a new object of class '<em>Logical Component</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Logical Component</em>'.
     * @generated
     */
	LogicalComponent createLogicalComponent();

	/**
     * Returns a new object of class '<em>Functionality</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Functionality</em>'.
     * @generated
     */
	Functionality createFunctionality();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	ComponentsSystemsPackage getComponentsSystemsPackage();

} //ComponentsSystemsFactory
