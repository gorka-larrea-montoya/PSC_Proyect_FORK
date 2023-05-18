package es.deusto.spq.client.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.SwingConstants;
import java.awt.Color;

import es.deusto.spq.server.jdo.User;
import es.deusto.spq.client.*;
import java.awt.Cursor;
import java.awt.Dimension;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel_1, panel;
	private JLabel lblNewLabel, lblRegistro, lblNombre, lblContrasenya;
	private JTextField textFieldNombre, textFieldContrasenia;
	private JButton btnRegistrarse, btnNewButton;

	public VentanaRegistro() {

		setBounds(100, 100, 1060, 708);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 239, 213));
		panel.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[191.00][grow][][grow][grow][grow][grow][grow][grow][][grow][][][]",
				"[][][40.00][][56.00][53.00][][42.00,grow][][][][][][][][][][][][][][][]"));

		lblRegistro = new JLabel("Registrate");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setFont(new Font("Montserrat", Font.BOLD, 25));
		panel_1.add(lblRegistro, "cell 0 0 13 3,alignx center,aligny center");

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Montserrat", Font.PLAIN, 20));
		panel_1.add(lblNombre, "flowx,cell 0 5,alignx center,growy");

		textFieldNombre = new JTextField();
		panel_1.add(textFieldNombre, "cell 2 5 12 1,grow");
		textFieldNombre.setColumns(10);

		lblContrasenya = new JLabel("Contraseña:");
		lblContrasenya.setFont(new Font("Montserrat", Font.PLAIN, 20));
		panel_1.add(lblContrasenya, "flowx,cell 0 7,alignx center,growy");

		textFieldContrasenia = new JTextField();
		panel_1.add(textFieldContrasenia, "cell 2 7 12 1,grow");
		textFieldContrasenia.setColumns(10);

		btnRegistrarse = new JButton("Registrarme");
		btnRegistrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistrarse.setForeground(Color.BLACK);
		btnRegistrarse.setFont(new Font("Montserrat", Font.PLAIN, 18));

		panel_1.add(btnRegistrarse, "cell 0 15 14 3,grow");

		btnNewButton = new JButton("Salir");

		btnNewButton.setFont(new Font("Montserrat", Font.PLAIN, 18));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_1.add(btnNewButton, "cell 0 18 14 1,grow");

		lblNewLabel = new JLabel();
		
		
	
		// Cargar la imagen en un ImageIcon
		ImageIcon imagenIcono = new ImageIcon("src/main/java/es/deusto/spq/client/utils/user.png");

		// Obtener la imagen del ImageIcon
		Image imagenOriginal = imagenIcono.getImage();

		// Obtener el tamaño del JLabel
		int anchoLabel = (int)600;
		int altoLabel = (int) 600;

		// Crear una nueva imagen escalada que cubra todo el espacio del JLabel
		Image imagenEscalada = imagenOriginal.getScaledInstance(anchoLabel, altoLabel, Image.SCALE_SMOOTH);

		// Crear un nuevo ImageIcon a partir de la imagen escalada
		ImageIcon imagenEscaladaIcono = new ImageIcon(imagenEscalada);

		// Establecer el ImageIcon en el JLabel
		lblNewLabel.setIcon(imagenEscaladaIcono);
		panel.add(lblNewLabel);

		// ACTION LISTENER / FUNCIONALIDAD

		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldNombre.getText().equals("") && textFieldContrasenia.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos", "ERROR!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					ClientController.getInstance().registrarUsuario(textFieldNombre.getText(),textFieldContrasenia.getText());
					dispose();
					new VentanaLoginN();
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			new VentanaLoginN();
			}
		});

	}
}
