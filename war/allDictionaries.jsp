<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="nl">
<body>
<div id="container">
    <jsp:include page="Header.jsp">
        <jsp:param name="subTitle" value="All Dictionaries" />
    </jsp:include>
    <div class="content">
        <jsp:include page="Menu.jsp" />
        <div class="messageBox">${message}</div>

        <%--TODO DICTIONARIES!--%>
        <c:set var="translator" value="${Translator.getInstance()}" />
        <c:set var="dictionaries" value="${translator.getDictionaries}" />
        <c:forEach items="${dictionaries}" var="dictionary">
            <label>${dictionary.getLanguage}</label><a href="nl.hu.tho6.controller.allDictionariesServlet?language=${dictionary.getLanguage}?type=Change">Change</a><a href="nl.hu.tho6.controller.allDictionariesServlet?language=${dictionary.getLanguage}?type=Delete">Delete</a>
        </c:forEach>

        <jsp:include page="Footer.jsp" />
    </div>
</div>
</body>
</html>
