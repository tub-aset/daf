/**
 */
package de.jpwinkler.daf.fap5.model.issuehistory.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl;
import de.jpwinkler.daf.dafcore.util.ECollectors;
import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryModel;
import de.jpwinkler.daf.fap5.model.issuehistory.IssueHistoryPackage;
import de.jpwinkler.daf.fap5.model.issuehistory.Version;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.issuehistory.impl.IssueHistoryModelImpl#getVersions <em>Versions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IssueHistoryModelImpl extends ModelObjectImpl implements IssueHistoryModel {
    /**
     * The cached value of the '{@link #getVersions() <em>Versions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersions()
     * @generated
     * @ordered
     */
    protected EList<Version> versions;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IssueHistoryModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return IssueHistoryPackage.Literals.ISSUE_HISTORY_MODEL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<Version> getVersions() {
        if (versions == null) {
            versions = new EObjectContainmentEList<Version>(Version.class, this, IssueHistoryPackage.ISSUE_HISTORY_MODEL__VERSIONS);
        }
        return versions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public EList<String> getDocumentNames() {
        return getVersions().stream().map(v -> v.getDocuments()).flatMap(List::stream).map(d -> d.getName()).distinct().collect(ECollectors.toEList());
    }

	    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public EList<String> getIssueTypes() {
        return getVersions().stream().map(v -> v.getDocuments()).flatMap(List::stream).map(d -> d.getIssueTypes()).flatMap(List::stream).distinct().collect(ECollectors.toEList());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case IssueHistoryPackage.ISSUE_HISTORY_MODEL__VERSIONS:
                return ((InternalEList<?>)getVersions()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IssueHistoryPackage.ISSUE_HISTORY_MODEL__VERSIONS:
                return getVersions();
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
            case IssueHistoryPackage.ISSUE_HISTORY_MODEL__VERSIONS:
                getVersions().clear();
                getVersions().addAll((Collection<? extends Version>)newValue);
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
            case IssueHistoryPackage.ISSUE_HISTORY_MODEL__VERSIONS:
                getVersions().clear();
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
            case IssueHistoryPackage.ISSUE_HISTORY_MODEL__VERSIONS:
                return versions != null && !versions.isEmpty();
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
            case IssueHistoryPackage.ISSUE_HISTORY_MODEL___GET_DOCUMENT_NAMES:
                return getDocumentNames();
            case IssueHistoryPackage.ISSUE_HISTORY_MODEL___GET_ISSUE_TYPES:
                return getIssueTypes();
        }
        return super.eInvoke(operationID, arguments);
    }

} //IssueHistoryModelImpl
