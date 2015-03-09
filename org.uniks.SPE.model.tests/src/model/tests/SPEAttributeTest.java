/**
 */
package model.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import model.ModelFactory;
import model.SPEAttribute;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>SPE Attribute</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class SPEAttributeTest extends TestCase {

    /**
     * The fixture for this SPE Attribute test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SPEAttribute fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args) {
        TestRunner.run(SPEAttributeTest.class);
    }

    /**
     * Constructs a new SPE Attribute test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SPEAttributeTest(String name) {
        super(name);
    }

    /**
     * Sets the fixture for this SPE Attribute test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(SPEAttribute fixture) {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this SPE Attribute test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SPEAttribute getFixture() {
        return fixture;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
    @Override
    protected void setUp() throws Exception {
        setFixture(ModelFactory.eINSTANCE.createSPEAttribute());
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#tearDown()
     * @generated
     */
    @Override
    protected void tearDown() throws Exception {
        setFixture(null);
    }

} //SPEAttributeTest
