package nl.hu.tho4.domain.businessrules;

import nl.hu.tho4.domain.Attribute;
import nl.hu.tho4.domain.BusinessRule;
import nl.hu.tho4.domain.Value;

public class attRangeBusinessRule extends BusinessRule {
    public attRangeBusinessRule(Attribute a1, Value v1, Value v2) {
        super();
    }

    public BusinessRule parseStringRepresentation(String text) {
        // perform magic: x = 10 --> a business rule\
        return null;
    }
}
