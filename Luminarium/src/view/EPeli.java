package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import excepciones.IllegalEntryData;
import model.Genero;
import model.Pelicula;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

/**
 * Esta clase representa la interfaz gráfica para modificar una película existente en el sistema.
 * Extiende la clase JFrame e implementa ActionListener para manejar eventos de botones y campos de texto.
 */
public class EPeli extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private Usuario user;
	private JButton btnVolver;
	private Pelicula peli;
	private JButton btnModificar; 
	private JLabel lblCabecera;
	private JLabel lblId;
	private JTextField textId;
	private JLabel lblGenero;
	private JLabel lblTitulo;
	private JTextField textTitulo;
	private JLabel lblTituloError;
	private JLabel lblPegi;
	private JTextField textPegi;
	private JLabel lblPegiError;
	private JLabel lblDuracion;
	private JTextField textDuracion;
	private JLabel lblDuracionError;
	private JLabel lblSinopsis;
	private JLabel lblSinopsisError;
	private JTextArea textAreaSinopsis;
	private JComboBox<String> comboBoxGenero;

	public EPeli(Controller c, Usuario u, Pelicula p) {
		this.controlador=c;
		this.user=u;
		this.peli=p;
		
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
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(795, 592, 264, 56);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(btnModificar);
		
		lblCabecera = new JLabel("MODIFICAR PELICULAS");
		lblCabecera.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCabecera.setBounds(278, 30, 468, 35);
		contentPane.add(lblCabecera);
		
		lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(56, 127, 241, 56);
		contentPane.add(lblId);
		
		textId = new JTextField();
		textId.setEditable(false);
		textId.setFont(new Font("Tahoma", Font.BOLD, 20));
		textId.setBounds(318, 124, 274, 59);
		contentPane.add(textId);
		textId.setColumns(10);
		
		lblGenero = new JLabel("GENERO");
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGenero.setBounds(56, 194, 241, 56);
		contentPane.add(lblGenero);
		
		lblTitulo = new JLabel("TITULO");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(56, 267, 241, 56);
		contentPane.add(lblTitulo);
		
		textTitulo = new JTextField();
		textTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		textTitulo.setColumns(10);
		textTitulo.setBounds(318, 264, 274, 59);
		contentPane.add(textTitulo);
		
		lblTituloError = new JLabel("");
		lblTituloError.setForeground(Color.RED);
		lblTituloError.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTituloError.setBounds(602, 264, 457, 56);
		contentPane.add(lblTituloError);
		
		lblPegi = new JLabel("PEGI");
		lblPegi.setHorizontalAlignment(SwingConstants.CENTER);
		lblPegi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPegi.setBounds(56, 337, 241, 56);
		contentPane.add(lblPegi);
		
		textPegi = new JTextField();
		textPegi.setFont(new Font("Tahoma", Font.BOLD, 20));
		textPegi.setColumns(10);
		textPegi.setBounds(318, 334, 274, 59);
		contentPane.add(textPegi);
		
		lblPegiError = new JLabel("");
		lblPegiError.setForeground(Color.RED);
		lblPegiError.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPegiError.setBounds(602, 334, 457, 56);
		contentPane.add(lblPegiError);
		
		lblDuracion = new JLabel("DURACION");
		lblDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuracion.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDuracion.setBounds(56, 404, 241, 56);
		contentPane.add(lblDuracion);
		
		textDuracion = new JTextField();
		textDuracion.setFont(new Font("Tahoma", Font.BOLD, 20));
		textDuracion.setColumns(10);
		textDuracion.setBounds(318, 401, 274, 59);
		contentPane.add(textDuracion);
		
		lblDuracionError = new JLabel("");
		lblDuracionError.setForeground(Color.RED);
		lblDuracionError.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDuracionError.setBounds(602, 401, 457, 56);
		contentPane.add(lblDuracionError);
		
		lblSinopsis = new JLabel("SINOPSIS");
		lblSinopsis.setHorizontalAlignment(SwingConstants.CENTER);
		lblSinopsis.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSinopsis.setBounds(56, 474, 241, 56);
		contentPane.add(lblSinopsis);
		
		lblSinopsisError = new JLabel("");
		lblSinopsisError.setForeground(Color.RED);
		lblSinopsisError.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSinopsisError.setBounds(602, 471, 457, 56);
		contentPane.add(lblSinopsisError);
		
		textAreaSinopsis = new JTextArea();
		textAreaSinopsis.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textAreaSinopsis.setBounds(318, 471, 274, 117);
		contentPane.add(textAreaSinopsis);
		textAreaSinopsis.setLineWrap(true);
		textAreaSinopsis.setWrapStyleWord(true);
		
		comboBoxGenero = new JComboBox<String>();
		comboBoxGenero.setBounds(318, 193, 274, 57);
		contentPane.add(comboBoxGenero);

		for (Genero genero:Genero.values()) {
			comboBoxGenero.addItem(genero.toString());
		}
		
		textId.setText(peli.getId());
		textTitulo.setText(peli.getTitulo());
		textPegi.setText(String.valueOf(peli.getPegi()));
		textDuracion.setText(String.valueOf(peli.getDuracion()));
		comboBoxGenero.setSelectedItem(peli.getGenero().toString());
		
		btnVolver.addActionListener(this);
		btnModificar.addActionListener(this);
		textAreaSinopsis.append(peli.getSinopsis());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();	
		

		if (o==btnVolver) {
			VPelicula menuA = new VPelicula(controlador, user);
			menuA.setVisible(true);
			dispose();
		}
		
		if (o==btnModificar) {
			try {
				verificarDatos();
			} catch (IllegalEntryData error) {
				 System.out.println("ERROR: "+error.getMessage());
			}
		}
	}
	
	/**
	 * Verifica los datos ingresados por el usuario en un formulario de modificación de pelicula.
	 * Realiza varias validaciones, incluyendo la longitud del titulo, la edad PEGI, la duracion de la pelicula
	 * y la longitud de la sinopsis. Si todas las validaciones son exitosas, se llama al metodo modificarPeli()
	 * del controlador para aplicar los cambios en la película.
	 * 
	 * @throws IllegalEntryData Si los datos ingresados por el usuario son incorrectos.
	 */
public void verificarDatos() throws IllegalEntryData {
		lblTituloError.setText("");
		lblPegiError.setText("");
		lblDuracionError.setText("");
		lblSinopsisError.setText("");
		
		boolean correcto=true;
		
		//Controlar longitud del titulo
		if(textTitulo.getText().length()>60) {
			lblTituloError.setText("Menos de 60 caracteres");
			correcto=false;
		}
		
		//Controlar Edad Pegi
		try {
			int pegi = Integer.parseInt(textPegi.getText());
			if (pegi < 0 || pegi > 18) {
				lblPegiError.setText("PEGI entre \"0\" y \"18\"");
				correcto = false;
			}
		} catch (NumberFormatException error) {
			System.out.println(error);
			lblPegiError.setText("PEGI tienen que ser digitos");
			correcto = false;
		}
		
		//Controlar duracion de la peli
		if (textDuracion.getText().matches(".*[a-zA-Z]+.*")) {
            System.out.println("No letras");
            correcto=false;
        } else {
			if(textDuracion.getText().length()>3) {
				lblDuracionError.setText("No mas de 3 cifras");
				correcto=false;
			} else {
				try {
					Integer.parseInt(textDuracion.getText());
				}catch (NumberFormatException error) {
					System.out.println(error);
					lblDuracionError.setText("La duracion debe ser digitos");
					correcto=false;
				}
			}
        }
		
		//Controlar longitud del String de sinopsis
		if(textAreaSinopsis.getText().length()>150) {
			lblSinopsisError.setText("No mas de 150 caracteres");
			correcto=false;
		}
				
		if(correcto){
			controlador.modificarPeli(peli, textId.getText(), Genero.valueOf((String)comboBoxGenero.getSelectedItem()), textTitulo.getText(), Integer.parseInt(textPegi.getText()), Integer.parseInt(textDuracion.getText()), textAreaSinopsis.getText(), peli.getId());
			JOptionPane.showMessageDialog(this,(String)"Pelicula modificada correctamente","",JOptionPane.INFORMATION_MESSAGE,null);
			VPelicula vpeli = new VPelicula(controlador, user);
			vpeli.setVisible(true);
			this.dispose();
		}
	}
}
