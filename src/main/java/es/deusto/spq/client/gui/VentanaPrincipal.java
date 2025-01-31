package es.deusto.spq.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.deusto.spq.client.ClientController;
import es.deusto.spq.client.ExampleClient;
import es.deusto.spq.pojo.LibroDTO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	protected static final Logger logger = LogManager.getLogger();
	
	String usuario, contraseña;
	JPanel contentPane, panel, panel_1;
	JLabel lblLogo;
	JLabel lblTablaLibros = new JLabel("TABLA LIBROS");
	//TODO rehacer toda esta tabla para que funcione
	DefaultTableModel modelo = new DefaultTableModel(new Object[] {/*"Id",*/ "Nombre", "Descripcion", "Precio" }, 0);
	JTable tabla = new JTable(modelo);
	JButton btnNewButton;
	private JPanel panel_2;
	private JButton btnProcesar;
	private JButton btnSalir;
	private JButton btnReturn;
	List<LibroDTO> books;


	public VentanaPrincipal(String tipo) {

		setLocationRelativeTo(null);
		setResizable(true);
		setTitle("LudoFun");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1160, 761);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		lblLogo = new JLabel();
		lblLogo.setOpaque(true);
		lblLogo.setBackground(new Color(236, 250, 244));
		

		// Cargar la imagen en un ImageIcon
		ImageIcon imagenIcono = new ImageIcon("src/main/java/es/deusto/spq/client/utils/LudoFun.png");

		// Obtener la imagen del ImageIcon
		Image imagenOriginal = imagenIcono.getImage();

		// Obtener el tamaño del JLabel
		int anchoLabel = (int) 600;
		int altoLabel = (int) 600;

		// Crear una nueva imagen escalada que cubra todo el espacio del JLabel
		Image imagenEscalada = imagenOriginal.getScaledInstance(anchoLabel, altoLabel, Image.SCALE_SMOOTH);

		// Crear un nuevo ImageIcon a partir de la imagen escalada
		ImageIcon imagenEscaladaIcono = new ImageIcon(imagenEscalada);

		// Establecer el ImageIcon en el JLabel
		lblLogo.setIcon(imagenEscaladaIcono);

		panel.add(lblLogo, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(187, 197, 193));
		panel.add(panel_2, BorderLayout.SOUTH);
		
		btnSalir = new JButton("Salir");
		btnReturn = new JButton("Volver");

		panel_2.add(btnSalir);
		panel_2.add(btnReturn);
		
		btnProcesar = new JButton();
		btnProcesar.setEnabled(true);


		
		panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		lblTablaLibros.setHorizontalAlignment(SwingConstants.CENTER);
		lblTablaLibros.setBackground(new Color(244, 164, 96));
		lblTablaLibros.setFont(new Font("Montserrat", Font.BOLD, 24));
		lblTablaLibros.setOpaque(true);
		panel_1.add(lblTablaLibros, BorderLayout.NORTH);
		panel_1.add(new JScrollPane(tabla), BorderLayout.CENTER);
	
		//CONTROL DE LOS BOTONES
		if(tipo=="alquiler") {
			cargarDatosAlquiler();
			btnProcesar.setText("Alquilar");
		}else if(tipo=="compra"){
			cargarDatosCompra();
			btnProcesar.setText("Comprar");
		}
		panel_2.add(btnProcesar);
		tabla.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			public void MouseClicked(MouseEvent e) {
				btnProcesar.setEnabled(true);
			}
		});
			
		//FUNCIONALIDAD DE ALQUILER o Compra
		
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				procesarLibros(tipo);
			}
		});
		
		btnReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaMenuN();
				dispose();
			}
		});
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		 

	}
	private void procesarLibros(String tipo) {
		
		int[] libros = tabla.getSelectedRows();
		ArrayList<LibroDTO> result = new ArrayList<LibroDTO>();
		for (int i = 0; i < libros.length; i++) {
			result.add(books.get(libros[i]));
			logger.debug(books.get(libros[i]).getNombre());
		}		
		if (tipo=="compra") {
			if (ClientController.getInstance().comprarLibros(result)) {
				JOptionPane.showMessageDialog(null, "Libro comprado correctamente", "Compra", JOptionPane.INFORMATION_MESSAGE);
				//Borra los libros del panel solo si se han alquilado correctamente
				cargarDatosCompra();
			}else {
				JOptionPane.showMessageDialog(null,"Error al comprar libro","Compra",JOptionPane.ERROR_MESSAGE);
			}	
		}else if (tipo=="alquiler") {
			//ventana emergente de "Se ha alquilado el libro". Así además se hace mas natural la actualización de la tabla.
			if (ClientController.getInstance().alquilarLibros(result)) {
				JOptionPane.showMessageDialog(null, "Libro alquilado correctamente", "Alquiler", JOptionPane.INFORMATION_MESSAGE);
				//Borra los libros del panel solo si se han alquilado correctamente
				cargarDatosAlquiler();
				
			}else {
				JOptionPane.showMessageDialog(null,"Error al alquilar libro","Alquiler",JOptionPane.ERROR_MESSAGE);
			}	
		}
	}

	private void cargarDatosAlquiler() {
		// Primero  descarga todas las lineas para que se pueda cargar 
		logger.debug("getrowcount is: " + modelo.getRowCount());
		for (int i = modelo.getRowCount()-1; i >= 0; i--) {
			logger.debug("Removing row: " + i);
			modelo.removeRow(i);
		}
		
		
		books = ExampleClient.getInstance().getBooksAlquiler();
		for (LibroDTO libro : books) {
			String[] fila = { libro.getNombre(), libro.getDescripccion(), String.valueOf(libro.getPrecio())};
			modelo.addRow(fila);
			logger.debug("anadiendo fila : " + libro.toString());
			
		}
		
	}

	private void cargarDatosCompra() {
		// Primero  descarga todas las lineas para que se pueda cargar 
		for (int i = modelo.getRowCount()-1; i >= 0; i--) {
			logger.debug("Removing row: " + i);
			modelo.removeRow(i);
		}
		
		books = ExampleClient.getInstance().getBooksCompra();
		for (LibroDTO libro : books) {
			String[] fila = {libro.getNombre(), libro.getDescripccion(), String.valueOf(libro.getPrecio()) };
			modelo.addRow(fila);
			logger.debug("anadiendo fila : " + libro.toString());
		}

	}

}