<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: levin1
  Date: 1/13/2017
  Time: 10:45 PM
  To change this template use File | Settings | File Templates.
--%>
<div class="navbar-text">
    Signed in as <a href="<c:url value="/profile" />"><strong><security:authentication property="principal.username" /></strong></a> <a
        href="<c:url value="/logout" />">Logout</a>
</div>