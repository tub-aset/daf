/**
 */
package de.jpwinkler.daf.doorsdb.doorsdbmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import de.jpwinkler.daf.doorsdb.util.DoorsDBVisitor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors DB</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getDbLocation <em>Db Location</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getTags <em>Tags</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage#getDoorsDB()
 * @model
 * @generated
 */
public interface DoorsDB extends EObject {
    /**
     * Returns the value of the '<em><b>Db Location</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Db Location</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Db Location</em>' attribute.
     * @see #setDbLocation(String)
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage#getDoorsDB_DbLocation()
     * @model
     * @generated
     */
    String getDbLocation();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB#getDbLocation <em>Db Location</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Db Location</em>' attribute.
     * @see #getDbLocation()
     * @generated
     */
    void setDbLocation(String value);

    /**
     * Returns the value of the '<em><b>Tags</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tags</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tags</em>' containment reference list.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage#getDoorsDB_Tags()
     * @model containment="true"
     * @generated
     */
    EList<DBTag> getTags();

    /**
     * Returns the value of the '<em><b>Root</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Root</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Root</em>' containment reference.
     * @see #setRoot(DBFolder)
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage#getDoorsDB_Root()
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
     * @model visitorDataType="de.jpwinkler.doors_db.doorsdbmodel.DoorsDBVisitor"
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
