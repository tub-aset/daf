/**
 */
package de.jpwinkler.daf.doorsdb.doorsdbmodel.util;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.*;

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
 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBPackage
 * @generated
 */
public class DoorsDBAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DoorsDBPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoorsDBAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DoorsDBPackage.eINSTANCE;
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
	protected DoorsDBSwitch<Adapter> modelSwitch =
		new DoorsDBSwitch<Adapter>() {
			@Override
			public Adapter caseDBItem(DBItem object) {
				return createDBItemAdapter();
			}
			@Override
			public Adapter caseDBFolder(DBFolder object) {
				return createDBFolderAdapter();
			}
			@Override
			public Adapter caseDBModule(DBModule object) {
				return createDBModuleAdapter();
			}
			@Override
			public Adapter caseDBVersion(DBVersion object) {
				return createDBVersionAdapter();
			}
			@Override
			public Adapter caseDoorsDB(DoorsDB object) {
				return createDoorsDBAdapter();
			}
			@Override
			public Adapter caseDBTag(DBTag object) {
				return createDBTagAdapter();
			}
			@Override
			public Adapter caseStringToStringMap(Map.Entry<String, String> object) {
				return createStringToStringMapAdapter();
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
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem <em>DB Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem
	 * @generated
	 */
	public Adapter createDBItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder <em>DB Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder
	 * @generated
	 */
	public Adapter createDBFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule <em>DB Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule
	 * @generated
	 */
	public Adapter createDBModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion <em>DB Version</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion
	 * @generated
	 */
	public Adapter createDBVersionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB <em>Doors DB</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDB
	 * @generated
	 */
	public Adapter createDoorsDBAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag <em>DB Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag
	 * @generated
	 */
	public Adapter createDBTagAdapter() {
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

} //DoorsDBAdapterFactory
