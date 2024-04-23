package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Usuario;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ASesion extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private Usuario user;
	private JButton btnVolver, btnRegistrar;
	private JTextField textId;
	private JTextField textAforo;
	private JLabel lblId, lblAforo;
	private JLabel lblIdError;
	private JLabel lblAforoError;

	public ASesion(Controller c, Usuario u) {
		this.controlador=c;
		this.user=u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVolver.setBounds(10, 619, 134, 32);
		contentPane.add(btnVolver);
		
		textId = new JTextField();
		textId.setBounds(138, 246, 325, 54);
		contentPane.add(textId);
		textId.setColumns(10);
		
		textAforo = new JTextField();
		textAforo.setBounds(558, 246, 325, 54);
		contentPane.add(textAforo);
		textAforo.setColumns(10);
		
		lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblId.setBounds(138, 174, 325, 54);
		contentPane.add(lblId);
		
		lblAforo = new JLabel("Aforo");
		lblAforo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAforo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAforo.setBounds(558, 174, 325, 54);
		contentPane.add(lblAforo);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRegistrar.setBounds(440, 472, 166, 40);
		contentPane.add(btnRegistrar);
		
		lblIdError = new JLabel("New label");
		lblIdError.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdError.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblIdError.setForeground(new Color(255, 0, 0));
		lblIdError.setBounds(138, 332, 325, 40);
		contentPane.add(lblIdError);
		
		lblAforoError = new JLabel("New label");
		lblAforoError.setHorizontalAlignment(SwingConstants.CENTER);
		lblAforoError.setForeground(Color.RED);
		lblAforoError.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAforoError.setBounds(558, 332, 325, 40);
		contentPane.add(lblAforoError);
		
		btnVolver.addActionListener(this);
		btnRegistrar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnVolver) {
			MenuAdmin menuA = new MenuAdmin(controlador, user);
			menuA.setVisible(true);
			dispose();
		} else if (e.getSource()==btnRegistrar) {
			String patron = "^S\\d+";
	        Pattern pattern = Pattern.compile(patron);
	        Matcher matcher = pattern.matcher(textId.getText());
	        if (matcher.matches() && !textAforo.equals("")) {
	        	controlador.registrarSala(textId.getText(), Integer.parseInt(textAforo.getText()));
	        } else if (textAforo.equals("")) {
	        	lblAforoError.setText("Introduce aforo");
	        } else if (!matcher.matches()) {
	        	lblIdError.setText("Ejemplo formato: S4");
	        }
		}
	}
}
