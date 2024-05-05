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

	public Usuario modificarDatosUsuario(Usuario us, String dni, String newDni,String nombre, String apellido, String passwd1, String email);
	
	public Usuario modificarDatosUsuarioPago(Usuario us, String dni, String newDni,String nombre, String apellido, String passwd1, String email, String tarjeta, YearMonth fechaCaducidad);
	
	public void registrarUsuario(String dni, String nombre, String apellido, String passwd1, String email);
	
	public String[][] getPelis();

	public Pelicula getPeliInfo(String id);  
	
	public ArrayList<String> getDni();
	
	public ArrayList<String> getSalasId();
	
	public Sala modificarSala(Sala sala,String newid, int aforo, String id);
	
	public ArrayList<String> getPelisId();
	
	public Pelicula modificarPeli(Pelicula peli,String newId,Genero genero,String titulo, int pegi,int duracion,String sinopsis, String id);
	
	public ArrayList<String> getSesionId();
	
	public Sesion modificarSesion(Sesion sesion,String newId, double precio, LocalDateTime fecha, String idSala,String idPelicula,String id,int ticketRestante);
	
	public ArrayList<Sesion> getHoraSesion(String id);
	
	public ArrayList<Sesion> geTSesiones();
	
	public HashMap<String, String> getTituloIdPelis();
	
	public ArrayList<Sala> getSalasM(); 
	
	

	
	
	
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
