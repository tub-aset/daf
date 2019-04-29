/**
 */
package de.jpwinkler.daf.doorscsv.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsModule#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsModule#getPath <em>Path</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsModule#getUrl <em>Url</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsModule#getAttributeDefinitions <em>Attribute Definitions</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsModule#getView <em>View</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsModule()
 * @model
 * @generated
 */
public interface DoorsModule extends DoorsTreeNode {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsModule_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsModule#getName <em>Name</em>}' attribute.
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
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsModule_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsModule#getPath <em>Path</em>}' attribute.
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
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsModule_Url()
	 * @model
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsModule#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>Attribute Definitions</b></em>' containment reference list.
	 * The list contents are of type {@link de.jpwinkler.daf.doorscsv.model.AttributeDefinition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Definitions</em>' containment reference list.
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsModule_AttributeDefinitions()
	 * @model containment="true"
	 * @generated
	 */
	EList<AttributeDefinition> getAttributeDefinitions();

	/**
	 * Returns the value of the '<em><b>View</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View</em>' attribute.
	 * @see #setView(String)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsModule_View()
	 * @model
	 * @generated
	 */
	String getView();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsModule#getView <em>View</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View</em>' attribute.
	 * @see #getView()
	 * @generated
	 */
	void setView(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	AttributeDefinition findAttributeDefinition(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	DoorsObject findObject(String objectIdentifier);

} // DoorsModule
