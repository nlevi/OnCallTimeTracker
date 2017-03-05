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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View Service Request ${sr.srId}</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
    <%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet">--%>
</head>

<body>

<div class="generic-container">
    <%@include file="navbar.jsp" %>
    <div class=" col-md-7 col-md-offset-2">
        <fmt:setTimeZone value="${timezone}"/>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title pull-left" style="padding-top: 7.5px;">View Service Request #${sr.srId}</h3>
                <a href="<c:url value='/addtime-${sr.srId}' />"
                   class="btn btn-default btn-sm pull-right"><span
                        class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Add Time</a>

                <div class="clearfix"></div>
            </div>
            <div class="panel-body col-sm-offset-0">
                <table id="srdetails" class="table table-condensed table-borderless">
                    <tbody>
                    <tr>
                        <td style="text-align: right"><strong>Service Request ID:</strong></td>
                        <td>${sr.srId}</td>
                        <td style="text-align: right"><strong>Created On:</strong></td>
                        <td><fmt:formatDate value="${sr.creationDate}"
                                            type="both" dateStyle="short"
                                            timeStyle="short"/></td>
                        <td style="text-align: right"><strong>Severity:</strong></td>
                        <td>${sr.severity}</td>
                    </tr>
                    <tr>
                        <td style="text-align: right"><strong>Customer:</strong></td>
                        <td>${sr.customerName}</td>
                        <td style="text-align: right"><strong>Site ID:</strong></td>
                        <td>${sr.siteId}</td>
                        <%--<td><strong>Owner:</strong></td>
                        <td>${sr.createdBy.fullName}</td>--%>
                    </tr>
                    <tr>
                        <td style="text-align: right"><strong>Subject:</strong></td>
                        <td colspan="4">${sr.subject}</td>
                    </tr>
                    </tbody>
                </table>
                <div class="panel-default">
                    <ul class="list-group">
                        <c:choose>
                            <c:when test="${empty srTimes}">
                                <li class="list-group-item">No time logged.</li>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${srTimes}" var="srTime">
                                    <li class="list-group-item"><strong>${srTime.user.fullName}
                                        <spacer width="50" type="block"/>
                                        : </strong><fmt:formatDate value="${srTime.startTime}" type="both"
                                                                   dateStyle="short" timeStyle="short"/> -
                                        <fmt:formatDate value="${srTime.endTime}" type="both" dateStyle="short"
                                                        timeStyle="short"/></li>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
                <c:if test="${hours != null}">
                    <div class="panel panel-info">Total hours <strong>${hours}</strong></div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
