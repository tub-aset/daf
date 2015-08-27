/**
 */
package de.jpwinkler.daf.dafcore.model.csv;

import de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.dafcore.model.common.ModelObject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors Tree Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode#getChildren <em>Children</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode#getParent <em>Parent</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode#getAttributes <em>Attributes</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsTreeNode()
 * @model
 * @generated
 */
public interface DoorsTreeNode extends ModelObject {
    /**
     * Returns the value of the '<em><b>Children</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode}.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsTreeNode_Children()
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode#getParent
     * @model opposite="parent" containment="true"
     * @generated
     */
    EList<DoorsTreeNode> getChildren();

    /**
     * Returns the value of the '<em><b>Parent</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' container reference.
     * @see #setParent(DoorsTreeNode)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsTreeNode_Parent()
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode#getChildren
     * @model opposite="children" transient="false"
     * @generated
     */
    DoorsTreeNode getParent();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNode#getParent <em>Parent</em>}' container reference.
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
     * <p>
     * If the meaning of the '<em>Attributes</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attributes</em>' map.
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsTreeNode_Attributes()
     * @model mapType="de.jpwinkler.daf.dafcore.model.csv.StringToStringMap<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" ordered="false"
     * @generated
     */
    EMap<String, String> getAttributes();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model visitorDataType="de.jpwinkler.daf.dafcore.model.csv.DoorsTreeNodeVisitor"
     * @generated
     */
    void accept(DoorsTreeNodeVisitor visitor);

} // DoorsTreeNode
