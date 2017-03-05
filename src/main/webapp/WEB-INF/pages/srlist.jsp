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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Service Requests List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <script type="text/javascript" src='/static/js/moment.js'></script>
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
                <h3 class="panel-title pull-left" style="padding-top: 7.5px;">Service Request</h3>

                <div class="col-sm-6">
                    <form:form method="POST" modelAttribute="searchCriteria" class="form-vertical">
                        <div class="col-sm-4">
                            <form:input type="datetime-local" path="start" id="starttmp"
                                        class="form-control input-sm" required="true"/>
                        </div>
                        <form:input type="hidden" path="start" id="start"/>
                        <div class="col-sm-4">
                            <form:input type="datetime-local" path="end" id="endtmp"
                                        class="form-control input-sm" required="true"/>
                            <form:input type="hidden" path="end" id="end"/>
                        </div>
                        <div class="col-sm-3">
                            <c:choose>
                                <c:when test="${user != null}">
                                    <form:input type="hidden" path="user" id="user" value="${user.id}"/>
                                </c:when>
                                <c:otherwise>
                                    <form:select type="hidden" path="user" id="user" items="${tses}" multiple="false"
                                                 itemValue="id" itemLabel="fullName" class="form-control input-sm"
                                                 required="true"/>
                                </c:otherwise>
                            </c:choose>

                        </div>
                        <div class="col-sm-1">
                            <input type="submit" value="Find" class="btn btn-primary btn-sm"/>

                        </div>
                    </form:form>
                </div>
                <a class="btn btn-default btn-sm pull-right" href="<c:url value='/newsr' />"><span
                        class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    Create New Service Request</a>

                <div class="clearfix"></div>
            </div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Customer Name</th>
                    <th>Site ID</th>
                    <th>Subject</th>
                    <th>Severity</th>
                    <th>Owner</th>
                    <sec:authorize access="hasRole('MANAGER')">
                        <th width="50"></th>
                    </sec:authorize>
                    <sec:authorize access="hasRole('TSE') or hasRole('MANAGER') or hasRole('WFM')">
                        <th width="50"></th>
                    </sec:authorize>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${srs}" var="sr">
                    <tr>
                        <td>${sr.srId}</td>
                        <td>${sr.customerName}</td>
                        <td>${sr.siteId}</td>
                        <td>${sr.subject}</td>
                        <td>${sr.severity}</td>
                        <td>${sr.createdBy.fullName}</td>
                        <sec:authorize access="hasRole('TSE')">
                            <td><a href="<c:url value='/addtime-${sr.srId}' />"
                                   class="btn btn-success btn-xs custom-width">Add Time</a></td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('TSE') or hasRole('MANAGER') or hasRole('WFM')">
                            <td><a href="<c:url value='/view-sr-${sr.srId}' />"
                                   class="btn btn-success btn-xs custom-width">View Details</a></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <c:if test="${hours != null}">
            <div class="panel panel-info">Total hours <strong>${hours}</strong></div>
        </c:if>
    </div>
    <%--<sec:authorize access="hasRole('ADMIN')">
        <div class="well">
            <a href="<c:url value='/newuser' />">Add New User</a>
        </div>
    </sec:authorize>--%>
</div>
</body>
<script>
    document.getElementById('starttmp').addEventListener('change', addTimeZoneOffset, false);
    document.getElementById('endtmp').addEventListener('change', addTimeZoneOffset, false);

    function addTimeZoneOffset() {
        var tmp = this;
        tmp.removeAttribute('name');
        var date = moment(tmp.value).format("YYYY-MM-DDTHH:mm:ssZZ");
        console.log("Value " + tmp.value);
        console.log("New date " + date);
        if (tmp.id == 'starttmp') {
            document.getElementById('start').value = date;
        } else {
            document.getElementById('end').value = date;
        }
        ;
    }
    ;
</script>
</html>
