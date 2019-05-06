/**
 */
package de.jpwinkler.daf.doorscsv.model.dbimpl;

import de.jpwinkler.daf.doorscsv.model.DoorsModule;
import de.jpwinkler.daf.doorscsv.model.DoorsObject;
import de.jpwinkler.daf.doorscsv.model.DoorsPackage;
import de.jpwinkler.daf.doorscsv.model.Link;
import de.jpwinkler.daf.doorscsv.model.ResolvedLink;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.dbimpl.DoorsObjectImpl#getObjectIdentifier <em>Object Identifier</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.dbimpl.DoorsObjectImpl#getObjectLevel <em>Object Level</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.dbimpl.DoorsObjectImpl#getObjectNumber <em>Object Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.dbimpl.DoorsObjectImpl#getAbsoluteNumber <em>Absolute Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.dbimpl.DoorsObjectImpl#getObjectText <em>Object Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.dbimpl.DoorsObjectImpl#getObjectShortText <em>Object Short Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.dbimpl.DoorsObjectImpl#getObjectHeading <em>Object Heading</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.dbimpl.DoorsObjectImpl#getText <em>Text</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.dbimpl.DoorsObjectImpl#getOutgoingLinks <em>Outgoing Links</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.dbimpl.DoorsObjectImpl#getIncomingLinks <em>Incoming Links</em>}</li>
 *   <li>{@link de.jpwinkler.daf.doorscsv.model.dbimpl.DoorsObjectImpl#getModule <em>Module</em>}</li>
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
	 * The cached value of the '{@link #getModule() <em>Module</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModule()
	 * @generated
	 * @ordered
	 */
	protected DoorsModule module;

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
		return DoorsPackage.Literals.DOORS_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getObjectIdentifier() {
		// TODO: implement this method to return the 'Object Identifier' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setObjectIdentifier(String newObjectIdentifier) {
		// TODO: implement this method to set the 'Object Identifier' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getObjectLevel() {
		// TODO: implement this method to return the 'Object Level' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setObjectLevel(int newObjectLevel) {
		// TODO: implement this method to set the 'Object Level' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getObjectNumber() {
		// TODO: implement this method to return the 'Object Number' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setObjectNumber(String newObjectNumber) {
		// TODO: implement this method to set the 'Object Number' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getAbsoluteNumber() {
		// TODO: implement this method to return the 'Absolute Number' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAbsoluteNumber(int newAbsoluteNumber) {
		// TODO: implement this method to set the 'Absolute Number' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getObjectText() {
		// TODO: implement this method to return the 'Object Text' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setObjectText(String newObjectText) {
		// TODO: implement this method to set the 'Object Text' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getObjectShortText() {
		// TODO: implement this method to return the 'Object Short Text' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setObjectShortText(String newObjectShortText) {
		// TODO: implement this method to set the 'Object Short Text' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getObjectHeading() {
		// TODO: implement this method to return the 'Object Heading' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setObjectHeading(String newObjectHeading) {
		// TODO: implement this method to set the 'Object Heading' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText() {
		// TODO: implement this method to return the 'Text' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setText(String newText) {
		// TODO: implement this method to set the 'Text' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Link> getOutgoingLinks() {
		if (outgoingLinks == null) {
			outgoingLinks = new EObjectContainmentWithInverseEList<Link>(Link.class, this, DoorsPackage.DOORS_OBJECT__OUTGOING_LINKS, DoorsPackage.LINK__SOURCE);
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
			incomingLinks = new EObjectWithInverseResolvingEList<ResolvedLink>(ResolvedLink.class, this, DoorsPackage.DOORS_OBJECT__INCOMING_LINKS, DoorsPackage.RESOLVED_LINK__TARGET);
		}
		return incomingLinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoorsModule getModule() {
		if (module != null && module.eIsProxy()) {
			InternalEObject oldModule = (InternalEObject)module;
			module = (DoorsModule)eResolveProxy(oldModule);
			if (module != oldModule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DoorsPackage.DOORS_OBJECT__MODULE, oldModule, module));
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
	public void setModule(DoorsModule newModule) {
		DoorsModule oldModule = module;
		module = newModule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DoorsPackage.DOORS_OBJECT__MODULE, oldModule, module));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isHeading() {
		return getObjectHeading() != null && !getObjectHeading().isEmpty();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
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
	 * <!-- end-user-doc -->
	 * @generated
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
	 * <!-- end-user-doc -->
	 * @generated
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
			case DoorsPackage.DOORS_OBJECT__MODULE:
				if (resolve) return getModule();
				return basicGetModule();
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
			case DoorsPackage.DOORS_OBJECT__MODULE:
				setModule((DoorsModule)newValue);
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
			case DoorsPackage.DOORS_OBJECT__MODULE:
				setModule((DoorsModule)null);
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
			case DoorsPackage.DOORS_OBJECT__MODULE:
				return module != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
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
