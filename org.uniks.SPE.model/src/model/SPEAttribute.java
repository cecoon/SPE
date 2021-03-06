/**
 */
package model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SPE Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link model.SPEAttribute#getName <em>Name</em>}</li>
 *   <li>{@link model.SPEAttribute#getOperation <em>Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @see model.ModelPackage#getSPEAttribute()
 * @model
 * @generated
 */
public interface SPEAttribute extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * The default value is <code>"name"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see model.ModelPackage#getSPEAttribute_Name()
     * @model default="name"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link model.SPEAttribute#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Operation</b></em>' attribute.
     * The default value is <code>"== value"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation</em>' attribute.
     * @see #setOperation(String)
     * @see model.ModelPackage#getSPEAttribute_Operation()
     * @model default="== value"
     * @generated
     */
    String getOperation();

    /**
     * Sets the value of the '{@link model.SPEAttribute#getOperation <em>Operation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operation</em>' attribute.
     * @see #getOperation()
     * @generated
     */
    void setOperation(String value);

} // SPEAttribute
