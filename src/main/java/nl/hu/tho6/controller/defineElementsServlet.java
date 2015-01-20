package nl.hu.tho6.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class defineElementsServlet extends HttpServlet {
    private String message;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("elements") != null){

            ArrayList<String> elements = (ArrayList<String>) request.getSession().getAttribute("elements");
            for(int i = 0; i < elements.size(); i++){
//            String element = request.getParameter("elements.get(i)");
//            if(element != null || elements.equals("")){
//                message = "Some elements are empty!";
//            }
//            TODO
//            Voeg element toe aan Dictionary
            }

        }

        RequestDispatcher rd;



        rd = request.getRequestDispatcher("dictionaryOverview.jsp");
        request.setAttribute("message", message);
        rd.forward(request, response);
    }

}
