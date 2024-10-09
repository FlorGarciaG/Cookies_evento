/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Configuration.ConnectionBD;
import Model.Evento;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author florc
 */
@WebServlet(name = "GuardarServelet", urlPatterns = {"/guardarServelet"})
public class GuardarServelet extends HttpServlet {

    Connection conn;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GuardarServelet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GuardarServelet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         //Eliminar la cookie
        Cookie [] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie: cookies) {
                if("evento".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        
        //Redirigir registro
        response.sendRedirect(request.getContextPath() + "/jsp/registro.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // Obtener cookies
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
        if (eventoJson != null) {
            Gson gson = new Gson();
            Evento evento = gson.fromJson(eventoJson, Evento.class);
            //System.out.println(evento);

            // Insertar el evento en la base de datos
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                // Conectar a la base de datos
                ConnectionBD conexion = new ConnectionBD();
                String sql = "INSERT INTO evento(clave, nombre, fecha, hora, costo, num_invitados) VALUES (?, ?, ?, ?, ?, ?)";
                conn = conexion.getConnectionBD();
                ps = conn.prepareStatement(sql);

                // Asignar valores al PreparedStatement
                ps.setString(1, evento.getClave());
                ps.setString(2, evento.getNombre());
                ps.setDate(3, evento.getFecha());
                ps.setTime(4, evento.getHora());
                ps.setDouble(5, evento.getCosto());
                ps.setInt(6, evento.getNum_invitados());

                // Ejecutar la inserción
                int filasInsertadas = ps.executeUpdate();

                // Verificar si se insertaron filas
                if (filasInsertadas > 0) {
                    System.out.println("Evento insertado correctamente.");
                } else {
                    System.out.println("No se insertó el evento.");
                }
            } catch (Exception e) {
                // Manejar excepciones
                e.printStackTrace(); // Imprime la traza de la excepción
                System.out.println("Error al insertar el evento: " + e.getMessage());
                request.setAttribute("error", e);
            }
        }

        // Redireccionar a la página de mostrar.jsp
        response.sendRedirect(request.getContextPath() + "/jsp/mostrar.jsp");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
