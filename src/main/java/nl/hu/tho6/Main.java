package nl.hu.tho6;

import nl.hu.tho6.controller.generator.Generator;
import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.translator.dictionary.exception.DictionaryNotFoundException;
import nl.hu.tho6.translator.filesystem.FileSystemFacade;
import nl.hu.tho6.translator.filesystem.types.impl.XMLFileSystem;
import org.stringtemplate.v4.ST;

public class Main {
    public static void main(String[] args) {
        FileSystemFacade fs = new FileSystemFacade(new XMLFileSystem());
        try {
            Translator.addDictionary(fs.readDictionary("plsql"));
        } catch (DictionaryNotFoundException e) {
            e.printStackTrace();
        }

        Generator gen = Generator.getInstance();
        ST code = gen.generate("plsql", new BusinessRule());
        System.out.println(code.render());
    }
}
