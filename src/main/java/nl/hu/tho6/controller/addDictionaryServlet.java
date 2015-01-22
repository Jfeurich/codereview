package nl.hu.tho6.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;


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
            if(!elements.contains("Error")){
                elements.add("Error");
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
        Pattern p = Pattern.compile("[(.*?)]");
        Matcher m = p.matcher(element);

        while (m.find()) {
            elements.add(m.group(1));
        }
        return elements;
    }
}
