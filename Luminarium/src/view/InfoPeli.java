package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Genero;
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
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JComboBox;

public class InfoPeli extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFoto, lblTitulo, lblGenero, lblPegi, lblDuracion, lblSinopsis;
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
	
	public InfoPeli(Controller c, Usuario u, Pelicula p) {
		this.user = u;
		this.controlador = c;
		this.peli = p;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblFoto = new JLabel("");
		lblFoto.setBounds(59, 67, 258, 381);
		contentPane.add(lblFoto);

		lblTitulo = new JLabel("");
		lblTitulo.setBounds(391, 67, 520, 37);
		contentPane.add(lblTitulo);
		lblTitulo.setText(peli.getTitulo());

		lblGenero = new JLabel("");
		lblGenero.setBounds(612, 114, 88, 23);
		contentPane.add(lblGenero);
		lblGenero.setText(peli.getGenero().name());

		lblPegi = new JLabel("");
		lblPegi.setBounds(785, 114, 88, 23);
		contentPane.add(lblPegi);
		lblPegi.setText("+" + peli.getPegi());

		lblDuracion = new JLabel("");
		lblDuracion.setBounds(399, 221, 230, 37);
		contentPane.add(lblDuracion);
		lblDuracion.setText(peli.getDuracion() + " min");

		lblSinopsis = new JLabel("");
		lblSinopsis.setBounds(399, 294, 560, 154);
		contentPane.add(lblSinopsis);
		lblSinopsis.setText(peli.getSinopsis());

		btnComprar = new JButton("Comprar entradas");
		btnComprar.setBounds(823, 614, 236, 37);
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 25));

		contentPane.add(btnComprar);

		menuBar = new JMenuBar();
		menuBar.setBounds(882, 10, 177, 36);
		contentPane.add(menuBar);

		mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);
		mnUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		mntmModificar = new JMenuItem("Modificar");
		mnUsuario.add(mntmModificar);

		mntmExit = new JMenuItem("Cerrar Sesión");
		mnUsuario.add(mntmExit);

		btnAtras = new JButton("Pag. anterior");
		btnAtras.setBounds(10, 626, 177, 23);
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnAtras);
		
		horas =c.getHoraSesion(peli.getId());
		comboBoxSesion = new JComboBox<String>();
		if(horas.isEmpty()) {
			comboBoxSesion.addItem("No hay sesiones disponibles");
		}else {
			for(Sesion sesion:horas) {
				hora = sesion.getFecha().toString();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm:ss");
				hora=LocalDateTime.parse(hora).format(formatter);
				hora= hora.substring(0,hora.length()-3);
				hora=hora.substring(5);
				comboBoxSesion.addItem(hora);
			}
		}
		comboBoxSesion.setBounds(440, 507, 160, 30);
		contentPane.add(comboBoxSesion);

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

			EUsuario frame = new EUsuario(controlador, user,user);

			frame.setVisible(true);
			this.dispose();
		} else if (e.getSource() == mntmExit) {
			LogIn logIn = new LogIn(controlador);
			logIn.setVisible(true);
			dispose();
		}
		if(e.getSource()==btnAtras) {
			VPeli vpeli = new VPeli(controlador, user);
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
