/**
 */
package de.jpwinkler.daf.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.model.DoorsPackage
 * @generated
 */
public interface DoorsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DoorsFactory eINSTANCE = de.jpwinkler.daf.model.impl.DoorsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Folder</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Folder</em>'.
	 * @generated
	 */
	DoorsFolder createDoorsFolder();

	/**
	 * Returns a new object of class '<em>Database</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Database</em>'.
	 * @generated
	 */
	DoorsDatabase createDoorsDatabase();

	/**
	 * Returns a new object of class '<em>Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Module</em>'.
	 * @generated
	 */
	DoorsModule createDoorsModule();

	/**
	 * Returns a new object of class '<em>Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Object</em>'.
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
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DoorsPackage getDoorsPackage();

} //DoorsFactory
