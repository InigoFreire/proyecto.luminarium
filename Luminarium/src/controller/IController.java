package controller;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;

import model.Genero;
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

	public void registrarSala(String id, int aforo);

	public Sala modificarSala(Sala sala, String newid, int aforo, String id);

	public ArrayList<String> getSalasId();

	public Pelicula modificarPeli(Pelicula peli, String newId, Genero genero, String titulo, int pegi, int duracion, String sinopsis, String id);

	public ArrayList<String> getPelisId();

	public Sesion modificarSesion(Sesion sesion, String newId, double precio, LocalDateTime fecha, String id);

	public ArrayList<String> getSesionId();

	public String getUltimoIdSala();

	public String getUltimoIdSesion();

	public void registrarSesion(String id, int precio, LocalDateTime fecha, String idSala, int idPeli);

	public HashMap<String, Integer> getTituloIdPelis();

	public int getUltimoIdPeli();

	public void registrarPeli(String id, Genero genero, String titulo, int pegi, int duracion, String sinopsis);  

}
