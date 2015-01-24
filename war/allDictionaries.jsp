<%@ page import="nl.hu.tho6.translator.Translator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="nl.hu.tho6.translator.dictionary.Dictionary" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

        <form action="allDictionaries.do" method="post">
            <%
                Translator translator = Translator.getInstance();
                ArrayList<Dictionary> dictionaries = translator.getDictionaries();
                if(dictionaries.size() != 0){
                for(Dictionary dictionary : dictionaries){
            %>
            <select name="language">

            <option><%=dictionary.getLanguage()%></option>
            </select>
            <br>
            <input type="submit" name="type" value="Change">
            <input type="submit" name="type" value="Delete">
            <%
                }} else {
            %>
                There are no languages.
            <%
                }
            %>

        </form>


        <jsp:include page="Footer.jsp" />
    </div>
</div>
</body>
</html>
