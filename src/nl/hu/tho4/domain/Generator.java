package nl.hu.tho4.domain;

import nl.hu.tho4.domain.template.TemplateOld;
import org.stringtemplate.v4.ST;

public class Generator {
    public void Generate(BusinessRule rule, TemplateOld t) {
        ST template = t.sqlbusinessruletemplateNew(rule);
        template.add("rule",rule);
    }

    public void getBusinessRule(String s) {

    }

    public void getTemplate(String s) {

    }
}
