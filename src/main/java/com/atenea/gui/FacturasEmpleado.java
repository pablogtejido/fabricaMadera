package com.atenea.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.atenea.data.Cliente;
import com.atenea.data.Factura;
import com.atenea.rsh.ClienteRSH;
import com.atenea.rsh.FacturaRSH;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FacturasEmpleado extends JFrame {
	private JTable table;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacturasEmpleado window = new FacturasEmpleado();
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
	public FacturasEmpleado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 827, 461);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel titulopanel = new JPanel();
		titulopanel.setBounds(0, 0, 811, 58);
		titulopanel.setBackground(new Color(72, 61, 139));
		getContentPane().add(titulopanel);
		titulopanel.setLayout(null);

		JLabel titulo = new JLabel("FACTURAS");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 11, 811, 36);
		titulopanel.add(titulo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 69, 751, 266);
		getContentPane().add(scrollPane);
		
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

		table = new JTable();
		modelo = new DefaultTableModel();
		table.setModel(modelo);

		modelo.addColumn("Id");
		modelo.addColumn("Nombre Cliente");
		modelo.addColumn("Apellido Cliente");
		modelo.addColumn("Nombre Empleado");
		//modelo.addColumn("Productos");
		//modelo.addColumn("Precio");
		modelo.addColumn("Fecha");
		table.getTableHeader().setReorderingAllowed(false);

		scrollPane.setViewportView(table);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFacturas = new JMenu("Facturas");
		menuBar.add(menuFacturas);

		JMenu menuClientes = new JMenu("Clientes");
		menuBar.add(menuClientes);

		JMenu mnNewMenu_2 = new JMenu("New menu");
		menuBar.add(mnNewMenu_2);

		JMenuItem verClientes = new JMenuItem("Ver Clientes");
		menuClientes.add(verClientes);

		verClientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientesEmpleado window2 = new ClientesEmpleado();
				window2.setVisible(true);
			}
		});
		
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Long id = (Long) modelo.getValueAt(table.getSelectedRow(), 0);
				System.out.println(id);
				Factura fa = null;
				FacturaRSH rs = FacturaRSH.getInstance();
				for (Factura f : rs.verFacturas()) {
					if (f.getId() == id) {
						System.out.println(f);
						fa = f;
					}
				}
				//ModificarFactura frameModificar = new ModificarFactura(fa);
				//frameModificar.setVisible(true);
				//setVisible(false);
			
			}
			
		});
		
		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Long id = (Long) modelo.getValueAt(table.getSelectedRow(), 0);
				System.out.println(id);
				Factura fa = null;
				FacturaRSH rs = FacturaRSH.getInstance();
				for (Factura f : rs.verFacturas()) {
					if (f.getId() == id) {
						System.out.println(f);
						fa = f;
					}
				}
				rs.borrarFactura(fa);
				modelo.setRowCount(0);
				FacturasJTable();
			}
			
		});
		FacturasJTable();
	}

	private void FacturasJTable() {
		FacturaRSH rsh = FacturaRSH.getInstance();
		List<Factura> facturas = rsh.verFacturas();
		String[] fila = new String[6];
		System.out.println(facturas);

		for (Factura factura : facturas) {
			
			long id = factura.getId();
			fila[0] = String.valueOf(id);
			fila[1] = factura.getCliente().getNombre();
			fila[2] = factura.getCliente().getApellidos();
			fila[3] = factura.getEmpleado().getNombre();
			//fila[4] = factura.toStringProductos();
			//Double precio = factura.getPrecio();
			//fila[5] = precio.toString();
			Date fecha = factura.getFcha_factura();
			fila[4] = fecha.toString(); //cambiar a 6

			modelo.addRow(fila);

		}
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
