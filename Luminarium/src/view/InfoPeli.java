package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Genero;
import model.Pelicula;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class InfoPeli extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static Stack<VPeli> stack = new Stack<>();
	private JPanel contentPane;
	private JLabel lblFoto, lblTitulo, lblGenero, lblPegi, lblDuracion, lblSinopsis;
	private JButton btnNewButton;
	private JMenuBar menuBar;
	private JMenu mnUsuario;
	private JMenuItem mntmModificar, mntmExit;
	private Usuario user;
	private Controller controlador;
	private Pelicula peli;

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
		lblTitulo.setBounds(399, 67, 520, 37);
		contentPane.add(lblTitulo);
		lblTitulo.setText(p.getTitulo());

		lblGenero = new JLabel("");
		lblGenero.setBounds(612, 114, 88, 23);
		contentPane.add(lblGenero);
		lblGenero.setText(p.getGenero().name());

		lblPegi = new JLabel("");
		lblPegi.setBounds(785, 114, 88, 23);
		contentPane.add(lblPegi);
		lblPegi.setText("+" + p.getPegi());

		lblDuracion = new JLabel("");
		lblDuracion.setBounds(399, 221, 230, 37);
		contentPane.add(lblDuracion);
		lblDuracion.setText(p.getDuracion() + " min");

		lblSinopsis = new JLabel("");
		lblSinopsis.setBounds(399, 294, 560, 154);
		contentPane.add(lblSinopsis);
		lblSinopsis.setText(p.getSinopsis());

		btnNewButton = new JButton("Comprar entradas");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBounds(823, 614, 236, 37);

		contentPane.add(btnNewButton);

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

		JButton btnAtras = new JButton("Pag. anterior");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAtras.setBounds(10, 626, 177, 23);
		contentPane.add(btnAtras);

		btnNewButton.addActionListener(this);

		mntmModificar.addActionListener(this);
		mntmExit.addActionListener(this);
		mnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mnUsuario.doClick();
			}
		});

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!stack.isEmpty()) {
					VPeli anterior = stack.pop();
					anterior.setVisible(true);
					dispose();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmModificar) {

			EUsuario frame = new EUsuario(controlador, user);

			frame.setVisible(true);
			this.dispose();
		} else if (e.getSource() == mntmExit) {
			LogIn logIn = new LogIn(controlador);
			logIn.setVisible(true);
			dispose();
		}
	}
}
