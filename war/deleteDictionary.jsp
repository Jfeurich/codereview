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
    <div class="messageBox">${message}</div><br>

    Are you sure you want to delete this dictionary and all its elements? <br><br>
    ${sessionScope.language}<br><br>
    <form action="deleteDictionary.do" method="post">
    <c:forEach items="${sessionScope.elements}" var="element">
      <label>[${element.getElement()}]</label> = <label>${element.getElementTranslation()}</label><br>
    </c:forEach>
    <br>
    <input type="submit" name="type" value="Delete">
    <input type="submit" name="type" value="Cancel">
    </form>
    <jsp:include page="Footer.jsp" />
  </div>
</div>
</body>
</html>