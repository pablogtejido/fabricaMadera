package com.atenea.gui.tablas;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import com.atenea.data.Empleado;
import com.atenea.gui.modificar.ModificarEmpleado;
import com.atenea.rsh.EmpleadoRSH;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Ventana para visualizar todos los empleados que hay almacenados en la base de
 * datos
 */

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
					VisualizarEmpleado window = new VisualizarEmpleado();
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
		// System.out.println();
		// System.out.println(calcularTiempoEmpresa());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Visualizar Empleados");
		setBounds(100, 100, 1137, 461);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel titulopanel = new JPanel();
		titulopanel.setBounds(0, 0, 1121, 58);
		titulopanel.setBackground(new Color(72, 61, 139));
		getContentPane().add(titulopanel);
		titulopanel.setLayout(null);

		JLabel titulo = new JLabel("EMPLEADOS");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(31, 11, 1090, 36);
		titulopanel.add(titulo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 1101, 266);
		getContentPane().add(scrollPane);

		JPanel panelBoton = new JPanel();
		panelBoton.setBounds(454, 346, 454, 43);
		getContentPane().add(panelBoton);
		panelBoton.setLayout(null);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(new Color(72, 61, 139));
		btnModificar.setBounds(36, 11, 95, 23);
		panelBoton.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(new Color(72, 61, 139));
		btnEliminar.setBounds(143, 11, 84, 23);
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
		modelo.addColumn("Fecha de nacimiento");
		modelo.addColumn("Fecha de empleado");
		modelo.addColumn("Sueldo");

		table.getTableHeader().setReorderingAllowed(false);

		scrollPane.setViewportView(table);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFacturas = new JMenu("Facturas");
		menuBar.add(menuFacturas);

		JMenu menuClientes = new JMenu("Clientes");
		menuBar.add(menuClientes);

		JMenu menuProductos = new JMenu("Producto");
		menuBar.add(menuProductos);

		JMenuItem verFacturas = new JMenuItem("Ver Facturas");
		menuFacturas.add(verFacturas);

		JMenuItem verClientes = new JMenuItem("Ver Clientes");
		menuClientes.add(verClientes);

		JMenuItem verProductos = new JMenuItem("Ver Producto");
		menuProductos.add(verProductos);

		verFacturas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarFacturas window2 = new VisualizarFacturas();
				window2.setVisible(true);
				setVisible(false);

			}
		});

		verClientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VisualizarClientes window2 = new VisualizarClientes();
				window2.setVisible(true);
				setVisible(false);

			}
		});

		verProductos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarProductos window2 = new VisualizarProductos();
				window2.setVisible(true);
				setVisible(false);

			}
		});

		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String dni = (String) modelo.getValueAt(table.getSelectedRow(), 0);
				System.out.println(dni);
				Empleado empl = null;
				EmpleadoRSH rs = EmpleadoRSH.getInstance();
				for (Empleado em : rs.verEmpleados()) {
					if (em.getDni().equals(dni)) {
						System.out.println(em);
						empl = em;
					}
				}
				ModificarEmpleado frameModificar = new ModificarEmpleado(empl);
				frameModificar.setVisible(true);

			}

		});

		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dni = (String) modelo.getValueAt(table.getSelectedRow(), 0);
				Empleado eml = null;
				EmpleadoRSH rs = EmpleadoRSH.getInstance();
				for (Empleado em : rs.verEmpleados()) {
					if (em.getDni().equals(dni)) {
						System.out.println(em);
						eml = em;
					}
				}
				System.out.println("Borrando Empleado");
				rs.borrarEmpleado(eml);
				modelo.setRowCount(0);
				EmpleadosJTable();
			}

		});

		EmpleadosJTable();
	}

	private void EmpleadosJTable() {
		EmpleadoRSH rsh = EmpleadoRSH.getInstance();
		List<Empleado> empleados = rsh.verEmpleados();
		String[] fila = new String[9];
		System.out.println(empleados);

		for (Empleado empl : empleados) {

			fila[0] = empl.getDni();
			fila[1] = empl.getNombre();
			fila[2] = empl.getDireccion();
			fila[3] = empl.getEmail();
			fila[4] = empl.getTelefono();
			fila[5] = String.valueOf(empl.getPuesto());
			fila[6] = String.valueOf(empl.getFcha_nacimiento());
			fila[7] = String.valueOf(empl.getFcha_empleado());
			fila[8] = String.valueOf(empl.getSueldo());

			// TODO meter campo tiempo en empresa calculado

			modelo.addRow(fila);

		}
	}
	

	public boolean isCellEditable(int row, int column) {
		return false;
	}
}