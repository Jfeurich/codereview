package nl.hu.tho6.controller.dictionaries;

import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.dictionary.Translation;
import nl.hu.tho6.translator.filesystem.FileSystemFacade;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class defineElementsServlet extends HttpServlet {
    private String message;
    private boolean noEmptyFields = true;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("addDictionary.jsp");
        FileSystemFacade facade = new FileSystemFacade();
        String language = (String) request.getSession().getAttribute("language");
        ArrayList<String> elements = (ArrayList<String>) request.getSession().getAttribute("elements");

        for (String element1 : elements) {

            String element = request.getParameter(element1);
            if (element == null || element.equals("")) {
                message = "Some elements are empty!";
                noEmptyFields = false;
                rd = request.getRequestDispatcher("defineElements.jsp");
            }

        }

        if (noEmptyFields) {

            Dictionary dictionary = new Dictionary();

            dictionary.setLanguage(language);
            System.out.println("lanugage: " + dictionary.getLanguage());

            for (String element : elements) {
                Translation t = new Translation();
                t.setLanguage(language);
                t.setElement(element);
                t.setElementTranslation(request.getParameter(element));
                t.addObserver(dictionary);
                dictionary.addElementTranslation(t);
            }
            facade.writeDictionary(dictionary);
            Translator.addDictionary(dictionary);
        }


        request.setAttribute("message", message);
        rd.forward(request, response);
    }

}
