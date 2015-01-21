package nl.hu.tho6.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class changeDictionaryServlet extends HttpServlet {
    private String message;
    private boolean noEmptyFields = true;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("elements") != null){

            ArrayList<String> elements = (ArrayList<String>) request.getSession().getAttribute("elements");

            for(int i = 0; i < elements.size(); i++){
                String element = request.getParameter("elements.get(i)");
                if(element == null || elements.equals("")){
                    message = "Some elements are empty!";
                    noEmptyFields = false;
                }

            }

            if(noEmptyFields) {
                for (int i = 0; i < elements.size(); i++) {
//            TODO
//            verander element in Dictionary
                }
            }
        }

        RequestDispatcher rd;



        rd = request.getRequestDispatcher("allDictionaries.jsp");
        request.setAttribute("message", message);
        rd.forward(request, response);
    }

}
