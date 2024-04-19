package controller;

import java.time.YearMonth;

import model.Pelicula;
import model.Usuario;

public interface IController {

	public Usuario logIn(String usuario, String password);

	public Usuario modificarDatosUsuario(Usuario us, String dni, String dniInput,String nombre, String apellido, String passwd1, String email);
	
	public Usuario modificarDatosUsuarioPago(Usuario us, String dni, String dniInput, String nombre, String apellido, String passwd1, String email, String tarjeta, YearMonth fechaCaducidad);
	
	public void registrarUsuario(String dni, String nombre, String apellido, String passwd1, String email);
	
	public String[][] getPelis();

	public Pelicula getPeliInfo(String id);  

}
