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
import model.Pelicula;
import model.Sesion;
import model.Usuario;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Compra extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static Stack<VPelicula> stack = new Stack<>();
	private JPanel contentPane;
	private JMenuItem mntmExit;
	private JButton btnAtras, btnCompra, btnSumarEntrada, btnRestarEntrada;
	private JLabel lblTitulo, lblCantidad, lblCantidadEntradas;
    private JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	private Usuario user;
	private Controller controlador;
	private int numEntradas;
	private Pelicula peli;
	private Sesion sesion;
	
	public Compra(Controller c, Usuario u,Pelicula p,Sesion s) { 
		this.user=u;
		this.controlador=c;
		this.peli=p;
		this.sesion=s;
		numEntradas=0;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1360, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
                writer.write("controlador.getDatosCompra()");//TODO quitar comillas y dejar el método
                writer.close();
                System.out.println("Archivo guardado en: " + selectedFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El usuario canceló la operación.");
        }
		
		btnAtras = new JButton("Pag. anterior");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAtras.setBounds(10, 626, 177, 23);
		contentPane.add(btnAtras);
		
		lblTitulo= new JLabel("TituloPelicula");
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
		btnCompra.setBounds(585, 614, 177, 46);
		contentPane.add(btnCompra);
		
		btnSumarEntrada.addActionListener(this);
		btnRestarEntrada.addActionListener(this);
		btnCompra.addActionListener(this);
		
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!stack.isEmpty()) {
					VPelicula anterior = stack.pop();
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
		// Codigo para imprimir ticket en archivo
		// ¿Redirigir a otra página para confirmar datos de tarjeta?
		} else if (e.getSource() == mntmExit) {
			LogIn logIn = new LogIn(controlador);
			logIn.setVisible(true);
			this.dispose();
		}
	}
}