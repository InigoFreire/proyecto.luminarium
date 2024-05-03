package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.HashMap;


import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Time;

import model.Genero;
import model.Pelicula;
import model.Sala;
import model.Sesion;
import model.Usuario;

public class Controller implements IController {

	private Connection con;
	private PreparedStatement stmt;

	// Sentencias SQL
	final String OBTENERusuario = "SELECT * FROM usuarios WHERE dni=? AND contraseña=?";
	final String MODIFICARusuario = "UPDATE USUARIOS SET nombre=?, apellido=?, email=?, contraseña=?, dni=?, adminCheck=false WHERE dni=?";
	final String MODIFICARusuarioPago = "UPDATE USUARIOS SET nombre=?, apellido=?, email=?, contraseña=?, metodoPago=?, fechaCaducidadTarjeta=?, dni=?, adminCheck=false WHERE dni=?";
	final String INSERTARusuario = "INSERT INTO USUARIOS VALUES (?,?,?,?,?,NULL,NULL,false)";
	final String GETPeliCorto = "SELECT titulo,PEGI from peliculas";
	// aitor quieres buscar por id o por titulo? 
	final String GetPeliInfo = "select * from peliculas where titulo = ?";
	final String GetDni = "select dni from usuarios";
	final String GetSalaId = "select id from salas";
	final String ModificarSala = "update salas set id = ?, aforo=? where id =?";
	final String GetPeliIds = "select id from peliculas";
	final String ModificarPeli = "update peliculas set id=?, genero=?, titulo=?, PEGI=?, duracion=?, sinopsis=? where id=?";
	final String GetSesionIds = "select id from sesiones";
	final String ModificarSesion = "update sesiones set id=?, precio=?, fecha=? where id=?";
	final String getHoraSesion = "select * from sesiones where idPelicula=? and ticketRestante>0";
	final String getSesion = "select * from sesiones";
	final String GetPelisIdTitulo = "SELECT titulo, id FROM peliculas";
	// añadido por aitziber
	final String GETPeliPorTitulo = "SELECT * FROM peliculas WHERE titulo = ?";
	// añadido por aitziber
	final String GETSalas = "SELECT id, aforo FROM salas";
	// añadido por aitziber
	final String GETSalaPorId = "SELECT * FROM salas WHERE id = ?";
	// añadido por aitziber
	final String GETSesiones = "SELECT sesiones.id, sesiones.precio, sesiones.fecha, \r\n"
			+ "(SELECT titulo FROM peliculas WHERE id = sesiones.idPelicula) AS tituloPelicula, sesiones.idSala FROM sesiones";	
	// añadido por aitziber
	final String GETSesionPorId = "SELECT * FROM sesiones WHERE id = ?";
	// añadido por aitziber
	final String GETUsuarios = "SELECT dni, nombre, apellido, adminCheck FROM usuarios";
	// añadido por aitziber
	final String GETUsuarioPorDni = "SELECT * FROM usuarios WHERE dni = ?";
	
	
	@Override
	public HashMap<String, Integer> getTituloIdPelis() {
		HashMap<String, Integer> pelis = new HashMap<String, Integer>();
		this.openConnection();
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(GetPelisIdTitulo);
								
			rs = stmt.executeQuery();
			while(rs.next()) {
				pelis.put(rs.getString("titulo"), rs.getInt("id"));
			}
			
		}catch (SQLException e) {
			System.out.println("Error en el cierre de la BD");
			e.printStackTrace();
		}
		
		return pelis;
	}
	
	@Override
	public ArrayList<Sesion> geTSesiones() {
		ArrayList<Sesion> horas = new ArrayList<Sesion>();
		openConnection();
		ResultSet rs = null;
		
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = null;
		try {
			stmt = con.prepareStatement(getSesion);
			
								
			rs = stmt.executeQuery();
						
			while(rs.next()) {
				Sesion sesion = new Sesion();
				sesion.setId(rs.getString("id"));
				sesion.setIdPelicula(rs.getString("idPelicula"));
				sesion.setIdSala(rs.getString("idSala"));
				sesion.setTicketRestante(rs.getInt("ticketRestante"));
				sesion.setPrecio(rs.getDouble("precio"));
				dateTime = LocalDateTime.parse(rs.getString("fecha"), formatter);
				sesion.setFecha(dateTime);
				
				horas.add(sesion);
				
			}
			
		}catch (SQLException e) {
			System.out.println("Error en el cierre de la BD");
			e.printStackTrace();
		}
		
		
		
		
		return horas;
	}
	
	@Override
	public ArrayList<Sesion> getHoraSesion(String id) {
		ArrayList<Sesion> horas = new ArrayList<Sesion>();
		openConnection();
		ResultSet rs = null;
		
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = null;
		try {
			stmt = con.prepareStatement(getHoraSesion);
			stmt.setString(1,id);
								
			rs = stmt.executeQuery();
						
			while(rs.next()) {
				Sesion sesion = new Sesion();
				sesion.setId(rs.getString("id"));
				sesion.setIdPelicula(rs.getString("idPelicula"));
				sesion.setIdSala(rs.getString("idSala"));
				sesion.setTicketRestante(rs.getInt("ticketRestante"));
				sesion.setPrecio(rs.getDouble("precio"));
				dateTime = LocalDateTime.parse(rs.getString("fecha"), formatter);
				sesion.setFecha(dateTime);
				
				horas.add(sesion);
				
			}
			
		}catch (SQLException e) {
			System.out.println("Error en el cierre de la BD");
			e.printStackTrace();
		}
		
		
		
		
		return horas;
	}

	
	@Override
	public ArrayList<String> getSesionId() {
		ArrayList<String> ids = new ArrayList<String>();
		openConnection();
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(GetSesionIds);
								
			rs = stmt.executeQuery();
			while(rs.next()) {
				ids.add(rs.getString("id"));
			}
			
		}catch (SQLException e) {
			System.out.println("Error en el cierre de la BD");
			e.printStackTrace();
		}
		
		return ids;
	}

	@Override
	public Sesion modificarSesion(Sesion sesion, String newId, double precio, LocalDateTime fecha, String id) {
		this.openConnection();
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String wfecha = fecha.format(formateador);

       
		try {
			stmt = con.prepareStatement(ModificarSesion);

			stmt.setString(1,newId);
			stmt.setDouble(2,precio);
			stmt.setString(3,wfecha);
			stmt.setString(4, id);
			

			if (stmt.executeUpdate()==1) {
				sesion.setId(newId);
				sesion.setFecha(fecha);
				sesion.setPrecio(precio);
								
			}
			
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			
			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
			}
		}

		return sesion;
	}
	
	@Override
	public ArrayList<String> getPelisId() {
		ArrayList<String> ids = new ArrayList<String>();
		openConnection();
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(GetPeliIds);
								
			rs = stmt.executeQuery();
			while(rs.next()) {
				ids.add(rs.getString("id"));
			}
			
		}catch (SQLException e) {
			System.out.println("Error en el cierre de la BD");
			e.printStackTrace();
		}
		
		return ids;
		
	}

	@Override
	public Pelicula modificarPeli(Pelicula peli, String newId, Genero genero, String titulo, int pegi, int duracion, String sinopsis, String id) {
		this.openConnection();
		
		try {
			stmt = con.prepareStatement(ModificarPeli);

			stmt.setString(1,newId);
			stmt.setString(2,genero.toString());
			stmt.setString(3,titulo);
			stmt.setInt(4, pegi);
			stmt.setInt(5, duracion);
			stmt.setString(6, sinopsis);
			stmt.setString(7,id);

			if (stmt.executeUpdate()==1) {
				peli.setId(newId);
				peli.setGenero(genero);
				peli.setTitulo(titulo);
				peli.setPegi(pegi);
				peli.setDuracion(duracion);
				peli.setSinopsis(sinopsis);
			}
			
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			
			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
			}
		}

		return peli;
	}

	
	
	@Override
	public ArrayList<String> getSalasId() {
		ArrayList<String> ids = new ArrayList<String>();
		openConnection();
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(GetSalaId);
								
			rs = stmt.executeQuery();
			while(rs.next()) {
				ids.add(rs.getString("id"));
			}
			
		}catch (SQLException e) {
			System.out.println("Error en el cierre de la BD");
			e.printStackTrace();
		}
		
		return ids;
		
	}

	@Override
	public Sala modificarSala(Sala sala,String newid, int aforo, String id) {
		
		this.openConnection();
		
		try {
			stmt = con.prepareStatement(ModificarSala);

			stmt.setString(1,newid);
			stmt.setInt(2,aforo);
			stmt.setString(3,id);
			

			if (stmt.executeUpdate()==1) {
				sala.setAforo(aforo);
				sala.setId(newid);
				
			}
			
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			
			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
			}
		}

		return sala;
		
	}
	
	
	
	
	@Override
	public Pelicula getPeliInfo(String id) {
		Pelicula pelicula = new Pelicula();
		ResultSet rs = null;

		this.openConnection();
		try {
			//esta select busca por titulo y no por id
			stmt = con.prepareStatement(GetPeliInfo);
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				pelicula.setId(rs.getString("id"));
				pelicula.setGenero(Genero.valueOf(rs.getString("genero").toUpperCase()));
				pelicula.setTitulo(rs.getString("titulo"));
				pelicula.setPegi(rs.getInt("PEGI"));
				pelicula.setDuracion(rs.getInt("duracion"));
				pelicula.setSinopsis(rs.getString("sinopsis"));
			}

			this.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pelicula;
	}

	@Override
	public String[][] getPelis() {
		int rowNum = 0, i = 0;
		ResultSet rs = null;
		String[][] peliculas = null;

		this.openConnection();
		try {
			stmt = con.prepareStatement(GETPeliCorto);

			rs = stmt.executeQuery();
			while (rs.next()) {
				rowNum += 1;
			}
			rs.beforeFirst();
			peliculas = new String[rowNum][2];
			while (rs.next()) {
				peliculas[i][0] = rs.getString("titulo");
				peliculas[i][1] = Integer.toString(rs.getInt("PEGI"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return peliculas;
	}

	// añadido por aitziber
	@Override
	public Pelicula getPeliPorTitulo(String titulo) {
		Pelicula pelicula = null;
		ResultSet rs = null;
		
		this.openConnection();
		try {
			stmt = con.prepareStatement(GETPeliPorTitulo);
			stmt.setString(1, titulo);
			rs = stmt.executeQuery();
					
			if (rs.next()) {
				pelicula = new Pelicula();
				pelicula.setId(rs.getString("id"));
				pelicula.setGenero(Genero.valueOf(rs.getString("genero").toUpperCase()));
				pelicula.setTitulo(rs.getString("titulo"));
				pelicula.setPegi(rs.getInt("PEGI"));
				pelicula.setDuracion(rs.getInt("duracion"));
				pelicula.setSinopsis(rs.getString("sinopsis"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pelicula;
	}
	// añadido por aitziber
	@Override
	public String[][] getUsuarios() {
		int rowNum = 0, i = 0;
		ResultSet rs = null;
		String[][] usuarios = null;

		this.openConnection();
		try {
			stmt = con.prepareStatement(GETUsuarios);

			rs = stmt.executeQuery();
			while (rs.next()) {
				rowNum += 1;
			}
			rs.beforeFirst();
			usuarios = new String[rowNum][4];
			while (rs.next()) {
				usuarios[i][0] = rs.getString("dni");
				usuarios[i][1] = rs.getString("nombre");
				usuarios[i][2] = rs.getString("apellido");
				if (rs.getBoolean("adminCheck")) {
					usuarios[i][3] = "Sí";
				} else {
					usuarios[i][3] = "No";
				}
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuarios;
	}
	// añadido por aitziber
	@Override
	public Usuario getUsuarioPorDni(String dni) {
		Usuario usuario = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
		ResultSet rs = null;
		
		this.openConnection();
		try {
			stmt = con.prepareStatement(GETUsuarioPorDni);
			stmt.setString(1, dni);
			rs = stmt.executeQuery();
					
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setDni(rs.getString("dni"));
				usuario.setContraseña(rs.getString("contraseña"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setEmail(rs.getString("email"));
				usuario.setMetodoPago(rs.getString("metodoPago"));
				if (rs.getString("fechaCaducidadTarjeta")!=null) {
					usuario.setFechaCaducidadTarjeta(YearMonth.parse(rs.getString("fechaCaducidadTarjeta"), formatter));
				}		        
				usuario.setAdminCheck(rs.getBoolean("adminCheck"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}
	// añadido por aitziber
	@Override
	public String[][] getSalas() {
		int rowNum = 0, i = 0;
		ResultSet rs = null;
		String[][] salas = null;

		this.openConnection();
		try {
			stmt = con.prepareStatement(GETSalas);

			rs = stmt.executeQuery();
			while (rs.next()) {
				rowNum += 1;
			}
			rs.beforeFirst();
			salas = new String[rowNum][2];
			while (rs.next()) {
				salas[i][0] = rs.getString("ID");
				salas[i][1] = Integer.toString(rs.getInt("aforo"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return salas;
	}
	// añadido por aitziber
	@Override
	public Sala getSalaPorId(String id) {
		Sala sala = null;
		ResultSet rs = null;
		
		this.openConnection();
		try {
			stmt = con.prepareStatement(GETSalaPorId);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
					
			if (rs.next()) {
				sala = new Sala();
				sala.setId(rs.getString("id"));
				sala.setAforo(rs.getInt("aforo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sala;
	}
	// añadido por aitziber
	@Override
	public String[][] getSesiones() {
		int rowNum = 0, i = 0;
		ResultSet rs = null;
		String[][] sesiones = null;
		Timestamp timestamp;
		Date utilDate, fecha;
		Time hora;

		this.openConnection();
		try {
			stmt = con.prepareStatement(GETSesiones);

			rs = stmt.executeQuery();
			while (rs.next()) {
				rowNum += 1;
			}
			rs.beforeFirst();
			sesiones = new String[rowNum][6];

			while (rs.next()) {
				sesiones[i][0] = rs.getString("id");
				sesiones[i][1] = Double.toString(rs.getDouble("precio"))+" €";
				
				timestamp = rs.getTimestamp("fecha");
				utilDate = new Date(timestamp.getTime());
				
				fecha = new Date(utilDate.getTime());
				hora = new Time(utilDate.getTime());
		
				sesiones[i][2] = fecha.toString();
				sesiones[i][3] = hora.toString().substring(0, hora.toString().length() - 3);
				
				
				sesiones[i][4] = rs.getString("tituloPelicula");
				sesiones[i][5] = rs.getString("idSala");

				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sesiones;
	}
	// añadido por aitziber
	@Override
	public Sesion getSesionPorId(String id) {
		Sesion sesion = null;
		ResultSet rs = null;
		
		this.openConnection();
		try {
			stmt = con.prepareStatement(GETSesionPorId);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
					
			if (rs.next()) {
				sesion = new Sesion();
				sesion.setId(rs.getString("id"));
				sesion.setPrecio(rs.getDouble("precio"));
				sesion.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
				sesion.setIdPelicula(rs.getString("idPelicula"));
				sesion.setIdSala(rs.getString("idSala"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sesion;
	}

	/**
	 * Método de entrar a la app con usuario y contraseña.
	 * 
	 * @param dni      es el dni que reconoce como usuario.
	 * @param password es la contraseña que reconoce como contraseña del usuario.
	 * @return El usuario que ha logeado.
	 */
	@Override
	public Usuario logIn(String dni, String password) {
		ResultSet rs = null;
		Usuario us = new Usuario();

		// Abrimos la conexión
		this.openConnection();

		try {
			stmt = con.prepareStatement(OBTENERusuario);

			// Cargamos los parámetros
			stmt.setString(1, dni);
			stmt.setString(2, password);
			rs = stmt.executeQuery();

			if (rs.next()) {
				us.setDni(dni);
				us.setContraseña(password);
				us.setNombre(rs.getString("nombre"));
				us.setApellido(rs.getString("apellido"));
				us.setEmail(rs.getString("email"));
				if (rs.getString("metodoPago") != null) {
					us.setMetodoPago(rs.getString("metodoPago"));
					us.setFechaCaducidadTarjeta(YearMonth.parse(rs.getString("fechaCaducidadTarjeta")));
				}
				us.setAdminCheck(rs.getBoolean("adminCheck"));
			} else
				us = null;
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			// Cerramos ResultSet
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					System.out.println("Error en cierre del ResultSet");
				}
			}
			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
			}
		}
		return us;
	}

	@Override

	public Usuario modificarDatosUsuario(Usuario us, String dni, String newDni,String nombre, String apellido, String passwd1, String email) {

		// Abrimos la conexión
		this.openConnection();

		try {
			stmt = con.prepareStatement(MODIFICARusuario);

			stmt.setString(1, nombre);
			stmt.setString(2, apellido);
			stmt.setString(3, email);
			stmt.setString(4, passwd1);
			stmt.setString(5, newDni);
			stmt.setString(6, dni);

			if (stmt.executeUpdate() == 1) {
				us.setNombre(nombre);
				us.setApellido(apellido);
				us.setEmail(email);
				us.setContraseña(passwd1);
				us.setDni(newDni);
				
			}

		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {

			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
			}
		}

		return us;
	}

	@Override

	public Usuario modificarDatosUsuarioPago(Usuario us, String dni, String newDni,String nombre, String apellido, String passwd1, String email, String tarjeta, YearMonth fechaCaducidad) {

		// Abrimos la conexión
		this.openConnection();

		try {
			stmt = con.prepareStatement(MODIFICARusuarioPago);

			stmt.setString(1, nombre);
			stmt.setString(2, apellido);
			stmt.setString(3, email);
			stmt.setString(4, passwd1);
			stmt.setString(5, tarjeta);
			stmt.setString(6, String.format("%d-%02d", fechaCaducidad.getYear(), fechaCaducidad.getMonthValue()));
			stmt.setString(7, newDni);
			stmt.setString(8, dni);


			if (stmt.executeUpdate() == 1) {
				us.setNombre(nombre);
				us.setApellido(apellido);
				us.setEmail(email);
				us.setContraseña(passwd1);
				us.setMetodoPago(tarjeta);
				us.setFechaCaducidadTarjeta(fechaCaducidad);
				us.setDni(newDni);
				
			}

		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {

			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
			}
		}

		return us;
	}

	@Override
	public void registrarUsuario(String dni, String nombre, String apellido, String passwd1, String email) {

		// Abrimos la conexión
		this.openConnection();

		try {
			stmt = con.prepareStatement(INSERTARusuario);

			stmt.setString(3, nombre);
			stmt.setString(4, apellido);
			stmt.setString(5, email);
			stmt.setString(2, passwd1);
			stmt.setString(1, dni);

			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {

			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
			}
		}

	}

	private void openConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/cines_g2?serverTimezone=Europe/Madrid&useSSL=false";
			con = DriverManager.getConnection(url, "root", "abcd*1234");

		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
		}
	}

	private void closeConnection() throws SQLException {
		System.out.println("Conexion Cerrada.");
		if (stmt != null)
			stmt.close();
		if (con != null)
			con.close();
		System.out.println("------------------------");
	}


	@Override
	public ArrayList<String> getDni() {
		ArrayList<String> dnis = new ArrayList<String>();
		openConnection();
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(GetDni);
								
			rs = stmt.executeQuery();
			while(rs.next()) {
				dnis.add(rs.getString("dni"));
			}
			
		}catch (SQLException e) {
			System.out.println("Error en el cierre de la BD");
			e.printStackTrace();
		}
		
		return dnis;
	}


	

	
	

	
	
	
}
