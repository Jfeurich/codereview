package nl.hu.tho6.controller;

import nl.hu.tho6.controller.generator.Generator;
import nl.hu.tho6.domain.businessrule.BusinessRule;
import org.stringtemplate.v4.ST;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BusinessruleOphaalServlet extends HttpServlet {
    private static final String LANGUAGE = "PL/SQL";
    private Generator generator = Generator.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int aantalBusinessRules = 0;
        String returnMessage;
        List<BusinessRule> ongeGenereerdeBusinessRules = new ArrayList<BusinessRule>();
        //        List<BusinessRule> ongeGenereerdeBusinessRules = ConnectDBBusinessRule.getOngegenereerdeBusinessRules();

        if (ongeGenereerdeBusinessRules.size() == 0) {
            returnMessage = "Er zijn geen businessrules te genereren.";
        } else {
            for (int i = 0; i < ongeGenereerdeBusinessRules.size(); i++) {
                ST gegenereerdeBusinessRule = generator.generate(LANGUAGE, ongeGenereerdeBusinessRules.get(i));
//                ConnectDBBusinessRule.saveBusinessrule(gegenereerdeBusinessRule);
                aantalBusinessRules++;
            }
            if (aantalBusinessRules == 1) {
                returnMessage = "er is " + aantalBusinessRules + " businessrule gegenereerd.";
            } else {
                returnMessage = "er zijn " + aantalBusinessRules + " businessrules gegenereerd.";
            }
        }

        String session = req.getParameter("SESSION");
        //TODO link niet hardcoded maar mee uit request params: base url, app id, page id, session id, page item return message
        resp.sendRedirect("https://ondora01.hu.nl:8080/apex/f?p=2298:1:" + session + "::::P1_TEST:" + returnMessage);
    }
}
