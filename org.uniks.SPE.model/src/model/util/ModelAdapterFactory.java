/**
 */
package model.util;

import model.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see model.ModelPackage
 * @generated
 */
public class ModelAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ModelPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = ModelPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ModelSwitch<Adapter> modelSwitch =
        new ModelSwitch<Adapter>() {
            @Override
            public Adapter caseSPEObject(SPEObject object) {
                return createSPEObjectAdapter();
            }
            @Override
            public Adapter caseSPEAttribute(SPEAttribute object) {
                return createSPEAttributeAdapter();
            }
            @Override
            public Adapter caseSPELink(SPELink object) {
                return createSPELinkAdapter();
            }
            @Override
            public Adapter caseSPEGroup(SPEGroup object) {
                return createSPEGroupAdapter();
            }
            @Override
            public Adapter caseIHasMatchTag(IHasMatchTag object) {
                return createIHasMatchTagAdapter();
            }
            @Override
            public Adapter caseIHasOperation(IHasOperation object) {
                return createIHasOperationAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link model.SPEObject <em>SPE Object</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see model.SPEObject
     * @generated
     */
    public Adapter createSPEObjectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link model.SPEAttribute <em>SPE Attribute</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see model.SPEAttribute
     * @generated
     */
    public Adapter createSPEAttributeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link model.SPELink <em>SPE Link</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see model.SPELink
     * @generated
     */
    public Adapter createSPELinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link model.SPEGroup <em>SPE Group</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see model.SPEGroup
     * @generated
     */
    public Adapter createSPEGroupAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link model.IHasMatchTag <em>IHas Match Tag</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see model.IHasMatchTag
     * @generated
     */
    public Adapter createIHasMatchTagAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link model.IHasOperation <em>IHas Operation</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see model.IHasOperation
     * @generated
     */
    public Adapter createIHasOperationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //ModelAdapterFactory
