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

public class Registrar extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textContrasena;
	private JTextField textDni;
	private JTextField textEmail;
	private JTextField textRcontrsena;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JTextField textApellido;
	private JLabel lblContrasena;
	private JLabel lblDni;
	private JLabel lblEmail;
	private JLabel lblRepiteContrasena;
	private JLabel lblCabecera;
	private JButton btnRegistrarse; 
	
	
	public Registrar(Controller c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
		
		textContrasena = new JTextField();
		textContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		textContrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textContrasena.setColumns(10);
		textContrasena.setBounds(192, 421, 224, 56);
		contentPane.add(textContrasena);
		
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
		
		lblRepiteContrasena = new JLabel("REPITE CONTRASEÑA");
		lblRepiteContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepiteContrasena.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRepiteContrasena.setBounds(594, 354, 276, 56);
		contentPane.add(lblRepiteContrasena);
		
		textRcontrsena = new JTextField();
		textRcontrsena.setHorizontalAlignment(SwingConstants.CENTER);
		textRcontrsena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textRcontrsena.setColumns(10);
		textRcontrsena.setBounds(625, 421, 224, 56);
		contentPane.add(textRcontrsena);
		
		lblCabecera = new JLabel("RESGISTRARSE");
		lblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCabecera.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCabecera.setBounds(229, 11, 611, 56);
		contentPane.add(lblCabecera);
		
		btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnRegistrarse.setBounds(387, 510, 264, 56);
		contentPane.add(btnRegistrarse);
		
		btnRegistrarse.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o= e.getSource();
		
		if(o==btnRegistrarse) {
			
		}
	}
}
