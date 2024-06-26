package view;

import java.awt.EventQueue;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import excepciones.IllegalEntryData;
import model.Usuario;

/**
 * Esta clase representa la interfaz gráfica para modificar un usuario existente en el sistema.
 * Extiende la clase JFrame e implementa ActionListener para manejar eventos de botones y campos de texto.
 */
public class EUsuario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;

	private Usuario user;
	private JTextField textNombre, textEmail, textApellido, textNTarjeta, textFechaCaducidad;
	private JLabel lblNombre, lblApellido, lblContrasena, lblEmail, lblRepiteContrasena, lblCabecera, lblNTarjeta, lblFechaCaducidadyyyymm;
	private JButton btnModificar; 
	private JPasswordField password, passwordR;
	private JLabel lblNombreError;
	private JLabel lblApellidoError;
	private JLabel lblpasswrodError1;
	private JLabel lblTarjetaError;
	private JLabel lblDniError;
	private JLabel lblEmailError;
	private JLabel lblCaducidadError; 
	private JTextField textDni;
	private JLabel lblDni;
	private JButton btnVolver ;
	private Usuario userM;
	private JToggleButton tglbtnVerContrasenas;
	
	public EUsuario(Controller c, Usuario u, Usuario m) {
			this.controlador=c;
			this.user=u;
			this.userM=m;
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(43, 53, 224, 56);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(43, 120, 224, 56);
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(43, 187, 224, 56);
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setBounds(43, 254, 224, 56);
		textApellido.setHorizontalAlignment(SwingConstants.CENTER);
		textApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textApellido.setColumns(10);
		contentPane.add(textApellido);
		
		lblContrasena = new JLabel("CONTRASEÑA");
		lblContrasena.setBounds(43, 321, 224, 56);
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblContrasena);
		
		lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(575, 187, 224, 56);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(575, 254, 224, 56);
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEmail.setColumns(10);
		contentPane.add(textEmail);
		
		lblRepiteContrasena = new JLabel("REINTRODUCE LA CONTRASEÑA");
		lblRepiteContrasena.setBounds(502, 321, 359, 56);
		lblRepiteContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepiteContrasena.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblRepiteContrasena);
		
		lblCabecera = new JLabel("Editar usuario");
		lblCabecera.setBounds(229, 11, 611, 56);
		lblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCabecera.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblCabecera);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(795, 592, 264, 56);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(btnModificar);
		
		password = new JPasswordField();
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(43, 374, 224, 56);
		password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(password);
		
		passwordR = new JPasswordField();
		passwordR.setHorizontalAlignment(SwingConstants.CENTER);
		passwordR.setBounds(575, 374, 224, 56);
		passwordR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(passwordR);
		
		lblNTarjeta = new JLabel("Nº TARJETA");
		lblNTarjeta.setBounds(43, 455, 224, 56);
		lblNTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNTarjeta.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNTarjeta);
		
		lblFechaCaducidadyyyymm = new JLabel("FECHA CADUCIDAD");
		lblFechaCaducidadyyyymm.setBounds(502, 455, 359, 56);
		lblFechaCaducidadyyyymm.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaCaducidadyyyymm.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFechaCaducidadyyyymm.setVisible(true);
		contentPane.add(lblFechaCaducidadyyyymm);
		
		btnModificar.addActionListener(this);
		textNombre.setText(userM.getNombre());
		textApellido.setText(userM.getApellido());
		textEmail.setText(userM.getEmail());
		
		textNTarjeta = new JTextField();
		textNTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		textNTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNTarjeta.setBounds(43, 503, 224, 56);
		contentPane.add(textNTarjeta);
		textNTarjeta.setColumns(10);
		
		textFechaCaducidad = new JTextField();
		textFechaCaducidad.setHorizontalAlignment(SwingConstants.CENTER);
		textFechaCaducidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFechaCaducidad.setBounds(575, 503, 224, 56);
		textFechaCaducidad.setColumns(10);
		contentPane.add(textFechaCaducidad);
		
		lblNombreError = new JLabel("");
		lblNombreError.setBounds(301, 120, 264, 43);
		lblNombreError.setForeground(new Color(255, 0, 0));
		lblNombreError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreError.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNombreError);
		
		lblApellidoError = new JLabel("");
		lblApellidoError.setBounds(301, 258, 264, 43);
		lblApellidoError.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidoError.setForeground(Color.RED);
		lblApellidoError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblApellidoError);
		
		lblpasswrodError1 = new JLabel("");
		lblpasswrodError1.setBounds(301, 379, 264, 43);
		lblpasswrodError1.setHorizontalAlignment(SwingConstants.CENTER);
		lblpasswrodError1.setForeground(Color.RED);
		lblpasswrodError1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblpasswrodError1);
		
		lblTarjetaError = new JLabel("");
		lblTarjetaError.setBounds(301, 507, 264, 43);
		lblTarjetaError.setHorizontalAlignment(SwingConstants.CENTER);
		lblTarjetaError.setForeground(Color.RED);
		lblTarjetaError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblTarjetaError);
		
		lblDniError = new JLabel("");
		lblDniError.setBounds(820, 129, 239, 43);
		lblDniError.setHorizontalAlignment(SwingConstants.CENTER);
		lblDniError.setForeground(Color.RED);
		lblDniError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblDniError);
		
		lblEmailError = new JLabel("");
		lblEmailError.setBounds(820, 267, 239, 43);
		lblEmailError.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailError.setForeground(Color.RED);
		lblEmailError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblEmailError);
		
		lblCaducidadError = new JLabel("");
		lblCaducidadError.setBounds(820, 516, 239, 43);
		lblCaducidadError.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaducidadError.setForeground(Color.RED);
		lblCaducidadError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblCaducidadError);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(575, 53, 224, 56);
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblDni);
		
		textDni = new JTextField();
		textDni.setBounds(575, 120, 224, 56);
		textDni.setText((String) null);
		textDni.setHorizontalAlignment(SwingConstants.CENTER);
		textDni.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textDni.setColumns(10);
		contentPane.add(textDni);
		textDni.setText(userM.getDni());
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnVolver.setBounds(523, 592, 264, 56);
		contentPane.add(btnVolver);
		if(!user.isAdminCheck()) {
			textDni.setEditable(false);
			
		}
		btnVolver.addActionListener(this);	
		
		tglbtnVerContrasenas = new JToggleButton("Ver contraseñas");
		tglbtnVerContrasenas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tglbtnVerContrasenas.setBounds(43, 605, 254, 43);
		contentPane.add(tglbtnVerContrasenas);
		char ch = password.getEchoChar();
		
		btnVolver.addActionListener(this);
		tglbtnVerContrasenas.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if (tglbtnVerContrasenas.isSelected()) {
		            password.setEchoChar((char) 0);
		            passwordR.setEchoChar((char) 0);
		        } else {
		            password.setEchoChar(ch);
		            passwordR.setEchoChar(ch);
		        }
		    }
		});
		
		lblFechaCaducidadyyyymm.setVisible(true);
		textFechaCaducidad.setVisible(true);
		if(userM.getMetodoPago()!=null) {
			textNTarjeta.setText(userM.getMetodoPago());
			textFechaCaducidad.setText(String.format("%02d/%02d", m.getFechaCaducidadTarjeta().getMonthValue(), m.getFechaCaducidadTarjeta().getYear() % 100));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnModificar) {
			try {
				verificarDatos();
			} catch (IllegalEntryData error) {
				 System.out.println("ERROR: "+error.getMessage());
			}
		}

		if (e.getSource()==btnVolver) {
			if(user.isAdminCheck()) {
				VUsuario menuA = new VUsuario(controlador, user);
				menuA.setVisible(true);
				dispose();
			}
			else {
				VPelicula frame = new VPelicula(controlador, user);
				frame.setVisible(true);
				this.dispose();
			}
		}
		
	}
	
	/**
	 * Verifica los datos ingresados por el usuario en un formulario de modificación de usuario.
	 * Realiza varias validaciones, incluyendo la coincidencia de contraseñas, el formato de un DNI,
	 * la validez de una dirección de correo electrónico y el formato de una fecha de caducidad.
	 * 
	 * @throws IllegalEntryData Si los datos ingresados son incorrectos.
	 */
	public void verificarDatos() throws IllegalEntryData {
		
		boolean correcto=true;
		String passwd = new String (password.getPassword());
		String passwdR = new String (passwordR.getPassword());
		String Regex;
        Pattern pattern;
        Matcher matcher;
		
		lblDniError.setText("");
		lblEmailError.setText("");
		lblpasswrodError1.setText("");
		lblNombreError.setText("");
		lblApellidoError.setText("");
		lblCaducidadError.setText("");
		lblTarjetaError.setText("");
		lblpasswrodError1.setText("");
		
		if(!passwd.equals(passwdR)) {
			lblpasswrodError1.setText("Las contraseñas no coinciden");
			correcto=false;
		}
		//comprobrar que ambos campos de contrasena no esten vacios
		if(passwd.equals("")&&passwdR.equals("")) {
			lblpasswrodError1.setText("Introduce las constraseñas");
			correcto=false;
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
		//comprobar DNI
	    Regex = "\\d{8}[a-zA-Z]";
        pattern = Pattern.compile(Regex);
        matcher = pattern.matcher(textDni.getText());
		if(!matcher.matches()) {
			lblDniError.setText("8 numeros y una letra");
			correcto=false;
		}
		
		//Comrprobar que mete un numero de tarjeta correcto
		if(!textNTarjeta.getText().equals("")) {
			if (textNTarjeta.getText().matches(".*[a-zA-Z]+.*")) {
				lblTarjetaError.setText("No letras");
	            correcto=false;
	        } else {
				Regex = "\\d{16}";
		        pattern = Pattern.compile(Regex);
		        matcher = pattern.matcher(textNTarjeta.getText());
		        if(!matcher.matches()) {
		        	lblTarjetaError.setText("Tienen que ser 16 numeros");
		        	correcto=false;
		        }
	        }
		}
		
		 //Comprobar si DNI ya existe en la base de datos y no es el del usuario activo
		ArrayList<String> dnis=controlador.getDni();
		for(String dni:dnis) {
			if(dni.equalsIgnoreCase(textDni.getText())&&!dni.equalsIgnoreCase(userM.getDni())) {
				correcto=false;
				lblDniError.setText("Ese DNI ya existe en la base de datos");
			}
		}
		
		//Comprobar que el formato de caducidad es correcto
        Regex = "(0[1-9]|1[0-2])\\/\\d{2}";
        pattern = Pattern.compile(Regex);
        matcher = pattern.matcher(textFechaCaducidad.getText());
        if(!matcher.matches()) {
        	lblCaducidadError.setText("Formato:\"MM/yy\"");
        	correcto=false;
        }
        
       if((textNTarjeta.getText().equals("")&&!textFechaCaducidad.getText().equals("")) || (!textNTarjeta.getText().equals("")&&textFechaCaducidad.getText().equals(""))) {
    	   lblTarjetaError.setText("Numero de tarjeta y caducidad");
    	   lblCaducidadError.setText("tienen que estar vacios o juntos");
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
        
       
		
		if(correcto) {
			controlador.modificarDatosUsuario(userM, userM.getDni(), textDni.getText(), textNombre.getText(), textApellido.getText(), passwd, textEmail.getText(), textNTarjeta.getText(), YearMonth.parse(textFechaCaducidad.getText(), DateTimeFormatter.ofPattern("MM/yy")));
			JOptionPane.showMessageDialog(this,(String)"Usuario modificado correctamente","",JOptionPane.INFORMATION_MESSAGE,null);
			VUsuario vusuario = new VUsuario(controlador, user);
			vusuario.setVisible(true);
			this.dispose();
		} else {
            throw new IllegalEntryData ("Datos introducidos incorrectos");
        }
		
	}
}
