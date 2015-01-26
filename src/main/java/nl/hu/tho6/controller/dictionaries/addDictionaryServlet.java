package nl.hu.tho6.controller.dictionaries;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class addDictionaryServlet extends HttpServlet {
    private ArrayList<String> elements = new ArrayList<String>();
    private String message;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        String language = request.getParameter("language");
        String code = request.getParameter("code");

        if(language == null || language.equals("")){
            message = "language is empty";
            rd = request.getRequestDispatcher("addDictionary.jsp");
        } else if(code == null || code.equals("")){
            message = "code is empty";
            rd = request.getRequestDispatcher("addDictionary.jsp");
        } else {
            elements = tagsInTemplate(code);
            if(elements.contains("TriggerName")){
                elements.remove("TriggerName");
            }
            if(elements.contains("TimeOperator")){
                elements.remove("TimeOperator");
            }
            if(elements.contains("TableName")){
                elements.remove("TableName");
            }
            if(elements.contains("Variables")){
                elements.remove("Variables");
            }
            if(elements.contains("Conditions")){
                elements.remove("Conditions");
            }
            if(elements.contains("Error")){
                elements.remove("Error");
            }
            if(!elements.contains("GreaterThanOrEqualTo")){
                elements.add("GreaterThanOrEqualTo");
            }
            if(!elements.contains("LesserThanOrEqualTo")){
                elements.add("LesserThanOrEqualTo");
            }
            if(!elements.contains("GreaterThan")){
                elements.add("GreaterThan");
            }
            if(!elements.contains("LesserThan")){
                elements.add("LesserThan");
            }
            if(!elements.contains("EqualsTo")){
                elements.add("EqualsTo");
            }
            if(!elements.contains("NotEqualsTo")){
                elements.add("NotEqualsTo");
            }
            if(!elements.contains("IsNot")){
                elements.add("IsNot");
            }
            if(!elements.contains("ErrorFunction")){
                elements.add("ErrorFunction");
            }
            if(!elements.contains("DefautError")){
                elements.add("DefautError");
            }
            if(!elements.contains("Assign")){
                elements.add("Assign");
            }
            if(!elements.contains("And")){
                elements.add("And");
            }
            if(!elements.contains("Or")){
                elements.add("Or");
            }
            if(!elements.contains("LoadOtherTableIntoVariable")){
                elements.add("LoadOtherTableIntoVariable");
            }

            HttpSession session = request.getSession();
            session.setAttribute("language", language);
            session.setAttribute("elements", elements);

            rd = request.getRequestDispatcher("defineElements.jsp");

        }
        request.setAttribute("message", message);
        rd.forward(request, response);
    }

    public static ArrayList<String> tagsInTemplate(String element) {
        ArrayList<String> elements = new ArrayList<String>();
        Pattern p = Pattern.compile("\\[([^\\]]+)]");
        Matcher m = p.matcher(element);

        while (m.find()) {
            elements.add(m.group(1));
        }
        return elements;
    }
}
