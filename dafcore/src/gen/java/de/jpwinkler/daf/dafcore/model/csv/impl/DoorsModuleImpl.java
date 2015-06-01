/**
 */
package de.jpwinkler.daf.dafcore.model.csv.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.dafcore.csv.DoorsModuleVisitor;
import de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl;
import de.jpwinkler.daf.dafcore.model.csv.CSVPackage;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Doors Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsModuleImpl#getObjects <em>Objects</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsModuleImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsModuleImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsModuleImpl#getPath <em>Path</em>}</li>
 *   <li>{@link de.jpwinkler.daf.dafcore.model.csv.impl.DoorsModuleImpl#getUrl <em>Url</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DoorsModuleImpl extends ModelObjectImpl implements DoorsModule {
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
     * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributes()
     * @generated
     * @ordered
     */
    protected EMap<String, String> attributes;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPath()
     * @generated
     * @ordered
     */
    protected static final String PATH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPath()
     * @generated
     * @ordered
     */
    protected String path = PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUrl()
     * @generated
     * @ordered
     */
    protected static final String URL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUrl()
     * @generated
     * @ordered
     */
    protected String url = URL_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DoorsModuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CSVPackage.Literals.DOORS_MODULE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<DoorsObject> getObjects() {
        if (objects == null) {
            objects = new EObjectContainmentEList<DoorsObject>(DoorsObject.class, this, CSVPackage.DOORS_MODULE__OBJECTS);
        }
        return objects;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EMap<String, String> getAttributes() {
        if (attributes == null) {
            attributes = new EcoreEMap<String,String>(CSVPackage.Literals.STRING_TO_STRING_MAP, StringToStringMapImpl.class, this, CSVPackage.DOORS_MODULE__ATTRIBUTES);
        }
        return attributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setName(final String newName) {
        final String oldName = name;
        name = newName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_MODULE__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setPath(final String newPath) {
        final String oldPath = path;
        path = newPath;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_MODULE__PATH, oldPath, path));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getUrl() {
        return url;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setUrl(final String newUrl) {
        final String oldUrl = url;
        url = newUrl;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, CSVPackage.DOORS_MODULE__URL, oldUrl, url));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void accept(final DoorsModuleVisitor visitor) {
        for (final DoorsObject object : getObjects()) {
            object.accept(visitor);
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID, final NotificationChain msgs) {
        switch (featureID) {
        case CSVPackage.DOORS_MODULE__OBJECTS:
            return ((InternalEList<?>)getObjects()).basicRemove(otherEnd, msgs);
        case CSVPackage.DOORS_MODULE__ATTRIBUTES:
            return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case CSVPackage.DOORS_MODULE__OBJECTS:
            return getObjects();
        case CSVPackage.DOORS_MODULE__ATTRIBUTES:
            if (coreType) {
                return getAttributes();
            } else {
                return getAttributes().map();
            }
        case CSVPackage.DOORS_MODULE__NAME:
            return getName();
        case CSVPackage.DOORS_MODULE__PATH:
            return getPath();
        case CSVPackage.DOORS_MODULE__URL:
            return getUrl();
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
        case CSVPackage.DOORS_MODULE__OBJECTS:
            getObjects().clear();
            getObjects().addAll((Collection<? extends DoorsObject>)newValue);
            return;
        case CSVPackage.DOORS_MODULE__ATTRIBUTES:
            ((EStructuralFeature.Setting)getAttributes()).set(newValue);
            return;
        case CSVPackage.DOORS_MODULE__NAME:
            setName((String)newValue);
            return;
        case CSVPackage.DOORS_MODULE__PATH:
            setPath((String)newValue);
            return;
        case CSVPackage.DOORS_MODULE__URL:
            setUrl((String)newValue);
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
        case CSVPackage.DOORS_MODULE__OBJECTS:
            getObjects().clear();
            return;
        case CSVPackage.DOORS_MODULE__ATTRIBUTES:
            getAttributes().clear();
            return;
        case CSVPackage.DOORS_MODULE__NAME:
            setName(NAME_EDEFAULT);
            return;
        case CSVPackage.DOORS_MODULE__PATH:
            setPath(PATH_EDEFAULT);
            return;
        case CSVPackage.DOORS_MODULE__URL:
            setUrl(URL_EDEFAULT);
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
        case CSVPackage.DOORS_MODULE__OBJECTS:
            return objects != null && !objects.isEmpty();
        case CSVPackage.DOORS_MODULE__ATTRIBUTES:
            return attributes != null && !attributes.isEmpty();
        case CSVPackage.DOORS_MODULE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case CSVPackage.DOORS_MODULE__PATH:
            return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
        case CSVPackage.DOORS_MODULE__URL:
            return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
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
        case CSVPackage.DOORS_MODULE___ACCEPT__DOORSMODULEVISITOR:
            accept((DoorsModuleVisitor)arguments.get(0));
            return null;
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
        result.append(" (name: ");
        result.append(name);
        result.append(", path: ");
        result.append(path);
        result.append(", url: ");
        result.append(url);
        result.append(')');
        return result.toString();
    }

} //DoorsModuleImpl
