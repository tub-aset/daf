/**
 */
package de.jpwinkler.daf.fap5.model.issuehistory;

import org.eclipse.emf.common.util.EList;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel#getVersions <em>Versions</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryPackage#getIssueHistoryModel()
 * @model
 * @generated
 */
public interface IssueHistoryModel extends ModelObject {
	/**
     * Returns the value of the '<em><b>Versions</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.issuehistory.Version}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Versions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Versions</em>' containment reference list.
     * @see de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryPackage#getIssueHistoryModel_Versions()
     * @model containment="true"
     * @generated
     */
	EList<Version> getVersions();

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	EList<String> getDocumentNames();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    EList<String> getIssueTypes();

} // IssueHistoryModel
