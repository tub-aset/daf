/**
 */
package de.jpwinkler.daf.fap5.model.codebeamer.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Issue</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.IssueImpl#getSeverity <em>Severity</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.IssueImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.IssueImpl#getIssueType <em>Issue Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IssueImpl extends ModelObjectImpl implements Issue {
	/**
     * The default value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSeverity()
     * @generated
     * @ordered
     */
	protected static final long SEVERITY_EDEFAULT = 0L;

	/**
     * The cached value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSeverity()
     * @generated
     * @ordered
     */
	protected long severity = SEVERITY_EDEFAULT;

	/**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
     * The default value of the '{@link #getIssueType() <em>Issue Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIssueType()
     * @generated
     * @ordered
     */
	protected static final String ISSUE_TYPE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getIssueType() <em>Issue Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIssueType()
     * @generated
     * @ordered
     */
	protected String issueType = ISSUE_TYPE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected IssueImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return CodebeamerPackage.Literals.ISSUE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public long getSeverity() {
        return severity;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSeverity(long newSeverity) {
        long oldSeverity = severity;
        severity = newSeverity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CodebeamerPackage.ISSUE__SEVERITY, oldSeverity, severity));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getDescription() {
        return description;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CodebeamerPackage.ISSUE__DESCRIPTION, oldDescription, description));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getIssueType() {
        return issueType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setIssueType(String newIssueType) {
        String oldIssueType = issueType;
        issueType = newIssueType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CodebeamerPackage.ISSUE__ISSUE_TYPE, oldIssueType, issueType));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case CodebeamerPackage.ISSUE__SEVERITY:
                return getSeverity();
            case CodebeamerPackage.ISSUE__DESCRIPTION:
                return getDescription();
            case CodebeamerPackage.ISSUE__ISSUE_TYPE:
                return getIssueType();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case CodebeamerPackage.ISSUE__SEVERITY:
                setSeverity((Long)newValue);
                return;
            case CodebeamerPackage.ISSUE__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case CodebeamerPackage.ISSUE__ISSUE_TYPE:
                setIssueType((String)newValue);
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
            case CodebeamerPackage.ISSUE__SEVERITY:
                setSeverity(SEVERITY_EDEFAULT);
                return;
            case CodebeamerPackage.ISSUE__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case CodebeamerPackage.ISSUE__ISSUE_TYPE:
                setIssueType(ISSUE_TYPE_EDEFAULT);
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
            case CodebeamerPackage.ISSUE__SEVERITY:
                return severity != SEVERITY_EDEFAULT;
            case CodebeamerPackage.ISSUE__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case CodebeamerPackage.ISSUE__ISSUE_TYPE:
                return ISSUE_TYPE_EDEFAULT == null ? issueType != null : !ISSUE_TYPE_EDEFAULT.equals(issueType);
        }
        return super.eIsSet(featureID);
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
        result.append(" (severity: ");
        result.append(severity);
        result.append(", description: ");
        result.append(description);
        result.append(", issueType: ");
        result.append(issueType);
        result.append(')');
        return result.toString();
    }

} //IssueImpl
