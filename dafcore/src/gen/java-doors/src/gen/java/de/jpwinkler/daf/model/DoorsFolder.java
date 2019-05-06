/**
 */
package de.jpwinkler.daf.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsFolder()
 * @model
 * @generated
 */
public interface DoorsFolder extends DoorsTreeNode {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	DoorsFolder getFolder(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	DoorsModule getModule(String name);

} // DoorsFolder
