package nl.hu.tho4.test;

import org.junit.*;

import static org.junit.Assert.*;

import org.stringtemplate.v4.ST;

import nl.hu.tho4.domain.businessrule.BusinessRule;
import nl.hu.tho4.translator.Translator;

/**
 * The class <code>TranslatorTest</code> contains tests for the class <code>{@link Translator}</code>.
 *
 * @generatedBy CodePro at 1/14/15 2:17 PM
 * @author jayfeurich
 * @version $Revision: 1.0 $
 */
public class TranslatorTest {
	/**
	 * Run the String Translator(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/14/15 2:17 PM
	 */
	@Test
	public void testTranslator_1()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");
		String language = "";

		String result = fixture.Translator(language);

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the String getLanguage() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/14/15 2:17 PM
	 */
	@Test
	public void testGetLanguage_1()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");

		String result = fixture.getLanguage();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setLanguage(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/14/15 2:17 PM
	 */
	@Test
	public void testSetLanguage_1()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");
		String language = "";

		fixture.setLanguage(language);

		// add additional test code here
	}

	/**
	 * Run the ST translate(ST,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/14/15 2:17 PM
	 */
	@Test
	public void testTranslate_1()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");
		ST st = new ST("");
		String language = "mysql";

		ST result = fixture.translate(st, language);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at st4hidden.org.antlr.runtime.ANTLRStringStream.<init>(ANTLRStringStream.java:75)
		//       at org.stringtemplate.v4.compiler.Compiler.compile(Compiler.java:111)
		//       at org.stringtemplate.v4.STGroup.compile(STGroup.java:448)
		//       at org.stringtemplate.v4.ST.<init>(ST.java:173)
		//       at org.stringtemplate.v4.ST.<init>(ST.java:159)
		//       at nl.hu.tho4.translator.Translator.translate(Translator.java:34)
		assertNotNull(result);
	}

	/**
	 * Run the ST translate(ST,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/14/15 2:17 PM
	 */
	@Test
	public void testTranslate_2()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");
		ST st = new ST("");
		String language = "mysql";

		ST result = fixture.translate(st, language);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at st4hidden.org.antlr.runtime.ANTLRStringStream.<init>(ANTLRStringStream.java:75)
		//       at org.stringtemplate.v4.compiler.Compiler.compile(Compiler.java:111)
		//       at org.stringtemplate.v4.STGroup.compile(STGroup.java:448)
		//       at org.stringtemplate.v4.ST.<init>(ST.java:173)
		//       at org.stringtemplate.v4.ST.<init>(ST.java:159)
		//       at nl.hu.tho4.translator.Translator.translate(Translator.java:34)
		assertNotNull(result);
	}

	/**
	 * Run the String translator(String,BusinessRule) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/14/15 2:17 PM
	 */
	@Test
	public void testTranslator_2()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");
		String language = "mysql";
		BusinessRule businessrule = new BusinessRule();

		String result = fixture.translator(language, businessrule);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at st4hidden.org.antlr.runtime.ANTLRStringStream.<init>(ANTLRStringStream.java:75)
		//       at org.stringtemplate.v4.compiler.Compiler.compile(Compiler.java:111)
		//       at org.stringtemplate.v4.STGroup.compile(STGroup.java:448)
		//       at org.stringtemplate.v4.ST.<init>(ST.java:173)
		//       at org.stringtemplate.v4.ST.<init>(ST.java:159)
		//       at nl.hu.tho4.translator.Translator.translator(Translator.java:15)
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 1/14/15 2:17 PM
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
	 * @generatedBy CodePro at 1/14/15 2:17 PM
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
	 * @generatedBy CodePro at 1/14/15 2:17 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(TranslatorTest.class);
	}
}