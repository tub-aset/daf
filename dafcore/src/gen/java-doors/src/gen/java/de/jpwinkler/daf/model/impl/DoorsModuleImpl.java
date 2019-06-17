/**
 */
package de.jpwinkler.daf.model.impl;

import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.FindObjectVisitor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Doors Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class DoorsModuleImpl extends DoorsTreeNodeImpl implements DoorsModule {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    protected DoorsModuleImpl() {
		super();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    protected EClass eStaticClass() {
		return DoorsPackage.Literals.DOORS_MODULE;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public String getView() {
            return null;
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public DoorsObject findObject(final String objectIdentifier) {
        final FindObjectVisitor visitor = new FindObjectVisitor(objectIdentifier);
        accept(visitor);
        return visitor.getObject();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public List<String> getObjectAttributes() {
        return DoorsAttributes.MODULE_OBJECT_ATTRIBUTES.getValue(List.class, this);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public void setObjectAttributes(List<String> attrs) {
        DoorsAttributes.MODULE_OBJECT_ATTRIBUTES.setValue(List.class, this, attrs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case DoorsPackage.DOORS_MODULE___GET_VIEW:
				return getView();
			case DoorsPackage.DOORS_MODULE___FIND_OBJECT__STRING:
				return findObject((String)arguments.get(0));
			case DoorsPackage.DOORS_MODULE___GET_OBJECT_ATTRIBUTES:
				return getObjectAttributes();
			case DoorsPackage.DOORS_MODULE___SET_OBJECT_ATTRIBUTES__LIST:
				setObjectAttributes((List<String>)arguments.get(0));
				return null;
		}
		return super.eInvoke(operationID, arguments);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return super.toString();
	}


} //DoorsModuleImpl
