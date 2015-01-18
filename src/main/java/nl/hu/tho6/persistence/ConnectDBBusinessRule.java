package nl.hu.tho6.persistence;

import nl.hu.tho6.domain.businessrule.BusinessRule;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/*
 * Deze methode moet bevatten:
 * connect()						Maak verbinding met de Oracle DB
 * getBusinessrule()				Haal Businessrule op
 * saveBusinessrule()				Sla Businessrule op
 * getongegenereerdeBusinessrules()	Haal de te genereren Businessrules op
 * searchBusinessrule()				Zoek een businessrule op naam/tabel/etc
 *
 */

public class ConnectDBBusinessRule {
    private Connection con;

    public ConnectDBBusinessRule(Connection c) {
        con = c;
    }

    public ArrayList<BusinessRule> getBusinessRules() {
        //Haal de businessrules op uit de ruledatabase
        ArrayList<BusinessRule> rules = new ArrayList<BusinessRule>();
        try {
            String sql = "SELECT * FROM businessrule WHERE conditie";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //declare variables here
            }
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Kan geen businessrules halen uit de database" + ex);
        }
        return rules;
    }

    public BusinessRule saveBusinessrule(Connection target) {
        BusinessRule rule = new BusinessRule();
        try {
            String sql = "CREATE OR REPLACE ETC";
            Statement stmt = target.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //declare variables here
            }
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Kan businessrule niet vinden" + ex);
        }
        return rule;
    }

    public ArrayList<BusinessRule> getOngegenereerdeBusinessRules() {
        //Haal de businessrules op uit de ruledatabase
        ArrayList<BusinessRule> rules = new ArrayList<BusinessRule>();
        try {
            String sql = "SELECT * FROM businessrule WHERE BUSINESSRULE.RULEID = ONGEGENEREEDE_BUSINESSRULE_1.RULEID AND ONGEGENEREERDE_BUSINESSRULE_1.RULEID = 'NOT_GENERATED'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("RULEID");
                String rulenaam = rs.getString("RULENAAM");
                String error = rs.getString("ERROR");
                int errortype = rs.getInt("ERRORTYPE");
                int operator = rs.getInt("OPERATOR");
                int ruletype = rs.getInt("BUSINESSRULETYPE");
            }
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Kan geen businessrules halen uit de database" + ex);
        }
        return rules;
    }

    public BusinessRule searchBusinessRule() {
        //moeten misschien meerdere functies worden afhankelijk van waarop gezocht moet worden
        BusinessRule rule = new BusinessRule();
        try {
            String sql = "SELECT * FROM businessrules WHERE conditie AND";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //declare variables here
            }
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Kan businessrule niet vinden" + ex);
        }
        return rule;
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
