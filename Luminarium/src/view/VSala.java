package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import model.Sala;
import model.Tabla;
import model.Usuario;

public class VSala extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private Usuario user;
	private JButton btnVolver, btnAñadir, btnEditar, btnBorrar;
	private JLabel lblSalas;
	private String[][] salas;
	private JTable tablaSalas;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JMenu mnUsuario;
	private JMenuItem mntmModificar, mntmExit;

	public VSala(Controller c, Usuario u) {
		getContentPane().setLayout(null);
		this.controlador = c;
		this.user = u;
		salas = c.getSalas();
		String[] columna = { "ID", "Aforo" };
		Tabla model = new Tabla(salas, columna);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		tablaSalas = new JTable(model);

		lblSalas = new JLabel("Salas");
		lblSalas.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalas.setBounds(456, 7, 124, 39);
		contentPane.add(lblSalas);

		scrollPane = new JScrollPane(tablaSalas);
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

		tablaSalas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tablaSalas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int nFilasSeleccionadas = tablaSalas.getSelectedRowCount();
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
			EUsuario frame = new EUsuario(controlador, user, user);
			frame.setVisible(true);
			this.dispose();
		} else if (e.getSource() == mntmExit) {
			LogIn logIn = new LogIn(controlador);
			logIn.setVisible(true);
			dispose();
		} else if (e.getSource() == btnAñadir) {
			ASala frame = new ASala(controlador, user);
			frame.setVisible(true);
			dispose();

		} else if (e.getSource() == btnEditar) {
			int filaSeleccionada = tablaSalas.getSelectedRow();
			if (filaSeleccionada != -1) {
				String id = (String) tablaSalas.getValueAt(filaSeleccionada, 0);
				Sala sala = controlador.getSalaPorId(id);
				ESala frame = new ESala(controlador, user, sala);
				frame.setVisible(true);
				dispose();
			}

		} else if (e.getSource() == btnBorrar) {

			int confirmacion = JOptionPane.showOptionDialog(this, "¿Eliminar la selección?", "Borrado",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sí", "No" }, -1);

			if (confirmacion == JOptionPane.YES_OPTION) {
				ArrayList<Sala> salasABorrar = new ArrayList<>();

				int[] filasSeleccionadas = tablaSalas.getSelectedRows();

				for (int filaSeleccionada : filasSeleccionadas) {
					String id = (String) tablaSalas.getValueAt(filaSeleccionada, 0);
					Sala sala = controlador.getSalaPorId(id);
					salasABorrar.add(sala);
				}

				BSala frame = new BSala(controlador, user, salasABorrar);
				frame.setVisible(true);
				dispose();
			}
		}
	}
}
