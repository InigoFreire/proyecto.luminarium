package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Usuario;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class VSesion extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private Usuario user;
	private JLabel lblSesiones;
	private JButton btnVolver;
	

	public VSesion(Controller c, Usuario u) {
		this.controlador=c;
		this.user=u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSesiones = new JLabel("Sesiones");
		lblSesiones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSesiones.setBounds(68, 30, 249, 33);
		contentPane.add(lblSesiones);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 221, 85, 21);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();	
		
		if (o==btnVolver) {
			MenuAdmin menuA = new MenuAdmin(controlador, user);
			menuA.setVisible(true);
			dispose();
		}
	}
}
