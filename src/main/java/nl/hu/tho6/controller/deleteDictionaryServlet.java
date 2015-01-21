package nl.hu.tho6.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class deleteDictionaryServlet extends HttpServlet  {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dictionary = request.getParameter("dictionary");


        RequestDispatcher rd;
//        TODO
//        delete dictionary and all its elements

        rd = request.getRequestDispatcher("allDictionaries.jsp");
        rd.forward(request, response);

    }

}
