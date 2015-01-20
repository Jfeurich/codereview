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
    <form action="defineElements.do" method="post">

      <c:forEach items="${sessionScope.elements}" var="element">
        <c:if test="${fn.toLowerCase(element) eq 'triggername'}">
          <label>&lt;triggerName&gt;</label> = <input type="text" name="triggerName">
        </c:if>
        <c:if test="${fn.toLowerCase(element) eq 'tablename'}">
          <label>&lt;tableName&gt;</label> = <input type="text" name="tableName">
        </c:if>
        <c:if test="${fn.toLowerCase(element) eq 'begin if'}">
          <label>&lt;begin if&gt;</label> = <input type="text" name="beginIf">
        </c:if>
        <c:if test="${fn.toLowerCase(element) eq 'end if'}">
          <label>&lt;end if&gt;</label> = <input type="text" name="endIf">
        </c:if>
        <label>&lt;${element}&gt;</label> = <input type="text" name="${element}">
      </c:forEach>
      <input type="submit" value="Submit">
    </form>
    <jsp:include page="Footer.jsp" />
  </div>
</div>
</body>
</html>
