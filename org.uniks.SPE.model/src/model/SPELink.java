/**
 */
package model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SPE Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link model.SPELink#getName <em>Name</em>}</li>
 *   <li>{@link model.SPELink#getSource <em>Source</em>}</li>
 *   <li>{@link model.SPELink#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see model.ModelPackage#getSPELink()
 * @model
 * @generated
 */
public interface SPELink extends IHasOperation, IHasMatchTag {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * The default value is <code>"has"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see model.ModelPackage#getSPELink_Name()
     * @model default="has"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link model.SPELink#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Source</b></em>' reference.
     * It is bidirectional and its opposite is '{@link model.SPEObject#getOutboundLinks <em>Outbound Links</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(SPEObject)
     * @see model.ModelPackage#getSPELink_Source()
     * @see model.SPEObject#getOutboundLinks
     * @model opposite="outboundLinks"
     * @generated
     */
    SPEObject getSource();

    /**
     * Sets the value of the '{@link model.SPELink#getSource <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(SPEObject value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * It is bidirectional and its opposite is '{@link model.SPEObject#getInboundLinks <em>Inbound Links</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(SPEObject)
     * @see model.ModelPackage#getSPELink_Target()
     * @see model.SPEObject#getInboundLinks
     * @model opposite="inboundLinks"
     * @generated
     */
    SPEObject getTarget();

    /**
     * Sets the value of the '{@link model.SPELink#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(SPEObject value);

} // SPELink
