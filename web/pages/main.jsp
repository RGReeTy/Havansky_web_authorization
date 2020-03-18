<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Welcome</title></head>
<div style="text-align: center;">
    <body><h3>Welcome</h3>
    <hr/>
    <c:out value="${user}, Hello!"/>
    <hr/>
    <!--<a href="controller">Return to login page</a>-->

<%--    <h2>--%>
<%--        <%--%>
<%--            if (session.getAttribute("login") == null || session.getAttribute("login") == "") {//check if condition for unauthorize user not direct access welcome.jsp page--%>
<%--                response.sendRedirect("index.jsp");--%>
<%--            }--%>
<%--        %>--%>
<%--        Welcome, <%=session.getAttribute("login")%>--%>
<%--    </h2>--%>


    <h3>
        <a href="logout.jsp">Logout</a>
    </h3>
</div>
</body>
</html>