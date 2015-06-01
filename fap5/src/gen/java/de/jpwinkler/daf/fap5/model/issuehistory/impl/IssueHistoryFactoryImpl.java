/**
 */
package de.jpwinkler.daf.fap5.model.issuehistory.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryFactory;
import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel;
import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryPackage;
import de.jpwinkler.daf.fap5.model.issuehistory.Version;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IssueHistoryFactoryImpl extends EFactoryImpl implements IssueHistoryFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static IssueHistoryFactory init() {
        try {
            IssueHistoryFactory theIssueHistoryFactory = (IssueHistoryFactory)EPackage.Registry.INSTANCE.getEFactory(IssueHistoryPackage.eNS_URI);
            if (theIssueHistoryFactory != null) {
                return theIssueHistoryFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new IssueHistoryFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public IssueHistoryFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case IssueHistoryPackage.ISSUE_HISTORY_MODEL: return createIssueHistoryModel();
            case IssueHistoryPackage.VERSION: return createVersion();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public IssueHistoryModel createIssueHistoryModel() {
        IssueHistoryModelImpl issueHistoryModel = new IssueHistoryModelImpl();
        return issueHistoryModel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Version createVersion() {
        VersionImpl version = new VersionImpl();
        return version;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public IssueHistoryPackage getIssueHistoryPackage() {
        return (IssueHistoryPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static IssueHistoryPackage getPackage() {
        return IssueHistoryPackage.eINSTANCE;
    }

} //IssueHistoryFactoryImpl
