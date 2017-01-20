<%--
  Created by IntelliJ IDEA.
  User: levin1
  Date: 1/13/2017
  Time: 10:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration Confirmation Page</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
</head>
<body>
<div class="generic-container">
    <%@include file="authheader.jsp" %>

    <div class="alert alert-success lead">
        ${success}
    </div>

        <span class="well floatRight">
            Go to <a href="<c:url value='/list' />">Users List</a>
        </span>
</div>
</body>

</html>
