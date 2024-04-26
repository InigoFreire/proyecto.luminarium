package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import controller.Controller;
import model.Usuario;
import model.Pelicula;
import model.Sesion;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class Compra extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static Stack<VPeli> stack = new Stack<>();
	private JPanel contentPane;
	private JMenuItem mntmExit;
	private JButton btnAtras, btnCompra, btnSumarEntrada, btnRestarEntrada;
	private JLabel lblTitulo, lblCantidad, lblCantidadEntradas;
    private JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	private Usuario user;
	private Controller controlador;
	private Pelicula pelicula;
	//private Sesion sesion;
	private int numEntradas;
	private JLabel lblCheckout;
	private JTextField cardNumField;
	private JTextField fechaCaducidadField;
	private JPasswordField cvcField;
	private JLabel lblCvc;
	
	public Compra(Controller controllerInput, Usuario usuarioInput, Pelicula peliInput){//, Sesion sesionInput) {
		this.user=usuarioInput;
		this.controlador=controllerInput;
		this.pelicula=peliInput;
		//this.sesion=sesionInput;
		numEntradas=0;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1360, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JInternalFrame checkoutFrame = new JInternalFrame("Ventana de pago");
		checkoutFrame.setBounds(430, 100, 550, 450);
		contentPane.add(checkoutFrame);
		checkoutFrame.getContentPane().setLayout(null);
		
		lblCheckout = new JLabel("Introduzca un método de pago, por favor:");
		lblCheckout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCheckout.setBounds(80, 31, 300, 30);
		checkoutFrame.getContentPane().add(lblCheckout);
		
		cardNumField = new JTextField();
		cardNumField.setToolTipText("Introduzca método de pago");
		cardNumField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cardNumField.setBounds(80, 66, 350, 25);
		checkoutFrame.getContentPane().add(cardNumField);
		cardNumField.setColumns(10);
		
		JLabel lblFechaCaducidad = new JLabel("Fecha caducidad");
		lblFechaCaducidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaCaducidad.setBounds(80, 125, 96, 25);
		checkoutFrame.getContentPane().add(lblFechaCaducidad);
		
		fechaCaducidadField = new JTextField();
		fechaCaducidadField.setHorizontalAlignment(SwingConstants.CENTER);
		fechaCaducidadField.setForeground(new Color(192, 192, 192));
		fechaCaducidadField.setText("01/24");
		fechaCaducidadField.setToolTipText("La fecha de caducidad de la tarjeta.");
		fechaCaducidadField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fechaCaducidadField.setBounds(80, 152, 96, 25);
		checkoutFrame.getContentPane().add(fechaCaducidadField);
		fechaCaducidadField.setColumns(10);
		
		lblCvc = new JLabel("CVC");
		lblCvc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCvc.setBounds(334, 125, 96, 25);
		checkoutFrame.getContentPane().add(lblCvc);
		
		cvcField = new JPasswordField();
		cvcField.setToolTipText("El código de seguridad ubicado en el reverso de la tarjeta.");
		cvcField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cvcField.setEchoChar('*');
		cvcField.setBounds(334, 152, 96, 25);
		checkoutFrame.getContentPane().add(cvcField);
		
		JButton btnCheckout = new JButton("Realizar pago");
		btnCheckout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCheckout.setBounds(180, 340, 175, 40);
		checkoutFrame.getContentPane().add(btnCheckout);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Guardar este método de pago para futuras compras");
		chckbxNewCheckBox.setBounds(180, 385, 265, 21);
		checkoutFrame.getContentPane().add(chckbxNewCheckBox);
		checkoutFrame.setVisible(true);
		
		btnAtras = new JButton("Pag. anterior");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAtras.setBounds(10, 626, 177, 23);
		contentPane.add(btnAtras);
		
		lblTitulo= new JLabel("");
		lblTitulo.setText(pelicula.getTitulo());
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitulo.setBounds(616, 153, 108, 46);
		contentPane.add(lblTitulo);
		
		lblCantidad= new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCantidad.setBounds(471, 334, 108, 46);
		contentPane.add(lblCantidad);
		
		lblCantidadEntradas= new JLabel(Integer.toString(numEntradas));
		lblCantidadEntradas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidadEntradas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCantidadEntradas.setBounds(645, 334, 61, 46);
		contentPane.add(lblCantidadEntradas);
		
		btnSumarEntrada = new JButton("+");
		btnSumarEntrada.setBackground(new Color(113, 184, 255));
		btnSumarEntrada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSumarEntrada.setBounds(716, 334, 61, 46);
		contentPane.add(btnSumarEntrada);
		
		btnRestarEntrada = new JButton("-");
		btnRestarEntrada.setBackground(new Color(113, 184, 255));
		btnRestarEntrada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRestarEntrada.setBounds(574, 334, 61, 46);
		contentPane.add(btnRestarEntrada);
		
		btnCompra = new JButton("Comprar entradas");
		btnCompra.setBackground(new Color(255, 255, 255));
		btnCompra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCompra.setBounds(600, 614, 177, 46);
		contentPane.add(btnCompra);
		
		btnSumarEntrada.addActionListener(this);
		btnRestarEntrada.addActionListener(this);
		btnCompra.addActionListener(this);
		
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
		if (e.getSource() == btnSumarEntrada) {
			int aux=Integer.parseInt(lblCantidadEntradas.getText())+1;
			lblCantidadEntradas.setText(Integer.toString(aux));
		} else if (e.getSource() == btnRestarEntrada){
			if (!lblCantidadEntradas.getText().equalsIgnoreCase("0")) {
				int aux=Integer.parseInt(lblCantidadEntradas.getText())-1;
				lblCantidadEntradas.setText(Integer.toString(aux));
			}
		} else if (e.getSource() == btnCompra) {
			// Pop-up para confirmar datos de tarjeta?
			
			
			
			// Actualizar entradas disponibles
			
			// Configurar el filtro para archivos de texto
	        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (.txt)", "txt");
	        fileChooser.setFileFilter(filter);
	        // Diálogo de guardar archivo
	        int returnValue = fileChooser.showSaveDialog(null);
	        // Selección del usuario
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fileChooser.getSelectedFile();
	            // Asegurarse de que la extensión sea .txt
	            String filePath = selectedFile.getAbsolutePath();
	            if (!filePath.endsWith(".txt")) {
	                selectedFile = new File(filePath + ".txt");
	            }
	            // Escribir
	            try {
	                FileWriter writer = new FileWriter(selectedFile);
	                writer.write("DNI del cliente: "+user.getDni()+"\nPelícula: "+pelicula.getTitulo()+" (ID: "+pelicula.getId()+")"+"\nEntradas compradas: "+lblCantidadEntradas.getText());//+"\n Sesión (dd/MM/AA): "+sesion.getFecha());
	                writer.close();
	                System.out.println("Archivo guardado en: " + selectedFile.getAbsolutePath());
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        } else {
	            System.out.println("El usuario canceló la operación.");
	        }
		} else if (e.getSource() == mntmExit) {
			LogIn logIn = new LogIn(controlador);
			logIn.setVisible(true);
			this.dispose();
		}
	}
}