package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Usuario;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuAdmin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnPeli, btnSesion, btnSala, btnUsuario;
	private JLabel lblBienvenida, lblAvatar;
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

		//lblAvatar = new JLabel("");
		//lblAvatar.setBounds(372, 10, 54, 58);
		//contentPane.add(lblAvatar);

		// el avatar no se ve completo
		//lblAvatar.setIcon(new ImageIcon("./src/resources/avatar.png"));
		lblBienvenida = new JLabel("Bienvenida "+user.getNombre()+" "+user.getApellido());
		lblBienvenida.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblBienvenida.setHorizontalAlignment(SwingConstants.LEFT);
		lblBienvenida.setBounds(456, 7, 500, 39);
		contentPane.add(lblBienvenida);
		
		btnPeli = new JButton("Pelicula");
		contentPane.add(btnPeli);
		btnPeli.setBounds(98, 104, 85, 21);
		contentPane.add(btnPeli);
		btnPeli.addActionListener(this);

		btnSesion = new JButton("Sesion");
		btnSesion.setBounds(241, 104, 85, 21);
		contentPane.add(btnSesion);
		btnSesion.addActionListener(this);

		btnSala = new JButton("Sala");
		btnSala.setBounds(98, 173, 85, 21);
		contentPane.add(btnSala);
		btnSala.addActionListener(this);

		btnUsuario = new JButton("Usuario");
		btnUsuario.setBounds(241, 173, 85, 21);
		contentPane.add(btnUsuario);
		btnUsuario.addActionListener(this);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(882, 10, 177, 36);
		contentPane.add(menuBar);

		mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);
		mnUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		mntmModificar = new JMenuItem("Modificar");
		mnUsuario.add(mntmModificar);
		mntmModificar.addActionListener(this);
		
		mntmExit = new JMenuItem("Cerrar Sesión");
		mnUsuario.add(mntmExit);
		mntmExit.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == btnPeli || o == btnSesion || o == btnSala || o == btnUsuario) {
			String nombreVentana = "view.V" + ((JButton) o).getText();

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
			EUsuario frame = new EUsuario(controlador, user);
			frame.setVisible(true);
			this.dispose();
		} else if (o == mntmExit) {
			LogIn logIn = new LogIn(controlador);
			logIn.setVisible(true);
			dispose();
		}
		
	}
}
