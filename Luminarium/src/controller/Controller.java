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

import model.Genero;
import model.Pelicula;
import model.Sala;
import model.Sesion;
import model.Usuario;

public class Controller implements IController{

	private Connection con;
	private PreparedStatement stmt;

	// Sentencias SQL
	final String OBTENERusuario = "SELECT * FROM usuarios WHERE dni=? AND contraseña=?";
	final String MODIFICARusuario = "UPDATE USUARIOS SET nombre=?, apellido=?, email=?, contraseña=?, dni=?, adminCheck=false WHERE dni=?";
	final String MODIFICARusuarioPago = "UPDATE USUARIOS SET nombre=?, apellido=?, email=?, contraseña=?, metodoPago=?, fechaCaducidadTarjeta=?, dni=?, adminCheck=false WHERE dni=?";
	final String INSERTARusuario = "INSERT INTO USUARIOS VALUES (?,?,?,?,?,NULL,NULL,false)";
	final String GETPeliCorto = "SELECT titulo,PEGI from peliculas";
	final String GetPeliInfo = "select * from peliculas where titulo = ?";
	final String GetDni = "select dni from usuarios";
	final String GetSalaId = "select id from salas";
	final String ModificarSala = "update salas set id = ?, aforo=? where id =?";
	final String GetPeliIds = "select id from peliculas";
	final String ModificarPeli = "update peliculas set id=?, genero=?, titulo=?, PEGI=?, duracion=?, sinopsis=? where id=?";
	final String GetSesionIds = "select id from sesiones";
	final String ModificarSesion = "update sesiones set id=?, precio=?, fecha=? where id=?";
	final String getHoraSesion = "select * from sesiones where idPelicula=? and ticketRestante>0";
	final String getSesion = "select * from sesiones where idPelicula=? and ticketRestante>0";
	final String GetPelisIdTitulo = "SELECT titulo, id FROM peliculas";
	
	
	
	
	@Override
	public ArrayList<Sesion> getSesiones() {
		ArrayList<Sesion> horas = new ArrayList<Sesion>();
		openConnection();
		ResultSet rs = null;
		
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = null;
		try {
			stmt = con.prepareStatement(getHoraSesion);
			
								
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
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pelicula;
	}
	
	@Override
	public String[][] getPelis() {
		int rowNum=0,i=0;
		ResultSet rs = null;
		String[][] peliculas=null;
		
				
		this.openConnection();
		try {
			stmt = con.prepareStatement(GETPeliCorto);
			
			rs = stmt.executeQuery();
			while(rs.next()) 
				rowNum+=1;
			
			rs.beforeFirst();
			peliculas= new String[rowNum][2];
			while(rs.next()) {
				peliculas[i][0]= rs.getString("titulo");
				peliculas[i][1]= Integer.toString(rs.getInt("PEGI"));
				i++;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.closeConnection();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
		}
		return peliculas;
	}
	
	/**
	 * Método de entrar a la app con usuario y contraseña.
	 * @param dni es el dni que reconoce como usuario.
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
				if(rs.getString("metodoPago")!=null) {
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

			stmt.setString(1,nombre);
			stmt.setString(2,apellido);
			stmt.setString(3,email);
			stmt.setString(4, passwd1);
			stmt.setString(5, newDni);
			stmt.setString(6, dni);

			if (stmt.executeUpdate()==1) {
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

			stmt.setString(1,nombre);
			stmt.setString(2,apellido);
			stmt.setString(3, email);
			stmt.setString(4, passwd1);
			stmt.setString(5, tarjeta);
			stmt.setString(6, String.format("%d-%02d", fechaCaducidad.getYear(), fechaCaducidad.getMonthValue()));
			stmt.setString(7, newDni);
			stmt.setString(8, dni);

			if (stmt.executeUpdate()==1) {
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
	public void registrarUsuario(String dni,String nombre, String apellido, String passwd1, String email) {
		
		// Abrimos la conexión
		this.openConnection();
		
		try {
			stmt = con.prepareStatement(INSERTARusuario);

			stmt.setString(3,nombre);
			stmt.setString(4,apellido);
			stmt.setString(5,email);
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
		  String url ="jdbc:mysql://localhost:3306/cines_G2?serverTimezone=Europe/Madrid&useSSL=false";
		  con =  DriverManager.getConnection(url,"root","abcd*1234");

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
