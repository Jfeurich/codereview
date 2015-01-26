package nl.hu.tho6.persistence;

import nl.hu.tho6.domain.businessrule.Attribute;
import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.domain.businessrule.Operator;
import nl.hu.tho6.domain.businessrule.Value;
import nl.hu.tho6.persistence.connection.ConnectionFactory;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
//import java.util.Optional;

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
                String output = "RuleID: " + id + ", " + attributes.size() + " ATT, " + values.size() + " VAL \t"  + rulenaam;
                System.out.println(output);
                // r = BusinessRule(rulenaam,error,errortype,code,o,v1,v2,a1,a2)
                r.setRuleNaam(rulenaam);
                r.setError(error);
                r.setErrorType(errortype);
                r.setCode(code);
                r.setOperator(o);
                r.setBusinessruletype(ruletype);
                if(a1 != null && a2 != null){
                    if(a1.getTabel() != a2.getTabel()){
                        setReferences(a1,a2);
                    }
                }
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

    public void saveToErrorLog(String error, String businessruleid){
        try {
            PreparedStatement updateStatement = con.prepareStatement("INSERT INTO ERROR_LOG (ERRORID,ERRORMESSAGE,DATUM,\"user\",TABLENAME) VALUES (SEQ_ERROR_LOG.NEXTVAL,?,SYSDATE,'JAVA',?)");
            updateStatement.setString(1,error);
            updateStatement.setString(2,businessruleid);
            updateStatement.executeQuery();
            updateStatement.close();
        } catch (Exception ex) {
            System.out.println("Kan gemaakte businessrule niet opslaan in de database" + ex);
            ex.printStackTrace();
        }
    }

    private void setReferences(Attribute a1, Attribute a2) {
        System.out.println("Getting references for the attributes");
        try {
            String URL = "jdbc:oracle:thin:@//ondora01.hu.nl:8521/cursus01.hu.nl";
            String USER = "tho6_2014_2b_team3_target";
            String PASSWORD = " tho6_2014_2b_team3_target";
            Connection tempcon = ConnectionFactory.getTargetConnection(URL,USER,PASSWORD);
            String sql = "SELECT UCC1.TABLE_NAME||'.'||UCC1.COLUMN_NAME CONSTRAINT_SOURCE,\n" +
                    "UCC2.TABLE_NAME||'.'||UCC2.COLUMN_NAME REFERENCES_COLUMN\n" +
                    "FROM USER_CONSTRAINTS uc,\n" +
                    "USER_CONS_COLUMNS ucc1,\n" +
                    "USER_CONS_COLUMNS ucc2\n" +
                    "WHERE UC.CONSTRAINT_NAME = UCC1.CONSTRAINT_NAME\n" +
                    "AND UC.R_CONSTRAINT_NAME = UCC2.CONSTRAINT_NAME\n" +
                    "AND UCC1.POSITION = UCC2.POSITION\n" +
                    "AND UC.CONSTRAINT_TYPE = 'R'\n" +
                    "AND (UCC1.TABLE_NAME = '" + a1.getTabel() + "' OR UCC2.TABLE_NAME = '" + a1.getTabel() + "')\n" +
                    "AND (UCC1.TABLE_NAME = '" + a2.getTabel() + "' OR UCC2.TABLE_NAME = '" + a2.getTabel() + "')\n";
            Statement stmt = tempcon.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String source1 = rs.getString("CONSTRAINT_SOURCE");
                String source2 = rs.getString("REFERENCES_COLUMN");
                System.out.println(source1);
                System.out.println(source2);
                String[] sourceSplit = source1.split("\\.");
                String[] sourceSplit2 = source2.split("\\.");
                if(a1.getTabel().equals(sourceSplit[0])){
                    a1.setReference(sourceSplit[1]);
                    a2.setReference(sourceSplit2[1]);
                } else {
                    a1.setReference(sourceSplit2[1]);
                    a2.setReference(sourceSplit[1]);
                }
                System.out.println("A1 reference: " + a1.getReference());
                System.out.println("A2 reference: " + a2.getReference());
            }
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Het lukt niet om de references op te halen" + ex);
            ex.printStackTrace();
        }
    }

    /*Haalt alle attributes behorende bij de businessrule uit de DB*/
    public ArrayList<Attribute> getAttribute(int businessruleID){
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        try {
            String sql = "select ATTRIBUTE.ATTRIBUTENAAM as ATTRIBUTENAAM,\n" +
                    " ATTRIBUTE.DBSCHEMA as DBSCHEMA,\n" +
                    " ATTRIBUTE.TABEL as TABEL,\n" +
                    " ATTRIBUTE.KOLOM as KOLOM,\n" +
                    " ATTRIBUTE.ATTRIBUTEID as ATTRIBUTEID \n" +
                    " from BUSINESSRULE_ATTRIBUTE BUSINESSRULE_ATTRIBUTE,\n" +
                    " ATTRIBUTE ATTRIBUTE \n" +
                    " where   BUSINESSRULE_ATTRIBUTE.BUSINESSRULE="+ businessruleID + "\n" +
                    " and BUSINESSRULE_ATTRIBUTE.ATTRIBUTE=ATTRIBUTE.ATTRIBUTEID";
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
            String sql =    "SELECT *\n" +
                            "FROM GEGENEREERDE_BUSINESSRULE\n" +
                            "WHERE GEGENEREERDE_BUSINESSRULE.BUSINESSRULENAAM = '" + BUSINESSRULENAAM + "'\n" +
                            "AND GEGENEREERDE_BUSINESSRULE.LANGUAGENAAM = '" + LANGUAGENAAM + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                PreparedStatement updateStatement = con.prepareStatement("UPDATE GEGENEREERDE_BUSINESSRULE \n" +
                        "SET CODE = ? \n" +
                        "WHERE GEGENEREERDE_BUSINESSRULE.BUSINESSRULENAAM = ?\n" +
                        "AND GEGENEREERDE_BUSINESSRULE.LANGUAGENAAM = ?");
                updateStatement.setString(1, CODE);
                updateStatement.setString(2, BUSINESSRULENAAM);
                updateStatement.setString(3, LANGUAGENAAM);
                updateStatement.executeQuery();
                updateStatement.close();
            } else {
                try {
                    PreparedStatement updateStatement = con.prepareStatement("INSERT INTO GEGENEREERDE_BUSINESSRULE (GENID,BUSINESSRULENAAM,LANGUAGENAAM,CODE) VALUES (SEQ_GEGENEREERDE_BUSINESSRULE.NEXTVAL,?,?,?)");
                    updateStatement.setString(1, BUSINESSRULENAAM);
                    updateStatement.setString(2, LANGUAGENAAM);
                    updateStatement.setString(3, CODE);
                    updateStatement.executeQuery();
                    updateStatement.close();
                } catch (Exception ex) {
                    System.out.println("Kan gemaakte businessrule niet opslaan in de database" + ex);
                    ex.printStackTrace();
                }
            }
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Kan gemaakte businessrule niet opslaan in de database" + ex);
            ex.printStackTrace();
        }
    }

    public void runCode(String code){
        try {
            String URL = "jdbc:oracle:thin:@//ondora01.hu.nl:8521/cursus01.hu.nl";
            String USER = "tho6_2014_2b_team3_target";
            String PASSWORD = " tho6_2014_2b_team3_target";
            Connection tempcon = ConnectionFactory.getTargetConnection(URL,USER,PASSWORD);
            Statement stmt = tempcon.createStatement();
            stmt.executeUpdate(code);
            stmt.close();
        } catch (Exception ex) {
            System.out.println("Het lukt niet om de references op te halen" + ex);
            ex.printStackTrace();
        }
    }

    /*Verander de status van de gegenereerde businessrule in de database.*/
    public void changeBusinessRuleStatus(int businessruleid, String status){
        try {
            String sql ="UPDATE ONGEGENEREERDE_BUSINESSRULE \n" +
                        "SET STATUS = '" + status + "' \n" +
                        "WHERE ONGEGENEREERDE_BUSINESSRULE.BUSINESSRULERULEID = " + businessruleid;
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
