package de.jpwinkler.daf.model;

/*-
 * #%L
 * dafcore
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
     * @return 
     * @generated NOT
     */
    CompletableFuture<List<DoorsTreeNode>> getChildrenAsync(BackgroundTaskExecutor executor);
    
    /**
     * @return 
     * @generated NOT
     */
     boolean isChildrenLoaded();

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
     * @return @generated NOT
     */
    CompletableFuture<Map<String, String>> getAttributesAsync(BackgroundTaskExecutor executor);

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
     * <!-- end-user-doc --> @model
     * visitorDataType="de.jpwinkler.daf.model.DoorsTreeNodeVisitor"
     * @generated
     */
    void accept(DoorsTreeNodeVisitor visitor);

    /**
     * 
     * @generated NOT
     */
    CompletableFuture<Void> acceptAsync(BackgroundTaskExecutor executor, DoorsTreeNodeVisitor visitor);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @model @generated
     */
    boolean hasTag(String tag);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @model
     * patternDataType="de.jpwinkler.daf.model.Pattern"
     * @generated
     */
    boolean hasTag(Pattern pattern);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @model kind="operation"
     * @generated
     */
    List<String> getTags();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @model @generated
     */
    void setTag(String tag);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @model @generated
     */
    void removeTag(String tag);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @model
     * patternDataType="de.jpwinkler.daf.model.Pattern"
     * @generated
     */
    void removeTag(Pattern pattern);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @model @generated
     */
    boolean canCopyFrom(DoorsTreeNode node);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @model @generated NOT
     */
    default DoorsTreeNode getChild(String name) {
        final List<String> pathSegments = Arrays.asList(name.split("/")).stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        DoorsTreeNode current = this;
        for (final String segment : pathSegments.subList(0, pathSegments.size())) {
            current = current.getChildren().stream().filter(c -> segment.equals(c.getName())).findAny().orElse(null);
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    /**
     * @return @generated NOT
     */
    CompletableFuture<DoorsTreeNode> getChildAsync(BackgroundTaskExecutor executor, String name);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @model @generated
     */
    String toString();

} // DoorsTreeNode
