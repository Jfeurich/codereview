package nl.hu.tho6.controller.dictionaries;

import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.translator.filesystem.FileSystemFacade;
import nl.hu.tho6.translator.filesystem.types.impl.XMLFileSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ConcurrentModificationException;

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
