package nl.hu.tho4.controller.generator;

import nl.hu.tho4.domain.businessrule.BusinessRule;
import nl.hu.tho4.translator.Translator;
import org.stringtemplate.v4.ST;

import java.io.PrintWriter;

/**
 * Created by Liam on 17-12-2014.
 */
public class Generator {
    private static Generator ourInstance = new Generator();
    Translator translator;

    public static Generator getInstance() {
        return ourInstance;
    }

    public void generate(String language, BusinessRule br){
        ST st = new ST("hier zou normaal gesproken de pseudocode komen te staan");
        ST result = translator.translate(st, language);
        writeFile(result.render());
    }

    private void writeFile(String s){
        try{
            PrintWriter out = new PrintWriter("lib/testfile.txt");
            out.println(s);
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private Generator() {
        translator = new Translator();
    }
}
