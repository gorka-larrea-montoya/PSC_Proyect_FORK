package es.deusto.spq.client.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import es.deusto.spq.client.ExampleClient;
import es.deusto.spq.pojo.LibroDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookSelectionDialog extends JDialog {
    private JTable table;
    private JButton selectButton;
	List<LibroDTO> books;
    private DefaultTableModel tableModel;

    private String selectedBook;

    public BookSelectionDialog(Frame parent) {
        super(parent, "Seleccionar libro", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(650, 490);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Seleccion de Libros");

        tableModel = new DefaultTableModel(new Object[] {"Nombre", "Descripcion", "Precio"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

      
        selectButton = new JButton("Aceptar");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    selectedBook = (String) table.getValueAt(selectedRow, 0);
                }
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        cargarDatos();
    }

    public String getSelectedBook() {
        return selectedBook;
    }
    
    private void cargarDatos() {
        books = ExampleClient.getInstance().getAllBooks();
        if (books != null) {
            for (LibroDTO libro : books) {
                String[] fila = { libro.getNombre(), libro.getDescripccion(), String.valueOf(libro.getPrecio()) };
                tableModel.addRow(fila);
                System.out.println(libro.toString());
            }
        } else {
            System.out.println("VACIO");
        }
    }
}
