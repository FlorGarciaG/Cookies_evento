/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author florc
 */
public class ConnectionBD {
    Connection conection;

    public ConnectionBD ()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conection = DriverManager.getConnection("jdbc:mysql://localhost:3306/prueba", "root", "");
        } catch(Exception e){
            System.out.println("El error esta en la conexion"+e);
        } 
    }
    
    public Connection getConnectionBD(){
        return conection;
    }
    
}
