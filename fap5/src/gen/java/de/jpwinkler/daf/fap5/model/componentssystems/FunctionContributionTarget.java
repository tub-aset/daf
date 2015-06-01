/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems;

import org.eclipse.emf.common.util.EList;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.fap5.model.srs.FunctionContribution;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Contribution Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getAcronym <em>Acronym</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getResponsibility <em>Responsibility</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getDepartment <em>Department</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getContributions <em>Contributions</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage#getFunctionContributionTarget()
 * @model
 * @generated
 */
public interface FunctionContributionTarget extends ModelObject {
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
     * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage#getFunctionContributionTarget_Name()
     * @model
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Acronym</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Acronym</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Acronym</em>' attribute.
     * @see #setAcronym(String)
     * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage#getFunctionContributionTarget_Acronym()
     * @model
     * @generated
     */
	String getAcronym();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getAcronym <em>Acronym</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Acronym</em>' attribute.
     * @see #getAcronym()
     * @generated
     */
	void setAcronym(String value);

	/**
     * Returns the value of the '<em><b>Responsibility</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Responsibility</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Responsibility</em>' attribute.
     * @see #setResponsibility(String)
     * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage#getFunctionContributionTarget_Responsibility()
     * @model
     * @generated
     */
	String getResponsibility();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getResponsibility <em>Responsibility</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Responsibility</em>' attribute.
     * @see #getResponsibility()
     * @generated
     */
	void setResponsibility(String value);

	/**
     * Returns the value of the '<em><b>Department</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Department</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Department</em>' attribute.
     * @see #setDepartment(String)
     * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage#getFunctionContributionTarget_Department()
     * @model
     * @generated
     */
	String getDepartment();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget#getDepartment <em>Department</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Department</em>' attribute.
     * @see #getDepartment()
     * @generated
     */
	void setDepartment(String value);

	/**
     * Returns the value of the '<em><b>Contributions</b></em>' reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.srs.FunctionContribution}.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.fap5.model.srs.FunctionContribution#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contributions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Contributions</em>' reference list.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage#getFunctionContributionTarget_Contributions()
     * @see de.jpwinkler.daf.fap5.model.srs.FunctionContribution#getTarget
     * @model opposite="target"
     * @generated
     */
	EList<FunctionContribution> getContributions();

} // FunctionContributionTarget
