/**
 */
package de.jpwinkler.daf.doorsdb.model;

import de.jpwinkler.daf.doorsdb.DoorsDBVisitor;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorsdb.model.DBItem#getChildren <em>Children</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.model.DBItem#getParent <em>Parent</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.model.DBItem#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.model.DBItem#getFullName <em>Full Name</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.doorsdb.model.DoorsDBPackage#getDBItem()
 * @model abstract="true"
 * @generated
 */
public interface DBItem extends EObject {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link de.jpwinkler.daf.doorsdb.model.DBItem}.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.doorsdb.model.DBItem#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see de.jpwinkler.daf.doorsdb.model.DoorsDBPackage#getDBItem_Children()
	 * @see de.jpwinkler.daf.doorsdb.model.DBItem#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<DBItem> getChildren();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.doorsdb.model.DBItem#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(DBItem)
	 * @see de.jpwinkler.daf.doorsdb.model.DoorsDBPackage#getDBItem_Parent()
	 * @see de.jpwinkler.daf.doorsdb.model.DBItem#getChildren
	 * @model opposite="children" transient="false"
	 * @generated
	 */
	DBItem getParent();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorsdb.model.DBItem#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(DBItem value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.jpwinkler.daf.doorsdb.model.DoorsDBPackage#getDBItem_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorsdb.model.DBItem#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Full Name</em>' attribute.
	 * @see de.jpwinkler.daf.doorsdb.model.DoorsDBPackage#getDBItem_FullName()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getFullName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model visitorDataType="de.jpwinkler.daf.doorsdb.model.DoorsDBVisitor"
	 * @generated
	 */
	void accept(DoorsDBVisitor visitor);

} // DBItem
