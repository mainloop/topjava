<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2>Meal list</h2>

<table>
    <c:forEach items="${mealList}" var="meal">

        <c:choose>
            <c:when test="${meal.exceed}">
               <tr style="color:red">
            </c:when>
            <c:otherwise>
                <tr style="color:green">
            </c:otherwise>
        </c:choose>
            <td>${meal.description}</td>
            <td>${meal.dateTime}</td>
            <td>${meal.calories}</td>
         </tr>
    </c:forEach>
</table>

</body>
</html>
