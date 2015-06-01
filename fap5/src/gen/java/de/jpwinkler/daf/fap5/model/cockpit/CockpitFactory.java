/**
 */
package de.jpwinkler.daf.fap5.model.cockpit;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitPackage
 * @generated
 */
public interface CockpitFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	CockpitFactory eINSTANCE = de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Model</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Model</em>'.
     * @generated
     */
	CockpitModel createCockpitModel();

	/**
     * Returns a new object of class '<em>Vehicle Function Progress</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Vehicle Function Progress</em>'.
     * @generated
     */
	VehicleFunctionProgress createVehicleFunctionProgress();

	/**
     * Returns a new object of class '<em>Function Contribution Target Mapping</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Function Contribution Target Mapping</em>'.
     * @generated
     */
	FunctionContributionTargetMapping createFunctionContributionTargetMapping();

	/**
     * Returns a new object of class '<em>Function Contribution Progress</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Function Contribution Progress</em>'.
     * @generated
     */
	FunctionContributionProgress createFunctionContributionProgress();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	CockpitPackage getCockpitPackage();

} //CockpitFactory
