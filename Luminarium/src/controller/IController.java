package controller;

import model.Usuario;

public interface IController {

	public Usuario logIn(String usuario, String password);

	public Usuario modificarDatosUsuario(Usuario us, String dni, String nombre, String apellido, String passwd1,
			String passwd2, String email);//NO sera necesario passwd2

	public void registrarUsuario(String dni, String nombre, String apellido, String passwd1, String email);
	
	

}
