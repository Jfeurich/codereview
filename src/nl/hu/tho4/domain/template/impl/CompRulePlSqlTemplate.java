package nl.hu.tho4.domain.template.impl;

import nl.hu.tho4.domain.template.Template;
import org.stringtemplate.v4.ST;

/**
 * Created by Liam on 12-12-2014.
 */
public class CompRulePlSqlTemplate implements Template {

    @Override
    public ST getTemplate() {
        ST template = new ST("CREATE OR REPLACE TRIGGER <trigger_name> " +
                "BEFORE INSERT OR UPDATE " +
                "OF <col_name> ON <table_name> " +
                " WHEN <rule.att1> <rule.operator> <rule.att2> " +
                " <declaration_statements> " +
                "BEGIN " +
                " <executable_statements> " +
                "EXCEPTION " +
                " <exception_statements> " +
                "END;");
        return template;
    }
}
