/**
 */
package de.jpwinkler.daf.doorsdb.doorsdbmodel.impl;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBPackage;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class DBFolderImpl extends DBItemImpl implements DBFolder {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DBFolderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DoorsDBPackage.Literals.DB_FOLDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DBFolder getFolder(String name) {
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
	public DBModule getModule(String name) {
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
			case DoorsDBPackage.DB_FOLDER___GET_FOLDER__STRING:
				return getFolder((String)arguments.get(0));
			case DoorsDBPackage.DB_FOLDER___GET_MODULE__STRING:
				return getModule((String)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

} //DBFolderImpl
