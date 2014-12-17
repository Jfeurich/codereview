package nl.hu.tho4.translator;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Translator {
	private String language;
	private Object businessrule;
	
	public final String Translator(String language) {
		String languagetemplate = null;
		switch (language){
		case "plsql" : try {
				languagetemplate = readFileToString("Stringtemplates/plsql.st");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}break;
		}
		return languagetemplate;
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

	public Object getBusinessrule() {
		return businessrule;
	}

	public void setBusinessrule(Object businessrule) {
		this.businessrule = businessrule;
	}

}
