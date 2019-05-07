/**
 */
package de.jpwinkler.daf.model;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors Tree Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.model.DoorsTreeNode#getChildren <em>Children</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsTreeNode#getParent <em>Parent</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsTreeNode#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsTreeNode#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsTreeNode#getFullName <em>Full Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsTreeNode#getFullNameSegments <em>Full Name Segments</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsTreeNode()
 * @model abstract="true"
 * @generated
 */
public interface DoorsTreeNode {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link de.jpwinkler.daf.model.DoorsTreeNode}.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.model.DoorsTreeNode#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsTreeNode_Children()
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	List<DoorsTreeNode> getChildren();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.model.DoorsTreeNode#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(DoorsTreeNode)
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsTreeNode_Parent()
	 * @see de.jpwinkler.daf.model.DoorsTreeNode#getChildren
	 * @model opposite="children" transient="false"
	 * @generated
	 */
	DoorsTreeNode getParent();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsTreeNode#getParent <em>Parent</em>}' container reference.
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
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsTreeNode_Attributes()
	 * @model mapType="de.jpwinkler.daf.model.AttributeMap&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;" ordered="false"
	 * @generated
	 */
	Map<String, String> getAttributes();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsTreeNode_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsTreeNode#getName <em>Name</em>}' attribute.
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
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsTreeNode_FullName()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getFullName();

	/**
	 * Returns the value of the '<em><b>Full Name Segments</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Full Name Segments</em>' attribute list.
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsTreeNode_FullNameSegments()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	List<String> getFullNameSegments();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model visitorDataType="de.jpwinkler.daf.model.DoorsTreeNodeVisitor"
	 * @generated
	 */
	void accept(DoorsTreeNodeVisitor visitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean hasTag(String tag);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model patternDataType="de.jpwinkler.daf.model.Pattern"
	 * @generated
	 */
	boolean hasTag(Pattern pattern);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean canCopyFrom(DoorsTreeNode node);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	DoorsTreeNode copyFrom(DoorsTreeNode node, DoorsTreeNode newParent);

} // DoorsTreeNode
