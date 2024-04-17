package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth; 

import model.Usuario;

public class Controller implements IController{

	private Connection con;
	private PreparedStatement stmt;

	// Sentencias SQL
	final String OBTENERusuario = "SELECT * FROM usuarios WHERE dni=? AND contraseña=?";
	final String MODIFICARusuario = "UPDATE USUARIOS SET nombre=?, apellido=? email=? contraseña=? dni=? adminCheck=false WHERE dni=?";
	final String MODIFICARusuarioPago = "UPDATE USUARIOS SET nombre=?, apellido=? email=? contraseña=? dni=? metodoPago=? fechaCaducidadTarjeta=? adminCheck=false WHERE dni=?";
	final String INSERTARusuario = "INSERT INTO USUARIOS VALUES (?,?,?,?,?,NULL,NULL,false)";

	
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
			stmt.setString(3,email);
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
	
}
