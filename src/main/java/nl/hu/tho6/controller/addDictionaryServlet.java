package nl.hu.tho6.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class addDictionaryServlet extends HttpServlet {
    private ArrayList<String> elements = new ArrayList<String>();
    private String message;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        String language = request.getParameter("language");
        String code = request.getParameter("code");

        if(language == null || language.equals("")){
            message = "language is empty";
            rd = request.getRequestDispatcher("addDictionary");
        } else if(code == null || code.equals("")){
            message = "code is empty";
            rd = request.getRequestDispatcher("addDictionary");
        } else {
//        TODO
//        Code om uit de String code de elementen te halen
//        Code om vaste elementen toe te voegen
//        elements.add(element);

            HttpSession session = request.getSession();
            session.setAttribute("elements", elements);

            rd = request.getRequestDispatcher("defineElements.jsp");

        }
        request.setAttribute("message", message);
        rd.forward(request, response);
    }

}
