/**
 */
package de.jpwinkler.daf.dafcore.model.csv;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import de.jpwinkler.daf.dafcore.csv.DoorsModuleVisitor;
import de.jpwinkler.daf.dafcore.model.common.ModelObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getObjects <em>Objects</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getPath <em>Path</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getUrl <em>Url</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsModule()
 * @model
 * @generated
 */
public interface DoorsModule extends ModelObject {
    /**
     * Returns the value of the '<em><b>Objects</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Objects</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Objects</em>' containment reference list.
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsModule_Objects()
     * @model containment="true"
     * @generated
     */
    EList<DoorsObject> getObjects();

    /**
     * Returns the value of the '<em><b>Attributes</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attributes</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attributes</em>' map.
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsModule_Attributes()
     * @model mapType="com.daimler.jonwink.srstp.core.model.csv.StringToStringMap<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" ordered="false"
     * @generated
     */
    EMap<String, String> getAttributes();

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
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsModule_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

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
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsModule_Path()
     * @model
     * @generated
     */
    String getPath();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getPath <em>Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Path</em>' attribute.
     * @see #getPath()
     * @generated
     */
    void setPath(String value);

    /**
     * Returns the value of the '<em><b>Url</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Url</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Url</em>' attribute.
     * @see #setUrl(String)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsModule_Url()
     * @model
     * @generated
     */
    String getUrl();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsModule#getUrl <em>Url</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Url</em>' attribute.
     * @see #getUrl()
     * @generated
     */
    void setUrl(String value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model visitorDataType="com.daimler.jonwink.srstp.core.model.csv.DoorsModuleVisitor"
     *        annotation="http://www.eclipse.org/emf/2002/GenModel body='for (DoorsObject object : getObjects()) {\r\n\tobject.accept(visitor);\r\n}'"
     * @generated
     */
    void accept(DoorsModuleVisitor visitor);

} // DoorsModule
