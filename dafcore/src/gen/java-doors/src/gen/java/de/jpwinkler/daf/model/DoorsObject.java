package de.jpwinkler.daf.model;

/*-
 * #%L
 * dafcore
 * %%
 * Copyright (C) 2019 TU Berlin ASET
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.model.DoorsObject#getObjectIdentifier <em>Object Identifier</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsObject#getObjectNumber <em>Object Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsObject#getAbsoluteNumber <em>Absolute Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsObject#getObjectText <em>Object Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsObject#getObjectShortText <em>Object Short Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsObject#getObjectHeading <em>Object Heading</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsObject#getText <em>Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsObject#getOutgoingLinks <em>Outgoing Links</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsObject()
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
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsObject_ObjectIdentifier()
	 * @model default="" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getObjectIdentifier();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsObject#getObjectIdentifier <em>Object Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object Identifier</em>' attribute.
	 * @see #getObjectIdentifier()
	 * @generated
	 */
	void setObjectIdentifier(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated NOT
	 */
        default int getObjectLevel() {
            return this.getParent() instanceof DoorsObject ? ((DoorsObject) this.getParent()).getObjectLevel() + 1 : 1;
        }

	/**
	 * Returns the value of the '<em><b>Object Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Number</em>' attribute.
	 * @see #setObjectNumber(String)
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsObject_ObjectNumber()
	 * @model unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getObjectNumber();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsObject#getObjectNumber <em>Object Number</em>}' attribute.
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
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsObject_AbsoluteNumber()
	 * @model unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	int getAbsoluteNumber();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsObject#getAbsoluteNumber <em>Absolute Number</em>}' attribute.
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
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsObject_ObjectText()
	 * @model unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getObjectText();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsObject#getObjectText <em>Object Text</em>}' attribute.
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
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsObject_ObjectShortText()
	 * @model unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getObjectShortText();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsObject#getObjectShortText <em>Object Short Text</em>}' attribute.
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
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsObject_ObjectHeading()
	 * @model unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getObjectHeading();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsObject#getObjectHeading <em>Object Heading</em>}' attribute.
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
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsObject_Text()
	 * @model default="" unique="false" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsObject#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

	/**
	 * Returns the value of the '<em><b>Outgoing Links</b></em>' reference list.
	 * The list contents are of type {@link de.jpwinkler.daf.model.DoorsLink}.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.model.DoorsLink#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Links</em>' reference list.
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsObject_OutgoingLinks()
	 * @see de.jpwinkler.daf.model.DoorsLink#getSource
	 * @model opposite="source"
	 * @generated
	 */
	List<DoorsLink> getOutgoingLinks();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isHeading();

} // DoorsObject
