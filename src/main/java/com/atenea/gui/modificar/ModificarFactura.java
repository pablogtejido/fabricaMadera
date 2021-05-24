package com.atenea.gui.modificar;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.atenea.data.Cliente;
import com.atenea.data.Empleado;
import com.atenea.data.Factura;
import com.atenea.data.Producto;
import com.atenea.gui.tablas.VisualizarEmpleado;
import com.atenea.gui.tablas.VisualizarFacturas;
import com.atenea.rsh.ClienteRSH;
import com.atenea.rsh.EmpleadoRSH;
import com.atenea.rsh.FacturaRSH;
import com.atenea.rsh.ProductoRSH;

public class ModificarFactura extends JFrame {

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

	/**
	 * Create the application.
	 */

	public ModificarFactura(final Factura factura) {
		System.out.println("\n\nFACTURA: " + factura);

		setTitle("Modificar Factura");
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

		JLabel lblfactura = new JLabel("FACTURA: " + factura.getId());
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

		buscarEmpleados(factura);

		JLabel lblCliente = new JLabel("Cliente: ");
		lblCliente.setBounds(26, 174, 57, 13);
		contentPane.add(lblCliente);

		comboBoxClientes = new JComboBox(); // Pasar listados de clientes registrados
		comboBoxClientes.setBounds(116, 169, 194, 22);
		contentPane.add(comboBoxClientes);

		buscarClientes(factura);

		// BUSCAR PRODUCTOS
		ProductoRSH rsh = ProductoRSH.getInstance();

		final List<Producto> productos = rsh.verProductos();

		int i = 0;
		int jProd = 2;
		labelProductos = new ArrayList<>();
		spinProductos = new ArrayList<>();

		for (Producto producto : productos) { // meter los spinners
			lblProducto = new JLabel("Test");
			System.out.println(producto.getNombre());
			lblProducto.setText(producto.getNombre() + " [medida: " + producto.getMedida() + " Grosor: "
					+ producto.getGrosor() + " Peso: " + producto.getPeso() + "]");
			lblProducto.setBounds(26, 232, 200, 13);
			labelProductos.add(lblProducto);
			
			i++;
			
			if(i >3 ) {
				jProd++;
				int yProd = 25 * jProd;
				setBounds(100, 100 , 631, 375+ yProd);
			}

		}

		JPanel p = new JPanel();
		p.setBounds(22, 230, 400, 500);

		int index = 0;
		Set<Producto> productos_facturas = factura.getProductos();
		// En los spinners deben de ir los valores que introducimos al registrar la
		// factura (HashMap??)
		for (JLabel j : labelProductos) {
			spinProducto = new JSpinner();
			boolean found = false;
			for (Producto pr : productos_facturas) {
				if (pr.equals(productos.get(index))) {
					spinProducto.setModel(new SpinnerNumberModel(1, 0, 1, 1));
					found = true;
				}
			}
			if (!found) {
				spinProducto.setModel(new SpinnerNumberModel(0, 0, 1, 1));
			}
			int cantidad = spinProducto.getValue().hashCode();
			System.out.println(cantidad);

			spinProductos.add(spinProducto);

			p.add(j);
			p.add(spinProducto);
			index++;
		}
		contentPane.add(p);

		// CALCULAR PRECIO
		double precio = factura.getPrecio();

		JLabel lblPrecio = new JLabel("Precio: " + precio + " €");
		lblPrecio.setBounds(352, 115, 97, 14);
		contentPane.add(lblPrecio);

		final Date fechaActual = new Date();

		JLabel lblFecha = new JLabel("Fecha: " + fechaActual);
		lblFecha.setBounds(352, 173, 241, 14);
		contentPane.add(lblFecha);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(new Color(72, 61, 139));
		btnModificar.setBounds(475, 111, 89, 23);
		contentPane.add(btnModificar);

		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Set<Producto> prodctServ = new HashSet<>();
				int ii = 0;
				for (JSpinner spin : spinProductos) { // TODO solucionar el asignar el valor del spinner a la cantidad
														// de producto
					System.out.println(spin.getValue());
					for (int i = 0; i < (int) spin.getValue(); i++) {
						prodctServ.add(productos.get(ii));

					}
					ii++;
				}
				System.out.println(prodctServ);

				Factura factura_new = new Factura(seleccionComboBoxEmpleado(), seleccionComboBoxCliente(), prodctServ,
						fechaActual);

				FacturaRSH rs = FacturaRSH.getInstance();
				for (Factura f : rs.verFacturas()) {
					if (f.getId() == factura.getId()) {
						rs.borrarFactura(f);
					}
				}
				rs.guardarFactura(factura_new);
				System.out.println(factura_new);
				new VisualizarFacturas().setVisible(true);
				setVisible(false);
			}

		});
		
		JButton volver = new JButton("Volver");
		volver.setForeground(Color.WHITE);
		volver.setBackground(new Color(72, 61, 139));
		volver.setBounds(475, 198, 80, 31);
		contentPane.add(volver);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarFacturas facts = new VisualizarFacturas();
				facts.setVisible(true);
				setVisible(false);
			}
		});

	}

	private void buscarEmpleados(Factura factura) {
		EmpleadoRSH rsh = EmpleadoRSH.getInstance();

		List<Empleado> empleados = rsh.verEmpleados();
		
		Empleado emplFact = factura.getEmpleado();

		for (Empleado empleado : empleados) {
			String empleadoString = empleado.getNombre() + " - " + empleado.getDni();
			comboBoxEmpleados.addItem(empleadoString);
			
			if(empleado.getDni().equals(emplFact.getDni())) {
				comboBoxEmpleados.setSelectedItem(empleadoString);
			}
		}
		
	}

	private void buscarClientes(Factura factura) {
		ClienteRSH rsh = ClienteRSH.getInstance();

		List<Cliente> clientes = rsh.verClientes();
		
		Cliente cliFact = factura.getCliente();

		for (Cliente cliente : clientes) {
			String clienteString = cliente.getNombre() + " " + cliente.getApellidos() + " - " + cliente.getDni();
			comboBoxClientes.addItem(clienteString);
			
			if(cliente.getDni().equals(cliFact.getDni())) {
				comboBoxClientes.setSelectedItem(clienteString);
			}
		}

	}

	private Empleado seleccionComboBoxEmpleado() {
		// Obtener el String del combobox y obtener el empleado con los mismos atributos
		String empleadoNombre = (String) comboBoxEmpleados.getSelectedItem();

		EmpleadoRSH rsh = EmpleadoRSH.getInstance();

		List<Empleado> empleados = rsh.verEmpleados();

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

		ClienteRSH rsh = ClienteRSH.getInstance();

		List<Cliente> clientes = rsh.verClientes();

		Cliente cli = new Cliente();

		for (Cliente cliente : clientes) {
			if (clienteNombre.equals(cliente.getNombre() + " " + cliente.getApellidos() + " - " + cliente.getDni())) {
				cli = cliente;
			}
		}

		return cli;
	}
}
