package nl.hu.tho6.persistence;

import nl.hu.tho6.domain.businessrule.Attribute;
import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.domain.businessrule.Operator;
import nl.hu.tho6.domain.businessrule.Value;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
//import java.util.Optional;

/*
 * Deze methode moet bevatten:
 * connect()						Maak verbinding met de Oracle DB
 * getBusinessrule()
 * getAttributes
 * getValues
 * getOperator				Haal Businessrule op
 * saveBusinessrule()				Sla Businessrule op
 * getongegenereerdeBusinessrules()	Haal de te genereren Businessrules op
 * changeBunsinessRuleStatus()
 */

public class ConnectDBBusinessRule {
    private Connection con;

    public ConnectDBBusinessRule(Connection c) {
        con = c;
    }

    /**
     * Haalt alle ongegenereerde businessrules op uit de database.
     */
    public ArrayList<BusinessRule> getOngegenereerdeBusinessRules() {
        //Haal de businessrules op uit de ruledatabase
        ArrayList<BusinessRule> rules = new ArrayList<BusinessRule>();
        try {
            String sql = "select BUSINESSRULE.RULEID as RULEID,\n" +
                    "BUSINESSRULE.RULENAAM as RULENAAM,\n" +
                    "BUSINESSRULE.ERROR as ERROR,\n" +
                    "BUSINESSRULE.ERRORTYPE as ERRORTYPE,\n" +
                    "BUSINESSRULE.OPERATOR as OPERATOR,\n" +
                    "BUSINESSRULE.BUSINESSRULETYPE as BUSINESSRULETYPE \n," +
                    "ONGEGENEREERDE_BUSINESSRULE.LANGUAGENAAM as LANGUAGE\n " +
                    " from ONGEGENEREERDE_BUSINESSRULE ,\n" +
                    "BUSINESSRULE \n" +
                    " where   BUSINESSRULE.RULEID=ONGEGENEREERDE_BUSINESSRULE.BUSINESSRULERULEID\n" +
                    " and     ONGEGENEREERDE_BUSINESSRULE.STATUS = 'NOT_GENERATED'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BusinessRule r = new BusinessRule();
                int id = rs.getInt("RULEID");
                String rulenaam = rs.getString("RULENAAM");
                String error = rs.getString("ERROR");
                String errortype = rs.getString("ERRORTYPE");
                String language = rs.getString("LANGUAGE");
                int operator = rs.getInt("OPERATOR");
                int ruletype = rs.getInt("BUSINESSRULETYPE");
                String code = "";
                //TODO dit gaat waarschijnlijk nog nullpointer excpetions opleveren
                // maar even kijken of dit handiger kan
                // TODO kijken of optional hier een optie is.
                ConnectDBBusinessRule con2 = new ConnectDBBusinessRule(con);
                ArrayList<Value> values = con2.getValue(id);
                ConnectDBBusinessRule con3 = new ConnectDBBusinessRule(con);
                Operator o = con3.getOperator(id);
                ConnectDBBusinessRule con4 = new ConnectDBBusinessRule(con);
                ArrayList<Attribute> attributes = con4.getAttribute(id);
                Attribute a1 = null;
                Attribute a2 = null;
                Value v1 = null;
                Value v2 = null;
                if (attributes.size() > 0) {
                    a1 = attributes.get(0);
                    if (attributes.size() > 1) {
                        a2 = attributes.get(1);
                    }
                }
                if (values.size() > 0) {
                    v1 = values.get(0);
                    if (values.size() > 1) {
                        v2 = values.get(1);
                    }
                }

                // r = BusinessRule(rulenaam,error,errortype,code,o,v1,v2,a1,a2)
                r.setRuleNaam(rulenaam);
                r.setError(error);
                r.setErrorType(errortype);
                r.setCode(code);
                r.setOperator(o);
                if(nulltest(v1)){
                    r.setValue1(v1);
                }
                if(nulltest(v2)){
                    r.setValue2(v2);
                }
                if(nulltest(a1)) {
                    r.setAttribute1(a1);
                }
                if(nulltest(a2)){
                    r.setAttribute2(a2);
                }
                r.setRuleID(id);
                r.setLanguage(language);
                rules.add(r);
            }
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Kan geen businessrules halen uit de database" + ex);
            ex.printStackTrace();
        }
        return rules;
    }
    /*Haalt alle attributes behorende bij de businessrule uit de DB*/
    public ArrayList<Attribute> getAttribute(int businessruleID){
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        try {
            String sql ="select ATTRIBUTE.ATTRIBUTENAAM as ATTRIBUTENAAM,\n" +
                        " ATTRIBUTE.DBSCHEMA as DBSCHEMA,\n" +
                        " ATTRIBUTE.TABEL as TABEL,\n" +
                        " ATTRIBUTE.KOLOM as KOLOM,\n" +
                        " ATTRIBUTE.ATTRIBUTEID as ATTRIBUTEID \n" +
                        " from BUSINESSRULE_ATTRIBUTE BUSINESSRULE_ATTRIBUTE,\n" +
                        " ONGEGENEREERDE_BUSINESSRULE ONGEGENEREERDE_BUSINESSRULE,\n" +
                        " BUSINESSRULE BUSINESSRULE,\n" +
                        " ATTRIBUTE ATTRIBUTE \n" +
                        " where   BUSINESSRULE_ATTRIBUTE.ATTRIBUTE=ATTRIBUTE.ATTRIBUTEID\n" +
                        "" +
                        " and BUSINESSRULE.RULEID="+ businessruleID;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int attributeid = rs.getInt("ATTRIBUTEID");
                String attributenaam = rs.getString("ATTRIBUTENAAM");
                String dbschema = rs.getString("DBSCHEMA");
                String tabel = rs.getString("TABEL");
                String kolom = rs.getString("KOLOM");
                Attribute a = new Attribute(attributenaam,dbschema,tabel,kolom,attributeid);
                attributes.add(a);
            }
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Kan geen attributes halen uit de database" + ex);
        }
        return attributes;
    }
    /*haal values uit de database behordende bij de database*/
    public ArrayList<Value> getValue(int businessruleID){
        ArrayList<Value> v = new ArrayList<Value>();
        try {
            String sql = "select VALUE.VALUEID as VALUEID,\n" +
                    " VALUE.WAARDENAAM as WAARDENAAM,\n" +
                    " VALUE.VALUETYPE as VALUETYPE,\n" +
                    " VALUE.VALUE as VALUECONTENT,\n" +
                    " VALUE.BUSINESSRULE as BUSINESSRULE \n" +
                    " from VALUE VALUE \n" +
                    " where   VALUE.BUSINESSRULE = " + businessruleID;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int valueID = rs.getInt("VALUEID");
                String waardenaam = rs.getString("WAARDENAAM");
                String valuetype = rs.getString("VALUETYPE");
                String value = rs.getString("VALUECONTENT");
                Value val = new Value(waardenaam,valuetype,value);
                v.add(val);
            }
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Kan geen values halen uit de database" + ex);
            ex.printStackTrace();
        }
        return v;
    }
    /*Haal de operator behorende bijde businessrule uit de database*/
    public Operator getOperator(int businessruleID){
        Operator op = null;
        try {
            String sql =    "select OPERATOR.OPERATORNAAM as OPERATORNAAM," +
                            "OPERATOR.OPERATORTYPE as OPERATORTYPE \n" +
                            "from BUSINESSRULE BUSINESSRULE,\n" +
                            "OPERATOR OPERATOR \n" +
                            "where   OPERATOR.OPERATORID=BUSINESSRULE.OPERATOR\n" +
                            "and  BUSINESSRULE.RULEID ="+businessruleID;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String naam = rs.getString("OPERATORNAAM");
                String operatortype = rs.getString("OPERATORTYPE");
                op = new Operator(naam,operatortype);
            }
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Kan geen operator halen uit de database" + ex);
        }
        return op;
    }
    /*Controleer of een object null returnt */
    public boolean nulltest(Object o){
        if(o!=null){
            return true;
        }
        return false;
    }

    /*Sla de gemaakte businessrule op in de oracle database.*/
    // TODO: pas de savebusinessrule aan zodat hij de businessrule als string opslaat in de apex database.
    public void saveBusinessRule(String BUSINESSRULENAAM,String LANGUAGENAAM, String CODE){
        try {
            String sql = "INSERT INTO GEGENEREERDE_BUSINESSRULE (GENID,BUSINESSRULENAAM,LANGUAGENAAM,CODE) VALUES (SEQ_GEGENEREERDE_BUSINESSRULE.NEXTVAL,'" + BUSINESSRULENAAM + "', '" + LANGUAGENAAM + "', '" + CODE +"')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Kan gemaakte businessrule niet opslaan in de database" + ex);
        }
    }

    public void changeBusinessRuleStatus(){
        try {
            String sql ="UPDATE ONGEGENEREERDE_BUSINESSRULE" +
                        "SET STATUS = 'GENERATED' " +
                        "WHERE EXISTS (" +
                        "SELECT ONGEGENEREERDE_BUSINESSRULE.STATUS," +
                        "ONGEGENEREERDE_BUSINESSRULE.LANGUAGE" +
                        "FROM GEGENEREERDE_BUSINESSRULE," +
                        "BUSINESSRULE" +
                        "ONGEGENEREERDE_BUSINESSRULE" +
                        "WHERE ONGEGENEREERDE_BUSINESSRULE.BUSINESSRULEID=BUSINESSRULE.RULEID" +
                        "AND BUSINESSRULE.RULENAAM=GEGENEREERDE_BUSINESSRULE.BUSINESSRULENAAM" +
                        "AND ONGEGENEREERDE_BUSINESSRULE.STATUS='NOT_GENERATED')" +
                        "AND ONGEGENEREERDE_BUSINESSRULE.LANGUAGE=GEGENEREERDE_BUSINESSRULE.LANGUAGENAAM";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch(Exception ex){
            System.out.println("Kan de status van de ongegenereerde businessrule niet veranderen");
            ex.printStackTrace();
        }
    }
}
