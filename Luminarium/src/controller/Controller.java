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
	final String OBTENERusuario = "SELECT * FROM usuario WHERE dni=? AND contraseña=?";
	final String MODIFICARusuario = "UPDATE USUARIO SET nombre=?, apellido=? email=? contraseña=? adminCheck=false WHERE dni=?";
	final String INSERTARusuario = "INSERT INTO USUARIO VALUES (?,?,?,?,?,NULL,NULL,false)";

	
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
				us.setMetodoPago(rs.getInt("metodoPago"));
				us.setFechaCaducidadTarjeta(YearMonth.parse(rs.getString("fechaCaducidadTarjeta")));
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
	public Usuario modificarDatosUsuario(Usuario us, String dni,String nombre, String apellido, String passwd1, String passwd2, String email) {
		// Abrimos la conexión
		this.openConnection();
		
		try {
			stmt = con.prepareStatement(MODIFICARusuario);

			stmt.setString(1,nombre);
			stmt.setString(2,apellido);
			stmt.setString(3,email);
			if (passwd1.equals(passwd2)) {
				stmt.setString(4, passwd1);
			}
			stmt.setString(5, dni);

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
	public Usuario registrarUsuario(String dni,String nombre, String apellido, String passwd1, String passwd2, String email) {
		Usuario us = new Usuario();
		
		// Abrimos la conexión
		this.openConnection();
		
		try {
			stmt = con.prepareStatement(INSERTARusuario);

			stmt.setString(3,nombre);
			stmt.setString(4,apellido);
			stmt.setString(5,email);
			if (passwd1.equals(passwd2)) {
				stmt.setString(2, passwd1);
			}
			stmt.setString(1, dni);

			if (stmt.executeUpdate()==1) {
				us.setNombre(nombre);
				us.setApellido(apellido);
				us.setEmail(email);
				us.setContraseña(passwd1);
				us.setDni(dni);
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

	private void openConnection() {
		 try {
		  String url ="jdbc:mysql://localhost:3306/cines_G2?serverTimezone=Europe/Madrid&useSSL=false";
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
