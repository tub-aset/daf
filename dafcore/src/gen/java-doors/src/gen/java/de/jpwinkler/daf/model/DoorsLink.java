/**
 */
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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.model.DoorsLink#getTargetModule <em>Target Module</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsLink#getTargetObject <em>Target Object</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.DoorsLink#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsLink()
 * @model
 * @generated
 */
public interface DoorsLink {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.model.DoorsObject#getOutgoingLinks <em>Outgoing Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(DoorsObject)
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsLink_Source()
	 * @see de.jpwinkler.daf.model.DoorsObject#getOutgoingLinks
	 * @model opposite="outgoingLinks"
	 * @generated
	 */
	DoorsObject getSource();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsLink#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(DoorsObject value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="de.jpwinkler.daf.model.DoorsLinkStatus"
	 * @generated
	 */
	DoorsLinkStatus getLinkStatus();

	/**
	 * Returns the value of the '<em><b>Target Module</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Module</em>' attribute.
	 * @see #setTargetModule(String)
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsLink_TargetModule()
	 * @model
	 * @generated
	 */
	String getTargetModule();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsLink#getTargetModule <em>Target Module</em>}' attribute.
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
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Object</em>' attribute.
	 * @see #setTargetObject(String)
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsLink_TargetObject()
	 * @model
	 * @generated
	 */
	String getTargetObject();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsLink#getTargetObject <em>Target Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Object</em>' attribute.
	 * @see #getTargetObject()
	 * @generated
	 */
	void setTargetObject(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	DoorsObject resolve();

} // DoorsLink
