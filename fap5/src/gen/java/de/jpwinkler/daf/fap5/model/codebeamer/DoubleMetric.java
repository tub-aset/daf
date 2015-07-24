/**
 */
package de.jpwinkler.daf.fap5.model.codebeamer;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Double Metric</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.DoubleMetric#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getDoubleMetric()
 * @model
 * @generated
 */
public interface DoubleMetric extends Metric {
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(double)
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getDoubleMetric_Value()
     * @model
     * @generated
     */
    double getValue();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.codebeamer.DoubleMetric#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(double value);

} // DoubleMetric
