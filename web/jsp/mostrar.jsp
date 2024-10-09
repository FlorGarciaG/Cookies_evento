<%-- 
    Document   : mostrar
    Created on : 2/10/2024, 08:24:31 PM
    Author     : florc
--%>

<%@page import="Configuration.ConnectionBD"%>
<%@page import="Model.Evento"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Mostrar Eventos</title>
        <%
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            ArrayList<Evento> eventos = new ArrayList<>();

            try {
                // Conectar a la base de datos
                ConnectionBD conexion = new ConnectionBD();
                conn = conexion.getConnectionBD();

                // Verificar que la conexión no sea nula
                if (conn != null) {
                    // Crear la consulta
                    String sql = "SELECT * FROM evento";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();

                    // Recorrer los resultados
                    while (rs.next()) {
                        // Crear un objeto Evento
                        Evento evento = new Evento();
                        evento.setClave(rs.getString("clave"));
                        evento.setNombre(rs.getString("nombre"));
                        evento.setFecha(rs.getDate("fecha")); // java.sql.Date
                        evento.setHora(rs.getTime("hora"));   // java.sql.Time
                        evento.setCosto(rs.getDouble("costo"));
                        evento.setNum_invitados(rs.getInt("num_invitados"));

                        // Agregar el evento a la lista
                        eventos.add(evento);
                    }
                } else {
                    out.println("<p>Error al conectar con la base de datos.</p>");
                }
            } catch (SQLException e) {
                out.println("<p>Error SQL: " + e.getMessage() + "</p>");
                e.printStackTrace();
            } finally {
                // Cerrar recursos
                if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
                if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        %>
    </head>
    <body>
        <h1>Eventos Registrados</h1>
        <table border="1">
            <tr>
                <th>Clave</th>
                <th>Nombre</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Costo</th>
                <th>Número de Invitados</th>
            </tr>
            <%
                for (Evento evento : eventos) {
            %>
            <tr>
                <td><%= evento.getClave()%></td>
                <td><%= evento.getNombre()%></td>
                <td><%= evento.getFecha()%></td>
                <td><%= evento.getHora()%></td>
                <td><%= evento.getCosto()%></td>
                <td><%= evento.getNum_invitados()%></td>
            </tr>
            <%
                }
            %>
        </table>
        <br>
        <button><a href="${pageContext.request.contextPath}/guardarServelet">Limpiar</a></button>
    </body>
</html>
