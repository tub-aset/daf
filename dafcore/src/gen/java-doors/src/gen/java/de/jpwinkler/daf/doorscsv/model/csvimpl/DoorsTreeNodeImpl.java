/**
 */
package de.jpwinkler.daf.doorscsv.model.csvimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.doorscsv.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.doorscsv.model.DoorsModule;
import de.jpwinkler.daf.doorscsv.model.DoorsObject;
import de.jpwinkler.daf.doorscsv.model.DoorsPackage;
import de.jpwinkler.daf.doorscsv.model.DoorsTreeNode;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Doors Tree Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.csvimpl.DoorsTreeNodeImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.csvimpl.DoorsTreeNodeImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.csvimpl.DoorsTreeNodeImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.csvimpl.DoorsTreeNodeImpl#getFullName <em>Full Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DoorsTreeNodeImpl extends MinimalEObjectImpl.Container implements DoorsTreeNode {
    /**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
    protected EList<DoorsTreeNode> children;

    /**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
    protected EMap<String, String> attributes;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    protected DoorsTreeNodeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return DoorsPackage.Literals.DOORS_TREE_NODE;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public EList<DoorsTreeNode> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<DoorsTreeNode>(DoorsTreeNode.class, this, DoorsPackage.DOORS_TREE_NODE__CHILDREN, DoorsPackage.DOORS_TREE_NODE__PARENT);
		}
		return children;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public DoorsTreeNode getParent() {
		if (eContainerFeatureID() != DoorsPackage.DOORS_TREE_NODE__PARENT) return null;
		return (DoorsTreeNode)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetParent(DoorsTreeNode newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, DoorsPackage.DOORS_TREE_NODE__PARENT, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void setParent(DoorsTreeNode newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != DoorsPackage.DOORS_TREE_NODE__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, DoorsPackage.DOORS_TREE_NODE__CHILDREN, DoorsTreeNode.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DoorsPackage.DOORS_TREE_NODE__PARENT, newParent, newParent));
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public EMap<String, String> getAttributes() {
		if (attributes == null) {
			attributes = new EcoreEMap<String,String>(DoorsPackage.Literals.STRING_TO_STRING_MAP, StringToStringMapImpl.class, this, DoorsPackage.DOORS_TREE_NODE__ATTRIBUTES);
		}
		return attributes;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFullName() {
		// TODO: implement this method to return the 'Full Name' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

				/**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public void accept(final DoorsTreeNodeVisitor visitor) {
        if (this instanceof DoorsModule) {
            if (visitor.visitPreTraverse((DoorsModule) this)) {
                for (final DoorsTreeNode child : getChildren()) {
                    child.accept(visitor);
                }
            }
            visitor.visitPostTraverse((DoorsModule) this);
        } else if (this instanceof DoorsObject) {
            if (visitor.visitPreTraverse((DoorsObject) this)) {
                for (final DoorsTreeNode child : getChildren()) {
                    child.accept(visitor);
                }
            }
            visitor.visitPostTraverse((DoorsObject) this);
        }
    }

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DoorsPackage.DOORS_TREE_NODE__CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
			case DoorsPackage.DOORS_TREE_NODE__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((DoorsTreeNode)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DoorsPackage.DOORS_TREE_NODE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case DoorsPackage.DOORS_TREE_NODE__PARENT:
				return basicSetParent(null, msgs);
			case DoorsPackage.DOORS_TREE_NODE__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case DoorsPackage.DOORS_TREE_NODE__PARENT:
				return eInternalContainer().eInverseRemove(this, DoorsPackage.DOORS_TREE_NODE__CHILDREN, DoorsTreeNode.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DoorsPackage.DOORS_TREE_NODE__CHILDREN:
				return getChildren();
			case DoorsPackage.DOORS_TREE_NODE__PARENT:
				return getParent();
			case DoorsPackage.DOORS_TREE_NODE__ATTRIBUTES:
				if (coreType) return getAttributes();
				else return getAttributes().map();
			case DoorsPackage.DOORS_TREE_NODE__FULL_NAME:
				return getFullName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DoorsPackage.DOORS_TREE_NODE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends DoorsTreeNode>)newValue);
				return;
			case DoorsPackage.DOORS_TREE_NODE__PARENT:
				setParent((DoorsTreeNode)newValue);
				return;
			case DoorsPackage.DOORS_TREE_NODE__ATTRIBUTES:
				((EStructuralFeature.Setting)getAttributes()).set(newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eUnset(int featureID) {
		switch (featureID) {
			case DoorsPackage.DOORS_TREE_NODE__CHILDREN:
				getChildren().clear();
				return;
			case DoorsPackage.DOORS_TREE_NODE__PARENT:
				setParent((DoorsTreeNode)null);
				return;
			case DoorsPackage.DOORS_TREE_NODE__ATTRIBUTES:
				getAttributes().clear();
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DoorsPackage.DOORS_TREE_NODE__CHILDREN:
				return children != null && !children.isEmpty();
			case DoorsPackage.DOORS_TREE_NODE__PARENT:
				return getParent() != null;
			case DoorsPackage.DOORS_TREE_NODE__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case DoorsPackage.DOORS_TREE_NODE__FULL_NAME:
				return FULL_NAME_EDEFAULT == null ? getFullName() != null : !FULL_NAME_EDEFAULT.equals(getFullName());
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case DoorsPackage.DOORS_TREE_NODE___ACCEPT__DOORSTREENODEVISITOR:
				accept((DoorsTreeNodeVisitor)arguments.get(0));
				return null;
		}
		return super.eInvoke(operationID, arguments);
	}

} // DoorsTreeNodeImpl
