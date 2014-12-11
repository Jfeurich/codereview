package nl.hu.tho4.template;

import java.util.ArrayList;

import nl.hu.tho4.domain.template.Template;

public abstract class Language {
	public String name;
	public ArrayList<Template> templates = new ArrayList<>();
	public abstract void addTemplate(Template t);
	
	public abstract String getName();
	public abstract void setName(String name);
}
