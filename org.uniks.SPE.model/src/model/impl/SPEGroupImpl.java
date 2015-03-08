/**
 */
package model.impl;

import java.util.Collection;

import model.ModelPackage;
import model.SPEGroup;
import model.SPEObject;
import model.Tag;

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
 *   <li>{@link model.impl.SPEGroupImpl#getObjects <em>Objects</em>}</li>
 *   <li>{@link model.impl.SPEGroupImpl#getTag <em>Tag</em>}</li>
 *   <li>{@link model.impl.SPEGroupImpl#getSubGroups <em>Sub Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SPEGroupImpl extends MinimalEObjectImpl.Container implements SPEGroup {
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
     * The default value of the '{@link #getTag() <em>Tag</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTag()
     * @generated
     * @ordered
     */
    protected static final Tag TAG_EDEFAULT = Tag.DEFAULT;

    /**
     * The cached value of the '{@link #getTag() <em>Tag</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTag()
     * @generated
     * @ordered
     */
    protected Tag tag = TAG_EDEFAULT;

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
    public Tag getTag() {
        return tag;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTag(Tag newTag) {
        Tag oldTag = tag;
        tag = newTag == null ? TAG_EDEFAULT : newTag;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SPE_GROUP__TAG, oldTag, tag));
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
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.SPE_GROUP__OBJECTS:
                return ((InternalEList<?>)getObjects()).basicRemove(otherEnd, msgs);
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
            case ModelPackage.SPE_GROUP__OBJECTS:
                return getObjects();
            case ModelPackage.SPE_GROUP__TAG:
                return getTag();
            case ModelPackage.SPE_GROUP__SUB_GROUPS:
                return getSubGroups();
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
            case ModelPackage.SPE_GROUP__OBJECTS:
                getObjects().clear();
                getObjects().addAll((Collection<? extends SPEObject>)newValue);
                return;
            case ModelPackage.SPE_GROUP__TAG:
                setTag((Tag)newValue);
                return;
            case ModelPackage.SPE_GROUP__SUB_GROUPS:
                getSubGroups().clear();
                getSubGroups().addAll((Collection<? extends SPEGroup>)newValue);
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
            case ModelPackage.SPE_GROUP__OBJECTS:
                getObjects().clear();
                return;
            case ModelPackage.SPE_GROUP__TAG:
                setTag(TAG_EDEFAULT);
                return;
            case ModelPackage.SPE_GROUP__SUB_GROUPS:
                getSubGroups().clear();
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
            case ModelPackage.SPE_GROUP__OBJECTS:
                return objects != null && !objects.isEmpty();
            case ModelPackage.SPE_GROUP__TAG:
                return tag != TAG_EDEFAULT;
            case ModelPackage.SPE_GROUP__SUB_GROUPS:
                return subGroups != null && !subGroups.isEmpty();
        }
        return super.eIsSet(featureID);
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
        result.append(" (tag: ");
        result.append(tag);
        result.append(')');
        return result.toString();
    }

} //SPEGroupImpl
