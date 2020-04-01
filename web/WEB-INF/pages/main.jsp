<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<%@ taglib prefix="ctg" uri="customtags" %>--%>

<c:if test="${sessionScope.locale == 'en' or empty sessionScope.locale}">
    <fmt:setLocale value="en" scope="session"/>
</c:if>
<c:if test="${sessionScope.locale == 'ru'}">
    <fmt:setLocale value="ru" scope="session"/>
</c:if>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <title><fmt:message key="title.main"/></title>
</head>
<body>
<div id="header">
    <%@include file="elements/header.jsp" %>
</div>
<div id="main">
    <div id="left-content">
        <div id="login">
            <%@include file="elements/login.jsp" %>
        </div>
        <div id="menu">
            <%@include file="elements/menu.jsp" %>
        </div>
    </div>
    <div id="right-content">
        <div id="content">
            <%@include file="elements/main_content.jsp" %>
        </div>
    </div>
    <div style="clear: left"></div>
    <%@include file="elements/footer_content.jsp" %>
</div>
</body>
</html>