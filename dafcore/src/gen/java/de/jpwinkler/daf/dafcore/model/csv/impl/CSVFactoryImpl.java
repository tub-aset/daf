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
import de.jpwinkler.daf.dafcore.model.csv.*;
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
            CSVFactory theCSVFactory = (CSVFactory)EPackage.Registry.INSTANCE.getEFactory(CSVPackage.eNS_URI);
            if (theCSVFactory != null) {
                return theCSVFactory;
            }
        }
        catch (Exception exception) {
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
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case CSVPackage.DOORS_MODULE: return createDoorsModule();
            case CSVPackage.DOORS_OBJECT: return createDoorsObject();
            case CSVPackage.STRING_TO_STRING_MAP: return (EObject)createStringToStringMap();
            case CSVPackage.RESOLVED_LINK: return createResolvedLink();
            case CSVPackage.UNRESOLVED_LINK: return createUnresolvedLink();
            case CSVPackage.DOORS_TREE_NODE: return createDoorsTreeNode();
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
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case CSVPackage.DOORS_TREE_NODE_VISITOR:
                return createDoorsTreeNodeVisitorFromString(eDataType, initialValue);
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
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case CSVPackage.DOORS_TREE_NODE_VISITOR:
                return convertDoorsTreeNodeVisitorToString(eDataType, instanceValue);
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
        DoorsModuleImpl doorsModule = new DoorsModuleImpl();
        return doorsModule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DoorsObject createDoorsObject() {
        DoorsObjectImpl doorsObject = new DoorsObjectImpl();
        return doorsObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map.Entry<String, String> createStringToStringMap() {
        StringToStringMapImpl stringToStringMap = new StringToStringMapImpl();
        return stringToStringMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ResolvedLink createResolvedLink() {
        ResolvedLinkImpl resolvedLink = new ResolvedLinkImpl();
        return resolvedLink;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public UnresolvedLink createUnresolvedLink() {
        UnresolvedLinkImpl unresolvedLink = new UnresolvedLinkImpl();
        return unresolvedLink;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DoorsTreeNode createDoorsTreeNode() {
        DoorsTreeNodeImpl doorsTreeNode = new DoorsTreeNodeImpl();
        return doorsTreeNode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor createDoorsTreeNodeVisitorFromString(EDataType eDataType, String initialValue) {
        return (de.jpwinkler.daf.dafcore.csv.DoorsTreeNodeVisitor)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertDoorsTreeNodeVisitorToString(EDataType eDataType, Object instanceValue) {
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
