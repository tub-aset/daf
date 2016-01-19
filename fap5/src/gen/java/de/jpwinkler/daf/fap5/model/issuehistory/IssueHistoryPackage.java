/**
 */
package de.jpwinkler.daf.fap5.model.issuehistory;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import de.jpwinkler.daf.dafcore.model.common.CommonPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryFactory
 * @model kind="package"
 * @generated
 */
public interface IssueHistoryPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "issuehistory";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "de.jpwinkler.daf.fap5.model.issuehistory";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "issuehistory";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	IssueHistoryPackage eINSTANCE = de.jpwinkler.daf.fap5.model.issuehistory.impl.IssueHistoryPackageImpl.init();

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.issuehistory.impl.IssueHistoryModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.issuehistory.impl.IssueHistoryModelImpl
     * @see de.jpwinkler.daf.fap5.model.issuehistory.impl.IssueHistoryPackageImpl#getIssueHistoryModel()
     * @generated
     */
	int ISSUE_HISTORY_MODEL = 0;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ISSUE_HISTORY_MODEL__SOURCE = CommonPackage.MODEL_OBJECT__SOURCE;

	/**
     * The feature id for the '<em><b>Versions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ISSUE_HISTORY_MODEL__VERSIONS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Model</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ISSUE_HISTORY_MODEL_FEATURE_COUNT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
     * The operation id for the '<em>Get Document Names</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ISSUE_HISTORY_MODEL___GET_DOCUMENT_NAMES = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 0;

	/**
     * The operation id for the '<em>Get Issue Types</em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ISSUE_HISTORY_MODEL___GET_ISSUE_TYPES = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 1;

    /**
     * The number of operations of the '<em>Model</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ISSUE_HISTORY_MODEL_OPERATION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 2;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.issuehistory.impl.VersionImpl <em>Version</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.issuehistory.impl.VersionImpl
     * @see de.jpwinkler.daf.fap5.model.issuehistory.impl.IssueHistoryPackageImpl#getVersion()
     * @generated
     */
	int VERSION = 1;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VERSION__SOURCE = CommonPackage.MODEL_OBJECT__SOURCE;

	/**
     * The feature id for the '<em><b>Documents</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VERSION__DOCUMENTS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Version Number</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VERSION__VERSION_NUMBER = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Version</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VERSION_FEATURE_COUNT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
     * The operation id for the '<em>Get Document</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VERSION___GET_DOCUMENT__STRING = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 0;

	/**
     * The operation id for the '<em>Get Issues</em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VERSION___GET_ISSUES__STRING = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 1;

    /**
     * The number of operations of the '<em>Version</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VERSION_OPERATION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 2;


	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel <em>Model</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Model</em>'.
     * @see de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel
     * @generated
     */
	EClass getIssueHistoryModel();

	/**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel#getVersions <em>Versions</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Versions</em>'.
     * @see de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel#getVersions()
     * @see #getIssueHistoryModel()
     * @generated
     */
	EReference getIssueHistoryModel_Versions();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel#getDocumentNames() <em>Get Document Names</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Document Names</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel#getDocumentNames()
     * @generated
     */
	EOperation getIssueHistoryModel__GetDocumentNames();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel#getIssueTypes() <em>Get Issue Types</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Issue Types</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel#getIssueTypes()
     * @generated
     */
    EOperation getIssueHistoryModel__GetIssueTypes();

    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.issuehistory.Version <em>Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Version</em>'.
     * @see de.jpwinkler.daf.fap5.model.issuehistory.Version
     * @generated
     */
	EClass getVersion();

	/**
     * Returns the meta object for the reference list '{@link de.jpwinkler.daf.fap5.model.issuehistory.Version#getDocuments <em>Documents</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Documents</em>'.
     * @see de.jpwinkler.daf.fap5.model.issuehistory.Version#getDocuments()
     * @see #getVersion()
     * @generated
     */
	EReference getVersion_Documents();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.issuehistory.Version#getVersionNumber <em>Version Number</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version Number</em>'.
     * @see de.jpwinkler.daf.fap5.model.issuehistory.Version#getVersionNumber()
     * @see #getVersion()
     * @generated
     */
	EAttribute getVersion_VersionNumber();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.issuehistory.Version#getDocument(java.lang.String) <em>Get Document</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Document</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.issuehistory.Version#getDocument(java.lang.String)
     * @generated
     */
	EOperation getVersion__GetDocument__String();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.issuehistory.Version#getIssues(java.lang.String) <em>Get Issues</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Issues</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.issuehistory.Version#getIssues(java.lang.String)
     * @generated
     */
    EOperation getVersion__GetIssues__String();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	IssueHistoryFactory getIssueHistoryFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.issuehistory.impl.IssueHistoryModelImpl <em>Model</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.issuehistory.impl.IssueHistoryModelImpl
         * @see de.jpwinkler.daf.fap5.model.issuehistory.impl.IssueHistoryPackageImpl#getIssueHistoryModel()
         * @generated
         */
		EClass ISSUE_HISTORY_MODEL = eINSTANCE.getIssueHistoryModel();

		/**
         * The meta object literal for the '<em><b>Versions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ISSUE_HISTORY_MODEL__VERSIONS = eINSTANCE.getIssueHistoryModel_Versions();

		/**
         * The meta object literal for the '<em><b>Get Document Names</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation ISSUE_HISTORY_MODEL___GET_DOCUMENT_NAMES = eINSTANCE.getIssueHistoryModel__GetDocumentNames();

		/**
         * The meta object literal for the '<em><b>Get Issue Types</b></em>' operation.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EOperation ISSUE_HISTORY_MODEL___GET_ISSUE_TYPES = eINSTANCE.getIssueHistoryModel__GetIssueTypes();

        /**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.issuehistory.impl.VersionImpl <em>Version</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.issuehistory.impl.VersionImpl
         * @see de.jpwinkler.daf.fap5.model.issuehistory.impl.IssueHistoryPackageImpl#getVersion()
         * @generated
         */
		EClass VERSION = eINSTANCE.getVersion();

		/**
         * The meta object literal for the '<em><b>Documents</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VERSION__DOCUMENTS = eINSTANCE.getVersion_Documents();

		/**
         * The meta object literal for the '<em><b>Version Number</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VERSION__VERSION_NUMBER = eINSTANCE.getVersion_VersionNumber();

		/**
         * The meta object literal for the '<em><b>Get Document</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation VERSION___GET_DOCUMENT__STRING = eINSTANCE.getVersion__GetDocument__String();

        /**
         * The meta object literal for the '<em><b>Get Issues</b></em>' operation.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EOperation VERSION___GET_ISSUES__STRING = eINSTANCE.getVersion__GetIssues__String();

	}

} //IssueHistoryPackage
