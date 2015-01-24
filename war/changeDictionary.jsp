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

    <form action="changeDictionary.do" method="post">
      De elementen voor ${sessionScope.language}
      <br>
    <c:forEach items="${sessionScope.elements}" var="element">
      <c:choose>
        <c:when test="${element.getElement() eq 'LoadOtherTableIntoVariable' || element.getElement() eq 'Template'}">
          <label>[${element.getElement()}]</label> = <textarea name="${element.getElement()}"> ${element.getElementTranslation()} </textarea><br>
        </c:when>
        <c:otherwise>
          <label>[${element.getElement()}]</label> = <input type="text" name="${element.getElement()}" value="${element.getElementTranslation()}"><br>
        </c:otherwise>
      </c:choose>
       </c:forEach>
      <br>
      <input type="submit" value="Submit">
    </form>
    <jsp:include page="Footer.jsp" />
  </div>
</div>
</body>
</html>

