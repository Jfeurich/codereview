package nl.hu.tho6;

import nl.hu.tho6.controller.generator.Generator;
import nl.hu.tho6.domain.businessrule.Attribute;
import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.domain.businessrule.Operator;
import nl.hu.tho6.domain.businessrule.Value;
import nl.hu.tho6.persistence.ConnectDBBusinessRule;
import nl.hu.tho6.persistence.connection.ConnectionFactory;
import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.dictionary.exception.DictionaryNotFoundException;
import nl.hu.tho6.translator.filesystem.FileSystemFacade;
import nl.hu.tho6.translator.filesystem.types.impl.XMLFileSystem;
import org.w3c.dom.Attr;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Nathan on 21/01/2015.
 */
public class test {
    public static void main(String[] args) throws DictionaryNotFoundException {

        Connection con = ConnectionFactory.getConnection();
        ConnectDBBusinessRule cdbbr = new ConnectDBBusinessRule(con);
        ArrayList<BusinessRule>  ongeGenereerdeBusinessRule = cdbbr.getOngegenereerdeBusinessRules();
        System.out.println(ongeGenereerdeBusinessRule.size());

        FileSystemFacade fs = new FileSystemFacade(new XMLFileSystem());
        Dictionary dc = fs.readDictionary("plsql");
        Translator.addDictionary(dc);
        Generator gen = Generator.getInstance();
        Operator o = new Operator("Between","Een type");
        Attribute a1 = new Attribute("Medewerkergeboortedatum","target","medewerker","geboortedatum",1);
        Attribute a2 = new Attribute("Spelersgeboortedatum","target","spelers","geboortedatum",1);
        Value v1 = new Value("Geboortedatum","Integer","18");
        Value v2 = new Value("Geboortedatum","Integer","65");
//        String result = gen.generate("plsql",new BusinessRule("BsRuleNaam","Medewerker moet 18 jaar of ouder zijn en jonger dan of gelijk aan 65.","CustomError","BSRULE01","PLSQL", o, v1, v2, a1, null));
        String result = gen.generate(ongeGenereerdeBusinessRule.get(0));
        System.out.println(result);
    }
}