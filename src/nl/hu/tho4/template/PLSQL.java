package nl.hu.tho4.template;

import java.util.ArrayList;

import nl.hu.tho4.domain.template.Template;

public class PLSQL extends Language{
	private String name;
	private ArrayList<Template> templates = new ArrayList<>();
	public void addTemplate(Template t) {
		templates.add(t);		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Template> getLanguages() {
		return templates;
	}

	public void setLanguages(ArrayList<Template> templates) {
		this.templates = templates;
	}
}
