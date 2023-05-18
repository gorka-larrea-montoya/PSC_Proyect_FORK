package es.deusto.spq.client.gui;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.deusto.spq.client.ClientController;
import es.deusto.spq.pojo.LibroDTO;

public class AnadirLibroOptionPane {
	JTextField titleField;
	JTextField descField;
	JTextField precioField;
	JRadioButton rbAlquiler;
	JRadioButton rbCompra; 
	ButtonGroup bg;
	GridLayout gl;
	JPanel myPanel;
	JLabel ErrLabel;
protected static final Logger logger = LogManager.getLogger();
   public AnadirLibroOptionPane() {
	
	   
      titleField = new JTextField();
      descField = new JTextField();
      precioField = new JTextField();      
      rbAlquiler = new JRadioButton("Alquiler");
      rbCompra  = new JRadioButton("compra");
      ErrLabel = new JLabel(); 
      ErrLabel.setVisible(false);
      bg = new ButtonGroup();
      rbAlquiler.setSelected(true);
      bg.add(rbCompra);
      bg.add(rbAlquiler);
      
      gl = new GridLayout(5,2,10,10);
      myPanel = new JPanel(gl);
      myPanel.add(new JLabel("Título del libro:"));
      myPanel.add(titleField);
      myPanel.add(new JLabel("Descripción del libro:"));
      myPanel.add(descField);
      myPanel.add(new JLabel("Precio del libro:"));
      myPanel.add(precioField);
      myPanel.add(rbAlquiler);
      myPanel.add(rbCompra);
      myPanel.add(ErrLabel);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
    		  "Introduzca los detalles del libro", JOptionPane.OK_CANCEL_OPTION);
      
      if (result == JOptionPane.OK_OPTION) {
    	  if (validarLibro()) {
    		  logger.info("OPTIONPANE - Introduciendo Libro: " + parsearLibro().toString());
    		  if (ClientController.getInstance().adminSaveBook(parsearLibro())) {
    			  JOptionPane.showMessageDialog(null, "Libro anadido correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error en el servidor. Lea el log de la aplicacion para mas informacion","Error",JOptionPane.WARNING_MESSAGE);
			}

    	  } else {
    		  JOptionPane.showMessageDialog(null, "Error al introducir los datos del libro", "Advertencia",
    				  JOptionPane.WARNING_MESSAGE);
    		  new AnadirLibroOptionPane();
    	  }  
      } else if (result == JOptionPane.CANCEL_OPTION) {
    	  logger.info("Salido de la ventana de añadir libro sin añadir nada");
      }
   }
   private boolean validarLibro() {
	   return  ClientController.getInstance().validarLibro(titleField.getText(), descField.getText(), precioField.getText(), null);
   }

   private LibroDTO parsearLibro(){
	   LibroDTO l = new LibroDTO();
	   l.setNombre(titleField.getText());
	   l.setPrecio(Float.parseFloat(precioField.getText()));
	   l.setDescripccion(descField.getText());
	   if (rbAlquiler.isSelected()) {
		l.setTipo("Alquiler");
	   }else if (rbCompra.isSelected()) {
		l.setTipo("Compra");   
	   }
	   return l;
   }
}
