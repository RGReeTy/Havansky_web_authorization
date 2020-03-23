<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Welcome</title></head>
<div style="text-align: center;">
    <body><h3>Welcome</h3>
    <hr/>
    <c:out value="${user}, Hello!"/>
    <hr/>

    <div>
        <form method="get" action="controller" name="signOutForm">
            <input type="hidden" name="command" value="getOrderInfoCommand"/>
            <input type="submit" name="btn_getInfo" value="Get order's info">
        </form>

    </div>

    <c:if test="${not empty orders}">
    <h3>${user} orders:</h3>
    <ul>
        <c:forEach items="${sessionScope.orders}" var="order">
            <li>
                <c:out value="${order}"/>
            </li>
        </c:forEach>
        </c:if>

    </ul>


    <form method="get" action="controller" name="signOutForm">
        <input type="hidden" name="command" value="signout"/>
        <input type="submit" name="btn_login" value="Sign Out">
    </form>


</div>
</body>
</html>