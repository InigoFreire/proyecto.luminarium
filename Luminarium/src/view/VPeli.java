package view;

import java.awt.EventQueue;

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

public class VPeli extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private Controller cont;
	private Usuario us;
	private JButton btnModificar;
	private JMenu mnNewMenu;
	private JButton btnExit;
	private JScrollPane scrollPane;
	private JTable tablaPeliculas;
	

	public VPeli(Usuario user, Controller c, String[][] peliculas) {
		this.cont=c;
		this.us=user;
		String [] columna = {"TITULO","PEGI"};
		TablaPelis model = new TablaPelis(peliculas,columna);
		
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
		
		mnNewMenu = new JMenu("Usuario");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnNewMenu.setBounds(935, 7, 124, 39);
		contentPane.add(mnNewMenu);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		mnNewMenu.add(btnModificar);
		
		btnExit = new JButton("Cerrar sesión");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mnNewMenu.add(btnExit);
		
		btnModificar.addActionListener(this);
		tablaPeliculas.addMouseListener(new MouseAdapter() {
			Pelicula pelicula;
			@Override
			public void mouseClicked(MouseEvent e) {
				int linea = tablaPeliculas.rowAtPoint(e.getPoint());
				int columna = tablaPeliculas.columnAtPoint(e.getPoint());
				if(linea >= 0 && columna ==0) {
					String valor = (String) tablaPeliculas.getValueAt(linea, columna);
					pelicula=c.getPeliInfo(valor);
					InfoPeli infoPeli= new InfoPeli(us,cont,pelicula);
					infoPeli.setVisible(true);
					dispose();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnModificar) {
			EUsuario frame = new EUsuario(us, cont);
			frame.setVisible(true);
			this.dispose();
		}
		
		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
}
