package nl.hu.tho4.template;

import java.util.ArrayList;

public abstract class Language {
	public String name;
	public ArrayList<Language> languages = new ArrayList<>();
	public abstract void addTemplate(Template t);
	
	public abstract String getName();
	public abstract void setName(String name);
}
