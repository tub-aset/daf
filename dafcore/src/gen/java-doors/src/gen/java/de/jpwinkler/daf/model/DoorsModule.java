/**
 */
package de.jpwinkler.daf.model;

import de.jpwinkler.daf.db.BackgroundTaskExecutor;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors Module</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see de.jpwinkler.daf.model.DoorsPackage#getDoorsModule()
 * @model
 * @generated
 */
public interface DoorsModule extends DoorsTreeNode {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getView();

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
         * @return 
         * @generated NOT
         */
        default CompletableFuture<List<String>> getObjectAttributesAsync(BackgroundTaskExecutor executor) {
            return CompletableFuture.completedFuture(getObjectAttributes());
        }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model attrsMany="true"
	 * @generated
	 */
	void setObjectAttributes(List<String> attrs);

} // DoorsModule
