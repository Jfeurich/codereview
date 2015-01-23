package nl.hu.tho6.persistence.connection;

import nl.hu.tho6.domain.businessrule.Attribute;
import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.domain.businessrule.Operator;
import nl.hu.tho6.domain.businessrule.Value;
import nl.hu.tho6.persistence.ConnectDBBusinessRule;
import nl.hu.tho6.persistence.connection.ConnectionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/** 
* ConnectDBBusinessRule Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 23, 2015</pre> 
* @version 1.0 
*/ 
public class ConnectDBBusinessRuleTest { 

@Before
public void before() throws Exception {

} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getOngegenereerdeBusinessRules() 
* 
*/ 
@Test
public void testGetOngegenereerdeBusinessRules() throws Exception {
    Connection con = ConnectionFactory.getConnection();
    ConnectDBBusinessRule cdbbr = new ConnectDBBusinessRule(con);
    ArrayList<BusinessRule> hurf = cdbbr.getOngegenereerdeBusinessRules();
    BusinessRule r = hurf.get(0);
    Assert.assertEquals(r.getRuleID(),361);
    Assert.assertEquals(r.getAttribute1().getAttributeNaam(),"Postcode");
    Assert.assertEquals(r.getValue1().getWaardeNaam(), "");
    Assert.assertEquals(r.getOperator().getNaam(),"");
    Assert.assertEquals(r.getValue2(),null);
    Assert.assertEquals(r.getAttribute2(),null);

} 

/** 
* 
* Method: getAttribute(int businessruleID) 
* 
*/ 
@Test
public void testGetAttribute() throws Exception { 
    Connection con = ConnectionFactory.getConnection();
    ConnectDBBusinessRule cdbbr = new ConnectDBBusinessRule(con);
    ArrayList<Attribute> abr = cdbbr.getAttribute(324);
    Attribute a = abr.get(0);
    Assert.assertEquals(a.getAttributeID(),214);
    Assert.assertEquals(a.getAttributeNaam(),"MEDEWERKERNAAM");
    Assert.assertEquals(a.getKolom(),"NAAM");
    Assert.assertEquals(a.getDbSchema(),"target");
    Attribute a2 = abr.get(1);
    Assert.assertEquals(a2.getAttributeID(),215);
    Assert.assertEquals(a2.getAttributeNaam(),"TEAMNAAM");
    Assert.assertEquals(a2.getKolom(),"NAAM");
    Assert.assertEquals(a2.getDbSchema(),"target");
    Attribute a3 = abr.get(2);
    Assert.assertEquals(a3.getAttributeID(),null);
    Assert.assertEquals(a3.getAttributeNaam(),null);
    Assert.assertEquals(a3.getKolom(),null);
    Assert.assertEquals(a3.getDbSchema(),null);
} 

/** 
* 
* Method: getValue(int businessruleID) 
* 
*/ 
@Test
public void testGetValue() throws Exception {
    Connection con = ConnectionFactory.getConnection();
    ConnectDBBusinessRule cdbbr = new ConnectDBBusinessRule(con);
    ArrayList<Value> val = cdbbr.getValue(260);
    Value v1 = val.get(0);
    Assert.assertEquals(v1.getValueID(),184);
    Assert.assertEquals(v1.getWaardeNaam(),"iets");
    Assert.assertEquals(v1.getValueType(),"NUMBER");
    Assert.assertEquals(v1.getValue(),100);
    Value v2 = val.get(1);
    Assert.assertEquals(v2.getValueID(),185);
    Assert.assertEquals(v2.getWaardeNaam(),"iets");
    Assert.assertEquals(v2.getValueType(),"NUMBER");
    Assert.assertEquals(v2.getValue(),500);
    Value v3 = val.get(2);
    Assert.assertEquals(v3.getValueID(),null);
    Assert.assertEquals(v3.getWaardeNaam(),null);
    Assert.assertEquals(v3.getValueType(),null);
} 

/** 
* 
* Method: getOperator(int businessruleID) 
* 
*/ 
@Test
public void testGetOperator() throws Exception {
    Connection con = ConnectionFactory.getConnection();
    ConnectDBBusinessRule cdbbr = new ConnectDBBusinessRule(con);
    Operator op = cdbbr.getOperator(296);
    Assert.assertEquals(op.getOperatorID(),5);
    Assert.assertEquals(op.getNaam(),"EqualsTo");
    Assert.assertEquals(op.getOperatorType(),1);
    Operator op2 = cdbbr.getOperator(288);
    Assert.assertEquals(op2.getOperatorID(),7);
    Assert.assertEquals(op2.getNaam(),"Between");
    Assert.assertEquals(op2.getOperatorType(),1);
} 

/** 
* 
* Method: nulltest(Object o) 
* 
*/ 
@Test
public void testNulltest() throws Exception {
    Connection con = ConnectionFactory.getConnection();
    ConnectDBBusinessRule cdbbr = new ConnectDBBusinessRule(con);
    Assert.assertEquals(cdbbr.nulltest(null),false);
    Assert.assertEquals(cdbbr.nulltest("test"),true);
} 

/** 
* 
* Method: saveBusinessRule(String BUSINESSRULENAAM, String LANGUAGENAAM, String CODE) 
* 
*/ 
@Test
public void testSaveBusinessRule() throws Exception {
    Connection con = ConnectionFactory.getConnection();
    ConnectDBBusinessRule cdbbr = new ConnectDBBusinessRule(con);
    cdbbr.saveBusinessRule("test", "test", "test");
    String sql =" SELECT BUSINESSRULENAAM,LANGUAGENAAM,CODE" +
                " FROM GEGENEREERDE_BUSINESSRULE" +
                " WHERE CODE='test'";
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    String t1 = null;
    String t2 = null;
    String t3 = null;
    while (rs.next()) {
        t1 = rs.getString("BUSINESSRULENAAM");
        t2 = rs.getString("LANGUAGENAAM");
        t3 = rs.getString("CODE");
    }
    Assert.assertEquals(t1, "test");
    Assert.assertEquals(t2, "test");
    Assert.assertEquals(t3, "test");


} 

/** 
* 
* Method: changeBusinessRuleStatus() 
* 
*/ 
@Test
public void testChangeBusinessRuleStatus() throws Exception {
    Connection con = ConnectionFactory.getConnection();
    ConnectDBBusinessRule cdbbr = new ConnectDBBusinessRule(con);
} 


} 
