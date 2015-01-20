<!DOCTYPE html>
<html lang="nl">
<body>
<div id="container">
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <jsp:include page="Header.jsp">
    <jsp:param name="subTitle" value="Define Elements" />
  </jsp:include>
  <div class="content">
    <jsp:include page="Menu.jsp" />
    <div class="messageBox">${message}</div>
    <form action="defineElements.do" method="post">

      <c:forEach items="${sessionScope.elements}" var="element">
        <label>&lt;${element}&gt;</label> = <input type="text" name="${element}">
      </c:forEach>
      <input type="submit" value="Submit">
    </form>
    <jsp:include page="Footer.jsp" />
  </div>
</div>
</body>
</html>
