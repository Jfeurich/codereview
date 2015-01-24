package nl.hu.tho6.controller;

import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.translator.filesystem.FileSystemFacade;
import nl.hu.tho6.translator.filesystem.types.impl.XMLFileSystem;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class deleteDictionaryServlet extends HttpServlet  {
    private String message;
    FileSystemFacade facade = new FileSystemFacade(new XMLFileSystem());


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConcurrentModificationException {
        String language = (String) request.getSession().getAttribute("language");
        String type = request.getParameter("type");
        RequestDispatcher rd;
        if(type.equals("Delete")){
            message = language + " is Deleted!";
            Translator translator = Translator.getInstance();

            facade.deleteDictionary(translator.selectDictionary(language));
            translator.removeDictionary(language);
        }

        request.setAttribute("message", message);
        rd = request.getRequestDispatcher("allDictionaries.jsp");

        rd.forward(request, response);

    }

}
