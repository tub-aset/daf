/**
 */
package de.jpwinkler.daf.fap5.model.componentssystems;

import org.eclipse.emf.common.util.EList;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsModel#getFunctionContributionTargets <em>Function Contribution Targets</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage#getComponentsSystemsModel()
 * @model
 * @generated
 */
public interface ComponentsSystemsModel extends ModelObject {
	/**
     * Returns the value of the '<em><b>Function Contribution Targets</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.fap5.model.componentssystems.FunctionContributionTarget}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Contribution Targets</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Function Contribution Targets</em>' containment reference list.
     * @see de.jpwinkler.daf.fap5.model.componentssystems.ComponentsSystemsPackage#getComponentsSystemsModel_FunctionContributionTargets()
     * @model containment="true"
     * @generated
     */
	EList<FunctionContributionTarget> getFunctionContributionTargets();

} // ComponentsSystemsModel
