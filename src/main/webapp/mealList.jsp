<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meal list</title>

    <script language="JavaScript" type="text/javascript">
        <!--
        function getsupport(selectedtype) {
            document.supportform.supporttype.value = selectedtype;
            document.supportform.submit();
        }
        -->
    </script>


</head>
<body>
<h2>Meal list</h2>

${testing}

<form name="supportform" method="post">
    <input type="hidden" name="supporttype" />
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
        <td><a href="meals/?=">delete</a>


        </td>
        <td>
            <a href="javascript:getsupport('Paid')">Paid Support</a> or
        </td>>
        </tr>
    </c:forEach>
</table>
</form>>


</body>
</html>
