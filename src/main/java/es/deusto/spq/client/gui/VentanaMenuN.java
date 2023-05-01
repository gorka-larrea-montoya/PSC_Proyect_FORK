package es.deusto.spq.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.event.MouseMotionAdapter;

public class VentanaMenuN extends JFrame {

	private JPanel contentPane;
	private JTextField txInfo;

	public VentanaMenuN(String usuario, String contraseña) {
		
		setBounds(100, 100, 969, 416);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		// IMAGENES
		ImageIcon imagenIcono = new ImageIcon("src/main/java/es/deusto/spq/client/utils/rent.png");
		ImageIcon imagenIconoAmp = new ImageIcon("src/main/java/es/deusto/spq/client/utils/rent.png");
		ImageIcon imagenIcono2 = new ImageIcon("src/main/java/es/deusto/spq/client/utils/compra.png");
		ImageIcon imagenIcono3 = new ImageIcon("src/main/java/es/deusto/spq/client/utils/devolver.png");

		// Obtener la imagen del ImageIcon
		Image imagenOriginal = imagenIcono.getImage();
		Image imagenOriginalAmp = imagenIconoAmp.getImage();
		Image imagenOriginal2 = imagenIcono2.getImage();
		Image imagenOriginal3 = imagenIcono3.getImage();

		// Obtener el tamaño del JLabel
		int anchoLabel = (int) 100;
		int anchoLabelMax = (int) 150;
		int altoLabel = (int) 100;
		int altoLabelMax = (int) 150;

		// Crear una nueva imagen escalada que cubra todo el espacio del JLabel
		Image imagenEscalada = imagenOriginal.getScaledInstance(anchoLabel, altoLabel, Image.SCALE_SMOOTH);
		Image imagenEscaladaMax = imagenOriginalAmp.getScaledInstance(anchoLabelMax, altoLabelMax, Image.SCALE_SMOOTH);
		Image imagenEscalada2 = imagenOriginal2.getScaledInstance(anchoLabel, altoLabel, Image.SCALE_SMOOTH);
		Image imagenEscalada3 = imagenOriginal3.getScaledInstance(anchoLabel, altoLabel, Image.SCALE_SMOOTH);

		// Crear un nuevo ImageIcon a partir de la imagen escalada
		ImageIcon imagenEscaladaIcono = new ImageIcon(imagenEscalada);
		ImageIcon imagenEscaladaIconoMax = new ImageIcon(imagenEscaladaMax);
		ImageIcon imagenEscaladaIcono2 = new ImageIcon(imagenEscalada2);
		ImageIcon imagenEscaladaIcono3 = new ImageIcon(imagenEscalada3);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JPanel pnlPrincipal = new JPanel();
		contentPane.add(pnlPrincipal, BorderLayout.CENTER);
		pnlPrincipal.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("- Menu -");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(255, 165, 0));
		lblNewLabel.setFont(new Font("Montserrat", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pnlPrincipal.add(lblNewLabel, BorderLayout.NORTH);

		JPanel pnlSur = new JPanel();
		pnlSur.setBackground(new Color(224, 255, 255));
		pnlPrincipal.add(pnlSur, BorderLayout.CENTER);
		pnlSur.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][grow]", "[][][grow][34.00][29.00][][]"));

		JLabel lblIconAlquiler = new JLabel();

		lblIconAlquiler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JLabel lblIconCompra = new JLabel();
		lblIconCompra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JLabel lblIconDevolver = new JLabel();
		lblIconDevolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblIconAlquiler.setIcon(imagenEscaladaIcono);
		lblIconCompra.setIcon(imagenEscaladaIcono2);
		lblIconDevolver.setIcon(imagenEscaladaIcono3);
		pnlSur.add(lblIconAlquiler, "cell 3 2 1 3,alignx center,growy");
		pnlSur.add(lblIconCompra, "cell 9 2 1 3,alignx center,growy");
		pnlSur.add(lblIconDevolver, "cell 15 2 1 3,alignx center,growy");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(175, 238, 238));
		pnlSur.add(panel, "cell 18 0 1 7,grow");
		panel.setLayout(new BorderLayout(0, 0));
		
		txInfo = new JTextField();
		txInfo.setHorizontalAlignment(SwingConstants.CENTER);
		txInfo.setOpaque(true);
		txInfo.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txInfo.setEditable(false);
		txInfo.setFont(new Font("Montserrat", Font.PLAIN, 14));
		txInfo.setText("Bienvenid@ de nuevo, " + usuario);
		panel.add(txInfo, BorderLayout.NORTH);
		txInfo.setColumns(10);

		JLabel lblAlquiler = new JLabel("Alquiler");
		lblAlquiler.setFont(new Font("Montserrat", Font.PLAIN, 20));

		pnlSur.add(lblAlquiler, "cell 3 6,alignx center,growy");

		JLabel lblCompra = new JLabel("Compra");
		lblCompra.setFont(new Font("Montserrat", Font.PLAIN, 20));

		pnlSur.add(lblCompra, "cell 9 6,alignx center,growy");

		JLabel lblDevolver = new JLabel("Devolver");
		lblDevolver.setFont(new Font("Montserrat", Font.PLAIN, 20));

		pnlSur.add(lblDevolver, "cell 15 6,alignx center,growy");

		lblIconAlquiler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
               
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(usuario, contraseña, "alquiler");
				dispose();
			}
//			@Override
//			public void mouseExited(MouseEvent e) {
//				lblIconAlquiler.setIcon(imagenEscaladaIconoMax);
//			}
		});
//		lblIconAlquiler.addMouseMotionListener(new MouseMotionAdapter() {
//			@Override
//			public void mouseMoved(MouseEvent arg0) {
//				lblIconAlquiler.setIcon(imagenEscaladaIconoMax);
//			}
//		});

		lblIconCompra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(usuario, contraseña, "compra");
				dispose();

			}
		});

	}

}
