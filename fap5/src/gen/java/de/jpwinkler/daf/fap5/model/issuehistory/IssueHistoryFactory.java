/**
 */
package de.jpwinkler.daf.fap5.model.issuehistory;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryPackage
 * @generated
 */
public interface IssueHistoryFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	IssueHistoryFactory eINSTANCE = de.jpwinkler.daf.fap5.model.issuehistory.impl.IssueHistoryFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Model</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Model</em>'.
     * @generated
     */
	IssueHistoryModel createIssueHistoryModel();

	/**
     * Returns a new object of class '<em>Version</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Version</em>'.
     * @generated
     */
	Version createVersion();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	IssueHistoryPackage getIssueHistoryPackage();

} //IssueHistoryFactory
