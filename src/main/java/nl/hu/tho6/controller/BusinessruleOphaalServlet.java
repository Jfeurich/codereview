package nl.hu.tho6.controller;

import nl.hu.tho6.domain.businessrule.BusinessRule;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import nl.hu.tho6.persistence.ConnectDBBusinessRule;
import nl.hu.tho6.controller.generator.Generator;
import org.stringtemplate.v4.ST;

public class BusinessruleOphaalServlet extends HttpServlet {
    private ArrayList<BusinessRule> ongeGenereerdeBusinessRule = new ArrayList<BusinessRule>();
    private String returnMessage;
    private String language = "PL/SQL";
    private int aantalBusinessRules = 0;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ConnectDBBusinessRule.getOngegenereerdeBusinessRules();
//        language = req.getParameter("Language");
        RequestDispatcher rd = null;
        if(ongeGenereerdeBusinessRule.size() == 0){
            returnMessage = "Er zijn geen businessrules te genereren.";
//            rd = req.getRequestDispatcher(???);
        } else {
            for(int i = 0; i < ongeGenereerdeBusinessRule.size(); i++){
                ST gegenereerdeBusinessRule = Generator.generate(language, ongeGenereerdeBusinessRule.get(i));
                ConnectDBBusinessRule.saveBusinessrule(gegenereerdeBusinessRule);
                aantalBusinessRules++;
            }
            if(aantalBusinessRules == 1){
                returnMessage = "er is " + aantalBusinessRules + " businessrule gegenereerd.";
            } else {
                returnMessage = "er zijn " + aantalBusinessRules + " businessrules gegenereerd.";
            }

//            rd = req.getRequestDispatcher(???);
        }

        req.setAttribute("message", returnMessage);
        rd.forward(req, resp);
    }

}
