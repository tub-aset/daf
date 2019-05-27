/**
 */
package de.jpwinkler.daf.model;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors DB</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.model.DoorsDatabase#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsDatabase()
 * @model
 * @generated
 */
public interface DoorsDatabase {
	/**
	 * Returns the value of the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' containment reference.
	 * @see #setRoot(DoorsTreeNode)
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsDatabase_Root()
	 * @model containment="true"
	 * @generated
	 */
	DoorsTreeNode getRoot();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsDatabase#getRoot <em>Root</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' containment reference.
	 * @see #getRoot()
	 * @generated
	 */
	void setRoot(DoorsTreeNode value);

} // DoorsDatabase
