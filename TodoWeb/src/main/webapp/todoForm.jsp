<%-- 
    Document   : todoForm
    Created on : 16-mar-2018, 8.53.39
    Author     : tss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel='stylesheet' href='webjars/bootstrap/4.0.0-2/css/bootstrap.min.css'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ToDo</title>
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
        <form action="${pageContext.request.contextPath}/save" method="POST">
            <input type="hidden" name="id" 
                   value="${pageContext.request.getParameter('id')}"   />
            <div class="container-fluid">
                <h1 class="text-center text-info">Dati</h1>
                <div class="form-group">
                    <label for="titolo">Titolo:</label>
                    <input type="text" name="titolo" class="form-control" id="titolo"
                           value="${pageContext.request.getParameter('titolo')}">
                </div>
                <div class="form-group">
                    <label for="testo">Testo</label>
                    <textarea id="testo" name="testo" class="form-control">
                        ${pageContext.request.getParameter('testo')}
                    </textarea>
                </div>
                <div class="form-group">
                    <label for="il">Il:</label>
                    <input type="datetime" name="il" class="form-control" id="il"
                           value="${pageContext.request.getParameter('il')}">
                </div>
                <button type="submit" name="salva" class="btn btn-default">Salva</button>
                <button type="submit" name="elimina" 
                        ${empty pageContext.request.getParameter('id') ? 'disabled' : ''} class="btn btn-default">Elimina</button>
            </div>
        </form> 
    </body>
</html>
