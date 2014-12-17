package nl.hu.tho4;


import nl.hu.tho4.controller.generator.Generator;
import nl.hu.tho4.domain.businessrule.Attribute;
import nl.hu.tho4.domain.businessrule.BusinessRule;
import nl.hu.tho4.domain.businessrule.Operator;

@SuppressWarnings ("unused")
public class Main {
    public static void main(String[] args) {
        Attribute att1 = new Attribute("Attribuut 1", "db1", "Tabel 1", "Kolom 1", "Value 1");
        Attribute att2 = new Attribute("Attribuut 2", "db1", "Tabel 1", "Kolom 2", "Value 2");
        Operator op = new Operator("Groter Dan", "gt");
        BusinessRule br = new BusinessRule("AttCompareRule", "Error", "ErrorType", "Code", op, null, null, att1, att2);

        Generator generator = Generator.getInstance();
        generator.generateCode(br, "plsql");
    }

}
