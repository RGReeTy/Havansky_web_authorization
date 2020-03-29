<%@ page language="java" contentType="text/html; charset=UTF-8"         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script language="javascript">
        function validate() {
            var username = document.LoginForm.text; //get form name "LoginForm" and textbox name "txt_username" store in variable username
            var password = document.LoginForm.password; //get form name "LoginForm" and textbox name "txt_password" store in variable password

            if (username.value == null || username.value == "") {//check username textbox not blank
                window.alert("please enter username !"); //alert message
                username.style.background = '#f08080'; //set textbox color
                username.focus();
                return false;
            }
            if (password.value == null || password.value == "") {//check password textbox not blank
                window.alert("please enter password !"); //alert message
                password.style.background = '#f08080'; //set textbox color
                password.focus();
                return false;
            }
        }
    </script>
</head>
<body>
<c:choose>
    <c:when test="${empty sessionScope.user}">
        <div id="innerlogin">

            <form method="post" action="controller" name="LoginForm" onsubmit="return validate();">
                <input type="hidden" name="command" value="login"/>
                Username :<input type="text" name="login" value="">
                Password :<input type="password" name="password" value="">

                <input type="submit" name="btn_login" value="Login">

                <h3>Your don't have an account? <a href="register.jsp">Register</a></h3>
            </form>

            <div id="register">
                <a href="controller?action=go_to_page&page=path.page.register">
                    <fmt:message key="menu.button.register"/></a>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div id="innerlogin">
            <div style="font-size: 15px; text-align: center">
                <fmt:message key="menu.user.appeal"/>
                <b style="color: red;">${sessionScope.user.login}</b>
            </div>
            <div>
                <form method="POST" action="controller">
                    <input name="action" type="hidden" value="show_account"/> <input
                        class="button" type="submit" name="accounts"
                        value="<fmt:message key="menu.button.accounts" />">
                </form>
            </div>
            <div>
                <form method="POST" action="controller">
                    <input name="action" type="hidden" value="show_orders"/> <input
                        class="button" type="submit" name="orders"
                        value="<fmt:message key="menu.button.orders" />">
                </form>
            </div>
            <c:if test="${sessionScope.user.accessLevel == 1 }">
                <form action="controller" method="POST">
                    <input name="action" type="hidden" value="all_orders_page"/> <input
                        class="button" type="submit"
                        value="<fmt:message key="menu.button.allorders"/>"/>
                </form>
                <form action="controller" method="POST">
                    <input name="action" type="hidden" value="users_page"/><input
                        class="button" type="submit"
                        value="<fmt:message key="menu.button.users"/>"/>
                </form>
            </c:if>
            <div>
                <form method="POST" action="controller">
                    <input name="action" type="hidden" value="logout"/> <input
                        class="button" type="submit" name="log_out"
                        value="<fmt:message key="menu.button.exit" />">
                </form>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>