/**
 */
package de.jpwinkler.daf.doorsdb.doorsdbmodel.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBModuleImpl#getVersions <em>Versions</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBModuleImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBModuleImpl#getTags <em>Tags</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DBModuleImpl extends DBItemImpl implements DBModule {
    /**
     * The cached value of the '{@link #getVersions() <em>Versions</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersions()
     * @generated
     * @ordered
     */
    protected EList<DBVersion> versions;

    /**
     * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUrl()
     * @generated
     * @ordered
     */
    protected static final String URL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUrl()
     * @generated
     * @ordered
     */
    protected String url = URL_EDEFAULT;

    /**
     * The cached value of the '{@link #getTags() <em>Tags</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTags()
     * @generated
     * @ordered
     */
    protected EList<DBTag> tags;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DBModuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DoorsDBModelPackage.Literals.DB_MODULE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<DBVersion> getVersions() {
        if (versions == null) {
            versions = new EObjectContainmentWithInverseEList<DBVersion>(DBVersion.class, this, DoorsDBModelPackage.DB_MODULE__VERSIONS, DoorsDBModelPackage.DB_VERSION__MODULE);
        }
        return versions;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getUrl() {
        return url;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setUrl(String newUrl) {
        String oldUrl = url;
        url = newUrl;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DoorsDBModelPackage.DB_MODULE__URL, oldUrl, url));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<DBTag> getTags() {
        if (tags == null) {
            tags = new EObjectWithInverseResolvingEList.ManyInverse<DBTag>(DBTag.class, this, DoorsDBModelPackage.DB_MODULE__TAGS, DoorsDBModelPackage.DB_TAG__MODULES);
        }
        return tags;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public DBVersion getLatestVersion() {
        DBVersion latest = null;
        for (final DBVersion version : getVersions()) {
            if (latest == null || latest.getDate().before(version.getDate())) {
                latest = version;
            }
        }
        return latest;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public boolean hasTag(final String tag) {
        return getTags().stream().anyMatch(t -> t.getName().equals(tag));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DoorsDBModelPackage.DB_MODULE__VERSIONS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getVersions()).basicAdd(otherEnd, msgs);
            case DoorsDBModelPackage.DB_MODULE__TAGS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getTags()).basicAdd(otherEnd, msgs);
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
            case DoorsDBModelPackage.DB_MODULE__VERSIONS:
                return ((InternalEList<?>)getVersions()).basicRemove(otherEnd, msgs);
            case DoorsDBModelPackage.DB_MODULE__TAGS:
                return ((InternalEList<?>)getTags()).basicRemove(otherEnd, msgs);
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
            case DoorsDBModelPackage.DB_MODULE__VERSIONS:
                return getVersions();
            case DoorsDBModelPackage.DB_MODULE__URL:
                return getUrl();
            case DoorsDBModelPackage.DB_MODULE__TAGS:
                return getTags();
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
            case DoorsDBModelPackage.DB_MODULE__VERSIONS:
                getVersions().clear();
                getVersions().addAll((Collection<? extends DBVersion>)newValue);
                return;
            case DoorsDBModelPackage.DB_MODULE__URL:
                setUrl((String)newValue);
                return;
            case DoorsDBModelPackage.DB_MODULE__TAGS:
                getTags().clear();
                getTags().addAll((Collection<? extends DBTag>)newValue);
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
            case DoorsDBModelPackage.DB_MODULE__VERSIONS:
                getVersions().clear();
                return;
            case DoorsDBModelPackage.DB_MODULE__URL:
                setUrl(URL_EDEFAULT);
                return;
            case DoorsDBModelPackage.DB_MODULE__TAGS:
                getTags().clear();
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
            case DoorsDBModelPackage.DB_MODULE__VERSIONS:
                return versions != null && !versions.isEmpty();
            case DoorsDBModelPackage.DB_MODULE__URL:
                return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
            case DoorsDBModelPackage.DB_MODULE__TAGS:
                return tags != null && !tags.isEmpty();
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
            case DoorsDBModelPackage.DB_MODULE___GET_LATEST_VERSION:
                return getLatestVersion();
            case DoorsDBModelPackage.DB_MODULE___HAS_TAG__STRING:
                return hasTag((String)arguments.get(0));
        }
        return super.eInvoke(operationID, arguments);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        return getName();
    }

} //DBModuleImpl
