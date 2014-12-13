package nl.hu.tho4;

import nl.hu.tho4.domain.businessrule.Attribute;
import nl.hu.tho4.domain.businessrule.BusinessRule;
import nl.hu.tho4.domain.Generator;
import nl.hu.tho4.domain.businessrule.Value;
import nl.hu.tho4.domain.businessrules.attCompareBusinessRule;
import nl.hu.tho4.domain.businessrules.attRangeBusinessRule;
import nl.hu.tho4.domain.template.Template;
import nl.hu.tho4.domain.template.impl.CompRulePlSqlTemplate;

public class Main {
	public static void main(String[] args) {
		Value v = new Value();
		v.setWaardeNaam("ugh");
		v.setValue("2");
		v.setValueType("int");
		Value v2 = new Value();
		v2.setWaardeNaam("ugh2");
		v2.setValue("4");
		v2.setValueType("int");
		Attribute a = new Attribute("Ietsuitdedatabase","uitschema1","kolom1","tabel1");
		Attribute a2 = new Attribute("Nogietsuitdedatabase","uitschema1","kolom1","tabel1");
		BusinessRule rule = new attCompareBusinessRule(a,a2);
		rule.setRuleNaam("regel1");
		rule.setError("dit is een error");
		rule.setErrorType("Custom");
		Template t = new CompRulePlSqlTemplate();
		Generator generator = new Generator();
		generator.Generate(rule, t);
		// TODO Auto-generated method stub

	}

}
