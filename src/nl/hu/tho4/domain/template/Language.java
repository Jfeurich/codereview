package nl.hu.tho4.domain.template;

public abstract class Language {
	public String name;
	public abstract void addTemplate(TemplateOld t);
	
	public abstract String getName();
	public abstract void setName(String name);
}
