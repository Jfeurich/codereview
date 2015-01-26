package nl.hu.tho6.controller.generator;

import nl.hu.tho6.domain.businessrule.Attribute;
import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.domain.businessrule.Operator;
import nl.hu.tho6.domain.businessrule.Value;
import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.filesystem.FileSystemFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Generator Tester.
 *
 * @author Liam de Haas
 * @version 1.0
 * @since jan 23, 2015
 */
public class GeneratorTest {
    private Generator    generator;
    private BusinessRule businessRule;
    private String expectedCode;

    @Before
    public void before() throws Exception {
        //Dictionary inlezen
        FileSystemFacade facade = new FileSystemFacade();
        Dictionary dic = facade.readDictionary("plsql");
        Translator.addDictionary(dic);

        //generator instantieren
        generator = Generator.getInstance();

        //businessrule aanmaken
        businessRule = new BusinessRule();

        Attribute attribute = new Attribute("Medewerkergeboortedatum","target","medewerker","geboortedatum",1);
        Operator operator = new Operator("GreaterThan","Een type");
        Value value = new Value("Geboortedatum","Integer","18");

        businessRule.setRuleNaam("Naam");
        businessRule.setError("Error");
        businessRule.setErrorType("2");
        businessRule.setCode("Code");
        businessRule.setLanguage("plsql");
        businessRule.setOperator(operator);
        businessRule.setValue1(value);
        businessRule.setValue2(value);
        businessRule.setAttribute1(attribute);
        businessRule.setAttribute2(attribute);
        businessRule.setRuleID(0);

        //expected code output voor de businessrule
        expectedCode = "\n" +
                       "            create or replace trigger BRG_TARGET_medewerker_TRIGGER0\n" +
                       "                before update or insert or delete\n" +
                       "                on medewerker\n" +
                       "                for each row\n" +
                       "            declare\n" +
                       "                \n" +
                       "            begin\n" +
                       "                if not ( new.geboortedatum > new.geboortedatum ) then\n" +
                       "                    raise_application_error(-20800, \"Error\");\n" +
                       "                end if;\n" +
                       "            end BRG_TARGET_medewerker_TRIGGER0;\n" +
                       "        ";
    }

    @After
    public void after() throws Exception {
        generator = null;
        businessRule = null;
        expectedCode = null;
    }

    /**
     * Method: getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
        Generator generator1 = Generator.getInstance();
        assertEquals(generator, generator1);
    }

    /**
     * Method: generate(BusinessRule businessRule)
     */
    @Test
    public void testGenerate() throws Exception {
        String generatedCode = generator.generate(businessRule);
        assertEquals(generatedCode, expectedCode);
    }
} 
