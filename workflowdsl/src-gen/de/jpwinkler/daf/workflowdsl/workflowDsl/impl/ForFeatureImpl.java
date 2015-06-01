/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl.impl;

import de.jpwinkler.daf.workflowdsl.workflowDsl.ForFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.OperationFeature;
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
 * An implementation of the model object '<em><b>For Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ForFeatureImpl#getLoopVar <em>Loop Var</em>}</li>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ForFeatureImpl#getArrayVar <em>Array Var</em>}</li>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.ForFeatureImpl#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForFeatureImpl extends OperationFeatureImpl implements ForFeature
{
  /**
   * The default value of the '{@link #getLoopVar() <em>Loop Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLoopVar()
   * @generated
   * @ordered
   */
  protected static final String LOOP_VAR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLoopVar() <em>Loop Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLoopVar()
   * @generated
   * @ordered
   */
  protected String loopVar = LOOP_VAR_EDEFAULT;

  /**
   * The default value of the '{@link #getArrayVar() <em>Array Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArrayVar()
   * @generated
   * @ordered
   */
  protected static final String ARRAY_VAR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getArrayVar() <em>Array Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArrayVar()
   * @generated
   * @ordered
   */
  protected String arrayVar = ARRAY_VAR_EDEFAULT;

  /**
   * The cached value of the '{@link #getFeatures() <em>Features</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFeatures()
   * @generated
   * @ordered
   */
  protected EList<OperationFeature> features;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ForFeatureImpl()
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
    return WorkflowDslPackage.Literals.FOR_FEATURE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLoopVar()
  {
    return loopVar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLoopVar(String newLoopVar)
  {
    String oldLoopVar = loopVar;
    loopVar = newLoopVar;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WorkflowDslPackage.FOR_FEATURE__LOOP_VAR, oldLoopVar, loopVar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getArrayVar()
  {
    return arrayVar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArrayVar(String newArrayVar)
  {
    String oldArrayVar = arrayVar;
    arrayVar = newArrayVar;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WorkflowDslPackage.FOR_FEATURE__ARRAY_VAR, oldArrayVar, arrayVar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<OperationFeature> getFeatures()
  {
    if (features == null)
    {
      features = new EObjectContainmentEList<OperationFeature>(OperationFeature.class, this, WorkflowDslPackage.FOR_FEATURE__FEATURES);
    }
    return features;
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
      case WorkflowDslPackage.FOR_FEATURE__FEATURES:
        return ((InternalEList<?>)getFeatures()).basicRemove(otherEnd, msgs);
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
      case WorkflowDslPackage.FOR_FEATURE__LOOP_VAR:
        return getLoopVar();
      case WorkflowDslPackage.FOR_FEATURE__ARRAY_VAR:
        return getArrayVar();
      case WorkflowDslPackage.FOR_FEATURE__FEATURES:
        return getFeatures();
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
      case WorkflowDslPackage.FOR_FEATURE__LOOP_VAR:
        setLoopVar((String)newValue);
        return;
      case WorkflowDslPackage.FOR_FEATURE__ARRAY_VAR:
        setArrayVar((String)newValue);
        return;
      case WorkflowDslPackage.FOR_FEATURE__FEATURES:
        getFeatures().clear();
        getFeatures().addAll((Collection<? extends OperationFeature>)newValue);
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
      case WorkflowDslPackage.FOR_FEATURE__LOOP_VAR:
        setLoopVar(LOOP_VAR_EDEFAULT);
        return;
      case WorkflowDslPackage.FOR_FEATURE__ARRAY_VAR:
        setArrayVar(ARRAY_VAR_EDEFAULT);
        return;
      case WorkflowDslPackage.FOR_FEATURE__FEATURES:
        getFeatures().clear();
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
      case WorkflowDslPackage.FOR_FEATURE__LOOP_VAR:
        return LOOP_VAR_EDEFAULT == null ? loopVar != null : !LOOP_VAR_EDEFAULT.equals(loopVar);
      case WorkflowDslPackage.FOR_FEATURE__ARRAY_VAR:
        return ARRAY_VAR_EDEFAULT == null ? arrayVar != null : !ARRAY_VAR_EDEFAULT.equals(arrayVar);
      case WorkflowDslPackage.FOR_FEATURE__FEATURES:
        return features != null && !features.isEmpty();
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
    result.append(" (loopVar: ");
    result.append(loopVar);
    result.append(", arrayVar: ");
    result.append(arrayVar);
    result.append(')');
    return result.toString();
  }

} //ForFeatureImpl
