package model;

import java.time.YearMonth;

public class Usuario {
	private String dni;
	private String contraseña;
	private String nombre;
	private String apellido;
	private String email;
	private String metodoPago;
	private YearMonth fechaCaducidadTarjeta;
    private boolean adminCheck;
    
    public Usuario() {
    	this.dni="";
    	this.contraseña="";
    	this.nombre="";
    	this.apellido="";
    	this.email="";
    	this.adminCheck=false;
    }
    
    public Usuario(String dni, String contraseña, String nombre, String apellido, String email, String metodoPago, YearMonth fechaCaducidadTarjeta, boolean adminCheck) {
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
    	this.metodoPago="";
    	this.fechaCaducidadTarjeta=null;
    	this.adminCheck=false;
    }

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public YearMonth getFechaCaducidadTarjeta() {
		return fechaCaducidadTarjeta;
	}

	public void setFechaCaducidadTarjeta(YearMonth fechaCaducidadTarjeta) {
		this.fechaCaducidadTarjeta = fechaCaducidadTarjeta;
	}

	public boolean isAdminCheck() {
		return adminCheck;
	}

	public void setAdminCheck(boolean adminCheck) {
		this.adminCheck = adminCheck;
	}

	@Override
	public String toString() {
		return "Usuario [dni=" + dni + ", contraseña=" + contraseña + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", email=" + email + ", metodoPago=" + metodoPago + ", fechaCaducidadTarjeta=" + fechaCaducidadTarjeta
				+ ", adminCheck=" + adminCheck + "]";
	}    
}
