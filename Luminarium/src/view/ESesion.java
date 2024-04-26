package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private ArrayList<String> idSalas = controlador.getSalasId();
	private HashMap<String, Integer> pelis = controlador.getTituloIdPelis();

	public ESesion(Controller c, Usuario u, Sesion s) {
		this.controlador=c;
		this.user=u;
		this.sesion=s;

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
		
		textSesionId.setText(sesion.getId());
		textPrecio.setText(String.valueOf(sesion.getPrecio()));
		textFecha.setText(sesion.getFecha().toString());
		comboBoxSala.setSelectedItem(sesion.getIdSala());
		comboBoxPelicula.setSelectedItem(sesion.getIdPeli());
		
		btnVolver.addActionListener(this);
		btnModificar.addActionListener(this);
		comboBoxPelicula.addActionListener(this);
		comboBoxSala.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();	
		
		if (o==btnVolver) {
			MenuAdmin menuA = new MenuAdmin(controlador, user);
			menuA.setVisible(true);
			dispose();
		}
		if(o==btnModificar) {
			
			boolean correcto=true;
			ArrayList<String> ids = controlador.getSesionId();
			for(String id:ids) {
				if(id.equalsIgnoreCase(textSesionId.getText())&&!id.equalsIgnoreCase(sesion.getId())) {
					lblIdError.setText("Ya existe otra peli con ese ID");
					correcto=false;
				}
			}
			if(correcto){
				controlador.modificarSesion(sesion, textSesionId.getText(), Double.parseDouble(textPrecio.getText()), LocalDateTime.parse(textFecha.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), (String) comboBoxSala.getSelectedItem(), pelis.get(comboBoxPelicula.getSelectedItem()), sesion.getId());
				JOptionPane.showMessageDialog(this,(String)"Sesion modificada correctamente","",JOptionPane.INFORMATION_MESSAGE,null);	
			}
		}
	}
}
