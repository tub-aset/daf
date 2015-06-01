/**
 */
package de.jpwinkler.daf.dafcore.model.csv;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unresolved Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink#getTargetModule <em>Target Module</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink#getTargetObject <em>Target Object</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getUnresolvedLink()
 * @model
 * @generated
 */
public interface UnresolvedLink extends Link {
    /**
     * Returns the value of the '<em><b>Target Module</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Module</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Module</em>' attribute.
     * @see #setTargetModule(String)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getUnresolvedLink_TargetModule()
     * @model
     * @generated
     */
    String getTargetModule();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink#getTargetModule <em>Target Module</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Module</em>' attribute.
     * @see #getTargetModule()
     * @generated
     */
    void setTargetModule(String value);

    /**
     * Returns the value of the '<em><b>Target Object</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Object</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Object</em>' attribute.
     * @see #setTargetObject(String)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getUnresolvedLink_TargetObject()
     * @model
     * @generated
     */
    String getTargetObject();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink#getTargetObject <em>Target Object</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Object</em>' attribute.
     * @see #getTargetObject()
     * @generated
     */
    void setTargetObject(String value);

} // UnresolvedLink
