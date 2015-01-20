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

    //TODO subject to change
    public ST generate(String language, BusinessRule businessRule) {
        //code vertalen naar pseudoCode
        ST pseudo = convertToPseudoCode(businessRule);
        //pseudoCode vertalen naar code voor language
        //        ST code = translator.translate(pseudo, language);
        ST code = new ST("");
        return code;
    }

    //TODO subject to change
    private Generator() {
        translator = Translator.getInstance();
    }

    //TODO subject to change
    private ST convertToPseudoCode(BusinessRule businessRule) {
        //dit klopt nog niet met wat we voor ogen hebben
        STGroup stGroup = new STGroupFile(PathUtils.PSEUDO_TEMPLATES_PATH + "pseudoTemplates.stg");
        ST pseudo = stGroup.getInstanceOf("AttCompare");
        pseudo.add("element", "HardCodedAttCompare");
        //TODO implement conversion to pseudo
        return pseudo;
    }
}
