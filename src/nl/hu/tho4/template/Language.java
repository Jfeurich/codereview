package nl.hu.tho4.template;

public abstract class Language {
	public String name;
	public abstract void addTemplate(Template t);
	
	public abstract String getName();
	public abstract void setName(String name);
}
