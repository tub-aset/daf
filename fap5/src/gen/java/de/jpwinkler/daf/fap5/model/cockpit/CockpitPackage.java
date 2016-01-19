/**
 */
package de.jpwinkler.daf.fap5.model.cockpit;

import org.eclipse.emf.ecore.EClass;
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
 * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitFactory
 * @model kind="package"
 * @generated
 */
public interface CockpitPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "cockpit";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "de.jpwinkler.daf.fap5.model.cockpit";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "cockpit";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	CockpitPackage eINSTANCE = de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitPackageImpl.init();

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitModelImpl
     * @see de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitPackageImpl#getCockpitModel()
     * @generated
     */
	int COCKPIT_MODEL = 0;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COCKPIT_MODEL__SOURCE = CommonPackage.MODEL_OBJECT__SOURCE;

	/**
     * The feature id for the '<em><b>Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COCKPIT_MODEL__MAPPINGS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Vehicle Function Progress</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COCKPIT_MODEL__VEHICLE_FUNCTION_PROGRESS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Documents</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COCKPIT_MODEL__DOCUMENTS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Model</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COCKPIT_MODEL_FEATURE_COUNT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 3;

	/**
     * The operation id for the '<em>Find Mapping</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COCKPIT_MODEL___FIND_MAPPING__FUNCTIONCONTRIBUTIONTARGET = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 0;

	/**
     * The operation id for the '<em>Find Function Contribution Targets</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COCKPIT_MODEL___FIND_FUNCTION_CONTRIBUTION_TARGETS__CODEBEAMERMODEL = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 1;

	/**
     * The operation id for the '<em>Get Issue Types</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COCKPIT_MODEL___GET_ISSUE_TYPES = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 2;

	/**
     * The operation id for the '<em>Get Issues</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COCKPIT_MODEL___GET_ISSUES__STRING = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 3;

	/**
     * The number of operations of the '<em>Model</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COCKPIT_MODEL_OPERATION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 4;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.cockpit.impl.VehicleFunctionProgressImpl <em>Vehicle Function Progress</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.cockpit.impl.VehicleFunctionProgressImpl
     * @see de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitPackageImpl#getVehicleFunctionProgress()
     * @generated
     */
	int VEHICLE_FUNCTION_PROGRESS = 1;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS__SOURCE = CommonPackage.MODEL_OBJECT__SOURCE;

	/**
     * The feature id for the '<em><b>Vehicle Function</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS__VEHICLE_FUNCTION = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Cockpit Model</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS__COCKPIT_MODEL = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Sub Function Progress</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS__SUB_FUNCTION_PROGRESS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Parent Progress</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Function Contribution Progress</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS__FUNCTION_CONTRIBUTION_PROGRESS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>Vehicle Function Progress</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS_FEATURE_COUNT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 5;

	/**
     * The operation id for the '<em>Is Specified</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS___IS_SPECIFIED = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 0;

	/**
     * The operation id for the '<em>Is Mapped</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS___IS_MAPPED = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 1;

	/**
     * The operation id for the '<em>Get Progress</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS___GET_PROGRESS = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 2;

	/**
     * The operation id for the '<em>Get Estimated Remaining Work</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS___GET_ESTIMATED_REMAINING_WORK = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 3;

	/**
     * The operation id for the '<em>Get Specified Function Contribution Count</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS___GET_SPECIFIED_FUNCTION_CONTRIBUTION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 4;

	/**
     * The operation id for the '<em>Get Total Function Contribution Count</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS___GET_TOTAL_FUNCTION_CONTRIBUTION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 5;

	/**
     * The number of operations of the '<em>Vehicle Function Progress</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VEHICLE_FUNCTION_PROGRESS_OPERATION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 6;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.cockpit.impl.FunctionContributionTargetMappingImpl <em>Function Contribution Target Mapping</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.cockpit.impl.FunctionContributionTargetMappingImpl
     * @see de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitPackageImpl#getFunctionContributionTargetMapping()
     * @generated
     */
	int FUNCTION_CONTRIBUTION_TARGET_MAPPING = 2;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_TARGET_MAPPING__SOURCE = CommonPackage.MODEL_OBJECT__SOURCE;

	/**
     * The feature id for the '<em><b>Function Contribution Target</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_TARGET_MAPPING__FUNCTION_CONTRIBUTION_TARGET = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Documents</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_TARGET_MAPPING__DOCUMENTS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Function Contribution Target Mapping</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_TARGET_MAPPING_FEATURE_COUNT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
     * The number of operations of the '<em>Function Contribution Target Mapping</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_TARGET_MAPPING_OPERATION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.cockpit.impl.FunctionContributionProgressImpl <em>Function Contribution Progress</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.cockpit.impl.FunctionContributionProgressImpl
     * @see de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitPackageImpl#getFunctionContributionProgress()
     * @generated
     */
	int FUNCTION_CONTRIBUTION_PROGRESS = 3;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_PROGRESS__SOURCE = CommonPackage.MODEL_OBJECT__SOURCE;

	/**
     * The feature id for the '<em><b>Function Contribution</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_PROGRESS__FUNCTION_CONTRIBUTION = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Cockpit Model</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_PROGRESS__COCKPIT_MODEL = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Function Contribution Progress</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_PROGRESS_FEATURE_COUNT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
     * The operation id for the '<em>Is Mapped</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_PROGRESS___IS_MAPPED = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 0;

	/**
     * The operation id for the '<em>Is Specified</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_PROGRESS___IS_SPECIFIED = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 1;

	/**
     * The operation id for the '<em>Get Exstimated Remaining Work</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_PROGRESS___GET_EXSTIMATED_REMAINING_WORK = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 2;

	/**
     * The number of operations of the '<em>Function Contribution Progress</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_PROGRESS_OPERATION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 3;

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.cockpit.CockpitModel <em>Model</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Model</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitModel
     * @generated
     */
	EClass getCockpitModel();

	/**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#getVehicleFunctionProgress <em>Vehicle Function Progress</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Vehicle Function Progress</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#getVehicleFunctionProgress()
     * @see #getCockpitModel()
     * @generated
     */
	EReference getCockpitModel_VehicleFunctionProgress();

	/**
     * Returns the meta object for the reference list '{@link de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#getDocuments <em>Documents</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Documents</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#getDocuments()
     * @see #getCockpitModel()
     * @generated
     */
	EReference getCockpitModel_Documents();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#findMapping(de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget) <em>Find Mapping</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Find Mapping</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#findMapping(de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget)
     * @generated
     */
	EOperation getCockpitModel__FindMapping__FunctionContributionTarget();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#findFunctionContributionTargets(de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel) <em>Find Function Contribution Targets</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Find Function Contribution Targets</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#findFunctionContributionTargets(de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel)
     * @generated
     */
	EOperation getCockpitModel__FindFunctionContributionTargets__CodeBeamerModel();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#getIssueTypes() <em>Get Issue Types</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Issue Types</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#getIssueTypes()
     * @generated
     */
	EOperation getCockpitModel__GetIssueTypes();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#getIssues(java.lang.String) <em>Get Issues</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Issues</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#getIssues(java.lang.String)
     * @generated
     */
	EOperation getCockpitModel__GetIssues__String();

	/**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#getMappings <em>Mappings</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Mappings</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.CockpitModel#getMappings()
     * @see #getCockpitModel()
     * @generated
     */
	EReference getCockpitModel_Mappings();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress <em>Vehicle Function Progress</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Vehicle Function Progress</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress
     * @generated
     */
	EClass getVehicleFunctionProgress();

	/**
     * Returns the meta object for the reference '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getVehicleFunction <em>Vehicle Function</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Vehicle Function</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getVehicleFunction()
     * @see #getVehicleFunctionProgress()
     * @generated
     */
	EReference getVehicleFunctionProgress_VehicleFunction();

	/**
     * Returns the meta object for the reference '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getCockpitModel <em>Cockpit Model</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Cockpit Model</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getCockpitModel()
     * @see #getVehicleFunctionProgress()
     * @generated
     */
	EReference getVehicleFunctionProgress_CockpitModel();

	/**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getSubFunctionProgress <em>Sub Function Progress</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Sub Function Progress</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getSubFunctionProgress()
     * @see #getVehicleFunctionProgress()
     * @generated
     */
	EReference getVehicleFunctionProgress_SubFunctionProgress();

	/**
     * Returns the meta object for the container reference '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getParentProgress <em>Parent Progress</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent Progress</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getParentProgress()
     * @see #getVehicleFunctionProgress()
     * @generated
     */
	EReference getVehicleFunctionProgress_ParentProgress();

	/**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getFunctionContributionProgress <em>Function Contribution Progress</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Function Contribution Progress</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getFunctionContributionProgress()
     * @see #getVehicleFunctionProgress()
     * @generated
     */
	EReference getVehicleFunctionProgress_FunctionContributionProgress();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#isSpecified() <em>Is Specified</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Is Specified</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#isSpecified()
     * @generated
     */
	EOperation getVehicleFunctionProgress__IsSpecified();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#isMapped() <em>Is Mapped</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Is Mapped</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#isMapped()
     * @generated
     */
	EOperation getVehicleFunctionProgress__IsMapped();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getProgress() <em>Get Progress</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Progress</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getProgress()
     * @generated
     */
	EOperation getVehicleFunctionProgress__GetProgress();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getEstimatedRemainingWork() <em>Get Estimated Remaining Work</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Estimated Remaining Work</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getEstimatedRemainingWork()
     * @generated
     */
	EOperation getVehicleFunctionProgress__GetEstimatedRemainingWork();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getSpecifiedFunctionContributionCount() <em>Get Specified Function Contribution Count</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Specified Function Contribution Count</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getSpecifiedFunctionContributionCount()
     * @generated
     */
	EOperation getVehicleFunctionProgress__GetSpecifiedFunctionContributionCount();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getTotalFunctionContributionCount() <em>Get Total Function Contribution Count</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Total Function Contribution Count</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.cockpit.VehicleFunctionProgress#getTotalFunctionContributionCount()
     * @generated
     */
	EOperation getVehicleFunctionProgress__GetTotalFunctionContributionCount();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping <em>Function Contribution Target Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Function Contribution Target Mapping</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping
     * @generated
     */
	EClass getFunctionContributionTargetMapping();

	/**
     * Returns the meta object for the reference '{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping#getFunctionContributionTarget <em>Function Contribution Target</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Function Contribution Target</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping#getFunctionContributionTarget()
     * @see #getFunctionContributionTargetMapping()
     * @generated
     */
	EReference getFunctionContributionTargetMapping_FunctionContributionTarget();

	/**
     * Returns the meta object for the reference list '{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping#getDocuments <em>Documents</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Documents</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionTargetMapping#getDocuments()
     * @see #getFunctionContributionTargetMapping()
     * @generated
     */
	EReference getFunctionContributionTargetMapping_Documents();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress <em>Function Contribution Progress</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Function Contribution Progress</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress
     * @generated
     */
	EClass getFunctionContributionProgress();

	/**
     * Returns the meta object for the reference '{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#getFunctionContribution <em>Function Contribution</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Function Contribution</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#getFunctionContribution()
     * @see #getFunctionContributionProgress()
     * @generated
     */
	EReference getFunctionContributionProgress_FunctionContribution();

	/**
     * Returns the meta object for the reference '{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#getCockpitModel <em>Cockpit Model</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Cockpit Model</em>'.
     * @see de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#getCockpitModel()
     * @see #getFunctionContributionProgress()
     * @generated
     */
	EReference getFunctionContributionProgress_CockpitModel();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#isMapped() <em>Is Mapped</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Is Mapped</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#isMapped()
     * @generated
     */
	EOperation getFunctionContributionProgress__IsMapped();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#isSpecified() <em>Is Specified</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Is Specified</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#isSpecified()
     * @generated
     */
	EOperation getFunctionContributionProgress__IsSpecified();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#getExstimatedRemainingWork() <em>Get Exstimated Remaining Work</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Exstimated Remaining Work</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.cockpit.FunctionContributionProgress#getExstimatedRemainingWork()
     * @generated
     */
	EOperation getFunctionContributionProgress__GetExstimatedRemainingWork();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	CockpitFactory getCockpitFactory();

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
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitModelImpl <em>Model</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitModelImpl
         * @see de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitPackageImpl#getCockpitModel()
         * @generated
         */
		EClass COCKPIT_MODEL = eINSTANCE.getCockpitModel();

		/**
         * The meta object literal for the '<em><b>Vehicle Function Progress</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference COCKPIT_MODEL__VEHICLE_FUNCTION_PROGRESS = eINSTANCE.getCockpitModel_VehicleFunctionProgress();

		/**
         * The meta object literal for the '<em><b>Documents</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference COCKPIT_MODEL__DOCUMENTS = eINSTANCE.getCockpitModel_Documents();

		/**
         * The meta object literal for the '<em><b>Find Mapping</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation COCKPIT_MODEL___FIND_MAPPING__FUNCTIONCONTRIBUTIONTARGET = eINSTANCE.getCockpitModel__FindMapping__FunctionContributionTarget();

		/**
         * The meta object literal for the '<em><b>Find Function Contribution Targets</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation COCKPIT_MODEL___FIND_FUNCTION_CONTRIBUTION_TARGETS__CODEBEAMERMODEL = eINSTANCE.getCockpitModel__FindFunctionContributionTargets__CodeBeamerModel();

		/**
         * The meta object literal for the '<em><b>Get Issue Types</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation COCKPIT_MODEL___GET_ISSUE_TYPES = eINSTANCE.getCockpitModel__GetIssueTypes();

		/**
         * The meta object literal for the '<em><b>Get Issues</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation COCKPIT_MODEL___GET_ISSUES__STRING = eINSTANCE.getCockpitModel__GetIssues__String();

		/**
         * The meta object literal for the '<em><b>Mappings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference COCKPIT_MODEL__MAPPINGS = eINSTANCE.getCockpitModel_Mappings();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.cockpit.impl.VehicleFunctionProgressImpl <em>Vehicle Function Progress</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.cockpit.impl.VehicleFunctionProgressImpl
         * @see de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitPackageImpl#getVehicleFunctionProgress()
         * @generated
         */
		EClass VEHICLE_FUNCTION_PROGRESS = eINSTANCE.getVehicleFunctionProgress();

		/**
         * The meta object literal for the '<em><b>Vehicle Function</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VEHICLE_FUNCTION_PROGRESS__VEHICLE_FUNCTION = eINSTANCE.getVehicleFunctionProgress_VehicleFunction();

		/**
         * The meta object literal for the '<em><b>Cockpit Model</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VEHICLE_FUNCTION_PROGRESS__COCKPIT_MODEL = eINSTANCE.getVehicleFunctionProgress_CockpitModel();

		/**
         * The meta object literal for the '<em><b>Sub Function Progress</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VEHICLE_FUNCTION_PROGRESS__SUB_FUNCTION_PROGRESS = eINSTANCE.getVehicleFunctionProgress_SubFunctionProgress();

		/**
         * The meta object literal for the '<em><b>Parent Progress</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VEHICLE_FUNCTION_PROGRESS__PARENT_PROGRESS = eINSTANCE.getVehicleFunctionProgress_ParentProgress();

		/**
         * The meta object literal for the '<em><b>Function Contribution Progress</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VEHICLE_FUNCTION_PROGRESS__FUNCTION_CONTRIBUTION_PROGRESS = eINSTANCE.getVehicleFunctionProgress_FunctionContributionProgress();

		/**
         * The meta object literal for the '<em><b>Is Specified</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation VEHICLE_FUNCTION_PROGRESS___IS_SPECIFIED = eINSTANCE.getVehicleFunctionProgress__IsSpecified();

		/**
         * The meta object literal for the '<em><b>Is Mapped</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation VEHICLE_FUNCTION_PROGRESS___IS_MAPPED = eINSTANCE.getVehicleFunctionProgress__IsMapped();

		/**
         * The meta object literal for the '<em><b>Get Progress</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation VEHICLE_FUNCTION_PROGRESS___GET_PROGRESS = eINSTANCE.getVehicleFunctionProgress__GetProgress();

		/**
         * The meta object literal for the '<em><b>Get Estimated Remaining Work</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation VEHICLE_FUNCTION_PROGRESS___GET_ESTIMATED_REMAINING_WORK = eINSTANCE.getVehicleFunctionProgress__GetEstimatedRemainingWork();

		/**
         * The meta object literal for the '<em><b>Get Specified Function Contribution Count</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation VEHICLE_FUNCTION_PROGRESS___GET_SPECIFIED_FUNCTION_CONTRIBUTION_COUNT = eINSTANCE.getVehicleFunctionProgress__GetSpecifiedFunctionContributionCount();

		/**
         * The meta object literal for the '<em><b>Get Total Function Contribution Count</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation VEHICLE_FUNCTION_PROGRESS___GET_TOTAL_FUNCTION_CONTRIBUTION_COUNT = eINSTANCE.getVehicleFunctionProgress__GetTotalFunctionContributionCount();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.cockpit.impl.FunctionContributionTargetMappingImpl <em>Function Contribution Target Mapping</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.cockpit.impl.FunctionContributionTargetMappingImpl
         * @see de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitPackageImpl#getFunctionContributionTargetMapping()
         * @generated
         */
		EClass FUNCTION_CONTRIBUTION_TARGET_MAPPING = eINSTANCE.getFunctionContributionTargetMapping();

		/**
         * The meta object literal for the '<em><b>Function Contribution Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FUNCTION_CONTRIBUTION_TARGET_MAPPING__FUNCTION_CONTRIBUTION_TARGET = eINSTANCE.getFunctionContributionTargetMapping_FunctionContributionTarget();

		/**
         * The meta object literal for the '<em><b>Documents</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FUNCTION_CONTRIBUTION_TARGET_MAPPING__DOCUMENTS = eINSTANCE.getFunctionContributionTargetMapping_Documents();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.cockpit.impl.FunctionContributionProgressImpl <em>Function Contribution Progress</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.cockpit.impl.FunctionContributionProgressImpl
         * @see de.jpwinkler.daf.fap5.model.cockpit.impl.CockpitPackageImpl#getFunctionContributionProgress()
         * @generated
         */
		EClass FUNCTION_CONTRIBUTION_PROGRESS = eINSTANCE.getFunctionContributionProgress();

		/**
         * The meta object literal for the '<em><b>Function Contribution</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FUNCTION_CONTRIBUTION_PROGRESS__FUNCTION_CONTRIBUTION = eINSTANCE.getFunctionContributionProgress_FunctionContribution();

		/**
         * The meta object literal for the '<em><b>Cockpit Model</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FUNCTION_CONTRIBUTION_PROGRESS__COCKPIT_MODEL = eINSTANCE.getFunctionContributionProgress_CockpitModel();

		/**
         * The meta object literal for the '<em><b>Is Mapped</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation FUNCTION_CONTRIBUTION_PROGRESS___IS_MAPPED = eINSTANCE.getFunctionContributionProgress__IsMapped();

		/**
         * The meta object literal for the '<em><b>Is Specified</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation FUNCTION_CONTRIBUTION_PROGRESS___IS_SPECIFIED = eINSTANCE.getFunctionContributionProgress__IsSpecified();

		/**
         * The meta object literal for the '<em><b>Get Exstimated Remaining Work</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation FUNCTION_CONTRIBUTION_PROGRESS___GET_EXSTIMATED_REMAINING_WORK = eINSTANCE.getFunctionContributionProgress__GetExstimatedRemainingWork();

	}

} //CockpitPackage
