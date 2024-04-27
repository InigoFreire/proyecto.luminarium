package controller;

import java.time.YearMonth;

import model.Pelicula;
import model.Sala;
import model.Sesion;
import model.Usuario;

public interface IController {

	public Usuario logIn(String usuario, String password);

	public Usuario modificarDatosUsuario(Usuario us, String dni, String dniInput,String nombre, String apellido, String passwd1, String email);
	
	public Usuario modificarDatosUsuarioPago(Usuario us, String dni, String dniInput, String nombre, String apellido, String passwd1, String email, String tarjeta, YearMonth fechaCaducidad);
	
	public void registrarUsuario(String dni, String nombre, String apellido, String passwd1, String email);
	
	public String[][] getPelis();

	public Pelicula getPeliInfo(String id);
	
	
	// añadido por aitziber
		public Pelicula getPeliPorTitulo(String titulo);
	// añadido por aitziber
	public String[][] getSalas();
	// añadido por aitziber
	public Sala getSalaPorId(String id);
	// añadido por aitziber
	public String[][] getSesiones();
	// añadido por aitziber
	public Sesion getSesionPorId(String id);
	// añadido por aitziber
	public String[][] getUsuarios();
	// añadido por aitziber
	public Usuario getUsuarioPorDni(String dni);
}
