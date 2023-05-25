package es.deusto.spq.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.deusto.spq.client.ClientController;
import es.deusto.spq.client.ExampleClient;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.*;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class VentanaLoginN extends JFrame {
	private JPanel contentPane, pnlPrincipal, pnlIzquierda, pnlDerechaa;
	private JLabel lblIniciarSesion, lblUsuario, lblContraseya;
	public JLabel lblErrorContrasenya;
	public JTextField txtUsuario;
	public JPasswordField passContraseya;
	public JButton btnIniciarSession;
	private JButton btnRegistrarse;
	private JButton btnSalir;
	private JLabel lblRegistrate;
	private JLabel lblNewLabel_3;
	private JLabel lblStravaCopyright;
	protected static final Logger logger = LogManager.getLogger();

	public VentanaLoginN() {

		setBounds(100, 100, 1160, 761);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setTitle("LogIn");
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		pnlPrincipal = new JPanel();
		contentPane.add(pnlPrincipal, BorderLayout.CENTER);
		pnlPrincipal.setLayout(new GridLayout(0, 2, 0, 0));

		pnlIzquierda = new JPanel();
		pnlIzquierda.setBackground(new Color(221, 84, 114));
		pnlPrincipal.add(pnlIzquierda);
		pnlIzquierda.setLayout(new MigLayout("", "[grow]",
				"[59.00][][46.00][36.00,top][][][3.00][41.00][][fill][46.00][47.00,grow][][][][][][47.00][][][][][][]"));

		lblIniciarSesion = new JLabel("Bienvenid@");
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setBorder(null);
		lblIniciarSesion.setFont(new Font("Monaco", Font.BOLD, 30));
		pnlIzquierda.add(lblIniciarSesion, "cell 0 0,alignx center,aligny center");

		lblErrorContrasenya = new JLabel("ERROR AL INTRODUCIR DATOS");
		lblErrorContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorContrasenya.setBorder(null);
		lblErrorContrasenya.setFont(new Font("Monaco", Font.BOLD, 30));
		lblErrorContrasenya.setVisible(false);
		pnlIzquierda.add(lblErrorContrasenya, "cell 0 1,alignx center,aligny center");

		pnlDerechaa = new JPanel();
		pnlDerechaa.setBorder(new MatteBorder(0, 3, 0, 0, (Color) new Color(0, 0, 0)));
		pnlDerechaa.setBackground(new Color(243, 254, 247));
		pnlPrincipal.add(pnlDerechaa);
		pnlDerechaa.setLayout(new BorderLayout(0, 0));

		lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setIcon(new ImageIcon("src/main/java/es/deusto/spq/client/utils/LudoFun.png"));
		pnlDerechaa.add(lblNewLabel_3, BorderLayout.CENTER);

		lblStravaCopyright = new JLabel("PSC 2023 CopyRight © Todos los derechos reservados.");
		lblStravaCopyright.setBackground(Color.WHITE);
		lblStravaCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDerechaa.add(lblStravaCopyright, BorderLayout.SOUTH);



		ImageIcon imgIcon = new ImageIcon("img/xApagada.png");
		Image imgEscalada = imgIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

		ImageIcon imgIconEnc = new ImageIcon("img/xEncendida.png");
		Image imgEscaladaEnc = imgIconEnc.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

		btnSalir = new JButton();
		btnSalir.setOpaque(false);
		btnIniciarSession = new JButton("Iniciar Sesion");

		lblUsuario = new JLabel("Nombre:");
		lblUsuario.setFont(new Font("Montserrat", Font.PLAIN, 20));
		pnlIzquierda.add(lblUsuario, "cell 0 5,alignx left,aligny center");

		txtUsuario = new JTextField();
		pnlIzquierda.add(txtUsuario, "cell 0 7,grow");
		txtUsuario.setColumns(10);

		lblContraseya = new JLabel("Contraseña:");
		lblContraseya.setFont(new Font("Montserrat", Font.PLAIN, 20));
		pnlIzquierda.add(lblContraseya, "cell 0 10,alignx left,aligny center");

		passContraseya = new JPasswordField();
		pnlIzquierda.add(passContraseya, "cell 0 11,grow");
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Montserrat", Font.PLAIN, 16));
		btnRegistrarse.setPreferredSize(new Dimension(120, 50));
		pnlIzquierda.add(btnRegistrarse, "flowx,cell 0 15,alignx center,aligny center");
		btnIniciarSession.setFont(new Font("Montserrat", Font.PLAIN, 16));
		btnIniciarSession.setPreferredSize(new Dimension(5000, 50));
		pnlIzquierda.add(btnIniciarSession, "cell 0 15,alignx center,aligny center");
		btnSalir.setText(" Salir");
		btnSalir.setRolloverIcon(new ImageIcon("img/xEncendida.png"));
		btnSalir.setFont(new Font("Montserrat", Font.PLAIN, 16));
		btnSalir.setPreferredSize(new Dimension(5000, 20));

		btnSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnIniciarSession.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				loginUser();
			}
		});

		btnRegistrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						VentanaRegistro registro = new VentanaRegistro();
						registro.setVisible(true);
						dispose();

					}
				});
			}
		});
		
		passContraseya.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		            // Activar ActionListener del botón de inicio de sesión
		        	btnIniciarSession.doClick();
		        }
		    }
		});
		
		txtUsuario.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		            // Activar ActionListener del botón de inicio de sesión
		        	btnIniciarSession.doClick();
		        }
		    }
		});

		pnlIzquierda.add(btnSalir, "cell 0 16 1 3,alignx center,growy");
		btnSalir.setIcon(new ImageIcon(imgEscalada));
		btnSalir.setRolloverIcon(new ImageIcon(imgEscaladaEnc));

		lblRegistrate = new JLabel("¡¡ Registrate !!");
		lblRegistrate.setFont(new Font("Montserrat", Font.PLAIN, 20));
		pnlIzquierda.add(lblRegistrate, "cell 0 19,alignx center,aligny center");

		btnSalir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btnSalir.setBackground(new Color(0, 0, 0));
			}
		});

		// HILOS
		Runnable r1 = new Runnable() {

			@Override
			public void run() {
			    while (!Thread.currentThread().isInterrupted()) {
			        try {
			            lblRegistrate.setForeground(new Color(243, 254, 247));
			            Thread.sleep(500);
			            lblRegistrate.setForeground(new Color(221,84,114,255));
			            Thread.sleep(500);
			        } catch (InterruptedException e) {
			            Thread.currentThread().interrupt(); // Restores interrupted state
			            break;
			        }
			    }
			}

		};
		Thread t1 = new Thread(r1);
		t1.start();

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				 t1.interrupt(); // interrupt the thread
			}

		});
		

	}
	private void loginUser() {
		boolean bool = ClientController.getInstance().loginUsuario(txtUsuario.getText(), passContraseya.getText());
		logger.info("Usuario:" + txtUsuario.getText() + " " + passContraseya.getText());

		if (bool) {
			//new VentanaPrincipal(txtUsuario.getText(), passContraseya.getText());
			new VentanaMenuN();
			dispose();
		} else {
			lblErrorContrasenya.setVisible(true);
		}
	}
}
