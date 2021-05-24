package com.atenea.gui.registrar;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.atenea.data.Cliente;
import com.atenea.data.Empleado;
import com.atenea.data.Factura;
import com.atenea.data.Producto;
import com.atenea.gui.tablas.VisualizarClientes;
import com.atenea.gui.tablas.VisualizarEmpleado;
import com.atenea.gui.tablas.VisualizarProductos;
import com.atenea.rsh.ClienteRSH;
import com.atenea.rsh.EmpleadoRSH;
import com.atenea.rsh.FacturaRSH;
import com.atenea.rsh.ProductoRSH;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

/**
 * Ventana para registrar una nueva factura en la base de datos
 */

public class RegistroFactura extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> comboBoxClientes;
	private JComboBox<String> comboBoxEmpleados;
	private ArrayList<JLabel> labelProductos;
	private JLabel lblProducto;
	private ArrayList<JSpinner> spinProductos;
	private JSpinner spinProducto;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroFactura window = new RegistroFactura();
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

	public RegistroFactura() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {

		setTitle("Registro Factura");
		setBounds(100, 100, 631, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(72, 61, 139));
		panel.setForeground(new Color(0, 128, 128));
		panel.setBounds(0, 21, 615, 43);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblfactura = new JLabel("FACTURA");
		lblfactura.setBounds(268, 11, 77, 32);
		lblfactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblfactura.setForeground(SystemColor.text);
		panel.add(lblfactura);

		JLabel lblEmpleado = new JLabel("Empleado: ");
		lblEmpleado.setBounds(26, 116, 80, 13);
		contentPane.add(lblEmpleado);

		comboBoxEmpleados = new JComboBox(); // Pasar el empleado que se ha registrado
		comboBoxEmpleados.setBounds(116, 111, 194, 22);
		contentPane.add(comboBoxEmpleados);

		// buscarProductos();
		buscarEmpleados();
		Object empleado = comboBoxEmpleados.getSelectedItem();

		JLabel lblCliente = new JLabel("Cliente: ");
		lblCliente.setBounds(26, 174, 57, 13);
		contentPane.add(lblCliente);

		comboBoxClientes = new JComboBox(); // Pasar listados de clientes registrados
		comboBoxClientes.setBounds(116, 169, 194, 22);
		contentPane.add(comboBoxClientes);

		buscarClientes();

		// BUSCAR PRODUCTOS
		ProductoRSH rsh = ProductoRSH.getInstance();

		final List<Producto> productos = rsh.verProductos();

		int y = 232;
		labelProductos = new ArrayList<JLabel>();
		spinProductos = new ArrayList<JSpinner>();

		for (Producto producto : productos) { // meter los spinners
			lblProducto = new JLabel("Test");
			System.out.println(producto.getNombre());
			lblProducto.setText(producto.getNombre() + " [medida: " + producto.getMedida() + " Grosor: "
					+ producto.getGrosor() + " Peso: " + producto.getPeso() + "]");
			lblProducto.setBounds(26, 232, 200, 13);
			y += 58;
			labelProductos.add(lblProducto);

		}
		JPanel p = new JPanel();
		p.setBounds(22, 230, 400, 500);

		for (JLabel j : labelProductos) {
			spinProducto = new JSpinner();
			spinProducto.setModel(new SpinnerNumberModel(0, 0, null, 1));

			int cantidad = spinProducto.getValue().hashCode();
			System.out.println(cantidad);

			spinProductos.add(spinProducto);

			p.add(j);
			p.add(spinProducto);
		}
		contentPane.add(p);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuProductos = new JMenu("Productos");
		menuBar.add(menuProductos);

		JMenu menuEmpleados = new JMenu("Empleados");
		menuBar.add(menuEmpleados);

		JMenu menuClientes = new JMenu("Clientes");
		menuBar.add(menuClientes);

		JMenuItem registrarProducto = new JMenuItem("Registrar Producto");
		menuProductos.add(registrarProducto);

		registrarProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RegistrarProducto window2 = new RegistrarProducto();
				window2.setVisible(true);
				// setVisible(false);
			}
		});

		JMenuItem registrarCliente = new JMenuItem("Registrar Cliente");
		menuClientes.add(registrarCliente);

		registrarCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RegistroCliente window2 = new RegistroCliente();
				window2.setVisible(true);
				// setVisible(false);
			}
		});

		JMenuItem verProductos = new JMenuItem("Ver Productos");
		menuProductos.add(verProductos);

		verProductos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarProductos window2 = new VisualizarProductos();
				window2.setVisible(true);
				// setVisible(false);
			}
		});

		JMenuItem verClientes = new JMenuItem("Ver Clientes");
		menuClientes.add(verClientes);

		verClientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarClientes window2 = new VisualizarClientes();
				window2.setVisible(true);
				// setVisible(false);
			}
		});

		JMenuItem verEmpleados = new JMenuItem("Ver Empleados");
		menuEmpleados.add(verEmpleados);

		verEmpleados.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarEmpleado window2 = new VisualizarEmpleado();
				window2.setVisible(true);
				// setVisible(false);
			}
		});

		// CALCULAR PRECIO
		double precio = 0;

		JLabel lblPrecio = new JLabel("Precio: " + precio + " €");
		lblPrecio.setBounds(352, 115, 97, 14);
		contentPane.add(lblPrecio);

		final Date fechaActual = new Date();

		JLabel lblFecha = new JLabel("Fecha: " + fechaActual);
		lblFecha.setBounds(352, 173, 241, 14);
		contentPane.add(lblFecha);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(462, 75, 89, 23);
		contentPane.add(btnRegistrar);

		btnRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Set<Producto> prodctServ = new HashSet<>();
				int ii = 0;
				for (JSpinner spin : spinProductos) { // TODO solucionar el asignar el valor del spinner a la cantidad
														// de producto
					System.out.println(spin.getValue());
					for (int i = 0; i < (int) spin.getValue(); i++) {
						System.out.println(productos.get(ii));
						prodctServ.add(productos.get(ii));
						System.out.println(prodctServ);

					}
					ii++;
				}
				System.out.println(prodctServ);

				Factura factura = new Factura(seleccionComboBoxEmpleado(), seleccionComboBoxCliente(), prodctServ,
						fechaActual);
				FacturaRSH.getInstance().guardarFactura(factura);
				System.out.println(factura);
			}

		});

	}

	private void buscarEmpleados() {
		EmpleadoRSH rsh = EmpleadoRSH.getInstance();

		List<Empleado> empleados = rsh.verEmpleados();

		for (Empleado empleado : empleados) {
			String empleadoString = empleado.getNombre() + " - " + empleado.getDni();
			comboBoxEmpleados.addItem(empleadoString);
		}
	}

	private void buscarClientes() {
		ClienteRSH rsh = ClienteRSH.getInstance();

		List<Cliente> clientes = rsh.verClientes();

		for (Cliente cliente : clientes) {
			String clienteString = cliente.getNombre() + " " + cliente.getApellidos() + " - " + cliente.getDni();
			comboBoxClientes.addItem(clienteString);
		}

	}

	private Empleado seleccionComboBoxEmpleado() {
		// Obtener el String del combobox y obtener el empleado con los mismos atributos
		String empleadoNombre = (String) comboBoxEmpleados.getSelectedItem();

		EmpleadoRSH rshEmpl = EmpleadoRSH.getInstance();

		List<Empleado> empleados = rshEmpl.verEmpleados();

		Empleado emp = new Empleado();

		for (Empleado empleado : empleados) {
			if (empleadoNombre.equals(empleado.getNombre() + " - " + empleado.getDni())) {
				emp = empleado;
			}
		}
		return emp;
	}

	private Cliente seleccionComboBoxCliente() {
		// Obtener el String del combobox y obtener el cliente con los mismos atributos

		String clienteNombre = (String) comboBoxClientes.getSelectedItem();

		ClienteRSH rshClient = ClienteRSH.getInstance();

		List<Cliente> clientes = rshClient.verClientes();

		Cliente cli = new Cliente();

		for (Cliente cliente : clientes) {
			if (clienteNombre.equals(cliente.getNombre() + " " + cliente.getApellidos() + " - " + cliente.getDni())) {
				cli = cliente;
			}
		}

		return cli;
	}

	private void buscarProductos() {

	}
}
