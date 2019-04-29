/**
 */
package de.jpwinkler.daf.doorsdb.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.doorsdb.model.DoorsDBPackage
 * @generated
 */
public interface DoorsDBFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DoorsDBFactory eINSTANCE = de.jpwinkler.daf.doorsdb.model.impl.DoorsDBFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>DB Folder</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DB Folder</em>'.
	 * @generated
	 */
	DBFolder createDBFolder();

	/**
	 * Returns a new object of class '<em>DB Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DB Module</em>'.
	 * @generated
	 */
	DBModule createDBModule();

	/**
	 * Returns a new object of class '<em>DB Version</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DB Version</em>'.
	 * @generated
	 */
	DBVersion createDBVersion();

	/**
	 * Returns a new object of class '<em>Doors DB</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Doors DB</em>'.
	 * @generated
	 */
	DoorsDB createDoorsDB();

	/**
	 * Returns a new object of class '<em>DB Tag</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DB Tag</em>'.
	 * @generated
	 */
	DBTag createDBTag();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DoorsDBPackage getDoorsDBPackage();

} //DoorsDBFactory
