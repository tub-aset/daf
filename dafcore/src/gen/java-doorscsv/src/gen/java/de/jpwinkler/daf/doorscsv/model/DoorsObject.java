/**
 */
package de.jpwinkler.daf.doorscsv.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getObjectIdentifier <em>Object Identifier</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getObjectLevel <em>Object Level</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getObjectNumber <em>Object Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getAbsoluteNumber <em>Absolute Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getObjectText <em>Object Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getObjectShortText <em>Object Short Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getObjectHeading <em>Object Heading</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getText <em>Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getOutgoingLinks <em>Outgoing Links</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getIncomingLinks <em>Incoming Links</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getModule <em>Module</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsObject()
 * @model
 * @generated
 */
public interface DoorsObject extends DoorsTreeNode {
	/**
	 * Returns the value of the '<em><b>Object Identifier</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Identifier</em>' attribute.
	 * @see #setObjectIdentifier(String)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsObject_ObjectIdentifier()
	 * @model default="" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getObjectIdentifier();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getObjectIdentifier <em>Object Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object Identifier</em>' attribute.
	 * @see #getObjectIdentifier()
	 * @generated
	 */
	void setObjectIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Object Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Level</em>' attribute.
	 * @see #setObjectLevel(int)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsObject_ObjectLevel()
	 * @model unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	int getObjectLevel();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getObjectLevel <em>Object Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object Level</em>' attribute.
	 * @see #getObjectLevel()
	 * @generated
	 */
	void setObjectLevel(int value);

	/**
	 * Returns the value of the '<em><b>Object Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Number</em>' attribute.
	 * @see #setObjectNumber(String)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsObject_ObjectNumber()
	 * @model unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getObjectNumber();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getObjectNumber <em>Object Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object Number</em>' attribute.
	 * @see #getObjectNumber()
	 * @generated
	 */
	void setObjectNumber(String value);

	/**
	 * Returns the value of the '<em><b>Absolute Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Absolute Number</em>' attribute.
	 * @see #setAbsoluteNumber(int)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsObject_AbsoluteNumber()
	 * @model unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	int getAbsoluteNumber();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getAbsoluteNumber <em>Absolute Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Absolute Number</em>' attribute.
	 * @see #getAbsoluteNumber()
	 * @generated
	 */
	void setAbsoluteNumber(int value);

	/**
	 * Returns the value of the '<em><b>Object Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Text</em>' attribute.
	 * @see #setObjectText(String)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsObject_ObjectText()
	 * @model unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getObjectText();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getObjectText <em>Object Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object Text</em>' attribute.
	 * @see #getObjectText()
	 * @generated
	 */
	void setObjectText(String value);

	/**
	 * Returns the value of the '<em><b>Object Short Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Short Text</em>' attribute.
	 * @see #setObjectShortText(String)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsObject_ObjectShortText()
	 * @model unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getObjectShortText();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getObjectShortText <em>Object Short Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object Short Text</em>' attribute.
	 * @see #getObjectShortText()
	 * @generated
	 */
	void setObjectShortText(String value);

	/**
	 * Returns the value of the '<em><b>Object Heading</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Heading</em>' attribute.
	 * @see #setObjectHeading(String)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsObject_ObjectHeading()
	 * @model unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getObjectHeading();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getObjectHeading <em>Object Heading</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object Heading</em>' attribute.
	 * @see #getObjectHeading()
	 * @generated
	 */
	void setObjectHeading(String value);

	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsObject_Text()
	 * @model default="" unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

	/**
	 * Returns the value of the '<em><b>Outgoing Links</b></em>' containment reference list.
	 * The list contents are of type {@link de.jpwinkler.daf.doorscsv.model.Link}.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.doorscsv.model.Link#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Links</em>' containment reference list.
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsObject_OutgoingLinks()
	 * @see de.jpwinkler.daf.doorscsv.model.Link#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<Link> getOutgoingLinks();

	/**
	 * Returns the value of the '<em><b>Incoming Links</b></em>' reference list.
	 * The list contents are of type {@link de.jpwinkler.daf.doorscsv.model.ResolvedLink}.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.doorscsv.model.ResolvedLink#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Links</em>' reference list.
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsObject_IncomingLinks()
	 * @see de.jpwinkler.daf.doorscsv.model.ResolvedLink#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<ResolvedLink> getIncomingLinks();

	/**
	 * Returns the value of the '<em><b>Module</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module</em>' reference.
	 * @see #setModule(DoorsModule)
	 * @see de.jpwinkler.daf.doorscsv.model.DoorsCSVPackage#getDoorsObject_Module()
	 * @model
	 * @generated
	 */
	DoorsModule getModule();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.doorscsv.model.DoorsObject#getModule <em>Module</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Module</em>' reference.
	 * @see #getModule()
	 * @generated
	 */
	void setModule(DoorsModule value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isHeading();

} // DoorsObject
