<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: levin1
  Date: 1/24/2017
  Time: 6:45 PM
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-inverse ">
    <div class="container-fluid">
        <div class="navbar-header">
            <%--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>--%>
            <a class="navbar-brand" href="#">On Call Time Tracker</a>
        </div>
        <div class="collapse navbar-collapse">
            <div class="btn-group">
                <sec:authorize access="hasRole('TSE') or hasRole('MANAGER')">
                    <a href="/srlist" class="btn btn-info navbar-btn">SR List</a>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN') or hasRole('MANAGER')">
                    <a href="/home" class="btn btn-info navbar-btn">User List</a>
                </sec:authorize>
            </div>
            <%--<ul class="nav navbar-nav navbar-left">
                <sec:authorize access="hasRole('TSE') or hasRole('MANAGER')">
                    <li><a href="/srlist" class="btn btn-info">SR List</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN') or hasRole('MANAGER')">
                    <li><a href="/home" class="btn btn-info">User List</a></li>
                </sec:authorize>
            </ul>--%>
            <ul class="nav navbar-nav navbar-right">
                <%@include file="authheader.jsp" %>
            </ul>
        </div>
    </div>
</nav>
