<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<ul>
    <li>
        <form action="controller" method="POST">
            <input name="action" type="hidden" value="show_travels"/> <input
                name="product_type" type="hidden" value="Shopping"/> <input
                class="button" type="submit"
                value="Шоппинг"/>
        </form>
    </li>
    <li>
        <form action="controller" method="POST">
            <input name="action" type="hidden" value="show_travels"/> <input
                name="product_type" type="hidden" value="Excursion"/> <input
                class="button" type="submit"
                value="Экскурсии"/>
        </form>
    </li>
    <li>
        <form action="controller" method="POST">
            <input name="action" type="hidden" value="show_travels"/> <input
                name="product_type" type="hidden" value="Relax"/> <input
                class="button" type="submit"
                value="Отдых"/>
        </form>
    </li>
</ul>
</body>
</html>