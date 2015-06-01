/**
 */
package de.jpwinkler.daf.dafcore.model.csv.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.jpwinkler.daf.dafcore.csv.DoorsModuleVisitor;
import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.CSVPackage;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dafcore.model.csv.ResolvedLink;
import de.jpwinkler.daf.dafcore.model.csv.UnresolvedLink;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CSVFactoryImpl extends EFactoryImpl implements CSVFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static CSVFactory init() {
        try {
            final CSVFactory theCSVFactory = (CSVFactory) EPackage.Registry.INSTANCE.getEFactory(CSVPackage.eNS_URI);
            if (theCSVFactory != null) {
                return theCSVFactory;
            }
        }
        catch (final Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new CSVFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CSVFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(final EClass eClass) {
        switch (eClass.getClassifierID()) {
        case CSVPackage.DOORS_MODULE: return createDoorsModule();
        case CSVPackage.DOORS_OBJECT: return createDoorsObject();
        case CSVPackage.STRING_TO_STRING_MAP: return (EObject)createStringToStringMap();
        case CSVPackage.RESOLVED_LINK: return createResolvedLink();
        case CSVPackage.UNRESOLVED_LINK: return createUnresolvedLink();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(final EDataType eDataType, final String initialValue) {
        switch (eDataType.getClassifierID()) {
        case CSVPackage.DOORS_MODULE_VISITOR:
            return createDoorsModuleVisitorFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(final EDataType eDataType, final Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case CSVPackage.DOORS_MODULE_VISITOR:
            return convertDoorsModuleVisitorToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DoorsModule createDoorsModule() {
        final DoorsModuleImpl doorsModule = new DoorsModuleImpl();
        return doorsModule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DoorsObject createDoorsObject() {
        final DoorsObjectImpl doorsObject = new DoorsObjectImpl();
        return doorsObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map.Entry<String, String> createStringToStringMap() {
        final StringToStringMapImpl stringToStringMap = new StringToStringMapImpl();
        return stringToStringMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ResolvedLink createResolvedLink() {
        final ResolvedLinkImpl resolvedLink = new ResolvedLinkImpl();
        return resolvedLink;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public UnresolvedLink createUnresolvedLink() {
        final UnresolvedLinkImpl unresolvedLink = new UnresolvedLinkImpl();
        return unresolvedLink;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoorsModuleVisitor createDoorsModuleVisitorFromString(final EDataType eDataType, final String initialValue) {
        return (DoorsModuleVisitor)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertDoorsModuleVisitorToString(final EDataType eDataType, final Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public CSVPackage getCSVPackage() {
        return (CSVPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static CSVPackage getPackage() {
        return CSVPackage.eINSTANCE;
    }

} // CSVFactoryImpl
