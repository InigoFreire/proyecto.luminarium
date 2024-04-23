package view;

import java.awt.EventQueue;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.YearMonth;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import excepciones.IllegalEntryData;
import model.Usuario;


public class EUsuario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private Usuario user;
	private JTextField textNombre, textEmail, textApellido, textNTarjeta, textFechaCaducidad;
	private JLabel lblNombre, lblApellido, lblContrasena, lblEmail, lblRepiteContrasena, lblCabecera, lblPassError, lblNTarjeta, lblFechaCaducidadyyyymm;
	private JButton btnRegistrarse; 
	private JPasswordField password, passwordR;
	private String[][] peliculas;
	private JLabel lblNombreError;
	private JLabel lblApellidoError;
	private JLabel lblpasswrodError1;
	private JLabel lblTarjetaError;
	private JLabel lblDniError;
	private JLabel lblEmailError;
	private JLabel lblpasswordErrorR;
	private JLabel lblCaducidadError; 
		
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
		textNombre.setBounds(191, 120, 224, 56);
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(191, 187, 224, 56);
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setBounds(191, 254, 224, 56);
		textApellido.setHorizontalAlignment(SwingConstants.CENTER);
		textApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textApellido.setColumns(10);
		contentPane.add(textApellido);
		
		lblContrasena = new JLabel("CONTRASEÑA");
		lblContrasena.setBounds(191, 321, 224, 56);
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblContrasena);
		
		lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(624, 187, 224, 56);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(624, 254, 224, 56);
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEmail.setColumns(10);
		contentPane.add(textEmail);
		
		lblRepiteContrasena = new JLabel("REINTRODUCE LA CONTRASEÑA");
		lblRepiteContrasena.setBounds(551, 321, 359, 56);
		lblRepiteContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepiteContrasena.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblRepiteContrasena);
		
		lblCabecera = new JLabel("Editar usuario");
		lblCabecera.setBounds(229, 11, 611, 56);
		lblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCabecera.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblCabecera);
		
		btnRegistrarse = new JButton("Modificar");
		btnRegistrarse.setBounds(795, 592, 264, 56);
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(btnRegistrarse);
		
		lblPassError = new JLabel("");
		lblPassError.setBounds(10, 592, 590, 55);
		lblPassError.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPassError.setForeground(new Color(255, 0, 0));
		contentPane.add(lblPassError);
		
		password = new JPasswordField();
		password.setBounds(191, 374, 224, 56);
		password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(password);
		
		passwordR = new JPasswordField();
		passwordR.setBounds(624, 374, 224, 56);
		passwordR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(passwordR);
		
		lblNTarjeta = new JLabel("Nº TARJETA");
		lblNTarjeta.setBounds(191, 455, 224, 56);
		lblNTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNTarjeta.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNTarjeta.setVisible(true);
		contentPane.add(lblNTarjeta);
		
		lblFechaCaducidadyyyymm = new JLabel("FECHA CADUCIDAD (YYYY-MM)");
		lblFechaCaducidadyyyymm.setBounds(551, 455, 359, 56);
		lblFechaCaducidadyyyymm.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaCaducidadyyyymm.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFechaCaducidadyyyymm.setVisible(true);
		contentPane.add(lblFechaCaducidadyyyymm);
		
		btnRegistrarse.addActionListener(this);
		textNombre.setText(u.getNombre());
		textApellido.setText(u.getApellido());
		textEmail.setText(u.getEmail());
		
		textNTarjeta = new JTextField();
		textNTarjeta.setBounds(191, 503, 224, 56);
		contentPane.add(textNTarjeta);
		textNTarjeta.setVisible(true);
		textNTarjeta.setColumns(10);
		
		textFechaCaducidad = new JTextField();
		textFechaCaducidad.setBounds(624, 503, 224, 56);
		textFechaCaducidad.setColumns(10);
		textFechaCaducidad.setVisible(false);
		contentPane.add(textFechaCaducidad);
		
		lblNombreError = new JLabel("");
		lblNombreError.setForeground(new Color(255, 0, 0));
		lblNombreError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreError.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreError.setBounds(438, 129, 136, 43);
		contentPane.add(lblNombreError);
		
		lblApellidoError = new JLabel("");
		lblApellidoError.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidoError.setForeground(Color.RED);
		lblApellidoError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellidoError.setBounds(438, 267, 136, 43);
		contentPane.add(lblApellidoError);
		
		lblpasswrodError1 = new JLabel("");
		lblpasswrodError1.setHorizontalAlignment(SwingConstants.CENTER);
		lblpasswrodError1.setForeground(Color.RED);
		lblpasswrodError1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblpasswrodError1.setBounds(438, 388, 136, 43);
		contentPane.add(lblpasswrodError1);
		
		lblTarjetaError = new JLabel("");
		lblTarjetaError.setHorizontalAlignment(SwingConstants.CENTER);
		lblTarjetaError.setForeground(Color.RED);
		lblTarjetaError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTarjetaError.setBounds(438, 516, 136, 43);
		contentPane.add(lblTarjetaError);
		
		lblDniError = new JLabel("");
		lblDniError.setHorizontalAlignment(SwingConstants.CENTER);
		lblDniError.setForeground(Color.RED);
		lblDniError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDniError.setBounds(871, 129, 136, 43);
		contentPane.add(lblDniError);
		
		lblEmailError = new JLabel("");
		lblEmailError.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailError.setForeground(Color.RED);
		lblEmailError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmailError.setBounds(871, 267, 136, 43);
		contentPane.add(lblEmailError);
		
		lblpasswordErrorR = new JLabel("");
		lblpasswordErrorR.setHorizontalAlignment(SwingConstants.CENTER);
		lblpasswordErrorR.setForeground(Color.RED);
		lblpasswordErrorR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblpasswordErrorR.setBounds(871, 387, 136, 43);
		contentPane.add(lblpasswordErrorR);
		
		lblCaducidadError = new JLabel("");
		lblCaducidadError.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaducidadError.setForeground(Color.RED);
		lblCaducidadError.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCaducidadError.setBounds(871, 516, 136, 43);
		contentPane.add(lblCaducidadError);
		
			
		
		lblFechaCaducidadyyyymm.setVisible(true);
		textFechaCaducidad.setVisible(true);
		if(u.getMetodoPago()!=null) {
			textNTarjeta.setText(user.getMetodoPago());
			textFechaCaducidad.setText(String.format("%d-%02d", user.getFechaCaducidadTarjeta().getYear(), user.getFechaCaducidadTarjeta().getMonthValue()));
		}
		
	
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnRegistrarse) {
			try {
				verificarDatos();
			}catch (IllegalEntryData error) {
				 System.out.println("ERROR: "+error.getMessage());
			}
		}
	}
	public void verificarDatos() throws IllegalEntryData {
		
		boolean correcto=true;
		String passwd = new String (password.getPassword());
		String passwdR = new String (passwordR.getPassword());
		String Regex;
        Pattern pattern;
        Matcher matcher;
		
		lblDniError.setText("");
		lblEmailError.setText("");
		lblPassError.setText("");
		lblNombreError.setText("");
		lblApellidoError.setText("");
		lblCaducidadError.setText("");
		lblTarjetaError.setText("");
		
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
		
		//Comrprobar que mete un numero de tarjeta correcto
		if(!textNTarjeta.getText().equals("")) {
			Regex = "\\d{16}";
	        pattern = Pattern.compile(Regex);
	        matcher = pattern.matcher(textNTarjeta.getText());
	        if(!matcher.matches()) {
	        	lblTarjetaError.setText("Tienen que ser 16 numeros");
	        	correcto=false;
	        }
		}
		
		//Comprobar que el formato de caducidad es correcto
        if(!textFechaCaducidad.getText().equals("")) {
        	Regex = "\\d{4}-(0[1-9]|1[0-2])";
	        pattern = Pattern.compile(Regex);
	        matcher = pattern.matcher(textFechaCaducidad.getText());
	        if(!matcher.matches()) {
	        	lblCaducidadError.setText("Introduce una fecha de cucidad correcta Formato:\"yyyy-mm\"");
	        	correcto=false;
	        }
        }
       if(textNTarjeta.getText().equals("")&&!textFechaCaducidad.getText().equals("")) {
    	   lblCaducidadError.setText("Numero de tarjeta y caducidad tienen que estar vacios o correctos");
    	   correcto=false;
       }
       if(!textNTarjeta.getText().equals("")&&textFechaCaducidad.getText().equals("")) {
    	   lblCaducidadError.setText("Numero de tarjeta y caducidad tienen que estar vacios o correctos");
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
			if(textNTarjeta.getText().equals("")) {
				controlador.modificarDatosUsuario(user, user.getDni(), textNombre.getText(), textApellido.getText(), passwd, textEmail.getText());
			}else {
				controlador.modificarDatosUsuarioPago(user, user.getDni(), textNombre.getText(), textApellido.getText(), passwd, textEmail.getText(), textNTarjeta.getText(), YearMonth.parse(textFechaCaducidad.getText()));
			}
			
			peliculas = controlador.getPelis();
			VPeli frame = new VPeli(controlador, user);
			frame.setVisible(true);
			this.dispose();
		} 
		
		if(!correcto){
            throw new IllegalEntryData ("Datos introducidos incorrectos");
        }
		
	}
}
