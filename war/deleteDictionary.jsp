<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="nl">
<body>
<div id="container">
  <jsp:include page="Header.jsp">
    <jsp:param name="subTitle" value="Change Dictionary" />
  </jsp:include>
  <div class="content">
    <jsp:include page="Menu.jsp" />
    <div class="messageBox">${message}</div>

    Are you sure you want to delete this dictionary and all its elements?
    ${dictionary}

    <c:forEach items="${sessionScope.elements}" var="element">
      <label>&lt;${element}&gt;</label> = <label>${elementvalue}</label>
    </c:forEach>

    <a href="nl.hu.tho6.contoller.deleteDictionaryServlet?dictionary=${dictionary}">Delete</a> <a href="allDictionaries.jsp">Cancel</a>

    <jsp:include page="Footer.jsp" />
  </div>
</div>
</body>
</html>