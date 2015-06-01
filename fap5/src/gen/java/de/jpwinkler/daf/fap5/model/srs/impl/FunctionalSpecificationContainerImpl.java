/**
 */
package de.jpwinkler.daf.fap5.model.srs.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.fap5.model.srs.FunctionContribution;
import de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer;
import de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationElement;
import de.jpwinkler.daf.fap5.model.srs.SrsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Functional Specification Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.srs.impl.FunctionalSpecificationContainerImpl#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionalSpecificationContainerImpl extends FunctionalSpecificationElementImpl implements FunctionalSpecificationContainer {
	/**
     * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getElements()
     * @generated
     * @ordered
     */
	protected EList<FunctionalSpecificationElement> elements;
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected FunctionalSpecificationContainerImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return SrsPackage.Literals.FUNCTIONAL_SPECIFICATION_CONTAINER;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<FunctionalSpecificationElement> getElements() {
        if (elements == null) {
            elements = new EObjectContainmentEList<FunctionalSpecificationElement>(FunctionalSpecificationElement.class, this, SrsPackage.FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS);
        }
        return elements;
    }

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EList<FunctionContribution> getFunctionContributions() {
		final EList<FunctionContribution> result = new BasicEList<>();
		for (final FunctionalSpecificationElement element : getElements()) {
			if (element instanceof FunctionContribution) {
				result.add((FunctionContribution) element);
			} else if (element instanceof FunctionalSpecificationContainer) {
				result.addAll(((FunctionalSpecificationContainer) element).getFunctionContributions());
			}
		}
		return result;
	}

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SrsPackage.FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS:
                return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
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
            case SrsPackage.FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS:
                return getElements();
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
            case SrsPackage.FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS:
                getElements().clear();
                getElements().addAll((Collection<? extends FunctionalSpecificationElement>)newValue);
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
            case SrsPackage.FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS:
                getElements().clear();
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
            case SrsPackage.FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS:
                return elements != null && !elements.isEmpty();
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
            case SrsPackage.FUNCTIONAL_SPECIFICATION_CONTAINER___GET_FUNCTION_CONTRIBUTIONS:
                return getFunctionContributions();
        }
        return super.eInvoke(operationID, arguments);
    }

} //FunctionalSpecificationContainerImpl
