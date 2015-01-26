package nl.hu.tho6.controller.businessrule;

import nl.hu.tho6.controller.generator.Generator;
import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.persistence.ConnectDBBusinessRule;
import nl.hu.tho6.persistence.connection.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.util.ArrayList;

public class BusinessruleOphaalServlet extends HttpServlet {
    private ArrayList<BusinessRule> ongeGenereerdeBusinessRule = new ArrayList<BusinessRule>();
    private String returnMessage;
    private String succeeded;
    private String failed;
    private Generator generator = Generator.getInstance();
    private int fouten;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        fouten = 0;
        Connection con = ConnectionFactory.getConnection();
        ConnectDBBusinessRule cdbbr = new ConnectDBBusinessRule(con);
        ongeGenereerdeBusinessRule = cdbbr.getOngegenereerdeBusinessRules();
        System.out.println("Size: " + ongeGenereerdeBusinessRule.size());
        if(ongeGenereerdeBusinessRule.size() == 0){
            returnMessage = "Er zijn geen businessrules te genereren.";
        } else {

            for(BusinessRule businessRule : ongeGenereerdeBusinessRule) {
                String gegenereerdeBusinessRule = "";
                try{
                    gegenereerdeBusinessRule = generator.generate(businessRule);
                    System.out.println(businessRule.getRuleNaam() + "\n");
                    System.out.println(gegenereerdeBusinessRule);
                    cdbbr.saveBusinessRule(businessRule.getRuleNaam(), businessRule.getLanguage(), gegenereerdeBusinessRule);
                    cdbbr.changeBusinessRuleStatus(businessRule.getRuleID(),"GENERATED");
                } catch (NullPointerException ex){
                    cdbbr.changeBusinessRuleStatus(businessRule.getRuleID(),"ERROR");
                    StringWriter errors = new StringWriter();
                    ex.printStackTrace(new PrintWriter(errors));
                    cdbbr.saveToErrorLog(errors.toString(),"" + businessRule.getRuleID());
                    ex.printStackTrace();
                    fouten++;
                }
            }
            if((ongeGenereerdeBusinessRule.size() - fouten) == 1){
                returnMessage = "Er is " + (ongeGenereerdeBusinessRule.size()-fouten) + " businessrule gegenereerd.";
            } else {
                returnMessage = "Er zijn " + (ongeGenereerdeBusinessRule.size()-fouten) + " businessrules gegenereerd.";
            }
            if(fouten > 0){
                if(fouten == 1) {
                    returnMessage += "\nEn bij 1 businessrule is er een fout opgetreden";
                } else {
                    returnMessage += "\nEn bij " + fouten + " businessrules zijn er fouten opgetreden";
                }
            }
        }
        String session = req.getParameter("SESSION");
        String saveTo = req.getParameter("ITEM");
        String page = req.getParameter("PAGE");
        String url = req.getParameter("URL");
        resp.sendRedirect(url + ":" + page + ":" + session + "::::" + saveTo + ":" + returnMessage);
    }

}
