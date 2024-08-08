<%@ taglib uri="http://jakarta.ee/taglibs/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL Core Tags Example</title>
</head>
<body>
    <c:set var="userName" value="JakartaUser" />
    <p>Username: <c:out value="${userName}" /></p>

    <c:choose>
        <c:when test="${userName == 'JakartaUser'}">
            <p>Welcome, JakartaUser!</p>
        </c:when>
        <c:otherwise>
            <p>Welcome, Guest!</p>
        </c:otherwise>
    </c:choose>

    <c:forEach var="i" begin="1" end="5">
        <p>Number: ${i}</p>
    </c:forEach>
</body>
</html>
