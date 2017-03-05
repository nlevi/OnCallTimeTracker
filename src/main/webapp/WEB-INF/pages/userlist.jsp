<%--
  Created by IntelliJ IDEA.
  User: levin1
  Date: 1/13/2017
  Time: 10:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet">--%>
</head>

<body>
<div class="generic-container">
    <%@include file="navbar.jsp" %>
    <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
            <c:if test="${success != null}">
                <div class="alert alert-success">
                        ${success}
                </div>
            </c:if>
            <div class="panel-heading">
                <h3 class="panel-title pull-left" style="padding-top: 7.5px;">List of Users </h3>
                <sec:authorize access="hasRole('ADMIN')or hasRole('MANAGER')">
                    <a class="btn btn-default btn-sm pull-right" href="<c:url value='/newuser' />"><span
                            class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                        Add New User</a>
                </sec:authorize>
                <div class="clearfix"></div>
            </div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Firstname</th>
                    <th>Lastname</th>
                    <th>Email</th>
                    <th>Manager</th>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('MANAGER')">
                        <th width="50"></th>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <th width="50"></th>
                    </sec:authorize>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.manager}</td>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('MANAGER')">
                            <td><a href="<c:url value='/edit-user-${user.username}' />"
                                   class="btn btn-success btn-xs custom-width">edit</a></td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <td><a href="<c:url value='/delete-user-${user.username}' />"
                                   class="btn btn-danger btn-xs custom-width">delete</a></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <%--<sec:authorize access="hasRole('ADMIN')">
        <div class="well">
            <a href="<c:url value='/newuser' />">Add New User</a>
        </div>
    </sec:authorize>--%>
</div>
</body>
</html>
