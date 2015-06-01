/**
 */
package de.jpwinkler.daf.fap5.model.codebeamer;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Int Metric</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.IntMetric#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getIntMetric()
 * @model
 * @generated
 */
public interface IntMetric extends Metric {
    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(int)
     * @see de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage#getIntMetric_Value()
     * @model
     * @generated
     */
    int getValue();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.fap5.model.codebeamer.IntMetric#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(int value);

} // IntMetric
