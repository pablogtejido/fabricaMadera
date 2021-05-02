
package com.atenea.gui.tablas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class VisualizarProductos extends JFrame {
	private JTable table;
	private DefaultTableModel modelo;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarProductos window = new VisualizarProductos();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisualizarProductos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 827, 461);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel titulopanel = new JPanel();
		titulopanel.setLayout(null);
		titulopanel.setBackground(new Color(72, 61, 139));
		titulopanel.setBounds(0, 21, 811, 58);
		getContentPane().add(titulopanel);
		
		JLabel titulo = new JLabel("PRODUCTOS");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titulo.setBounds(0, 11, 811, 36);
		titulopanel.add(titulo);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 811, 21);
		getContentPane().add(menuBar);
		
		JMenu menuClientes = new JMenu("Clientes");
		menuBar.add(menuClientes);

		JMenu menuEmpleados = new JMenu("Empleado");
		menuBar.add(menuEmpleados);

		JMenuItem verClientes = new JMenuItem("Ver Clientes");
		menuClientes.add(verClientes);

		JMenuItem verEmpleados = new JMenuItem("Ver Empleados");
		menuEmpleados.add(verEmpleados);

		verClientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarClientes window2 = new VisualizarClientes();
				window2.setVisible(true);
				setVisible(false);
			}
		});

		verEmpleados.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarEmpleado window2 = new VisualizarEmpleado();
				window2.setVisible(true);
				setVisible(false);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 90, 751, 266);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		modelo = new DefaultTableModel();
		
		modelo.addColumn("Id");
		modelo.addColumn("Nombre");
		modelo.addColumn("Precio");
		modelo.addColumn("Peso");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Medida");
		modelo.addColumn("Grosor");
		modelo.addColumn("Añadidos");
		table.getTableHeader().setReorderingAllowed(false);
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnModificar.setBounds(360, 367, 89, 31);
		btnModificar.setBackground(new Color(72, 61, 139));
		getContentPane().add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnEliminar.setBounds(261, 367, 89, 31);
		btnEliminar.setBackground(new Color(72, 61, 139));
		getContentPane().add(btnEliminar);
		
		JButton btnAnyadir = new JButton("Añadir");
		btnAnyadir.setForeground(Color.WHITE);
		btnAnyadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		} );
		btnAnyadir.setBounds(459, 367, 89, 31);
		btnAnyadir.setBackground(new Color(72, 61, 139));
		getContentPane().add(btnAnyadir);
		
		
	}
}
