package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

import controller.Controller;

import model.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * La clase MenuAdmin representa una ventana de menú para un usuario administrador en una interfaz
 * gráfica (GUI), donde puede acceder a diferentes opciones relacionadas con la administración del sistema,
 * como gestionar películas, sesiones, salas y usuarios. Esta clase extiende JFrame e implementa ActionListener
 * para manejar las interacciones del usuario con los distintos componentes del menú.
 */
public class MenuAdmin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnPeli, btnSesion, btnSala, btnUsuario;
	private JFrame ventana;
	private Controller controlador;
	private Usuario user;
	private JMenuBar menuBar;
	private JMenu mnUsuario;
	private JMenuItem mntmModificar, mntmExit;

	public MenuAdmin(Controller c, Usuario u) {
		this.controlador = c;
		this.user = u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnPeli = new JButton("Peliculas");
		btnPeli.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(btnPeli);
		btnPeli.setBounds(171, 179, 262, 63);
		contentPane.add(btnPeli);
		btnPeli.addActionListener(this);

		btnSesion = new JButton("Sesiones");
		btnSesion.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSesion.setBounds(585, 179, 262, 63);
		contentPane.add(btnSesion);
		btnSesion.addActionListener(this);

		btnSala = new JButton("Salas");
		btnSala.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSala.setBounds(171, 434, 262, 63);
		contentPane.add(btnSala);
		btnSala.addActionListener(this);

		btnUsuario = new JButton("Usuarios");
		btnUsuario.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnUsuario.setBounds(585, 434, 262, 63);
		contentPane.add(btnUsuario);
		btnUsuario.addActionListener(this);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(882, 10, 177, 36);
		contentPane.add(menuBar);

		mnUsuario = new JMenu(user.getNombre());
		mnUsuario.setBounds(882, 10, 177, 36);
		menuBar.add(mnUsuario);
		mnUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		mntmModificar = new JMenuItem("Modificar");
		mnUsuario.add(mntmModificar);
		mntmModificar.addActionListener(this);
		
		mntmExit = new JMenuItem("Cerrar Sesión");
		mnUsuario.add(mntmExit);
		
		JLabel lblNewLabel = new JLabel("Bienvenid@ admin");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(365, 29, 262, 36);
		contentPane.add(lblNewLabel);
		mntmExit.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnPeli || o == btnSesion || o == btnSala || o == btnUsuario) {
			String botonEnSingular = null;
			
			if (((JButton) o).getText().endsWith("es")) {
				botonEnSingular = ((JButton) o).getText().substring(0, ((JButton) o).getText().length() - 2);
	        } else if (((JButton) o).getText().endsWith("s")) {
	        	botonEnSingular = ((JButton) o).getText().substring(0, ((JButton) o).getText().length() - 1);
	        }
 
			String nombreVentana = "view.V" + botonEnSingular;

			Class<?> ClaseVentana;
			try {
				ClaseVentana = Class.forName(nombreVentana);
				ventana = (JFrame) ClaseVentana.getDeclaredConstructor(Controller.class, Usuario.class)
						.newInstance(controlador, user);
				ventana.setVisible(true);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dispose();
		} else if (o == mntmModificar) {
			EUsuario frame = new EUsuario(controlador, user, user);
			frame.setVisible(true);
			this.dispose();
		} else if (o == mntmExit) {
			LogIn logIn = new LogIn(controlador);
			logIn.setVisible(true);
			dispose();
		}
		
	}
}
