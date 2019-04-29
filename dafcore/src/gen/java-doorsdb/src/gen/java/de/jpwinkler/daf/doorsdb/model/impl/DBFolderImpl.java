/**
 */
package de.jpwinkler.daf.doorsdb.model.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import de.jpwinkler.daf.doorsdb.model.DBFolder;
import de.jpwinkler.daf.doorsdb.model.DBItem;
import de.jpwinkler.daf.doorsdb.model.DBModule;
import de.jpwinkler.daf.doorsdb.model.DoorsDBPackage;

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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public DBFolder getFolder(final String name) {
        for (final DBItem item : getChildren()) {
            if (item instanceof DBFolder && item.getName().equals(name)) {
                return (DBFolder) item;
            }
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public DBModule getModule(final String name) {
        for (final DBItem item : getChildren()) {
            if (item instanceof DBModule && item.getName().equals(name)) {
                return (DBModule) item;
            }
        }
        return null;
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
