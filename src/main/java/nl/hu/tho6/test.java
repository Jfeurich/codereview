package nl.hu.tho6;

import nl.hu.tho6.controller.generator.Generator;
import nl.hu.tho6.domain.businessrule.Attribute;
import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.domain.businessrule.Operator;
import nl.hu.tho6.domain.businessrule.Value;
import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.dictionary.exception.DictionaryNotFoundException;
import nl.hu.tho6.translator.filesystem.FileSystemFacade;
import nl.hu.tho6.translator.filesystem.types.impl.XMLFileSystem;

/**
 * Created by Nathan on 21/01/2015.
 */
public class test {
    public static void main(String[] args) throws DictionaryNotFoundException {
        FileSystemFacade fs = new FileSystemFacade(new XMLFileSystem());
        Dictionary dc = fs.readDictionary("plsql");
        Translator.addDictionary(dc);
        Generator gen = Generator.getInstance();
        Operator o = new Operator("GreaterThanOrEqualsTo","Een type");
        Attribute a1 = new Attribute("MedewerkerGeboortedatum","target","medewerker","geboortedatum",1);
        Value v = new Value("Geboortedatum","Integer","18");
        String result = gen.generate("plsql",new BusinessRule("BsRuleNaam","Medeweker moet ouder dan 18 zijn.","CustomError","BSRULE01", o, v, null, a1, null));
        System.out.println(result);
    }
}