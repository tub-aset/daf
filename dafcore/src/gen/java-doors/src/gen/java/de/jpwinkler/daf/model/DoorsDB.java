/**
 */
package de.jpwinkler.daf.model;


import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors DB</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.model.DoorsDB#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsDB()
 * @model
 * @generated
 */
public interface DoorsDB extends EObject {
	/**
	 * Returns the value of the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' containment reference.
	 * @see #setRoot(DoorsFolder)
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsDB_Root()
	 * @model containment="true"
	 * @generated
	 */
	DoorsFolder getRoot();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsDB#getRoot <em>Root</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' containment reference.
	 * @see #getRoot()
	 * @generated
	 */
	void setRoot(DoorsFolder value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model visitorDataType="de.jpwinkler.daf.model.DoorsTreeNodeVisitor"
	 * @generated
	 */
	void accept(DoorsTreeNodeVisitor visitor);

} // DoorsDB
