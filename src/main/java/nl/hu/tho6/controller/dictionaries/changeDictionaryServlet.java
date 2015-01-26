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

public class changeDictionaryServlet extends HttpServlet {
    private String message;
    private boolean noEmptyFields = true;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = (String) request.getSession().getAttribute("language");
        ArrayList<Translation> elements = (ArrayList<Translation>) request.getSession().getAttribute("elements");
        RequestDispatcher rd;
        FileSystemFacade facade = new FileSystemFacade();
        rd = request.getRequestDispatcher("allDictionaries.jsp");

        for (int i = 0; i < elements.size(); i++) {
            String element = request.getParameter(elements.get(i).getElement());
            if (element == null || element.equals("")) {
                message = "Some elements are empty!";
                noEmptyFields = false;
                rd = request.getRequestDispatcher("changeDictionary.jsp");
            }
        }

        if (noEmptyFields) {
            Translator translator = Translator.getInstance();
            Dictionary dictionary = translator.selectDictionary(language);
            facade.deleteDictionary(dictionary);

            dictionary.setLanguage(language);
//            System.out.println("lanugage: " + dictionary.getLanguage());
            dictionary.addObserver(facade);
            for (int i = 0; i < elements.size(); i++) {
                elements.get(i).setLanguage(language);
                System.out.println(request.getParameter(elements.get(i).getElement()));
                elements.get(i).setElementTranslation(request.getParameter(elements.get(i).getElement()));
            }
            facade.writeDictionary(dictionary);

//            List<Translation> translations = dictionary.getTranslations();
//            for (int i = 0; i < elements.size(); i++) {
//                for (Translation translation : translations) {
//                    if (elements.get(i).equals(translation.getElement())){
//                        translation.setElementTranslation(request.getParameter(elements.get(i)));
//                    }
//                }
//            }
            message = "Dictionary is changed";


//
//            facade.writeDictionary(dictionary);
//            translator.addDictionary(dictionary);
        }

        request.setAttribute("message", message);
        rd.forward(request, response);
    }

}
