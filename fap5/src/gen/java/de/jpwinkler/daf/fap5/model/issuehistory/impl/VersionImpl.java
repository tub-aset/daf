/**
 */
package de.jpwinkler.daf.fap5.model.issuehistory.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl;
import de.jpwinkler.daf.dafcore.util.ECollectors;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;
import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryPackage;
import de.jpwinkler.daf.fap5.model.issuehistory.Version;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Version</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.issuehistory.impl.VersionImpl#getDocuments <em>Documents</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.issuehistory.impl.VersionImpl#getVersionNumber <em>Version Number</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VersionImpl extends ModelObjectImpl implements Version {
    /**
     * The cached value of the '{@link #getDocuments() <em>Documents</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDocuments()
     * @generated
     * @ordered
     */
    protected EList<CodeBeamerModel> documents;

    /**
     * The default value of the '{@link #getVersionNumber() <em>Version Number</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersionNumber()
     * @generated
     * @ordered
     */
    protected static final String VERSION_NUMBER_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getVersionNumber() <em>Version Number</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersionNumber()
     * @generated
     * @ordered
     */
    protected String versionNumber = VERSION_NUMBER_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VersionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IssueHistoryPackage.Literals.VERSION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<CodeBeamerModel> getDocuments() {
        if (documents == null) {
            documents = new EObjectResolvingEList<CodeBeamerModel>(CodeBeamerModel.class, this, IssueHistoryPackage.VERSION__DOCUMENTS);
        }
        return documents;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getVersionNumber() {
        return versionNumber;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setVersionNumber(String newVersionNumber) {
        String oldVersionNumber = versionNumber;
        versionNumber = newVersionNumber;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IssueHistoryPackage.VERSION__VERSION_NUMBER, oldVersionNumber, versionNumber));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public CodeBeamerModel getDocument(final String name) {
        for (final CodeBeamerModel codeBeamerModel : getDocuments()) {
            if (codeBeamerModel.getName().equals(name)) {
                return codeBeamerModel;
            }
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public EList<Issue> getIssues(final String issueType) {
        return getDocuments().stream().map(d -> d.getIssues(issueType)).flatMap(EList::stream).collect(ECollectors.toEList());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IssueHistoryPackage.VERSION__DOCUMENTS:
                return getDocuments();
            case IssueHistoryPackage.VERSION__VERSION_NUMBER:
                return getVersionNumber();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case IssueHistoryPackage.VERSION__DOCUMENTS:
                getDocuments().clear();
                getDocuments().addAll((Collection<? extends CodeBeamerModel>)newValue);
                return;
            case IssueHistoryPackage.VERSION__VERSION_NUMBER:
                setVersionNumber((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case IssueHistoryPackage.VERSION__DOCUMENTS:
                getDocuments().clear();
                return;
            case IssueHistoryPackage.VERSION__VERSION_NUMBER:
                setVersionNumber(VERSION_NUMBER_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case IssueHistoryPackage.VERSION__DOCUMENTS:
                return documents != null && !documents.isEmpty();
            case IssueHistoryPackage.VERSION__VERSION_NUMBER:
                return VERSION_NUMBER_EDEFAULT == null ? versionNumber != null : !VERSION_NUMBER_EDEFAULT.equals(versionNumber);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
        switch (operationID) {
            case IssueHistoryPackage.VERSION___GET_DOCUMENT__STRING:
                return getDocument((String)arguments.get(0));
            case IssueHistoryPackage.VERSION___GET_ISSUES__STRING:
                return getIssues((String)arguments.get(0));
        }
        return super.eInvoke(operationID, arguments);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (versionNumber: ");
        result.append(versionNumber);
        result.append(')');
        return result.toString();
    }

} //VersionImpl
