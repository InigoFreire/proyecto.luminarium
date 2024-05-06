package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import model.Sala;
import model.Sesion;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

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
	private JLabel lblSalaError;
	private JLabel lblPeliError;
	private JLabel lblPelicula;
	private JLabel lblSala;
	private JComboBox<String> comboBoxSala;
	private JComboBox<String> comboBoxPelicula;
	private ArrayList<String> idSalas;
	private HashMap<String, String> pelis;
	private JLabel lblTickets;
	private JLabel lblTicketsError;
	private JTextField textTickets;

	public ESesion(Controller c, Usuario u, Sesion s) {
		this.controlador=c;
		this.user=u;
		this.sesion=s;
		this.idSalas = controlador.getSalasId();
		this.pelis = controlador.getTituloIdPelis();
		
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
		
		lblCabecera = new JLabel("EDITAR SESION");
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
		
		lblTickets = new JLabel("Tickets restantes");
		lblTickets.setHorizontalAlignment(SwingConstants.CENTER);
		lblTickets.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTickets.setBounds(687, 398, 275, 56);
		contentPane.add(lblTickets);
		
		textTickets = new JTextField();
		textTickets.setText((String) null);
		textTickets.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textTickets.setColumns(10);
		textTickets.setBounds(687, 466, 299, 56);
		contentPane.add(textTickets);
		
		lblTicketsError = new JLabel("");
		lblTicketsError.setForeground(Color.RED);
		lblTicketsError.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTicketsError.setBounds(687, 532, 299, 56);
		contentPane.add(lblTicketsError);
		
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
		
		lblFecha = new JLabel("FECHA");
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
		textTickets.setText(String.valueOf(sesion.getTicketRestante()));
		
		lblSala = new JLabel("ID Sala");
		lblSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblSala.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblSala.setBounds(687, 90, 275, 56);
		contentPane.add(lblSala);
		
		lblPelicula = new JLabel("ID Pelicula");
		lblPelicula.setHorizontalAlignment(SwingConstants.CENTER);
		lblPelicula.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblPelicula.setBounds(687, 246, 275, 56);
		contentPane.add(lblPelicula);
		
		comboBoxSala = new JComboBox<String>();
		comboBoxSala.setBounds(687, 157, 275, 56);
		contentPane.add(comboBoxSala);
		
		comboBoxPelicula = new JComboBox<String>();
		comboBoxPelicula.setBounds(687, 313, 275, 56);
		contentPane.add(comboBoxPelicula);
		
		for (String id:idSalas) {
			comboBoxSala.addItem(id);
		}
		
		for (Map.Entry<String, String> entry : pelis.entrySet()) {
			comboBoxPelicula.addItem(entry.getKey());
			if (sesion.getIdPelicula().equals(entry.getValue())) {
				comboBoxPelicula.setSelectedItem(entry.getKey());
			}
		}
		
		textSesionId.setText(sesion.getId());
		textPrecio.setText(String.valueOf(sesion.getPrecio()));
		textFecha.setText(sesion.getFecha().toString());
		comboBoxSala.setSelectedItem(sesion.getIdSala());
		
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
			lblTicketsError.setText("");
			
			ArrayList<Sala> salas = controlador.getSalasM();
			boolean correcto=true;
			
			
			//Controlar que precio no sea negativo
			if(Double.parseDouble(textPrecio.getText())<0) {
				lblPrecioError.setText("El precio no puede ser negativo");
				correcto=false;
			}
			//Comprobar LocalDateTime tiene formato correcto
			try {
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			    LocalDateTime dateTime = LocalDateTime.parse(textFecha.getText(), formatter);
			}catch (DateTimeParseException error) {
				lblFechaError.setText("formato \"yyyy-MM-dd'T'HH:mm\" incorrecto o fecha incorrecta");
				correcto=false;
			}
			//Comprobar que el numero de tickets no supera el aforo de la sala en el combo box
			for(Sala sala:salas) {
				if(sala.getId().equals((String)comboBoxSala.getSelectedItem())) {
					int maxTickets = sala.getAforo();
					if(maxTickets<Integer.parseInt(textTickets.getText()) || Integer.parseInt(textTickets.getText())<0) {
						correcto=false;
						lblTicketsError.setText("Aforo maximo: "+ maxTickets);
					}
				}
			}
			
			if(correcto){
				String idPeli = (String)comboBoxPelicula.getSelectedItem();
				idPeli = pelis.get(idPeli); 
				controlador.modificarSesion(sesion, textSesionId.getText(), Double.parseDouble(textPrecio.getText()), LocalDateTime.parse(textFecha.getText()), (String)comboBoxSala.getSelectedItem(),idPeli,sesion.getId(),Integer.parseInt(textTickets.getText()));
				JOptionPane.showMessageDialog(this,(String)"Sesion modificada correctamente","",JOptionPane.INFORMATION_MESSAGE,null);	
			}
		}
	}
}
