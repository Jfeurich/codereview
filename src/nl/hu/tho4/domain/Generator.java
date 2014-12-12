package nl.hu.tho4.domain;

import nl.hu.tho4.domain.template.Template;
import org.stringtemplate.v4.ST;

public class Generator {
    public void Generate(BusinessRule rule, Template t) {
        ST template = t.sqlbusinessruletemplateNew();
        template.add("rule",rule);
    }

    public void getBusinessRule(String s) {

    }

    public void getTemplate(String s) {

    }
}
