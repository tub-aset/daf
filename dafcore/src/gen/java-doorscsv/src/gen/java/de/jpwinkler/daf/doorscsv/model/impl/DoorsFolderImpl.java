/**
 */
package de.jpwinkler.daf.doorscsv.model.impl;

import de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage;
import de.jpwinkler.daf.doorscsv.model.DoorsFolder;
import de.jpwinkler.daf.doorscsv.model.DoorsModule;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Doors Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class DoorsFolderImpl extends DoorsTreeNodeImpl implements DoorsFolder {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DoorsFolderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DoorsCSVPackage.Literals.DOORS_FOLDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsFolder getFolder(String name) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsModule getModule(String name) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case DoorsCSVPackage.DOORS_FOLDER___GET_FOLDER__STRING:
				return getFolder((String)arguments.get(0));
			case DoorsCSVPackage.DOORS_FOLDER___GET_MODULE__STRING:
				return getModule((String)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

} //DoorsFolderImpl
