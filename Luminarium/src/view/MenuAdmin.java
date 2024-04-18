package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import controller.Controller;
import model.Usuario;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MenuAdmin extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnPeli, btnSesion, btnSala, btnUsuario;
	private JLabel lblBienvenida, lblAvatar;
	private JFrame ventana;
	private Controller controlador;
	private Usuario user;
	
	
	public MenuAdmin(Controller c, Usuario u) {
		this.controlador=c;
		this.user=u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAvatar = new JLabel("");
		lblAvatar.setBounds(372, 10, 54, 58);
		contentPane.add(lblAvatar);

		//el avatar no se ve completo
        lblAvatar.setIcon(new ImageIcon("./src/resources/avatar.png"));
		
		btnPeli = new JButton("Pelicula");
		contentPane.add(btnPeli);
		btnPeli.setBounds(98, 104, 85, 21);
		contentPane.add(btnPeli);
		btnPeli.addActionListener(this);
		
		btnSesion = new JButton("Sesion");
		btnSesion.setBounds(241, 104, 85, 21);
		contentPane.add(btnSesion);
		btnSesion.addActionListener(this);
		
		btnSala = new JButton("Sala");
		btnSala.setBounds(98, 173, 85, 21);
		contentPane.add(btnSala);
		btnSala.addActionListener(this);
		
		btnUsuario = new JButton("Usuario");
		btnUsuario.setBounds(241, 173, 85, 21);
		contentPane.add(btnUsuario);
		btnUsuario.addActionListener(this);
		
		lblBienvenida = new JLabel("Bienvenida");//+Nombre
		lblBienvenida.setBounds(98, 33, 238, 13);
		contentPane.add(lblBienvenida);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();		
		if(o==btnPeli || o==btnSesion || o==btnSala || o==btnUsuario) {
			String nombreVentana;
			String respuesta=getOpcionDialog(o);
			
			if (respuesta!=null) {
				if (o==btnPeli) {
					nombreVentana= "view."+respuesta.substring(0,1).concat("Peli");
				}
				else {
					nombreVentana= "view."+respuesta.substring(0,1).concat(((JButton)o).getText());
				}
				
	            Class<?> ClaseVentana;
				try {
					ClaseVentana = Class.forName(nombreVentana);
					ventana = (JFrame) ClaseVentana.getDeclaredConstructor().newInstance(controlador, user);
					ventana.setVisible(true);
					
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            dispose();
			}			
		}	
	}
	public String getOpcionDialog(Object o) {
		String[] opciones = {"Ver", "Añadir", "Editar", "Borrar"};
		int indiceRespuesta = JOptionPane.showOptionDialog(this, "¿Qué accion quieres realizar?",
				((JButton)o).getText(), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);
		//controla la respuesta -1, cuando se cierra el JOptionPane
		if (indiceRespuesta==-1) {
			return null;
		}
		return opciones[indiceRespuesta];
	}
}
