package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import excepciones.IllegalEntryData;
import model.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;

/**
 * La clase Registrar es una ventana de interfaz gráfica de usuario (GUI) que permite a los usuarios registrarse en un sistema.
 * Extiende la clase JFrame y implementa la interfaz ActionListener para manejar eventos de acción.
 */
public class Registrar extends JFrame implements ActionListener { 

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textDni;
	private JTextField textEmail;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JTextField textApellido;
	private JLabel lblContrasena;
	private JLabel lblDni;
	private JLabel lblEmail;
	private JLabel lblRepiteContrasena;
	private JLabel lblCabecera;
	private JButton btnRegistrarse; 
	private Controller c;
	private JLabel lblPassError;
	private JPasswordField password;
	private JPasswordField passwordR;
	private JLabel lblDniIncorrecto;
	private JLabel lblEmailError;
	private JLabel lblNombreError;
	private JLabel lblApellidoError;
	
	
	public Registrar(Controller Con) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.c=Con;

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(192, 64, 224, 56);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(192, 120, 224, 56);
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(192, 191, 224, 56);
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setBounds(192, 247, 224, 56);
		textApellido.setHorizontalAlignment(SwingConstants.CENTER);
		textApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textApellido.setColumns(10);
		contentPane.add(textApellido);
		
		lblContrasena = new JLabel("CONTRASEÑA");
		lblContrasena.setBounds(192, 340, 224, 56);
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblContrasena);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(625, 64, 224, 56);
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblDni);
		
		textDni = new JTextField();
		textDni.setBounds(625, 120, 224, 56);
		textDni.setHorizontalAlignment(SwingConstants.CENTER);
		textDni.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textDni.setColumns(10);
		contentPane.add(textDni);
		
		lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(625, 191, 224, 56);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(625, 247, 224, 56);
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEmail.setColumns(10);
		contentPane.add(textEmail);
		
		lblRepiteContrasena = new JLabel("REINTRODUCE LA CONTRASEÑA");
		lblRepiteContrasena.setBounds(552, 354, 359, 56);
		lblRepiteContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepiteContrasena.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblRepiteContrasena);
		
		lblCabecera = new JLabel("RESGISTRARSE");
		lblCabecera.setBounds(229, 11, 611, 56);
		lblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCabecera.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblCabecera);
		
		btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.setBounds(387, 510, 264, 56);
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(btnRegistrarse);
		
		lblPassError = new JLabel("");
		lblPassError.setBounds(223, 593, 587, 55);
		lblPassError.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPassError.setForeground(new Color(255, 0, 0));
		contentPane.add(lblPassError);
		
		password = new JPasswordField();
		password.setBounds(192, 407, 224, 56);
		password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(password);
		
		passwordR = new JPasswordField();
		passwordR.setBounds(625, 407, 224, 56);
		passwordR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(passwordR);
		
		lblDniIncorrecto = new JLabel("");
		lblDniIncorrecto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDniIncorrecto.setBounds(859, 120, 235, 56);
		lblDniIncorrecto.setForeground(new Color(255, 0, 0));
		contentPane.add(lblDniIncorrecto);
		
		lblEmailError = new JLabel("");
		lblEmailError.setBounds(859, 247, 170, 56);
		lblEmailError.setForeground(new Color(255, 0, 0));
		lblEmailError.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmailError.setBackground(new Color(255, 255, 255));
		contentPane.add(lblEmailError);
		
		lblNombreError = new JLabel("");
		lblNombreError.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreError.setForeground(new Color(255, 0, 0));
		lblNombreError.setBounds(426, 124, 189, 52);
		contentPane.add(lblNombreError);
		
		lblApellidoError = new JLabel("");
		lblApellidoError.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidoError.setForeground(new Color(255, 0, 0));
		lblApellidoError.setBounds(426, 247, 189, 56);
		contentPane.add(lblApellidoError);
		
		btnRegistrarse.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o= e.getSource();
		
		if(o==btnRegistrarse) {
			try {
				verificarDatos();
			}catch (IllegalEntryData error) {
				 System.out.println("ERROR: "+error.getMessage());
			}
			
		}
	}
	public void verificarDatos() throws IllegalEntryData {
		
		lblDniIncorrecto.setText("");
		lblEmailError.setText("");
		lblPassError.setText("");
		lblNombreError.setText("");
		lblApellidoError.setText("");
		
		Usuario user;
		boolean correcto=true;
		String passwd = new String (password.getPassword());
		String passwdR = new String (passwordR.getPassword());
		
		String   Regex; 
        Pattern pattern; 
        Matcher matcher; 
		
		if(!passwd.equals(passwdR)) {
			lblPassError.setText("Las contraseñas no coinciden");
			correcto=false;
		}
		if(passwd.equals("")&&passwdR.equals("")) {
			lblPassError.setText("Introduce las constraseñas");
		}
		//Comprobar que nombre no esta vacio
		if(textNombre.getText().equals("")) {
			lblNombreError.setText("Introduce el nombre");
			correcto=false;
		}
		//Comprobar que apellido no esta vacio
		if(textApellido.getText().equals("")) {
			lblApellidoError.setText("Introduce el apellido");
			correcto=false;
		}
		//comprobar longitud DNI
	    Regex = "\\d{8}[a-zA-Z]";
        pattern = Pattern.compile(Regex);
        matcher = pattern.matcher(textDni.getText());
		if(!matcher.matches()) {
			lblDniIncorrecto.setText("El DNI tiene que estar formado por 8 numeros y una letra");
			correcto=false;
		}
		
		//Comprobar veracidad email
	     Regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        pattern = Pattern.compile(Regex);
        matcher = pattern.matcher(textEmail.getText());
        if(!matcher.matches()) {
        	lblEmailError.setText("No es un mail valido");
        	correcto=false;
        }
        
        //Comprobar si DNI ya existe en la base de datos 
		ArrayList<String> dnis=c.getDni();
		for(String dni:dnis) {
			if(dni.equalsIgnoreCase(textDni.getText())) {
				correcto=false;
				lblDniIncorrecto.setText("Ese DNI ya existe en la base de datos");
			}
		}
		
        if(correcto) {
        	       	
			c.registrarUsuario(textDni.getText(), textNombre.getText(), textApellido.getText(), passwd, textEmail.getText());
			JOptionPane.showMessageDialog(this,(String)"Usuario registrado","",JOptionPane.INFORMATION_MESSAGE,null);	
			LogIn login = new LogIn(c);
			login.setVisible(true);
			this.dispose();
        }
        if(!correcto){
            throw new IllegalEntryData ("Datos introducidos incorrectos");
        }
		
	}
}
