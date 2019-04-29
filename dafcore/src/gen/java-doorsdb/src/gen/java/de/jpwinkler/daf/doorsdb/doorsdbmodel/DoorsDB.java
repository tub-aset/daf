/**
 */
package de.jpwinkler.daf.doorsdb.doorsdbmodel;

import de.jpwinkler.daf.doorsdb.DoorsDBVisitor;

import org.eclipse.emf.common.util.EList;

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
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getTags <em>Tags</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBPackage#getDoorsDB()
 * @model
 * @generated
 */
public interface DoorsDB extends EObject {
	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference list.
	 * The list contents are of type {@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference list.
	 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBPackage#getDoorsDB_Tags()
	 * @model containment="true"
	 * @generated
	 */
	EList<DBTag> getTags();

	/**
	 * Returns the value of the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' containment reference.
	 * @see #setRoot(DBFolder)
	 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBPackage#getDoorsDB_Root()
	 * @model containment="true"
	 * @generated
	 */
	DBFolder getRoot();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getRoot <em>Root</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' containment reference.
	 * @see #getRoot()
	 * @generated
	 */
	void setRoot(DBFolder value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model visitorDataType="de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBVisitor"
	 * @generated
	 */
	void accept(DoorsDBVisitor visitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	DBTag getTag(String name);

} // DoorsDB
