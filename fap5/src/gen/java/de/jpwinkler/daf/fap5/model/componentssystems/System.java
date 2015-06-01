/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.System#getFunctionalities <em>Functionalities</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage#getSystem()
 * @model
 * @generated
 */
public interface System extends FunctionContributionTarget {

	/**
     * Returns the value of the '<em><b>Functionalities</b></em>' reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.componentssystems.Functionality}.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.fap5.model.componentssystems.Functionality#getSystem <em>System</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Functionalities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Functionalities</em>' reference list.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage#getSystem_Functionalities()
     * @see de.jpwinkler.daf.fap5.model.componentssystems.Functionality#getSystem
     * @model opposite="system"
     * @generated
     */
	EList<Functionality> getFunctionalities();
} // System
