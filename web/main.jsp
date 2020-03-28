<%@ page language="java" contentType="text/html; charset=UTF-8"         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head><title>Welcome</title></head>
<div style="text-align: center;">

    <body><h3>Welcome</h3>
    <hr/>
    <c:out value="${user}, Hello!"/>
    <hr/>

    <div>
        <form method="get" action="controller" name="getOrderInfoCommand">
            <input type="hidden" name="command" value="getOrderInfoCommand"/>
            <input type="submit" name="btn_getInfo" value="Get order's info">

            <c:out value="${orders}"/>

            <c:forEach items="${requestScope.orders}" var="order">
                <c:out value="${orders}"/>
                <c:out value="${order.Id}"/>

            </c:forEach>
        </form>

    </div>

    <div style="width: 80%; padding-left: 18%; padding-top: 30px">
        <table id="orderTable">
            <tr>
                <th><fmt:message key="orders.id" /></th>
                <th><fmt:message key="orders.status" /></th>
                <th><fmt:message key="orders.action" /></th>
            </tr>
            <c:forEach items="${requestScope.orders}" var="order">
                <tr>
                    ${order.Id}
                    <td>
                        ${order.Id} : ${order.Customer_nickname} : ${order.CreatedAt} : ${order.Count}
                    </td>

                    <td>


<%--                        <form method="POST" action="Controller">--%>
<%--                        <input name="action" type="hidden" value="show_order" /> <input--%>
<%--                            name="order_id" type="hidden" value="${order.id}" /> <input--%>
<%--                            style="border-style: none; cursor: pointer; background-color: white; text-decoration: underline"--%>
<%--                            type="submit" value="${order.id}" />--%>
<%--                    </form>--%>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>



    <form method="get" action="controller" name="signOutForm">
        <input type="hidden" name="command" value="signout"/>
        <input type="submit" name="btn_login" value="Sign Out">
    </form>


</div>
</body>
</html>