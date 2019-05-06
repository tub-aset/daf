/**
 */
package de.jpwinkler.daf.model.util;

import de.jpwinkler.daf.model.*;
import java.util.Map;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.model.DoorsPackage
 * @generated
 */
public class DoorsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DoorsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoorsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DoorsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DoorsSwitch<Adapter> modelSwitch =
		new DoorsSwitch<Adapter>() {
			@Override
			public Adapter caseDoorsTreeNode(DoorsTreeNode object) {
				return createDoorsTreeNodeAdapter();
			}
			@Override
			public Adapter caseDoorsDB(DoorsDB object) {
				return createDoorsDBAdapter();
			}
			@Override
			public Adapter caseDoorsFolder(DoorsFolder object) {
				return createDoorsFolderAdapter();
			}
			@Override
			public Adapter caseDoorsDatabaseVersion(DoorsDatabaseVersion object) {
				return createDoorsDatabaseVersionAdapter();
			}
			@Override
			public Adapter caseDoorsModule(DoorsModule object) {
				return createDoorsModuleAdapter();
			}
			@Override
			public Adapter caseDoorsObject(DoorsObject object) {
				return createDoorsObjectAdapter();
			}
			@Override
			public Adapter caseStringToStringMap(Map.Entry<String, String> object) {
				return createStringToStringMapAdapter();
			}
			@Override
			public Adapter caseLink(Link object) {
				return createLinkAdapter();
			}
			@Override
			public Adapter caseResolvedLink(ResolvedLink object) {
				return createResolvedLinkAdapter();
			}
			@Override
			public Adapter caseUnresolvedLink(UnresolvedLink object) {
				return createUnresolvedLinkAdapter();
			}
			@Override
			public Adapter caseAttributeDefinition(AttributeDefinition object) {
				return createAttributeDefinitionAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.model.DoorsTreeNode <em>Tree Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.model.DoorsTreeNode
	 * @generated
	 */
	public Adapter createDoorsTreeNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.model.DoorsDB <em>DB</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.model.DoorsDB
	 * @generated
	 */
	public Adapter createDoorsDBAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.model.DoorsFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.model.DoorsFolder
	 * @generated
	 */
	public Adapter createDoorsFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.model.DoorsDatabaseVersion <em>Database Version</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.model.DoorsDatabaseVersion
	 * @generated
	 */
	public Adapter createDoorsDatabaseVersionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.model.DoorsModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.model.DoorsModule
	 * @generated
	 */
	public Adapter createDoorsModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.model.DoorsObject <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.model.DoorsObject
	 * @generated
	 */
	public Adapter createDoorsObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To String Map</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringToStringMapAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.model.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.model.Link
	 * @generated
	 */
	public Adapter createLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.model.ResolvedLink <em>Resolved Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.model.ResolvedLink
	 * @generated
	 */
	public Adapter createResolvedLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.model.UnresolvedLink <em>Unresolved Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.model.UnresolvedLink
	 * @generated
	 */
	public Adapter createUnresolvedLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.model.AttributeDefinition <em>Attribute Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.model.AttributeDefinition
	 * @generated
	 */
	public Adapter createAttributeDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //DoorsAdapterFactory
