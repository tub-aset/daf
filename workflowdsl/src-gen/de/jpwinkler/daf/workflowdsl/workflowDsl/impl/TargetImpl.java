/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl.impl;

import de.jpwinkler.daf.workflowdsl.workflowDsl.Step;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Target;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Target</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.TargetImpl#getStep <em>Step</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TargetImpl extends WorkflowElementImpl implements Target
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TargetImpl()
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
    return WorkflowDslPackage.Literals.TARGET;
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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, WorkflowDslPackage.TARGET__STEP, oldStep, step));
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
      eNotify(new ENotificationImpl(this, Notification.SET, WorkflowDslPackage.TARGET__STEP, oldStep, step));
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
      case WorkflowDslPackage.TARGET__STEP:
        if (resolve) return getStep();
        return basicGetStep();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case WorkflowDslPackage.TARGET__STEP:
        setStep((Step)newValue);
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
      case WorkflowDslPackage.TARGET__STEP:
        setStep((Step)null);
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
      case WorkflowDslPackage.TARGET__STEP:
        return step != null;
    }
    return super.eIsSet(featureID);
  }

} //TargetImpl
