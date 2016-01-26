/**
 */
package de.jpwinkler.daf.fap5.model.codebeamer.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.jpwinkler.daf.dafcore.model.common.impl.ModelObjectImpl;
import de.jpwinkler.daf.dafcore.util.ECollectors;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.CodebeamerPackage;
import de.jpwinkler.daf.fap5.model.codebeamer.DoubleMetric;
import de.jpwinkler.daf.fap5.model.codebeamer.IntMetric;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;
import de.jpwinkler.daf.fap5.model.codebeamer.Metric;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Code Beamer Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.CodeBeamerModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.CodeBeamerModelImpl#isSpecified <em>Specified</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.CodeBeamerModelImpl#getSize <em>Size</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.CodeBeamerModelImpl#getIssues <em>Issues</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.CodeBeamerModelImpl#getVersionNumber <em>Version Number</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.CodeBeamerModelImpl#getMetrics <em>Metrics</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.CodeBeamerModelImpl#getPath <em>Path</em>}</li>
 *   <li>{@link de.jpwinkler.daf.fap5.model.codebeamer.impl.CodeBeamerModelImpl#getView <em>View</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CodeBeamerModelImpl extends ModelObjectImpl implements CodeBeamerModel {
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
     * The default value of the '{@link #isSpecified() <em>Specified</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSpecified()
     * @generated
     * @ordered
     */
    protected static final boolean SPECIFIED_EDEFAULT = false;
    /**
     * The cached value of the '{@link #isSpecified() <em>Specified</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSpecified()
     * @generated
     * @ordered
     */
    protected boolean specified = SPECIFIED_EDEFAULT;
    /**
     * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected static final float SIZE_EDEFAULT = 0.0F;
    /**
     * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSize()
     * @generated
     * @ordered
     */
    protected float size = SIZE_EDEFAULT;

    /**
     * The cached value of the '{@link #getIssues() <em>Issues</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIssues()
     * @generated
     * @ordered
     */
    protected EList<Issue> issues;

    /**
     * The default value of the '{@link #getVersionNumber() <em>Version Number</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersionNumber()
     * @generated
     * @ordered
     */
    protected static final String VERSION_NUMBER_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getVersionNumber() <em>Version Number</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVersionNumber()
     * @generated
     * @ordered
     */
    protected String versionNumber = VERSION_NUMBER_EDEFAULT;

    /**
     * The cached value of the '{@link #getMetrics() <em>Metrics</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMetrics()
     * @generated
     * @ordered
     */
    protected EList<Metric> metrics;

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
     * The default value of the '{@link #getView() <em>View</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getView()
     * @generated
     * @ordered
     */
    protected static final String VIEW_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getView() <em>View</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getView()
     * @generated
     * @ordered
     */
    protected String view = VIEW_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CodeBeamerModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CodebeamerPackage.Literals.CODE_BEAMER_MODEL;
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
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CodebeamerPackage.CODE_BEAMER_MODEL__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSpecified() {
        return specified;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSpecified(boolean newSpecified) {
        boolean oldSpecified = specified;
        specified = newSpecified;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CodebeamerPackage.CODE_BEAMER_MODEL__SPECIFIED, oldSpecified, specified));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public long getEstimatedRemainingWork() {
        return getIssues().stream().mapToLong(Issue::getSeverity).sum();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public EList<String> getIssueTypes() {
        return getIssues().stream().map(Issue::getIssueType).distinct().collect(ECollectors.toEList());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public EList<Issue> getIssues(final String issueType) {
        return getIssues().stream().filter(i -> i.getIssueType().equals(issueType)).collect(ECollectors.toEList());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public Double getDoubleMetric(final String name) {
        for (final Metric metric : getMetrics()) {
            if (metric instanceof DoubleMetric && metric.getName().equals(name)) {
                return ((DoubleMetric) metric).getValue();
            }
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public Integer getIntMetric(final String name) {
        for (final Metric metric : getMetrics()) {
            if (metric instanceof IntMetric && metric.getName().equals(name)) {
                return ((IntMetric) metric).getValue();
            }
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public String getFullName() {
        if (getName() == null) {
            return null;
        } else if (getPath() == null) {
            return getName();
        } else if (!getPath().endsWith("/")) {
            return getPath() + "/" + getName();
        } else {
            return getPath() + getName();
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public float getSize() {
        return size;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSize(float newSize) {
        float oldSize = size;
        size = newSize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CodebeamerPackage.CODE_BEAMER_MODEL__SIZE, oldSize, size));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<Issue> getIssues() {
        if (issues == null) {
            issues = new EObjectContainmentEList<Issue>(Issue.class, this, CodebeamerPackage.CODE_BEAMER_MODEL__ISSUES);
        }
        return issues;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getVersionNumber() {
        return versionNumber;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setVersionNumber(String newVersionNumber) {
        String oldVersionNumber = versionNumber;
        versionNumber = newVersionNumber;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CodebeamerPackage.CODE_BEAMER_MODEL__VERSION_NUMBER, oldVersionNumber, versionNumber));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<Metric> getMetrics() {
        if (metrics == null) {
            metrics = new EObjectContainmentEList<Metric>(Metric.class, this, CodebeamerPackage.CODE_BEAMER_MODEL__METRICS);
        }
        return metrics;
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
    public void setPath(String newPath) {
        String oldPath = path;
        path = newPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CodebeamerPackage.CODE_BEAMER_MODEL__PATH, oldPath, path));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getView() {
        return view;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setView(String newView) {
        String oldView = view;
        view = newView;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CodebeamerPackage.CODE_BEAMER_MODEL__VIEW, oldView, view));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CodebeamerPackage.CODE_BEAMER_MODEL__ISSUES:
                return ((InternalEList<?>)getIssues()).basicRemove(otherEnd, msgs);
            case CodebeamerPackage.CODE_BEAMER_MODEL__METRICS:
                return ((InternalEList<?>)getMetrics()).basicRemove(otherEnd, msgs);
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
            case CodebeamerPackage.CODE_BEAMER_MODEL__NAME:
                return getName();
            case CodebeamerPackage.CODE_BEAMER_MODEL__SPECIFIED:
                return isSpecified();
            case CodebeamerPackage.CODE_BEAMER_MODEL__SIZE:
                return getSize();
            case CodebeamerPackage.CODE_BEAMER_MODEL__ISSUES:
                return getIssues();
            case CodebeamerPackage.CODE_BEAMER_MODEL__VERSION_NUMBER:
                return getVersionNumber();
            case CodebeamerPackage.CODE_BEAMER_MODEL__METRICS:
                return getMetrics();
            case CodebeamerPackage.CODE_BEAMER_MODEL__PATH:
                return getPath();
            case CodebeamerPackage.CODE_BEAMER_MODEL__VIEW:
                return getView();
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
            case CodebeamerPackage.CODE_BEAMER_MODEL__NAME:
                setName((String)newValue);
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__SPECIFIED:
                setSpecified((Boolean)newValue);
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__SIZE:
                setSize((Float)newValue);
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__ISSUES:
                getIssues().clear();
                getIssues().addAll((Collection<? extends Issue>)newValue);
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__VERSION_NUMBER:
                setVersionNumber((String)newValue);
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__METRICS:
                getMetrics().clear();
                getMetrics().addAll((Collection<? extends Metric>)newValue);
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__PATH:
                setPath((String)newValue);
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__VIEW:
                setView((String)newValue);
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
            case CodebeamerPackage.CODE_BEAMER_MODEL__NAME:
                setName(NAME_EDEFAULT);
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__SPECIFIED:
                setSpecified(SPECIFIED_EDEFAULT);
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__SIZE:
                setSize(SIZE_EDEFAULT);
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__ISSUES:
                getIssues().clear();
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__VERSION_NUMBER:
                setVersionNumber(VERSION_NUMBER_EDEFAULT);
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__METRICS:
                getMetrics().clear();
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__PATH:
                setPath(PATH_EDEFAULT);
                return;
            case CodebeamerPackage.CODE_BEAMER_MODEL__VIEW:
                setView(VIEW_EDEFAULT);
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
            case CodebeamerPackage.CODE_BEAMER_MODEL__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case CodebeamerPackage.CODE_BEAMER_MODEL__SPECIFIED:
                return specified != SPECIFIED_EDEFAULT;
            case CodebeamerPackage.CODE_BEAMER_MODEL__SIZE:
                return size != SIZE_EDEFAULT;
            case CodebeamerPackage.CODE_BEAMER_MODEL__ISSUES:
                return issues != null && !issues.isEmpty();
            case CodebeamerPackage.CODE_BEAMER_MODEL__VERSION_NUMBER:
                return VERSION_NUMBER_EDEFAULT == null ? versionNumber != null : !VERSION_NUMBER_EDEFAULT.equals(versionNumber);
            case CodebeamerPackage.CODE_BEAMER_MODEL__METRICS:
                return metrics != null && !metrics.isEmpty();
            case CodebeamerPackage.CODE_BEAMER_MODEL__PATH:
                return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
            case CodebeamerPackage.CODE_BEAMER_MODEL__VIEW:
                return VIEW_EDEFAULT == null ? view != null : !VIEW_EDEFAULT.equals(view);
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
            case CodebeamerPackage.CODE_BEAMER_MODEL___GET_ESTIMATED_REMAINING_WORK:
                return getEstimatedRemainingWork();
            case CodebeamerPackage.CODE_BEAMER_MODEL___GET_ISSUE_TYPES:
                return getIssueTypes();
            case CodebeamerPackage.CODE_BEAMER_MODEL___GET_ISSUES__STRING:
                return getIssues((String)arguments.get(0));
            case CodebeamerPackage.CODE_BEAMER_MODEL___GET_DOUBLE_METRIC__STRING:
                return getDoubleMetric((String)arguments.get(0));
            case CodebeamerPackage.CODE_BEAMER_MODEL___GET_INT_METRIC__STRING:
                return getIntMetric((String)arguments.get(0));
            case CodebeamerPackage.CODE_BEAMER_MODEL___GET_FULL_NAME:
                return getFullName();
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
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(", specified: ");
        result.append(specified);
        result.append(", size: ");
        result.append(size);
        result.append(", versionNumber: ");
        result.append(versionNumber);
        result.append(", path: ");
        result.append(path);
        result.append(", view: ");
        result.append(view);
        result.append(')');
        return result.toString();
    }

} //CodeBeamerModelImpl
