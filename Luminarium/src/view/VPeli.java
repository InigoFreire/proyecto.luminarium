package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Usuario;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class VPeli extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private Controller cont;
	private Usuario us;
	private JMenu mnUsuario;
	private JScrollPane scrollPane;
	private JMenuItem mntmModificar;
	private JMenuItem mntmExit;
	private JMenuBar menuBar;

	public VPeli(Usuario user, Controller c) {
		this.cont=c;
		this.us=user;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Películas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(456, 7, 124, 39);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 1049, 595);
		contentPane.add(scrollPane);
		
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
			if (e.getSource()==mntmModificar) {
				EUsuario frame = new EUsuario(us, cont);
				frame.setVisible(true);
				this.dispose();
			} else if (e.getSource()==mntmExit) {
				LogIn logIn = new LogIn(cont);
				logIn.setVisible(true);
				dispose();
			}
	}
}
