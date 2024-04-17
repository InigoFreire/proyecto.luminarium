package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;

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
	
	
	public Registrar(Controller Con) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.c=Con;

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(192, 86, 224, 56);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textNombre.setBounds(192, 153, 224, 56);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		lblApellido = new JLabel("APELLIDO");
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblApellido.setBounds(192, 220, 224, 56);
		contentPane.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setHorizontalAlignment(SwingConstants.CENTER);
		textApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textApellido.setColumns(10);
		textApellido.setBounds(192, 287, 224, 56);
		contentPane.add(textApellido);
		
		lblContrasena = new JLabel("CONTRASEÑA");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblContrasena.setBounds(192, 354, 224, 56);
		contentPane.add(lblContrasena);
		
		lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDni.setBounds(625, 86, 224, 56);
		contentPane.add(lblDni);
		
		textDni = new JTextField();
		textDni.setHorizontalAlignment(SwingConstants.CENTER);
		textDni.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textDni.setColumns(10);
		textDni.setBounds(625, 153, 224, 56);
		contentPane.add(textDni);
		
		lblEmail = new JLabel("EMAIL");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmail.setBounds(625, 220, 224, 56);
		contentPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEmail.setColumns(10);
		textEmail.setBounds(625, 287, 224, 56);
		contentPane.add(textEmail);
		
		lblRepiteContrasena = new JLabel("REINTRODUCE LA CONTRASEÑA");
		lblRepiteContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepiteContrasena.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRepiteContrasena.setBounds(552, 354, 359, 56);
		contentPane.add(lblRepiteContrasena);
		
		lblCabecera = new JLabel("RESGISTRARSE");
		lblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCabecera.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCabecera.setBounds(229, 11, 611, 56);
		contentPane.add(lblCabecera);
		
		btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnRegistrarse.setBounds(387, 510, 264, 56);
		contentPane.add(btnRegistrarse);
		
		lblPassError = new JLabel("");
		lblPassError.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPassError.setForeground(new Color(255, 0, 0));
		lblPassError.setBounds(223, 593, 587, 55);
		contentPane.add(lblPassError);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		password.setBounds(192, 407, 224, 56);
		contentPane.add(password);
		
		passwordR = new JPasswordField();
		passwordR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordR.setBounds(625, 407, 224, 56);
		contentPane.add(passwordR);
		
		btnRegistrarse.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o= e.getSource();
		
		if(o==btnRegistrarse) {
			String passwd = new String (password.getPassword());
			if(passwd.equals(new String (passwordR.getPassword()))) {
				c.registrarUsuario(textDni.getText(), textNombre.getName(), textApellido.getText(), passwd, textEmail.getText());
				lblPassError.setText("Usuario registrado correctamente");
				LogIn login = new LogIn(c);
			}
			else {
				lblPassError.setText("Las contraseñas no coinciden");
			}
			
		}
	}
}
