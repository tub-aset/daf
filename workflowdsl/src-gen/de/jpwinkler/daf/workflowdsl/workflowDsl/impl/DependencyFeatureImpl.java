/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl.impl;

import de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Step;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Variable;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dependency Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.DependencyFeatureImpl#getStep <em>Step</em>}</li>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.DependencyFeatureImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.DependencyFeatureImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DependencyFeatureImpl extends OperationFeatureImpl implements DependencyFeature
{
  /**
   * The cached value of the '{@link #getStep() <em>Step</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStep()
   * @generated
   * @ordered
   */
  protected Step step;

  /**
   * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariables()
   * @generated
   * @ordered
   */
  protected EList<Variable> variables;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DependencyFeatureImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return WorkflowDslPackage.Literals.DEPENDENCY_FEATURE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Step getStep()
  {
    if (step != null && step.eIsProxy())
    {
      InternalEObject oldStep = (InternalEObject)step;
      step = (Step)eResolveProxy(oldStep);
      if (step != oldStep)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, WorkflowDslPackage.DEPENDENCY_FEATURE__STEP, oldStep, step));
      }
    }
    return step;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Step basicGetStep()
  {
    return step;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStep(Step newStep)
  {
    Step oldStep = step;
    step = newStep;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WorkflowDslPackage.DEPENDENCY_FEATURE__STEP, oldStep, step));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Variable> getVariables()
  {
    if (variables == null)
    {
      variables = new EObjectContainmentEList<Variable>(Variable.class, this, WorkflowDslPackage.DEPENDENCY_FEATURE__VARIABLES);
    }
    return variables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WorkflowDslPackage.DEPENDENCY_FEATURE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case WorkflowDslPackage.DEPENDENCY_FEATURE__VARIABLES:
        return ((InternalEList<?>)getVariables()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case WorkflowDslPackage.DEPENDENCY_FEATURE__STEP:
        if (resolve) return getStep();
        return basicGetStep();
      case WorkflowDslPackage.DEPENDENCY_FEATURE__VARIABLES:
        return getVariables();
      case WorkflowDslPackage.DEPENDENCY_FEATURE__NAME:
        return getName();
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
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case WorkflowDslPackage.DEPENDENCY_FEATURE__STEP:
        setStep((Step)newValue);
        return;
      case WorkflowDslPackage.DEPENDENCY_FEATURE__VARIABLES:
        getVariables().clear();
        getVariables().addAll((Collection<? extends Variable>)newValue);
        return;
      case WorkflowDslPackage.DEPENDENCY_FEATURE__NAME:
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
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case WorkflowDslPackage.DEPENDENCY_FEATURE__STEP:
        setStep((Step)null);
        return;
      case WorkflowDslPackage.DEPENDENCY_FEATURE__VARIABLES:
        getVariables().clear();
        return;
      case WorkflowDslPackage.DEPENDENCY_FEATURE__NAME:
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
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case WorkflowDslPackage.DEPENDENCY_FEATURE__STEP:
        return step != null;
      case WorkflowDslPackage.DEPENDENCY_FEATURE__VARIABLES:
        return variables != null && !variables.isEmpty();
      case WorkflowDslPackage.DEPENDENCY_FEATURE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //DependencyFeatureImpl
