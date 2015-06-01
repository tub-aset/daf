/**
 */
package de.jpwinkler.daf.fap5.model.codebeamer;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Issue</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.Issue#getSeverity <em>Severity</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.Issue#getDescription <em>Description</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.Issue#getIssueType <em>Issue Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getIssue()
 * @model
 * @generated
 */
public interface Issue extends ModelObject {
	/**
     * Returns the value of the '<em><b>Severity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Severity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Severity</em>' attribute.
     * @see #setSeverity(long)
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getIssue_Severity()
     * @model
     * @generated
     */
	long getSeverity();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.codebeamer.Issue#getSeverity <em>Severity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Severity</em>' attribute.
     * @see #getSeverity()
     * @generated
     */
	void setSeverity(long value);

	/**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getIssue_Description()
     * @model
     * @generated
     */
	String getDescription();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.codebeamer.Issue#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
	void setDescription(String value);

	/**
     * Returns the value of the '<em><b>Issue Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Issue Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Issue Type</em>' attribute.
     * @see #setIssueType(String)
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getIssue_IssueType()
     * @model
     * @generated
     */
	String getIssueType();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.codebeamer.Issue#getIssueType <em>Issue Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Issue Type</em>' attribute.
     * @see #getIssueType()
     * @generated
     */
	void setIssueType(String value);

} // Issue
