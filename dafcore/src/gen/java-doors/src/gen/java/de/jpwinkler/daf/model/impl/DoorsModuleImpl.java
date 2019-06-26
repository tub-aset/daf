package de.jpwinkler.daf.model.impl;

/*-
 * #%L
 * dafcore
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
import static de.jpwinkler.daf.bridge.DoorsApplication.STANDARD_VIEW;
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsModule;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.DoorsTreeNodeVisitor;
import de.jpwinkler.daf.model.FindObjectVisitor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
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
        return STANDARD_VIEW;
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
        LinkedHashSet<String> objectAttrs = new LinkedHashSet<>(DoorsAttributes.valuesFor(DoorsObject.class)
                .filter(v -> !v.isSystemKey())
                .map(a -> a.getKey())
                .collect(Collectors.toList()));
        this.accept(new DoorsTreeNodeVisitor(DoorsObject.class) {
            @Override
            protected void visitPostTraverse(DoorsTreeNode object) {
                objectAttrs.addAll(object.getAttributes().keySet());
            }

        });
        return new ArrayList<>(objectAttrs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    @Override
    public void setObjectAttributes(List<String> attrs) {
        this.accept(new DoorsTreeNodeVisitor(DoorsObject.class) {
            @Override
            protected void visitPostTraverse(DoorsTreeNode object) {
                attrs.stream()
                        .filter(a -> !object.getAttributes().containsKey(a))
                        .forEach(a -> object.getAttributes().put(a, ""));
            }

        });
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

} //DoorsModuleImpl
