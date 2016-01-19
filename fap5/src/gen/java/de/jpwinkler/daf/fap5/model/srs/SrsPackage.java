/**
 */
package de.jpwinkler.daf.fap5.model.srs;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import de.jpwinkler.daf.dafcore.model.common.CommonPackage;

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
 * @see de.jpwinkler.daf.fap5.model.srs.SrsFactory
 * @model kind="package"
 * @generated
 */
public interface SrsPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "srs";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "de.jpwinkler.daf.fap5.model.srs";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "srs";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	SrsPackage eINSTANCE = de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl.init();

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.SRSModelImpl <em>SRS Model</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.srs.impl.SRSModelImpl
     * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getSRSModel()
     * @generated
     */
	int SRS_MODEL = 0;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SRS_MODEL__SOURCE = CommonPackage.MODEL_OBJECT__SOURCE;

	/**
     * The feature id for the '<em><b>Vehicle Functions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SRS_MODEL__VEHICLE_FUNCTIONS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>SRS Model</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SRS_MODEL_FEATURE_COUNT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
     * The number of operations of the '<em>SRS Model</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SRS_MODEL_OPERATION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.VehicleFunctionImpl <em>Vehicle Function</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.srs.impl.VehicleFunctionImpl
     * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getVehicleFunction()
     * @generated
     */
	int VEHICLE_FUNCTION = 1;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.FunctionalSpecificationElementImpl <em>Functional Specification Element</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.srs.impl.FunctionalSpecificationElementImpl
     * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getFunctionalSpecificationElement()
     * @generated
     */
	int FUNCTIONAL_SPECIFICATION_ELEMENT = 4;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONAL_SPECIFICATION_ELEMENT__SOURCE = CommonPackage.MODEL_OBJECT__SOURCE;

	/**
     * The number of structural features of the '<em>Functional Specification Element</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONAL_SPECIFICATION_ELEMENT_FEATURE_COUNT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
     * The number of operations of the '<em>Functional Specification Element</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONAL_SPECIFICATION_ELEMENT_OPERATION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.FunctionContributionImpl <em>Function Contribution</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.srs.impl.FunctionContributionImpl
     * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getFunctionContribution()
     * @generated
     */
	int FUNCTION_CONTRIBUTION = 2;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.FunctionalSpecificationContainerImpl <em>Functional Specification Container</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.srs.impl.FunctionalSpecificationContainerImpl
     * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getFunctionalSpecificationContainer()
     * @generated
     */
	int FUNCTIONAL_SPECIFICATION_CONTAINER = 3;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONAL_SPECIFICATION_CONTAINER__SOURCE = FUNCTIONAL_SPECIFICATION_ELEMENT__SOURCE;

	/**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS = FUNCTIONAL_SPECIFICATION_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Functional Specification Container</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT = FUNCTIONAL_SPECIFICATION_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The operation id for the '<em>Get Function Contributions</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONAL_SPECIFICATION_CONTAINER___GET_FUNCTION_CONTRIBUTIONS = FUNCTIONAL_SPECIFICATION_ELEMENT_OPERATION_COUNT + 0;

	/**
     * The number of operations of the '<em>Functional Specification Container</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONAL_SPECIFICATION_CONTAINER_OPERATION_COUNT = FUNCTIONAL_SPECIFICATION_ELEMENT_OPERATION_COUNT + 1;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION__SOURCE = FUNCTIONAL_SPECIFICATION_CONTAINER__SOURCE;

	/**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION__ELEMENTS = FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION__NAME = FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION__TYPE = FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Sub Functions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION__SUB_FUNCTIONS = FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION__PARENT = FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Vehicle Function</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_FEATURE_COUNT = FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT + 4;

	/**
     * The operation id for the '<em>Get Function Contributions</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION___GET_FUNCTION_CONTRIBUTIONS = FUNCTIONAL_SPECIFICATION_CONTAINER___GET_FUNCTION_CONTRIBUTIONS;

	/**
     * The number of operations of the '<em>Vehicle Function</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_OPERATION_COUNT = FUNCTIONAL_SPECIFICATION_CONTAINER_OPERATION_COUNT + 0;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION__SOURCE = FUNCTIONAL_SPECIFICATION_ELEMENT__SOURCE;

	/**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION__TARGET = FUNCTIONAL_SPECIFICATION_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION__TEXT = FUNCTIONAL_SPECIFICATION_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Function Contribution</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_FEATURE_COUNT = FUNCTIONAL_SPECIFICATION_ELEMENT_FEATURE_COUNT + 2;

	/**
     * The number of operations of the '<em>Function Contribution</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_OPERATION_COUNT = FUNCTIONAL_SPECIFICATION_ELEMENT_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.PreconditionImpl <em>Precondition</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.srs.impl.PreconditionImpl
     * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getPrecondition()
     * @generated
     */
	int PRECONDITION = 5;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PRECONDITION__SOURCE = FUNCTIONAL_SPECIFICATION_CONTAINER__SOURCE;

	/**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PRECONDITION__ELEMENTS = FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PRECONDITION__NAME = FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Precondition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PRECONDITION_FEATURE_COUNT = FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT + 1;

	/**
     * The operation id for the '<em>Get Function Contributions</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PRECONDITION___GET_FUNCTION_CONTRIBUTIONS = FUNCTIONAL_SPECIFICATION_CONTAINER___GET_FUNCTION_CONTRIBUTIONS;

	/**
     * The number of operations of the '<em>Precondition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PRECONDITION_OPERATION_COUNT = FUNCTIONAL_SPECIFICATION_CONTAINER_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.EndConditionImpl <em>End Condition</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.srs.impl.EndConditionImpl
     * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getEndCondition()
     * @generated
     */
	int END_CONDITION = 6;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_CONDITION__SOURCE = FUNCTIONAL_SPECIFICATION_CONTAINER__SOURCE;

	/**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_CONDITION__ELEMENTS = FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_CONDITION__NAME = FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>End Condition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_CONDITION_FEATURE_COUNT = FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT + 1;

	/**
     * The operation id for the '<em>Get Function Contributions</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_CONDITION___GET_FUNCTION_CONTRIBUTIONS = FUNCTIONAL_SPECIFICATION_CONTAINER___GET_FUNCTION_CONTRIBUTIONS;

	/**
     * The number of operations of the '<em>End Condition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int END_CONDITION_OPERATION_COUNT = FUNCTIONAL_SPECIFICATION_CONTAINER_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.TriggerImpl <em>Trigger</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.srs.impl.TriggerImpl
     * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getTrigger()
     * @generated
     */
	int TRIGGER = 7;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TRIGGER__SOURCE = FUNCTIONAL_SPECIFICATION_CONTAINER__SOURCE;

	/**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TRIGGER__ELEMENTS = FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TRIGGER__NAME = FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Trigger</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TRIGGER_FEATURE_COUNT = FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT + 1;

	/**
     * The operation id for the '<em>Get Function Contributions</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TRIGGER___GET_FUNCTION_CONTRIBUTIONS = FUNCTIONAL_SPECIFICATION_CONTAINER___GET_FUNCTION_CONTRIBUTIONS;

	/**
     * The number of operations of the '<em>Trigger</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TRIGGER_OPERATION_COUNT = FUNCTIONAL_SPECIFICATION_CONTAINER_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.HeadingImpl <em>Heading</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.srs.impl.HeadingImpl
     * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getHeading()
     * @generated
     */
	int HEADING = 8;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HEADING__SOURCE = FUNCTIONAL_SPECIFICATION_CONTAINER__SOURCE;

	/**
     * The feature id for the '<em><b>Elements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HEADING__ELEMENTS = FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HEADING__NAME = FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Heading</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HEADING_FEATURE_COUNT = FUNCTIONAL_SPECIFICATION_CONTAINER_FEATURE_COUNT + 1;

	/**
     * The operation id for the '<em>Get Function Contributions</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HEADING___GET_FUNCTION_CONTRIBUTIONS = FUNCTIONAL_SPECIFICATION_CONTAINER___GET_FUNCTION_CONTRIBUTIONS;

	/**
     * The number of operations of the '<em>Heading</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int HEADING_OPERATION_COUNT = FUNCTIONAL_SPECIFICATION_CONTAINER_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.DescriptionImpl <em>Description</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.srs.impl.DescriptionImpl
     * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getDescription()
     * @generated
     */
	int DESCRIPTION = 9;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DESCRIPTION__SOURCE = FUNCTIONAL_SPECIFICATION_ELEMENT__SOURCE;

	/**
     * The feature id for the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DESCRIPTION__TEXT = FUNCTIONAL_SPECIFICATION_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Description</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DESCRIPTION_FEATURE_COUNT = FUNCTIONAL_SPECIFICATION_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The number of operations of the '<em>Description</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DESCRIPTION_OPERATION_COUNT = FUNCTIONAL_SPECIFICATION_ELEMENT_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunctionType <em>Vehicle Function Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.srs.VehicleFunctionType
     * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getVehicleFunctionType()
     * @generated
     */
	int VEHICLE_FUNCTION_TYPE = 10;


	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.srs.SRSModel <em>SRS Model</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>SRS Model</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.SRSModel
     * @generated
     */
	EClass getSRSModel();

	/**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.fap5.model.srs.SRSModel#getVehicleFunctions <em>Vehicle Functions</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Vehicle Functions</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.SRSModel#getVehicleFunctions()
     * @see #getSRSModel()
     * @generated
     */
	EReference getSRSModel_VehicleFunctions();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction <em>Vehicle Function</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Vehicle Function</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.VehicleFunction
     * @generated
     */
	EClass getVehicleFunction();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getName()
     * @see #getVehicleFunction()
     * @generated
     */
	EAttribute getVehicleFunction_Name();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getType()
     * @see #getVehicleFunction()
     * @generated
     */
	EAttribute getVehicleFunction_Type();

	/**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getSubFunctions <em>Sub Functions</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Sub Functions</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getSubFunctions()
     * @see #getVehicleFunction()
     * @generated
     */
	EReference getVehicleFunction_SubFunctions();

	/**
     * Returns the meta object for the container reference '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.VehicleFunction#getParent()
     * @see #getVehicleFunction()
     * @generated
     */
	EReference getVehicleFunction_Parent();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.srs.FunctionContribution <em>Function Contribution</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Function Contribution</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.FunctionContribution
     * @generated
     */
	EClass getFunctionContribution();

	/**
     * Returns the meta object for the reference '{@link de.jpwinkler.daf.fap5.model.srs.FunctionContribution#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.FunctionContribution#getTarget()
     * @see #getFunctionContribution()
     * @generated
     */
	EReference getFunctionContribution_Target();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.srs.FunctionContribution#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.FunctionContribution#getText()
     * @see #getFunctionContribution()
     * @generated
     */
	EAttribute getFunctionContribution_Text();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer <em>Functional Specification Container</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Functional Specification Container</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer
     * @generated
     */
	EClass getFunctionalSpecificationContainer();

	/**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer#getElements <em>Elements</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Elements</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer#getElements()
     * @see #getFunctionalSpecificationContainer()
     * @generated
     */
	EReference getFunctionalSpecificationContainer_Elements();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer#getFunctionContributions() <em>Get Function Contributions</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Function Contributions</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationContainer#getFunctionContributions()
     * @generated
     */
	EOperation getFunctionalSpecificationContainer__GetFunctionContributions();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationElement <em>Functional Specification Element</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Functional Specification Element</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.FunctionalSpecificationElement
     * @generated
     */
	EClass getFunctionalSpecificationElement();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.srs.Precondition <em>Precondition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Precondition</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.Precondition
     * @generated
     */
	EClass getPrecondition();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.srs.Precondition#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.Precondition#getName()
     * @see #getPrecondition()
     * @generated
     */
	EAttribute getPrecondition_Name();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.srs.EndCondition <em>End Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>End Condition</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.EndCondition
     * @generated
     */
	EClass getEndCondition();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.srs.EndCondition#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.EndCondition#getName()
     * @see #getEndCondition()
     * @generated
     */
	EAttribute getEndCondition_Name();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.srs.Trigger <em>Trigger</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Trigger</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.Trigger
     * @generated
     */
	EClass getTrigger();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.srs.Trigger#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.Trigger#getName()
     * @see #getTrigger()
     * @generated
     */
	EAttribute getTrigger_Name();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.srs.Heading <em>Heading</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Heading</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.Heading
     * @generated
     */
	EClass getHeading();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.srs.Heading#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.Heading#getName()
     * @see #getHeading()
     * @generated
     */
	EAttribute getHeading_Name();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.srs.Description <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Description</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.Description
     * @generated
     */
	EClass getDescription();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.srs.Description#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.Description#getText()
     * @see #getDescription()
     * @generated
     */
	EAttribute getDescription_Text();

	/**
     * Returns the meta object for enum '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunctionType <em>Vehicle Function Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Vehicle Function Type</em>'.
     * @see de.jpwinkler.daf.fap5.model.srs.VehicleFunctionType
     * @generated
     */
	EEnum getVehicleFunctionType();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	SrsFactory getSrsFactory();

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
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.SRSModelImpl <em>SRS Model</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.srs.impl.SRSModelImpl
         * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getSRSModel()
         * @generated
         */
		EClass SRS_MODEL = eINSTANCE.getSRSModel();

		/**
         * The meta object literal for the '<em><b>Vehicle Functions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SRS_MODEL__VEHICLE_FUNCTIONS = eINSTANCE.getSRSModel_VehicleFunctions();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.VehicleFunctionImpl <em>Vehicle Function</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.srs.impl.VehicleFunctionImpl
         * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getVehicleFunction()
         * @generated
         */
		EClass VEHICLE_FUNCTION = eINSTANCE.getVehicleFunction();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VEHICLE_FUNCTION__NAME = eINSTANCE.getVehicleFunction_Name();

		/**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VEHICLE_FUNCTION__TYPE = eINSTANCE.getVehicleFunction_Type();

		/**
         * The meta object literal for the '<em><b>Sub Functions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VEHICLE_FUNCTION__SUB_FUNCTIONS = eINSTANCE.getVehicleFunction_SubFunctions();

		/**
         * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VEHICLE_FUNCTION__PARENT = eINSTANCE.getVehicleFunction_Parent();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.FunctionContributionImpl <em>Function Contribution</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.srs.impl.FunctionContributionImpl
         * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getFunctionContribution()
         * @generated
         */
		EClass FUNCTION_CONTRIBUTION = eINSTANCE.getFunctionContribution();

		/**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FUNCTION_CONTRIBUTION__TARGET = eINSTANCE.getFunctionContribution_Target();

		/**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FUNCTION_CONTRIBUTION__TEXT = eINSTANCE.getFunctionContribution_Text();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.FunctionalSpecificationContainerImpl <em>Functional Specification Container</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.srs.impl.FunctionalSpecificationContainerImpl
         * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getFunctionalSpecificationContainer()
         * @generated
         */
		EClass FUNCTIONAL_SPECIFICATION_CONTAINER = eINSTANCE.getFunctionalSpecificationContainer();

		/**
         * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FUNCTIONAL_SPECIFICATION_CONTAINER__ELEMENTS = eINSTANCE.getFunctionalSpecificationContainer_Elements();

		/**
         * The meta object literal for the '<em><b>Get Function Contributions</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation FUNCTIONAL_SPECIFICATION_CONTAINER___GET_FUNCTION_CONTRIBUTIONS = eINSTANCE.getFunctionalSpecificationContainer__GetFunctionContributions();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.FunctionalSpecificationElementImpl <em>Functional Specification Element</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.srs.impl.FunctionalSpecificationElementImpl
         * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getFunctionalSpecificationElement()
         * @generated
         */
		EClass FUNCTIONAL_SPECIFICATION_ELEMENT = eINSTANCE.getFunctionalSpecificationElement();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.PreconditionImpl <em>Precondition</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.srs.impl.PreconditionImpl
         * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getPrecondition()
         * @generated
         */
		EClass PRECONDITION = eINSTANCE.getPrecondition();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PRECONDITION__NAME = eINSTANCE.getPrecondition_Name();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.EndConditionImpl <em>End Condition</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.srs.impl.EndConditionImpl
         * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getEndCondition()
         * @generated
         */
		EClass END_CONDITION = eINSTANCE.getEndCondition();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute END_CONDITION__NAME = eINSTANCE.getEndCondition_Name();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.TriggerImpl <em>Trigger</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.srs.impl.TriggerImpl
         * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getTrigger()
         * @generated
         */
		EClass TRIGGER = eINSTANCE.getTrigger();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TRIGGER__NAME = eINSTANCE.getTrigger_Name();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.HeadingImpl <em>Heading</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.srs.impl.HeadingImpl
         * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getHeading()
         * @generated
         */
		EClass HEADING = eINSTANCE.getHeading();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute HEADING__NAME = eINSTANCE.getHeading_Name();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.srs.impl.DescriptionImpl <em>Description</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.srs.impl.DescriptionImpl
         * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getDescription()
         * @generated
         */
		EClass DESCRIPTION = eINSTANCE.getDescription();

		/**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DESCRIPTION__TEXT = eINSTANCE.getDescription_Text();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.srs.VehicleFunctionType <em>Vehicle Function Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.srs.VehicleFunctionType
         * @see de.jpwinkler.daf.fap5.model.srs.impl.SrsPackageImpl#getVehicleFunctionType()
         * @generated
         */
		EEnum VEHICLE_FUNCTION_TYPE = eINSTANCE.getVehicleFunctionType();

	}

} //SrsPackage
