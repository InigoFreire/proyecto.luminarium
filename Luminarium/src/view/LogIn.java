package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Genero;
import model.Pelicula;
import model.Sala;
import model.Sesion;
import model.Usuario;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;

/**
 * La clase LogIn representa una ventana de inicio de sesión (login) en una interfaz
 * gráfica (GUI), donde los usuarios pueden ingresar su DNI y contraseña para acceder
 * al sistema. Esta clase extiende JFrame e implementa ActionListener para manejar
 * las interacciones del usuario con los componentes de la interfaz.
 */
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
	private JToggleButton tglbtnVer;

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
		lblError.setBounds(182, 534, 664, 75);
		contentPane.add(lblError);
		
		btnRegistrar = new JButton("Registrarse");
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRegistrar.setBounds(903, 26, 154, 40);
		contentPane.add(btnRegistrar);
		
		tglbtnVer = new JToggleButton("Ver");
		tglbtnVer.setFont(new Font("Tahoma", Font.PLAIN, 45));
		tglbtnVer.setBounds(871, 277, 111, 59);
		contentPane.add(tglbtnVer);
		char c = passwordField.getEchoChar();
		
		btnEntrar.addActionListener(this);
		btnInvitado.addActionListener(this);
		btnRegistrar.addActionListener(this);
		tglbtnVer.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if (tglbtnVer.isSelected()) {
		            passwordField.setEchoChar((char) 0);
		        } else {
		            passwordField.setEchoChar(c);
		        }
		    }
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();	
		
		if (o==btnEntrar) {
			Usuario user = c.logIn(textField.getText(), new String(passwordField.getPassword()));
			if (user != null) {
				if(user.isAdminCheck()) {
					MenuAdmin menuAdmin= new MenuAdmin(c, user);
					menuAdmin.setVisible(true);
					this.dispose();
				} else {
				VPelicula frame = new VPelicula(c, user);
				frame.setVisible(true);
				this.dispose();
				}
			} else {
				lblError.setText("Usuario no encontrado.");
			}
		} else if (e.getSource()==btnInvitado) {
			Usuario user = new Usuario();
			VPelicula frame = new VPelicula(c, user);
			frame.setVisible(true);
			this.dispose();
		} else if (e.getSource()==btnRegistrar) {
			AUsuario frame = new AUsuario(c, new Usuario());
			frame.setVisible(true);
			this.dispose();
		}
		
		
	}
}
