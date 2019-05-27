/**
 */
package de.jpwinkler.daf.model.impl;

import de.jpwinkler.daf.csv.FindObjectVisitor;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.model.DoorsSystemAttributes;
import de.jpwinkler.daf.model.DoorsTreeNode;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Doors Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsModuleImpl#getView <em>View</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DoorsModuleImpl extends DoorsTreeNodeImpl implements DoorsModule {

    /**
	 * The default value of the '{@link #getView() <em>View</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getView()
	 * @generated
	 * @ordered
	 */
    protected static final String VIEW_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getView() <em>View</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getView()
	 * @generated
	 * @ordered
	 */
    protected String view = VIEW_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    protected DoorsModuleImpl() {
		super();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    protected EClass eStaticClass() {
		return DoorsPackage.Literals.DOORS_MODULE;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public String getView() {
		return view;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public void setView(String newView) {
		String oldView = view;
		view = newView;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DoorsPackage.DOORS_MODULE__VIEW, oldView, view));
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public DoorsObject findObject(final String objectIdentifier) {
        final FindObjectVisitor visitor = new FindObjectVisitor(objectIdentifier);
        accept(visitor);
        return visitor.getObject();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public List<String> getObjectAttributes() {
        return DoorsSystemAttributes.OBJECT_ATTRIBUTES.getValue(List.class, getAttributes());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public void setObjectAttributes(List<String> attrs) {
        DoorsSystemAttributes.OBJECT_ATTRIBUTES.setValue(List.class, getAttributes(), attrs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public DoorsModule copyFrom(DoorsTreeNode newModule, DoorsTreeNode newParent) {
        super.copyFrom(newModule, newParent);
        return this;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DoorsPackage.DOORS_MODULE__VIEW:
				return getView();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DoorsPackage.DOORS_MODULE__VIEW:
				setView((String)newValue);
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
			case DoorsPackage.DOORS_MODULE__VIEW:
				setView(VIEW_EDEFAULT);
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
			case DoorsPackage.DOORS_MODULE__VIEW:
				return VIEW_EDEFAULT == null ? view != null : !VIEW_EDEFAULT.equals(view);
		}
		return super.eIsSet(featureID);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case DoorsPackage.DOORS_MODULE___FIND_OBJECT__STRING:
				return findObject((String)arguments.get(0));
			case DoorsPackage.DOORS_MODULE___GET_OBJECT_ATTRIBUTES:
				return getObjectAttributes();
			case DoorsPackage.DOORS_MODULE___SET_OBJECT_ATTRIBUTES__LIST:
				setObjectAttributes((List<String>)arguments.get(0));
				return null;
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (view: ");
		result.append(view);
		result.append(')');
		return result.toString();
	}

} //DoorsModuleImpl
