package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

/**
 * Controlador principal de la aplicación.
 * Esta clase gestiona la lógica de negocio y la comunicación con la base de datos para las operaciones relacionadas
 * con la gestión de películas, sesiones, salas y usuarios.
 */
public class Controller implements IController {

	private Connection con;
	private PreparedStatement stmt;

	// Sentencias SQL
	final String OBTENERusuario = "SELECT * FROM usuarios WHERE dni=? AND contraseña=?";
	final String MODIFICARusuario = "UPDATE USUARIOS SET nombre=?, apellido=?, email=?, contraseña=?, dni=?, adminCheck=false WHERE dni=?";
	final String MODIFICARusuarioPago = "UPDATE USUARIOS SET nombre=?, apellido=?, email=?, contraseña=?, metodoPago=?, fechaCaducidadTarjeta=?, dni=?, adminCheck=false WHERE dni=?";
	final String INSERTARusuario = "INSERT INTO USUARIOS VALUES (?,?,?,?,?,NULL,NULL,false)";
	final String INSERTARSala = "INSERT INTO salas VALUES (?,?)";
	final String GETPeliCorto = "SELECT titulo,PEGI from peliculas";
	final String GetPeliInfo = "select * from peliculas where titulo = ?";
	final String GetDni = "select dni from usuarios";
	final String GetSalaId = "select id from salas";
	final String ModificarSala = "update salas set id = ?, aforo=? where id =?";
	final String GetPeliIds = "select id from peliculas";
	final String ModificarPeli = "update peliculas set id=?, genero=?, titulo=?, PEGI=?, duracion=?, sinopsis=? where id=?";
	final String GetUltimoIdSala = "call GetNextIDFromSalas()";
	final String GetUltimoIdSesion = "call GetNextIDFromSesiones()";
	final String GetUltimoIdPeli = "call GetNextIDFromPeliculas()";
	final String INSERTARsesion = " INSERT INTO sesiones (id, precio, fecha, idSala, idPelicula, ticketRestante) VALUES (?,?,?,?,?,?)";
	final String INSERTARpeli = "INSERT INTO peliculas VALUES (?,?,?,?,?,?)";
	final String GetPelisIdTitulo = "SELECT titulo, id FROM peliculas";
	final String ModificarSesion = "update sesiones set id=?, precio=?, fecha=?, idSala=?, idPelicula=?, ticketRestante=? where id=?";
	final String getHoraSesion = "select * from sesiones where idPelicula=? and ticketRestante>0";
	final String getSesion = "select * from sesiones";
	final String GETPeliPorTitulo = "SELECT * FROM peliculas WHERE titulo = ?";
	final String GETSalas = "SELECT id, aforo FROM salas";
	final String GETSalaPorId = "SELECT * FROM salas WHERE id = ?";
	final String GETSesiones = "SELECT sesiones.id, sesiones.precio, sesiones.fecha, \r\n"
			+ "(SELECT titulo FROM peliculas WHERE id = sesiones.idPelicula) AS tituloPelicula, sesiones.idSala, sesiones.ticketRestante FROM sesiones";
	final String GETSesionPorId = "SELECT * FROM sesiones WHERE id = ?";
	final String GETUsuarios = "SELECT dni, nombre, apellido, adminCheck FROM usuarios";
	final String GETUsuarioPorDni = "SELECT * FROM usuarios WHERE dni = ?";
	final String GetSesionIds = "select id from sesiones";
	final String BorrarSala ="DELETE FROM salas where id=?";
	final String BorrarPelicula ="DELETE FROM peliculas where id=?";
	final String BorrarSesion ="DELETE FROM sesiones where id=?";
	final String BorrarUsuario ="DELETE FROM usuarios where dni=?";
	final String actulizarEntradas ="select CalcularEntradasRestantes(?,?)";
	
	@Override
	public Sesion actualizarEntradas(Sesion sesion, int entradas) {
		this.openConnection();
			
		try {
			stmt = con.prepareStatement(actulizarEntradas);
			
			stmt.setInt(1, entradas);
			stmt.setString(2, sesion.getId());
			
			stmt.executeQuery();
			
			sesion.setTicketRestante(sesion.getTicketRestante()-entradas);	
					
			
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
	public boolean borrarSalas(ArrayList<Sala> salas) {
		this.openConnection();
		
		boolean correcto = true;
		
		try {
			for (Sala sala: salas) {
				stmt = con.prepareStatement(BorrarSala);
				
				stmt.setString(1, sala.getId());
				
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
			correcto= false;
		} finally {
			
			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
				correcto= false;
			}
		
			

		}
		
		
		return correcto;
	}

	@Override
	public boolean borrarPeliculas(ArrayList<Pelicula> peliculas) {
		this.openConnection();
		
		boolean correcto = true;
		
		try {
			for (Pelicula peli: peliculas) {
				stmt = con.prepareStatement(BorrarPelicula);
				
				stmt.setString(1, peli.getId());
				
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
			correcto= false;
		} finally {
			
			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
				correcto= false;
			}
		
			

		}
		
		
		return correcto;
	}
	

	@Override
	public boolean borrarSesion(ArrayList<Sesion> sesiones) {
this.openConnection();
		
		boolean correcto = true;
		
		try {
			for (Sesion sesion: sesiones) {
				stmt = con.prepareStatement(BorrarSesion);
				
				stmt.setString(1, sesion.getId());
				
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
			correcto= false;
		} finally {
			
			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
				correcto= false;
			}
		
			

		}
		
		
		return correcto;
	}

	@Override
	public boolean borrarUsuarios(ArrayList<String> usuarios) {
this.openConnection();
		
		boolean correcto = true;
		
		try {
			for (String usuario: usuarios) {
				stmt = con.prepareStatement(BorrarUsuario);
				
				stmt.setString(1, usuario);
				
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
			correcto= false;
		} finally {
			
			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
				correcto= false;
			}
		
			

		}
		
		
		return correcto;
	}
	
	
	
	/**
	 * Registra una película en la base de datos.
	 * 
	 * Este metodo establece una conexion con la base de datos y registra una nueva pelicula utilizando los parametros
	 * proporcionados. Si se produce un error durante la ejecución de la consulta SQL, se imprime un mensaje de error 
	 * en la consola y se muestra la traza de la excepcion. Finalmente, se cierra la conexión con la base de datos.
	 * 
	 * @param id El ID de la película a registrar.
	 * @param genero El género de la película.
	 * @param titulo El título de la película.
	 * @param pegi El rating de la película según el sistema PEGI.
	 * @param duracion La duración de la película en minutos.
	 * @param sinopsis La sinopsis de la película.
	 */
	@Override
	public void registrarPeli(String id, Genero genero, String titulo, int pegi, int duracion, String sinopsis) {
		this.openConnection();
		
		try {
			stmt = con.prepareStatement(INSERTARpeli);
			
			stmt.setString(6, sinopsis);
			stmt.setInt(5, duracion);
			stmt.setInt(4, pegi);
			stmt.setString(3, titulo);
			stmt.setString(2, String.valueOf(genero));
			stmt.setString(1, id);
			
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
	
	/**
	 * Devuelve el proximo ID de pelicula para almacenar en la base de datos.
	 * 
	 * Este metodo establece una conexión con la base de datos, ejecuta un procedimiento para obtener el ultimo ID de pelicula mas 1,
	 * y devuelve el valor obtenido. Si no se puede realizar la consulta debido a un error de SQL, se imprime un mensaje de 
	 * error en la consola y se muestra la traza de la excepción.
	 * 
	 * @return El proximo ID de pelicula para almacenar en la base de datos.
	 */
	@Override
	public int getUltimoIdPeli() {
	    this.openConnection();
	    ResultSet rs = null;
	    int id = 0;
	    
	    try {
	        stmt = con.prepareStatement(GetUltimoIdPeli);
	       
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            id = rs.getInt("NextID");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en la consulta de la BD");
	        e.printStackTrace();
	    }
	    
	    return id;
	}

	/**
	 * Registra una nueva sesion en la base de datos.
	 * 
	 * Este metodo establece una conexión con la base de datos y registra una nueva sesión utilizando los parametros
	 * proporcionados. La fecha se formatea según el patron "yyyy-MM-dd HH:mm" antes de ser insertada en la base de datos.
	 * Si se produce un error durante la ejecución de la consulta SQL, se imprime un mensaje de error en la consola y 
	 * se muestra la traza de la excepcion. Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @param id El ID de la sesión.
	 * @param precio El precio de la sesión.
	 * @param fecha La fecha y hora de la sesión.
	 * @param idSala El ID de la sala asociada a la sesion.
	 * @param idPeli El ID de la pelicula asociada a la sesion.
	 */
	@Override
	public void registrarSesion(String id, double precio, LocalDateTime fecha, String idSala, String idPeli, int tickets) {
		
		// Abrimos la conexión
		this.openConnection();
		
		try {
			stmt = con.prepareStatement(INSERTARsesion);
			
			stmt.setString(1, id);
			stmt.setDouble(2, precio);
			stmt.setString(3, fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
			stmt.setString(4, idSala);
			stmt.setString(5, idPeli);
			stmt.setInt(6, tickets);
			
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
	
	/**
	 * Devuelve el proximo ID de sesion para almacenar en la base de datos.
	 * 
	 * Este metodo establece una conexión con la base de datos, ejecuta un procedimiento para obtener el ultimo ID de sesion mas 1,
	 * y devuelve el valor obtenido. Si no se puede realizar la consulta debido a un error de SQL, se imprime un mensaje de 
	 * error en la consola y se muestra la traza de la excepción.
	 * 
	 * @return El proximo ID de sesion para almacenar en la base de datos.
	 */
	@Override
	public String getUltimoIdSesion() {
		this.openConnection();
		ResultSet rs = null;
		String id = null;
		try {
			stmt = con.prepareStatement(GetUltimoIdSesion);
								
			rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getString("NextID");
			}
			
		}catch (SQLException e) {
			System.out.println("Error en la consulta de la BD");
			e.printStackTrace();
		}
		
		return id;
	}
	
	/**
	 * Devuelve el proximo ID de sala para almacenar en la base de datos.
	 * 
	 * Este metodo establece una conexión con la base de datos, ejecuta un procedimiento para obtener el ultimo ID de sala mas 1,
	 * y devuelve el valor obtenido. Si no se puede realizar la consulta debido a un error de SQL, se imprime un mensaje de 
	 * error en la consola y se muestra la traza de la excepción.
	 * 
	 * @return El proximo ID de sala para almacenar en la base de datos.
	 */
	@Override
	public String getUltimoIdSala() {
		this.openConnection();
		ResultSet rs = null;
		String id = null;
		try {
			stmt = con.prepareStatement(GetUltimoIdSala);
								
			rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getString("NextID");
			}
		}catch (SQLException e) {
			System.out.println("Error en la consulta de la BD");
			e.printStackTrace();
		}
		
		return id;
	}
	
	/**
	 * Modifica una sesion existente en la base de datos con nuevos valores proporcionados.
	 * 
	 * Este metodo establece una conexión con la base de datos y modifica la sesion especificada con los nuevos valores 
	 * proporcionados. Si la modificación se realiza con exito, los valores de la sesión se actualizan con los nuevos 
	 * valores proporcionados. Si se produce un error durante la ejecucion de la consulta SQL, se imprime un mensaje de 
	 * error en la consola y se muestra la traza de la excepcion. Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @param sesion La sesion a modificar.
	 * @param newId El nuevo ID de la sesion.
	 * @param precio El nuevo precio de la sesion.
	 * @param fecha La nueva fecha y hora de la sesion.
	 * @param idSala El nuevo ID de la sala asociada a la sesion.
	 * @param idPeli El nuevo ID de la pelicula asociada a la sesion.
	 * @param id El ID original de la sesión que se desea modificar.
	 * @return La sesion modificada con los nuevos valores, o la sesion original si la modificación no se pudo realizar.
	 */
	@Override
	public Sesion modificarSesion(Sesion sesion, String newId, double precio, LocalDateTime fecha, String idSala, String idPeli, String id) {
		this.openConnection();
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String wfecha;

        wfecha = fecha.format(formateador);
		try {
			stmt = con.prepareStatement(ModificarPeli);

			stmt.setString(1, newId);
			stmt.setDouble(2, precio);
			stmt.setString(3, wfecha);
			stmt.setString(4, idSala);
			stmt.setString(5, idPeli);
			stmt.setString(4, id);
			

			if (stmt.executeUpdate()==1) {
				sesion.setId(newId);
				sesion.setFecha(fecha);
				sesion.setPrecio(precio);
				sesion.setIdPelicula(idPeli);
				sesion.setIdSala(idSala);
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
	
	/**
	 * Registra una nueva sala en la base de datos.
	 * 
	 * Este metodo establece una conexion con la base de datos y registra una nueva sala utilizando los parametros
	 * proporcionados. Si se produce un error durante la ejecución de la consulta SQL, se imprime un mensaje de error 
	 * en la consola y se muestra la traza de la excepcion. Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @param id El ID de la sala a registrar.
	 * @param aforo El aforo maximo de la sala.
	 */
	@Override
	public void registrarSala(String id, int aforo) {
		
		// Abrimos la conexión
		this.openConnection();
		
		try {
			stmt = con.prepareStatement(INSERTARSala);

			stmt.setInt(2, aforo);
			stmt.setString(1, id);
			
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
	
	/**
	 * Obtiene una lista de todas las salas almacenadas en la base de datos.
	 * 
	 * Este metodo establece una conexion con la base de datos y obtiene todas las salas almacenadas. 
	 * Luego, crea objetos Sala para cada registro recuperado de la base de datos y los agrega a una lista.
	 * Si se produce un error durante la ejecucion de la consulta SQL, se imprime un mensaje de error 
	 * en la consola y se muestra la traza de la excepcion. Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @return Una lista de todas las salas almacenadas en la base de datos.
	 */
	@Override
	public ArrayList<Sala> getSalasM() {
		ArrayList<Sala> salas = new ArrayList<Sala>();
		openConnection();
		ResultSet rs = null;
        
		try {
			stmt = con.prepareStatement(GETSalas);
								
			rs = stmt.executeQuery();
						
			while(rs.next()) {
				Sala sala = new Sala();
				sala.setId(rs.getString("id"));
				sala.setAforo(rs.getInt("aforo"));
				salas.add(sala);
			}
			
		}catch (SQLException e) {
			System.out.println("Error en el cierre de la BD");
			e.printStackTrace();
		}
		
		return salas;
	}

	/**
	 * Obtiene un mapeo de los titulos de peliculas y sus respectivos IDs almacenados en la base de datos.
	 * 
	 * Este metodo establece una conexion con la base de datos y obtiene todos los titulos de peliculas y sus IDs 
	 * almacenados. Luego, crea un HashMap donde las claves son los títulos de las peliculas y los valores son los IDs 
	 * correspondientes. Si se produce un error durante la ejecucion de la consulta SQL, se imprime un mensaje de error 
	 * en la consola y se muestra la traza de la excepcion. Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @return Un HashMap que mapea los titulos de peliculas con sus respectivos IDs almacenados en la base de datos.
	 */
	@Override
	public HashMap<String, String> getTituloIdPelis() {
		HashMap<String, String> pelis = new HashMap<String, String>();
		this.openConnection();
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(GetPelisIdTitulo);
								
			rs = stmt.executeQuery();
			while(rs.next()) {
				pelis.put(rs.getString("titulo"), rs.getString("id"));
			}
			
		}catch (SQLException e) {
			System.out.println("Error en el cierre de la BD");
			e.printStackTrace();
		}
		
		return pelis;
	}
	
	/**
	 * Obtiene una lista de todas las sesiones almacenadas en la base de datos.
	 * 
	 * Este metodo establece una conexión con la base de datos y obtiene todas las sesiones almacenadas. 
	 * Luego, crea objetos Sesion para cada registro recuperado de la base de datos y los agrega a una lista.
	 * Si se produce un error durante la ejecucion de la consulta SQL, se imprime un mensaje de error 
	 * en la consola y se muestra la traza de la excepcion. Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @return Una lista de todas las sesiones almacenadas en la base de datos.
	 */
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
	
	/**
	 * Obtiene una lista de sesiones asociadas a una pelicula especifica identificada por su ID.
	 * 
	 * Este metodo establece una conexion con la base de datos y obtiene todas las sesiones asociadas a una pelicula 
	 * especifica identificada por su ID. Luego, crea objetos Sesion para cada registro recuperado de la base de datos 
	 * y los agrega a una lista. Si se produce un error durante la ejecución de la consulta SQL, se imprime un mensaje 
	 * de error en la consola y se muestra la traza de la excepcion. Finalmente, se cierra la conexión con la base de datos.
	 * 
	 * @param id El ID de la pelicula para la cual se desean obtener las sesiones.
	 * @return Una lista de sesiones asociadas a la película especifica identificada por su ID.
	 */
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

	/**
	 * Obtiene una lista de IDs de sesiones almacenadas en la base de datos.
	 * 
	 * Este metodo establece una conexion con la base de datos y obtiene todos los IDs de sesiones almacenados. 
	 * Luego, los agrega a una lista. Si se produce un error durante la ejecucion de la consulta SQL, se imprime 
	 * un mensaje de error en la consola y se muestra la traza de la excepcion. Finalmente, se cierra la conexion 
	 * con la base de datos.
	 * 
	 * @return Una lista de todos los IDs de sesiones almacenados en la base de datos.
	 */
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

	/**
	 * Modifica los detalles de una sesion en la base de datos.
	 * 
	 * Este metodo modifica los detalles de una sesion especifica en la base de datos, incluyendo su ID, precio, fecha, 
	 * ID de sala, ID de película y numero de tickets restantes. Para ello, establece una conexion con la base de datos, 
	 * ejecuta una consulta SQL para actualizar los detalles de la sesión y actualiza el objeto Sesion proporcionado con 
	 * los nuevos detalles si la modificación tiene exito. Si se produce un error durante la ejecución de la consulta SQL, 
	 * se imprime un mensaje de error en la consola y se muestra la traza de la excepción. Finalmente, se cierra la conexion 
	 * con la base de datos.
	 * 
	 * @param sesion El objeto Sesion que se va a modificar.
	 * @param newId El nuevo ID de la sesion.
	 * @param precio El nuevo precio de la sesion.
	 * @param fecha La nueva fecha y hora de la sesion.
	 * @param idSala El nuevo ID de la sala asociada a la sesion.
	 * @param idPelicula El nuevo ID de la pelicula asociada a la sesion.
	 * @param id El ID actual de la sesion que se va a modificar.
	 * @param tickets El nuevo numero de tickets restantes para la sesion.
	 * @return El objeto Sesion modificado.
	 */
	@Override
	public Sesion modificarSesion(Sesion sesion, String newId, double precio, LocalDateTime fecha, String idSala,String idPelicula,String id,int tickets) {
		this.openConnection();
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String wfecha = fecha.format(formateador);

		try {
			stmt = con.prepareStatement(ModificarSesion);

			stmt.setString(1,newId);
			stmt.setDouble(2,precio);
			stmt.setString(3,wfecha);
			stmt.setString(4,idSala);
			stmt.setString(5,idPelicula);
			stmt.setInt(6, tickets);
			stmt.setString(7, id);
			
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
	
	/**
	 * Obtiene una lista de IDs de peliculas almacenadas en la base de datos.
	 * 
	 * Este metodo establece una conexion con la base de datos y obtiene todos los IDs de peliculas almacenadas. 
	 * Luego, los agrega a una lista. Si se produce un error durante la ejecucion de la consulta SQL, se imprime 
	 * un mensaje de error en la consola y se muestra la traza de la excepcion. Finalmente, se cierra la conexion 
	 * con la base de datos.
	 * 
	 * @return Una lista de todos los IDs de peliculas almacenadas en la base de datos.
	 */
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
	
	/**
	 * Modifica los detalles de una pelicula en la base de datos.
	 * 
	 * Este metodo modifica los detalles de una pelicula especifica en la base de datos, incluyendo su ID, genero, titulo, 
	 * clasificacion por edad (PEGI), duracion y sinopsis. Para ello, establece una conexion con la base de datos, ejecuta 
	 * una consulta SQL para actualizar los detalles de la pelicula y actualiza el objeto Pelicula proporcionado con los 
	 * nuevos detalles si la modificacion tiene exito. Si se produce un error durante la ejecución de la consulta SQL, se 
	 * imprime un mensaje de error en la consola y se muestra la traza de la excepcion. Finalmente, se cierra la conexión 
	 * con la base de datos.
	 * 
	 * @param peli El objeto Pelicula que se va a modificar.
	 * @param newId El nuevo ID de la pelicula.
	 * @param genero El nuevo genero de la pelicula.
	 * @param titulo El nuevo titulo de la pelicula.
	 * @param pegi La nueva clasificación por edad (PEGI) de la pelicula.
	 * @param duracion La nueva duracion de la pelicula.
	 * @param sinopsis La nueva sinopsis de la pelicula.
	 * @param id El ID actual de la pelicula que se va a modificar.
	 * @return El objeto Pelicula modificado.
	 */
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

	/**
	 * Obtiene una lista de IDs de salas almacenadas en la base de datos.
	 * 
	 * Este metodo establece una conexión con la base de datos y obtiene todos los IDs de salas almacenadas. 
	 * Luego, los agrega a una lista. Si se produce un error durante la ejecucion de la consulta SQL, se imprime 
	 * un mensaje de error en la consola y se muestra la traza de la excepcion. Finalmente, se cierra la conexión 
	 * con la base de datos.
	 * 
	 * @return Una lista de todos los IDs de salas almacenadas en la base de datos.
	 */
	@Override
	public ArrayList<String> getSalasId() {
		ArrayList<String> ids = new ArrayList<String>();
		this.openConnection();
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
		} finally {
			try {
				this.closeConnection();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
				e.printStackTrace();
			}
		}
		
		return ids;
	}

	/**
	 * Modifica los detalles de una sala en la base de datos.
	 * 
	 * Este metodo modifica los detalles de una sala especifica en la base de datos, incluyendo su ID y aforo. Para ello, 
	 * establece una conexion con la base de datos, ejecuta una consulta SQL para actualizar los detalles de la sala y 
	 * actualiza el objeto Sala proporcionado con los nuevos detalles si la modificación tiene exito. Si se produce un 
	 * error durante la ejecucion de la consulta SQL, se imprime un mensaje de error en la consola y se muestra la traza 
	 * de la excepcion. Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @param sala El objeto Sala que se va a modificar.
	 * @param newid El nuevo ID de la sala.
	 * @param aforo El nuevo aforo de la sala.
	 * @param id El ID actual de la sala que se va a modificar.
	 * @return El objeto Sala modificado.
	 */
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
	
	/**
	 * Obtiene la informacion de una pelicula dado su ID.
	 * 
	 * Este metodo busca la informacion de una pelicula en la base de datos utilizando su ID. Establece una conexion 
	 * con la base de datos, ejecuta una consulta SQL para obtener los detalles de la pelicula y los asigna al objeto 
	 * Pelicula correspondiente. Si se encuentra una coincidencia, se asignan los valores de las columnas de la tabla 
	 * a los atributos del objeto Pelicula. Finalmente, se cierra la conexión con la base de datos. Si se produce un 
	 * error durante la ejecucion de la consulta SQL, se imprime la traza de la excepcion.
	 * 
	 * @param id El ID de la pelicula que se desea obtener.
	 * @return El objeto Pelicula con la información correspondiente al ID proporcionado. Si no se encuentra ninguna 
	 * coincidencia en la base de datos, se devuelve un objeto Pelicula vacío.
	 */
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

	/**
	 * Obtiene una matriz de String que contiene informacion resumida sobre todas las peliculas almacenadas en la base de datos.
	 * 
	 * Este metodo establece una conexion con la base de datos y ejecuta una consulta SQL para obtener informacion 
	 * resumida sobre todas las peliculas almacenadas. La información resumida incluye el titulo de la pelicula y su clasificacion 
	 * por edad (PEGI). Los resultados se almacenan en una matriz de String bidimensional. Si se produce un error durante 
	 * la ejecucion de la consulta SQL, se imprime la traza de la excepcion. Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @return Una matriz de String bidimensional que contiene informacion resumida sobre todas las peliculas almacenadas. 
	 * Cada fila de la matriz representa una pelicula, donde el primer elemento de la fila es el titulo de la película y 
	 * el segundo elemento es su clasificacion por edad (PEGI).
	 */
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
	
	/**
	 * Obtiene la información de una pelicula dado su titulo.
	 * 
	 * Este metodo busca la informacion de una pelicula en la base de datos utilizando su titulo. Establece una conexión 
	 * con la base de datos, ejecuta una consulta SQL para obtener los detalles de la pelicula y los asigna al objeto 
	 * Pelicula correspondiente. Si se encuentra una coincidencia, se asignan los valores de las columnas de la tabla 
	 * a los atributos del objeto Pelicula. Finalmente, se cierra la conexión con la base de datos. Si se produce un 
	 * error durante la ejecucion de la consulta SQL, se imprime la traza de la excepcion.
	 * 
	 * @param titulo El título de la pelicula que se desea obtener.
	 * @return El objeto Pelicula con la información correspondiente al título proporcionado. Si no se encuentra ninguna 
	 * coincidencia en la base de datos, se devuelve null.
	 */
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
	
	/**
	 * Obtiene una matriz de String que contiene informacion sobre todos los usuarios almacenados en la base de datos.
	 * 
	 * Este metodo establece una conexion con la base de datos y ejecuta una consulta SQL para obtener informacion 
	 * sobre todos los usuarios almacenados. La informacion incluye el DNI, nombre, apellido y estado de administrador 
	 * (Si/No) de cada usuario. Los resultados se almacenan en una matriz de String bidimensional. Si se produce un error durante 
	 * la ejecucion de la consulta SQL, se imprime la traza de la excepcion. Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @return Una matriz de String bidimensional que contiene informacion sobre todos los usuarios almacenados. 
	 * Cada fila de la matriz representa un usuario, donde el primer elemento de la fila es el DNI del usuario, 
	 * el segundo elemento es su nombre, el tercer elemento es su apellido y el cuarto elemento indica si el usuario es administrador (Si/No).
	 */
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
	
	/**
	 * Obtiene la informacion de un usuario por su numero de DNI.
	 * 
	 * Este metodo establece una conexion con la base de datos y ejecuta una consulta SQL para obtener la informacion 
	 * del usuario correspondiente al numero de DNI especificado. Si se encuentra un usuario con el DNI proporcionado, 
	 * se crea un objeto Usuario y se asignan sus atributos con los valores obtenidos de la base de datos. 
	 * Si no se encuentra ningún usuario con el DNI proporcionado, se devuelve null. 
	 * Si se produce un error durante la ejecución de la consulta SQL, se imprime la traza de la excepcion. 
	 * Finalmente, se cierra la conexión con la base de datos.
	 * 
	 * @param dni El número de DNI del usuario del cual se desea obtener la informacion.
	 * @return Un objeto Usuario que contiene la informacion del usuario correspondiente al número de DNI especificado. 
	 * Si no se encuentra ningun usuario con el DNI proporcionado, se devuelve null.
	 */
	@Override
	public Usuario getUsuarioPorDni(String dni) {
		Usuario usuario = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
		ResultSet rs = null;
		
		this.openConnection();
		try {
			stmt = con.prepareStatement(GETUsuarioPorDni);
			stmt.setString(1, dni);
			rs = stmt.executeQuery();
					
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setDni(rs.getString("dni"));
				usuario.setPassword(rs.getString("contraseña"));
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
	
	/**
	 * Obtiene la informacion de todas las salas del cine.
	 * 
	 * Este metodo establece una conexion con la base de datos y ejecuta una consulta SQL para obtener la informacion 
	 * de todas las salas del cine. Los datos obtenidos se almacenan en un arreglo bidimensional de cadenas, donde cada fila 
	 * representa una sala y cada columna contiene informacion sobre el ID de la sala y su aforo respectivamente. 
	 * Si se produce un error durante la ejecucion de la consulta SQL, se imprime la traza de la excepcion. 
	 * Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @return Una matriz bidimensional que contiene la informacion de todas las salas del cine. 
	 * Cada fila representa una sala y cada columna contiene informacion sobre el ID de la sala y su aforo respectivamente.
	 */
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
	
	/**
	 * Obtiene la informacion de una sala del cine mediante su ID.
	 * 
	 * Este metodo establece una conexion con la base de datos y ejecuta una consulta SQL para obtener la informacion 
	 * de una sala del cine identificada por su ID. Los datos recuperados incluyen el ID de la sala y su aforo. 
	 * Si se encuentra una sala con el ID proporcionado, se crea un objeto Sala y se asignan los valores correspondientes. 
	 * Si no se encuentra ninguna sala con el ID especificado, se devuelve null.
	 * Si se produce un error durante la ejecucion de la consulta SQL, se imprime la traza de la excepcion. 
	 * Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @param id El ID de la sala cuya informacion se desea obtener.
	 * @return Un objeto Sala que contiene la informacion de la sala identificada por el ID especificado, 
	 * o null si no se encuentra ninguna sala con ese ID.
	 */
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
	
	/**
	 * Obtiene la informacion de todas las sesiones de peliculas disponibles en el cine.
	 * 
	 * Este metodo establece una conexion con la base de datos y ejecuta una consulta SQL para obtener la informacion 
	 * de todas las sesiones de peliculas disponibles en el cine. Los datos recuperados incluyen el ID de la sesion, el precio de la entrada, 
	 * la fecha y hora de la sesion, el titulo de la pelicula, el ID de la sala y el numero de entradas restantes. 
	 * Los resultados se almacenan en una matriz bidimensional de cadenas. 
	 * Si se produce un error durante la ejecucion de la consulta SQL, se imprime la traza de la excepcion. 
	 * Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @return Una matriz bidimensional que contiene la información de todas las sesiones de películas disponibles en el cine. 
	 * Cada fila representa una sesión, y las columnas incluyen el ID de la sesión, el precio de la entrada, la fecha, la hora, el titulo de la película, 
	 * el ID de la sala y el número de entradas restantes.
	 */
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
			sesiones = new String[rowNum][7];

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
				sesiones[i][6] = rs.getString("ticketRestante");
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
	
	/**
	 * Obtiene la informacion de una sesion de pelicula especifica por su ID.
	 * 
	 * Este metodo establece una conexion con la base de datos y ejecuta una consulta SQL para obtener la informacion 
	 * de una sesion de pelicula especifica por su ID. Los datos recuperados incluyen el ID de la sesion, el precio de la entrada, 
	 * la fecha y hora de la sesion, el ID de la pelicula asociada, el ID de la sala y el numero de entradas restantes. 
	 * Si se encuentra una sesion con el ID proporcionado, se crea un objeto de sesion y se asignan los valores correspondientes. 
	 * Si no se encuentra ninguna sesion con el ID proporcionado, se devuelve null. 
	 * Si se produce un error durante la ejecucion de la consulta SQL, se imprime la traza de la excepcion. 
	 * Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @param id El ID de la sesion de pelicula que se desea obtener.
	 * @return Un objeto de sesion que contiene la informacion de la sesion de pelicula especifica, si se encuentra; 
	 * de lo contrario, devuelve null si no se encuentra ninguna sesion con el ID proporcionado.
	 */
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
				sesion.setTicketRestante(rs.getInt("ticketRestante"));
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
	 * Realiza la autenticacion de un usuario mediante su DNI y contraseña.
	 * 
	 * Este metodo establece una conexion con la base de datos y ejecuta una consulta SQL para verificar las credenciales de inicio de sesion.
	 * Se pasan el DNI y la contraseña como parámetros para la consulta. Si se encuentra un usuario con las credenciales proporcionadas,
	 * se crea un objeto de usuario y se asignan los valores correspondientes, incluyendo el nombre, apellido, correo electronico, metodo de pago
	 * y si es administrador o no. Si no se encuentra ningun usuario con las credenciales proporcionadas, se devuelve null.
	 * Si se produce un error durante la ejecucion de la consulta SQL, se imprime la traza de la excepcion. Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @param dni El DNI del usuario que intenta iniciar sesion.
	 * @param password La contraseña del usuario que intenta iniciar sesion.
	 * @return Un objeto de usuario si las credenciales son validas; de lo contrario, devuelve null si las credenciales son incorrectas.
	 */
	@Override
	public Usuario logIn(String dni, String password) {
		ResultSet rs = null;
		Usuario us = new Usuario();

		this.openConnection();

		try {
			stmt = con.prepareStatement(OBTENERusuario);

			// Cargamos los parámetros
			stmt.setString(1, dni);
			stmt.setString(2, password);
			rs = stmt.executeQuery();

			if (rs.next()) {
				us.setDni(dni);
				us.setPassword(password);
				us.setNombre(rs.getString("nombre"));
				us.setApellido(rs.getString("apellido"));
				us.setEmail(rs.getString("email"));
				if (rs.getString("metodoPago") != null) {
					us.setMetodoPago(rs.getString("metodoPago"));
					us.setFechaCaducidadTarjeta(YearMonth.parse(rs.getString("fechaCaducidadTarjeta"), DateTimeFormatter.ofPattern("MM/yy")));
				}
				us.setAdminCheck(rs.getBoolean("adminCheck"));
			} else
				us = null;
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
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

	/**
	 * Modifica los datos de un usuario en la base de datos.
	 * 
	 * Este metodo establece una conexion con la base de datos y ejecuta una consulta SQL para actualizar los datos del usuario.
	 * Se pasan como parametros el objeto de usuario a modificar y los nuevos valores para el DNI, nombre, apellido, contraseña y correo electronico.
	 * Se prepara y ejecuta una consulta SQL para actualizar los datos del usuario en la base de datos.
	 * Si la ejecución de la consulta es exitosa (es decir, si se actualiza un solo registro), se actualizan los valores del objeto de usuario con los nuevos valores.
	 * Si se produce un error durante la ejecucion de la consulta SQL, se imprime la traza de la excepcion. Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @param us El objeto de usuario que se va a modificar.
	 * @param dni El DNI actual del usuario.
	 * @param newDni El nuevo DNI del usuario.
	 * @param nombre El nuevo nombre del usuario.
	 * @param apellido El nuevo apellido del usuario.
	 * @param passwd1 La nueva contraseña del usuario.
	 * @param email El nuevo correo electrónico del usuario.
	 * @return El objeto de usuario modificado con los nuevos datos si la actualizacion es exitosa; de lo contrario, devuelve el objeto de usuario sin modificar.
	 */
	@Override
	public Usuario modificarDatosUsuario(Usuario us, String dni, String newDni, String nombre, String apellido, String passwd1, String email) {
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
				us.setPassword(passwd1);
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

	/**
	 * Modifica los datos de un usuario si tiene metodo de pago en la base de datos.
	 * 
	 * Este metodo establece una conexion con la base de datos y ejecuta una consulta SQL para actualizar los datos del usuario si tiene metodo de pago.
	 * Se pasan como parametros el objeto de usuario a modificar y los nuevos valores para el DNI, nombre, apellido, contraseña, correo electronico, metodo de pago y fecha de caducidad de la tarjeta.
	 * Se prepara y ejecuta una consulta SQL para actualizar los datos de pago del usuario en la base de datos.
	 * Si la ejecución de la consulta es exitosa (es decir, si se actualiza un solo registro), se actualizan los valores del objeto de usuario con los nuevos valores.
	 * Si se produce un error durante la ejecucion de la consulta SQL, se imprime la traza de la excepcion. Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @param us El objeto de usuario que se va a modificar.
	 * @param dni El DNI actual del usuario.
	 * @param newDni El nuevo DNI del usuario.
	 * @param nombre El nuevo nombre del usuario.
	 * @param apellido El nuevo apellido del usuario.
	 * @param passwd1 La nueva contraseña del usuario.
	 * @param email El nuevo correo electronico del usuario.
	 * @param tarjeta El nuevo metodo de pago del usuario.
	 * @param fechaCaducidad La nueva fecha de caducidad de la tarjeta del usuario.
	 * @return El objeto de usuario modificado con los nuevos datos si la actualizacion es exitosa; de lo contrario, devuelve el objeto de usuario sin modificar.
	 */
	@Override
	public Usuario modificarDatosUsuarioPago(Usuario us, String dni, String newDni, String nombre, String apellido, String passwd1, String email, String tarjeta, YearMonth fechaCaducidad) {
		// Abrimos la conexión
		this.openConnection();

		try {
			stmt = con.prepareStatement(MODIFICARusuarioPago);

			stmt.setString(1, nombre);
			stmt.setString(2, apellido);
			stmt.setString(3, email);
			stmt.setString(4, passwd1);
			stmt.setString(5, tarjeta);
			stmt.setString(6, String.format("%02d/%02d", fechaCaducidad.getMonthValue(), fechaCaducidad.getYear() % 100));
			stmt.setString(7, newDni);
			stmt.setString(8, dni);

			if (stmt.executeUpdate() == 1) {
				us.setNombre(nombre);
				us.setApellido(apellido);
				us.setEmail(email);
				us.setPassword(passwd1);
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

	/**
	 * Registra un nuevo usuario en la base de datos.
	 * 
	 * Este metodo establece una conexion con la base de datos y ejecuta una consulta SQL para insertar los datos del nuevo usuario.
	 * Se pasan como parametros el DNI, nombre, apellido, contraseña y correo electrónico del nuevo usuario.
	 * Se prepara y ejecuta una consulta SQL para insertar los datos del nuevo usuario en la base de datos.
	 * Si la ejecucion de la consulta es exitosa, se registra el nuevo usuario en la base de datos.
	 * Si se produce un error durante la ejecución de la consulta SQL, se imprime la traza de la excepcion. Finalmente, se cierra la conexion con la base de datos.
	 * 
	 * @param dni El DNI del nuevo usuario.
	 * @param nombre El nombre del nuevo usuario.
	 * @param apellido El apellido del nuevo usuario.
	 * @param passwd1 La contraseña del nuevo usuario.
	 * @param email El correo electrónico del nuevo usuario.
	 */
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

	/**
	 * Abre una conexion con la base de datos.
	 * 
	 * Este metodo establece una conexión con la base de datos utilizando JDBC.
	 * Se configura la URL de conexion, el nombre de usuario y la contraseña para acceder a la base de datos.
	 * Si la conexión se establece correctamente, la variable de conexión `con` se inicializa con la conexión establecida.
	 * Si se produce un error durante la apertura de la conexion, se imprime un mensaje de error en la consola.
	 */
	private void openConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/cinesG2?serverTimezone=Europe/Madrid&useSSL=false";
			con = DriverManager.getConnection(url, "root", "abcd*1234");

		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
		}
	}

	/**
	 * Cierra la conexion con la base de datos.
	 * 
	 * Este metodo cierra la conexion establecida con la base de datos, asi como cualquier declaracion asociada con la conexion.
	 * Si se produce un error al cerrar la declaracion o la conexion, se lanzara una excepcion SQLException.
	 * 
	 * @throws SQLException Si ocurre un error al cerrar la declaracion o la conexion con la base de datos.
	 */
	private void closeConnection() throws SQLException {
		System.out.println("Conexion Cerrada.");
		if (stmt != null)
			stmt.close();
		if (con != null)
			con.close();
		System.out.println("------------------------");
	}

	/**
	 * Obtiene una lista de todos los DNI almacenados en la base de datos.
	 * 
	 * Este metodo abre una conexion con la base de datos y ejecuta una consulta para obtener todos los DNI almacenados.
	 * Luego, crea y devuelve una lista de cadenas que contienen los DNI obtenidos de la base de datos.
	 * Si se produce un error durante la ejecucion de la consulta o al cerrar la conexion con la base de datos,
	 * se imprimira un mensaje de error y se mostrara la traza de la excepcion.
	 * 
	 * @return Una lista de cadenas que representan los DNI almacenados en la base de datos.
	 */
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
