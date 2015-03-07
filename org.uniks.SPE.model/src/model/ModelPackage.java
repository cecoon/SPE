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
     * The number of structural features of the '<em>SPE Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT_FEATURE_COUNT = 3;

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
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_ATTRIBUTE__VALUE = 1;

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
     * The number of structural features of the '<em>SPE Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>SPE Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK_OPERATION_COUNT = 0;


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
     * Returns the meta object for the attribute '{@link model.SPEAttribute#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see model.SPEAttribute#getValue()
     * @see #getSPEAttribute()
     * @generated
     */
    EAttribute getSPEAttribute_Value();

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
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPE_ATTRIBUTE__VALUE = eINSTANCE.getSPEAttribute_Value();

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

    }

} //ModelPackage
