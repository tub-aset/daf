/**
 */
package de.jpwinkler.daf.dafcore.model.csv.impl;

import de.jpwinkler.daf.dafcore.model.csv.CSVPackage;
import de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unresolved Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.UnresolvedLinkImpl#getTargetModule <em>Target Module</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.UnresolvedLinkImpl#getTargetObject <em>Target Object</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UnresolvedLinkImpl extends LinkImpl implements UnresolvedLink {
    /**
     * The default value of the '{@link #getTargetModule() <em>Target Module</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetModule()
     * @generated
     * @ordered
     */
    protected static final String TARGET_MODULE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetModule() <em>Target Module</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetModule()
     * @generated
     * @ordered
     */
    protected String targetModule = TARGET_MODULE_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetObject() <em>Target Object</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetObject()
     * @generated
     * @ordered
     */
    protected static final String TARGET_OBJECT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetObject() <em>Target Object</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetObject()
     * @generated
     * @ordered
     */
    protected String targetObject = TARGET_OBJECT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected UnresolvedLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CSVPackage.Literals.UNRESOLVED_LINK;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTargetModule() {
        return targetModule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetModule(String newTargetModule) {
        String oldTargetModule = targetModule;
        targetModule = newTargetModule;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.UNRESOLVED_LINK__TARGET_MODULE, oldTargetModule, targetModule));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTargetObject() {
        return targetObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetObject(String newTargetObject) {
        String oldTargetObject = targetObject;
        targetObject = newTargetObject;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.UNRESOLVED_LINK__TARGET_OBJECT, oldTargetObject, targetObject));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case CSVPackage.UNRESOLVED_LINK__TARGET_MODULE:
                return getTargetModule();
            case CSVPackage.UNRESOLVED_LINK__TARGET_OBJECT:
                return getTargetObject();
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
            case CSVPackage.UNRESOLVED_LINK__TARGET_MODULE:
                setTargetModule((String)newValue);
                return;
            case CSVPackage.UNRESOLVED_LINK__TARGET_OBJECT:
                setTargetObject((String)newValue);
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
            case CSVPackage.UNRESOLVED_LINK__TARGET_MODULE:
                setTargetModule(TARGET_MODULE_EDEFAULT);
                return;
            case CSVPackage.UNRESOLVED_LINK__TARGET_OBJECT:
                setTargetObject(TARGET_OBJECT_EDEFAULT);
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
            case CSVPackage.UNRESOLVED_LINK__TARGET_MODULE:
                return TARGET_MODULE_EDEFAULT == null ? targetModule != null : !TARGET_MODULE_EDEFAULT.equals(targetModule);
            case CSVPackage.UNRESOLVED_LINK__TARGET_OBJECT:
                return TARGET_OBJECT_EDEFAULT == null ? targetObject != null : !TARGET_OBJECT_EDEFAULT.equals(targetObject);
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
        result.append(" (targetModule: ");
        result.append(targetModule);
        result.append(", targetObject: ");
        result.append(targetObject);
        result.append(')');
        return result.toString();
    }

} //UnresolvedLinkImpl
