<%
    if (session.getAttribute("login") != null) {
        response.sendRedirect("welcome.jsp"); //session login user not back to index.jsp page or not type direct in url
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Java Login & Register</title>
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

<div style="text-align: center;">

    <h2>Login</h2>

    <form method="post" action="controller" name="LoginForm" onsubmit="return validate();">
        <input type="hidden" name="command" value="login"/>
        Username :<input type="text" name="login" value="">
        Password :<input type="password" name="password" value="">

        <input type="submit" name="btn_login" value="Login">

        <h3>Your don't have an account? <a href="register.jsp">Register</a></h3>

    </form>

</div>
</body>
</html>

