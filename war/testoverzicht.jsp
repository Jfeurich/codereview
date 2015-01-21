<%--
  Created by IntelliJ IDEA.
  User: jay
  Date: 21/01/2015
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="nl">
<body>
<%@ page import="java.sql.Connection" %>
<div id="container">
  <jsp:include page="Header.jsp">
    <jsp:param name="subTitle" value="Dit is een testpagina" />
  </jsp:include>
  <div class="content">
    <jsp:include page="Menu.jsp" />
    <div class="messageBox">${message}</div>
    <form action="TestServlet.do" method="post">
      <input type="submit" value="Submit">
    </form>
    <jsp:include page="Footer.jsp" />
  </div>
</div>
</body>
</html>
