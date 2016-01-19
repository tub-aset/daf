/**
 */
package de.jpwinkler.daf.fap5.model.codebeamer;

import org.eclipse.emf.ecore.EAttribute;
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
 * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerFactory
 * @model kind="package"
 * @generated
 */
public interface CodebeamerPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "codebeamer";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "de.jpwinkler.daf.fap5.model.codebeamer";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "codebeamer";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	CodebeamerPackage eINSTANCE = de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl.init();

	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.CodeBeamerModelImpl <em>Code Beamer Model</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.CodeBeamerModelImpl
     * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl#getCodeBeamerModel()
     * @generated
     */
	int CODE_BEAMER_MODEL = 0;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CODE_BEAMER_MODEL__SOURCE = CommonPackage.MODEL_OBJECT__SOURCE;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CODE_BEAMER_MODEL__NAME = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Specified</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CODE_BEAMER_MODEL__SPECIFIED = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Size</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CODE_BEAMER_MODEL__SIZE = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Issues</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CODE_BEAMER_MODEL__ISSUES = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Version Number</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CODE_BEAMER_MODEL__VERSION_NUMBER = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Metrics</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CODE_BEAMER_MODEL__METRICS = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Module</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CODE_BEAMER_MODEL__MODULE = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CODE_BEAMER_MODEL__PATH = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Code Beamer Model</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CODE_BEAMER_MODEL_FEATURE_COUNT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 8;

	/**
     * The operation id for the '<em>Get Estimated Remaining Work</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CODE_BEAMER_MODEL___GET_ESTIMATED_REMAINING_WORK = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 0;

	/**
     * The operation id for the '<em>Get Issue Types</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CODE_BEAMER_MODEL___GET_ISSUE_TYPES = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 1;

	/**
     * The operation id for the '<em>Get Issues</em>' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CODE_BEAMER_MODEL___GET_ISSUES__STRING = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 2;

	/**
     * The operation id for the '<em>Get Double Metric</em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CODE_BEAMER_MODEL___GET_DOUBLE_METRIC__STRING = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 3;

    /**
     * The operation id for the '<em>Get Int Metric</em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CODE_BEAMER_MODEL___GET_INT_METRIC__STRING = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 4;

    /**
     * The operation id for the '<em>Get Full Name</em>' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CODE_BEAMER_MODEL___GET_FULL_NAME = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 5;

    /**
     * The number of operations of the '<em>Code Beamer Model</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CODE_BEAMER_MODEL_OPERATION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 6;


	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.IssueImpl <em>Issue</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.IssueImpl
     * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl#getIssue()
     * @generated
     */
	int ISSUE = 1;

	/**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ISSUE__SOURCE = CommonPackage.MODEL_OBJECT__SOURCE;

	/**
     * The feature id for the '<em><b>Severity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ISSUE__SEVERITY = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ISSUE__DESCRIPTION = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Issue Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ISSUE__ISSUE_TYPE = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Issue</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ISSUE_FEATURE_COUNT = CommonPackage.MODEL_OBJECT_FEATURE_COUNT + 3;

	/**
     * The number of operations of the '<em>Issue</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ISSUE_OPERATION_COUNT = CommonPackage.MODEL_OBJECT_OPERATION_COUNT + 0;


	/**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.MetricImpl <em>Metric</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.MetricImpl
     * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl#getMetric()
     * @generated
     */
    int METRIC = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METRIC__NAME = 0;

    /**
     * The number of structural features of the '<em>Metric</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METRIC_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Metric</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int METRIC_OPERATION_COUNT = 0;


    /**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.IntMetricImpl <em>Int Metric</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.IntMetricImpl
     * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl#getIntMetric()
     * @generated
     */
    int INT_METRIC = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_METRIC__NAME = METRIC__NAME;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_METRIC__VALUE = METRIC_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Int Metric</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_METRIC_FEATURE_COUNT = METRIC_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Int Metric</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_METRIC_OPERATION_COUNT = METRIC_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.DoubleMetricImpl <em>Double Metric</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.DoubleMetricImpl
     * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl#getDoubleMetric()
     * @generated
     */
    int DOUBLE_METRIC = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_METRIC__NAME = METRIC__NAME;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_METRIC__VALUE = METRIC_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Double Metric</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_METRIC_FEATURE_COUNT = METRIC_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Double Metric</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOUBLE_METRIC_OPERATION_COUNT = METRIC_OPERATION_COUNT + 0;


    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel <em>Code Beamer Model</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Code Beamer Model</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel
     * @generated
     */
	EClass getCodeBeamerModel();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getName()
     * @see #getCodeBeamerModel()
     * @generated
     */
	EAttribute getCodeBeamerModel_Name();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#isSpecified <em>Specified</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Specified</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#isSpecified()
     * @see #getCodeBeamerModel()
     * @generated
     */
	EAttribute getCodeBeamerModel_Specified();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getSize <em>Size</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getSize()
     * @see #getCodeBeamerModel()
     * @generated
     */
	EAttribute getCodeBeamerModel_Size();

	/**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getIssues <em>Issues</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Issues</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getIssues()
     * @see #getCodeBeamerModel()
     * @generated
     */
	EReference getCodeBeamerModel_Issues();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getVersionNumber <em>Version Number</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version Number</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getVersionNumber()
     * @see #getCodeBeamerModel()
     * @generated
     */
	EAttribute getCodeBeamerModel_VersionNumber();

	/**
     * Returns the meta object for the containment reference list '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getMetrics <em>Metrics</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Metrics</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getMetrics()
     * @see #getCodeBeamerModel()
     * @generated
     */
    EReference getCodeBeamerModel_Metrics();

    /**
     * Returns the meta object for the reference '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getModule <em>Module</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Module</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getModule()
     * @see #getCodeBeamerModel()
     * @generated
     */
    EReference getCodeBeamerModel_Module();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getPath <em>Path</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Path</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getPath()
     * @see #getCodeBeamerModel()
     * @generated
     */
    EAttribute getCodeBeamerModel_Path();

    /**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getEstimatedRemainingWork() <em>Get Estimated Remaining Work</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Estimated Remaining Work</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getEstimatedRemainingWork()
     * @generated
     */
	EOperation getCodeBeamerModel__GetEstimatedRemainingWork();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getIssueTypes() <em>Get Issue Types</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Issue Types</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getIssueTypes()
     * @generated
     */
	EOperation getCodeBeamerModel__GetIssueTypes();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getIssues(java.lang.String) <em>Get Issues</em>}' operation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Issues</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getIssues(java.lang.String)
     * @generated
     */
	EOperation getCodeBeamerModel__GetIssues__String();

	/**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getDoubleMetric(java.lang.String) <em>Get Double Metric</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Double Metric</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getDoubleMetric(java.lang.String)
     * @generated
     */
    EOperation getCodeBeamerModel__GetDoubleMetric__String();

    /**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getIntMetric(java.lang.String) <em>Get Int Metric</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Int Metric</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getIntMetric(java.lang.String)
     * @generated
     */
    EOperation getCodeBeamerModel__GetIntMetric__String();

    /**
     * Returns the meta object for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getFullName() <em>Get Full Name</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the '<em>Get Full Name</em>' operation.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getFullName()
     * @generated
     */
    EOperation getCodeBeamerModel__GetFullName();

    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.codebeamer.Issue <em>Issue</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Issue</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.Issue
     * @generated
     */
	EClass getIssue();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.codebeamer.Issue#getSeverity <em>Severity</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Severity</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.Issue#getSeverity()
     * @see #getIssue()
     * @generated
     */
	EAttribute getIssue_Severity();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.codebeamer.Issue#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.Issue#getDescription()
     * @see #getIssue()
     * @generated
     */
	EAttribute getIssue_Description();

	/**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.codebeamer.Issue#getIssueType <em>Issue Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Issue Type</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.Issue#getIssueType()
     * @see #getIssue()
     * @generated
     */
	EAttribute getIssue_IssueType();

	/**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.codebeamer.Metric <em>Metric</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Metric</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.Metric
     * @generated
     */
    EClass getMetric();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.codebeamer.Metric#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.Metric#getName()
     * @see #getMetric()
     * @generated
     */
    EAttribute getMetric_Name();

    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.codebeamer.IntMetric <em>Int Metric</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Int Metric</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.IntMetric
     * @generated
     */
    EClass getIntMetric();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.codebeamer.IntMetric#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.IntMetric#getValue()
     * @see #getIntMetric()
     * @generated
     */
    EAttribute getIntMetric_Value();

    /**
     * Returns the meta object for class '{@link de.jpwinkler.daf.fap5.model.codebeamer.DoubleMetric <em>Double Metric</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Double Metric</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.DoubleMetric
     * @generated
     */
    EClass getDoubleMetric();

    /**
     * Returns the meta object for the attribute '{@link de.jpwinkler.daf.fap5.model.codebeamer.DoubleMetric#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.DoubleMetric#getValue()
     * @see #getDoubleMetric()
     * @generated
     */
    EAttribute getDoubleMetric_Value();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	CodebeamerFactory getCodebeamerFactory();

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
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.CodeBeamerModelImpl <em>Code Beamer Model</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.CodeBeamerModelImpl
         * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl#getCodeBeamerModel()
         * @generated
         */
		EClass CODE_BEAMER_MODEL = eINSTANCE.getCodeBeamerModel();
		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CODE_BEAMER_MODEL__NAME = eINSTANCE.getCodeBeamerModel_Name();
		/**
         * The meta object literal for the '<em><b>Specified</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CODE_BEAMER_MODEL__SPECIFIED = eINSTANCE.getCodeBeamerModel_Specified();
		/**
         * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CODE_BEAMER_MODEL__SIZE = eINSTANCE.getCodeBeamerModel_Size();
		/**
         * The meta object literal for the '<em><b>Issues</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CODE_BEAMER_MODEL__ISSUES = eINSTANCE.getCodeBeamerModel_Issues();
		/**
         * The meta object literal for the '<em><b>Version Number</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CODE_BEAMER_MODEL__VERSION_NUMBER = eINSTANCE.getCodeBeamerModel_VersionNumber();
		/**
         * The meta object literal for the '<em><b>Metrics</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CODE_BEAMER_MODEL__METRICS = eINSTANCE.getCodeBeamerModel_Metrics();
        /**
         * The meta object literal for the '<em><b>Module</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CODE_BEAMER_MODEL__MODULE = eINSTANCE.getCodeBeamerModel_Module();
        /**
         * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute CODE_BEAMER_MODEL__PATH = eINSTANCE.getCodeBeamerModel_Path();
        /**
         * The meta object literal for the '<em><b>Get Estimated Remaining Work</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation CODE_BEAMER_MODEL___GET_ESTIMATED_REMAINING_WORK = eINSTANCE.getCodeBeamerModel__GetEstimatedRemainingWork();
		/**
         * The meta object literal for the '<em><b>Get Issue Types</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation CODE_BEAMER_MODEL___GET_ISSUE_TYPES = eINSTANCE.getCodeBeamerModel__GetIssueTypes();
		/**
         * The meta object literal for the '<em><b>Get Issues</b></em>' operation.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EOperation CODE_BEAMER_MODEL___GET_ISSUES__STRING = eINSTANCE.getCodeBeamerModel__GetIssues__String();
		/**
         * The meta object literal for the '<em><b>Get Double Metric</b></em>' operation.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EOperation CODE_BEAMER_MODEL___GET_DOUBLE_METRIC__STRING = eINSTANCE.getCodeBeamerModel__GetDoubleMetric__String();
        /**
         * The meta object literal for the '<em><b>Get Int Metric</b></em>' operation.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EOperation CODE_BEAMER_MODEL___GET_INT_METRIC__STRING = eINSTANCE.getCodeBeamerModel__GetIntMetric__String();
        /**
         * The meta object literal for the '<em><b>Get Full Name</b></em>' operation.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EOperation CODE_BEAMER_MODEL___GET_FULL_NAME = eINSTANCE.getCodeBeamerModel__GetFullName();
        /**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.IssueImpl <em>Issue</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.IssueImpl
         * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl#getIssue()
         * @generated
         */
		EClass ISSUE = eINSTANCE.getIssue();
		/**
         * The meta object literal for the '<em><b>Severity</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ISSUE__SEVERITY = eINSTANCE.getIssue_Severity();
		/**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ISSUE__DESCRIPTION = eINSTANCE.getIssue_Description();
		/**
         * The meta object literal for the '<em><b>Issue Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ISSUE__ISSUE_TYPE = eINSTANCE.getIssue_IssueType();
        /**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.MetricImpl <em>Metric</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.MetricImpl
         * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl#getMetric()
         * @generated
         */
        EClass METRIC = eINSTANCE.getMetric();
        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute METRIC__NAME = eINSTANCE.getMetric_Name();
        /**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.IntMetricImpl <em>Int Metric</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.IntMetricImpl
         * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl#getIntMetric()
         * @generated
         */
        EClass INT_METRIC = eINSTANCE.getIntMetric();
        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INT_METRIC__VALUE = eINSTANCE.getIntMetric_Value();
        /**
         * The meta object literal for the '{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.DoubleMetricImpl <em>Double Metric</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.DoubleMetricImpl
         * @see de.jpwinkler.daf.fap5.model.codebeamer.impl.CodebeamerPackageImpl#getDoubleMetric()
         * @generated
         */
        EClass DOUBLE_METRIC = eINSTANCE.getDoubleMetric();
        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOUBLE_METRIC__VALUE = eINSTANCE.getDoubleMetric_Value();

	}

} //CodebeamerPackage
