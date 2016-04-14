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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage;
import de.jpwinkler.daf.doorsdb.util.DoorsDBVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Doors DB</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBImpl#getDbLocation <em>Db Location</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DoorsDBImpl#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DoorsDBImpl extends MinimalEObjectImpl.Container implements DoorsDB {
    /**
     * The default value of the '{@link #getDbLocation() <em>Db Location</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDbLocation()
     * @generated
     * @ordered
     */
    protected static final String DB_LOCATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDbLocation() <em>Db Location</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDbLocation()
     * @generated
     * @ordered
     */
    protected String dbLocation = DB_LOCATION_EDEFAULT;

    /**
     * The cached value of the '{@link #getTags() <em>Tags</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTags()
     * @generated
     * @ordered
     */
    protected EList<DBTag> tags;

    /**
     * The cached value of the '{@link #getRoot() <em>Root</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRoot()
     * @generated
     * @ordered
     */
    protected DBFolder root;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DoorsDBImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DoorsDBModelPackage.Literals.DOORS_DB;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getDbLocation() {
        return dbLocation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDbLocation(String newDbLocation) {
        String oldDbLocation = dbLocation;
        dbLocation = newDbLocation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DoorsDBModelPackage.DOORS_DB__DB_LOCATION, oldDbLocation, dbLocation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<DBTag> getTags() {
        if (tags == null) {
            tags = new EObjectContainmentEList<DBTag>(DBTag.class, this, DoorsDBModelPackage.DOORS_DB__TAGS);
        }
        return tags;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DBFolder getRoot() {
        return root;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRoot(DBFolder newRoot, NotificationChain msgs) {
        DBFolder oldRoot = root;
        root = newRoot;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DoorsDBModelPackage.DOORS_DB__ROOT, oldRoot, newRoot);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRoot(DBFolder newRoot) {
        if (newRoot != root) {
            NotificationChain msgs = null;
            if (root != null)
                msgs = ((InternalEObject)root).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DoorsDBModelPackage.DOORS_DB__ROOT, null, msgs);
            if (newRoot != null)
                msgs = ((InternalEObject)newRoot).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DoorsDBModelPackage.DOORS_DB__ROOT, null, msgs);
            msgs = basicSetRoot(newRoot, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DoorsDBModelPackage.DOORS_DB__ROOT, newRoot, newRoot));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public void accept(final DoorsDBVisitor visitor) {
        getRoot().accept(visitor);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public DBTag getTag(final String name) {
        for (final DBTag tag : getTags()) {
            if (tag.getName().equals(name)) {
                return tag;
            }
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DoorsDBModelPackage.DOORS_DB__TAGS:
                return ((InternalEList<?>)getTags()).basicRemove(otherEnd, msgs);
            case DoorsDBModelPackage.DOORS_DB__ROOT:
                return basicSetRoot(null, msgs);
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
            case DoorsDBModelPackage.DOORS_DB__DB_LOCATION:
                return getDbLocation();
            case DoorsDBModelPackage.DOORS_DB__TAGS:
                return getTags();
            case DoorsDBModelPackage.DOORS_DB__ROOT:
                return getRoot();
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
            case DoorsDBModelPackage.DOORS_DB__DB_LOCATION:
                setDbLocation((String)newValue);
                return;
            case DoorsDBModelPackage.DOORS_DB__TAGS:
                getTags().clear();
                getTags().addAll((Collection<? extends DBTag>)newValue);
                return;
            case DoorsDBModelPackage.DOORS_DB__ROOT:
                setRoot((DBFolder)newValue);
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
            case DoorsDBModelPackage.DOORS_DB__DB_LOCATION:
                setDbLocation(DB_LOCATION_EDEFAULT);
                return;
            case DoorsDBModelPackage.DOORS_DB__TAGS:
                getTags().clear();
                return;
            case DoorsDBModelPackage.DOORS_DB__ROOT:
                setRoot((DBFolder)null);
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
            case DoorsDBModelPackage.DOORS_DB__DB_LOCATION:
                return DB_LOCATION_EDEFAULT == null ? dbLocation != null : !DB_LOCATION_EDEFAULT.equals(dbLocation);
            case DoorsDBModelPackage.DOORS_DB__TAGS:
                return tags != null && !tags.isEmpty();
            case DoorsDBModelPackage.DOORS_DB__ROOT:
                return root != null;
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
            case DoorsDBModelPackage.DOORS_DB___ACCEPT__DOORSDBVISITOR:
                accept((DoorsDBVisitor)arguments.get(0));
                return null;
            case DoorsDBModelPackage.DOORS_DB___GET_TAG__STRING:
                return getTag((String)arguments.get(0));
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
        result.append(" (dbLocation: ");
        result.append(dbLocation);
        result.append(')');
        return result.toString();
    }

} //DoorsDBImpl
