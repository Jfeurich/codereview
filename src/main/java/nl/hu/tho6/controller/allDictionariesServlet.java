package nl.hu.tho6.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class allDictionariesServlet extends HttpServlet  {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dictionary = request.getParameter("dictionary");
        String type = request.getParameter("type");

        RequestDispatcher rd;
//        TODO
//        get dictionary elements
//        put in session

        request.setAttribute("dictioinary", dictionary);
        if(type.equals("Change")){
            rd = request.getRequestDispatcher("changeDictionary.jsp");
        } else {
            rd = request.getRequestDispatcher("deleteDictionary.jsp");
        }

        rd.forward(request, response);

    }

}
