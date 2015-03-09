/**
 */
package model.impl;

import java.util.Collection;

import model.IHasMatchTag;
import model.MatchTag;
import model.ModelPackage;
import model.Operations;
import model.SPEAttribute;
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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SPE Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link model.impl.SPEObjectImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link model.impl.SPEObjectImpl#getTag <em>Tag</em>}</li>
 *   <li>{@link model.impl.SPEObjectImpl#getName <em>Name</em>}</li>
 *   <li>{@link model.impl.SPEObjectImpl#getType <em>Type</em>}</li>
 *   <li>{@link model.impl.SPEObjectImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link model.impl.SPEObjectImpl#getOutboundLinks <em>Outbound Links</em>}</li>
 *   <li>{@link model.impl.SPEObjectImpl#getInboundLinks <em>Inbound Links</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SPEObjectImpl extends MinimalEObjectImpl.Container implements SPEObject {
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
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = "this";

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
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final String TYPE_EDEFAULT = "Class";

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected String type = TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAttributes()
     * @generated
     * @ordered
     */
    protected EList<SPEAttribute> attributes;

    /**
     * The cached value of the '{@link #getOutboundLinks() <em>Outbound Links</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOutboundLinks()
     * @generated
     * @ordered
     */
    protected EList<SPELink> outboundLinks;

    /**
     * The cached value of the '{@link #getInboundLinks() <em>Inbound Links</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInboundLinks()
     * @generated
     * @ordered
     */
    protected EList<SPELink> inboundLinks;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SPEObjectImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.SPE_OBJECT;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SPE_OBJECT__OPERATION, oldOperation, operation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SPE_OBJECT__TAG, oldTag, tag));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SPE_OBJECT__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(String newType) {
        String oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SPE_OBJECT__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SPEAttribute> getAttributes() {
        if (attributes == null) {
            attributes = new EObjectContainmentEList<SPEAttribute>(SPEAttribute.class, this, ModelPackage.SPE_OBJECT__ATTRIBUTES);
        }
        return attributes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SPELink> getOutboundLinks() {
        if (outboundLinks == null) {
            outboundLinks = new EObjectWithInverseResolvingEList<SPELink>(SPELink.class, this, ModelPackage.SPE_OBJECT__OUTBOUND_LINKS, ModelPackage.SPE_LINK__SOURCE);
        }
        return outboundLinks;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<SPELink> getInboundLinks() {
        if (inboundLinks == null) {
            inboundLinks = new EObjectWithInverseResolvingEList<SPELink>(SPELink.class, this, ModelPackage.SPE_OBJECT__INBOUND_LINKS, ModelPackage.SPE_LINK__TARGET);
        }
        return inboundLinks;
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
            case ModelPackage.SPE_OBJECT__OUTBOUND_LINKS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutboundLinks()).basicAdd(otherEnd, msgs);
            case ModelPackage.SPE_OBJECT__INBOUND_LINKS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getInboundLinks()).basicAdd(otherEnd, msgs);
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
            case ModelPackage.SPE_OBJECT__ATTRIBUTES:
                return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
            case ModelPackage.SPE_OBJECT__OUTBOUND_LINKS:
                return ((InternalEList<?>)getOutboundLinks()).basicRemove(otherEnd, msgs);
            case ModelPackage.SPE_OBJECT__INBOUND_LINKS:
                return ((InternalEList<?>)getInboundLinks()).basicRemove(otherEnd, msgs);
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
            case ModelPackage.SPE_OBJECT__OPERATION:
                return getOperation();
            case ModelPackage.SPE_OBJECT__TAG:
                return getTag();
            case ModelPackage.SPE_OBJECT__NAME:
                return getName();
            case ModelPackage.SPE_OBJECT__TYPE:
                return getType();
            case ModelPackage.SPE_OBJECT__ATTRIBUTES:
                return getAttributes();
            case ModelPackage.SPE_OBJECT__OUTBOUND_LINKS:
                return getOutboundLinks();
            case ModelPackage.SPE_OBJECT__INBOUND_LINKS:
                return getInboundLinks();
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
            case ModelPackage.SPE_OBJECT__OPERATION:
                setOperation((Operations)newValue);
                return;
            case ModelPackage.SPE_OBJECT__TAG:
                setTag((MatchTag)newValue);
                return;
            case ModelPackage.SPE_OBJECT__NAME:
                setName((String)newValue);
                return;
            case ModelPackage.SPE_OBJECT__TYPE:
                setType((String)newValue);
                return;
            case ModelPackage.SPE_OBJECT__ATTRIBUTES:
                getAttributes().clear();
                getAttributes().addAll((Collection<? extends SPEAttribute>)newValue);
                return;
            case ModelPackage.SPE_OBJECT__OUTBOUND_LINKS:
                getOutboundLinks().clear();
                getOutboundLinks().addAll((Collection<? extends SPELink>)newValue);
                return;
            case ModelPackage.SPE_OBJECT__INBOUND_LINKS:
                getInboundLinks().clear();
                getInboundLinks().addAll((Collection<? extends SPELink>)newValue);
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
            case ModelPackage.SPE_OBJECT__OPERATION:
                setOperation(OPERATION_EDEFAULT);
                return;
            case ModelPackage.SPE_OBJECT__TAG:
                setTag(TAG_EDEFAULT);
                return;
            case ModelPackage.SPE_OBJECT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ModelPackage.SPE_OBJECT__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case ModelPackage.SPE_OBJECT__ATTRIBUTES:
                getAttributes().clear();
                return;
            case ModelPackage.SPE_OBJECT__OUTBOUND_LINKS:
                getOutboundLinks().clear();
                return;
            case ModelPackage.SPE_OBJECT__INBOUND_LINKS:
                getInboundLinks().clear();
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
            case ModelPackage.SPE_OBJECT__OPERATION:
                return operation != OPERATION_EDEFAULT;
            case ModelPackage.SPE_OBJECT__TAG:
                return tag != TAG_EDEFAULT;
            case ModelPackage.SPE_OBJECT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ModelPackage.SPE_OBJECT__TYPE:
                return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
            case ModelPackage.SPE_OBJECT__ATTRIBUTES:
                return attributes != null && !attributes.isEmpty();
            case ModelPackage.SPE_OBJECT__OUTBOUND_LINKS:
                return outboundLinks != null && !outboundLinks.isEmpty();
            case ModelPackage.SPE_OBJECT__INBOUND_LINKS:
                return inboundLinks != null && !inboundLinks.isEmpty();
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
                case ModelPackage.SPE_OBJECT__TAG: return ModelPackage.IHAS_MATCH_TAG__TAG;
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
                case ModelPackage.IHAS_MATCH_TAG__TAG: return ModelPackage.SPE_OBJECT__TAG;
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
        result.append(", type: ");
        result.append(type);
        result.append(')');
        return result.toString();
    }

} //SPEObjectImpl
