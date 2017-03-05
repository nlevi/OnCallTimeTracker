<%--
  Created by IntelliJ IDEA.
  User: levin1
  Date: 1/13/2017
  Time: 10:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <script type="text/javascript" src='/static/js/jstz.min.js'></script>
    <%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet">--%>
</head>

<body>

<div class="generic-container">
    <%@include file="navbar.jsp" %>
    <div class=" col-md-5 col-md-offset-3">

        <div class="panel panel-default">
            <div class="panel-heading">User Registration Form</div>
            <div class="panel-body col-sm-offset-1">
                <form:form method="POST" modelAttribute="user" class="form-horizontal">
                    <form:input type="hidden" path="id" id="id"/>

                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 control-lable" for="firstName">First Name</label>

                            <div class="col-md-6">
                                <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="firstName" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 control-lable" for="lastName">Last Name</label>

                            <div class="col-md-6">
                                <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="lastName" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 control-lable" for="username">Username</label>

                            <div class="col-md-6">
                                <c:choose>
                                    <c:when test="${edit}">
                                        <form:input type="text" path="username" id="username"
                                                    class="form-control input-sm"
                                                    disabled="true"/>
                                    </c:when>
                                    <c:otherwise>
                                        <form:input type="text" path="username" id="username"
                                                    class="form-control input-sm"/>
                                        <div class="has-error">
                                            <form:errors path="username" class="help-inline"/>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 control-lable" for="password">Password</label>

                            <div class="col-md-6">
                                <form:input type="password" path="password" id="password"
                                            class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="password" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 control-lable" for="email">Email</label>

                            <div class="col-md-6">
                                <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="email" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 control-lable" for="userProfiles">Roles</label>

                            <div class="col-md-6">
                                <c:if test="${editMgr != null}">
                                    <c:choose>
                                        <c:when test="${editMgr}">
                                            <form:select path="userProfiles" id="userProfiles" items="${roles}"
                                                         multiple="false"
                                                         itemValue="id"
                                                         itemLabel="type" class="form-control input-sm"/>
                                        </c:when>
                                        <c:otherwise>
                                            <form:select path="userProfiles" id="userProfiles" items="${roles}"
                                                         multiple="false"
                                                         itemValue="id"
                                                         itemLabel="type" class="form-control input-sm"
                                                         disabled="true"/>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>

                                <div class="has-error">
                                    <form:errors path="userProfiles" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 control-lable" for="manager">Manager</label>

                            <div class="col-md-6">
                                <c:if test="${editMgr != null}">
                                    <c:choose>
                                        <c:when test="${editMgr}">
                                            <form:select path="manager" id="manager" multiple="false"
                                                         class="form-control input-sm">
                                                <form:option value=""/>
                                                <form:options items="${managers}" itemValue="id" itemLabel="fullName"/>
                                            </form:select>
                                        </c:when>
                                        <c:otherwise>
                                            <form:select path="manager" id="manager" multiple="false"
                                                         class="form-control input-sm" disabled="true" hidden="true">
                                                <form:option value=""/>
                                                <form:options items="${managers}" itemValue="id" itemLabel="fullName"/>
                                            </form:select>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>

                                <div class="has-error">
                                    <form:errors path="manager" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 control-lable" for="timezone">TimeZone</label>

                            <div class="col-md-6">
                                <form:select path="timezone" id="timezone" multiple="false"
                                             class="form-control input-sm">
                                    <form:options items="${zones}"/>
                                </form:select>
                                <div class="has-error">
                                    <form:errors path="timezone" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <c:choose>
                                <c:when test="${edit}">
                                    <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a
                                        href="<c:url value='/home' />">Cancel</a>
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a
                                        href="<c:url value='/home' />">Cancel</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
<script>

    var options = document.getElementsByTagName('option');
    options.forEach(setSelected);

    function setSelected(item) {
        var timezone = getTimeZone();
        if (item.value == timezone) {
            item.selected = true;
        }
    }

    function getTimeZone() {
        var zone = jstz.determine();
        return zone.name();
    }
</script>
</html>
