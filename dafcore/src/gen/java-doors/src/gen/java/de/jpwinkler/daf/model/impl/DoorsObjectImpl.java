/**
 */
package de.jpwinkler.daf.model.impl;

import de.jpwinkler.daf.model.DoorsFactory;
import de.jpwinkler.daf.model.DoorsObject;
import de.jpwinkler.daf.model.DoorsPackage;
import de.jpwinkler.daf.model.DoorsAttributes;
import de.jpwinkler.daf.model.DoorsTreeNode;
import de.jpwinkler.daf.model.Link;
import de.jpwinkler.daf.model.ResolvedLink;
import de.jpwinkler.daf.model.UnresolvedLink;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Doors Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsObjectImpl#getObjectIdentifier <em>Object Identifier</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsObjectImpl#getObjectLevel <em>Object Level</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsObjectImpl#getObjectNumber <em>Object Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsObjectImpl#getAbsoluteNumber <em>Absolute Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsObjectImpl#getObjectText <em>Object Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsObjectImpl#getObjectShortText <em>Object Short Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsObjectImpl#getObjectHeading <em>Object Heading</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsObjectImpl#getText <em>Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsObjectImpl#getOutgoingLinks <em>Outgoing Links</em>}</li>
 *   <li>{@link de.jpwinkler.daf.model.impl.DoorsObjectImpl#getIncomingLinks <em>Incoming Links</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DoorsObjectImpl extends DoorsTreeNodeImpl implements DoorsObject {

    /**
	 * The default value of the '{@link #getObjectIdentifier() <em>Object Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getObjectIdentifier()
	 * @generated
	 * @ordered
	 */
    protected static final String OBJECT_IDENTIFIER_EDEFAULT = "";

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
	 * The default value of the '{@link #getObjectNumber() <em>Object Number</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getObjectNumber()
	 * @generated
	 * @ordered
	 */
    protected static final String OBJECT_NUMBER_EDEFAULT = null;

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
	 * The default value of the '{@link #getObjectText() <em>Object Text</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getObjectText()
	 * @generated
	 * @ordered
	 */
    protected static final String OBJECT_TEXT_EDEFAULT = null;

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
	 * The default value of the '{@link #getObjectHeading() <em>Object Heading</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getObjectHeading()
	 * @generated
	 * @ordered
	 */
    protected static final String OBJECT_HEADING_EDEFAULT = null;

    /**
	 * The default value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
    protected static final String TEXT_EDEFAULT = "";

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
     * <!-- end-user-doc --> @generated
     */
    protected DoorsObjectImpl() {
		super();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    protected EClass eStaticClass() {
		return DoorsPackage.Literals.DOORS_OBJECT;
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public String getObjectIdentifier() {
        return DoorsAttributes.OBJECT_IDENTIFIER.getValue(String.class, this);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public void setObjectIdentifier(final String newObjectIdentifier) {
        DoorsAttributes.OBJECT_IDENTIFIER.setValue(String.class, this, newObjectIdentifier);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public int getObjectLevel() {
        return DoorsAttributes.OBJECT_LEVEL.getValue(Integer.class, this);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public void setObjectLevel(final int newObjectLevel) {
        DoorsAttributes.OBJECT_LEVEL.setValue(Integer.class, this, newObjectLevel);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public String getObjectNumber() {
        return DoorsAttributes.OBJECT_NUMBER.getValue(String.class, this);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public void setObjectNumber(final String newObjectNumber) {
        DoorsAttributes.OBJECT_NUMBER.setValue(String.class, this, newObjectNumber);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public int getAbsoluteNumber() {
        return DoorsAttributes.ABSOLUTE_NUMBER.getValue(Integer.class, this);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public void setAbsoluteNumber(final int newAbsoluteNumber) {
        DoorsAttributes.ABSOLUTE_NUMBER.setValue(Integer.class, this, newAbsoluteNumber);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public String getObjectText() {
        return DoorsAttributes.OBJECT_TEXT.getValue(String.class, this);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public void setObjectText(final String newObjectText) {
        DoorsAttributes.OBJECT_TEXT.setValue(String.class, this, newObjectText);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public String getObjectShortText() {
        return DoorsAttributes.OBJECT_SHORT_TEXT.getValue(String.class, this);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public void setObjectShortText(final String newObjectShortText) {
        DoorsAttributes.OBJECT_SHORT_TEXT.setValue(String.class, this, newObjectShortText);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public String getObjectHeading() {
        return DoorsAttributes.OBJECT_HEADING.getValue(String.class, this);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public void setObjectHeading(final String newObjectHeading) {
        DoorsAttributes.OBJECT_HEADING.setValue(String.class, this, newObjectHeading);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public String getText() {
        return isHeading() ? getObjectHeading(): getObjectText();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public void setText(final String newText) {
        if (isHeading()) {
            setObjectHeading(newText);
        } else {
            setObjectText(newText);
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public List<Link> getOutgoingLinks() {
		if (outgoingLinks == null) {
			outgoingLinks = new EObjectContainmentWithInverseEList<Link>(Link.class, this, DoorsPackage.DOORS_OBJECT__OUTGOING_LINKS, DoorsPackage.LINK__SOURCE);
		}
		return outgoingLinks;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public List<ResolvedLink> getIncomingLinks() {
		if (incomingLinks == null) {
			incomingLinks = new EObjectWithInverseResolvingEList<ResolvedLink>(ResolvedLink.class, this, DoorsPackage.DOORS_OBJECT__INCOMING_LINKS, DoorsPackage.RESOLVED_LINK__TARGET);
		}
		return incomingLinks;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public boolean isHeading() {
		return getObjectHeading() != null && !getObjectHeading().isEmpty();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DoorsPackage.DOORS_OBJECT__OUTGOING_LINKS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingLinks()).basicAdd(otherEnd, msgs);
			case DoorsPackage.DOORS_OBJECT__INCOMING_LINKS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingLinks()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DoorsPackage.DOORS_OBJECT__OUTGOING_LINKS:
				return ((InternalEList<?>)getOutgoingLinks()).basicRemove(otherEnd, msgs);
			case DoorsPackage.DOORS_OBJECT__INCOMING_LINKS:
				return ((InternalEList<?>)getIncomingLinks()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DoorsPackage.DOORS_OBJECT__OBJECT_IDENTIFIER:
				return getObjectIdentifier();
			case DoorsPackage.DOORS_OBJECT__OBJECT_LEVEL:
				return getObjectLevel();
			case DoorsPackage.DOORS_OBJECT__OBJECT_NUMBER:
				return getObjectNumber();
			case DoorsPackage.DOORS_OBJECT__ABSOLUTE_NUMBER:
				return getAbsoluteNumber();
			case DoorsPackage.DOORS_OBJECT__OBJECT_TEXT:
				return getObjectText();
			case DoorsPackage.DOORS_OBJECT__OBJECT_SHORT_TEXT:
				return getObjectShortText();
			case DoorsPackage.DOORS_OBJECT__OBJECT_HEADING:
				return getObjectHeading();
			case DoorsPackage.DOORS_OBJECT__TEXT:
				return getText();
			case DoorsPackage.DOORS_OBJECT__OUTGOING_LINKS:
				return getOutgoingLinks();
			case DoorsPackage.DOORS_OBJECT__INCOMING_LINKS:
				return getIncomingLinks();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DoorsPackage.DOORS_OBJECT__OBJECT_IDENTIFIER:
				setObjectIdentifier((String)newValue);
				return;
			case DoorsPackage.DOORS_OBJECT__OBJECT_LEVEL:
				setObjectLevel((Integer)newValue);
				return;
			case DoorsPackage.DOORS_OBJECT__OBJECT_NUMBER:
				setObjectNumber((String)newValue);
				return;
			case DoorsPackage.DOORS_OBJECT__ABSOLUTE_NUMBER:
				setAbsoluteNumber((Integer)newValue);
				return;
			case DoorsPackage.DOORS_OBJECT__OBJECT_TEXT:
				setObjectText((String)newValue);
				return;
			case DoorsPackage.DOORS_OBJECT__OBJECT_SHORT_TEXT:
				setObjectShortText((String)newValue);
				return;
			case DoorsPackage.DOORS_OBJECT__OBJECT_HEADING:
				setObjectHeading((String)newValue);
				return;
			case DoorsPackage.DOORS_OBJECT__TEXT:
				setText((String)newValue);
				return;
			case DoorsPackage.DOORS_OBJECT__OUTGOING_LINKS:
				getOutgoingLinks().clear();
				getOutgoingLinks().addAll((Collection<? extends Link>)newValue);
				return;
			case DoorsPackage.DOORS_OBJECT__INCOMING_LINKS:
				getIncomingLinks().clear();
				getIncomingLinks().addAll((Collection<? extends ResolvedLink>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public void eUnset(int featureID) {
		switch (featureID) {
			case DoorsPackage.DOORS_OBJECT__OBJECT_IDENTIFIER:
				setObjectIdentifier(OBJECT_IDENTIFIER_EDEFAULT);
				return;
			case DoorsPackage.DOORS_OBJECT__OBJECT_LEVEL:
				setObjectLevel(OBJECT_LEVEL_EDEFAULT);
				return;
			case DoorsPackage.DOORS_OBJECT__OBJECT_NUMBER:
				setObjectNumber(OBJECT_NUMBER_EDEFAULT);
				return;
			case DoorsPackage.DOORS_OBJECT__ABSOLUTE_NUMBER:
				setAbsoluteNumber(ABSOLUTE_NUMBER_EDEFAULT);
				return;
			case DoorsPackage.DOORS_OBJECT__OBJECT_TEXT:
				setObjectText(OBJECT_TEXT_EDEFAULT);
				return;
			case DoorsPackage.DOORS_OBJECT__OBJECT_SHORT_TEXT:
				setObjectShortText(OBJECT_SHORT_TEXT_EDEFAULT);
				return;
			case DoorsPackage.DOORS_OBJECT__OBJECT_HEADING:
				setObjectHeading(OBJECT_HEADING_EDEFAULT);
				return;
			case DoorsPackage.DOORS_OBJECT__TEXT:
				setText(TEXT_EDEFAULT);
				return;
			case DoorsPackage.DOORS_OBJECT__OUTGOING_LINKS:
				getOutgoingLinks().clear();
				return;
			case DoorsPackage.DOORS_OBJECT__INCOMING_LINKS:
				getIncomingLinks().clear();
				return;
		}
		super.eUnset(featureID);
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc --> @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DoorsPackage.DOORS_OBJECT__OBJECT_IDENTIFIER:
				return OBJECT_IDENTIFIER_EDEFAULT == null ? getObjectIdentifier() != null : !OBJECT_IDENTIFIER_EDEFAULT.equals(getObjectIdentifier());
			case DoorsPackage.DOORS_OBJECT__OBJECT_LEVEL:
				return getObjectLevel() != OBJECT_LEVEL_EDEFAULT;
			case DoorsPackage.DOORS_OBJECT__OBJECT_NUMBER:
				return OBJECT_NUMBER_EDEFAULT == null ? getObjectNumber() != null : !OBJECT_NUMBER_EDEFAULT.equals(getObjectNumber());
			case DoorsPackage.DOORS_OBJECT__ABSOLUTE_NUMBER:
				return getAbsoluteNumber() != ABSOLUTE_NUMBER_EDEFAULT;
			case DoorsPackage.DOORS_OBJECT__OBJECT_TEXT:
				return OBJECT_TEXT_EDEFAULT == null ? getObjectText() != null : !OBJECT_TEXT_EDEFAULT.equals(getObjectText());
			case DoorsPackage.DOORS_OBJECT__OBJECT_SHORT_TEXT:
				return OBJECT_SHORT_TEXT_EDEFAULT == null ? getObjectShortText() != null : !OBJECT_SHORT_TEXT_EDEFAULT.equals(getObjectShortText());
			case DoorsPackage.DOORS_OBJECT__OBJECT_HEADING:
				return OBJECT_HEADING_EDEFAULT == null ? getObjectHeading() != null : !OBJECT_HEADING_EDEFAULT.equals(getObjectHeading());
			case DoorsPackage.DOORS_OBJECT__TEXT:
				return TEXT_EDEFAULT == null ? getText() != null : !TEXT_EDEFAULT.equals(getText());
			case DoorsPackage.DOORS_OBJECT__OUTGOING_LINKS:
				return outgoingLinks != null && !outgoingLinks.isEmpty();
			case DoorsPackage.DOORS_OBJECT__INCOMING_LINKS:
				return incomingLinks != null && !incomingLinks.isEmpty();
		}
		return super.eIsSet(featureID);
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> @generated
     */
    @Override
    public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case DoorsPackage.DOORS_OBJECT___IS_HEADING:
				return isHeading();
		}
		return super.eInvoke(operationID, arguments);
	}

} //DoorsObjectImpl
