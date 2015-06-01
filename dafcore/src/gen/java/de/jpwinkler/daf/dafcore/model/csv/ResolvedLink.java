/**
 */
package de.jpwinkler.daf.dafcore.model.csv;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resolved Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.ResolvedLink#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getResolvedLink()
 * @model
 * @generated
 */
public interface ResolvedLink extends Link {
    /**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getIncomingLinks <em>Incoming Links</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(DoorsObject)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getResolvedLink_Target()
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getIncomingLinks
     * @model opposite="incomingLinks"
     * @generated
     */
    DoorsObject getTarget();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.ResolvedLink#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(DoorsObject value);

} // ResolvedLink
