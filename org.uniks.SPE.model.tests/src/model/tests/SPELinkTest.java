/**
 */
package model.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import model.ModelFactory;
import model.SPELink;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>SPE Link</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class SPELinkTest extends TestCase {

    /**
     * The fixture for this SPE Link test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SPELink fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args) {
        TestRunner.run(SPELinkTest.class);
    }

    /**
     * Constructs a new SPE Link test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SPELinkTest(String name) {
        super(name);
    }

    /**
     * Sets the fixture for this SPE Link test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(SPELink fixture) {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this SPE Link test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SPELink getFixture() {
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
        setFixture(ModelFactory.eINSTANCE.createSPELink());
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

} //SPELinkTest
