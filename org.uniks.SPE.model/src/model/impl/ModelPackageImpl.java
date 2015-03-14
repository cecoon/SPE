/**
 */
package model.impl;

import model.IHasMatchTag;
import model.IHasOperation;
import model.MatchTag;
import model.ModelFactory;
import model.ModelPackage;
import model.Operations;
import model.SPEAttribute;
import model.SPEGroup;
import model.SPELink;
import model.SPEObject;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelPackageImpl extends EPackageImpl implements ModelPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass speObjectEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass speAttributeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass speLinkEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass speGroupEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iHasMatchTagEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iHasOperationEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum matchTagEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum operationsEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see model.ModelPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ModelPackageImpl() {
        super(eNS_URI, ModelFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link ModelPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ModelPackage init() {
        if (isInited) return (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);

        // Obtain or create and register package
        ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ModelPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theModelPackage.createPackageContents();

        // Initialize created meta-data
        theModelPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theModelPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, theModelPackage);
        return theModelPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSPEObject() {
        return speObjectEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSPEObject_Name() {
        return (EAttribute)speObjectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSPEObject_Type() {
        return (EAttribute)speObjectEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSPEObject_Attributes() {
        return (EReference)speObjectEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSPEObject_OutboundLinks() {
        return (EReference)speObjectEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSPEObject_InboundLinks() {
        return (EReference)speObjectEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSPEAttribute() {
        return speAttributeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSPEAttribute_Name() {
        return (EAttribute)speAttributeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSPEAttribute_Operation() {
        return (EAttribute)speAttributeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSPELink() {
        return speLinkEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSPELink_Name() {
        return (EAttribute)speLinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSPELink_Source() {
        return (EReference)speLinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSPELink_Target() {
        return (EReference)speLinkEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSPEGroup() {
        return speGroupEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSPEGroup_Objects() {
        return (EReference)speGroupEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSPEGroup_Name() {
        return (EAttribute)speGroupEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSPEGroup_Links() {
        return (EReference)speGroupEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSPEGroup_SubGroups() {
        return (EReference)speGroupEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSPEGroup_Model() {
        return (EAttribute)speGroupEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIHasMatchTag() {
        return iHasMatchTagEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIHasMatchTag_Tag() {
        return (EAttribute)iHasMatchTagEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIHasOperation() {
        return iHasOperationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIHasOperation_Operation() {
        return (EAttribute)iHasOperationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getMatchTag() {
        return matchTagEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getOperations() {
        return operationsEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelFactory getModelFactory() {
        return (ModelFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        speObjectEClass = createEClass(SPE_OBJECT);
        createEAttribute(speObjectEClass, SPE_OBJECT__NAME);
        createEAttribute(speObjectEClass, SPE_OBJECT__TYPE);
        createEReference(speObjectEClass, SPE_OBJECT__ATTRIBUTES);
        createEReference(speObjectEClass, SPE_OBJECT__OUTBOUND_LINKS);
        createEReference(speObjectEClass, SPE_OBJECT__INBOUND_LINKS);

        speAttributeEClass = createEClass(SPE_ATTRIBUTE);
        createEAttribute(speAttributeEClass, SPE_ATTRIBUTE__NAME);
        createEAttribute(speAttributeEClass, SPE_ATTRIBUTE__OPERATION);

        speLinkEClass = createEClass(SPE_LINK);
        createEAttribute(speLinkEClass, SPE_LINK__NAME);
        createEReference(speLinkEClass, SPE_LINK__SOURCE);
        createEReference(speLinkEClass, SPE_LINK__TARGET);

        speGroupEClass = createEClass(SPE_GROUP);
        createEReference(speGroupEClass, SPE_GROUP__OBJECTS);
        createEAttribute(speGroupEClass, SPE_GROUP__NAME);
        createEReference(speGroupEClass, SPE_GROUP__LINKS);
        createEReference(speGroupEClass, SPE_GROUP__SUB_GROUPS);
        createEAttribute(speGroupEClass, SPE_GROUP__MODEL);

        iHasMatchTagEClass = createEClass(IHAS_MATCH_TAG);
        createEAttribute(iHasMatchTagEClass, IHAS_MATCH_TAG__TAG);

        iHasOperationEClass = createEClass(IHAS_OPERATION);
        createEAttribute(iHasOperationEClass, IHAS_OPERATION__OPERATION);

        // Create enums
        matchTagEEnum = createEEnum(MATCH_TAG);
        operationsEEnum = createEEnum(OPERATIONS);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        speObjectEClass.getESuperTypes().add(this.getIHasOperation());
        speObjectEClass.getESuperTypes().add(this.getIHasMatchTag());
        speLinkEClass.getESuperTypes().add(this.getIHasOperation());
        speLinkEClass.getESuperTypes().add(this.getIHasMatchTag());
        speGroupEClass.getESuperTypes().add(this.getIHasOperation());
        speGroupEClass.getESuperTypes().add(this.getIHasMatchTag());

        // Initialize classes, features, and operations; add parameters
        initEClass(speObjectEClass, SPEObject.class, "SPEObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSPEObject_Name(), ecorePackage.getEString(), "name", "this", 0, 1, SPEObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPEObject_Type(), ecorePackage.getEString(), "type", "Class", 0, 1, SPEObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSPEObject_Attributes(), this.getSPEAttribute(), null, "attributes", null, 0, -1, SPEObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSPEObject_OutboundLinks(), this.getSPELink(), this.getSPELink_Source(), "outboundLinks", null, 0, -1, SPEObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSPEObject_InboundLinks(), this.getSPELink(), this.getSPELink_Target(), "inboundLinks", null, 0, -1, SPEObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(speAttributeEClass, SPEAttribute.class, "SPEAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSPEAttribute_Name(), ecorePackage.getEString(), "name", "name", 0, 1, SPEAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPEAttribute_Operation(), ecorePackage.getEString(), "operation", "== value", 0, 1, SPEAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(speLinkEClass, SPELink.class, "SPELink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSPELink_Name(), ecorePackage.getEString(), "name", "has", 0, 1, SPELink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSPELink_Source(), this.getSPEObject(), this.getSPEObject_OutboundLinks(), "source", null, 0, 1, SPELink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSPELink_Target(), this.getSPEObject(), this.getSPEObject_InboundLinks(), "target", null, 0, 1, SPELink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(speGroupEClass, SPEGroup.class, "SPEGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSPEGroup_Objects(), this.getSPEObject(), null, "objects", null, 0, -1, SPEGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPEGroup_Name(), ecorePackage.getEString(), "name", null, 0, 1, SPEGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSPEGroup_Links(), this.getSPELink(), null, "links", null, 0, -1, SPEGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSPEGroup_SubGroups(), this.getSPEGroup(), null, "subGroups", null, 0, -1, SPEGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSPEGroup_Model(), ecorePackage.getEString(), "model", null, 0, 1, SPEGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iHasMatchTagEClass, IHasMatchTag.class, "IHasMatchTag", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIHasMatchTag_Tag(), this.getMatchTag(), "tag", "Default", 0, 1, IHasMatchTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iHasOperationEClass, IHasOperation.class, "IHasOperation", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIHasOperation_Operation(), this.getOperations(), "operation", "Nop", 0, 1, IHasOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(matchTagEEnum, MatchTag.class, "MatchTag");
        addEEnumLiteral(matchTagEEnum, MatchTag.DEFAULT);
        addEEnumLiteral(matchTagEEnum, MatchTag.NOT);
        addEEnumLiteral(matchTagEEnum, MatchTag.OPTIONAL);

        initEEnum(operationsEEnum, Operations.class, "Operations");
        addEEnumLiteral(operationsEEnum, Operations.NOP);
        addEEnumLiteral(operationsEEnum, Operations.CREATE);
        addEEnumLiteral(operationsEEnum, Operations.DELETE);

        // Create resource
        createResource(eNS_URI);
    }

} //ModelPackageImpl
