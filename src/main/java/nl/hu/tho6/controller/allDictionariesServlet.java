package nl.hu.tho6.controller;

import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.dictionary.Translation;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

public class allDictionariesServlet extends HttpServlet  {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("dictionary");
        String type = request.getParameter("type");

        RequestDispatcher rd;
        Translator translator = Translator.getInstance();
        Dictionary dictionary = translator.selectDictionary(language);

        List<Translation> translations = dictionary.getTranslations();
        request.getSession().setAttribute("elementen", translations);
        request.getSession().setAttribute("language", language);
        if(type.equals("Change")){
            rd = request.getRequestDispatcher("changeDictionary.jsp");
        } else {
            rd = request.getRequestDispatcher("deleteDictionary.jsp");
        }

        rd.forward(request, response);

    }

}
