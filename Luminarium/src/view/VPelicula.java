package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import model.Pelicula;
import model.Tabla;
import model.Usuario;

/**
 * La clase VPelicula es una ventana de interfaz gráfica de usuario (GUI) que muestra información sobre las películas en el sistema.
 * Extiende la clase JFrame e implementa la interfaz ActionListener para manejar eventos de acción.
 */
public class VPelicula extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPeliculas;
	private Controller controlador;
	private Usuario user;
	private JMenu mnUsuario;
	private JScrollPane scrollPane;
	private JTable tablaPeliculas;
	private JMenuItem mntmModificar, mntmExit;
	private JMenuBar menuBar;
	private String[][] peliculas;
	private JButton btnVolver, btnAniadir, btnEditar, btnBorrar;

	public VPelicula(Controller c, Usuario u) {
		this.controlador = c;
		this.user = u;
		peliculas = controlador.getPelis();
		String[] columna = { "TITULO", "PEGI" };
		Tabla model = new Tabla(peliculas, columna);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		tablaPeliculas = new JTable(model);

		lblPeliculas = new JLabel("Películas");
		lblPeliculas.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPeliculas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeliculas.setBounds(456, 7, 124, 39);
		contentPane.add(lblPeliculas);

		scrollPane = new JScrollPane(tablaPeliculas);
		scrollPane.setBounds(10, 54, 900, 160);
		contentPane.add(scrollPane);

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

		if (user.isAdminCheck()) {

			btnAniadir = new JButton("Aniadir");
			btnAniadir.setBounds(925, 90, 127, 31);
			contentPane.add(btnAniadir);
			btnAniadir.addActionListener(this);

			btnEditar = new JButton("Editar");
			btnEditar.setBounds(925, 140, 127, 31);
			contentPane.add(btnEditar);
			btnEditar.addActionListener(this);
			btnEditar.setEnabled(false);

			btnBorrar = new JButton("Borrar");
			btnBorrar.setBounds(925, 190, 127, 31);
			contentPane.add(btnBorrar);
			btnBorrar.addActionListener(this);
			btnBorrar.setEnabled(false);

			btnVolver = new JButton("Volver");
			btnVolver.setBounds(10, 221, 85, 21);
			contentPane.add(btnVolver);
			btnVolver.addActionListener(this);

			tablaPeliculas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			tablaPeliculas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					int nFilasSeleccionadas = tablaPeliculas.getSelectedRowCount();
					btnAniadir.setEnabled(nFilasSeleccionadas < 1);
					btnEditar.setEnabled(nFilasSeleccionadas == 1);
					btnBorrar.setEnabled(nFilasSeleccionadas > 0);

				}
			});

		} else {
			// si es usuario
			tablaPeliculas.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					int linea = tablaPeliculas.rowAtPoint(e.getPoint());
					int columna = tablaPeliculas.columnAtPoint(e.getPoint());
					// solo cuando se haga click en una celda de la primera columna de una fila
					// válida en la tabla de películas
					if (linea >= 0 && columna == 0) {
						String valor = (String) tablaPeliculas.getValueAt(linea, columna);
						Pelicula pelicula = controlador.getPeliInfo(valor);
						InfoPeli infoPeli = new InfoPeli(controlador, user, pelicula);
						infoPeli.setVisible(true);
						dispose();
					}
				}
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnVolver) {
			MenuAdmin frame = new MenuAdmin(controlador, user);
			frame.setVisible(true);
			dispose();
			
		} else if (e.getSource() == mntmModificar) {
			EUsuario frame = new EUsuario(controlador, user, user);
			frame.setVisible(true);
			dispose();

		} else if (e.getSource() == mntmExit) {
			LogIn frame = new LogIn(controlador);
			frame.setVisible(true);
			dispose();

		} else if (e.getSource() == btnAniadir) {
			APeli frame = new APeli(controlador, user);
			frame.setVisible(true);
			dispose();

		} else if (e.getSource() == btnEditar) {
			int filaSeleccionada = tablaPeliculas.getSelectedRow();
			if (filaSeleccionada != -1) {
				String titulo = (String) tablaPeliculas.getValueAt(filaSeleccionada, 0);
				Pelicula pelicula = controlador.getPeliPorTitulo(titulo);
				EPeli frame = new EPeli(controlador, user, pelicula);
				frame.setVisible(true);
				dispose();
			}

		} else if (e.getSource() == btnBorrar) {

			int confirmacion = JOptionPane.showOptionDialog(this, "¿Eliminar la selección?", "Borrado",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sí", "No" }, -1);

			if (confirmacion == JOptionPane.YES_OPTION) {
				ArrayList<Pelicula> peliculasABorrar = new ArrayList<>();
				int[] filasSeleccionadas = tablaPeliculas.getSelectedRows();

				for (int filaSeleccionada : filasSeleccionadas) {
					String titulo = (String) tablaPeliculas.getValueAt(filaSeleccionada, 0);
					Pelicula pelicula = controlador.getPeliPorTitulo(titulo);
					peliculasABorrar.add(pelicula);
				}
				BPeli frame = new BPeli(controlador, user, peliculasABorrar);
				frame.setVisible(true);
				dispose();
			}
		}
	}
}