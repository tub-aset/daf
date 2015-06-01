/**
 */
package de.jpwinkler.daf.dafcore.model.common;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.jpwinkler.daf.dafcore.model.common.CommonFactory
 * @model kind="package"
 * @generated
 */
public interface CommonPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "common";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "de.jpwinkler.daf.dafcore.model.common";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "common";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    CommonPackage eINSTANCE = de.jpwinkler.daf.dafcore.model.common.impl.CommonPackageImpl.init();

    /**
     * The meta object id for the '{@link de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl <em>Model Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl
     * @see de.jpwinkler.daf.dafcore.model.common.impl.CommonPackageImpl#getModelObject()
     * @generated
     */
    int MODEL_OBJECT = 0;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODEL_OBJECT__SOURCE = 0;

    /**
     * The number of structural features of the '<em>Model Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODEL_OBJECT_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Model Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MODEL_OBJECT_OPERATION_COUNT = 0;


    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.dafcore.model.common.ModelObject <em>Model Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Model Object</em>'.
     * @see de.jpwinkler.daf.dafcore.model.common.ModelObject
     * @generated
     */
    EClass getModelObject();

    /**
     * Returns the meta object for the reference '{@link de.jpwinkler.daf.dafcore.model.common.ModelObject#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see de.jpwinkler.daf.dafcore.model.common.ModelObject#getSource()
     * @see #getModelObject()
     * @generated
     */
    EReference getModelObject_Source();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    CommonFactory getCommonFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each operation of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl <em>Model Object</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl
         * @see de.jpwinkler.daf.dafcore.model.common.impl.CommonPackageImpl#getModelObject()
         * @generated
         */
        EClass MODEL_OBJECT = eINSTANCE.getModelObject();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MODEL_OBJECT__SOURCE = eINSTANCE.getModelObject_Source();

    }

} //CommonPackage
