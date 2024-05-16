package principal;

import controller.Controller;
import view.LogIn;

/**
 * Clase principal que inicia la aplicación.
 */
public class Main {

	/**
     * Método principal que inicia la aplicación.
     * Crea una instancia del controlador y de la ventana de inicio de sesión, y muestra la ventana.
     * @param args Los argumentos de la línea de comandos (no se utilizan en este caso).
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Controller c = new Controller(); 
			LogIn login= new LogIn(c);
			login.setVisible(true);
		
	}

}
