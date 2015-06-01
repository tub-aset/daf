/**
 */
package de.jpwinkler.daf.workflowdsl.workflowDsl.impl;

import de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet;
import de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Source Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.workflowdsl.workflowDsl.impl.SourceFeatureImpl#getModuleSet <em>Module Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SourceFeatureImpl extends OperationFeatureImpl implements SourceFeature
{
  /**
   * The cached value of the '{@link #getModuleSet() <em>Module Set</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModuleSet()
   * @generated
   * @ordered
   */
  protected ModuleSet moduleSet;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SourceFeatureImpl()
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
    return WorkflowDslPackage.Literals.SOURCE_FEATURE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModuleSet getModuleSet()
  {
    if (moduleSet != null && moduleSet.eIsProxy())
    {
      InternalEObject oldModuleSet = (InternalEObject)moduleSet;
      moduleSet = (ModuleSet)eResolveProxy(oldModuleSet);
      if (moduleSet != oldModuleSet)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, WorkflowDslPackage.SOURCE_FEATURE__MODULE_SET, oldModuleSet, moduleSet));
      }
    }
    return moduleSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModuleSet basicGetModuleSet()
  {
    return moduleSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModuleSet(ModuleSet newModuleSet)
  {
    ModuleSet oldModuleSet = moduleSet;
    moduleSet = newModuleSet;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, WorkflowDslPackage.SOURCE_FEATURE__MODULE_SET, oldModuleSet, moduleSet));
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
      case WorkflowDslPackage.SOURCE_FEATURE__MODULE_SET:
        if (resolve) return getModuleSet();
        return basicGetModuleSet();
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
      case WorkflowDslPackage.SOURCE_FEATURE__MODULE_SET:
        setModuleSet((ModuleSet)newValue);
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
      case WorkflowDslPackage.SOURCE_FEATURE__MODULE_SET:
        setModuleSet((ModuleSet)null);
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
      case WorkflowDslPackage.SOURCE_FEATURE__MODULE_SET:
        return moduleSet != null;
    }
    return super.eIsSet(featureID);
  }

} //SourceFeatureImpl
