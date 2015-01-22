package nl.hu.tho6.controller;

import nl.hu.tho6.translator.Translator;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class deleteDictionaryServlet extends HttpServlet  {
    private String message;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");


        RequestDispatcher rd;
        Translator translator = Translator.getInstance();
        translator.removeDictionary(language);

        message = language + " is Deleted!";
        request.setAttribute("message", message);
        rd = request.getRequestDispatcher("allDictionaries.jsp");
        rd.forward(request, response);

    }

}
