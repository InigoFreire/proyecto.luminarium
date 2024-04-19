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
import javax.swing.JTextField;

public class APeli extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private Usuario user;
	private JTextField textFieldTitulo;

	public APeli(Controller c, Usuario u) {
		this.controlador=c;
		this.user=u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNuevaPelicula = new JLabel("Nueva Película");
		lblNuevaPelicula.setBounds(91, 34, 153, 13);
		contentPane.add(lblNuevaPelicula);
		
		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setBounds(49, 57, 45, 13);
		contentPane.add(lblTitulo);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(43, 80, 96, 19);
		contentPane.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
