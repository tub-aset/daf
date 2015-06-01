/**
 */
package de.jpwinkler.daf.fap5.model.codebeamer.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerFactory;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage;
import de.jpwinkler.daf.fap5.model.codebeamer.DoubleMetric;
import de.jpwinkler.daf.fap5.model.codebeamer.IntMetric;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CodebeamerFactoryImpl extends EFactoryImpl implements CodebeamerFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static CodebeamerFactory init() {
        try {
            CodebeamerFactory theCodebeamerFactory = (CodebeamerFactory)EPackage.Registry.INSTANCE.getEFactory(CodebeamerPackage.eNS_URI);
            if (theCodebeamerFactory != null) {
                return theCodebeamerFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new CodebeamerFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CodebeamerFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case CodebeamerPackage.CODE_BEAMER_MODEL: return createCodeBeamerModel();
            case CodebeamerPackage.ISSUE: return createIssue();
            case CodebeamerPackage.INT_METRIC: return createIntMetric();
            case CodebeamerPackage.DOUBLE_METRIC: return createDoubleMetric();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CodeBeamerModel createCodeBeamerModel() {
        CodeBeamerModelImpl codeBeamerModel = new CodeBeamerModelImpl();
        return codeBeamerModel;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Issue createIssue() {
        IssueImpl issue = new IssueImpl();
        return issue;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntMetric createIntMetric() {
        IntMetricImpl intMetric = new IntMetricImpl();
        return intMetric;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoubleMetric createDoubleMetric() {
        DoubleMetricImpl doubleMetric = new DoubleMetricImpl();
        return doubleMetric;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CodebeamerPackage getCodebeamerPackage() {
        return (CodebeamerPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static CodebeamerPackage getPackage() {
        return CodebeamerPackage.eINSTANCE;
    }

} //CodebeamerFactoryImpl
