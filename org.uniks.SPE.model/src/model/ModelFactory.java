/**
 */
package model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see model.ModelPackage
 * @generated
 */
public interface ModelFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ModelFactory eINSTANCE = model.impl.ModelFactoryImpl.init();

    /**
     * Returns a new object of class '<em>SPE Object</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>SPE Object</em>'.
     * @generated
     */
    SPEObject createSPEObject();

    /**
     * Returns a new object of class '<em>SPE Attribute</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>SPE Attribute</em>'.
     * @generated
     */
    SPEAttribute createSPEAttribute();

    /**
     * Returns a new object of class '<em>SPE Link</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>SPE Link</em>'.
     * @generated
     */
    SPELink createSPELink();

    /**
     * Returns a new object of class '<em>SPE Group</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>SPE Group</em>'.
     * @generated
     */
    SPEGroup createSPEGroup();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    ModelPackage getModelPackage();

} //ModelFactory
