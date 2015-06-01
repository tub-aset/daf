/**
 */
package de.jpwinkler.daf.dafcore.model.csv;

import java.util.Date;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import de.jpwinkler.daf.dafcore.csv.DoorsModuleVisitor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doors Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getModule <em>Module</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjects <em>Objects</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getParent <em>Parent</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectIdentifier <em>Object Identifier</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectLevel <em>Object Level</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectNumber <em>Object Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getCreatedBy <em>Created By</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getCreatedThru <em>Created Thru</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getCreatedOn <em>Created On</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getAbsoluteNumber <em>Absolute Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getLastModifiedOn <em>Last Modified On</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getLastModifiedBy <em>Last Modified By</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectText <em>Object Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectShortText <em>Object Short Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectHeading <em>Object Heading</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getOutgoingLinks <em>Outgoing Links</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getIncomingLinks <em>Incoming Links</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject()
 * @model
 * @generated
 */
public interface DoorsObject extends EObject {
    /**
     * Returns the value of the '<em><b>Module</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Module</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Module</em>' reference.
     * @see #setModule(DoorsModule)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_Module()
     * @model
     * @generated
     */
    DoorsModule getModule();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getModule <em>Module</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Module</em>' reference.
     * @see #getModule()
     * @generated
     */
    void setModule(DoorsModule value);

    /**
     * Returns the value of the '<em><b>Attributes</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attributes</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attributes</em>' map.
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_Attributes()
     * @model mapType="com.daimler.jonwink.srstp.core.model.csv.StringToStringMap<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" ordered="false"
     * @generated
     */
    EMap<String, String> getAttributes();

    /**
     * Returns the value of the '<em><b>Objects</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject}.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Objects</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Objects</em>' containment reference list.
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_Objects()
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getParent
     * @model opposite="parent" containment="true"
     * @generated
     */
    EList<DoorsObject> getObjects();

    /**
     * Returns the value of the '<em><b>Parent</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjects <em>Objects</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' container reference.
     * @see #setParent(DoorsObject)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_Parent()
     * @see de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjects
     * @model opposite="objects" transient="false"
     * @generated
     */
    DoorsObject getParent();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getParent <em>Parent</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' container reference.
     * @see #getParent()
     * @generated
     */
    void setParent(DoorsObject value);

    /**
     * Returns the value of the '<em><b>Object Identifier</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Object Identifier</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Object Identifier</em>' attribute.
     * @see #setObjectIdentifier(String)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_ObjectIdentifier()
     * @model unique="false"
     * @generated
     */
    String getObjectIdentifier();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectIdentifier <em>Object Identifier</em>}' attribute.
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
     * <p>
     * If the meaning of the '<em>Object Level</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Object Level</em>' attribute.
     * @see #setObjectLevel(int)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_ObjectLevel()
     * @model unique="false"
     * @generated
     */
    int getObjectLevel();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectLevel <em>Object Level</em>}' attribute.
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
     * <p>
     * If the meaning of the '<em>Object Number</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Object Number</em>' attribute.
     * @see #setObjectNumber(String)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_ObjectNumber()
     * @model unique="false"
     * @generated
     */
    String getObjectNumber();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectNumber <em>Object Number</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Object Number</em>' attribute.
     * @see #getObjectNumber()
     * @generated
     */
    void setObjectNumber(String value);

    /**
     * Returns the value of the '<em><b>Created By</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Created By</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Created By</em>' attribute.
     * @see #setCreatedBy(String)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_CreatedBy()
     * @model unique="false"
     * @generated
     */
    String getCreatedBy();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getCreatedBy <em>Created By</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Created By</em>' attribute.
     * @see #getCreatedBy()
     * @generated
     */
    void setCreatedBy(String value);

    /**
     * Returns the value of the '<em><b>Created Thru</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Created Thru</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Created Thru</em>' attribute.
     * @see #setCreatedThru(String)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_CreatedThru()
     * @model unique="false"
     * @generated
     */
    String getCreatedThru();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getCreatedThru <em>Created Thru</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Created Thru</em>' attribute.
     * @see #getCreatedThru()
     * @generated
     */
    void setCreatedThru(String value);

    /**
     * Returns the value of the '<em><b>Created On</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Created On</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Created On</em>' attribute.
     * @see #setCreatedOn(Date)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_CreatedOn()
     * @model unique="false"
     * @generated
     */
    Date getCreatedOn();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getCreatedOn <em>Created On</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Created On</em>' attribute.
     * @see #getCreatedOn()
     * @generated
     */
    void setCreatedOn(Date value);

    /**
     * Returns the value of the '<em><b>Absolute Number</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Absolute Number</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Absolute Number</em>' attribute.
     * @see #setAbsoluteNumber(int)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_AbsoluteNumber()
     * @model unique="false"
     * @generated
     */
    int getAbsoluteNumber();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getAbsoluteNumber <em>Absolute Number</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Absolute Number</em>' attribute.
     * @see #getAbsoluteNumber()
     * @generated
     */
    void setAbsoluteNumber(int value);

    /**
     * Returns the value of the '<em><b>Last Modified On</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Last Modified On</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Last Modified On</em>' attribute.
     * @see #setLastModifiedOn(Date)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_LastModifiedOn()
     * @model unique="false"
     * @generated
     */
    Date getLastModifiedOn();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getLastModifiedOn <em>Last Modified On</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Last Modified On</em>' attribute.
     * @see #getLastModifiedOn()
     * @generated
     */
    void setLastModifiedOn(Date value);

    /**
     * Returns the value of the '<em><b>Last Modified By</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Last Modified By</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Last Modified By</em>' attribute.
     * @see #setLastModifiedBy(String)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_LastModifiedBy()
     * @model unique="false"
     * @generated
     */
    String getLastModifiedBy();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getLastModifiedBy <em>Last Modified By</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Last Modified By</em>' attribute.
     * @see #getLastModifiedBy()
     * @generated
     */
    void setLastModifiedBy(String value);

    /**
     * Returns the value of the '<em><b>Object Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Object Text</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Object Text</em>' attribute.
     * @see #setObjectText(String)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_ObjectText()
     * @model unique="false"
     * @generated
     */
    String getObjectText();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectText <em>Object Text</em>}' attribute.
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
     * <p>
     * If the meaning of the '<em>Object Short Text</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Object Short Text</em>' attribute.
     * @see #setObjectShortText(String)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_ObjectShortText()
     * @model unique="false"
     * @generated
     */
    String getObjectShortText();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectShortText <em>Object Short Text</em>}' attribute.
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
     * <p>
     * If the meaning of the '<em>Object Heading</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Object Heading</em>' attribute.
     * @see #setObjectHeading(String)
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_ObjectHeading()
     * @model unique="false"
     * @generated
     */
    String getObjectHeading();

    /**
     * Sets the value of the '{@link de.jpwinkler.daf.dafcore.model.csv.DoorsObject#getObjectHeading <em>Object Heading</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Object Heading</em>' attribute.
     * @see #getObjectHeading()
     * @generated
     */
    void setObjectHeading(String value);

    /**
     * Returns the value of the '<em><b>Outgoing Links</b></em>' containment reference list.
     * The list contents are of type {@link de.jpwinkler.daf.dafcore.model.csv.Link}.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.dafcore.model.csv.Link#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Outgoing Links</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outgoing Links</em>' containment reference list.
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_OutgoingLinks()
     * @see de.jpwinkler.daf.dafcore.model.csv.Link#getSource
     * @model opposite="source" containment="true"
     * @generated
     */
    EList<Link> getOutgoingLinks();

    /**
     * Returns the value of the '<em><b>Incoming Links</b></em>' reference list.
     * The list contents are of type {@link de.jpwinkler.daf.dafcore.model.csv.ResolvedLink}.
     * It is bidirectional and its opposite is '{@link de.jpwinkler.daf.dafcore.model.csv.ResolvedLink#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Incoming Links</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Incoming Links</em>' reference list.
     * @see de.jpwinkler.daf.dafcore.model.csv.CSVPackage#getDoorsObject_IncomingLinks()
     * @see de.jpwinkler.daf.dafcore.model.csv.ResolvedLink#getTarget
     * @model opposite="target"
     * @generated
     */
    EList<ResolvedLink> getIncomingLinks();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model visitorDataType="com.daimler.jonwink.srstp.core.model.csv.DoorsModuleVisitor"
     *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (visitor.visitPreTraverse(this)) {\r\n\tfor (final DoorsObject object : getObjects()) {\r\n\t\tobject.accept(visitor);\r\n\t}\r\n}\r\nvisitor.visitPostTraverse(this);'"
     * @generated
     */
    void accept(DoorsModuleVisitor visitor);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return !getObjectHeading().isEmpty();'"
     * @generated
     */
    boolean isHeading();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (isHeading()) {\r\n\treturn getObjectHeading();\r\n} else {\r\n\treturn getObjectText();\r\n}'"
     * @generated
     */
    String getText();

} // DoorsObject
