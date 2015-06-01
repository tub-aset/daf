/**
 */
package de.jpwinkler.daf.fap5.model.codebeamer;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage
 * @generated
 */
public interface CodebeamerFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	CodebeamerFactory eINSTANCE = de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Code Beamer Model</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Code Beamer Model</em>'.
     * @generated
     */
	CodeBeamerModel createCodeBeamerModel();

	/**
     * Returns a new object of class '<em>Issue</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Issue</em>'.
     * @generated
     */
	Issue createIssue();

	/**
     * Returns a new object of class '<em>Int Metric</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Int Metric</em>'.
     * @generated
     */
    IntMetric createIntMetric();

    /**
     * Returns a new object of class '<em>Double Metric</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Double Metric</em>'.
     * @generated
     */
    DoubleMetric createDoubleMetric();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	CodebeamerPackage getCodebeamerPackage();

} //CodebeamerFactory
