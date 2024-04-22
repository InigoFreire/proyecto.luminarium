package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;

import model.Genero;
import model.Pelicula;
import model.Usuario;

public class Controller implements IController{

	private Connection con;
	private PreparedStatement stmt;

	// Sentencias SQL
	final String OBTENERusuario = "SELECT * FROM usuarios WHERE dni=? AND contraseña=?";
	final String MODIFICARusuario = "UPDATE USUARIOS SET nombre=?, apellido=? email=? contraseña=? dni=? adminCheck=false WHERE dni=?";
	final String MODIFICARusuarioPago = "UPDATE USUARIOS SET nombre=?, apellido=? email=? contraseña=? dni=? metodoPago=? fechaCaducidadTarjeta=? adminCheck=false WHERE dni=?";
	final String INSERTARusuario = "INSERT INTO USUARIOS VALUES (?,?,?,?,?,NULL,NULL,false)";
	final String GETPeliCorto = "SELECT titulo,PEGI from peliculas";
	final String GetPeliInfo = "select * from peliculas where titulo = ?";
	
	
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
	public Usuario modificarDatosUsuario(Usuario us, String dni, String dniInput,String nombre, String apellido, String passwd1, String email) {
		// Abrimos la conexión
		this.openConnection();
		
		try {
			stmt = con.prepareStatement(MODIFICARusuario);

			stmt.setString(1,nombre);
			stmt.setString(2,apellido);
			stmt.setString(3,email);
			stmt.setString(4, passwd1);
			stmt.setString(5, dniInput);
			stmt.setString(6, dni);

			if (stmt.executeUpdate()==1) {
				us.setNombre(nombre);
				us.setApellido(apellido);
				us.setEmail(email);
				us.setContraseña(passwd1);
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
	public Usuario modificarDatosUsuarioPago(Usuario us, String dni, String dniInput, String nombre, String apellido, String passwd1, String email, String tarjeta, YearMonth fechaCaducidad) {
		// Abrimos la conexión
		this.openConnection();
		
		try {
			stmt = con.prepareStatement(MODIFICARusuarioPago);

			stmt.setString(1,nombre);
			stmt.setString(2,apellido);
			stmt.setString(3, email);
			stmt.setString(4, passwd1);
			stmt.setString(5, dni);
			stmt.setString(6, tarjeta);
			stmt.setString(7, String.format("%d-%02d", us.getFechaCaducidadTarjeta().getYear(), us.getFechaCaducidadTarjeta().getMonthValue()));

			if (stmt.executeUpdate()==1) {
				us.setNombre(nombre);
				us.setApellido(apellido);
				us.setEmail(email);
				us.setContraseña(passwd1);
				us.setMetodoPago(tarjeta);
				us.setFechaCaducidadTarjeta(fechaCaducidad);
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
		  String url ="jdbc:mysql://localhost:3306/cines_g2?serverTimezone=Europe/Madrid&useSSL=false";
		  con =  DriverManager.getConnection(url,"dami_g2","abcd*1234");

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
	
}
