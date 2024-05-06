package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import model.Sesion;
import model.Tabla;
import model.Usuario;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;

public class VSesion extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private Usuario user;
	private JLabel lblSesiones;
	private JButton btnVolver, btnAñadir, btnEditar, btnBorrar;
	private String[][] sesiones;
	private JTable tablaSesiones;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JMenu mnUsuario;
	private JMenuItem mntmModificar, mntmExit;

	public VSesion(Controller c, Usuario u) {
		this.controlador = c;
		this.user = u;
		sesiones = c.getSesiones();
		String[] columna = { "ID", "precio", "fecha", "hora", "pelicula", "ID sala", "Entradas disponibles" };
		Tabla model = new Tabla(sesiones, columna);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		tablaSesiones = new JTable(model);

		lblSesiones = new JLabel("Sesiones");
		lblSesiones.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSesiones.setHorizontalAlignment(SwingConstants.CENTER);
		lblSesiones.setBounds(456, 7, 124, 39);
		contentPane.add(lblSesiones);

		scrollPane = new JScrollPane(tablaSesiones);
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
		mntmModificar.addActionListener(this);

		mntmExit = new JMenuItem("Cerrar Sesión");
		mnUsuario.add(mntmExit);
		mntmExit.addActionListener(this);

		btnAñadir = new JButton("Añadir");
		btnAñadir.setBounds(925, 90, 127, 31);
		contentPane.add(btnAñadir);
		btnAñadir.addActionListener(this);

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

		tablaSesiones.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tablaSesiones.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int nFilasSeleccionadas = tablaSesiones.getSelectedRowCount();
				btnAñadir.setEnabled(nFilasSeleccionadas < 1);
				btnEditar.setEnabled(nFilasSeleccionadas == 1);
				btnBorrar.setEnabled(nFilasSeleccionadas > 0);

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnVolver) {
			MenuAdmin menuA = new MenuAdmin(controlador, user);
			menuA.setVisible(true);
			dispose();
		} else if (e.getSource() == mntmModificar) {
			EUsuario frame = new EUsuario(controlador, user,user);
			frame.setVisible(true);
			this.dispose();
		} else if (e.getSource() == mntmExit) {
			LogIn logIn = new LogIn(controlador);
			logIn.setVisible(true);
			dispose();
		} else if (e.getSource() == btnAñadir) {
			ASesion frame = new ASesion(controlador, user);
			frame.setVisible(true);
			dispose();

		} else if (e.getSource() == btnEditar) {
			int filaSeleccionada = tablaSesiones.getSelectedRow();
			if (filaSeleccionada != -1) {
				String id = (String) tablaSesiones.getValueAt(filaSeleccionada, 0);
				Sesion sesion = controlador.getSesionPorId(id);
				ESesion frame = new ESesion(controlador, user, sesion);
				frame.setVisible(true);
				dispose();
			}

		} else if (e.getSource() == btnBorrar) {

			int confirmacion = JOptionPane.showOptionDialog(this, "¿Eliminar la selección?", "Borrado",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sí", "No" }, -1);

			if (confirmacion == JOptionPane.YES_OPTION) {
				ArrayList<Sesion> sesionesABorrar = new ArrayList<>();
				int[] filasSeleccionadas = tablaSesiones.getSelectedRows();

				for (int filaSeleccionada : filasSeleccionadas) {

					String id = (String) tablaSesiones.getValueAt(filaSeleccionada, 0);
					Sesion sesion = controlador.getSesionPorId(id);
					sesionesABorrar.add(sesion);
				}
				BSesion frame = new BSesion(controlador, user, sesionesABorrar);
				frame.setVisible(true);
				dispose();
			}
		}
	}
}