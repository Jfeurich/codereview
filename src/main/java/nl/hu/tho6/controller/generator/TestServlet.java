package nl.hu.tho6.controller.generator;

        import nl.hu.tho6.domain.businessrule.BusinessRule;
        import nl.hu.tho6.persistence.ConnectDBBusinessRule;
        import nl.hu.tho6.persistence.connection.ConnectionFactory;

        import java.io.IOException;
        import java.sql.Connection;
        import java.util.ArrayList;
        import javax.servlet.RequestDispatcher;
        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private ArrayList<BusinessRule> ongeGenereerdeBusinessRule = new ArrayList<BusinessRule>();
    private String returnMessage;
    private String language = "PL/SQL";
    private int aantalBusinessRules = 0;
    private Generator generator = Generator.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher rd = req.getRequestDispatcher("testoverzicht.jsp");
        String button = req.getParameter("button");
        if(button.equals("genereertest")) {
            Connection con = ConnectionFactory.getConnection();
            ConnectDBBusinessRule cdbbr = new ConnectDBBusinessRule(con);
            ongeGenereerdeBusinessRule = cdbbr.getOngegenereerdeBusinessRules();
            String gegenereerdeBusinessRule = null;
            if (ongeGenereerdeBusinessRule.size() == 0) {
                returnMessage = "Er zijn geen businessrules te genereren.";
            } else {
                for (int i = 0; i < ongeGenereerdeBusinessRule.size(); i++) {
                    gegenereerdeBusinessRule = generator.generate(language, ongeGenereerdeBusinessRule.get(i));
                    cdbbr.saveBusinessRule(gegenereerdeBusinessRule);
                    aantalBusinessRules++;
                }
                if (aantalBusinessRules == 1) {
                    returnMessage = "er is " + aantalBusinessRules + " businessrule gegenereerd.";
                } else {
                    returnMessage = "er zijn " + aantalBusinessRules + " businessrules gegenereerd.";
                }

            }
//
//            String session = req.getParameter("SESSION");
//            resp.sendRedirect("https://ondora01.hu.nl:8080/apex/f?p=2298:1:" + session + "::::P1_TEST:" + returnMessage);
            req.getSession().setAttribute("gegenereerdeString", gegenereerdeBusinessRule);
        }
        rd.forward(req, resp);
    }

}
