package nl.hu.tho6.controller.generator;

import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.utils.file.PathUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * Created by Liam on 17-12-2014.
 */
public class Generator {
    private static Generator ourInstance = new Generator();
    private Translator translator;

    public static Generator getInstance() {
        return ourInstance;
    }

    public ST generate(String language, BusinessRule businessRule) {
        ST pseudo = convertToPseudoCode(businessRule);
        ST code = translator.translate(pseudo, language);
        return code;
    }

    private Generator() {
        translator = Translator.getInstance();
    }

    private ST convertToPseudoCode(BusinessRule businessRule) {
        STGroup stGroup = new STGroupFile(PathUtils.PSEUDO_TEMPLATES_PATH + "pseudoTemplates.stg");
        ST pseudo = stGroup.getInstanceOf("AttCompare");
        pseudo.add("element", "HardCodedAttCompare");
        //TODO implement conversion
        return pseudo;
    }
}
