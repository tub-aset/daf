/**
 */
package de.jpwinkler.daf.model.impl;

import de.jpwinkler.daf.model.DoorsFolder;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.model.DoorsTreeNode;
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
		return DoorsPackage.Literals.DOORS_FOLDER;
	}

	    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public DoorsFolder getFolder(final String name) {
        for (final DoorsTreeNode item : getChildren()) {
            if (item instanceof DoorsFolder && item.getName().equals(name)) {
                return (DoorsFolder) item;
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
    public DoorsModule getModule(final String name) {
        for (final DoorsTreeNode item : getChildren()) {
            if (item instanceof DoorsModule && item.getName().equals(name)) {
                return (DoorsModule) item;
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
			case DoorsPackage.DOORS_FOLDER___GET_FOLDER__STRING:
				return getFolder((String)arguments.get(0));
			case DoorsPackage.DOORS_FOLDER___GET_MODULE__STRING:
				return getModule((String)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

} //DoorsFolderImpl
