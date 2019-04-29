/**
 */
package de.jpwinkler.daf.doorsdb.model.impl;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.ecore.util.InternalEList;
import de.jpwinkler.daf.doorsdb.model.DBModule;
import de.jpwinkler.daf.doorsdb.model.DBVersion;
import de.jpwinkler.daf.doorsdb.model.DoorsDBPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB Version</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorsdb.model.impl.DBVersionImpl#getModule <em>Module</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.model.impl.DBVersionImpl#getDate <em>Date</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.model.impl.DBVersionImpl#getAttributes <em>Attributes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DBVersionImpl extends MinimalEObjectImpl.Container implements DBVersion {
    /**
	 * The default value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
    protected static final Date DATE_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
    protected Date date = DATE_EDEFAULT;

    /**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' map.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
    protected EMap<String, String> attributes;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected DBVersionImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return DoorsDBPackage.Literals.DB_VERSION;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public DBModule getModule() {
		if (eContainerFeatureID() != DoorsDBPackage.DB_VERSION__MODULE) return null;
		return (DBModule)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetModule(DBModule newModule, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModule, DoorsDBPackage.DB_VERSION__MODULE, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setModule(DBModule newModule) {
		if (newModule != eInternalContainer() || (eContainerFeatureID() != DoorsDBPackage.DB_VERSION__MODULE && newModule != null)) {
			if (EcoreUtil.isAncestor(this, newModule))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModule != null)
				msgs = ((InternalEObject)newModule).eInverseAdd(this, DoorsDBPackage.DB_MODULE__VERSIONS, DBModule.class, msgs);
			msgs = basicSetModule(newModule, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DoorsDBPackage.DB_VERSION__MODULE, newModule, newModule));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Date getDate() {
		return date;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setDate(Date newDate) {
		Date oldDate = date;
		date = newDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DoorsDBPackage.DB_VERSION__DATE, oldDate, date));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
				public EMap<String, String> getAttributes() {
		if (attributes == null) {
			attributes = new EcoreEMap<String,String>(DoorsDBPackage.Literals.STRING_TO_STRING_MAP, StringToStringMapImpl.class, this, DoorsDBPackage.DB_VERSION__ATTRIBUTES);
		}
		return attributes;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DoorsDBPackage.DB_VERSION__MODULE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModule((DBModule)otherEnd, msgs);
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
			case DoorsDBPackage.DB_VERSION__MODULE:
				return basicSetModule(null, msgs);
			case DoorsDBPackage.DB_VERSION__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case DoorsDBPackage.DB_VERSION__MODULE:
				return eInternalContainer().eInverseRemove(this, DoorsDBPackage.DB_MODULE__VERSIONS, DBModule.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DoorsDBPackage.DB_VERSION__MODULE:
				return getModule();
			case DoorsDBPackage.DB_VERSION__DATE:
				return getDate();
			case DoorsDBPackage.DB_VERSION__ATTRIBUTES:
				if (coreType) return getAttributes();
				else return getAttributes().map();
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
			case DoorsDBPackage.DB_VERSION__MODULE:
				setModule((DBModule)newValue);
				return;
			case DoorsDBPackage.DB_VERSION__DATE:
				setDate((Date)newValue);
				return;
			case DoorsDBPackage.DB_VERSION__ATTRIBUTES:
				((EStructuralFeature.Setting)getAttributes()).set(newValue);
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
			case DoorsDBPackage.DB_VERSION__MODULE:
				setModule((DBModule)null);
				return;
			case DoorsDBPackage.DB_VERSION__DATE:
				setDate(DATE_EDEFAULT);
				return;
			case DoorsDBPackage.DB_VERSION__ATTRIBUTES:
				getAttributes().clear();
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
			case DoorsDBPackage.DB_VERSION__MODULE:
				return getModule() != null;
			case DoorsDBPackage.DB_VERSION__DATE:
				return DATE_EDEFAULT == null ? date != null : !DATE_EDEFAULT.equals(date);
			case DoorsDBPackage.DB_VERSION__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public String toString() {
        return date != null ? date.toString() : "null";
    }

} //DBVersionImpl
