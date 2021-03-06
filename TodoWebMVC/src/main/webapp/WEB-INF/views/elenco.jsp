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
        <link rel='stylesheet' href='${mvc.contextPath}/webjars/bootstrap/4.0.0-2/css/bootstrap.min.css'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ToDo Web App</title>
    </head>
    <body>
        <c:import url="/WEB-INF/nav.jsp"/>
        <div class="list-group">
            <c:forEach items="${all}" var="v">
                <a href="#" class="list-group-item list-group-item-action">
                    <c:out value="${v.titolo} - ${v.testo}" /> 
                    - <fmt:formatDate pattern="dd/MM/yyy" value="${v.il}"/> 
                </a> 
            </c:forEach>
        </div>
    </body>
</html>
