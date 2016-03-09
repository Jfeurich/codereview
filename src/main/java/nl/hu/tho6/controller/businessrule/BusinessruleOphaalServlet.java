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
    private Generator generator = Generator.getInstance();
    private int aantalFouten;

    //test
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String database = req.getParameter("DATABASE");
        aantalFouten = 0;
        ConnectDBBusinessRule cdbbr = getBusinessRuleDataBaseConnection();
        getOngegenereerdeBusinessRules(cdbbr);
        if(ongeGenereerdeBusinessRule.isEmpty()){
            setReturnMessageForNoRules();
        } else {
            getBusinessRulesFromDatabase(database, cdbbr);
        }
        String session = req.getParameter("SESSION");
        String saveTo = req.getParameter("ITEM");
        String page = req.getParameter("PAGE");
        String url = req.getParameter("URL");
        resp.sendRedirect(url + ":" + page + ":" + session + "::::" + saveTo + ":" + returnMessage);
    }

    private void getOngegenereerdeBusinessRules(ConnectDBBusinessRule cdbbr) {
        ongeGenereerdeBusinessRule = cdbbr.getOngegenereerdeBusinessRules();
    }

    private void getBusinessRulesFromDatabase(String database, ConnectDBBusinessRule cdbbr) {
        for(BusinessRule businessRule : ongeGenereerdeBusinessRule) {
            getBusinessRuleFromDatabase(database, cdbbr, businessRule);
        }
        if((ongeGenereerdeBusinessRule.size() - aantalFouten) == 1){
            setReturnMessageForSingleGeneratedBusinessRule();
        } else {
            setReturnMessageForMultipleGeneratedBusinessRules();
        }
        if(aantalFouten > 0){
            if(aantalFouten == 1) {
                addSingleErrorToReturnMessage();
            } else {
                addMultipleErrorsToReturnMessages();
            }
        }
    }

    private void addMultipleErrorsToReturnMessages() {
        returnMessage += "\nEn bij " + aantalFouten + " businessrules zijn er aantalFouten opgetreden";
    }

    private void addSingleErrorToReturnMessage() {
        returnMessage += "\nEn bij 1 businessrule is er een fout opgetreden";
    }

    private void setReturnMessageForMultipleGeneratedBusinessRules() {
        returnMessage = "Er zijn " + (ongeGenereerdeBusinessRule.size()- aantalFouten) + " businessrules gegenereerd.";
    }

    private void setReturnMessageForSingleGeneratedBusinessRule() {
        returnMessage = "Er is " + (ongeGenereerdeBusinessRule.size()- aantalFouten) + " businessrule gegenereerd.";
    }

    private void getBusinessRuleFromDatabase(String database, ConnectDBBusinessRule cdbbr, BusinessRule businessRule) {
        String gegenereerdeBusinessRule = "";
        try{
            gegenereerdeBusinessRule = getGenerator().generate(businessRule);
            cdbbr.saveBusinessRule(businessRule.getRuleNaam(), businessRule.getLanguage(), gegenereerdeBusinessRule);
            cdbbr.changeBusinessRuleStatus(businessRule.getRuleID(),"GENERATED");
            if("target".equals(database)){
                cdbbr.runCode(gegenereerdeBusinessRule);
            }
        } catch (NullPointerException ex){
            handleNullPointerException(cdbbr, businessRule, ex);
        }
    }

    private void handleNullPointerException(ConnectDBBusinessRule cdbbr, BusinessRule businessRule, NullPointerException ex) {
        cdbbr.changeBusinessRuleStatus(businessRule.getRuleID(),"ERROR");
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        cdbbr.saveToErrorLog(errors.toString(),"" + businessRule.getRuleID());
        ex.printStackTrace();
        aantalFouten++;
    }

    private ConnectDBBusinessRule getBusinessRuleDataBaseConnection() {
        Connection con = ConnectionFactory.getConnection();
        return new ConnectDBBusinessRule(con);
    }

    private void setReturnMessageForNoRules() {
        returnMessage = "Er zijn geen businessrules te genereren.";
    }

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }
}
