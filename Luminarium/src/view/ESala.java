package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Usuario;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ESala extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private Usuario user;
	private JButton btnVolver ;
	private JButton btnModificar; 
	private JTextField textSala;
	private JTextField textSalaAforo;
	private JLabel lblEditarSala;
	private JLabel lblSalaId;
	private JLabel lblSalaAforo;

	public ESala(Controller c, Usuario u) {
		this.controlador=c;
		this.user=u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(523, 592, 264, 56);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(btnVolver);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(795, 592, 264, 56);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentPane.add(btnModificar);
		
		lblEditarSala = new JLabel("Editar Sala");
		lblEditarSala.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEditarSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarSala.setBounds(247, 28, 540, 56);
		contentPane.add(lblEditarSala);
		
		lblSalaId = new JLabel("ID SALA");
		lblSalaId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSalaId.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalaId.setBounds(108, 128, 245, 56);
		contentPane.add(lblSalaId);
		
		textSala = new JTextField();
		textSala.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textSala.setBounds(442, 128, 345, 56);
		contentPane.add(textSala);
		textSala.setColumns(10);
		
		lblSalaAforo = new JLabel("AFORO");
		lblSalaAforo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalaAforo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSalaAforo.setBounds(108, 283, 245, 56);
		contentPane.add(lblSalaAforo);
		
		textSalaAforo = new JTextField();
		textSalaAforo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textSalaAforo.setColumns(10);
		textSalaAforo.setBounds(442, 283, 345, 56);
		contentPane.add(textSalaAforo);
		
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
