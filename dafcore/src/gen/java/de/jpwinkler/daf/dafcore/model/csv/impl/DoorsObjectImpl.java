/**
 */
package de.jpwinkler.daf.dafcore.model.csv.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.dafcore.csv.DoorsModuleVisitor;
import de.jpwinkler.daf.dafcore.model.csv.CSVPackage;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.Link;
import de.jpwinkler.daf.dafcore.model.csv.ResolvedLink;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Doors Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getModule <em>Module</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getObjects <em>Objects</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getObjectIdentifier <em>Object Identifier</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getObjectLevel <em>Object Level</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getObjectNumber <em>Object Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getCreatedBy <em>Created By</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getCreatedThru <em>Created Thru</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getCreatedOn <em>Created On</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getAbsoluteNumber <em>Absolute Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getLastModifiedOn <em>Last Modified On</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getLastModifiedBy <em>Last Modified By</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getObjectText <em>Object Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getObjectShortText <em>Object Short Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getObjectHeading <em>Object Heading</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getOutgoingLinks <em>Outgoing Links</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsObjectImpl#getIncomingLinks <em>Incoming Links</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DoorsObjectImpl extends MinimalEObjectImpl.Container implements DoorsObject {
    /**
     * The cached value of the '{@link #getModule() <em>Module</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModule()
     * @generated
     * @ordered
     */
    protected DoorsModule module;

    /**
     * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributes()
     * @generated
     * @ordered
     */
    protected EMap<String, String> attributes;

    /**
     * The cached value of the '{@link #getObjects() <em>Objects</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjects()
     * @generated
     * @ordered
     */
    protected EList<DoorsObject> objects;

    /**
     * The default value of the '{@link #getObjectIdentifier() <em>Object Identifier</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjectIdentifier()
     * @generated
     * @ordered
     */
    protected static final String OBJECT_IDENTIFIER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getObjectIdentifier() <em>Object Identifier</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjectIdentifier()
     * @generated
     * @ordered
     */
    protected String objectIdentifier = OBJECT_IDENTIFIER_EDEFAULT;

    /**
     * The default value of the '{@link #getObjectLevel() <em>Object Level</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjectLevel()
     * @generated
     * @ordered
     */
    protected static final int OBJECT_LEVEL_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getObjectLevel() <em>Object Level</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjectLevel()
     * @generated
     * @ordered
     */
    protected int objectLevel = OBJECT_LEVEL_EDEFAULT;

    /**
     * The default value of the '{@link #getObjectNumber() <em>Object Number</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjectNumber()
     * @generated
     * @ordered
     */
    protected static final String OBJECT_NUMBER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getObjectNumber() <em>Object Number</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjectNumber()
     * @generated
     * @ordered
     */
    protected String objectNumber = OBJECT_NUMBER_EDEFAULT;

    /**
     * The default value of the '{@link #getCreatedBy() <em>Created By</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreatedBy()
     * @generated
     * @ordered
     */
    protected static final String CREATED_BY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCreatedBy() <em>Created By</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreatedBy()
     * @generated
     * @ordered
     */
    protected String createdBy = CREATED_BY_EDEFAULT;

    /**
     * The default value of the '{@link #getCreatedThru() <em>Created Thru</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreatedThru()
     * @generated
     * @ordered
     */
    protected static final String CREATED_THRU_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCreatedThru() <em>Created Thru</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreatedThru()
     * @generated
     * @ordered
     */
    protected String createdThru = CREATED_THRU_EDEFAULT;

    /**
     * The default value of the '{@link #getCreatedOn() <em>Created On</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreatedOn()
     * @generated
     * @ordered
     */
    protected static final Date CREATED_ON_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCreatedOn() <em>Created On</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCreatedOn()
     * @generated
     * @ordered
     */
    protected Date createdOn = CREATED_ON_EDEFAULT;

    /**
     * The default value of the '{@link #getAbsoluteNumber() <em>Absolute Number</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAbsoluteNumber()
     * @generated
     * @ordered
     */
    protected static final int ABSOLUTE_NUMBER_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getAbsoluteNumber() <em>Absolute Number</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAbsoluteNumber()
     * @generated
     * @ordered
     */
    protected int absoluteNumber = ABSOLUTE_NUMBER_EDEFAULT;

    /**
     * The default value of the '{@link #getLastModifiedOn() <em>Last Modified On</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLastModifiedOn()
     * @generated
     * @ordered
     */
    protected static final Date LAST_MODIFIED_ON_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLastModifiedOn() <em>Last Modified On</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLastModifiedOn()
     * @generated
     * @ordered
     */
    protected Date lastModifiedOn = LAST_MODIFIED_ON_EDEFAULT;

    /**
     * The default value of the '{@link #getLastModifiedBy() <em>Last Modified By</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLastModifiedBy()
     * @generated
     * @ordered
     */
    protected static final String LAST_MODIFIED_BY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLastModifiedBy() <em>Last Modified By</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLastModifiedBy()
     * @generated
     * @ordered
     */
    protected String lastModifiedBy = LAST_MODIFIED_BY_EDEFAULT;

    /**
     * The default value of the '{@link #getObjectText() <em>Object Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjectText()
     * @generated
     * @ordered
     */
    protected static final String OBJECT_TEXT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getObjectText() <em>Object Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjectText()
     * @generated
     * @ordered
     */
    protected String objectText = OBJECT_TEXT_EDEFAULT;

    /**
     * The default value of the '{@link #getObjectShortText() <em>Object Short Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjectShortText()
     * @generated
     * @ordered
     */
    protected static final String OBJECT_SHORT_TEXT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getObjectShortText() <em>Object Short Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjectShortText()
     * @generated
     * @ordered
     */
    protected String objectShortText = OBJECT_SHORT_TEXT_EDEFAULT;

    /**
     * The default value of the '{@link #getObjectHeading() <em>Object Heading</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjectHeading()
     * @generated
     * @ordered
     */
    protected static final String OBJECT_HEADING_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getObjectHeading() <em>Object Heading</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjectHeading()
     * @generated
     * @ordered
     */
    protected String objectHeading = OBJECT_HEADING_EDEFAULT;

    /**
     * The cached value of the '{@link #getOutgoingLinks() <em>Outgoing Links</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOutgoingLinks()
     * @generated
     * @ordered
     */
    protected EList<Link> outgoingLinks;

    /**
     * The cached value of the '{@link #getIncomingLinks() <em>Incoming Links</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIncomingLinks()
     * @generated
     * @ordered
     */
    protected EList<ResolvedLink> incomingLinks;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DoorsObjectImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CSVPackage.Literals.DOORS_OBJECT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DoorsModule getModule() {
        if (module != null && module.eIsProxy()) {
            final InternalEObject oldModule = (InternalEObject)module;
            module = (DoorsModule)eResolveProxy(oldModule);
            if (module != oldModule) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CSVPackage.DOORS_OBJECT__MODULE, oldModule, module));
                }
            }
        }
        return module;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoorsModule basicGetModule() {
        return module;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setModule(final DoorsModule newModule) {
        final DoorsModule oldModule = module;
        module = newModule;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__MODULE, oldModule, module));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EMap<String, String> getAttributes() {
        if (attributes == null) {
            attributes = new EcoreEMap<String,String>(CSVPackage.Literals.STRING_TO_STRING_MAP, StringToStringMapImpl.class, this, CSVPackage.DOORS_OBJECT__ATTRIBUTES);
        }
        return attributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<DoorsObject> getObjects() {
        if (objects == null) {
            objects = new EObjectContainmentWithInverseEList<DoorsObject>(DoorsObject.class, this, CSVPackage.DOORS_OBJECT__OBJECTS, CSVPackage.DOORS_OBJECT__PARENT);
        }
        return objects;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DoorsObject getParent() {
        if (eContainerFeatureID() != CSVPackage.DOORS_OBJECT__PARENT) {
            return null;
        }
        return (DoorsObject)eInternalContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParent(final DoorsObject newParent, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newParent, CSVPackage.DOORS_OBJECT__PARENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setParent(final DoorsObject newParent) {
        if (newParent != eInternalContainer() || (eContainerFeatureID() != CSVPackage.DOORS_OBJECT__PARENT && newParent != null)) {
            if (EcoreUtil.isAncestor(this, newParent)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            }
            NotificationChain msgs = null;
            if (eInternalContainer() != null) {
                msgs = eBasicRemoveFromContainer(msgs);
            }
            if (newParent != null) {
                msgs = ((InternalEObject)newParent).eInverseAdd(this, CSVPackage.DOORS_OBJECT__OBJECTS, DoorsObject.class, msgs);
            }
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        }
        else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__PARENT, newParent, newParent));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getObjectIdentifier() {
        return objectIdentifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setObjectIdentifier(final String newObjectIdentifier) {
        final String oldObjectIdentifier = objectIdentifier;
        objectIdentifier = newObjectIdentifier;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__OBJECT_IDENTIFIER, oldObjectIdentifier, objectIdentifier));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getObjectLevel() {
        return objectLevel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setObjectLevel(final int newObjectLevel) {
        final int oldObjectLevel = objectLevel;
        objectLevel = newObjectLevel;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__OBJECT_LEVEL, oldObjectLevel, objectLevel));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getObjectNumber() {
        return objectNumber;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setObjectNumber(final String newObjectNumber) {
        final String oldObjectNumber = objectNumber;
        objectNumber = newObjectNumber;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__OBJECT_NUMBER, oldObjectNumber, objectNumber));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCreatedBy(final String newCreatedBy) {
        final String oldCreatedBy = createdBy;
        createdBy = newCreatedBy;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__CREATED_BY, oldCreatedBy, createdBy));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCreatedThru() {
        return createdThru;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCreatedThru(final String newCreatedThru) {
        final String oldCreatedThru = createdThru;
        createdThru = newCreatedThru;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__CREATED_THRU, oldCreatedThru, createdThru));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCreatedOn(final Date newCreatedOn) {
        final Date oldCreatedOn = createdOn;
        createdOn = newCreatedOn;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__CREATED_ON, oldCreatedOn, createdOn));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getAbsoluteNumber() {
        return absoluteNumber;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAbsoluteNumber(final int newAbsoluteNumber) {
        final int oldAbsoluteNumber = absoluteNumber;
        absoluteNumber = newAbsoluteNumber;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__ABSOLUTE_NUMBER, oldAbsoluteNumber, absoluteNumber));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLastModifiedOn(final Date newLastModifiedOn) {
        final Date oldLastModifiedOn = lastModifiedOn;
        lastModifiedOn = newLastModifiedOn;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__LAST_MODIFIED_ON, oldLastModifiedOn, lastModifiedOn));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLastModifiedBy(final String newLastModifiedBy) {
        final String oldLastModifiedBy = lastModifiedBy;
        lastModifiedBy = newLastModifiedBy;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__LAST_MODIFIED_BY, oldLastModifiedBy, lastModifiedBy));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getObjectText() {
        return objectText;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setObjectText(final String newObjectText) {
        final String oldObjectText = objectText;
        objectText = newObjectText;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__OBJECT_TEXT, oldObjectText, objectText));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getObjectShortText() {
        return objectShortText;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setObjectShortText(final String newObjectShortText) {
        final String oldObjectShortText = objectShortText;
        objectShortText = newObjectShortText;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__OBJECT_SHORT_TEXT, oldObjectShortText, objectShortText));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getObjectHeading() {
        return objectHeading;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setObjectHeading(final String newObjectHeading) {
        final String oldObjectHeading = objectHeading;
        objectHeading = newObjectHeading;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_OBJECT__OBJECT_HEADING, oldObjectHeading, objectHeading));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<Link> getOutgoingLinks() {
        if (outgoingLinks == null) {
            outgoingLinks = new EObjectContainmentWithInverseEList<Link>(Link.class, this, CSVPackage.DOORS_OBJECT__OUTGOING_LINKS, CSVPackage.LINK__SOURCE);
        }
        return outgoingLinks;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<ResolvedLink> getIncomingLinks() {
        if (incomingLinks == null) {
            incomingLinks = new EObjectWithInverseResolvingEList<ResolvedLink>(ResolvedLink.class, this, CSVPackage.DOORS_OBJECT__INCOMING_LINKS, CSVPackage.RESOLVED_LINK__TARGET);
        }
        return incomingLinks;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void accept(final DoorsModuleVisitor visitor) {
        if (visitor.visitPreTraverse(this)) {
            for (final DoorsObject object : getObjects()) {
                object.accept(visitor);
            }
        }
        visitor.visitPostTraverse(this);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isHeading() {
        return !getObjectHeading().isEmpty();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText() {
        if (isHeading()) {
            return getObjectHeading();
        } else {
            return getObjectText();
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case CSVPackage.DOORS_OBJECT__OBJECTS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getObjects()).basicAdd(otherEnd, msgs);
        case CSVPackage.DOORS_OBJECT__PARENT:
            if (eInternalContainer() != null) {
                msgs = eBasicRemoveFromContainer(msgs);
            }
            return basicSetParent((DoorsObject)otherEnd, msgs);
        case CSVPackage.DOORS_OBJECT__OUTGOING_LINKS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingLinks()).basicAdd(otherEnd, msgs);
        case CSVPackage.DOORS_OBJECT__INCOMING_LINKS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingLinks()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID, final NotificationChain msgs) {
        switch (featureID) {
        case CSVPackage.DOORS_OBJECT__ATTRIBUTES:
            return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
        case CSVPackage.DOORS_OBJECT__OBJECTS:
            return ((InternalEList<?>)getObjects()).basicRemove(otherEnd, msgs);
        case CSVPackage.DOORS_OBJECT__PARENT:
            return basicSetParent(null, msgs);
        case CSVPackage.DOORS_OBJECT__OUTGOING_LINKS:
            return ((InternalEList<?>)getOutgoingLinks()).basicRemove(otherEnd, msgs);
        case CSVPackage.DOORS_OBJECT__INCOMING_LINKS:
            return ((InternalEList<?>)getIncomingLinks()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(final NotificationChain msgs) {
        switch (eContainerFeatureID()) {
        case CSVPackage.DOORS_OBJECT__PARENT:
            return eInternalContainer().eInverseRemove(this, CSVPackage.DOORS_OBJECT__OBJECTS, DoorsObject.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case CSVPackage.DOORS_OBJECT__MODULE:
            if (resolve) {
                return getModule();
            }
            return basicGetModule();
        case CSVPackage.DOORS_OBJECT__ATTRIBUTES:
            if (coreType) {
                return getAttributes();
            } else {
                return getAttributes().map();
            }
        case CSVPackage.DOORS_OBJECT__OBJECTS:
            return getObjects();
        case CSVPackage.DOORS_OBJECT__PARENT:
            return getParent();
        case CSVPackage.DOORS_OBJECT__OBJECT_IDENTIFIER:
            return getObjectIdentifier();
        case CSVPackage.DOORS_OBJECT__OBJECT_LEVEL:
            return getObjectLevel();
        case CSVPackage.DOORS_OBJECT__OBJECT_NUMBER:
            return getObjectNumber();
        case CSVPackage.DOORS_OBJECT__CREATED_BY:
            return getCreatedBy();
        case CSVPackage.DOORS_OBJECT__CREATED_THRU:
            return getCreatedThru();
        case CSVPackage.DOORS_OBJECT__CREATED_ON:
            return getCreatedOn();
        case CSVPackage.DOORS_OBJECT__ABSOLUTE_NUMBER:
            return getAbsoluteNumber();
        case CSVPackage.DOORS_OBJECT__LAST_MODIFIED_ON:
            return getLastModifiedOn();
        case CSVPackage.DOORS_OBJECT__LAST_MODIFIED_BY:
            return getLastModifiedBy();
        case CSVPackage.DOORS_OBJECT__OBJECT_TEXT:
            return getObjectText();
        case CSVPackage.DOORS_OBJECT__OBJECT_SHORT_TEXT:
            return getObjectShortText();
        case CSVPackage.DOORS_OBJECT__OBJECT_HEADING:
            return getObjectHeading();
        case CSVPackage.DOORS_OBJECT__OUTGOING_LINKS:
            return getOutgoingLinks();
        case CSVPackage.DOORS_OBJECT__INCOMING_LINKS:
            return getIncomingLinks();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case CSVPackage.DOORS_OBJECT__MODULE:
            setModule((DoorsModule)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__ATTRIBUTES:
            ((EStructuralFeature.Setting)getAttributes()).set(newValue);
            return;
        case CSVPackage.DOORS_OBJECT__OBJECTS:
            getObjects().clear();
            getObjects().addAll((Collection<? extends DoorsObject>)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__PARENT:
            setParent((DoorsObject)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__OBJECT_IDENTIFIER:
            setObjectIdentifier((String)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__OBJECT_LEVEL:
            setObjectLevel((Integer)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__OBJECT_NUMBER:
            setObjectNumber((String)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__CREATED_BY:
            setCreatedBy((String)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__CREATED_THRU:
            setCreatedThru((String)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__CREATED_ON:
            setCreatedOn((Date)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__ABSOLUTE_NUMBER:
            setAbsoluteNumber((Integer)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__LAST_MODIFIED_ON:
            setLastModifiedOn((Date)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__LAST_MODIFIED_BY:
            setLastModifiedBy((String)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__OBJECT_TEXT:
            setObjectText((String)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__OBJECT_SHORT_TEXT:
            setObjectShortText((String)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__OBJECT_HEADING:
            setObjectHeading((String)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__OUTGOING_LINKS:
            getOutgoingLinks().clear();
            getOutgoingLinks().addAll((Collection<? extends Link>)newValue);
            return;
        case CSVPackage.DOORS_OBJECT__INCOMING_LINKS:
            getIncomingLinks().clear();
            getIncomingLinks().addAll((Collection<? extends ResolvedLink>)newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case CSVPackage.DOORS_OBJECT__MODULE:
            setModule((DoorsModule)null);
            return;
        case CSVPackage.DOORS_OBJECT__ATTRIBUTES:
            getAttributes().clear();
            return;
        case CSVPackage.DOORS_OBJECT__OBJECTS:
            getObjects().clear();
            return;
        case CSVPackage.DOORS_OBJECT__PARENT:
            setParent((DoorsObject)null);
            return;
        case CSVPackage.DOORS_OBJECT__OBJECT_IDENTIFIER:
            setObjectIdentifier(OBJECT_IDENTIFIER_EDEFAULT);
            return;
        case CSVPackage.DOORS_OBJECT__OBJECT_LEVEL:
            setObjectLevel(OBJECT_LEVEL_EDEFAULT);
            return;
        case CSVPackage.DOORS_OBJECT__OBJECT_NUMBER:
            setObjectNumber(OBJECT_NUMBER_EDEFAULT);
            return;
        case CSVPackage.DOORS_OBJECT__CREATED_BY:
            setCreatedBy(CREATED_BY_EDEFAULT);
            return;
        case CSVPackage.DOORS_OBJECT__CREATED_THRU:
            setCreatedThru(CREATED_THRU_EDEFAULT);
            return;
        case CSVPackage.DOORS_OBJECT__CREATED_ON:
            setCreatedOn(CREATED_ON_EDEFAULT);
            return;
        case CSVPackage.DOORS_OBJECT__ABSOLUTE_NUMBER:
            setAbsoluteNumber(ABSOLUTE_NUMBER_EDEFAULT);
            return;
        case CSVPackage.DOORS_OBJECT__LAST_MODIFIED_ON:
            setLastModifiedOn(LAST_MODIFIED_ON_EDEFAULT);
            return;
        case CSVPackage.DOORS_OBJECT__LAST_MODIFIED_BY:
            setLastModifiedBy(LAST_MODIFIED_BY_EDEFAULT);
            return;
        case CSVPackage.DOORS_OBJECT__OBJECT_TEXT:
            setObjectText(OBJECT_TEXT_EDEFAULT);
            return;
        case CSVPackage.DOORS_OBJECT__OBJECT_SHORT_TEXT:
            setObjectShortText(OBJECT_SHORT_TEXT_EDEFAULT);
            return;
        case CSVPackage.DOORS_OBJECT__OBJECT_HEADING:
            setObjectHeading(OBJECT_HEADING_EDEFAULT);
            return;
        case CSVPackage.DOORS_OBJECT__OUTGOING_LINKS:
            getOutgoingLinks().clear();
            return;
        case CSVPackage.DOORS_OBJECT__INCOMING_LINKS:
            getIncomingLinks().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case CSVPackage.DOORS_OBJECT__MODULE:
            return module != null;
        case CSVPackage.DOORS_OBJECT__ATTRIBUTES:
            return attributes != null && !attributes.isEmpty();
        case CSVPackage.DOORS_OBJECT__OBJECTS:
            return objects != null && !objects.isEmpty();
        case CSVPackage.DOORS_OBJECT__PARENT:
            return getParent() != null;
        case CSVPackage.DOORS_OBJECT__OBJECT_IDENTIFIER:
            return OBJECT_IDENTIFIER_EDEFAULT == null ? objectIdentifier != null : !OBJECT_IDENTIFIER_EDEFAULT.equals(objectIdentifier);
        case CSVPackage.DOORS_OBJECT__OBJECT_LEVEL:
            return objectLevel != OBJECT_LEVEL_EDEFAULT;
        case CSVPackage.DOORS_OBJECT__OBJECT_NUMBER:
            return OBJECT_NUMBER_EDEFAULT == null ? objectNumber != null : !OBJECT_NUMBER_EDEFAULT.equals(objectNumber);
        case CSVPackage.DOORS_OBJECT__CREATED_BY:
            return CREATED_BY_EDEFAULT == null ? createdBy != null : !CREATED_BY_EDEFAULT.equals(createdBy);
        case CSVPackage.DOORS_OBJECT__CREATED_THRU:
            return CREATED_THRU_EDEFAULT == null ? createdThru != null : !CREATED_THRU_EDEFAULT.equals(createdThru);
        case CSVPackage.DOORS_OBJECT__CREATED_ON:
            return CREATED_ON_EDEFAULT == null ? createdOn != null : !CREATED_ON_EDEFAULT.equals(createdOn);
        case CSVPackage.DOORS_OBJECT__ABSOLUTE_NUMBER:
            return absoluteNumber != ABSOLUTE_NUMBER_EDEFAULT;
        case CSVPackage.DOORS_OBJECT__LAST_MODIFIED_ON:
            return LAST_MODIFIED_ON_EDEFAULT == null ? lastModifiedOn != null : !LAST_MODIFIED_ON_EDEFAULT.equals(lastModifiedOn);
        case CSVPackage.DOORS_OBJECT__LAST_MODIFIED_BY:
            return LAST_MODIFIED_BY_EDEFAULT == null ? lastModifiedBy != null : !LAST_MODIFIED_BY_EDEFAULT.equals(lastModifiedBy);
        case CSVPackage.DOORS_OBJECT__OBJECT_TEXT:
            return OBJECT_TEXT_EDEFAULT == null ? objectText != null : !OBJECT_TEXT_EDEFAULT.equals(objectText);
        case CSVPackage.DOORS_OBJECT__OBJECT_SHORT_TEXT:
            return OBJECT_SHORT_TEXT_EDEFAULT == null ? objectShortText != null : !OBJECT_SHORT_TEXT_EDEFAULT.equals(objectShortText);
        case CSVPackage.DOORS_OBJECT__OBJECT_HEADING:
            return OBJECT_HEADING_EDEFAULT == null ? objectHeading != null : !OBJECT_HEADING_EDEFAULT.equals(objectHeading);
        case CSVPackage.DOORS_OBJECT__OUTGOING_LINKS:
            return outgoingLinks != null && !outgoingLinks.isEmpty();
        case CSVPackage.DOORS_OBJECT__INCOMING_LINKS:
            return incomingLinks != null && !incomingLinks.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eInvoke(final int operationID, final EList<?> arguments) throws InvocationTargetException {
        switch (operationID) {
        case CSVPackage.DOORS_OBJECT___ACCEPT__DOORSMODULEVISITOR:
            accept((DoorsModuleVisitor)arguments.get(0));
            return null;
        case CSVPackage.DOORS_OBJECT___IS_HEADING:
            return isHeading();
        case CSVPackage.DOORS_OBJECT___GET_TEXT:
            return getText();
        }
        return super.eInvoke(operationID, arguments);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        final StringBuffer result = new StringBuffer(super.toString());
        result.append(" (objectIdentifier: ");
        result.append(objectIdentifier);
        result.append(", objectLevel: ");
        result.append(objectLevel);
        result.append(", objectNumber: ");
        result.append(objectNumber);
        result.append(", createdBy: ");
        result.append(createdBy);
        result.append(", createdThru: ");
        result.append(createdThru);
        result.append(", createdOn: ");
        result.append(createdOn);
        result.append(", absoluteNumber: ");
        result.append(absoluteNumber);
        result.append(", lastModifiedOn: ");
        result.append(lastModifiedOn);
        result.append(", lastModifiedBy: ");
        result.append(lastModifiedBy);
        result.append(", objectText: ");
        result.append(objectText);
        result.append(", objectShortText: ");
        result.append(objectShortText);
        result.append(", objectHeading: ");
        result.append(objectHeading);
        result.append(')');
        return result.toString();
    }

} //DoorsObjectImpl
