/**
 */
package model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SPE Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link model.SPEObject#getName <em>Name</em>}</li>
 *   <li>{@link model.SPEObject#getType <em>Type</em>}</li>
 *   <li>{@link model.SPEObject#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link model.SPEObject#getOutboundLinks <em>Outbound Links</em>}</li>
 *   <li>{@link model.SPEObject#getInboundLinks <em>Inbound Links</em>}</li>
 * </ul>
 * </p>
 *
 * @see model.ModelPackage#getSPEObject()
 * @model
 * @generated
 */
public interface SPEObject extends IHasOperation, IHasMatchTag {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * The default value is <code>"this"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see model.ModelPackage#getSPEObject_Name()
     * @model default="this" id="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link model.SPEObject#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The default value is <code>"Class"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see model.ModelPackage#getSPEObject_Type()
     * @model default="Class"
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '{@link model.SPEObject#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(String value);

    /**
     * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
     * The list contents are of type {@link model.SPEAttribute}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attributes</em>' containment reference list.
     * @see model.ModelPackage#getSPEObject_Attributes()
     * @model containment="true"
     * @generated
     */
    EList<SPEAttribute> getAttributes();

    /**
     * Returns the value of the '<em><b>Outbound Links</b></em>' reference list.
     * The list contents are of type {@link model.SPELink}.
     * It is bidirectional and its opposite is '{@link model.SPELink#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Outbound Links</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outbound Links</em>' reference list.
     * @see model.ModelPackage#getSPEObject_OutboundLinks()
     * @see model.SPELink#getSource
     * @model opposite="source"
     * @generated
     */
    EList<SPELink> getOutboundLinks();

    /**
     * Returns the value of the '<em><b>Inbound Links</b></em>' reference list.
     * The list contents are of type {@link model.SPELink}.
     * It is bidirectional and its opposite is '{@link model.SPELink#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Inbound Links</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Inbound Links</em>' reference list.
     * @see model.ModelPackage#getSPEObject_InboundLinks()
     * @see model.SPELink#getTarget
     * @model opposite="target"
     * @generated
     */
    EList<SPELink> getInboundLinks();

} // SPEObject
