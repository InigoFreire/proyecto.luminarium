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

/**
 * Interfaz que define los métodos que deben implementar los controladores en la aplicación.
 * Estos métodos proporcionan la funcionalidad necesaria para la gestión de usuarios, películas, salas, sesiones, etc.
 */
public interface IController {

	public Usuario logIn(String usuario, String password);
	
	public Usuario modificarDatosUsuario(Usuario us, String dni, String newDni, String nombre, String apellido, String passwd1, String email, String tarjeta, YearMonth fechaCaducidad);
	
	public void registrarUsuario(String dni, String nombre, String apellido, String passwd1, String email);
	
	public String[][] getPelis();

	public Pelicula getPeliInfo(String id);  
	
	public ArrayList<String> getDni();
	
	public ArrayList<String> getSalasId();
	
	public Sala modificarSala(Sala sala,String newid, int aforo, String id);
	
	public ArrayList<String> getPelisId();
	
	public Pelicula modificarPeli(Pelicula peli,String newId,Genero genero,String titulo, int pegi,int duracion,String sinopsis, String id);
	
	public Sesion modificarSesion(Sesion sesion,String newId, double precio, LocalDateTime fecha, String idSala,String idPelicula,String id,int ticketRestante);
	
	public ArrayList<Sesion> getHoraSesion(String id);
	
	public ArrayList<Sesion> geTSesiones();
	
	public ArrayList<Sala> getSalasM(); 
	
	public void registrarSala(String id, int aforo);

	public String getUltimoIdSala();

	public String getUltimoIdSesion();

	public void registrarSesion(String id, double precio, LocalDateTime fecha, String idSala, String idPeli, int tickets);

	public int getUltimoIdPeli();

	public void registrarPeli(String id, Genero genero, String titulo, int pegi, int duracion, String sinopsis);
	
	public Pelicula getPeliPorTitulo(String titulo);

	public String[][] getSalas();
	
	public Sala getSalaPorId(String id);
	
	public String[][] getSesiones();
	
	public Sesion getSesionPorId(String id);
	
	public String[][] getUsuarios();
	
	public Usuario getUsuarioPorDni(String dni);
	
	public HashMap<String, String> getTituloIdPelis();

	public ArrayList<String> getSesionId();
	
	public Sesion actualizarEntradas(Sesion sesion,int entradas);

	boolean borrarElementos(ArrayList<?> elementos, String tipo);
	
}
