/**
 */
package de.jpwinkler.daf.model;

import java.util.Collection;
import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.model.DoorsModule#getView <em>View</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsModule#getVersions <em>Versions</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsModule()
 * @model
 * @generated
 */
public interface DoorsModule extends DoorsTreeNode {
	/**
	 * Returns the value of the '<em><b>View</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View</em>' attribute.
	 * @see #setView(String)
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsModule_View()
	 * @model
	 * @generated
	 */
	String getView();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsModule#getView <em>View</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View</em>' attribute.
	 * @see #getView()
	 * @generated
	 */
	void setView(String value);

	/**
	 * Returns the value of the '<em><b>Versions</b></em>' containment reference list.
	 * The list contents are of type {@link de.jpwinkler.daf.model.DoorsModuleVersion}.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.model.DoorsModuleVersion#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Versions</em>' containment reference list.
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsModule_Versions()
	 * @see de.jpwinkler.daf.model.DoorsModuleVersion#getModule
	 * @model opposite="module" containment="true"
	 * @generated
	 */
	List<DoorsModuleVersion> getVersions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	DoorsObject findObject(String objectIdentifier);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	List<String> getObjectAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model attrsDataType="de.jpwinkler.daf.model.Collection"
	 * @generated
	 */
	void setObjectAttributes(Collection attrs);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	DoorsModuleVersion getLatestVersion();

} // DoorsModule
