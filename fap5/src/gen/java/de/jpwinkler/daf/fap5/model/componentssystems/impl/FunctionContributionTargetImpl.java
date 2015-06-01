/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl;
import de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage;
import de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget;
import de.jpwinkler.daf.fap5.model.srs.FunctionContribution;
import de.jpwinkler.daf.fap5.model.srs.SrsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Contribution Target</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionContributionTargetImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionContributionTargetImpl#getAcronym <em>Acronym</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionContributionTargetImpl#getResponsibility <em>Responsibility</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionContributionTargetImpl#getDepartment <em>Department</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionContributionTargetImpl#getContributions <em>Contributions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionContributionTargetImpl extends ModelObjectImpl implements FunctionContributionTarget {
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
     * The default value of the '{@link #getAcronym() <em>Acronym</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAcronym()
     * @generated
     * @ordered
     */
	protected static final String ACRONYM_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getAcronym() <em>Acronym</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAcronym()
     * @generated
     * @ordered
     */
	protected String acronym = ACRONYM_EDEFAULT;

	/**
     * The default value of the '{@link #getResponsibility() <em>Responsibility</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getResponsibility()
     * @generated
     * @ordered
     */
	protected static final String RESPONSIBILITY_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getResponsibility() <em>Responsibility</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getResponsibility()
     * @generated
     * @ordered
     */
	protected String responsibility = RESPONSIBILITY_EDEFAULT;

	/**
     * The default value of the '{@link #getDepartment() <em>Department</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDepartment()
     * @generated
     * @ordered
     */
	protected static final String DEPARTMENT_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getDepartment() <em>Department</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDepartment()
     * @generated
     * @ordered
     */
	protected String department = DEPARTMENT_EDEFAULT;

	/**
     * The cached value of the '{@link #getContributions() <em>Contributions</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getContributions()
     * @generated
     * @ordered
     */
	protected EList<FunctionContribution> contributions;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected FunctionContributionTargetImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ComponentsSystemsPackage.Literals.FUNCTION_CONTRIBUTION_TARGET;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getName() {
        return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getAcronym() {
        return acronym;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setAcronym(String newAcronym) {
        String oldAcronym = acronym;
        acronym = newAcronym;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__ACRONYM, oldAcronym, acronym));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getResponsibility() {
        return responsibility;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setResponsibility(String newResponsibility) {
        String oldResponsibility = responsibility;
        responsibility = newResponsibility;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__RESPONSIBILITY, oldResponsibility, responsibility));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getDepartment() {
        return department;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setDepartment(String newDepartment) {
        String oldDepartment = department;
        department = newDepartment;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__DEPARTMENT, oldDepartment, department));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<FunctionContribution> getContributions() {
        if (contributions == null) {
            contributions = new EObjectWithInverseResolvingEList<FunctionContribution>(FunctionContribution.class, this, ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS, SrsPackage.FUNCTION_CONTRIBUTION__TARGET);
        }
        return contributions;
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
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getContributions()).basicAdd(otherEnd, msgs);
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
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS:
                return ((InternalEList<?>)getContributions()).basicRemove(otherEnd, msgs);
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
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__NAME:
                return getName();
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__ACRONYM:
                return getAcronym();
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__RESPONSIBILITY:
                return getResponsibility();
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__DEPARTMENT:
                return getDepartment();
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS:
                return getContributions();
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
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__NAME:
                setName((String)newValue);
                return;
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__ACRONYM:
                setAcronym((String)newValue);
                return;
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__RESPONSIBILITY:
                setResponsibility((String)newValue);
                return;
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__DEPARTMENT:
                setDepartment((String)newValue);
                return;
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS:
                getContributions().clear();
                getContributions().addAll((Collection<? extends FunctionContribution>)newValue);
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
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__ACRONYM:
                setAcronym(ACRONYM_EDEFAULT);
                return;
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__RESPONSIBILITY:
                setResponsibility(RESPONSIBILITY_EDEFAULT);
                return;
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__DEPARTMENT:
                setDepartment(DEPARTMENT_EDEFAULT);
                return;
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS:
                getContributions().clear();
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
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__ACRONYM:
                return ACRONYM_EDEFAULT == null ? acronym != null : !ACRONYM_EDEFAULT.equals(acronym);
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__RESPONSIBILITY:
                return RESPONSIBILITY_EDEFAULT == null ? responsibility != null : !RESPONSIBILITY_EDEFAULT.equals(responsibility);
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__DEPARTMENT:
                return DEPARTMENT_EDEFAULT == null ? department != null : !DEPARTMENT_EDEFAULT.equals(department);
            case ComponentsSystemsPackage.FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS:
                return contributions != null && !contributions.isEmpty();
        }
        return super.eIsSet(featureID);
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
        result.append(" (name: ");
        result.append(name);
        result.append(", acronym: ");
        result.append(acronym);
        result.append(", responsibility: ");
        result.append(responsibility);
        result.append(", department: ");
        result.append(department);
        result.append(')');
        return result.toString();
    }

} //FunctionContributionTargetImpl
