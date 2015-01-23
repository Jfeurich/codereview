<!DOCTYPE html>
<html lang="nl">
<body>
<div id="container">
    <jsp:include page="Header.jsp">
        <jsp:param name="subTitle" value="Add Dictionary" />
    </jsp:include>
    <div class="content">
        <jsp:include page="Menu.jsp" />
        <div class="messageBox">${message}</div>
        <form action="addDictionary.do" method="post">
            <label>Language: </label><input type="text" name="language" value="${param.language}">
            <br><br>
            <label>De elementen moeten tussen [ en ] gezet worden.</label><br>
            <div id="Code">
                <label>Template:</label><br><br>
                <textarea name="code"></textarea>
            </div>
            <div id="Elementen">
                <label>Element suggesties:</label><br><br>
                <label>[TriggerName]</label><br>
                <label>[TimeOperator]</label><br>
                <label>[TableName]</label><br>
                <label>[Variables]</label><br>
                <label>[Conditions]</label><br>
                <label>[Error]</label><br>
            </div>
            <div id="Submit">
                <input type="submit" value="Submit">
            </div>
        </form>
        <jsp:include page="Footer.jsp" />
    </div>
</div>
</body>
</html>
