package nl.hu.tho4.controller.generator;

import org.junit.*;
import nl.hu.tho4.domain.businessrule.BusinessRule;
import nl.hu.tho4.translator.Translator;
import static org.junit.Assert.*;

/**
 * The class <code>GeneratorTest</code> contains tests for the class <code>{@link Generator}</code>.
 *
 * @generatedBy CodePro at 14/01/15 10:30
 * @author jay
 * @version $Revision: 1.0 $
 */
public class GeneratorTest {
	/**
	 * Run the void generateCode(String,BusinessRule) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14/01/15 10:30
	 */
	@Test
	public void testGenerateCode_1()
		throws Exception {
		Generator fixture = Generator.getInstance();
		fixture.translator = new Translator();
		String language = "";
		BusinessRule br = new BusinessRule();

		fixture.generateCode(language, br);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at st4hidden.org.antlr.runtime.ANTLRStringStream.<init>(ANTLRStringStream.java:75)
		//       at org.stringtemplate.v4.compiler.Compiler.compile(Compiler.java:111)
		//       at org.stringtemplate.v4.STGroup.compile(STGroup.java:448)
		//       at org.stringtemplate.v4.ST.<init>(ST.java:173)
		//       at org.stringtemplate.v4.ST.<init>(ST.java:159)
		//       at nl.hu.tho4.translator.Translator.translate(Translator.java:27)
		//       at nl.hu.tho4.controller.generator.Generator.generateCode(Generator.java:22)
	}

	/**
	 * Run the Generator getInstance() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14/01/15 10:30
	 */
	@Test
	public void testGetInstance_1()
		throws Exception {

		Generator result = Generator.getInstance();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 14/01/15 10:30
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 14/01/15 10:30
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 14/01/15 10:30
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GeneratorTest.class);
	}
}