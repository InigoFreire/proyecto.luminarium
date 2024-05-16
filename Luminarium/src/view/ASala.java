package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import excepciones.IllegalEntryData;
import model.Usuario;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;

/**
 * Clase que representa la ventana de registro de una nueva sala en el sistema.
 * Permite a los administradores del sistema registrar salas con un ID y un aforo específicos.
 */
public class ASala extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador = new Controller();
	private Usuario user;
	private JButton btnVolver, btnRegistrar;
	private JTextField textId;
	private JTextField textAforo;
	private JLabel lblId, lblAforo, lblEditarSala;
	private JLabel lblAforoError;

	public ASala(Controller c, Usuario u) {
		this.controlador=c;
		this.user=u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblEditarSala = new JLabel("Añadir Sala");
		lblEditarSala.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEditarSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarSala.setBounds(247, 28, 540, 56);
		contentPane.add(lblEditarSala);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVolver.setBounds(10, 619, 134, 32);
		contentPane.add(btnVolver);
		
		textId = new JTextField();
		textId.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textId.setHorizontalAlignment(SwingConstants.CENTER);
		textId.setEditable(false);
		textId.setBounds(138, 246, 325, 54);
		contentPane.add(textId);
		textId.setColumns(10);
		textId.setText(controlador.getUltimoIdSala());
		
		textAforo = new JTextField();
		textAforo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textAforo.setHorizontalAlignment(SwingConstants.CENTER);
		textAforo.setBounds(558, 246, 325, 54);
		contentPane.add(textAforo);
		textAforo.setColumns(10);
		
		lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblId.setBounds(138, 174, 325, 54);
		contentPane.add(lblId);
		
		lblAforo = new JLabel("Aforo");
		lblAforo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAforo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAforo.setBounds(558, 174, 325, 54);
		contentPane.add(lblAforo);
		
		btnRegistrar = new JButton("Añadir");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRegistrar.setBounds(440, 472, 166, 40);
		contentPane.add(btnRegistrar);
		
		lblAforoError = new JLabel("");
		lblAforoError.setHorizontalAlignment(SwingConstants.CENTER);
		lblAforoError.setForeground(Color.RED);
		lblAforoError.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAforoError.setBounds(558, 332, 325, 40);
		contentPane.add(lblAforoError);
		
		btnVolver.addActionListener(this);
		btnRegistrar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnVolver) {
			VSala menuA = new VSala(controlador, user);
			menuA.setVisible(true);
			dispose();
		} else if (e.getSource()==btnRegistrar) {
			try {
				verificarDatos();
			} catch (IllegalEntryData error) {
				 System.out.println("ERROR: "+error.getMessage());
			}
		}
	} 
	
	/**
	 * Verifica los datos ingresados por el usuario en un formulario de registro de sala.
	 * Realiza una validación para asegurarse de que se ha ingresado un valor para el aforo.
	 * Si se ha ingresado un valor, se llama al método registrarSala() del controlador para añadir la sala a la base de datos.
	 * Si no se ha ingresado un valor para el aforo, se muestra un mensaje de error en el JLabel correspondiente.
	 * 
	 * @throws IllegalEntryData Si los datos ingresados por el usuario son incorrectos.
	 */
	public void verificarDatos() throws IllegalEntryData {
		lblAforoError.setText("");
		
		boolean correcto=true;
		//Controlar que no mete mas de 3 cifras
		if (!textAforo.equals("")) {
			if(textAforo.getText().length()>3) {
				lblAforoError.setText("No se admiten mas de 3 cifras");
				correcto=false;
			}
		} else {
			if (textAforo.getText().matches(".*[a-zA-Z]+.*")) {
	            System.out.println("No letras");
	            correcto=false;
	        } else {
	        	lblAforoError.setText("Introduce aforo");
	        	correcto=false;
	        }
		}
		if(correcto) {
				try {
					controlador.registrarSala(textId.getText(), Integer.parseInt(textAforo.getText()));
					JOptionPane.showMessageDialog(this,(String)"Sala añadida correctamente","",JOptionPane.INFORMATION_MESSAGE,null);
				} catch (NumberFormatException error) {
					lblAforoError.setText("Introduce numeros");
				}
		}
	}
}

