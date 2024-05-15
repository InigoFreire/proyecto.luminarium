package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import excepciones.IllegalEntryData;
import model.Sala;
import model.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

/**
 * Esta clase representa la interfaz gráfica para modificar una sala existente en el sistema.
 * Extiende la clase JFrame e implementa ActionListener para manejar eventos de botones y campos de texto.
 */
public class ESala extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controlador;
	private Usuario user;
	private JButton btnVolver ;
	private JButton btnModificar; 
	private JTextField textSalaId;
	private JTextField textSalaAforo;
	private JLabel lblEditarSala;
	private JLabel lblSalaId;
	private JLabel lblSalaAforo;
	private Sala sala;
	private JLabel lblIDsalaError; 
	private JLabel lblAforoError;


	public ESala(Controller c, Usuario u, Sala s) {
		this.controlador=c;
		this.user=u;
		this.sala=s;
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
		
		textSalaId = new JTextField();
		textSalaId.setEditable(false);
		textSalaId.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textSalaId.setBounds(442, 128, 345, 56);
		contentPane.add(textSalaId);
		textSalaId.setColumns(10);
		
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
		
		textSalaId.setText(sala.getId());
		textSalaAforo.setText(Integer.toString(sala.getAforo()));
		
		lblIDsalaError = new JLabel("");
		lblIDsalaError.setForeground(new Color(255, 0, 0));
		lblIDsalaError.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblIDsalaError.setBounds(795, 128, 262, 56);
		contentPane.add(lblIDsalaError);
		
		lblAforoError = new JLabel("");
		lblAforoError.setForeground(Color.RED);
		lblAforoError.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblAforoError.setBounds(797, 283, 262, 56);
		contentPane.add(lblAforoError);
		
		btnModificar.addActionListener(this);
		btnVolver.addActionListener(this);	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o=e.getSource();	
		
		if (o==btnVolver) {

			VSala menuA = new VSala(controlador, user); 
			menuA.setVisible(true);
			dispose();
		}
		if(o==btnModificar) {
			try {
				verificarDatos();
			} catch (IllegalEntryData error) {
				 System.out.println("ERROR: "+error.getMessage());
			}
		}
	}
		
	/**
	 * Verifica los datos ingresados por el usuario en un formulario de modificación de sala.
	 * Realiza una validación para asegurarse de que la longitud del número de aforo no excede las 3 cifras.
	 * Si la validación es exitosa, se llama al método modificarSala() del controlador para actualizar la información de la sala en la base de datos.
	 * 
	 * @throws IllegalEntryData Si los datos ingresados por el usuario son incorrectos.
	 */
	public void verificarDatos() throws IllegalEntryData {
		lblAforoError.setText("");
		
		boolean correcto=true;
		//Controlar que no mete mas de 3 cifras
		if(textSalaAforo.getText().length()>3) {
			lblAforoError.setText("No se admiten mas de 3 cifras");
			correcto=false;
		}
		if(correcto) {
			if (!textSalaAforo.equals("")) {
				try {
					controlador.modificarSala(sala, textSalaId.getText(),Integer.parseInt(textSalaAforo.getText()),sala.getId());
					JOptionPane.showMessageDialog(this,(String)"Sala modificada correctamente","",JOptionPane.INFORMATION_MESSAGE,null);
				} catch (NumberFormatException error) {
					lblAforoError.setText("Introduce numeros");
				}
			} else if (textSalaAforo.equals("")) {
				lblAforoError.setText("Introduce aforo");
			}
				
		}
	}
}
