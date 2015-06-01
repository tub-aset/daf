/**
 */
package de.jpwinkler.daf.dafcore.model.csv;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.Link#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getLink()
 * @model abstract="true"
 * @generated
 */
public interface Link extends EObject {

    /**
     * Returns the value of the '<em><b>Source</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getOutgoingLinks <em>Outgoing Links</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' container reference.
     * @see #setSource(DoorsObject)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getLink_Source()
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getOutgoingLinks
     * @model opposite="outgoingLinks" transient="false"
     * @generated
     */
    DoorsObject getSource();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.Link#getSource <em>Source</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' container reference.
     * @see #getSource()
     * @generated
     */
    void setSource(DoorsObject value);
} // Link
