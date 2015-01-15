package nl.hu.tho4.persistence;

import nl.hu.tho4.domain.businessrule.BusinessRule;
import nl.hu.tho4.persistence.ConnectionFactory;

public class ConnectDBBusinessRule {
	private ConnectionFactory connectionFactory;


	public void ConectDBBusinessRule(ConnectionFactory cf){
		this.connectionFactory = cf;
		DBConnection connection = connectionFactory.getConnection();
	    connection.connect();
	}
	public void getBusinessRule(BusinessRule r){

	}

	public void parseStrings() {
	/*	for (String string : businessRuleStrings) {
			
		} */
	}
}
/*
 * Deze methode moet bevatten:
 * connect()						Maak verbinding met de Oracle DB
 * getBusinessrule()				Haal Businessrule op
 * saveBusinessrule()				Sla Businessrule op
 * getongegenereerdeBusinessrules()	Haal de te genereren Businessrules op
 * searchBusinessrule()				Zoek een businessrule op naam/tabel/etc
 *  
 */
