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
    <title>New Service Request Form</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet">--%>
</head>

<body>

<div class="generic-container">
    <%@include file="navbar.jsp" %>
    <div class=" col-md-5 col-md-offset-3">

        <div class="panel panel-default">
            <div class="panel-heading">New Service Request Form</div>
            <div class="panel-body col-sm-offset-1">
                <form:form method="POST" modelAttribute="sr" class="form-horizontal">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 control-lable" for="srId">Service Request ID</label>

                            <div class="col-md-6">
                                <form:input type="number" path="srId" id="srId" class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="srId" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 control-lable" for="customerName">Customer Name</label>

                            <div class="col-md-6">
                                <form:input type="text" path="customerName" id="customerName"
                                            class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="customerName" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 control-lable" for="siteId">Site ID</label>

                            <div class="col-md-6">
                                <form:input type="number" path="siteId" id="siteId"
                                            class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="siteId" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 control-lable" for="subject">Subject</label>

                            <div class="col-md-6">
                                <form:input type="text" path="subject" id="subject"
                                            class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="subject" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 control-lable" for="severity">Severity</label>

                            <div class="col-md-6">
                                <form:select type="text" path="severity" id="severity"
                                             class="form-control input-sm">
                                    <form:option value="S1" label="S1"/>
                                    <form:option value="S2" label="S2"/>
                                </form:select>
                                <div class="has-error">
                                    <form:errors path="severity" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit" value="Create" class="btn btn-primary btn-sm"/> or <a
                                href="<c:url value='/srlist' />">Cancel</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
