/**
 */
package de.jpwinkler.daf.doorscsv.model.impl;

import de.jpwinkler.daf.doorscsv.DoorsTreeNodeVisitor;

import de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage;
import de.jpwinkler.daf.doorscsv.model.DoorsTreeNode;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Doors Tree Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.impl.DoorsTreeNodeImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.impl.DoorsTreeNodeImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.impl.DoorsTreeNodeImpl#getAttributes <em>Attributes</em>}</li>
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
	protected DoorsTreeNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DoorsCSVPackage.Literals.DOORS_TREE_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DoorsTreeNode> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<DoorsTreeNode>(DoorsTreeNode.class, this, DoorsCSVPackage.DOORS_TREE_NODE__CHILDREN, DoorsCSVPackage.DOORS_TREE_NODE__PARENT);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsTreeNode getParent() {
		if (eContainerFeatureID() != DoorsCSVPackage.DOORS_TREE_NODE__PARENT) return null;
		return (DoorsTreeNode)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(DoorsTreeNode newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, DoorsCSVPackage.DOORS_TREE_NODE__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParent(DoorsTreeNode newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != DoorsCSVPackage.DOORS_TREE_NODE__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, DoorsCSVPackage.DOORS_TREE_NODE__CHILDREN, DoorsTreeNode.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DoorsCSVPackage.DOORS_TREE_NODE__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getAttributes() {
		if (attributes == null) {
			attributes = new EcoreEMap<String,String>(DoorsCSVPackage.Literals.STRING_TO_STRING_MAP, StringToStringMapImpl.class, this, DoorsCSVPackage.DOORS_TREE_NODE__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void accept(DoorsTreeNodeVisitor visitor) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
			case DoorsCSVPackage.DOORS_TREE_NODE__CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
			case DoorsCSVPackage.DOORS_TREE_NODE__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((DoorsTreeNode)otherEnd, msgs);
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
			case DoorsCSVPackage.DOORS_TREE_NODE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case DoorsCSVPackage.DOORS_TREE_NODE__PARENT:
				return basicSetParent(null, msgs);
			case DoorsCSVPackage.DOORS_TREE_NODE__ATTRIBUTES:
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
			case DoorsCSVPackage.DOORS_TREE_NODE__PARENT:
				return eInternalContainer().eInverseRemove(this, DoorsCSVPackage.DOORS_TREE_NODE__CHILDREN, DoorsTreeNode.class, msgs);
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
			case DoorsCSVPackage.DOORS_TREE_NODE__CHILDREN:
				return getChildren();
			case DoorsCSVPackage.DOORS_TREE_NODE__PARENT:
				return getParent();
			case DoorsCSVPackage.DOORS_TREE_NODE__ATTRIBUTES:
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
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DoorsCSVPackage.DOORS_TREE_NODE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends DoorsTreeNode>)newValue);
				return;
			case DoorsCSVPackage.DOORS_TREE_NODE__PARENT:
				setParent((DoorsTreeNode)newValue);
				return;
			case DoorsCSVPackage.DOORS_TREE_NODE__ATTRIBUTES:
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
			case DoorsCSVPackage.DOORS_TREE_NODE__CHILDREN:
				getChildren().clear();
				return;
			case DoorsCSVPackage.DOORS_TREE_NODE__PARENT:
				setParent((DoorsTreeNode)null);
				return;
			case DoorsCSVPackage.DOORS_TREE_NODE__ATTRIBUTES:
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
			case DoorsCSVPackage.DOORS_TREE_NODE__CHILDREN:
				return children != null && !children.isEmpty();
			case DoorsCSVPackage.DOORS_TREE_NODE__PARENT:
				return getParent() != null;
			case DoorsCSVPackage.DOORS_TREE_NODE__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
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
			case DoorsCSVPackage.DOORS_TREE_NODE___ACCEPT__DOORSTREENODEVISITOR:
				accept((DoorsTreeNodeVisitor)arguments.get(0));
				return null;
		}
		return super.eInvoke(operationID, arguments);
	}

} //DoorsTreeNodeImpl
