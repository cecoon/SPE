/**
 */
package model.impl;

import java.util.Collection;
import model.IHasMatchTag;
import model.MatchTag;
import model.ModelPackage;
import model.Operations;
import model.SPEGroup;
import model.SPELink;
import model.SPEObject;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SPE Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link model.impl.SPEGroupImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link model.impl.SPEGroupImpl#getTag <em>Tag</em>}</li>
 *   <li>{@link model.impl.SPEGroupImpl#getObjects <em>Objects</em>}</li>
 *   <li>{@link model.impl.SPEGroupImpl#getName <em>Name</em>}</li>
 *   <li>{@link model.impl.SPEGroupImpl#getLinks <em>Links</em>}</li>
 *   <li>{@link model.impl.SPEGroupImpl#getSubGroups <em>Sub Groups</em>}</li>
 *   <li>{@link model.impl.SPEGroupImpl#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SPEGroupImpl extends MinimalEObjectImpl.Container implements SPEGroup {
    /**
     * The default value of the '{@link #getOperation() <em>Operation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperation()
     * @generated
     * @ordered
     */
    protected static final Operations OPERATION_EDEFAULT = Operations.NOP;

    /**
     * The cached value of the '{@link #getOperation() <em>Operation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperation()
     * @generated
     * @ordered
     */
    protected Operations operation = OPERATION_EDEFAULT;

    /**
     * The default value of the '{@link #getTag() <em>Tag</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTag()
     * @generated
     * @ordered
     */
    protected static final MatchTag TAG_EDEFAULT = MatchTag.DEFAULT;

    /**
     * The cached value of the '{@link #getTag() <em>Tag</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTag()
     * @generated
     * @ordered
     */
    protected MatchTag tag = TAG_EDEFAULT;

    /**
     * The cached value of the '{@link #getObjects() <em>Objects</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getObjects()
     * @generated
     * @ordered
     */
    protected EList<SPEObject> objects;

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
     * The cached value of the '{@link #getLinks() <em>Links</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLinks()
     * @generated
     * @ordered
     */
    protected EList<SPELink> links;

    /**
     * The cached value of the '{@link #getSubGroups() <em>Sub Groups</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSubGroups()
     * @generated
     * @ordered
     */
    protected EList<SPEGroup> subGroups;

    /**
     * The default value of the '{@link #getModel() <em>Model</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModel()
     * @generated
     * @ordered
     */
    protected static final String MODEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getModel() <em>Model</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModel()
     * @generated
     * @ordered
     */
    protected String model = MODEL_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SPEGroupImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.SPE_GROUP;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Operations getOperation() {
        return operation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOperation(Operations newOperation) {
        Operations oldOperation = operation;
        operation = newOperation == null ? OPERATION_EDEFAULT : newOperation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SPE_GROUP__OPERATION, oldOperation, operation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MatchTag getTag() {
        return tag;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTag(MatchTag newTag) {
        MatchTag oldTag = tag;
        tag = newTag == null ? TAG_EDEFAULT : newTag;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SPE_GROUP__TAG, oldTag, tag));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SPEObject> getObjects() {
        if (objects == null) {
            objects = new EObjectContainmentEList<SPEObject>(SPEObject.class, this, ModelPackage.SPE_GROUP__OBJECTS);
        }
        return objects;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SPE_GROUP__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SPELink> getLinks() {
        if (links == null) {
            links = new EObjectContainmentEList<SPELink>(SPELink.class, this, ModelPackage.SPE_GROUP__LINKS);
        }
        return links;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SPEGroup> getSubGroups() {
        if (subGroups == null) {
            subGroups = new EObjectContainmentEList<SPEGroup>(SPEGroup.class, this, ModelPackage.SPE_GROUP__SUB_GROUPS);
        }
        return subGroups;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getModel() {
        return model;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setModel(String newModel) {
        String oldModel = model;
        model = newModel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SPE_GROUP__MODEL, oldModel, model));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.SPE_GROUP__OBJECTS:
                return ((InternalEList<?>)getObjects()).basicRemove(otherEnd, msgs);
            case ModelPackage.SPE_GROUP__LINKS:
                return ((InternalEList<?>)getLinks()).basicRemove(otherEnd, msgs);
            case ModelPackage.SPE_GROUP__SUB_GROUPS:
                return ((InternalEList<?>)getSubGroups()).basicRemove(otherEnd, msgs);
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
            case ModelPackage.SPE_GROUP__OPERATION:
                return getOperation();
            case ModelPackage.SPE_GROUP__TAG:
                return getTag();
            case ModelPackage.SPE_GROUP__OBJECTS:
                return getObjects();
            case ModelPackage.SPE_GROUP__NAME:
                return getName();
            case ModelPackage.SPE_GROUP__LINKS:
                return getLinks();
            case ModelPackage.SPE_GROUP__SUB_GROUPS:
                return getSubGroups();
            case ModelPackage.SPE_GROUP__MODEL:
                return getModel();
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
            case ModelPackage.SPE_GROUP__OPERATION:
                setOperation((Operations)newValue);
                return;
            case ModelPackage.SPE_GROUP__TAG:
                setTag((MatchTag)newValue);
                return;
            case ModelPackage.SPE_GROUP__OBJECTS:
                getObjects().clear();
                getObjects().addAll((Collection<? extends SPEObject>)newValue);
                return;
            case ModelPackage.SPE_GROUP__NAME:
                setName((String)newValue);
                return;
            case ModelPackage.SPE_GROUP__LINKS:
                getLinks().clear();
                getLinks().addAll((Collection<? extends SPELink>)newValue);
                return;
            case ModelPackage.SPE_GROUP__SUB_GROUPS:
                getSubGroups().clear();
                getSubGroups().addAll((Collection<? extends SPEGroup>)newValue);
                return;
            case ModelPackage.SPE_GROUP__MODEL:
                setModel((String)newValue);
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
            case ModelPackage.SPE_GROUP__OPERATION:
                setOperation(OPERATION_EDEFAULT);
                return;
            case ModelPackage.SPE_GROUP__TAG:
                setTag(TAG_EDEFAULT);
                return;
            case ModelPackage.SPE_GROUP__OBJECTS:
                getObjects().clear();
                return;
            case ModelPackage.SPE_GROUP__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ModelPackage.SPE_GROUP__LINKS:
                getLinks().clear();
                return;
            case ModelPackage.SPE_GROUP__SUB_GROUPS:
                getSubGroups().clear();
                return;
            case ModelPackage.SPE_GROUP__MODEL:
                setModel(MODEL_EDEFAULT);
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
            case ModelPackage.SPE_GROUP__OPERATION:
                return operation != OPERATION_EDEFAULT;
            case ModelPackage.SPE_GROUP__TAG:
                return tag != TAG_EDEFAULT;
            case ModelPackage.SPE_GROUP__OBJECTS:
                return objects != null && !objects.isEmpty();
            case ModelPackage.SPE_GROUP__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ModelPackage.SPE_GROUP__LINKS:
                return links != null && !links.isEmpty();
            case ModelPackage.SPE_GROUP__SUB_GROUPS:
                return subGroups != null && !subGroups.isEmpty();
            case ModelPackage.SPE_GROUP__MODEL:
                return MODEL_EDEFAULT == null ? model != null : !MODEL_EDEFAULT.equals(model);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == IHasMatchTag.class) {
            switch (derivedFeatureID) {
                case ModelPackage.SPE_GROUP__TAG: return ModelPackage.IHAS_MATCH_TAG__TAG;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == IHasMatchTag.class) {
            switch (baseFeatureID) {
                case ModelPackage.IHAS_MATCH_TAG__TAG: return ModelPackage.SPE_GROUP__TAG;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (operation: ");
        result.append(operation);
        result.append(", tag: ");
        result.append(tag);
        result.append(", name: ");
        result.append(name);
        result.append(", model: ");
        result.append(model);
        result.append(')');
        return result.toString();
    }

} //SPEGroupImpl
