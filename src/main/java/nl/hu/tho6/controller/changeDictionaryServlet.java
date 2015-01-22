package nl.hu.tho6.controller;

import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.dictionary.Translation;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class changeDictionaryServlet extends HttpServlet {
    private String message;
    private boolean noEmptyFields = true;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        ArrayList<String> elements = (ArrayList<String>) request.getSession().getAttribute("elements");
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("allDictionaries.jsp");
        for (int i = 0; i < elements.size(); i++) {
            String element = request.getParameter(elements.get(i));
            if (element == null || element.equals("")) {
                message = "Some elements are empty!";
                noEmptyFields = false;
            }
            rd = request.getRequestDispatcher("changeDictionary.jsp");
        }

        if (noEmptyFields) {
            Translator translator = Translator.getInstance();
            Dictionary dictionary = translator.selectDictionary(language);
            List<Translation> translations = dictionary.getTranslations();
            for (int i = 0; i < elements.size(); i++) {
                for (Translation translation : translations) {
                    if (elements.get(i).equals(translation.getElement())){
                        translation.setElementTranslation(request.getParameter(elements.get(i)));
                    }
                }
            }
            message = "Dictionary is changed";
        }

        request.setAttribute("message", message);
        rd.forward(request, response);
    }

}
