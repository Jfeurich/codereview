package nl.hu.tho4.persistence;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import nl.hu.tho4.domain.businessrule.BusinessRule;
import nl.hu.tho4.persistence.ConnectionFactory;

public class ConnectDBBusinessRule {
	private Connection con;
	//Connectie
	public ConnectDBBusinessRule(OracleConnection c){
		con = c;
	}
	
	public ArrayList<BusinessRule> getBusinessRules() {
		//Haal de businessrules op uit de ruledatabase
		ArrayList<BusinessRule> rules = new ArrayList<BusinessRule>();
		try{
			String sql = "SELECT * FROM businessrules WHERE conditie";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {  
				//declare variables here
			}
			stmt.close();
		}
		catch(Exception ex){
			System.out.println("Kan geen businessrules halen uit de database" + ex);
		}
		return rules;
	}
	
	public BusinessRule saveBusinessrule(Connection target){
		BusinessRule rule = new Businessrule();
		try{
			String sql = "CREATE OR REPLACE ETC";
			Statement stmt = target.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {  
				//declare variables here
			}
			stmt.close();
		}
		catch(Exception ex){
			System.out.println("Kan businessrule niet vinden" + ex);
		}
		return rule;
	}
	public ArrayList<BusinessRule> getOngegenereerdeBusinessRules() {
		//Haal de businessrules op uit de ruledatabase
		ArrayList<BusinessRule> rules = new ArrayList<BusinessRule>();
		try{
			String sql = "SELECT * FROM businessrules WHERE conditie";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {  
				//declare variables here
			}
			stmt.close();
		}
		catch(Exception ex){
			System.out.println("Kan geen businessrules halen uit de database" + ex);
		}
		return rules;
	}
	public BusinessRule searchBusinessRule(){
		//moeten misschien meerdere functies worden afhankelijk van waarop gezocht moet worden
		BusinessRule rule = new Businessrule();
		try{
			String sql = "SELECT * FROM businessrules WHERE conditie AND";
			Statement stmt = target.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {  
				//declare variables here
			}
			stmt.close();
		}
		catch(Exception ex){
			System.out.println("Kan businessrule niet vinden" + ex);
		}
		return rule;
	}

}
/*
 * Deze methode moet bevatten:
 * 
 * <ArrayList>Businessrule getBusinessrules()				Haal Businessrule op uit DB
 * void saveBusinessrule(Connection c)				Sla Businessrule op in target DB
 * getongegenereerdeBusinessrules()	Haal de te genereren Businessrules op uit een arraylist?
 * searchBusinessrule()				Zoek een businessrule op naam/tabel/etc uit de database
 *  
 */
