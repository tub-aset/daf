/**
 */
package de.jpwinkler.daf.doorsdb.doorsdbmodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule#getVersions <em>Versions</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule#getUrl <em>Url</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule#getTags <em>Tags</em>}</li>
 * </ul>
 *
 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage#getDBModule()
 * @model
 * @generated
 */
public interface DBModule extends DBItem {
    /**
     * Returns the value of the '<em><b>Versions</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion}.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion#getModule <em>Module</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Versions</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Versions</em>' containment reference list.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage#getDBModule_Versions()
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBVersion#getModule
     * @model opposite="module" containment="true"
     * @generated
     */
    EList<DBVersion> getVersions();

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
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage#getDBModule_Url()
     * @model
     * @generated
     */
    String getUrl();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBModule#getUrl <em>Url</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Url</em>' attribute.
     * @see #getUrl()
     * @generated
     */
    void setUrl(String value);

    /**
     * Returns the value of the '<em><b>Tags</b></em>' reference list.
     * The list contents are of type {@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag}.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag#getModules <em>Modules</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tags</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tags</em>' reference list.
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage#getDBModule_Tags()
     * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DBTag#getModules
     * @model opposite="modules"
     * @generated
     */
    EList<DBTag> getTags();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    DBVersion getLatestVersion();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    boolean hasTag(String tag);

} // DBModule
