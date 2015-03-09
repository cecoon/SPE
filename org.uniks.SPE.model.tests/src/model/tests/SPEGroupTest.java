/**
 */
package model.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import model.ModelFactory;
import model.SPEGroup;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>SPE Group</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class SPEGroupTest extends TestCase {

    /**
     * The fixture for this SPE Group test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SPEGroup fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args) {
        TestRunner.run(SPEGroupTest.class);
    }

    /**
     * Constructs a new SPE Group test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SPEGroupTest(String name) {
        super(name);
    }

    /**
     * Sets the fixture for this SPE Group test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(SPEGroup fixture) {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this SPE Group test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SPEGroup getFixture() {
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
        setFixture(ModelFactory.eINSTANCE.createSPEGroup());
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

} //SPEGroupTest
