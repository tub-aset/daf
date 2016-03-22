/**
 */
package de.jpwinkler.daf.doorsdb.doorsdbmodel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB Folder</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see de.jpwinkler.daf.doorsdb.doorsdbmodel.DoorsDBModelPackage#getDBFolder()
 * @model
 * @generated
 */
public interface DBFolder extends DBItem {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    DBFolder getFolder(String name);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    DBModule getModule(String name);

} // DBFolder
