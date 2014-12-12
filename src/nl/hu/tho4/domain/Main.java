package nl.hu.tho4.domain;

import nl.hu.tho4.domain.businessrules.attCompareBusinessRule;
import nl.hu.tho4.domain.businessrules.attRangeBusinessRule;

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
		BusinessRule rule = new attRangeBusinessRule(a,v,v2);
		rule.setRuleNaam("regel1");
		rule.setError("dit is een error");
		rule.setErrorType("Custom");
		// TODO Auto-generated method stub

	}

}
