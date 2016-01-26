/**
 */
package de.jpwinkler.daf.fap5.model.codebeamer;

import org.eclipse.emf.common.util.EList;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Code Beamer Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#isSpecified <em>Specified</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getSize <em>Size</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getIssues <em>Issues</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getVersionNumber <em>Version Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getMetrics <em>Metrics</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getPath <em>Path</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getView <em>View</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getCodeBeamerModel()
 * @model
 * @generated
 */
public interface CodeBeamerModel extends ModelObject {

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getCodeBeamerModel_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Specified</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Specified</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Specified</em>' attribute.
     * @see #setSpecified(boolean)
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getCodeBeamerModel_Specified()
     * @model
     * @generated
     */
    boolean isSpecified();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#isSpecified <em>Specified</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Specified</em>' attribute.
     * @see #isSpecified()
     * @generated
     */
    void setSpecified(boolean value);

    /**
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Estimated Remaining Work</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    long getEstimatedRemainingWork();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    EList<String> getIssueTypes();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    EList<Issue> getIssues(String issueType);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    Double getDoubleMetric(String name);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    Integer getIntMetric(String name);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    String getFullName();

    /**
     * Returns the value of the '<em><b>Size</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Size</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Size</em>' attribute.
     * @see #setSize(float)
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getCodeBeamerModel_Size()
     * @model default="0"
     * @generated
     */
    float getSize();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getSize <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Size</em>' attribute.
     * @see #getSize()
     * @generated
     */
    void setSize(float value);

    /**
     * Returns the value of the '<em><b>Issues</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.codebeamer.Issue}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Issues</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Issues</em>' containment reference list.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getCodeBeamerModel_Issues()
     * @model containment="true"
     * @generated
     */
    EList<Issue> getIssues();

    /**
     * Returns the value of the '<em><b>Version Number</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Version Number</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Version Number</em>' attribute.
     * @see #setVersionNumber(String)
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getCodeBeamerModel_VersionNumber()
     * @model
     * @generated
     */
    String getVersionNumber();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getVersionNumber <em>Version Number</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version Number</em>' attribute.
     * @see #getVersionNumber()
     * @generated
     */
    void setVersionNumber(String value);

    /**
     * Returns the value of the '<em><b>Metrics</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.codebeamer.Metric}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Metrics</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Metrics</em>' containment reference list.
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getCodeBeamerModel_Metrics()
     * @model containment="true"
     * @generated
     */
    EList<Metric> getMetrics();

    /**
     * Returns the value of the '<em><b>Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Path</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Path</em>' attribute.
     * @see #setPath(String)
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getCodeBeamerModel_Path()
     * @model
     * @generated
     */
    String getPath();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getPath <em>Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Path</em>' attribute.
     * @see #getPath()
     * @generated
     */
    void setPath(String value);

    /**
     * Returns the value of the '<em><b>View</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>View</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>View</em>' attribute.
     * @see #setView(String)
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getCodeBeamerModel_View()
     * @model
     * @generated
     */
    String getView();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel#getView <em>View</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>View</em>' attribute.
     * @see #getView()
     * @generated
     */
    void setView(String value);
} // CodeBeamerModel
