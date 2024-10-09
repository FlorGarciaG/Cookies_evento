/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Evento;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "RegistroServlet", urlPatterns = {"/registroServlet"})
public class RegistroServlet extends HttpServlet {

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
            out.println("<title>Servlet RegistroServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistroServlet at " + request.getContextPath() + "</h1>");
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

        String clave = request.getParameter("clave");
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String costo = request.getParameter("costo");
        String invitados = request.getParameter("num_invitados");

        Date fechafinal = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(fecha);
            fechafinal = new java.sql.Date(utilDate.getTime()); 
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Conversión de hora a java.sql.Time
        Time horafinal = null;
        try {
            SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
            java.util.Date utilTime = sdfTime.parse(hora);
            horafinal = new java.sql.Time(utilTime.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Conversión de costo a double
        double costofinal = 0.0;
        try {
            costofinal = Double.parseDouble(costo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Conversión de invitados a int
        int invitadosfinal = 0;
        try {
            invitadosfinal = Integer.parseInt(invitados);
        } catch (NumberFormatException e) {
            e.printStackTrace(); // En caso de que no se pueda convertir a int
        }

        // Crear el objeto Evento con los valores convertidos
        Evento evento = new Evento(clave, nombre, fechafinal, horafinal, costofinal, invitadosfinal);
        
        Gson gson = new Gson();

        String eventoJson = gson.toJson(evento);
        String encodedJson = Base64.getEncoder().encodeToString(eventoJson.getBytes());

        System.out.println(eventoJson);

        Cookie cookie = new Cookie("evento", encodedJson);
        cookie.setMaxAge(60 * 60 * 24); // Vida de 24 hrs
        //Establecer la ruta
        cookie.setPath("/");
        // Agregar la cookie a la respuesta
        response.addCookie(cookie);
        response.sendRedirect(request.getContextPath() + "/jsp/confirmacion.jsp");
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
