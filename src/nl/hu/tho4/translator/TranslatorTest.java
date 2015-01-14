package nl.hu.tho4.translator;

import org.junit.*;
import static org.junit.Assert.*;
import org.stringtemplate.v4.ST;
import nl.hu.tho4.domain.businessrule.Operator;
import nl.hu.tho4.domain.businessrule.BusinessRule;
import nl.hu.tho4.domain.businessrule.Attribute;
import nl.hu.tho4.domain.businessrule.Value;

/**
 * The class <code>TranslatorTest</code> contains tests for the class <code>{@link Translator}</code>.
 *
 * @generatedBy CodePro at 14/01/15 10:31
 * @author jay
 * @version $Revision: 1.0 $
 */
public class TranslatorTest {
	/**
	 * Run the BusinessRule getBusinessrule() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14/01/15 10:31
	 */
	@Test
	public void testGetBusinessrule_1()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");
		//je hoeft geen businessrule te setten in de translator het is alleen maar een soort utility class
		//fixture.setBusinessrule(new BusinessRule());

		//BusinessRule result = fixture.getBusinessrule();

		// add additional test code here
//		assertNotNull(result);
//		assertEquals(null, result.getAttribute2());
//		assertEquals(null, result.getErrorType());
//		assertEquals(null, result.getValue2());
//		assertEquals(null, result.getValue1());
//		assertEquals(null, result.getRuleNaam());
//		assertEquals(null, result.getOperator());
//		assertEquals(null, result.getCode());
//		assertEquals(null, result.getError());
//		assertEquals(null, result.getAttribute1());
	}

	/**
	 * Run the String getLanguage() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14/01/15 10:31
	 */
	@Test
	public void testGetLanguage_1()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");

		//fixture.setBusinessrule(new BusinessRule());

		String result = fixture.getLanguage();

		// add additional test code here
		assertEquals("", result);
	}

	/**
	 * Run the void setBusinessrule(BusinessRule) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14/01/15 10:31
	 */
	@Test
	public void testSetBusinessrule_1()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");
		//fixture.setBusinessrule(new BusinessRule());
		BusinessRule businessrule = new BusinessRule();

		//fixture.setBusinessrule(businessrule);

		// add additional test code here
	}

	/**
	 * Run the void setLanguage(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14/01/15 10:31
	 */
	@Test
	public void testSetLanguage_1()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");
		//fixture.setBusinessrule(new BusinessRule());
		String language = "";

		fixture.setLanguage(language);

		// add additional test code here
	}

	/**
	 * Run the ST translate(ST,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14/01/15 10:31
	 */
	@Test
	public void testTranslate_1()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");
		//fixture.setBusinessrule(new BusinessRule());
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
		//       at nl.hu.tho4.translator.Translator.translate(Translator.java:27)
		assertNotNull(result);
	}

	/**
	 * Run the ST translate(ST,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14/01/15 10:31
	 */
	@Test
	public void testTranslate_2()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");
		//fixture.setBusinessrule(new BusinessRule());
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
		//       at nl.hu.tho4.translator.Translator.translate(Translator.java:27)
		assertNotNull(result);
	}

	/**
	 * Run the String translator(String,BusinessRule) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14/01/15 10:31
	 */
	@Test
	public void testTranslator_1()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");
		//fixture.setBusinessrule(new BusinessRule());
		String language = "mysql";
		BusinessRule businessrule = new BusinessRule("", "", "", "", new Operator("", ""), new Value("", "", ""), new Value("", "", ""), new Attribute("", "", "", "", ""), new Attribute("", "", "", "", ""));

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
	 * Run the String translator(String,BusinessRule) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14/01/15 10:31
	 */
	@Test
	public void testTranslator_2()
		throws Exception {
		Translator fixture = new Translator();
		fixture.setLanguage("");
		//fixture.setBusinessrule(new BusinessRule());
		String language = "mysql";
		BusinessRule businessrule = new BusinessRule("", "", "", "", new Operator("", ""), new Value("", "", ""), new Value("", "", ""), new Attribute("", "", "", "", ""), new Attribute("", "", "", "", ""));

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
	 * @generatedBy CodePro at 14/01/15 10:31
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
	 * @generatedBy CodePro at 14/01/15 10:31
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
	 * @generatedBy CodePro at 14/01/15 10:31
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(TranslatorTest.class);
	}
}