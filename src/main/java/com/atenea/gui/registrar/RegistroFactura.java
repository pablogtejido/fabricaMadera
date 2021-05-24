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
import javax.swing.JOptionPane;
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
import com.atenea.gui.tablas.VisualizarFacturas;
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

		//Propiedades de la ventana
		setTitle("Registro Factura");
		setBounds(100, 100, 631, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Panel del titulo
		JPanel panel = new JPanel();
		panel.setBackground(new Color(72, 61, 139));
		panel.setForeground(new Color(0, 128, 128));
		panel.setBounds(0, 21, 615, 43);
		contentPane.add(panel);
		panel.setLayout(null);

		//Label del titulo de la ventana
		JLabel lblfactura = new JLabel("FACTURA");
		lblfactura.setBounds(268, 11, 77, 32);
		lblfactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblfactura.setForeground(SystemColor.text);
		panel.add(lblfactura);

		//Label del empleado
		JLabel lblEmpleado = new JLabel("Empleado: ");
		lblEmpleado.setBounds(26, 116, 80, 13);
		contentPane.add(lblEmpleado);

		//ComboBox en el que se mete el listado de empleados
		comboBoxEmpleados = new JComboBox(); 
		comboBoxEmpleados.setBounds(116, 111, 194, 22);
		contentPane.add(comboBoxEmpleados);

		
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
		

		labelProductos = new ArrayList<JLabel>();
		spinProductos = new ArrayList<JSpinner>();
		
		int i = 0;
		int jProd = 2; 

		for (Producto producto : productos) { // meter los spinners
			lblProducto = new JLabel("Test");
			System.out.println(producto.getNombre());
			lblProducto.setText(producto.getNombre() + " [medida: " + producto.getMedida() + " Grosor: "
					+ producto.getGrosor() + " Peso: " + producto.getPeso() + "]");
			lblProducto.setBounds(26, 232, 200, 13);
			labelProductos.add(lblProducto);
			
			i++;
			//Aumentar la ventana si hay mas de 3 productos
			if(i >3 ) {
				jProd++;
				int yProd = 25 * jProd;
				setBounds(100, 100 , 631, 375+ yProd);
			}

		}
		
		JPanel p = new JPanel();
		p.setBounds(22, 230, 400, 500);

		for (JLabel j : labelProductos) {
			spinProducto = new JSpinner();
			spinProducto.setModel(new SpinnerNumberModel(0, 0, 1, 1));

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
				setVisible(false);
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
				setVisible(false);
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
				setVisible(false);
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
				setVisible(false);
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

		final Date fechaActual = new Date();

		JLabel lblFecha = new JLabel("Fecha: " + fechaActual);
		lblFecha.setBounds(348, 115, 241, 14);
		contentPane.add(lblFecha);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setForeground(Color.WHITE);
		btnRegistrar.setBackground(new Color(72, 61, 139));
		btnRegistrar.setBounds(348, 169, 89, 23);
		contentPane.add(btnRegistrar);

		btnRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Set<Producto> prodctServ = new HashSet<>();
				int ii = 0;
				Double totalPrecio = 0.0;
				for (JSpinner spin : spinProductos) { 
					System.out.println(spin.getValue());
					for (int i = 0; i < (int) spin.getValue(); i++) {
						System.out.println(productos.get(ii));
						totalPrecio += productos.get(ii).getPrecio();
						prodctServ.add(productos.get(ii));
						System.out.println(prodctServ);

					}
					ii++;
				}
				System.out.println(prodctServ);
				
				String empleadoNombre = (String) comboBoxEmpleados.getSelectedItem();
				String clienteNombre = (String) comboBoxClientes.getSelectedItem();

				Factura factura = new Factura(seleccionComboBoxEmpleado(empleadoNombre), seleccionComboBoxCliente(clienteNombre), prodctServ,
						fechaActual);
				
				
				int seleccion = JOptionPane.showOptionDialog(null, "¿Deseas guardar la factura? \nPrecio: " + totalPrecio + " €", "PRECIO FACTURA", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Aceptar", "Cancelar"}, "Aceptar");
				
				if(seleccion == 0) {
					FacturaRSH.getInstance().guardarFactura(factura);
					System.out.println(factura);
					VisualizarFacturas facts = new VisualizarFacturas();
					facts.setVisible(true);
					setVisible(false);
				}
				
				
			}
			

		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarFacturas facts = new VisualizarFacturas();
				facts.setVisible(true);
				setVisible(false);
			}
		} );
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBackground(new Color(72, 61, 139));
		btnVolver.setBounds(447, 169, 89, 23);
		getContentPane().add(btnVolver);
		
		setLocationRelativeTo(null);

	}

	/**
	 * Genera un String por cada Empleado para poder introducirlo en un comboBox
	 */
	private void buscarEmpleados() {
		EmpleadoRSH rsh = EmpleadoRSH.getInstance();

		List<Empleado> empleados = rsh.verEmpleados();

		for (Empleado empleado : empleados) {
			String empleadoString = empleado.getNombre() + " - " + empleado.getDni();
			comboBoxEmpleados.addItem(empleadoString);
		}
	}
	
	/**
	 * Genera un String por cada Cliente para poder introducirlo en un comboBox
	 */
	private void buscarClientes() {
		ClienteRSH rsh = ClienteRSH.getInstance();

		List<Cliente> clientes = rsh.verClientes();

		for (Cliente cliente : clientes) {
			String clienteString = cliente.getNombre() + " " + cliente.getApellidos() + " - " + cliente.getDni();
			comboBoxClientes.addItem(clienteString);
		}

	}

	/**
	 * @param String item del comboBox de Empleado
	 * @return <Code>Empleado</Code> Convierte el item, String, del comboBox del empleado en un Empleado
	 */
	private Empleado seleccionComboBoxEmpleado(String empleadoNombre) {

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
	
	/**
	 * @param String item del comboBox de Cliente
	 * @return <Code>Cliente</Code> Convierte el item, String, del comboBox del empleado en un Cliente
	 */
	private Cliente seleccionComboBoxCliente(String clienteNombre) {
		// Obtener el String del combobox y obtener el cliente con los mismos atributos

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

}
