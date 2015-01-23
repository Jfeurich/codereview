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
            <label>De elementen moeten tussen [ en ] gezet worden.</label>
            <div id="Code">
                <label>Code:</label><br><br>
                <textarea name="code"></textarea>
            </div>
            <div id="Elementen">
                <label>Element suggesties:</label><br><br>
                <label>[triggerNaam]</label><br>
                <label>[tableNaam]</label><br>
                <label>[condition]</label><br>
                <label>[variable]</label><br>
                <label>[code]</label><br>
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
