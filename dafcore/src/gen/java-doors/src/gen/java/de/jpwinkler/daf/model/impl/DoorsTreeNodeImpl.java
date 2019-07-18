package de.jpwinkler.daf.model.impl;

/*-
 * #%L
 * dafcore
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Doors Tree Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsTreeNodeImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsTreeNodeImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsTreeNodeImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsTreeNodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsTreeNodeImpl#getFullName <em>Full Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsTreeNodeImpl#getFullNameSegments <em>Full Name Segments</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DoorsTreeNodeImpl extends MinimalEObjectImpl.Container implements DoorsTreeNode {

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
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
     */
    protected DoorsTreeNodeImpl() {
		super();
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
     */
    @Override
    protected EClass eStaticClass() {
		return DoorsPackage.Literals.DOORS_TREE_NODE;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public List<DoorsTreeNode> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<DoorsTreeNode>(DoorsTreeNode.class, this, DoorsPackage.DOORS_TREE_NODE__CHILDREN, DoorsPackage.DOORS_TREE_NODE__PARENT);
		}
		return children;
	}
    
    

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
     */
    @Override
    public DoorsTreeNode getParent() {
		if (eContainerFeatureID() != DoorsPackage.DOORS_TREE_NODE__PARENT) return null;
		return (DoorsTreeNode)eInternalContainer();
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
     */
    public NotificationChain basicSetParent(DoorsTreeNode newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, DoorsPackage.DOORS_TREE_NODE__PARENT, msgs);
		return msgs;
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
     */
    @Override
    public void setParent(DoorsTreeNode newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != DoorsPackage.DOORS_TREE_NODE__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newParent))
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
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
     */
    @Override
    public Map<String, String> getAttributes() {
		if (attributes == null) {
			attributes = new EcoreEMap<String,String>(DoorsPackage.Literals.ATTRIBUTE_MAP, AttributeMapImpl.class, this, DoorsPackage.DOORS_TREE_NODE__ATTRIBUTES);
		}
		return attributes.map();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public String getName() {
		return name;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DoorsPackage.DOORS_TREE_NODE__NAME, oldName, name));
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public String getFullName() {
        DoorsTreeNode parent = this;
        String fullName = "";
        // make sure root does not show up in path
        while (parent != null && parent.getParent() != null) {
            fullName = parent.getName() + "/" + fullName;
            parent = parent.getParent();
        }
        return "/" + fullName.replaceAll("/$", "");
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public EList<String> getFullNameSegments() {
        DoorsTreeNode parent = this;
        EList<String> fullName = new BasicEList<>();
        while (parent != null) {
            fullName.add(0, parent.getName());
            parent = parent.getParent();
        }
        return fullName;
    }

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean hasTag(String tag) {
		return DoorsTreeNode.super.hasTag(tag);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean hasTag(Pattern pattern) {
		return DoorsTreeNode.super.hasTag(pattern);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public List<String> getTags() {
		return DoorsTreeNode.super.getTags();
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setTag(String tag) {
		DoorsTreeNode.super.setTag(tag);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void removeTag(String tag) {
		          DoorsTreeNode.super.removeTag(tag);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void removeTag(Pattern pattern) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

				/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public boolean canCopyFrom(DoorsTreeNode node) {
        return !Arrays.stream(this.getClass().getInterfaces())
                .filter(i -> DoorsTreeNode.class.isAssignableFrom(i))
                .filter(i -> !i.isAssignableFrom(node.getClass()))
                .findAny().isPresent();
    }

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public DoorsTreeNode getChild(String name) {
		return DoorsTreeNode.super.getChild(name);
	}

				/**
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
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
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DoorsPackage.DOORS_TREE_NODE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case DoorsPackage.DOORS_TREE_NODE__PARENT:
				return basicSetParent(null, msgs);
			case DoorsPackage.DOORS_TREE_NODE__ATTRIBUTES:
				return ((InternalEList<?>)((EMap.InternalMapView<String, String>)getAttributes()).eMap()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
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
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DoorsPackage.DOORS_TREE_NODE__CHILDREN:
				return getChildren();
			case DoorsPackage.DOORS_TREE_NODE__PARENT:
				return getParent();
			case DoorsPackage.DOORS_TREE_NODE__ATTRIBUTES:
				if (coreType) return ((EMap.InternalMapView<String, String>)getAttributes()).eMap();
				else return getAttributes();
			case DoorsPackage.DOORS_TREE_NODE__NAME:
				return getName();
			case DoorsPackage.DOORS_TREE_NODE__FULL_NAME:
				return getFullName();
			case DoorsPackage.DOORS_TREE_NODE__FULL_NAME_SEGMENTS:
				return getFullNameSegments();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
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
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, String>)getAttributes()).eMap()).set(newValue);
				return;
			case DoorsPackage.DOORS_TREE_NODE__NAME:
				setName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
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
			case DoorsPackage.DOORS_TREE_NODE__NAME:
				setName(NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
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
			case DoorsPackage.DOORS_TREE_NODE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DoorsPackage.DOORS_TREE_NODE__FULL_NAME:
				return FULL_NAME_EDEFAULT == null ? getFullName() != null : !FULL_NAME_EDEFAULT.equals(getFullName());
			case DoorsPackage.DOORS_TREE_NODE__FULL_NAME_SEGMENTS:
				return !getFullNameSegments().isEmpty();
		}
		return super.eIsSet(featureID);
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case DoorsPackage.DOORS_TREE_NODE___HAS_TAG__STRING:
				return hasTag((String)arguments.get(0));
			case DoorsPackage.DOORS_TREE_NODE___HAS_TAG__PATTERN:
				return hasTag((Pattern)arguments.get(0));
			case DoorsPackage.DOORS_TREE_NODE___GET_TAGS:
				return getTags();
			case DoorsPackage.DOORS_TREE_NODE___SET_TAG__STRING:
				setTag((String)arguments.get(0));
				return null;
			case DoorsPackage.DOORS_TREE_NODE___REMOVE_TAG__STRING:
				removeTag((String)arguments.get(0));
				return null;
			case DoorsPackage.DOORS_TREE_NODE___REMOVE_TAG__PATTERN:
				removeTag((Pattern)arguments.get(0));
				return null;
			case DoorsPackage.DOORS_TREE_NODE___CAN_COPY_FROM__DOORSTREENODE:
				return canCopyFrom((DoorsTreeNode)arguments.get(0));
			case DoorsPackage.DOORS_TREE_NODE___GET_CHILD__STRING:
				return getChild((String)arguments.get(0));
			case DoorsPackage.DOORS_TREE_NODE___TO_STRING:
				return toString();
		}
		return super.eInvoke(operationID, arguments);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public CompletableFuture<List<DoorsTreeNode>> getChildrenAsync(BackgroundTaskExecutor executor) {
        return CompletableFuture.completedFuture(this.getChildren());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public boolean isChildrenLoaded() {
        return true;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public CompletableFuture<Map<String, String>> getAttributesAsync(BackgroundTaskExecutor executor) {
        return CompletableFuture.completedFuture(this.getAttributes());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public <T extends DoorsTreeNode, U> U accept(DoorsTreeNodeVisitor<T, U> visitor) {
        return visitor.traverse(this);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public <T extends DoorsTreeNode, U> CompletableFuture<U> acceptAsync(BackgroundTaskExecutor executor, DoorsTreeNodeVisitor<T,U> visitor) {
        return executor.runBackgroundTask("Visiting nodes", i -> {
            return this.accept(visitor);
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public CompletableFuture<DoorsTreeNode> getChildAsync(BackgroundTaskExecutor executor, String name) {
        return CompletableFuture.completedFuture(this.getChild(name));
    }
    
    

} // DoorsTreeNodeImpl
