/**
 */
package model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "model";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.example.org/model";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "model";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ModelPackage eINSTANCE = model.impl.ModelPackageImpl.init();

    /**
     * The meta object id for the '{@link model.impl.SPEObjectImpl <em>SPE Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.impl.SPEObjectImpl
     * @see model.impl.ModelPackageImpl#getSPEObject()
     * @generated
     */
    int SPE_OBJECT = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT__NAME = 0;

    /**
     * The feature id for the '<em><b>Class</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT__CLASS = 1;

    /**
     * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT__ATTRIBUTES = 2;

    /**
     * The feature id for the '<em><b>Outbound Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT__OUTBOUND_LINKS = 3;

    /**
     * The feature id for the '<em><b>Inbound Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT__INBOUND_LINKS = 4;

    /**
     * The number of structural features of the '<em>SPE Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT_FEATURE_COUNT = 5;

    /**
     * The number of operations of the '<em>SPE Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link model.impl.SPEAttributeImpl <em>SPE Attribute</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.impl.SPEAttributeImpl
     * @see model.impl.ModelPackageImpl#getSPEAttribute()
     * @generated
     */
    int SPE_ATTRIBUTE = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_ATTRIBUTE__NAME = 0;

    /**
     * The feature id for the '<em><b>Operation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_ATTRIBUTE__OPERATION = 1;

    /**
     * The number of structural features of the '<em>SPE Attribute</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_ATTRIBUTE_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>SPE Attribute</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_ATTRIBUTE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link model.impl.SPELinkImpl <em>SPE Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.impl.SPELinkImpl
     * @see model.impl.ModelPackageImpl#getSPELink()
     * @generated
     */
    int SPE_LINK = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK__NAME = 0;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK__SOURCE = 1;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK__TARGET = 2;

    /**
     * The number of structural features of the '<em>SPE Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>SPE Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link model.impl.SPEOptionalObjectImpl <em>SPE Optional Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.impl.SPEOptionalObjectImpl
     * @see model.impl.ModelPackageImpl#getSPEOptionalObject()
     * @generated
     */
    int SPE_OPTIONAL_OBJECT = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OPTIONAL_OBJECT__NAME = SPE_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Class</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OPTIONAL_OBJECT__CLASS = SPE_OBJECT__CLASS;

    /**
     * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OPTIONAL_OBJECT__ATTRIBUTES = SPE_OBJECT__ATTRIBUTES;

    /**
     * The feature id for the '<em><b>Outbound Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OPTIONAL_OBJECT__OUTBOUND_LINKS = SPE_OBJECT__OUTBOUND_LINKS;

    /**
     * The feature id for the '<em><b>Inbound Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OPTIONAL_OBJECT__INBOUND_LINKS = SPE_OBJECT__INBOUND_LINKS;

    /**
     * The number of structural features of the '<em>SPE Optional Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OPTIONAL_OBJECT_FEATURE_COUNT = SPE_OBJECT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>SPE Optional Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OPTIONAL_OBJECT_OPERATION_COUNT = SPE_OBJECT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link model.impl.SPENotObjectImpl <em>SPE Not Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.impl.SPENotObjectImpl
     * @see model.impl.ModelPackageImpl#getSPENotObject()
     * @generated
     */
    int SPE_NOT_OBJECT = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_NOT_OBJECT__NAME = SPE_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Class</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_NOT_OBJECT__CLASS = SPE_OBJECT__CLASS;

    /**
     * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_NOT_OBJECT__ATTRIBUTES = SPE_OBJECT__ATTRIBUTES;

    /**
     * The feature id for the '<em><b>Outbound Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_NOT_OBJECT__OUTBOUND_LINKS = SPE_OBJECT__OUTBOUND_LINKS;

    /**
     * The feature id for the '<em><b>Inbound Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_NOT_OBJECT__INBOUND_LINKS = SPE_OBJECT__INBOUND_LINKS;

    /**
     * The number of structural features of the '<em>SPE Not Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_NOT_OBJECT_FEATURE_COUNT = SPE_OBJECT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>SPE Not Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_NOT_OBJECT_OPERATION_COUNT = SPE_OBJECT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link model.impl.SPENotLinkImpl <em>SPE Not Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.impl.SPENotLinkImpl
     * @see model.impl.ModelPackageImpl#getSPENotLink()
     * @generated
     */
    int SPE_NOT_LINK = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_NOT_LINK__NAME = SPE_LINK__NAME;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_NOT_LINK__SOURCE = SPE_LINK__SOURCE;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_NOT_LINK__TARGET = SPE_LINK__TARGET;

    /**
     * The number of structural features of the '<em>SPE Not Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_NOT_LINK_FEATURE_COUNT = SPE_LINK_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>SPE Not Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_NOT_LINK_OPERATION_COUNT = SPE_LINK_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link model.impl.SPEOptionalLinkImpl <em>SPE Optional Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.impl.SPEOptionalLinkImpl
     * @see model.impl.ModelPackageImpl#getSPEOptionalLink()
     * @generated
     */
    int SPE_OPTIONAL_LINK = 6;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OPTIONAL_LINK__NAME = SPE_LINK__NAME;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OPTIONAL_LINK__SOURCE = SPE_LINK__SOURCE;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OPTIONAL_LINK__TARGET = SPE_LINK__TARGET;

    /**
     * The number of structural features of the '<em>SPE Optional Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OPTIONAL_LINK_FEATURE_COUNT = SPE_LINK_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>SPE Optional Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OPTIONAL_LINK_OPERATION_COUNT = SPE_LINK_OPERATION_COUNT + 0;


    /**
     * Returns the meta object for class '{@link model.SPEObject <em>SPE Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>SPE Object</em>'.
     * @see model.SPEObject
     * @generated
     */
    EClass getSPEObject();

    /**
     * Returns the meta object for the attribute '{@link model.SPEObject#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see model.SPEObject#getName()
     * @see #getSPEObject()
     * @generated
     */
    EAttribute getSPEObject_Name();

    /**
     * Returns the meta object for the attribute '{@link model.SPEObject#getClass_ <em>Class</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Class</em>'.
     * @see model.SPEObject#getClass_()
     * @see #getSPEObject()
     * @generated
     */
    EAttribute getSPEObject_Class();

    /**
     * Returns the meta object for the containment reference list '{@link model.SPEObject#getAttributes <em>Attributes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Attributes</em>'.
     * @see model.SPEObject#getAttributes()
     * @see #getSPEObject()
     * @generated
     */
    EReference getSPEObject_Attributes();

    /**
     * Returns the meta object for the reference list '{@link model.SPEObject#getOutboundLinks <em>Outbound Links</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Outbound Links</em>'.
     * @see model.SPEObject#getOutboundLinks()
     * @see #getSPEObject()
     * @generated
     */
    EReference getSPEObject_OutboundLinks();

    /**
     * Returns the meta object for the reference list '{@link model.SPEObject#getInboundLinks <em>Inbound Links</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Inbound Links</em>'.
     * @see model.SPEObject#getInboundLinks()
     * @see #getSPEObject()
     * @generated
     */
    EReference getSPEObject_InboundLinks();

    /**
     * Returns the meta object for class '{@link model.SPEAttribute <em>SPE Attribute</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>SPE Attribute</em>'.
     * @see model.SPEAttribute
     * @generated
     */
    EClass getSPEAttribute();

    /**
     * Returns the meta object for the attribute '{@link model.SPEAttribute#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see model.SPEAttribute#getName()
     * @see #getSPEAttribute()
     * @generated
     */
    EAttribute getSPEAttribute_Name();

    /**
     * Returns the meta object for the attribute '{@link model.SPEAttribute#getOperation <em>Operation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Operation</em>'.
     * @see model.SPEAttribute#getOperation()
     * @see #getSPEAttribute()
     * @generated
     */
    EAttribute getSPEAttribute_Operation();

    /**
     * Returns the meta object for class '{@link model.SPELink <em>SPE Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>SPE Link</em>'.
     * @see model.SPELink
     * @generated
     */
    EClass getSPELink();

    /**
     * Returns the meta object for the attribute '{@link model.SPELink#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see model.SPELink#getName()
     * @see #getSPELink()
     * @generated
     */
    EAttribute getSPELink_Name();

    /**
     * Returns the meta object for the reference '{@link model.SPELink#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see model.SPELink#getSource()
     * @see #getSPELink()
     * @generated
     */
    EReference getSPELink_Source();

    /**
     * Returns the meta object for the reference '{@link model.SPELink#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see model.SPELink#getTarget()
     * @see #getSPELink()
     * @generated
     */
    EReference getSPELink_Target();

    /**
     * Returns the meta object for class '{@link model.SPEOptionalObject <em>SPE Optional Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>SPE Optional Object</em>'.
     * @see model.SPEOptionalObject
     * @generated
     */
    EClass getSPEOptionalObject();

    /**
     * Returns the meta object for class '{@link model.SPENotObject <em>SPE Not Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>SPE Not Object</em>'.
     * @see model.SPENotObject
     * @generated
     */
    EClass getSPENotObject();

    /**
     * Returns the meta object for class '{@link model.SPENotLink <em>SPE Not Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>SPE Not Link</em>'.
     * @see model.SPENotLink
     * @generated
     */
    EClass getSPENotLink();

    /**
     * Returns the meta object for class '{@link model.SPEOptionalLink <em>SPE Optional Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>SPE Optional Link</em>'.
     * @see model.SPEOptionalLink
     * @generated
     */
    EClass getSPEOptionalLink();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ModelFactory getModelFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each operation of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link model.impl.SPEObjectImpl <em>SPE Object</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see model.impl.SPEObjectImpl
         * @see model.impl.ModelPackageImpl#getSPEObject()
         * @generated
         */
        EClass SPE_OBJECT = eINSTANCE.getSPEObject();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPE_OBJECT__NAME = eINSTANCE.getSPEObject_Name();

        /**
         * The meta object literal for the '<em><b>Class</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPE_OBJECT__CLASS = eINSTANCE.getSPEObject_Class();

        /**
         * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SPE_OBJECT__ATTRIBUTES = eINSTANCE.getSPEObject_Attributes();

        /**
         * The meta object literal for the '<em><b>Outbound Links</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SPE_OBJECT__OUTBOUND_LINKS = eINSTANCE.getSPEObject_OutboundLinks();

        /**
         * The meta object literal for the '<em><b>Inbound Links</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SPE_OBJECT__INBOUND_LINKS = eINSTANCE.getSPEObject_InboundLinks();

        /**
         * The meta object literal for the '{@link model.impl.SPEAttributeImpl <em>SPE Attribute</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see model.impl.SPEAttributeImpl
         * @see model.impl.ModelPackageImpl#getSPEAttribute()
         * @generated
         */
        EClass SPE_ATTRIBUTE = eINSTANCE.getSPEAttribute();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPE_ATTRIBUTE__NAME = eINSTANCE.getSPEAttribute_Name();

        /**
         * The meta object literal for the '<em><b>Operation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPE_ATTRIBUTE__OPERATION = eINSTANCE.getSPEAttribute_Operation();

        /**
         * The meta object literal for the '{@link model.impl.SPELinkImpl <em>SPE Link</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see model.impl.SPELinkImpl
         * @see model.impl.ModelPackageImpl#getSPELink()
         * @generated
         */
        EClass SPE_LINK = eINSTANCE.getSPELink();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPE_LINK__NAME = eINSTANCE.getSPELink_Name();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SPE_LINK__SOURCE = eINSTANCE.getSPELink_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SPE_LINK__TARGET = eINSTANCE.getSPELink_Target();

        /**
         * The meta object literal for the '{@link model.impl.SPEOptionalObjectImpl <em>SPE Optional Object</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see model.impl.SPEOptionalObjectImpl
         * @see model.impl.ModelPackageImpl#getSPEOptionalObject()
         * @generated
         */
        EClass SPE_OPTIONAL_OBJECT = eINSTANCE.getSPEOptionalObject();

        /**
         * The meta object literal for the '{@link model.impl.SPENotObjectImpl <em>SPE Not Object</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see model.impl.SPENotObjectImpl
         * @see model.impl.ModelPackageImpl#getSPENotObject()
         * @generated
         */
        EClass SPE_NOT_OBJECT = eINSTANCE.getSPENotObject();

        /**
         * The meta object literal for the '{@link model.impl.SPENotLinkImpl <em>SPE Not Link</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see model.impl.SPENotLinkImpl
         * @see model.impl.ModelPackageImpl#getSPENotLink()
         * @generated
         */
        EClass SPE_NOT_LINK = eINSTANCE.getSPENotLink();

        /**
         * The meta object literal for the '{@link model.impl.SPEOptionalLinkImpl <em>SPE Optional Link</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see model.impl.SPEOptionalLinkImpl
         * @see model.impl.ModelPackageImpl#getSPEOptionalLink()
         * @generated
         */
        EClass SPE_OPTIONAL_LINK = eINSTANCE.getSPEOptionalLink();

    }

} //ModelPackage
