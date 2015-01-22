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
                <label>Code:</label>
                <textarea name="code"></textarea>
            </div>
            <div id="Elementen">
                <label>Element suggesties:</label>
                <label>[triggerNaam]</label>
                <label>[tableNaam]</label>
                <label>[condition]</label>
                <label>[variable]</label>
                <label>[code]</label>
            </div>
            <input type="submit" value="Submit">
        </form>
        <jsp:include page="Footer.jsp" />
    </div>
</div>
</body>
</html>
