package nl.hu.tho4.translator;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import nl.hu.tho4.domain.businessrule.BusinessRule;

import org.stringtemplate.v4.*;

public class Translator {
	private String language;
	private BusinessRule businessrule;
	public String Translator(String language, BusinessRule businessrule){
		ST query = new ST(Translatorlanguage(language));
		query.add("$trigger_name$",generateTriggerName());
		query.add("$table_name$",businessrule.getAttribute1().getTabel());
		for(int i = 0;i<4;i++){
			query.add("$value_declarations$","declareval");
		}
		query.add("$trigger_code$",generateTriggerCode());
		query.add("$exception_code$",generateExceptionCode());
		return query.render();
	}
	
	private String generateTriggerName(){
		//applicatienaam_entiteit_typerestrictie_soortrule
		String s ="";
		return s;
	}
	
	private String generateTriggerCode(){
		String s ="";
		return s;
	}
	
	private String generateExceptionCode(){
		String s ="";
		return s;
	}
	
	private final String Translatorlanguage(String language) {
//		String languagetemplate = null;
//		try{
//		switch (language){
//			case "plsql" : languagetemplate = readFileToString("Stringtemplates/plsql.st");break;
//			case "mysql" : languagetemplate = readFileToString("Stringtemplates/mysql.st");break;
//			}
//		}
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return languagetemplate;
		return "";
	}
	
	private String readFileToString(String pathname) throws IOException {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {        
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public BusinessRule getBusinessrule() {
		return businessrule;
	}

	public void setBusinessrule(BusinessRule businessrule) {
		this.businessrule = businessrule;
	}

	public ST translate(ST st, String language) {
		return null;
	}
}
