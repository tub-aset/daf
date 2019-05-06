/**
 */
package de.jpwinkler.daf.doorscsv.model;

import de.jpwinkler.daf.doorscsv.DoorsTreeNodeVisitor;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors Tree Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsTreeNode#getChildren <em>Children</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsTreeNode#getParent <em>Parent</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsTreeNode#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsTreeNode#getFullName <em>Full Name</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.doorscsv.model.DoorsPackage#getDoorsTreeNode()
 * @model
 * @generated
 */
public interface DoorsTreeNode extends EObject {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link de.jpwinkler.daf.doorscsv.model.DoorsTreeNode}.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.doorscsv.model.DoorsTreeNode#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsPackage#getDoorsTreeNode_Children()
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsTreeNode#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<DoorsTreeNode> getChildren();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.doorscsv.model.DoorsTreeNode#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(DoorsTreeNode)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsPackage#getDoorsTreeNode_Parent()
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsTreeNode#getChildren
	 * @model opposite="children" transient="false"
	 * @generated
	 */
	DoorsTreeNode getParent();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsTreeNode#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(DoorsTreeNode value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' map.
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsPackage#getDoorsTreeNode_Attributes()
	 * @model mapType="de.jpwinkler.daf.doorscsv.model.StringToStringMap&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;" ordered="false"
	 * @generated
	 */
	EMap<String, String> getAttributes();

	/**
	 * Returns the value of the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Full Name</em>' attribute.
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsPackage#getDoorsTreeNode_FullName()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getFullName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model visitorDataType="de.jpwinkler.daf.doorscsv.model.DoorsTreeNodeVisitor"
	 * @generated
	 */
	void accept(DoorsTreeNodeVisitor visitor);

} // DoorsTreeNode
