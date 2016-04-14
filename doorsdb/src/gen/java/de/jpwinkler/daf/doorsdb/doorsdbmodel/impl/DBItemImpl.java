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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage;
import de.jpwinkler.daf.doorsdb.util.DoorsDBVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBItemImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBItemImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBItemImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.impl.DBItemImpl#getFullName <em>Full Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DBItemImpl extends MinimalEObjectImpl.Container implements DBItem {
    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildren()
     * @generated
     * @ordered
     */
    protected EList<DBItem> children;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getFullName() <em>Full Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFullName()
     * @generated
     * @ordered
     */
    protected static final String FULL_NAME_EDEFAULT = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DBItemImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DoorsDBModelPackage.Literals.DB_ITEM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<DBItem> getChildren() {
        if (children == null) {
            children = new EObjectContainmentWithInverseEList<DBItem>(DBItem.class, this, DoorsDBModelPackage.DB_ITEM__CHILDREN, DoorsDBModelPackage.DB_ITEM__PARENT);
        }
        return children;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DBItem getParent() {
        if (eContainerFeatureID() != DoorsDBModelPackage.DB_ITEM__PARENT) return null;
        return (DBItem)eInternalContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParent(DBItem newParent, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newParent, DoorsDBModelPackage.DB_ITEM__PARENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setParent(DBItem newParent) {
        if (newParent != eInternalContainer() || (eContainerFeatureID() != DoorsDBModelPackage.DB_ITEM__PARENT && newParent != null)) {
            if (EcoreUtil.isAncestor(this, newParent))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newParent != null)
                msgs = ((InternalEObject)newParent).eInverseAdd(this, DoorsDBModelPackage.DB_ITEM__CHILDREN, DBItem.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DoorsDBModelPackage.DB_ITEM__PARENT, newParent, newParent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DoorsDBModelPackage.DB_ITEM__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public String getFullName() {
        return (getParent() != null ? getParent().getFullName() + "/" : "") + (getName() != null ? getName() : "");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public void accept(final DoorsDBVisitor visitor) {
        if (this instanceof DBFolder) {
            visitor.visit((DBFolder) this);
        } else if (this instanceof DBModule) {
            visitor.visit((DBModule) this);
        }
        getChildren().forEach(m -> m.accept(visitor));
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
            case DoorsDBModelPackage.DB_ITEM__CHILDREN:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
            case DoorsDBModelPackage.DB_ITEM__PARENT:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetParent((DBItem)otherEnd, msgs);
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
            case DoorsDBModelPackage.DB_ITEM__CHILDREN:
                return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
            case DoorsDBModelPackage.DB_ITEM__PARENT:
                return basicSetParent(null, msgs);
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
            case DoorsDBModelPackage.DB_ITEM__PARENT:
                return eInternalContainer().eInverseRemove(this, DoorsDBModelPackage.DB_ITEM__CHILDREN, DBItem.class, msgs);
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
            case DoorsDBModelPackage.DB_ITEM__CHILDREN:
                return getChildren();
            case DoorsDBModelPackage.DB_ITEM__PARENT:
                return getParent();
            case DoorsDBModelPackage.DB_ITEM__NAME:
                return getName();
            case DoorsDBModelPackage.DB_ITEM__FULL_NAME:
                return getFullName();
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
            case DoorsDBModelPackage.DB_ITEM__CHILDREN:
                getChildren().clear();
                getChildren().addAll((Collection<? extends DBItem>)newValue);
                return;
            case DoorsDBModelPackage.DB_ITEM__PARENT:
                setParent((DBItem)newValue);
                return;
            case DoorsDBModelPackage.DB_ITEM__NAME:
                setName((String)newValue);
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
            case DoorsDBModelPackage.DB_ITEM__CHILDREN:
                getChildren().clear();
                return;
            case DoorsDBModelPackage.DB_ITEM__PARENT:
                setParent((DBItem)null);
                return;
            case DoorsDBModelPackage.DB_ITEM__NAME:
                setName(NAME_EDEFAULT);
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
            case DoorsDBModelPackage.DB_ITEM__CHILDREN:
                return children != null && !children.isEmpty();
            case DoorsDBModelPackage.DB_ITEM__PARENT:
                return getParent() != null;
            case DoorsDBModelPackage.DB_ITEM__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case DoorsDBModelPackage.DB_ITEM__FULL_NAME:
                return FULL_NAME_EDEFAULT == null ? getFullName() != null : !FULL_NAME_EDEFAULT.equals(getFullName());
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
            case DoorsDBModelPackage.DB_ITEM___ACCEPT__DOORSDBVISITOR:
                accept((DoorsDBVisitor)arguments.get(0));
                return null;
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
        return name;
    }

} //DBItemImpl
