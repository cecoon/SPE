/**
 */
package model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SPE Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link model.SPEGroup#getObjects <em>Objects</em>}</li>
 *   <li>{@link model.SPEGroup#getTag <em>Tag</em>}</li>
 * </ul>
 * </p>
 *
 * @see model.ModelPackage#getSPEGroup()
 * @model
 * @generated
 */
public interface SPEGroup extends EObject {
    /**
     * Returns the value of the '<em><b>Objects</b></em>' containment reference list.
     * The list contents are of type {@link model.SPEObject}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Objects</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Objects</em>' containment reference list.
     * @see model.ModelPackage#getSPEGroup_Objects()
     * @model containment="true"
     * @generated
     */
    EList<SPEObject> getObjects();

    /**
     * Returns the value of the '<em><b>Tag</b></em>' attribute.
     * The default value is <code>"Default"</code>.
     * The literals are from the enumeration {@link model.Tag}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tag</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tag</em>' attribute.
     * @see model.Tag
     * @see #setTag(Tag)
     * @see model.ModelPackage#getSPEGroup_Tag()
     * @model default="Default"
     * @generated
     */
    Tag getTag();

    /**
     * Sets the value of the '{@link model.SPEGroup#getTag <em>Tag</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Tag</em>' attribute.
     * @see model.Tag
     * @see #getTag()
     * @generated
     */
    void setTag(Tag value);

} // SPEGroup
