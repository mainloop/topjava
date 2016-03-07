<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2>Meal list</h2>

<table>

    <c:forEach items="${mealList}" var="meal">
        <tr>
            <td>${meal.dateTime}</td>
            <td>${user.description}</td>
            <td>${user.calories}</td>
            <td>${user.exceed}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
