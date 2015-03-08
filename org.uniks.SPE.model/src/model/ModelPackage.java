/**
 */
package model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
     * The meta object id for the '{@link model.IHasOperation <em>IHas Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.IHasOperation
     * @see model.impl.ModelPackageImpl#getIHasOperation()
     * @generated
     */
    int IHAS_OPERATION = 5;

    /**
     * The feature id for the '<em><b>Operation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IHAS_OPERATION__OPERATION = 0;

    /**
     * The number of structural features of the '<em>IHas Operation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IHAS_OPERATION_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>IHas Operation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IHAS_OPERATION_OPERATION_COUNT = 0;

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
     * The feature id for the '<em><b>Operation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT__OPERATION = IHAS_OPERATION__OPERATION;

    /**
     * The feature id for the '<em><b>Tag</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT__TAG = IHAS_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT__NAME = IHAS_OPERATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT__TYPE = IHAS_OPERATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT__ATTRIBUTES = IHAS_OPERATION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Outbound Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT__OUTBOUND_LINKS = IHAS_OPERATION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Inbound Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT__INBOUND_LINKS = IHAS_OPERATION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>SPE Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT_FEATURE_COUNT = IHAS_OPERATION_FEATURE_COUNT + 6;

    /**
     * The number of operations of the '<em>SPE Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_OBJECT_OPERATION_COUNT = IHAS_OPERATION_OPERATION_COUNT + 0;

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
     * The feature id for the '<em><b>Operation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK__OPERATION = IHAS_OPERATION__OPERATION;

    /**
     * The feature id for the '<em><b>Tag</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK__TAG = IHAS_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK__NAME = IHAS_OPERATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK__SOURCE = IHAS_OPERATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK__TARGET = IHAS_OPERATION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>SPE Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK_FEATURE_COUNT = IHAS_OPERATION_FEATURE_COUNT + 4;

    /**
     * The number of operations of the '<em>SPE Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_LINK_OPERATION_COUNT = IHAS_OPERATION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link model.impl.SPEGroupImpl <em>SPE Group</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.impl.SPEGroupImpl
     * @see model.impl.ModelPackageImpl#getSPEGroup()
     * @generated
     */
    int SPE_GROUP = 3;

    /**
     * The feature id for the '<em><b>Operation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_GROUP__OPERATION = IHAS_OPERATION__OPERATION;

    /**
     * The feature id for the '<em><b>Tag</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_GROUP__TAG = IHAS_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Objects</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_GROUP__OBJECTS = IHAS_OPERATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Sub Groups</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_GROUP__SUB_GROUPS = IHAS_OPERATION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>SPE Group</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_GROUP_FEATURE_COUNT = IHAS_OPERATION_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>SPE Group</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SPE_GROUP_OPERATION_COUNT = IHAS_OPERATION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link model.IHasMatchTag <em>IHas Match Tag</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.IHasMatchTag
     * @see model.impl.ModelPackageImpl#getIHasMatchTag()
     * @generated
     */
    int IHAS_MATCH_TAG = 4;

    /**
     * The feature id for the '<em><b>Tag</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IHAS_MATCH_TAG__TAG = 0;

    /**
     * The number of structural features of the '<em>IHas Match Tag</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IHAS_MATCH_TAG_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>IHas Match Tag</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IHAS_MATCH_TAG_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link model.MatchTag <em>Match Tag</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.MatchTag
     * @see model.impl.ModelPackageImpl#getMatchTag()
     * @generated
     */
    int MATCH_TAG = 6;

    /**
     * The meta object id for the '{@link model.Operations <em>Operations</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see model.Operations
     * @see model.impl.ModelPackageImpl#getOperations()
     * @generated
     */
    int OPERATIONS = 7;


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
     * Returns the meta object for the attribute '{@link model.SPEObject#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see model.SPEObject#getType()
     * @see #getSPEObject()
     * @generated
     */
    EAttribute getSPEObject_Type();

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
     * Returns the meta object for class '{@link model.SPEGroup <em>SPE Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>SPE Group</em>'.
     * @see model.SPEGroup
     * @generated
     */
    EClass getSPEGroup();

    /**
     * Returns the meta object for the containment reference list '{@link model.SPEGroup#getObjects <em>Objects</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Objects</em>'.
     * @see model.SPEGroup#getObjects()
     * @see #getSPEGroup()
     * @generated
     */
    EReference getSPEGroup_Objects();

    /**
     * Returns the meta object for the containment reference list '{@link model.SPEGroup#getSubGroups <em>Sub Groups</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Sub Groups</em>'.
     * @see model.SPEGroup#getSubGroups()
     * @see #getSPEGroup()
     * @generated
     */
    EReference getSPEGroup_SubGroups();

    /**
     * Returns the meta object for class '{@link model.IHasMatchTag <em>IHas Match Tag</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IHas Match Tag</em>'.
     * @see model.IHasMatchTag
     * @generated
     */
    EClass getIHasMatchTag();

    /**
     * Returns the meta object for the attribute '{@link model.IHasMatchTag#getTag <em>Tag</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Tag</em>'.
     * @see model.IHasMatchTag#getTag()
     * @see #getIHasMatchTag()
     * @generated
     */
    EAttribute getIHasMatchTag_Tag();

    /**
     * Returns the meta object for class '{@link model.IHasOperation <em>IHas Operation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IHas Operation</em>'.
     * @see model.IHasOperation
     * @generated
     */
    EClass getIHasOperation();

    /**
     * Returns the meta object for the attribute '{@link model.IHasOperation#getOperation <em>Operation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Operation</em>'.
     * @see model.IHasOperation#getOperation()
     * @see #getIHasOperation()
     * @generated
     */
    EAttribute getIHasOperation_Operation();

    /**
     * Returns the meta object for enum '{@link model.MatchTag <em>Match Tag</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Match Tag</em>'.
     * @see model.MatchTag
     * @generated
     */
    EEnum getMatchTag();

    /**
     * Returns the meta object for enum '{@link model.Operations <em>Operations</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Operations</em>'.
     * @see model.Operations
     * @generated
     */
    EEnum getOperations();

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
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SPE_OBJECT__TYPE = eINSTANCE.getSPEObject_Type();

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
         * The meta object literal for the '{@link model.impl.SPEGroupImpl <em>SPE Group</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see model.impl.SPEGroupImpl
         * @see model.impl.ModelPackageImpl#getSPEGroup()
         * @generated
         */
        EClass SPE_GROUP = eINSTANCE.getSPEGroup();

        /**
         * The meta object literal for the '<em><b>Objects</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SPE_GROUP__OBJECTS = eINSTANCE.getSPEGroup_Objects();

        /**
         * The meta object literal for the '<em><b>Sub Groups</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SPE_GROUP__SUB_GROUPS = eINSTANCE.getSPEGroup_SubGroups();

        /**
         * The meta object literal for the '{@link model.IHasMatchTag <em>IHas Match Tag</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see model.IHasMatchTag
         * @see model.impl.ModelPackageImpl#getIHasMatchTag()
         * @generated
         */
        EClass IHAS_MATCH_TAG = eINSTANCE.getIHasMatchTag();

        /**
         * The meta object literal for the '<em><b>Tag</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute IHAS_MATCH_TAG__TAG = eINSTANCE.getIHasMatchTag_Tag();

        /**
         * The meta object literal for the '{@link model.IHasOperation <em>IHas Operation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see model.IHasOperation
         * @see model.impl.ModelPackageImpl#getIHasOperation()
         * @generated
         */
        EClass IHAS_OPERATION = eINSTANCE.getIHasOperation();

        /**
         * The meta object literal for the '<em><b>Operation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute IHAS_OPERATION__OPERATION = eINSTANCE.getIHasOperation_Operation();

        /**
         * The meta object literal for the '{@link model.MatchTag <em>Match Tag</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see model.MatchTag
         * @see model.impl.ModelPackageImpl#getMatchTag()
         * @generated
         */
        EEnum MATCH_TAG = eINSTANCE.getMatchTag();

        /**
         * The meta object literal for the '{@link model.Operations <em>Operations</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see model.Operations
         * @see model.impl.ModelPackageImpl#getOperations()
         * @generated
         */
        EEnum OPERATIONS = eINSTANCE.getOperations();

    }

} //ModelPackage
