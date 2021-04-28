package com.atenea.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.atenea.data.Empleado;
import com.atenea.data.Factura;
import com.atenea.rsh.EmpleadoRSH;
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

public class VisualizarEmpleado extends JFrame {
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
	public VisualizarEmpleado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Visualizar Empleados");

		setBounds(100, 100, 827, 461);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel titulopanel = new JPanel();
		titulopanel.setBounds(0, 0, 811, 58);
		titulopanel.setBackground(new Color(72, 61, 139));
		getContentPane().add(titulopanel);
		titulopanel.setLayout(null);

		JLabel titulo = new JLabel("EMPLEADOS");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 11, 811, 36);
		titulopanel.add(titulo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 69, 751, 266);
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

		modelo.addColumn("Dni");
		modelo.addColumn("Nombre");
		modelo.addColumn("Direccion");
		modelo.addColumn("Email");
		modelo.addColumn("Telefono");
		modelo.addColumn("Puesto");
		// modelo.addColumn("Fecha de nacimiento");
		// modelo.addColumn("Fecha de empleado");
		// modelo.addColumn("Sueldo");
		// modelo.addColumn("Contraseña");

		table.getTableHeader().setReorderingAllowed(false);

		scrollPane.setViewportView(table);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFacturas = new JMenu("Facturas");
		menuBar.add(menuFacturas);

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
				ClientesEmpleado window2 = new ClientesEmpleado();
				window2.setVisible(true);
			}
		});

		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dni = (String) modelo.getValueAt(table.getSelectedRow(), 0);
				Empleado fa = null;
				EmpleadoRSH rs = EmpleadoRSH.getInstance();
				for (Empleado f : rs.verEmpleados()) {
					if (f.getDni().equals(dni)) {
						System.out.println(f);
						fa = f;
					}
				}
				System.out.println("Borrando factura");
				rs.borrarEmpleado(fa);
				modelo.setRowCount(0);
				EmpleadosJTable();
			}

		});
		EmpleadosJTable();
	}

	private void EmpleadosJTable() {
		EmpleadoRSH rsh = EmpleadoRSH.getInstance();
		List<Empleado> empleados = rsh.verEmpleados();
		String[] fila = new String[6];
		System.out.println(empleados);

		for (Empleado empl : empleados) {
			/*
			 * modelo.addColumn("Fecha de nacimiento");
			 * modelo.addColumn("Fecha de empleado"); modelo.addColumn("Sueldo");
			 * modelo.addColumn("Contraseña");
			 */
			fila[0] = empl.getDni();
			fila[1] = empl.getNombre();
			fila[2] = empl.getDireccion();
			fila[3] = empl.getEmail();
			fila[4] = empl.getTelefono();
			fila[5] = String.valueOf(empl.getPuesto());
			/*
			 * Date fecha = empl.getFcha_nacimiento(); System.out.println("/n");
			 * System.out.println(fecha); fila[6] = fecha.toString(); //fila[7] =
			 * String.valueOf(empl.getFcha_empleado()); //fila[8] =
			 * String.valueOf(empl.getSueldo()); //fila[9] =
			 * String.valueOf(empl.getContrasena());
			 */
			modelo.addRow(fila);

		}
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}
}