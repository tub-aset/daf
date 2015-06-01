/**
 */
package de.jpwinkler.daf.fap5.model.srs;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.fap5.model.srs.SrsPackage
 * @generated
 */
public interface SrsFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	SrsFactory eINSTANCE = de.jpwinkler.daf.fap5.model.srs.impl.SrsFactoryImpl.init();

	/**
     * Returns a new object of class '<em>SRS Model</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>SRS Model</em>'.
     * @generated
     */
	SRSModel createSRSModel();

	/**
     * Returns a new object of class '<em>Vehicle Function</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Vehicle Function</em>'.
     * @generated
     */
	VehicleFunction createVehicleFunction();

	/**
     * Returns a new object of class '<em>Function Contribution</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Function Contribution</em>'.
     * @generated
     */
	FunctionContribution createFunctionContribution();

	/**
     * Returns a new object of class '<em>Functional Specification Container</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Functional Specification Container</em>'.
     * @generated
     */
	FunctionalSpecificationContainer createFunctionalSpecificationContainer();

	/**
     * Returns a new object of class '<em>Precondition</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Precondition</em>'.
     * @generated
     */
	Precondition createPrecondition();

	/**
     * Returns a new object of class '<em>End Condition</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>End Condition</em>'.
     * @generated
     */
	EndCondition createEndCondition();

	/**
     * Returns a new object of class '<em>Trigger</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Trigger</em>'.
     * @generated
     */
	Trigger createTrigger();

	/**
     * Returns a new object of class '<em>Heading</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Heading</em>'.
     * @generated
     */
	Heading createHeading();

	/**
     * Returns a new object of class '<em>Description</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Description</em>'.
     * @generated
     */
	Description createDescription();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	SrsPackage getSrsPackage();

} //SrsFactory
