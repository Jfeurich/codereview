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
    public List<BusinessRule> getOngegenereerdeBusinessRules() {
        Statement stmt;
        //Haal de businessrules op uit de ruledatabase
        List<BusinessRule> rules = new ArrayList<>();
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
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BusinessRule r = new BusinessRule();
                int id = rs.getInt("RULEID");
                String rulenaam = rs.getString("RULENAAM");
                String error = rs.getString("ERROR");
                String errortype = rs.getString("ERRORTYPE");
                String language = rs.getString("LANGUAGE");
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
                if (attributes.isEmpty()) {
                    a1 = attributes.get(0);
                    if (attributes.size() > 1) {
                        a2 = attributes.get(1);
                    }
                }
                if (values.isEmpty()) {
                    v1 = values.get(0);
                    if (values.size() > 1) {
                        v2 = values.get(1);
                    }
                }
                r.setRuleNaam(rulenaam);
                r.setError(error);
                r.setErrorType(errortype);
                r.setCode(code);
                r.setOperator(o);
                r.setBusinessruletype(ruletype);
                if(a1 != null && a2 != null && a1.getTabel() != a2.getTabel()){
                    setReferences(a1,a2);
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
            ex.printStackTrace();
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }
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
            ex.printStackTrace();
        }
    }

    private void setReferences(Attribute a1, Attribute a2) {
        Statement stmt;
        try {
            String url = "jdbc:oracle:thin:@//ondora01.hu.nl:8521/cursus01.hu.nl";
            String user = "tho6_2014_2b_team3_target";
            String password = " tho6_2014_2b_team3_target";
            Connection tempcon = ConnectionFactory.getTargetConnection(url,user,password);
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
            stmt = tempcon.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String source1 = rs.getString("CONSTRAINT_SOURCE");
                String source2 = rs.getString("REFERENCES_COLUMN");
                String[] sourceSplit = source1.split("\\.");
                String[] sourceSplit2 = source2.split("\\.");
                if(a1.getTabel().equals(sourceSplit[0])){
                    a1.setReference(sourceSplit[1]);
                    a2.setReference(sourceSplit2[1]);
                } else {
                    a1.setReference(sourceSplit2[1]);
                    a2.setReference(sourceSplit[1]);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    /*Haalt alle attributes behorende bij de businessrule uit de DB*/
    public List<Attribute> getAttribute(int businessruleID){
        Statement stmt;
        List<Attribute> attributes = new ArrayList<>();
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
            stmt = con.createStatement();
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }
        }
        return attributes;
    }
    /*haal values uit de database behordende bij de database*/
    public List<Value> getValue(int businessruleID){
        Statement stmt;
        List<Value> v = new ArrayList<>();
        try {
            String sql = "select VALUE.VALUEID as VALUEID,\n" +
                    " VALUE.WAARDENAAM as WAARDENAAM,\n" +
                    " VALUE.VALUETYPE as VALUETYPE,\n" +
                    " VALUE.VALUE as VALUECONTENT,\n" +
                    " VALUE.BUSINESSRULE as BUSINESSRULE \n" +
                    " from VALUE VALUE \n" +
                    " where   VALUE.BUSINESSRULE = " + businessruleID;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String waardenaam = rs.getString("WAARDENAAM");
                String valuetype = rs.getString("VALUETYPE");
                String value = rs.getString("VALUECONTENT");
                Value val = new Value(waardenaam,valuetype,value);
                v.add(val);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }
        }
        return v;
    }
    /*Haal de operator behorende bijde businessrule uit de database*/
    public Operator getOperator(int businessruleID){
        Statement stmt;
        Operator op = null;
        try {
            String sql =    "select OPERATOR.OPERATORNAAM as OPERATORNAAM," +
                            "OPERATOR.OPERATORTYPE as OPERATORTYPE \n" +
                            "from BUSINESSRULE BUSINESSRULE,\n" +
                            "OPERATOR OPERATOR \n" +
                            "where   OPERATOR.OPERATORID=BUSINESSRULE.OPERATOR\n" +
                            "and  BUSINESSRULE.RULEID ="+businessruleID;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String naam = rs.getString("OPERATORNAAM");
                String operatortype = rs.getString("OPERATORTYPE");
                op = new Operator(naam,operatortype);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }
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
    public void saveBusinessRule(String businessrulenaam,String languagenaam, String code){
        Statement stmt;
        try {
            String sql =    "SELECT *\n" +
                            "FROM GEGENEREERDE_BUSINESSRULE\n" +
                            "WHERE GEGENEREERDE_BUSINESSRULE.BUSINESSRULENAAM = '" + businessrulenaam + "'\n" +
                            "AND GEGENEREERDE_BUSINESSRULE.LANGUAGENAAM = '" + languagenaam + "'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                PreparedStatement updateStatement = con.prepareStatement("UPDATE GEGENEREERDE_BUSINESSRULE \n" +
                        "SET CODE = ? \n" +
                        "WHERE GEGENEREERDE_BUSINESSRULE.BUSINESSRULENAAM = ?\n" +
                        "AND GEGENEREERDE_BUSINESSRULE.LANGUAGENAAM = ?");
                updateStatement.setString(1, code);
                updateStatement.setString(2, businessrulenaam);
                updateStatement.setString(3, languagenaam);
                updateStatement.executeQuery();
                updateStatement.close();
            } else {
                try {
                    PreparedStatement updateStatement = con.prepareStatement("INSERT INTO GEGENEREERDE_BUSINESSRULE (GENID,BUSINESSRULENAAM,LANGUAGENAAM,CODE) VALUES (SEQ_GEGENEREERDE_BUSINESSRULE.NEXTVAL,?,?,?)");
                    updateStatement.setString(1, businessrulenaam);
                    updateStatement.setString(2, languagenaam);
                    updateStatement.setString(3, code);
                    updateStatement.executeQuery();
                    updateStatement.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    public void runCode(String code){
        Statement stmt;
        try {
            String url = "jdbc:oracle:thin:@//ondora01.hu.nl:8521/cursus01.hu.nl";
            String user = "tho6_2014_2b_team3_target";
            String password = " tho6_2014_2b_team3_target";
            Connection tempcon = ConnectionFactory.getTargetConnection(url,user,password);
            stmt = tempcon.createStatement();
            stmt.executeUpdate(code);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }

    /*Verander de status van de gegenereerde businessrule in de database.*/
    public void changeBusinessRuleStatus(int businessruleid, String status){
        Statement stmt;
        try {
            String sql ="UPDATE ONGEGENEREERDE_BUSINESSRULE \n" +
                        "SET STATUS = '" + status + "' \n" +
                        "WHERE ONGEGENEREERDE_BUSINESSRULE.BUSINESSRULERULEID = " + businessruleid;
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
}
