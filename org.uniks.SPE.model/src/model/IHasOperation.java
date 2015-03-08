/**
 */
package model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IHas Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link model.IHasOperation#getOperation <em>Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @see model.ModelPackage#getIHasOperation()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IHasOperation extends EObject {
    /**
     * Returns the value of the '<em><b>Operation</b></em>' attribute.
     * The default value is <code>"Nop"</code>.
     * The literals are from the enumeration {@link model.Operations}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation</em>' attribute.
     * @see model.Operations
     * @see #setOperation(Operations)
     * @see model.ModelPackage#getIHasOperation_Operation()
     * @model default="Nop"
     * @generated
     */
    Operations getOperation();

    /**
     * Sets the value of the '{@link model.IHasOperation#getOperation <em>Operation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operation</em>' attribute.
     * @see model.Operations
     * @see #getOperation()
     * @generated
     */
    void setOperation(Operations value);

} // IHasOperation
