<%--
  Created by IntelliJ IDEA.
  User: levin1
  Date: 2/3/2017
  Time: 3:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Service Request Time</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
    <script type="text/javascript" src='/static/js/moment.js'></script>

</head>

<body>

<div class="generic-container">
    <%@include file="navbar.jsp" %>
    <div class=" col-md-7 col-md-offset-2">

        <div class="panel panel-default">
            <div class="panel-heading">Service Request ${sr.srId}</div>
            <div class="panel-body col-sm-offset-0">
                <table id="srdetails" class="table table-condensed table-borderless">
                    <tbody>
                    <tr>
                        <td style="text-align: right"><strong>Service Request ID:</strong></td>
                        <td>${sr.srId}</td>
                        <td style="text-align: right"><strong>Created On:</strong></td>
                        <td><fmt:setTimeZone value="Europe/Moscow"/><fmt:formatDate value="${sr.creationDate}"
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
                    </tr>
                    <tr>
                        <td style="text-align: right"><strong>Subject:</strong></td>
                        <td colspan="3">${sr.subject}</td>
                    </tr>
                    </tbody>
                </table>
                <div class="clearfix"></div>
                <div class="panel-default col-md-7 col-sm-offset-3">
                    <form:form method="POST" modelAttribute="srTime" class="form-horizontal">
                        <form:input type="hidden" path="serviceRequest" id="serviceRequest" value="${sr.srId}"/>

                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3 control-lable" for="starttmp">Start Time</label>

                                <div class="col-md-6">
                                    <form:input type="datetime-local" path="startTime" id="starttmp"
                                                class="form-control input-sm"/>
                                    <form:input type="hidden" path="startTime" id="startTime"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3 control-lable" for="endtmp">End Time</label>

                                <div class="col-md-6">
                                    <form:input type="datetime-local" path="endTime" id="endtmp"
                                                class="form-control input-sm"/>
                                    <form:input type="hidden" path="endTime" id="endTime"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-actions floatRight">
                                <input type="submit" value="Add" class="btn btn-primary btn-sm"/> or <a
                                    href="<c:url value='/srlist' />">Cancel</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
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
            document.getElementById('startTime').value = date;
        } else {
            document.getElementById('endTime').value = date;
        }
        ;
    }
    ;
</script>
</html>
