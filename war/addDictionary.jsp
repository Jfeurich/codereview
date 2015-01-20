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
            <label>Language: </label><input type="text" name="language">
            <br><br>
            <label>De elementen moeten tussen &lt; en &gt; gezet worden.</label>
            <div id="Code">
                <label>Code:</label>
                <textarea name="code"></textarea>
            </div>
            <div id="Elementen">
                <label>Element suggesties:</label>
                <label>&lt;triggerNaam&gt;</label>
                <label>&lt;tableNaam&gt;</label>
                <label>&lt;condition&gt;</label>
                <label>&lt;variable&gt;</label>
                <label>&lt;code&gt;</label>
            </div>
            <input type="submit" value="Submit">
        </form>
        <jsp:include page="Footer.jsp" />
    </div>
</div>
</body>
</html>
