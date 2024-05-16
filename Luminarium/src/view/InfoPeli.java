package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Pelicula;
import model.Sesion;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;

/**
 * La clase InfoPeli representa una ventana de interfaz gráfica (GUI) para
 * mostrar información sobre una película, incluyendo su título, género,
 * clasificación por edad, duración, sinopsis y horarios de sesiones para la
 * compra de entradas. Esta clase extiende JFrame e implementa ActionListener
 * para manejar las interacciones del usuario con varios componentes.
 */
public class InfoPeli extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo, lblGenero, lblPegi, lblDuracion;
	private JButton btnComprar;
	private JMenuBar menuBar;
	private JMenu mnUsuario;
	private JMenuItem mntmModificar, mntmExit;
	private Usuario user;
	private Controller controlador;
	private Pelicula peli;
	private JButton btnAtras;
	private JComboBox<String> comboBoxSesion;
	private ArrayList<Sesion> horas = new ArrayList<Sesion>();
	private String hora,horaS;
	private JLabel lblTItulo;
	private JLabel lblGEnero;
	private JLabel lblPEgi;
	private JLabel lblDuracinmin;
	private JLabel lblSinopsis_1;
	private JLabel lblSesiones;
	private JTextArea lblSinopsis;
	
	public InfoPeli(Controller c, Usuario u, Pelicula p) {
		this.user = u;
		this.controlador = c;
		this.peli = p;
		this.mntmModificar = new JMenuItem();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTitulo = new JLabel("");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(391, 67, 520, 37);
		contentPane.add(lblTitulo);
		lblTitulo.setText(peli.getTitulo());

		lblGenero = new JLabel("");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblGenero.setBounds(391, 114, 560, 37);
		contentPane.add(lblGenero);
		lblGenero.setText(peli.getGenero().name());

		lblPegi = new JLabel("");
		lblPegi.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPegi.setBounds(391, 164, 450, 37);
		contentPane.add(lblPegi);
		lblPegi.setText("+" + peli.getPegi());

		lblDuracion = new JLabel("");
		lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblDuracion.setBounds(391, 211, 560, 37);
		contentPane.add(lblDuracion);
		lblDuracion.setText(peli.getDuracion() + " min");

		btnComprar = new JButton("Comprar entradas");

		btnComprar.setBounds(823, 614, 236, 37);
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 25));

		contentPane.add(btnComprar);

		menuBar = new JMenuBar();
		menuBar.setBounds(882, 10, 177, 36);
		contentPane.add(menuBar);

		if (user.getNombre().isBlank()) {
			mnUsuario = new JMenu("Invitado");
		} else {
			mnUsuario = new JMenu(user.getNombre());
		}
		menuBar.add(mnUsuario);
		mnUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		if (!user.getNombre().isBlank()) {
			mntmModificar = new JMenuItem("Modificar");
			mnUsuario.add(mntmModificar);
			mntmModificar.addActionListener(this);
		}

		mntmExit = new JMenuItem("Cerrar Sesión");
		mnUsuario.add(mntmExit);

		btnAtras = new JButton("Volver");
		btnAtras.setBounds(10, 614, 177, 35);
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnAtras);
		
		horas =c.getHoraSesion(peli.getId());
		comboBoxSesion = new JComboBox<String>();
		comboBoxSesion.setBounds(391, 489, 293, 37);
		contentPane.add(comboBoxSesion);
		
		lblTItulo = new JLabel("Título:");
		lblTItulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTItulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTItulo.setBounds(172, 67, 177, 37);
		contentPane.add(lblTItulo);
		
		lblGEnero = new JLabel("Género:");
		lblGEnero.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblGEnero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGEnero.setBounds(172, 114, 177, 37);
		contentPane.add(lblGEnero);
		
		lblPEgi = new JLabel("Edad:");
		lblPEgi.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPEgi.setHorizontalAlignment(SwingConstants.CENTER);
		lblPEgi.setBounds(172, 164, 177, 37);
		contentPane.add(lblPEgi);
		
		lblDuracinmin = new JLabel("Duración (min):");
		lblDuracinmin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblDuracinmin.setBounds(172, 211, 177, 37);
		contentPane.add(lblDuracinmin);
		
		lblSinopsis_1 = new JLabel("Sinopsis:");
		lblSinopsis_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSinopsis_1.setBounds(172, 317, 177, 37);
		contentPane.add(lblSinopsis_1);
		
		lblSesiones = new JLabel("Sesiones:");
		lblSesiones.setHorizontalAlignment(SwingConstants.CENTER);
		lblSesiones.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSesiones.setBounds(172, 489, 177, 37);
		contentPane.add(lblSesiones);
		
		lblSinopsis = new JTextArea();
		lblSinopsis.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblSinopsis.setBackground(new Color(240, 240, 240));
		lblSinopsis.setBounds(391, 265, 520, 182);
		lblSinopsis.setEditable(false);
		lblSinopsis.setText(p.getSinopsis());
		lblSinopsis.setLineWrap(true);
		lblSinopsis.setWrapStyleWord(true);
		contentPane.add(lblSinopsis);


		if(horas.isEmpty()) {
			comboBoxSesion.addItem("No hay sesiones disponibles");
			btnComprar.setEnabled(false);
		} else {
			for(Sesion sesion:horas) {
				hora = sesion.getFecha().toString();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm:ss");
				hora=LocalDateTime.parse(hora).format(formatter);
				hora= hora.substring(0,hora.length()-3);
				hora=hora.substring(5);
				comboBoxSesion.addItem(hora);
			}
		}
		

		btnComprar.addActionListener(this);

		mntmModificar.addActionListener(this);
		mntmExit.addActionListener(this);
		mnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mnUsuario.doClick();
			}
		});


		btnAtras.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmModificar) {
			EUsuario frame = new EUsuario(controlador, user, user);
			frame.setVisible(true);
			this.dispose();
		} else if (e.getSource() == mntmExit) {
			LogIn logIn = new LogIn(controlador);
			logIn.setVisible(true);
			dispose();
		}
		
		if(e.getSource()==btnAtras) {
			VPelicula vpeli = new VPelicula(controlador, user);
			vpeli.setVisible(true);
			this.dispose();
		}
		
		if(e.getSource()==btnComprar) {
			hora = (String)comboBoxSesion.getSelectedItem(); 
			Sesion sesionEle=null;
			for(Sesion sesion:horas) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm:ss");
				horaS=LocalDateTime.parse(sesion.getFecha().toString()).format(formatter);
				horaS= horaS.substring(0,horaS.length()-3);
				horaS=horaS.substring(5);
				if(horaS.contains(hora)) {
					sesionEle=sesion;
				}
			}
			
			Compra vcompra= new Compra(controlador, user,peli,sesionEle);
			vcompra.setVisible(true);
			this.dispose();
		}
	}
}
