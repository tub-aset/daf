/**
 */
package de.jpwinkler.daf.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.model.DoorsCSVPackage
 * @generated
 */
public interface DoorsCSVFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DoorsCSVFactory eINSTANCE = de.jpwinkler.daf.model.impl.DoorsCSVFactoryImpl.init();

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
	 * Returns a new object of class '<em>Doors DB</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Doors DB</em>'.
	 * @generated
	 */
	DoorsDB createDoorsDB();

	/**
	 * Returns a new object of class '<em>Doors Folder</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Doors Folder</em>'.
	 * @generated
	 */
	DoorsFolder createDoorsFolder();

	/**
	 * Returns a new object of class '<em>Doors Database Version</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Doors Database Version</em>'.
	 * @generated
	 */
	DoorsDatabaseVersion createDoorsDatabaseVersion();

	/**
	 * Returns a new object of class '<em>Attribute Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute Definition</em>'.
	 * @generated
	 */
	AttributeDefinition createAttributeDefinition();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DoorsCSVPackage getDoorsCSVPackage();

} //DoorsCSVFactory
