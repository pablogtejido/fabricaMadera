package com.atenea.gui.tablas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.atenea.data.Cliente;
import com.atenea.gui.modificar.ModificarCliente;
import com.atenea.rsh.ClienteRSH;

public class VisualizarClientes extends JFrame{

	private JTable table;
	private DefaultTableModel modelo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarClientes window = new VisualizarClientes();
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
	public VisualizarClientes() {
		initialize();
	}

	private void initialize() {
		this.setTitle("Visualizar Clientes");
		setBounds(100, 100, 827, 461);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel titulopanel = new JPanel();
		titulopanel.setBounds(0, 0, 811, 58);
		titulopanel.setBackground(new Color(72, 61, 139));
		getContentPane().add(titulopanel);
		titulopanel.setLayout(null);
		
		JLabel titulo = new JLabel("CLIENTES");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 11, 811, 36);
		titulopanel.add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 69, 751, 266);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		modelo = new DefaultTableModel();
		table.setModel(modelo);
		
		modelo.addColumn("DNI");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellidos");
		table.getTableHeader().setReorderingAllowed(false);
		
		scrollPane.setViewportView(table);
		
		JPanel panelBoton = new JPanel();
		panelBoton.setBounds(270, 346, 247, 43);
		getContentPane().add(panelBoton);
		panelBoton.setLayout(null);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(new Color(72, 61, 139));
		btnModificar.setBounds(37, 11, 95, 23);
		panelBoton.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(new Color(72, 61, 139));
		btnEliminar.setBounds(142, 11, 95, 23);
		panelBoton.add(btnEliminar);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFacturas = new JMenu("Facturas");
		menuBar.add(menuFacturas);
					
		JMenu menuEmpleados = new JMenu("Empleado");
		menuBar.add(menuEmpleados);
		
		JMenuItem verFacturas = new JMenuItem("Ver Facturas");
		menuFacturas.add(verFacturas);
		
		JMenuItem verEmpleados = new JMenuItem("Ver Empleados");
		menuEmpleados.add(verEmpleados);
		
		
		verFacturas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarFacturas window2 = new VisualizarFacturas();
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
		
		
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String dni = (String) modelo.getValueAt(table.getSelectedRow(), 0);
				System.out.println(dni);
				Cliente cl = null;
				ClienteRSH rs = ClienteRSH.getInstance();
				for (Cliente c : rs.verClientes()) {
					if (c.getDni().equals(dni)) {
						System.out.println(c);
						cl = c;
					}
				}
				ModificarCliente frameModificar = new ModificarCliente(cl);
				frameModificar.setVisible(true);
				setVisible(false);
			
			}
			
		});
		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dni = (String) modelo.getValueAt(table.getSelectedRow(), 0);
				System.out.println(dni);
				Cliente cl = null;
				ClienteRSH rs = ClienteRSH.getInstance();
				for (Cliente c : rs.verClientes()) {
					if (c.getDni().equals(dni)) {
						System.out.println(c);
						cl = c;
					}
				}
				rs.borrarCliente(cl);
				modelo.setRowCount(0);
				ClientesJTable();
			}
			
		});
		ClientesJTable();

	}
	
	
	public void ClientesJTable() {
		ClienteRSH rsh = ClienteRSH.getInstance();
		List<Cliente> clientes = rsh.verClientes();
		String[] fila = new String [3];
		
		for (Cliente cliente : clientes) {
			
			fila[0] = cliente.getDni();
			fila[1] = cliente.getNombre();
			fila[2] = cliente.getApellidos();
			
			modelo.addRow(fila);
			
		}
				
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
