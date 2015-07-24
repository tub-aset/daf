/**
 */
package de.jpwinkler.daf.dafcore.model.csv;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage
 * @generated
 */
public interface CSVFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	CSVFactory eINSTANCE = de.jpwinkler.daf.dafcore.model.csv.impl.CSVFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Doors Module</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Doors Module</em>'.
     * @generated
     */
	DoorsModule createDoorsModule();

	/**
     * Returns a new object of class '<em>Doors Object</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Doors Object</em>'.
     * @generated
     */
	DoorsObject createDoorsObject();

	/**
     * Returns a new object of class '<em>Resolved Link</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Resolved Link</em>'.
     * @generated
     */
    ResolvedLink createResolvedLink();

    /**
     * Returns a new object of class '<em>Unresolved Link</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Unresolved Link</em>'.
     * @generated
     */
    UnresolvedLink createUnresolvedLink();

    /**
     * Returns a new object of class '<em>Doors Tree Node</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Doors Tree Node</em>'.
     * @generated
     */
    DoorsTreeNode createDoorsTreeNode();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	CSVPackage getCSVPackage();

} //CSVFactory
