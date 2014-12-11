package nl.hu.tho4.domain.businessrules;

import nl.hu.tho4.domain.Attribute;
import nl.hu.tho4.domain.BusinessRule;

public class attCompareBusinessRule extends BusinessRule {

    public attCompareBusinessRule(Attribute a, Attribute b) {
        super();
    }

    public BusinessRule parseStringRepresentation(String text) {
        // perform magic: x = 10 --> a business rule\
        return null;
    }
}
