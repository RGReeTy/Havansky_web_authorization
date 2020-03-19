<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Welcome</title></head>
<div style="text-align: center;">
    <body><h3>Welcome</h3>
    <hr/>
    <c:out value="${user}, Hello!"/>
    <hr/>


    <h3>
        <a href="logout.jsp" onclick="signout">Logout</a>
    </h3>
    <form method="get" action="controller" name="signOutForm">
        <input type="hidden" name="command" value="signout"/>
        <input type="submit" name="btn_login" value="Sign Out">
    </form>




</div>
</body>
</html>