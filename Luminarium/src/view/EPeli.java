package view;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import model.Genero;

import model.Pelicula;
import model.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

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
	private JTextField textGenero;
	private JLabel lblGeneroError;
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
	private JTextField textSinopsis;
	private JLabel lblSinopsisError;
	private JLabel lblIdError;
	private Pelicula pelicula;


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
		
		lblIdError = new JLabel("");
		lblIdError.setForeground(new Color(255, 0, 0));
		lblIdError.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIdError.setBounds(593, 124, 241, 56);
		contentPane.add(lblIdError);
		
		lblGenero = new JLabel("GENERO");
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGenero.setBounds(56, 194, 241, 56);
		contentPane.add(lblGenero);
		
		textGenero = new JTextField();
		textGenero.setFont(new Font("Tahoma", Font.BOLD, 20));
		textGenero.setColumns(10);
		textGenero.setBounds(318, 194, 274, 59);
		contentPane.add(textGenero);
		
		lblGeneroError = new JLabel("");
		lblGeneroError.setForeground(Color.RED);
		lblGeneroError.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGeneroError.setBounds(593, 194, 241, 56);
		contentPane.add(lblGeneroError);
		
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
		lblTituloError.setBounds(593, 264, 241, 56);
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
		lblPegiError.setBounds(593, 334, 241, 56);
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
		lblDuracionError.setBounds(593, 401, 241, 56);
		contentPane.add(lblDuracionError);
		
		lblSinopsis = new JLabel("SINOPSIS");
		lblSinopsis.setHorizontalAlignment(SwingConstants.CENTER);
		lblSinopsis.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSinopsis.setBounds(56, 474, 241, 56);
		contentPane.add(lblSinopsis);
		
		textSinopsis = new JTextField();
		textSinopsis.setFont(new Font("Tahoma", Font.BOLD, 20));
		textSinopsis.setColumns(10);
		textSinopsis.setBounds(318, 471, 274, 59);
		contentPane.add(textSinopsis);
		
		lblSinopsisError = new JLabel("");
		lblSinopsisError.setForeground(Color.RED);
		lblSinopsisError.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSinopsisError.setBounds(593, 471, 241, 56);
		contentPane.add(lblSinopsisError);
		
		textId.setText(peli.getId());
		textGenero.setText(peli.getGenero().toString());
		textTitulo.setText(peli.getTitulo());
		textPegi.setText(String.valueOf(peli.getPegi()));
		textDuracion.setText(String.valueOf(peli.getDuracion()));
		textSinopsis.setText(peli.getSinopsis());
		
		btnVolver.addActionListener(this);
		btnModificar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		Object o=e.getSource();	
		

		if (o==btnVolver) {
			MenuAdmin menuA = new MenuAdmin(controlador, user);
			menuA.setVisible(true);
			dispose();
		}

		if (o==btnModificar) {
			
			lblGeneroError.setText("");
			lblTituloError.setText("");
			lblPegiError.setText("");
			lblDuracionError.setText("");
			lblSinopsisError.setText("");
			
			boolean correcto=true;
			
			//Controlar Enum genero 
			try {
				Genero.valueOf(textGenero.getText().toUpperCase());
			}catch (IllegalArgumentException error) {
				lblGeneroError.setText("El genero introducido no existe en la base de datos");
				correcto=false;
			}
			//Controlar longitud del titulo
			if(textTitulo.getText().length()>60) {
				lblTituloError.setText("El titulo tiene que contener menor de 60 caracteres");
				correcto=false;
			}
			//Controlar Edad Pegi
			int pegi = Integer.parseInt(textPegi.getText());
			if(pegi<0 || pegi>18) {
				lblPegiError.setText("PEGI tiene que ser una edad entre \"0\" y \"18\"");
				correcto=false;
			}
			//Controlar duracion de la peli
			if(textDuracion.getText().length()>3) {
				lblDuracionError.setText("El numero introducido no puede tener mas de 3 cifras");
				correcto=false;
			}
			//Controlar longitud del String de sinopsis
			if(textSinopsis.getText().length()>150) {
				lblSinopsisError.setText("La longitud de la sinopsis no puede ser mas de 150 caracteres");
				correcto=false;
			}
					
			if(correcto){
				controlador.modificarPeli(peli, textId.getText(),Genero.valueOf(textGenero.getText().toUpperCase()), textTitulo.getText(), Integer.parseInt(textPegi.getText()), Integer.parseInt(textDuracion.getText()), textSinopsis.getText(), peli.getId());
				JOptionPane.showMessageDialog(this,(String)"Sala modificada correctamente","",JOptionPane.INFORMATION_MESSAGE,null);	
			}
		}

	}
}
