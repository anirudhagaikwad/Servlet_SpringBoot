<%@ taglib uri="http://jakarta.ee/taglibs/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL Formatting Tags Example</title>
</head>
<body>
    <fmt:setLocale value="en_US" />
    <fmt:formatNumber value="12345.678" type="currency" />
    <br/>
    <fmt:formatDate value="${pageContext.request.time}" pattern="yyyy-MM-dd HH:mm:ss" />
</body>
</html>
