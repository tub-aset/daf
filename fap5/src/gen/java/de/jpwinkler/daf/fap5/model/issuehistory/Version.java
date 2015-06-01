/**
 */
package de.jpwinkler.daf.fap5.model.issuehistory;

import org.eclipse.emf.common.util.EList;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Version</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.issuehistory.Version#getDocuments <em>Documents</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.issuehistory.Version#getVersionNumber <em>Version Number</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryPackage#getVersion()
 * @model
 * @generated
 */
public interface Version extends ModelObject {
	/**
     * Returns the value of the '<em><b>Documents</b></em>' reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documents</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Documents</em>' reference list.
     * @see de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryPackage#getVersion_Documents()
     * @model
     * @generated
     */
	EList<CodeBeamerModel> getDocuments();

	/**
     * Returns the value of the '<em><b>Version Number</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Version Number</em>' attribute.
     * @see #setVersionNumber(String)
     * @see de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryPackage#getVersion_VersionNumber()
     * @model
     * @generated
     */
	String getVersionNumber();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.issuehistory.Version#getVersionNumber <em>Version Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version Number</em>' attribute.
     * @see #getVersionNumber()
     * @generated
     */
	void setVersionNumber(String value);

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model
     * @generated
     */
	CodeBeamerModel getDocument(String name);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    EList<Issue> getIssues(String issueType);

} // Version
