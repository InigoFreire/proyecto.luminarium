package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.Controller;
import model.Pelicula;
import model.TablaPelis;
import model.Usuario;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class VPeli extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private Controller controlador;
	private Usuario user;
	private JButton btnModificar;
	private JMenu mnNewMenu, mnUsuario;
	private JButton btnExit;
	private JScrollPane scrollPane;
	private JTable tablaPeliculas;
	private JMenuItem mntmModificar, mntmExit;
	private JMenuBar menuBar;
	private String[][] peliculas;

	public VPeli(Controller c, Usuario u) {
		this.controlador = c;
		this.user = u;
		peliculas = c.getPelis();
		String[] columna = { "TITULO", "PEGI" };
		TablaPelis model = new TablaPelis(peliculas, columna);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		tablaPeliculas = new JTable(model);

		lblNewLabel = new JLabel("Películas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(456, 7, 124, 39);
		contentPane.add(lblNewLabel);

		scrollPane = new JScrollPane(tablaPeliculas);
		scrollPane.setBounds(10, 54, 900, 100);

		contentPane.add(scrollPane);

		menuBar = new JMenuBar();
		menuBar.setBounds(882, 10, 177, 36);
		contentPane.add(menuBar);

		mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);
		mnUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		mntmModificar = new JMenuItem("Modificar");
		mnUsuario.add(mntmModificar);

		btnModificar=new JButton("Modificar");
		btnModificar.addActionListener(this);
		tablaPeliculas.addMouseListener(new MouseAdapter() {
			Pelicula pelicula;

			@Override
			public void mouseClicked(MouseEvent e) {
				int linea = tablaPeliculas.rowAtPoint(e.getPoint());
				int columna = tablaPeliculas.columnAtPoint(e.getPoint());
				if (linea >= 0 && columna == 0) {
					String valor = (String) tablaPeliculas.getValueAt(linea, columna);
					pelicula = c.getPeliInfo(valor);
					InfoPeli infoPeli = new InfoPeli(controlador, user, pelicula);
					infoPeli.setVisible(true);
					dispose();
				}
			}
		});

		mntmExit = new JMenuItem("Cerrar Sesión");
		mnUsuario.add(mntmExit);

		mntmModificar.addActionListener(this);
		mntmExit.addActionListener(this);
		mnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mnUsuario.doClick();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnModificar) {
			EUsuario frame = new EUsuario(controlador, user);
			frame.setVisible(true);
			this.dispose();
		}
	}

	/*private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
				if (e.getSource()==mntmModificar) {
					EUsuario frame = new EUsuario(controlador, user);
					frame.setVisible(true);
					this.dispose();
				} else if (e.getSource()==mntmExit) {
					LogIn logIn = new LogIn(controlador);
					logIn.setVisible(true);
					dispose();
				}
			}
		});
	}*/
}
