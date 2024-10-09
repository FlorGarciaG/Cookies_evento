<%-- 
    Document   : registro
    Created on : 2/10/2024, 08:21:24 PM
    Author     : florc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <body>
        <h1>Registro de evento</h1>
        <form method="POST" action="${pageContext.request.contextPath}/registroServlet">
            <p><label>Clave: </label><input type="text" maxlength="20" name="clave" id="clave" required /></p>
            <p><label>Nombre de evento: </label><input type="text" name="nombre" id="nombre" required /></p>
            <p><label>Fecha: </label><input type="date" name="fecha" id="fecha" required />
            <p><label>Hora: </label><input type="time" name="hora" id="hora" required /></p>            
            <p><label>Costo: </label><input type="number" step="0.01" name="costo" id="costo" required /></p>
            <p><label>Número de invitados: </label><input type="number" name="num_invitados" id="num_invitados" required min="1" step="1" /></p>
            <p><input type="submit" value="Registro"></p>
        </form>
    </body>
</html>
