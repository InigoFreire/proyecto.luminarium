package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Usuario;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class LogIn extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnEntrar;
	private JButton btnInvitado;
	private JLabel lblContrasea;
	private JLabel lblDni;
	private JLabel lblError;
	private Controller c;
	private JButton btnRegistrar;

	public LogIn(Controller cont) {
		this.c=cont;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 45));
		textField.setBounds(545, 141, 301, 59);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblDni.setBounds(182, 141, 301, 59);
		contentPane.add(lblDni);
		
		lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblContrasea.setBounds(182, 277, 301, 59);
		contentPane.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 45));
		passwordField.setBounds(545, 277, 301, 59);
		contentPane.add(passwordField);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 45));
		btnEntrar.setBounds(182, 403, 259, 64);
		contentPane.add(btnEntrar);
		
		btnInvitado = new JButton("Entrar como invitado");
		btnInvitado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInvitado.setBounds(587, 403, 259, 59);
		contentPane.add(btnInvitado);
		
		lblError = new JLabel("");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblError.setBounds(182, 529, 664, 75);
		contentPane.add(lblError);
		
		btnRegistrar = new JButton("Registrarse");
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRegistrar.setBounds(903, 26, 154, 40);
		contentPane.add(btnRegistrar);
		
		btnEntrar.addActionListener(this);
		btnInvitado.addActionListener(this);
		btnRegistrar.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==btnEntrar) {
			Usuario user = c.logIn(textField.getText(), new String(passwordField.getPassword()));
			if (user != null) {
				VPeli frame = new VPeli(user, c);
				frame.setVisible(true);
				this.dispose();
			} else {
				lblError.setText("Usuario no encontrado.");
			}
		} else if (e.getSource()==btnInvitado) {
			Usuario user = null;
			VPeli frame = new VPeli(user, c);
			frame.setVisible(true);
			this.dispose();
		} else if (e.getSource()==btnRegistrar) {
			Registrar frame = new Registrar(c);
			frame.setVisible(true);
			this.dispose();
		}
	}
}
