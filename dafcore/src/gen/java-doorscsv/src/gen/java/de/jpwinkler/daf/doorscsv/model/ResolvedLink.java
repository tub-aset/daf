/**
 */
package de.jpwinkler.daf.doorscsv.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resolved Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.ResolvedLink#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getResolvedLink()
 * @model
 * @generated
 */
public interface ResolvedLink extends Link {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getIncomingLinks <em>Incoming Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(DoorsObject)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getResolvedLink_Target()
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsObject#getIncomingLinks
	 * @model opposite="incomingLinks"
	 * @generated
	 */
	DoorsObject getTarget();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.ResolvedLink#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(DoorsObject value);

} // ResolvedLink
