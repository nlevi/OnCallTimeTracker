<%--
  Created by IntelliJ IDEA.
  User: levin1
  Date: 1/11/2017
  Time: 9:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome page</title>
</head>
<body>
Greeting : ${greeting}
This is a welcome page.
<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>
