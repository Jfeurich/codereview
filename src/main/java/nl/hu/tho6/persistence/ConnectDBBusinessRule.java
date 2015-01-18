package nl.hu.tho6.persistence;

import nl.hu.tho6.domain.businessrule.Attribute;
import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.domain.businessrule.Value;

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

//    public ArrayList<BusinessRule> getBusinessRules() {
//        //Haal de businessrules op uit de ruledatabase
//        ArrayList<BusinessRule> rules = new ArrayList<BusinessRule>();
//        try {
//            String sql = "SELECT * FROM businessrule WHERE conditie";
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                //declare variables here
//            }
//            stmt.close();
//        } catch (Exception ex) {
//            System.out.println("Kan geen businessrules halen uit de database" + ex);
//        }
//        return rules;
//    }

//    public BusinessRule saveBusinessrule(Connection target) {
//        BusinessRule rule = new BusinessRule();
//        try {
//            String sql = "CREATE OR REPLACE ETC";
//            Statement stmt = target.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                //declare variables here
//            }
//            stmt.close();
//        } catch (Exception ex) {
//            System.out.println("Kan businessrule niet vinden" + ex);
//        }
//        return rule;
//    }

    public ArrayList<BusinessRule> getOngegenereerdeBusinessRules() {
        //Haal de businessrules op uit de ruledatabase
        ArrayList<BusinessRule> rules = new ArrayList<BusinessRule>();
        try {
            String sql = "select\t \"BUSINESSRULE\".\"RULEID\" as \"RULEID\",\n" +
                    "\t \"BUSINESSRULE\".\"RULENAAM\" as \"RULENAAM\",\n" +
                    "\t \"BUSINESSRULE\".\"ERROR\" as \"ERROR\",\n" +
                    "\t \"BUSINESSRULE\".\"ERRORTYPE\" as \"ERRORTYPE\",\n" +
                    "\t \"BUSINESSRULE\".\"OPERATOR\" as \"OPERATOR\",\n" +
                    "\t \"BUSINESSRULE\".\"BUSINESSRULETYPE\" as \"BUSINESSRULETYPE\" \n" +
                    " from\t \"ONGEGENEREERDE_BUSINESSRULE\" \"ONGEGENEREERDE_BUSINESSRULE_1\",\n" +
                    "\t \"BUSINESSRULE\" \"BUSINESSRULE\" \n" +
                    " where   \"BUSINESSRULE\".\"RULEID\"=\"ONGEGENEREERDE_BUSINESSRULE_1\".\"BUSINESSRULERULEID\"\n" +
                    " and     \"ONGEGENEREERDE_BUSINESSRULE_1\".\"STATUS\" = 'NOT_GENERATED'";
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

    public ArrayList<Attribute> getAttribute(){
        ArrayList<Attribute> a = null;
        try {
            String sql = "";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

            }
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Kan geen businessrules halen uit de database" + ex);
        }
        return a;
    }

    public ArrayList<Value> getValue(){
        ArrayList<Value> v = null;

        return v;
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
