/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsFactory
 * @model kind="package"
 * @generated
 */
public interface ComponentsSystemsPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "componentssystems";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "de.jpwinkler.daf.fap5.model.componentssystems";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "componentssystems";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	ComponentsSystemsPackage eINSTANCE = de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl.init();

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsModelImpl
     * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl#getComponentsSystemsModel()
     * @generated
     */
	int COMPONENTS_SYSTEMS_MODEL = 0;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPONENTS_SYSTEMS_MODEL__SOURCE = CommonPackage.MODEL_OBJECT__SOURCE;

	/**
     * The feature id for the '<em><b>Function Contribution Targets</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPONENTS_SYSTEMS_MODEL__FUNCTION_CONTRIBUTION_TARGETS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Model</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPONENTS_SYSTEMS_MODEL_FEATURE_COUNT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
     * The number of operations of the '<em>Model</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPONENTS_SYSTEMS_MODEL_OPERATION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionContributionTargetImpl <em>Function Contribution Target</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionContributionTargetImpl
     * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl#getFunctionContributionTarget()
     * @generated
     */
	int FUNCTION_CONTRIBUTION_TARGET = 1;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_TARGET__SOURCE = CommonPackage.MODEL_OBJECT__SOURCE;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_TARGET__NAME = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Acronym</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_TARGET__ACRONYM = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Responsibility</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_TARGET__RESPONSIBILITY = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Department</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_TARGET__DEPARTMENT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Contributions</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>Function Contribution Target</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_TARGET_FEATURE_COUNT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 5;

	/**
     * The number of operations of the '<em>Function Contribution Target</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTION_CONTRIBUTION_TARGET_OPERATION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.SystemImpl <em>System</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.SystemImpl
     * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl#getSystem()
     * @generated
     */
	int SYSTEM = 2;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SYSTEM__SOURCE = FUNCTION_CONTRIBUTION_TARGET__SOURCE;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SYSTEM__NAME = FUNCTION_CONTRIBUTION_TARGET__NAME;

	/**
     * The feature id for the '<em><b>Acronym</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SYSTEM__ACRONYM = FUNCTION_CONTRIBUTION_TARGET__ACRONYM;

	/**
     * The feature id for the '<em><b>Responsibility</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SYSTEM__RESPONSIBILITY = FUNCTION_CONTRIBUTION_TARGET__RESPONSIBILITY;

	/**
     * The feature id for the '<em><b>Department</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SYSTEM__DEPARTMENT = FUNCTION_CONTRIBUTION_TARGET__DEPARTMENT;

	/**
     * The feature id for the '<em><b>Contributions</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SYSTEM__CONTRIBUTIONS = FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS;

	/**
     * The feature id for the '<em><b>Functionalities</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SYSTEM__FUNCTIONALITIES = FUNCTION_CONTRIBUTION_TARGET_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>System</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SYSTEM_FEATURE_COUNT = FUNCTION_CONTRIBUTION_TARGET_FEATURE_COUNT + 1;

	/**
     * The number of operations of the '<em>System</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SYSTEM_OPERATION_COUNT = FUNCTION_CONTRIBUTION_TARGET_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentImpl <em>Component</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentImpl
     * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl#getComponent()
     * @generated
     */
	int COMPONENT = 3;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPONENT__SOURCE = FUNCTION_CONTRIBUTION_TARGET__SOURCE;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPONENT__NAME = FUNCTION_CONTRIBUTION_TARGET__NAME;

	/**
     * The feature id for the '<em><b>Acronym</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPONENT__ACRONYM = FUNCTION_CONTRIBUTION_TARGET__ACRONYM;

	/**
     * The feature id for the '<em><b>Responsibility</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPONENT__RESPONSIBILITY = FUNCTION_CONTRIBUTION_TARGET__RESPONSIBILITY;

	/**
     * The feature id for the '<em><b>Department</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPONENT__DEPARTMENT = FUNCTION_CONTRIBUTION_TARGET__DEPARTMENT;

	/**
     * The feature id for the '<em><b>Contributions</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPONENT__CONTRIBUTIONS = FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS;

	/**
     * The number of structural features of the '<em>Component</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPONENT_FEATURE_COUNT = FUNCTION_CONTRIBUTION_TARGET_FEATURE_COUNT + 0;

	/**
     * The number of operations of the '<em>Component</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPONENT_OPERATION_COUNT = FUNCTION_CONTRIBUTION_TARGET_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.LogicalComponentImpl <em>Logical Component</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.LogicalComponentImpl
     * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl#getLogicalComponent()
     * @generated
     */
	int LOGICAL_COMPONENT = 4;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOGICAL_COMPONENT__SOURCE = FUNCTION_CONTRIBUTION_TARGET__SOURCE;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOGICAL_COMPONENT__NAME = FUNCTION_CONTRIBUTION_TARGET__NAME;

	/**
     * The feature id for the '<em><b>Acronym</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOGICAL_COMPONENT__ACRONYM = FUNCTION_CONTRIBUTION_TARGET__ACRONYM;

	/**
     * The feature id for the '<em><b>Responsibility</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOGICAL_COMPONENT__RESPONSIBILITY = FUNCTION_CONTRIBUTION_TARGET__RESPONSIBILITY;

	/**
     * The feature id for the '<em><b>Department</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOGICAL_COMPONENT__DEPARTMENT = FUNCTION_CONTRIBUTION_TARGET__DEPARTMENT;

	/**
     * The feature id for the '<em><b>Contributions</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOGICAL_COMPONENT__CONTRIBUTIONS = FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS;

	/**
     * The number of structural features of the '<em>Logical Component</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOGICAL_COMPONENT_FEATURE_COUNT = FUNCTION_CONTRIBUTION_TARGET_FEATURE_COUNT + 0;

	/**
     * The number of operations of the '<em>Logical Component</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int LOGICAL_COMPONENT_OPERATION_COUNT = FUNCTION_CONTRIBUTION_TARGET_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionalityImpl <em>Functionality</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionalityImpl
     * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl#getFunctionality()
     * @generated
     */
	int FUNCTIONALITY = 5;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONALITY__SOURCE = FUNCTION_CONTRIBUTION_TARGET__SOURCE;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONALITY__NAME = FUNCTION_CONTRIBUTION_TARGET__NAME;

	/**
     * The feature id for the '<em><b>Acronym</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONALITY__ACRONYM = FUNCTION_CONTRIBUTION_TARGET__ACRONYM;

	/**
     * The feature id for the '<em><b>Responsibility</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONALITY__RESPONSIBILITY = FUNCTION_CONTRIBUTION_TARGET__RESPONSIBILITY;

	/**
     * The feature id for the '<em><b>Department</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONALITY__DEPARTMENT = FUNCTION_CONTRIBUTION_TARGET__DEPARTMENT;

	/**
     * The feature id for the '<em><b>Contributions</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONALITY__CONTRIBUTIONS = FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS;

	/**
     * The feature id for the '<em><b>System</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONALITY__SYSTEM = FUNCTION_CONTRIBUTION_TARGET_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Functionality</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONALITY_FEATURE_COUNT = FUNCTION_CONTRIBUTION_TARGET_FEATURE_COUNT + 1;

	/**
     * The number of operations of the '<em>Functionality</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FUNCTIONALITY_OPERATION_COUNT = FUNCTION_CONTRIBUTION_TARGET_OPERATION_COUNT + 0;

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel <em>Model</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Model</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel
     * @generated
     */
	EClass getComponentsSystemsModel();

	/**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel#getFunctionContributionTargets <em>Function Contribution Targets</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Function Contribution Targets</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel#getFunctionContributionTargets()
     * @see #getComponentsSystemsModel()
     * @generated
     */
	EReference getComponentsSystemsModel_FunctionContributionTargets();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget <em>Function Contribution Target</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Function Contribution Target</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget
     * @generated
     */
	EClass getFunctionContributionTarget();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getName()
     * @see #getFunctionContributionTarget()
     * @generated
     */
	EAttribute getFunctionContributionTarget_Name();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getAcronym <em>Acronym</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Acronym</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getAcronym()
     * @see #getFunctionContributionTarget()
     * @generated
     */
	EAttribute getFunctionContributionTarget_Acronym();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getResponsibility <em>Responsibility</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Responsibility</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getResponsibility()
     * @see #getFunctionContributionTarget()
     * @generated
     */
	EAttribute getFunctionContributionTarget_Responsibility();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getDepartment <em>Department</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Department</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getDepartment()
     * @see #getFunctionContributionTarget()
     * @generated
     */
	EAttribute getFunctionContributionTarget_Department();

	/**
     * Returns the meta object for the reference list '{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getContributions <em>Contributions</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Contributions</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getContributions()
     * @see #getFunctionContributionTarget()
     * @generated
     */
	EReference getFunctionContributionTarget_Contributions();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.componentssystems.System <em>System</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>System</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.System
     * @generated
     */
	EClass getSystem();

	/**
     * Returns the meta object for the reference list '{@link de.jpwinkler.daf.fap5.model.componentssystems.System#getFunctionalities <em>Functionalities</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Functionalities</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.System#getFunctionalities()
     * @see #getSystem()
     * @generated
     */
	EReference getSystem_Functionalities();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.componentssystems.Component <em>Component</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Component</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.Component
     * @generated
     */
	EClass getComponent();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.componentssystems.LogicalComponent <em>Logical Component</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Logical Component</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.LogicalComponent
     * @generated
     */
	EClass getLogicalComponent();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.componentssystems.Functionality <em>Functionality</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Functionality</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.Functionality
     * @generated
     */
	EClass getFunctionality();

	/**
     * Returns the meta object for the reference '{@link de.jpwinkler.daf.fap5.model.componentssystems.Functionality#getSystem <em>System</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>System</em>'.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.Functionality#getSystem()
     * @see #getFunctionality()
     * @generated
     */
	EReference getFunctionality_System();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	ComponentsSystemsFactory getComponentsSystemsFactory();

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
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsModelImpl <em>Model</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsModelImpl
         * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl#getComponentsSystemsModel()
         * @generated
         */
		EClass COMPONENTS_SYSTEMS_MODEL = eINSTANCE.getComponentsSystemsModel();

		/**
         * The meta object literal for the '<em><b>Function Contribution Targets</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference COMPONENTS_SYSTEMS_MODEL__FUNCTION_CONTRIBUTION_TARGETS = eINSTANCE.getComponentsSystemsModel_FunctionContributionTargets();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionContributionTargetImpl <em>Function Contribution Target</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionContributionTargetImpl
         * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl#getFunctionContributionTarget()
         * @generated
         */
		EClass FUNCTION_CONTRIBUTION_TARGET = eINSTANCE.getFunctionContributionTarget();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FUNCTION_CONTRIBUTION_TARGET__NAME = eINSTANCE.getFunctionContributionTarget_Name();

		/**
         * The meta object literal for the '<em><b>Acronym</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FUNCTION_CONTRIBUTION_TARGET__ACRONYM = eINSTANCE.getFunctionContributionTarget_Acronym();

		/**
         * The meta object literal for the '<em><b>Responsibility</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FUNCTION_CONTRIBUTION_TARGET__RESPONSIBILITY = eINSTANCE.getFunctionContributionTarget_Responsibility();

		/**
         * The meta object literal for the '<em><b>Department</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FUNCTION_CONTRIBUTION_TARGET__DEPARTMENT = eINSTANCE.getFunctionContributionTarget_Department();

		/**
         * The meta object literal for the '<em><b>Contributions</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FUNCTION_CONTRIBUTION_TARGET__CONTRIBUTIONS = eINSTANCE.getFunctionContributionTarget_Contributions();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.SystemImpl <em>System</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.SystemImpl
         * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl#getSystem()
         * @generated
         */
		EClass SYSTEM = eINSTANCE.getSystem();

		/**
         * The meta object literal for the '<em><b>Functionalities</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SYSTEM__FUNCTIONALITIES = eINSTANCE.getSystem_Functionalities();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentImpl <em>Component</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentImpl
         * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl#getComponent()
         * @generated
         */
		EClass COMPONENT = eINSTANCE.getComponent();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.LogicalComponentImpl <em>Logical Component</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.LogicalComponentImpl
         * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl#getLogicalComponent()
         * @generated
         */
		EClass LOGICAL_COMPONENT = eINSTANCE.getLogicalComponent();

		/**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionalityImpl <em>Functionality</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.FunctionalityImpl
         * @see de.jpwinkler.daf.fap5.model.componentssystems.impl.ComponentsSystemsPackageImpl#getFunctionality()
         * @generated
         */
		EClass FUNCTIONALITY = eINSTANCE.getFunctionality();

		/**
         * The meta object literal for the '<em><b>System</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FUNCTIONALITY__SYSTEM = eINSTANCE.getFunctionality_System();

	}

} //ComponentsSystemsPackage
