/**
 */
package de.jpwinkler.daf.model;

import java.util.Date;
import java.util.Map;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Version</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.model.DoorsModuleVersion#getModule <em>Module</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsModuleVersion#getDate <em>Date</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsModuleVersion#getAttributes <em>Attributes</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsModuleVersion()
 * @model
 * @generated
 */
public interface DoorsModuleVersion {
	/**
	 * Returns the value of the '<em><b>Module</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.model.DoorsModule#getVersions <em>Versions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module</em>' container reference.
	 * @see #setModule(DoorsModule)
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsModuleVersion_Module()
	 * @see de.jpwinkler.daf.model.DoorsModule#getVersions
	 * @model opposite="versions" transient="false"
	 * @generated
	 */
	DoorsModule getModule();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsModuleVersion#getModule <em>Module</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Module</em>' container reference.
	 * @see #getModule()
	 * @generated
	 */
	void setModule(DoorsModule value);

	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(Date)
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsModuleVersion_Date()
	 * @model
	 * @generated
	 */
	Date getDate();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsModuleVersion#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(Date value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' map.
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsModuleVersion_Attributes()
	 * @model mapType="de.jpwinkler.daf.model.StringToStringMap&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	Map<String, String> getAttributes();

} // DoorsModuleVersion
