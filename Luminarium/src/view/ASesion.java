package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

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
import javax.swing.SwingConstants;
import java.awt.Color;

import javax.swing.JComboBox;

/**
 * Esta clase representa la interfaz gráfica para añadir una nueva sesión al sistema.
 * Extiende la clase JFrame e implementa ActionListener para manejar eventos de botones y selección de elementos.
 */
public class ASesion extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador = new Controller();
	private Usuario user;
	private JButton btnVolver;
	private JButton btnModificar; 
	private JTextField textSesionId;
	private JTextField textPrecio;
	private JTextField textFecha;
	private JLabel lblCabecera;
	private JLabel lblSesionId ;
	private JLabel lblPrecio;
	private JLabel lblPrecioError;
	private JLabel lblFecha;
	private JLabel lblFechaError;
	private JLabel lblSalaError;
	private JLabel lblPeliError;
	private JLabel lblPelicula;
	private JLabel lblSala;
	private JComboBox<String> comboBoxSala;
	private JComboBox<String> comboBoxPelicula;
	private ArrayList<String> idSalas = controlador.getSalasId();
	private HashMap<String, String> pelis = controlador.getTituloIdPelis();

	public ASesion(Controller c, Usuario u) {
		this.controlador=c;
		this.user=u;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(523, 592, 264, 56);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(btnVolver);
		
		btnModificar = new JButton("Añadir");
		btnModificar.setBounds(795, 592, 264, 56);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(btnModificar);
		
		lblCabecera = new JLabel("AÑADIR SESION");
		lblCabecera.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCabecera.setBounds(251, 47, 501, 56);
		contentPane.add(lblCabecera);
		
		lblSesionId = new JLabel("ID");
		lblSesionId.setHorizontalAlignment(SwingConstants.CENTER);
		lblSesionId.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblSesionId.setBounds(10, 157, 190, 56);
		contentPane.add(lblSesionId);
		
		textSesionId = new JTextField();
		textSesionId.setEditable(false);
		textSesionId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textSesionId.setBounds(223, 157, 299, 56);
		contentPane.add(textSesionId);
		textSesionId.setColumns(10);
		textSesionId.setText(c.getUltimoIdSesion());
		
		lblPrecio = new JLabel("PRECIO");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblPrecio.setBounds(10, 313, 190, 56);
		contentPane.add(lblPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPrecio.setColumns(10);
		textPrecio.setBounds(223, 313, 299, 56);
		contentPane.add(textPrecio);
		
		lblPrecioError = new JLabel("");
		lblPrecioError.setForeground(Color.RED);
		lblPrecioError.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrecioError.setBounds(210, 378, 320, 56);
		contentPane.add(lblPrecioError);
		
		lblFecha = new JLabel("FECHA");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblFecha.setBounds(10, 472, 190, 56);
		contentPane.add(lblFecha);
		
		textFecha = new JTextField();
		textFecha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFecha.setColumns(10);
		textFecha.setBounds(223, 472, 299, 56);
		contentPane.add(textFecha);
		
		lblFechaError = new JLabel("");
		lblFechaError.setForeground(Color.RED);
		lblFechaError.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFechaError.setBounds(210, 537, 320, 56);
		contentPane.add(lblFechaError);
		
		comboBoxSala = new JComboBox<String>();
		comboBoxSala.setBounds(676, 157, 299, 56);
		contentPane.add(comboBoxSala);
		
		comboBoxPelicula = new JComboBox<String>();
		comboBoxPelicula.setBounds(676, 417, 299, 56);
		contentPane.add(comboBoxPelicula);
		
		lblSalaError = new JLabel("");
		lblSalaError.setForeground(Color.RED);
		lblSalaError.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSalaError.setBounds(667, 223, 320, 56);
		contentPane.add(lblSalaError);
		
		lblPeliError = new JLabel("");
		lblPeliError.setForeground(Color.RED);
		lblPeliError.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPeliError.setBounds(667, 490, 320, 56);
		contentPane.add(lblPeliError);
		
		lblPelicula = new JLabel("PELICULA");
		lblPelicula.setHorizontalAlignment(SwingConstants.CENTER);
		lblPelicula.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblPelicula.setBounds(731, 348, 190, 56);
		contentPane.add(lblPelicula);
		
		lblSala = new JLabel("SALA");
		lblSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblSala.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblSala.setBounds(731, 90, 190, 56);
		contentPane.add(lblSala);
		
		for (String id:idSalas) {
			comboBoxSala.addItem(id);
		}
		
		for (String titulo:pelis.keySet()) {
			comboBoxPelicula.addItem(titulo);
		}
		
		comboBoxSala.setSelectedIndex(-1);
		comboBoxPelicula.setSelectedIndex(-1);
		
		btnVolver.addActionListener(this);
		btnModificar.addActionListener(this);
		comboBoxPelicula.addActionListener(this);
		comboBoxSala.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();	
		
		if (o==btnVolver) {
			MenuAdmin menuA = new MenuAdmin(controlador, user);
			menuA.setVisible(true);
			dispose();
		}
		
		if(o==btnModificar) {
			try {
				verificarDatos();
			}catch (IllegalEntryData error) {
				 System.out.println("ERROR: "+error.getMessage());
			}
		}
	}
	
	/**
	 * Verifica los datos ingresados por el usuario en un formulario de registro de sesión.
	 * Realiza varias validaciones para asegurarse de que los datos ingresados son correctos.
	 * Si los datos son válidos, se llama al método registrarSesion() del controlador para agregar la sesión a la base de datos.
	 * Si los datos no son válidos, se muestra un mensaje de error en los JLabel correspondientes.
	 * 
	 * @throws IllegalEntryData Si los datos ingresados por el usuario son incorrectos.
	 */
	public void verificarDatos() throws IllegalEntryData {		
		LocalDateTime fecha = null;
		boolean correcto = true;
		lblFechaError.setText("");
		lblPeliError.setText("");
		lblPrecioError.setText("");
		lblSalaError.setText("");
		
		if (textPrecio.equals("")) {
			lblPrecioError.setText("Introduce precio");
			correcto = false;
		} else if (textFecha.equals("")) {
			lblFechaError.setText("Introduce fecha");
			correcto = false;
		} else if (comboBoxSala.getSelectedItem()==null) {
			lblSalaError.setText("Selelcciona sala");
			correcto = false;
		} else if (comboBoxPelicula.getSelectedItem()==null) {
			lblPeliError.setText("Selecciona pelicula");
			correcto = false;
		} else if (!textFecha.equals("")) {
			try {
				fecha = LocalDateTime.parse(textFecha.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
			} catch (DateTimeParseException a) {
				correcto = false;
				lblFechaError.setText("Formato: yyyy-MM-dd HH:mm");
			}
		}
		
		if (correcto) {
			controlador.registrarSesion(textSesionId.getText(), Integer.parseInt(textPrecio.getText()), fecha, (String) comboBoxSala.getSelectedItem(), pelis.get(comboBoxPelicula.getSelectedItem()));
			JOptionPane.showMessageDialog(this,(String)"Sesion añadida correctamente","",JOptionPane.INFORMATION_MESSAGE,null);	
		}
	}
}
