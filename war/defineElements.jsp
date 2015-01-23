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
        <c:choose>
          <c:when test="${element eq 'ErrorFunction'}">
          <label>[${element}]</label> = <input type="text" name="${element}"> <label>Use [ErrorMessage] inside the ErrorFunction</label><br>
          </c:when>
          <c:when test="${element eq 'LoadOtherTableIntoVariable'}">
            <label>[${element}]</label> = <textarea name="${element}"></textarea> <label>Use [Variable] inside the LoadOtherTableIntoVariable</label><br>
          </c:when>
          <c:otherwise>
            <label>[${element}]</label> = <input type="text" name="${element}"><br>
          </c:otherwise>
        </c:choose>

         </c:forEach>
      <input type="submit" value="Submit">
    </form>
    <jsp:include page="Footer.jsp" />
  </div>
</div>
</body>
</html>
