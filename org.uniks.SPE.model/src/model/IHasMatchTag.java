/**
 */
package model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IHas Match Tag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link model.IHasMatchTag#getTag <em>Tag</em>}</li>
 * </ul>
 * </p>
 *
 * @see model.ModelPackage#getIHasMatchTag()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IHasMatchTag extends EObject {
    /**
     * Returns the value of the '<em><b>Tag</b></em>' attribute.
     * The default value is <code>"Default"</code>.
     * The literals are from the enumeration {@link model.MatchTag}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tag</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tag</em>' attribute.
     * @see model.MatchTag
     * @see #setTag(MatchTag)
     * @see model.ModelPackage#getIHasMatchTag_Tag()
     * @model default="Default"
     * @generated
     */
    MatchTag getTag();

    /**
     * Sets the value of the '{@link model.IHasMatchTag#getTag <em>Tag</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Tag</em>' attribute.
     * @see model.MatchTag
     * @see #getTag()
     * @generated
     */
    void setTag(MatchTag value);

} // IHasMatchTag
