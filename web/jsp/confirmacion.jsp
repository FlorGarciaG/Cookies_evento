<%-- 
    Document   : confirmacion
    Created on : 2/10/2024, 08:21:32 PM
    Author     : florc
--%>

<%@page import="Model.Evento"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Base64"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmacion</title>
        <%
            Cookie[] cookies = request.getCookies();
            String eventoJson = null;

            // Buscar la cookie con el evento
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("evento")) {
                        // Decodificar el valor de la cookie
                        eventoJson = new String(Base64.getDecoder().decode(cookie.getValue()));
                        break;
                    }
                }
            }

            Evento evento = null;
            if (eventoJson != null) {
                Gson gson = new Gson();
                evento = gson.fromJson(eventoJson, Evento.class);
            }


        %>
    </head>
    <body>
        <h1>Confirmacion</h1>
        <%            if (evento != null) {
        %>
        <form method="POST" action="${pageContext.request.contextPath}/guardarServelet">
            
            <p><label>Clave: </label><input type="text" name="clave" value="<%= evento != null ? evento.getClave() : ""%>" disabled /></p>
            <p><label>Nombre de evento: </label><input type="text" name="nombre" value="<%= evento != null ? evento.getNombre() : ""%>" disabled /></p>
            <p><label>Fecha: </label><input type="date" name="fecha" value="<%= evento != null ? evento.getFecha().toString() : ""%>" disabled /></p>
            <p><label>Hora: </label><input type="time" name="hora" value="<%= evento != null ? evento.getHora().toString() : ""%>" disabled /></p>
            <p><label>Costo: </label><input type="number" step="0.01" name="costo" value="<%= evento != null ? evento.getCosto() : ""%>" disabled /></p>
            <p><label>Número de invitados: </label><input type="number" name="num_invitados" value="<%= evento != null ? evento.getNum_invitados() : ""%>" disabled /></p>

            <p>
                <input type="submit" value="Confirmar" />
                <button><a href="${pageContext.request.contextPath}/registroServlet">Cancelar</a></button>
            </p>
        </form>
        <%
        } else {
        %>
        <p>No se encontraron datos del evento.</p>
        <%
            }
        %>
    </body>
</html>
