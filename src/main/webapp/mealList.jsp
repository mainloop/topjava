<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meal list</title>

    <script language="JavaScript" type="text/javascript">
        <!--
        function getoperation(operation,identificator) {
            document.actionform.operation.value = operation;
            document.actionform.identificator.value = identificator;
            document.actionform.submit();
        }
        -->
    </script>


</head>
<body>
<h2>Meal list</h2>


<form method="post">
      <input type="hidden" name="id" value="${id}" />
      <label for="description">description</label>
      <input id="description" name="description" value="${description}">
      <br>
      <label for="calories">calories</label>
      <input id="calories" name="calories" value="${calories}">
      <br>
      <input type="submit" 	value="save" >
</form>


<a>
${operation} </a>
<br>
<a>
${identificator}
</a>



<form name="actionform" method="post">
    <input type="hidden" name="operation" value="" />
    <input type="hidden" name="identificator" value="" />


<table>
    <c:forEach items="${mealList}" var="meal">
        <tr style="color:"${meal.exceed? 'red':'green'}">
        <td>${meal.description}</td>
        <td>${meal.dateTime}</td>
        <td>${meal.calories}</td>
        <td>

            <a href="javaScript:{getoperation('${meal.id}')}">Delete</a>

            <a href='javascript:getoperation("delete",1)'></a>

                </td>
        <td>
           <!-- <a href="javascript:getoperation('edit','${meal.id}')">Edit</a> -->
        </td>
        </tr>
    </c:forEach>
</table>

</form>>

</body>
</html>
