package model;

import java.time.LocalDate;

public class Usuario {
	private String dni;
	private String contraseña;
	private String nombre;
	private String apellido;
	private String email;
	private int metodoPago;
	private LocalDate fechaCaducidadTarjeta;
    private boolean adminCheck;
    
    public Usuario(String dni, String contraseña, String nombre, String apellido, String email, int metodoPago, LocalDate fechaCaducidadTarjeta, boolean adminCheck) {
    	//constructor de usuario desde admin
    	this.dni=dni;
    	this.contraseña=contraseña;
    	this.nombre=nombre;
    	this.apellido=apellido;
    	this.email=email;
    	this.metodoPago=metodoPago;
    	this.fechaCaducidadTarjeta=fechaCaducidadTarjeta;
    	this.adminCheck=adminCheck;
    }
    public Usuario(String dni, String contraseña, String nombre, String apellido, String email) {
    	//constructor de cliente desde login
    	this.dni=dni;
    	this.contraseña=contraseña;
    	this.nombre=nombre;
    	this.apellido=apellido;
    	this.email=email;
    	this.metodoPago=0;
    	this.fechaCaducidadTarjeta=null;
    	this.adminCheck=false;
    }
}
