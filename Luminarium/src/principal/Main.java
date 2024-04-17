package principal;

import controller.Controller;
import view.LogIn;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Controller c = new Controller();
			LogIn login= new LogIn(c);
			login.setVisible(true);
		
	}

}
