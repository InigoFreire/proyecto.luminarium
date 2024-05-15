package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;

/**
 * Esta clase representa la interfaz gráfica para el registro de un nuevo usuario en el sistema.
 * Extiende la clase JFrame e implementa ActionListener para manejar eventos de botones y campos de texto.
 */
public class AUsuario extends JFrame implements ActionListener { 

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
	private JButton btnVolver;
	private Controller c = new Controller();
	private JPasswordField password;
	private JPasswordField passwordR;
	private JLabel lblDniIncorrecto;
	private JLabel lblEmailError;
	private JLabel lblNombreError;
	private JLabel lblApellidoError;
	private Usuario user;
	private JLabel lblContrasenaError;
	private JToggleButton tglbtnVerContrasenas;
	
	public AUsuario(Controller Con, Usuario us) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.c=Con;
		this.user=us;

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(47, 64, 224, 56);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(47, 120, 224, 56);
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(47, 191, 224, 56);
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setBounds(47, 247, 224, 56);
		textApellido.setHorizontalAlignment(SwingConstants.CENTER);
		textApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textApellido.setColumns(10);
		contentPane.add(textApellido);
		
		lblContrasena = new JLabel("CONTRASEÑA");
		lblContrasena.setBounds(47, 340, 224, 56);
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblContrasena);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(554, 64, 224, 56);
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblDni);
		
		textDni = new JTextField();
		textDni.setBounds(554, 120, 224, 56);
		textDni.setHorizontalAlignment(SwingConstants.CENTER);
		textDni.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textDni.setColumns(10);
		contentPane.add(textDni);
		
		lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(554, 191, 224, 56);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(554, 247, 224, 56);
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEmail.setColumns(10);
		contentPane.add(textEmail);
		
		lblRepiteContrasena = new JLabel("REINTRODUCE LA CONTRASEÑA");
		lblRepiteContrasena.setBounds(481, 354, 359, 56);
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
		
		password = new JPasswordField();
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(47, 407, 224, 56);
		password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(password);
		
		passwordR = new JPasswordField();
		passwordR.setHorizontalAlignment(SwingConstants.CENTER);
		passwordR.setBounds(554, 407, 224, 56);
		passwordR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(passwordR);
		
		lblDniIncorrecto = new JLabel("");
		lblDniIncorrecto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDniIncorrecto.setBounds(788, 120, 251, 56);
		lblDniIncorrecto.setForeground(new Color(255, 0, 0));
		contentPane.add(lblDniIncorrecto);
		
		lblEmailError = new JLabel("");
		lblEmailError.setBounds(788, 247, 251, 56);
		lblEmailError.setForeground(new Color(255, 0, 0));
		lblEmailError.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmailError.setBackground(new Color(255, 255, 255));
		contentPane.add(lblEmailError);
		
		lblNombreError = new JLabel("");
		lblNombreError.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreError.setForeground(new Color(255, 0, 0));
		lblNombreError.setBounds(293, 120, 251, 52);
		contentPane.add(lblNombreError);
		
		lblApellidoError = new JLabel("");
		lblApellidoError.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidoError.setForeground(new Color(255, 0, 0));
		lblApellidoError.setBounds(293, 243, 251, 56);
		contentPane.add(lblApellidoError);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVolver.setBounds(10, 619, 134, 32);
		contentPane.add(btnVolver);
		
		lblContrasenaError = new JLabel("");
		lblContrasenaError.setBounds(282, 407, 262, 56);
		lblContrasenaError.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblContrasenaError.setForeground(new Color(255, 0, 0));
		contentPane.add(lblContrasenaError);
		
		tglbtnVerContrasenas = new JToggleButton("Ver contraseñas");
		tglbtnVerContrasenas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tglbtnVerContrasenas.setBounds(387, 619, 264, 32);
		contentPane.add(tglbtnVerContrasenas);
		char c = password.getEchoChar();
		
		btnVolver.addActionListener(this);
		btnRegistrarse.addActionListener(this);
		tglbtnVerContrasenas.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if (tglbtnVerContrasenas.isSelected()) {
		            password.setEchoChar((char) 0);
		            passwordR.setEchoChar((char) 0);
		        } else {
		            password.setEchoChar(c);
		            passwordR.setEchoChar(c);
		        }
		    }
		});
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnRegistrarse) {
			try {
				verificarDatos();
			}catch (IllegalEntryData error) {
				 System.out.println("ERROR: "+error.getMessage());
			}
			
		} else if (e.getSource()==btnVolver) {
			if (user.isAdminCheck()) {
				VUsuario menuA = new VUsuario(c, user);
				menuA.setVisible(true);
				dispose();
			} else {
				LogIn frame = new LogIn(c);
				frame.setVisible(true);
				this.dispose();
			}
		}
	}
	
	/**
	 * Verifica los datos ingresados por el usuario en un formulario de registro de usuario.
	 * Realiza varias validaciones, incluyendo la coincidencia de contraseñas, el formato de un DNI,
	 * la validez de una dirección de correo electrónico y el formato de una fecha de caducidad.
	 * 
	 * @throws IllegalEntryData Si los datos ingresados son incorrectos.
	 */
	public void verificarDatos() throws IllegalEntryData {
		
		lblDniIncorrecto.setText("");
		lblEmailError.setText("");
		lblContrasenaError.setText("");
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
			lblContrasenaError.setText("Las contraseñas no coinciden");
			correcto=false;
		}
		if(passwd.equals("")&&passwdR.equals("")) {
			lblContrasenaError.setText("Introduce las constraseñas");
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
			lblDniIncorrecto.setText("8 numeros y una letra");
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
        user = c.logIn(textDni.getText(), passwd);
        if(user!=null) {
        	lblDniIncorrecto.setText("Ya existe un usuario con ese DNI");
        	correcto=false;
        }
		
        if(correcto) {        	       	
			c.registrarUsuario(textDni.getText(), textNombre.getText(), textApellido.getText(), passwd, textEmail.getText());
			JOptionPane.showMessageDialog(this,(String)"Usuario registrado correctamente","",JOptionPane.INFORMATION_MESSAGE,null);
			textNombre.setText("");
			textDni.setText("");
			textApellido.setText("");
			textEmail.setText("");
			password.setText("");
			passwordR.setText("");
        } else {
            throw new IllegalEntryData ("Datos introducidos incorrectos");
        }
	}
}
