<%@ taglib uri="http://jakarta.ee/taglibs/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL SQL Tags Example</title>
</head>
<body>
    <sql:setDataSource var="ds" driver="com.mysql.cj.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/testdb"
                       user="root" password="password" />
    <sql:query dataSource="${ds}" var="result">
        SELECT * FROM users
    </sql:query>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Username</th>
        </tr>
        <c:forEach var="row" items="${result.rows}">
            <tr>
                <td><c:out value="${row.id}" /></td>
                <td><c:out value="${row.username}" /></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
