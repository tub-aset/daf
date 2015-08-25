/**
 */
package de.jpwinkler.daf.dafcore.model.common;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.common.ModelObject#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.dafcore.model.common.CommonPackage#getModelObject()
 * @model abstract="true"
 * @generated
 */
public interface ModelObject extends EObject {
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(DoorsObject)
     * @see de.jpwinkler.daf.dafcore.model.common.CommonPackage#getModelObject_Source()
     * @model
     * @generated
     */
    DoorsObject getSource();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.common.ModelObject#getSource <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(DoorsObject value);

} // ModelObject
