package domain;

public class BusinessRuleType {
	public String toelichting;
	public String naam;
	
	public String getNaam(){
		return this.naam;
	}
	public void setNaam(String s){
		this.naam = s;
	}
	
	public String getToelichting(){
		return this.toelichting;
	}
	public void setToelichting(String s){
		this.toelichting = s;
		
	}

}
