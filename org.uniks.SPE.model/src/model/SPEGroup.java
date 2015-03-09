/**
 */
package model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SPE Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link model.SPEGroup#getObjects <em>Objects</em>}</li>
 *   <li>{@link model.SPEGroup#getName <em>Name</em>}</li>
 *   <li>{@link model.SPEGroup#getLinks <em>Links</em>}</li>
 *   <li>{@link model.SPEGroup#getSubGroups <em>Sub Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @see model.ModelPackage#getSPEGroup()
 * @model
 * @generated
 */
public interface SPEGroup extends IHasOperation, IHasMatchTag {
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
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * The default value is <code>"MySPEDiagram"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see model.ModelPackage#getSPEGroup_Name()
     * @model default="MySPEDiagram"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link model.SPEGroup#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Links</b></em>' containment reference list.
     * The list contents are of type {@link model.SPELink}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Links</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Links</em>' containment reference list.
     * @see model.ModelPackage#getSPEGroup_Links()
     * @model containment="true"
     * @generated
     */
    EList<SPELink> getLinks();

    /**
     * Returns the value of the '<em><b>Sub Groups</b></em>' containment reference list.
     * The list contents are of type {@link model.SPEGroup}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Groups</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sub Groups</em>' containment reference list.
     * @see model.ModelPackage#getSPEGroup_SubGroups()
     * @model containment="true"
     * @generated
     */
    EList<SPEGroup> getSubGroups();

} // SPEGroup
