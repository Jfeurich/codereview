package nl.hu.tho6.controller;

import nl.hu.tho6.translator.Translator;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class deleteDictionaryServlet extends HttpServlet  {
    private String message;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = (String) request.getSession().getAttribute("language");
        System.out.println(language);
        String type = request.getParameter("type");
        RequestDispatcher rd;
        if(type.equals("Delete")){
            message = language + " is Deleted!";
            Translator translator = Translator.getInstance();
            translator.removeDictionary(language);

        }

        request.setAttribute("message", message);
        rd = request.getRequestDispatcher("allDictionaries.jsp");

        rd.forward(request, response);

    }

}
