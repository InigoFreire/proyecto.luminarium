package principal;

import controller.Controller;
import view.Registrar;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Controller c = new Controller();
			Registrar registrar= new Registrar(c);
			registrar.setVisible(true);
		
	}

}
