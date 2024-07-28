<%@ taglib uri="http://jakarta.ee/taglibs/xml" prefix="x" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL XML Tags Example</title>
</head>
<body>
    <x:parse var="doc" xml="<users><user><id>1</id><name>John</name></user><user><id>2</id><name>Jane</name></user></users>" />
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        <x:forEach select="$doc/users/user">
            <tr>
                <td><x:out select="id" /></td>
                <td><x:out select="name" /></td>
            </tr>
        </x:forEach>
    </table>
</body>
</html>
