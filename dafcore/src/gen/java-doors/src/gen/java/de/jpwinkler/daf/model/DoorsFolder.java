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
 * A representation of the model object '<em><b>Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.model.DoorsFolder#isProject <em>Project</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsFolder()
 * @model
 * @generated
 */
public interface DoorsFolder extends DoorsTreeNode {

	/**
	 * Returns the value of the '<em><b>Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project</em>' attribute.
	 * @see #setProject(boolean)
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsFolder_Project()
	 * @model unique="false"
	 * @generated
	 */
	boolean isProject();

	/**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsFolder#isProject <em>Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project</em>' attribute.
	 * @see #isProject()
	 * @generated
	 */
	void setProject(boolean value);
} // DoorsFolder
