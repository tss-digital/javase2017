<%-- 
    Document   : index
    Created on : 13-mar-2018, 9.51.28
    Author     : tss
--%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel='stylesheet' href='webjars/bootstrap/4.0.0-2/css/bootstrap.min.css'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ToDo Web App</title>
    </head>
    <body>

        <!-- A grey horizontal navbar that becomes vertical on small screens -->
        <nav class="navbar navbar-expand-sm bg-light">

            <!-- Links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="todoForm.jsp">Nuovo</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Tutti</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp?oggi=true">Oggi</a>
                </li>
            </ul>

        </nav>

        <div class="list-group">
            <c:forEach items="${empty pageContext.request.getParameter('oggi') ? store.findAll() : store.findToday()}" var="v">
                <a href="todoForm.jsp?id=${v.id}&titolo=${v.titolo}&testo=${v.testo}&il=${v.ilToString}" class="list-group-item list-group-item-action">
                    <c:out value="${v.titolo} - ${v.testo}" /> 
                    - <fmt:formatDate pattern="dd/MM/yyy" value="${v.il}"/> 
                </a> 
            </c:forEach>
        </div>


    </body>
</html>
