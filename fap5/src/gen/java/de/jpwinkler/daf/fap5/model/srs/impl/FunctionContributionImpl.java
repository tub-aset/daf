/**
 */
package de.jpwinkler.daf.fap5.model.srs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;
import de.jpwinkler.daf.fap5.model.srs.FunctionContribution;
import de.jpwinkler.daf.fap5.model.srs.SrsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Contribution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.impl.FunctionContributionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.impl.FunctionContributionImpl#getText <em>Text</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FunctionContributionImpl extends FunctionalSpecificationElementImpl implements FunctionContribution {
	/**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
	protected FunctionContributionTarget target;

	/**
     * The default value of the '{@link #getText() <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getText()
     * @generated
     * @ordered
     */
	protected static final String TEXT_EDEFAULT = null;
	/**
     * The cached value of the '{@link #getText() <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getText()
     * @generated
     * @ordered
     */
	protected String text = TEXT_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected FunctionContributionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return SrsPackage.Literals.FUNCTION_CONTRIBUTION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FunctionContributionTarget getTarget() {
        if (target != null && target.eIsProxy()) {
            InternalEObject oldTarget = (InternalEObject)target;
            target = (FunctionContributionTarget)eResolveProxy(oldTarget);
            if (target != oldTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SrsPackage.FUNCTION_CONTRIBUTION__TARGET, oldTarget, target));
            }
        }
        return target;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FunctionContributionTarget basicGetTarget() {
        return target;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetTarget(FunctionContributionTarget newTarget, NotificationChain msgs) {
        FunctionContributionTarget oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SrsPackage.FUNCTION_CONTRIBUTION__TARGET, oldTarget, newTarget);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTarget(FunctionContributionTarget newTarget) {
        if (newTarget != target) {
            NotificationChain msgs = null;
            if (target != null)
                msgs = ((InternalEObject)target).eInverseRemove(this, ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS, FunctionContributionTarget.class, msgs);
            if (newTarget != null)
                msgs = ((InternalEObject)newTarget).eInverseAdd(this, ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS, FunctionContributionTarget.class, msgs);
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SrsPackage.FUNCTION_CONTRIBUTION__TARGET, newTarget, newTarget));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getText() {
        return text;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setText(String newText) {
        String oldText = text;
        text = newText;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SrsPackage.FUNCTION_CONTRIBUTION__TEXT, oldText, text));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SrsPackage.FUNCTION_CONTRIBUTION__TARGET:
                if (target != null)
                    msgs = ((InternalEObject)target).eInverseRemove(this, ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS, FunctionContributionTarget.class, msgs);
                return basicSetTarget((FunctionContributionTarget)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SrsPackage.FUNCTION_CONTRIBUTION__TARGET:
                return basicSetTarget(null, msgs);
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
            case SrsPackage.FUNCTION_CONTRIBUTION__TARGET:
                if (resolve) return getTarget();
                return basicGetTarget();
            case SrsPackage.FUNCTION_CONTRIBUTION__TEXT:
                return getText();
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
            case SrsPackage.FUNCTION_CONTRIBUTION__TARGET:
                setTarget((FunctionContributionTarget)newValue);
                return;
            case SrsPackage.FUNCTION_CONTRIBUTION__TEXT:
                setText((String)newValue);
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
            case SrsPackage.FUNCTION_CONTRIBUTION__TARGET:
                setTarget((FunctionContributionTarget)null);
                return;
            case SrsPackage.FUNCTION_CONTRIBUTION__TEXT:
                setText(TEXT_EDEFAULT);
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
            case SrsPackage.FUNCTION_CONTRIBUTION__TARGET:
                return target != null;
            case SrsPackage.FUNCTION_CONTRIBUTION__TEXT:
                return TEXT_EDEFAULT == null ? text != null : !TEXT_EDEFAULT.equals(text);
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
        result.append(" (text: ");
        result.append(text);
        result.append(')');
        return result.toString();
    }

} //FunctionContributionImpl
