package org.uniks.spe.generator.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AttributesTest.class, CreateDeleteLinkTest.class, CreateObjectAndLinkTest.class,
        DeleteObjectTest.class, NACGrpTest.class, NotLinkTests.class, NotObjectTests.class,
        SetValueOnOptionalObjectTests.class, SimpleModelGeneratorTests.class })
public class AllTests {

}
