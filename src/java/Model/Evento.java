/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author florc
 */
public class Evento {
    private String clave;
    private String nombre;
    private Date fecha;
    private Time hora;
    private Double costo;
    private int num_invitados;

    public Evento() {
    }

    public Evento(String clave, String nombre, Date fecha, Time hora, Double costo, int num_invitados) {
        this.clave = clave;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.costo = costo;
        this.num_invitados = num_invitados;
    }

    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
    
    

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public int getNum_invitados() {
        return num_invitados;
    }

    public void setNum_invitados(int num_invitados) {
        this.num_invitados = num_invitados;
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
