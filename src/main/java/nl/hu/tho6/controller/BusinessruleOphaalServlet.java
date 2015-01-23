package nl.hu.tho6.controller;

import nl.hu.tho6.controller.generator.Generator;
import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.persistence.ConnectDBBusinessRule;
import nl.hu.tho6.persistence.connection.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class BusinessruleOphaalServlet extends HttpServlet {
    private ArrayList<BusinessRule> ongeGenereerdeBusinessRule = new ArrayList<BusinessRule>();
    private String returnMessage;
    private int aantalBusinessRules = 0;
    private Generator generator = Generator.getInstance();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Connection con = ConnectionFactory.getConnection();
        ConnectDBBusinessRule cdbbr = new ConnectDBBusinessRule(con);
        ongeGenereerdeBusinessRule = cdbbr.getOngegenereerdeBusinessRules();
        if(ongeGenereerdeBusinessRule.size() == 0){
            returnMessage = "Er zijn geen businessrules te genereren.";
        } else {

            for(BusinessRule businessRule : ongeGenereerdeBusinessRule) {
                String gegenereerdeBusinessRule = generator.generate(businessRule);
                cdbbr.saveBusinessRule(businessRule.getRuleNaam(), businessRule.getLanguage(), gegenereerdeBusinessRule);
                aantalBusinessRules++;
            }
            if(aantalBusinessRules == 1){
                returnMessage = "er is " + aantalBusinessRules + " businessrule gegenereerd.";
            } else {
                returnMessage = "er zijn " + aantalBusinessRules + " businessrules gegenereerd.";
            }
        }
        cdbbr.changeBusinessRuleStatus();
        String session = req.getParameter("SESSION");
        resp.sendRedirect("https://ondora01.hu.nl:8080/apex/f?p=2298:1:" + session + "::::P1_TEST:" + returnMessage);
    }

}
