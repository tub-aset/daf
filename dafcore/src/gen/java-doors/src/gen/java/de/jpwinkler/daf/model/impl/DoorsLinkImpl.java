/**
 */
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
import de.jpwinkler.daf.model.DoorsLink;
import de.jpwinkler.daf.model.DoorsLinkResolveException;
import de.jpwinkler.daf.model.DoorsLinkStatus;
import de.jpwinkler.daf.model.DoorsModelUtil;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsLinkImpl#getTargetModule <em>Target Module</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsLinkImpl#getTargetObject <em>Target Object</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsLinkImpl#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DoorsLinkImpl extends MinimalEObjectImpl.Container implements DoorsLink {

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
     * <!-- end-user-doc --> @generated
     */
    protected DoorsLinkImpl() {
		super();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    protected EClass eStaticClass() {
		return DoorsPackage.Literals.DOORS_LINK;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public DoorsObject getSource() {
		if (eContainerFeatureID() != DoorsPackage.DOORS_LINK__SOURCE) return null;
		return (DoorsObject)eInternalContainer();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    public NotificationChain basicSetSource(DoorsObject newSource, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSource, DoorsPackage.DOORS_LINK__SOURCE, msgs);
		return msgs;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public void setSource(DoorsObject newSource) {
		if (newSource != eInternalContainer() || (eContainerFeatureID() != DoorsPackage.DOORS_LINK__SOURCE && newSource != null)) {
			if (EcoreUtil.isAncestor(this, (EObject)newSource))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, DoorsPackage.DOORS_OBJECT__OUTGOING_LINKS, DoorsObject.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DoorsPackage.DOORS_LINK__SOURCE, newSource, newSource));
	}

				/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public String getTargetModule() {
		return targetModule;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public void setTargetModule(String newTargetModule) {
		String oldTargetModule = targetModule;
		targetModule = newTargetModule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DoorsPackage.DOORS_LINK__TARGET_MODULE, oldTargetModule, targetModule));
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public String getTargetObject() {
		return targetObject;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public void setTargetObject(String newTargetObject) {
		String oldTargetObject = targetObject;
		targetObject = newTargetObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DoorsPackage.DOORS_LINK__TARGET_OBJECT, oldTargetObject, targetObject));
	}

    /**
     * @generated NOT
     */
    private DoorsObject target;
    /**
     * @generated NOT
     */
    private DoorsLinkStatus targetStatus = DoorsLinkStatus.UNRESOLVED;
    
    /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
    @Override
    public DoorsLinkStatus getLinkStatus() {
            return targetStatus;
    }

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsObject resolve() throws DoorsLinkResolveException {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

				/**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
    @Override
    public DoorsObject resolve(DoorsTreeNode sourceOverride) throws DoorsLinkResolveException {
        return DoorsModelUtil.resolve(this, sourceOverride, () -> this.target, t -> this.target = t, s -> this.targetStatus = s);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DoorsPackage.DOORS_LINK__SOURCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSource((DoorsObject)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DoorsPackage.DOORS_LINK__SOURCE:
				return basicSetSource(null, msgs);
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
			case DoorsPackage.DOORS_LINK__SOURCE:
				return eInternalContainer().eInverseRemove(this, DoorsPackage.DOORS_OBJECT__OUTGOING_LINKS, DoorsObject.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

				/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DoorsPackage.DOORS_LINK__TARGET_MODULE:
				return getTargetModule();
			case DoorsPackage.DOORS_LINK__TARGET_OBJECT:
				return getTargetObject();
			case DoorsPackage.DOORS_LINK__SOURCE:
				return getSource();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DoorsPackage.DOORS_LINK__TARGET_MODULE:
				setTargetModule((String)newValue);
				return;
			case DoorsPackage.DOORS_LINK__TARGET_OBJECT:
				setTargetObject((String)newValue);
				return;
			case DoorsPackage.DOORS_LINK__SOURCE:
				setSource((DoorsObject)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public void eUnset(int featureID) {
		switch (featureID) {
			case DoorsPackage.DOORS_LINK__TARGET_MODULE:
				setTargetModule(TARGET_MODULE_EDEFAULT);
				return;
			case DoorsPackage.DOORS_LINK__TARGET_OBJECT:
				setTargetObject(TARGET_OBJECT_EDEFAULT);
				return;
			case DoorsPackage.DOORS_LINK__SOURCE:
				setSource((DoorsObject)null);
				return;
		}
		super.eUnset(featureID);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DoorsPackage.DOORS_LINK__TARGET_MODULE:
				return TARGET_MODULE_EDEFAULT == null ? targetModule != null : !TARGET_MODULE_EDEFAULT.equals(targetModule);
			case DoorsPackage.DOORS_LINK__TARGET_OBJECT:
				return TARGET_OBJECT_EDEFAULT == null ? targetObject != null : !TARGET_OBJECT_EDEFAULT.equals(targetObject);
			case DoorsPackage.DOORS_LINK__SOURCE:
				return getSource() != null;
		}
		return super.eIsSet(featureID);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case DoorsPackage.DOORS_LINK___GET_LINK_STATUS:
				return getLinkStatus();
			case DoorsPackage.DOORS_LINK___RESOLVE:
				try {
					return resolve();
				}
				catch (Throwable throwable) {
					throw new InvocationTargetException(throwable);
				}
			case DoorsPackage.DOORS_LINK___RESOLVE__DOORSTREENODE:
				try {
					return resolve((DoorsTreeNode)arguments.get(0));
				}
				catch (Throwable throwable) {
					throw new InvocationTargetException(throwable);
				}
		}
		return super.eInvoke(operationID, arguments);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (targetModule: ");
		result.append(targetModule);
		result.append(", targetObject: ");
		result.append(targetObject);
		result.append(')');
		return result.toString();
	}

} //DoorsLinkImpl
