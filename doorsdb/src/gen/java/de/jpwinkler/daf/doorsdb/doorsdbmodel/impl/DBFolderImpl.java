/**
 */
package de.jpwinkler.daf.doorsdb.doorsdbmodel.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBFolder;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBItem;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule;
import de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage;

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
        return DoorsDBModelPackage.Literals.DB_FOLDER;
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
            case DoorsDBModelPackage.DB_FOLDER___GET_FOLDER__STRING:
                return getFolder((String)arguments.get(0));
            case DoorsDBModelPackage.DB_FOLDER___GET_MODULE__STRING:
                return getModule((String)arguments.get(0));
        }
        return super.eInvoke(operationID, arguments);
    }

} //DBFolderImpl
