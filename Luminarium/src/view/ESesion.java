package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Genero;
import model.Sesion;
import model.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;

public class ESesion extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private Usuario user;
	private JButton btnVolver;
	private JButton btnModificar; 
	private JTextField textSesionId;
	private JTextField textPrecio;
	private JTextField textFecha;
	private JLabel lblCabecera;
	private JLabel lblSesionId ;
	private JLabel lblIdError ;
	private JLabel lblPrecio;
	private JLabel lblPrecioError;
	private JLabel lblFecha;
	private JLabel lblFechaError;
	private Sesion sesion;
	private JLabel lblSesionSala;
	private JLabel lblSesionPeli;
	private JComboBox<String> comboBoxSala;
	private JComboBox<String> comboBoxPeli;
	private ArrayList<Sesion> sesiones;

	public ESesion(Controller c, Usuario u, Sesion s) {
		this.controlador=c;
		this.user=u;
		this.sesion=s;
		this.sesiones=c.getSesiones();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(521, 592, 264, 56);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(btnVolver);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(795, 592, 264, 56);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(btnModificar);
		
		lblCabecera = new JLabel("MODIFICAR SESION");
		lblCabecera.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		lblCabecera.setBounds(251, 47, 501, 56);
		contentPane.add(lblCabecera);
		
		lblSesionId = new JLabel("ID");
		lblSesionId.setHorizontalAlignment(SwingConstants.CENTER);
		lblSesionId.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblSesionId.setBounds(10, 150, 275, 56);
		contentPane.add(lblSesionId);
		
		textSesionId = new JTextField();
		textSesionId.setEditable(false);
		textSesionId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textSesionId.setBounds(284, 150, 299, 56);
		contentPane.add(textSesionId);
		textSesionId.setColumns(10);
		
		lblIdError = new JLabel("");
		lblIdError.setForeground(new Color(255, 0, 0));
		lblIdError.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIdError.setBounds(284, 217, 299, 56);
		contentPane.add(lblIdError);
		
		lblPrecio = new JLabel("PRECIO");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblPrecio.setBounds(10, 306, 275, 56);
		contentPane.add(lblPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPrecio.setColumns(10);
		textPrecio.setBounds(292, 306, 299, 56);
		contentPane.add(textPrecio);
		
		lblPrecioError = new JLabel("");
		lblPrecioError.setForeground(Color.RED);
		lblPrecioError.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrecioError.setBounds(292, 373, 299, 56);
		contentPane.add(lblPrecioError);
		
		lblFecha = new JLabel("FECHA\r\n");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblFecha.setBounds(10, 465, 275, 56);
		contentPane.add(lblFecha);
		
		textFecha = new JTextField();
		textFecha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFecha.setColumns(10);
		textFecha.setBounds(292, 465, 299, 56);
		contentPane.add(textFecha);
		
		lblFechaError = new JLabel("");
		lblFechaError.setForeground(Color.RED);
		lblFechaError.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFechaError.setBounds(292, 532, 299, 56);
		contentPane.add(lblFechaError);
		
		textSesionId.setText(sesion.getId());
		textPrecio.setText(String.valueOf(sesion.getPrecio()));
		textFecha.setText(sesion.getFecha().toString());
		
		lblSesionSala = new JLabel("ID Sala");
		lblSesionSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblSesionSala.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblSesionSala.setBounds(679, 200, 275, 56);
		contentPane.add(lblSesionSala);
		
		lblSesionPeli = new JLabel("ID Pelicula");
		lblSesionPeli.setHorizontalAlignment(SwingConstants.CENTER);
		lblSesionPeli.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblSesionPeli.setBounds(679, 367, 275, 56);
		contentPane.add(lblSesionPeli);
		
		comboBoxSala = new JComboBox<String>();
		comboBoxSala.setBounds(679, 267, 275, 56);
		contentPane.add(comboBoxSala);
		
		comboBoxPeli = new JComboBox<String>();
		comboBoxPeli.setBounds(679, 434, 275, 56);
		contentPane.add(comboBoxPeli);
		
		for(Sesion sesion1:sesiones) {
			comboBoxSala.addItem(sesion1.getIdSala());
			comboBoxPeli.addItem(sesion1.getIdPelicula());
			
		}
		comboBoxSala.setSelectedItem(sesion.getIdSala());
		comboBoxPeli.setSelectedItem(sesion.getIdPelicula());
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
		if(o==btnModificar) {
			
			lblPrecioError.setText("");
			lblFechaError.setText("");
			
			boolean correcto=true;
			
			//Controlar que precio no sea negativo
			if(Double.parseDouble(textPrecio.getText())<0) {
				lblPrecioError.setText("El precio no puede ser negativo");
				correcto=false;
			}
			//Comprobar LocalDateTime tiene formato correcto
			try {
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			    LocalDateTime dateTime = LocalDateTime.parse(textFecha.getText(), formatter);
			}catch (DateTimeParseException error) {
				lblFechaError.setText("formato \"yyyy-MM-dd'T'HH:mm:ss\" incorrecto o fecha incorrecta");
				correcto=false;
			}
			
			if(correcto){
				controlador.modificarSesion(sesion, textSesionId.getText(), Double.parseDouble(textPrecio.getText()), LocalDateTime.parse(textFecha.getText()), sesion.getId());
				JOptionPane.showMessageDialog(this,(String)"Sesion modificada correctamente","",JOptionPane.INFORMATION_MESSAGE,null);	
			}
		}
	}
}
