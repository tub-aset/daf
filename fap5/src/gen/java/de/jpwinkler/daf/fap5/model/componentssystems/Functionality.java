/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Functionality</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.Functionality#getSystem <em>System</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage#getFunctionality()
 * @model
 * @generated
 */
public interface Functionality extends FunctionContributionTarget {
	/**
     * Returns the value of the '<em><b>System</b></em>' reference.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.fap5.model.componentssystems.System#getFunctionalities <em>Functionalities</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>System</em>' reference.
     * @see #setSystem(de.jpwinkler.daf.fap5.model.componentssystems.System)
     * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage#getFunctionality_System()
     * @see de.jpwinkler.daf.fap5.model.componentssystems.System#getFunctionalities
     * @model opposite="functionalities"
     * @generated
     */
	de.jpwinkler.daf.fap5.model.componentssystems.System getSystem();

	/**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.componentssystems.Functionality#getSystem <em>System</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>System</em>' reference.
     * @see #getSystem()
     * @generated
     */
	void setSystem(de.jpwinkler.daf.fap5.model.componentssystems.System value);

} // Functionality
