package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.MaskFormatter;

import controller.Controller;
import model.Pelicula;
import model.Sesion;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JCheckBox;

public class Compra extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnUsuario;
	private JMenuItem mntmModificar, mntmExit;
	private JButton btnAtras, btnCompra, btnSumarEntrada, btnRestarEntrada, btnCheckout, btnSalir;
	private JLabel lblTitulo, lblCantidad, lblCantidadEntradas, lblPrecioTotalEntradas, lblCheckout, lblTarjetaError,
			lblFechaCaducidad, lblCvc, lblFechaError, lblCVCError;
	private JFileChooser fileChooser ;
	private JInternalFrame checkoutFrame;
	private JFormattedTextField cardNumField, fechaCaducidadField, cvcField;
	private JCheckBox checkboxSaveCard;
	private int numEntradas;
	private Usuario user;
	private Controller controlador;
	private Pelicula pelicula;
	private Sesion sesion;

	public Compra(Controller controllerInput, Usuario usuarioInput, Pelicula peliInput, Sesion sesionInput) {
		this.user = usuarioInput;
		this.controlador = controllerInput;
		this.pelicula = peliInput;
		this.sesion = sesionInput;
		this.numEntradas = 0;
		this.fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		this.mntmModificar = new JMenuItem();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		if (user.equals(null)) {
			setVisible(false);
		}

		checkoutFrame = new JInternalFrame("Ventana de pago");
		checkoutFrame.setEnabled(false);
		checkoutFrame.setBounds(242, 99, 550, 450);
		contentPane.add(checkoutFrame);
		checkoutFrame.getContentPane().setLayout(null);

		lblCheckout = new JLabel("Introduzca un método de pago, por favor:");
		lblCheckout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCheckout.setBounds(80, 31, 300, 30);
		checkoutFrame.getContentPane().add(lblCheckout);

		cardNumField = new JFormattedTextField();

		cardNumField.setForeground(new Color(192, 192, 192));
		cardNumField.setText("0000 0000 0000 0000");
		cardNumField.setToolTipText("Introduzca método de pago");
		cardNumField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cardNumField.setBounds(80, 66, 350, 25);
		if(user.getMetodoPago()!=null) {
			cardNumField.setText(user.getMetodoPago());
		}
		

		cardNumField.setColumns(10);
		checkoutFrame.getContentPane().add(cardNumField);

		lblFechaCaducidad = new JLabel("Fecha caducidad");
		lblFechaCaducidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaCaducidad.setBounds(80, 125, 96, 25);
		checkoutFrame.getContentPane().add(lblFechaCaducidad);

		fechaCaducidadField = new JFormattedTextField();

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

		cvcField = new JFormattedTextField();

		cvcField.setHorizontalAlignment(SwingConstants.CENTER);
		cvcField.setForeground(new Color(192, 192, 192));
		cvcField.setText("000");
		cvcField.setToolTipText("El código de seguridad ubicado en el reverso de la tarjeta.");
		cvcField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cvcField.setBounds(334, 152, 96, 25);
		checkoutFrame.getContentPane().add(cvcField);

		btnCheckout = new JButton("Realizar pago");
		btnCheckout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCheckout.setBounds(302, 339, 175, 40);
		checkoutFrame.getContentPane().add(btnCheckout);

		checkboxSaveCard = new JCheckBox("Guardar este método de pago para futuras compras");
		checkboxSaveCard.setBounds(120, 385, 357, 21);
		checkoutFrame.getContentPane().add(checkboxSaveCard);

		lblTarjetaError = new JLabel("");
		lblTarjetaError.setEnabled(false);
		lblTarjetaError.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTarjetaError.setBounds(80, 203, 350, 30);
		checkoutFrame.getContentPane().add(lblTarjetaError);
		
		lblFechaError = new JLabel("");
		lblFechaError.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFechaError.setEnabled(false);
		lblFechaError.setBounds(80, 249, 350, 30);
		checkoutFrame.getContentPane().add(lblFechaError);
		
		lblCVCError = new JLabel("");
		lblCVCError.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCVCError.setEnabled(false);
		lblCVCError.setBounds(80, 289, 350, 30);
		checkoutFrame.getContentPane().add(lblCVCError);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalir.setBounds(80, 339, 175, 40);
		checkoutFrame.getContentPane().add(btnSalir);
		checkoutFrame.setVisible(false);

		btnAtras = new JButton("Volver");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAtras.setBounds(10, 617, 177, 34);
		contentPane.add(btnAtras);
		lblTitulo = new JLabel("");
		lblTitulo.setText(pelicula.getTitulo());
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitulo.setBounds(475, 120, 108, 46);
		contentPane.add(lblTitulo);

		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCantidad.setBounds(330, 301, 108, 46);
		contentPane.add(lblCantidad);

		lblCantidadEntradas = new JLabel(Integer.toString(numEntradas));
		lblCantidadEntradas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidadEntradas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCantidadEntradas.setBounds(504, 301, 61, 46);
		contentPane.add(lblCantidadEntradas);

		lblPrecioTotalEntradas = new JLabel("0 €");
		lblPrecioTotalEntradas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioTotalEntradas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrecioTotalEntradas.setBounds(504, 447, 61, 46);
		contentPane.add(lblPrecioTotalEntradas);

		btnSumarEntrada = new JButton("+");
		btnSumarEntrada.setBackground(new Color(255, 255, 255));
		btnSumarEntrada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSumarEntrada.setBounds(575, 301, 61, 46);
		contentPane.add(btnSumarEntrada);

		btnRestarEntrada = new JButton("-");
		btnRestarEntrada.setBackground(new Color(255, 255, 255));
		btnRestarEntrada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRestarEntrada.setBounds(433, 301, 61, 46);
		contentPane.add(btnRestarEntrada);

		btnCompra = new JButton("Comprar entradas");
		btnCompra.setBackground(new Color(255, 255, 255));
		btnCompra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCompra.setBounds(454, 605, 177, 46);
		contentPane.add(btnCompra);
		btnCompra.setEnabled(false);

		btnSumarEntrada.addActionListener(this);
		btnRestarEntrada.addActionListener(this);
		btnCompra.addActionListener(this);

		menuBar = new JMenuBar();
		menuBar.setBounds(882, 10, 177, 36);
		contentPane.add(menuBar);

		if (user.getNombre().isBlank()) {
			mnUsuario = new JMenu("Invitado");
		} else {
			mnUsuario = new JMenu(user.getNombre());
		}
		menuBar.add(mnUsuario);
		mnUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		if (!user.getNombre().isBlank()) {
			mntmModificar = new JMenuItem("Modificar");
			mnUsuario.add(mntmModificar);
			mntmModificar.addActionListener(this);
		}

		mntmExit = new JMenuItem("Cerrar Sesión");
		mnUsuario.add(mntmExit);
		mntmModificar.addActionListener(this);
		mntmExit.addActionListener(this);
		mnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mnUsuario.doClick();
			}
		});
		btnAtras.addActionListener(this);
		btnCheckout.addActionListener(this);
		btnSalir.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean correcto = true;
		if (e.getSource() == btnSalir) {
			checkoutFrame.dispose();
		}
		
		if (e.getSource() == btnAtras) {
			InfoPeli infopeli = new InfoPeli(controlador, user, pelicula);
			infopeli.setVisible(true);
			this.dispose();
		}

		int aux = Integer.parseInt(lblCantidadEntradas.getText());
		if (e.getSource() == btnSumarEntrada && aux < sesion.getTicketRestante()) {
			aux += 1; // Suma 1
			lblCantidadEntradas.setText(Integer.toString(aux));
			lblPrecioTotalEntradas.setText(String.valueOf(sesion.getPrecio() * aux) + " €");
			if(!lblCantidadEntradas.getText().equals("0")){
				btnCompra.setEnabled(true);
			}
		} else if (e.getSource() == btnRestarEntrada && aux > 0) {
			aux -= 1; // Resta 1
			lblCantidadEntradas.setText(Integer.toString(aux));
			lblPrecioTotalEntradas.setText(String.valueOf(sesion.getPrecio() * aux) + " €");
			if(lblCantidadEntradas.getText().equals("0")){
				btnCompra.setEnabled(false);
			}
		} else if (e.getSource() == btnCompra) {
			// Pop-up para confirmar datos de tarjeta
			checkoutFrame.setVisible(true);
			checkoutFrame.setEnabled(true);
			
			
			// Comprobar formato de tarjeta
			try {
				MaskFormatter cardFormatter = new MaskFormatter("#### #### #### ####");
				cardFormatter.setValidCharacters("0123456789");
				cardFormatter.install(cardNumField);

			} catch (ParseException ex) {
				lblTarjetaError.setText("Solo números en el Nº de tarjeta.");
				lblTarjetaError.setForeground(Color.RED);
				return;
			}
			
			// Comprobar formato de fecha de caducidad
			try {
				MaskFormatter fechaCaducidadFormatter = new MaskFormatter("##/##");
				fechaCaducidadFormatter.setValidCharacters("0123456789");
				fechaCaducidadFormatter.install(fechaCaducidadField);
			} catch (ParseException ex) {
				lblFechaError.setText("Introduzca solo números en la fecha.");
				lblFechaError.setForeground(Color.RED);
				return;
			}
			
			// Comprobar formato de CVC
			try {
				MaskFormatter cvcFormatter = new MaskFormatter("###");
				cvcFormatter.setValidCharacters("0123456789");
				cvcFormatter.install(cvcField);
			} catch (ParseException ex) {
				lblCVCError.setText("Error en el CVC. Introduzca solo números.");
				lblCVCError.setForeground(Color.RED);
				return;
			}
			
			if (user.getMetodoPago() != null) {
				cardNumField.setForeground(new Color(0, 0, 0));
				cardNumField.setText(user.getMetodoPago());
				fechaCaducidadField.setForeground(new Color(0, 0, 0));
				String fecha;
				fecha = String.format("%02d/%02d", user.getFechaCaducidadTarjeta().getMonthValue(), user.getFechaCaducidadTarjeta().getYear() % 100);
				fechaCaducidadField.setText(fecha);
			}
			
			if (cardNumField.hasFocus()) {
				cardNumField.setText("");
				cardNumField.setForeground(new Color(0, 0, 0));
			} else if (fechaCaducidadField.hasFocus()) {
				fechaCaducidadField.setText("");
				fechaCaducidadField.setForeground(new Color(0, 0, 0));
			} else if (cvcField.hasFocus()) {
				cvcField.setForeground(new Color(0, 0, 0));
				cvcField.setText("");
			}
			
		}
		
		if (e.getSource() == btnCheckout) {
			int month=0,year=0;
			
			lblTarjetaError.setText("");
			lblFechaError.setText("");
			lblCVCError.setText("");
			
			try {
				String fechaCaducidad = fechaCaducidadField.getText();
			String[] parts = fechaCaducidad.split("/");
			month = Integer.parseInt(parts[0]);
			year = Integer.parseInt(parts[1]);

			} catch (NumberFormatException error) {
				System.out.println(error);
				lblFechaError.setText("La fecha de la tarjeta a de tener 4 digitos");
			}
			
			// Check if month and year are valid
			if (month < 1 || month > 12 || year < 24 || year > 50) {
				lblFechaError.setText("Fecha de caducidad no valida");
				correcto = false;
			}
			
			//verificar que los campos no estan vacios
			if(cardNumField.getText().trim().isEmpty() && cvcField.getText().trim().isEmpty() && fechaCaducidadField.getText().trim().isEmpty()) {
				correcto=false;
				lblTarjetaError.setText("Todos los los campos son obligatorios");
			}
			
			//comprobar que el numero de tarjeta esta completo
			if(cardNumField.getText().trim().length()!=19) {
				
				lblTarjetaError.setText("El numero de la tarjeta a de contener 16 numeros");
				correcto=false;
			}
			
			if(cvcField.getText().trim().length()!=3) {
				
				lblCVCError.setText("El CVC de la tarjeta a de contener 3 numeros");
				correcto=false;
			}
			
			String regex = "^(0[1-9]|1[0-2])/(\\d{2})$";

			if (!fechaCaducidadField.getText().matches(regex)) {
			    lblFechaError.setText("La fecha de la tarjeta no es correcta");;
			}
			
			if (checkboxSaveCard.isSelected()) {
				controlador.guardarTarjeta(cardNumField.getText(), YearMonth.parse(fechaCaducidadField.getText(), DateTimeFormatter.ofPattern("MM/yy")), user);
			}
			
			// Configurar el filtro para archivos de texto
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (.txt)", "txt");
			fileChooser.setFileFilter(filter);
			if (correcto) {
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
						String numTarjeta = cardNumField.getText().substring(cardNumField.getText().length() - 4);
						FileWriter writer = new FileWriter(selectedFile);
						writer.write("Cliente: " + user.getNombre()+" "+user.getApellido() + "\nPelícula: " + pelicula.getTitulo()
								+ "\nEntradas compradas: " + lblCantidadEntradas.getText() + "\n Método de pago usado: "
								+ "****"+numTarjeta);

						writer.close();
						System.out.println("Archivo guardado en: " + selectedFile.getAbsolutePath());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					System.out.println("El usuario canceló la operación.");
				}

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(this, (String) "Compra realizada correctamente", "",
							JOptionPane.INFORMATION_MESSAGE, null);
					sesion = controlador.actualizarEntradas(sesion, aux);
					VPelicula vpeli = new VPelicula(controlador, user);
					vpeli.setVisible(true);
					this.dispose();
				}
			}
		}

		if (e.getSource() == mntmModificar) {
			EUsuario frame = new EUsuario(controlador, user, user);
			frame.setVisible(true);
			this.dispose();

		} else if (e.getSource() == mntmExit) {
			LogIn logIn = new LogIn(controlador); 
			logIn.setVisible(true);
			dispose();
		}
	}
}