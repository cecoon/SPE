/**
 */
package model.util;

import model.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see model.ModelPackage
 * @generated
 */
public class ModelSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ModelPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelSwitch() {
        if (modelPackage == null) {
            modelPackage = ModelPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case ModelPackage.SPE_OBJECT: {
                SPEObject speObject = (SPEObject)theEObject;
                T result = caseSPEObject(speObject);
                if (result == null) result = caseIHasOperation(speObject);
                if (result == null) result = caseIHasMatchTag(speObject);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.SPE_ATTRIBUTE: {
                SPEAttribute speAttribute = (SPEAttribute)theEObject;
                T result = caseSPEAttribute(speAttribute);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.SPE_LINK: {
                SPELink speLink = (SPELink)theEObject;
                T result = caseSPELink(speLink);
                if (result == null) result = caseIHasOperation(speLink);
                if (result == null) result = caseIHasMatchTag(speLink);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.SPE_GROUP: {
                SPEGroup speGroup = (SPEGroup)theEObject;
                T result = caseSPEGroup(speGroup);
                if (result == null) result = caseIHasOperation(speGroup);
                if (result == null) result = caseIHasMatchTag(speGroup);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.IHAS_MATCH_TAG: {
                IHasMatchTag iHasMatchTag = (IHasMatchTag)theEObject;
                T result = caseIHasMatchTag(iHasMatchTag);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ModelPackage.IHAS_OPERATION: {
                IHasOperation iHasOperation = (IHasOperation)theEObject;
                T result = caseIHasOperation(iHasOperation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>SPE Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SPE Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSPEObject(SPEObject object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>SPE Attribute</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SPE Attribute</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSPEAttribute(SPEAttribute object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>SPE Link</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SPE Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSPELink(SPELink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>SPE Group</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SPE Group</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSPEGroup(SPEGroup object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>IHas Match Tag</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>IHas Match Tag</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIHasMatchTag(IHasMatchTag object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>IHas Operation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>IHas Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIHasOperation(IHasOperation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} //ModelSwitch
