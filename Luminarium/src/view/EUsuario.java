package view;

import java.awt.EventQueue;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Usuario;


public class EUsuario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private Usuario user, usuarioAEditar;
	private JTextField textNombre, textDni, textEmail, textApellido, textNTarjeta, textFechaCaducidad;
	private JLabel lblNombre, lblApellido, lblContrasena, lblDni, lblEmail, lblRepiteContrasena, lblCabecera, lblPassError, lblNTarjeta, lblFechaCaducidadyyyymm;
	private JButton btnRegistrarse, btnVolver; 
	private JPasswordField password, passwordR;

	
	public EUsuario (Controller c, Usuario u, Usuario uAE) {
		//desde VUsuario llamo a este constructor pasandote en el tercer parametro el usuario que se quiere editar
		this.controlador=c;
		this.user=u;
		this.usuarioAEditar=uAE;
	}
	public EUsuario(Controller c, Usuario u) {
			this.controlador=c;
			this.user=u;
			

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(191, 53, 224, 56);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textNombre.setBounds(191, 120, 224, 56);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		lblApellido = new JLabel("APELLIDO");
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblApellido.setBounds(191, 187, 224, 56);
		contentPane.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setHorizontalAlignment(SwingConstants.CENTER);
		textApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textApellido.setColumns(10);
		textApellido.setBounds(191, 254, 224, 56);
		contentPane.add(textApellido);
		
		lblContrasena = new JLabel("CONTRASEÑA");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblContrasena.setBounds(191, 321, 224, 56);
		contentPane.add(lblContrasena);
		
		lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDni.setBounds(624, 53, 224, 56);
		contentPane.add(lblDni);
		
		textDni = new JTextField();
		textDni.setHorizontalAlignment(SwingConstants.CENTER);
		textDni.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textDni.setColumns(10);
		textDni.setBounds(624, 120, 224, 56);
		contentPane.add(textDni);
		
		lblEmail = new JLabel("EMAIL");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmail.setBounds(624, 187, 224, 56);
		contentPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEmail.setColumns(10);
		textEmail.setBounds(624, 254, 224, 56);
		contentPane.add(textEmail);
		
		lblRepiteContrasena = new JLabel("REINTRODUCE LA CONTRASEÑA");
		lblRepiteContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepiteContrasena.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRepiteContrasena.setBounds(551, 321, 359, 56);
		contentPane.add(lblRepiteContrasena);
		
		lblCabecera = new JLabel("Editar usuario");
		lblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCabecera.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCabecera.setBounds(229, 11, 611, 56);
		contentPane.add(lblCabecera);
		
		btnRegistrarse = new JButton("Modificar");
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnRegistrarse.setBounds(795, 592, 264, 56);
		contentPane.add(btnRegistrarse);
		
		lblPassError = new JLabel("");
		lblPassError.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPassError.setForeground(new Color(255, 0, 0));
		lblPassError.setBounds(10, 592, 590, 55);
		contentPane.add(lblPassError);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		password.setBounds(191, 374, 224, 56);
		contentPane.add(password);
		
		passwordR = new JPasswordField();
		passwordR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordR.setBounds(624, 374, 224, 56);
		contentPane.add(passwordR);
		
		lblNTarjeta = new JLabel("Nº TARJETA");
		lblNTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNTarjeta.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNTarjeta.setBounds(191, 455, 224, 56);
		lblNTarjeta.setVisible(false);
		contentPane.add(lblNTarjeta);
		
		lblFechaCaducidadyyyymm = new JLabel("FECHA CADUCIDAD (YYYY-MM)");
		lblFechaCaducidadyyyymm.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaCaducidadyyyymm.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFechaCaducidadyyyymm.setBounds(551, 455, 359, 56);
		lblFechaCaducidadyyyymm.setVisible(false);
		contentPane.add(lblFechaCaducidadyyyymm);
		
		btnRegistrarse.addActionListener(this);
		
		textDni.setText(user.getDni());
		textNombre.setText(user.getNombre());
		textApellido.setText(user.getApellido());
		textEmail.setText(user.getEmail());
		
		textNTarjeta = new JTextField();
		textNTarjeta.setBounds(191, 503, 224, 56);
		contentPane.add(textNTarjeta);
		textNTarjeta.setVisible(false);
		textNTarjeta.setColumns(10);
		
		textFechaCaducidad = new JTextField();
		textFechaCaducidad.setColumns(10);
		textFechaCaducidad.setBounds(624, 503, 224, 56);
		textFechaCaducidad.setVisible(false);
		contentPane.add(textFechaCaducidad);
		
		if (user.getMetodoPago()!=null) {
			lblNTarjeta.setVisible(true);
			textNTarjeta.setVisible(true);
			textNTarjeta.setText(user.getMetodoPago());
			lblFechaCaducidadyyyymm.setVisible(true);
			textFechaCaducidad.setVisible(true);
			textFechaCaducidad.setText(String.format("%d-%02d", user.getFechaCaducidadTarjeta().getYear(), user.getFechaCaducidadTarjeta().getMonthValue()));
		}
		
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 221, 85, 21);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnRegistrarse) {
			String passwd = new String (password.getPassword());
			if(passwd.equals(new String (passwordR.getPassword()))) {
				controlador.modificarDatosUsuario(user, user.getDni(), textDni.getText(), textNombre.getText(), textApellido.getText(), passwd, textEmail.getText());
				//peliculas = controlador.getPelis();
				VPelicula frame = new VPelicula (controlador, user);
				frame.setVisible(true);
				this.dispose();
			} else {
				lblPassError.setText("Las contraseñas no coinciden");
			}
		}
		else if (e.getSource()==btnVolver) {
			MenuAdmin menuA = new MenuAdmin(controlador, user);
			menuA.setVisible(true);
			dispose();
		}
	}
}
