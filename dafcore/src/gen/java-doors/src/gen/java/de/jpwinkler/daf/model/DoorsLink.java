/**
 */
package de.jpwinkler.daf.model;

import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.apache.commons.lang3.tuple.Pair;

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
	 * Returns the value of the '<em><b>Source</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.model.DoorsObject#getOutgoingLinks <em>Outgoing Links</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' container reference.
	 * @see #setSource(DoorsObject)
	 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsLink_Source()
	 * @see de.jpwinkler.daf.model.DoorsObject#getOutgoingLinks
	 * @model opposite="outgoingLinks" transient="false"
	 * @generated
	 */
    DoorsObject getSource();

    /**
	 * Sets the value of the '{@link de.jpwinkler.daf.model.DoorsLink#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
    void setSource(DoorsObject value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @model kind="operation"
     * dataType="de.jpwinkler.daf.model.DoorsLinkStatus"
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
     * <!-- end-user-doc --> @model
     * exceptions="de.jpwinkler.daf.model.DoorsLinkResolveException"
     * @generated
     */
    DoorsObject resolve() throws DoorsLinkResolveException;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated NOT
     */
    default CompletableFuture<DoorsObject> resolveAsync(BackgroundTaskExecutor exec) {
        return exec.runBackgroundTask("Resolve link", i -> this.resolve());
    }

    /**
     * Write out this DoorsLink in the canonical string format. This format is
     * "/my_project/folder_xy/first_module:23".
     *
     * @generated NOT
     * @return
     */
    default String formatLink() {
        return this.getTargetModule() + ":" + this.getTargetObject();
    }

    /**
     * Parse a link in the canonical string format into a tuple (module path,
     * target object absolute number). This format is
     * "/my_project/folder_xy/first_module:23".
     *
     * @param link link in the canonical string format
     * @return an optional whose value will be missing if the string contains no target module
     */
    public static Optional<Pair<String, String>> parseLink(String link) {
        int colonIndex = link.lastIndexOf(":");
        String targetModule;
        String targetObject;

        if (colonIndex == -1) {
            targetModule = link;
            targetObject = "1";
        } else {
            targetModule = link.substring(0, colonIndex);
            targetObject = link.substring(colonIndex + 1);
        }

        return targetModule.isEmpty() ? Optional.empty() : Optional.of(Pair.of(targetModule.trim(), targetObject.trim()));
    }

} // DoorsLink
