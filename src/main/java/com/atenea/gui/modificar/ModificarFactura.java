package com.atenea.gui.modificar;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.atenea.data.Cliente;
import com.atenea.data.Empleado;
import com.atenea.data.Factura;
import com.atenea.data.Producto;
import com.atenea.gui.registrar.RegistrarProducto;
import com.atenea.gui.registrar.RegistroCliente;
import com.atenea.gui.tablas.VisualizarFacturas;
import com.atenea.rsh.ClienteRSH;
import com.atenea.rsh.EmpleadoRSH;
import com.atenea.rsh.ProductoRSH;

public class ModificarFactura extends JFrame{
	
	private JPanel contentPane;
	private JComboBox<String> comboBoxClientes;
	private JComboBox<String> comboBoxEmpleados;
	private ArrayList<JLabel> labelProductos;
	private JLabel lblProducto;
	private ArrayList<JSpinner> spinProductos;
	private JSpinner spinProducto;

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarFactura window = new ModificarFactura();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}*/
	public ModificarFactura(final Factura factura) {
		
		setTitle("Modificar Factura");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
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
		
		comboBoxEmpleados = new JComboBox(); //Pasar el empleado que se ha registrado
		comboBoxEmpleados.setBounds(116, 111, 194, 22);
		contentPane.add(comboBoxEmpleados);
		
		buscarEmpleados();		
		
		JLabel lblCliente = new JLabel("Cliente: ");
		lblCliente.setBounds(26, 174, 57, 13);
		contentPane.add(lblCliente);
		
		comboBoxClientes = new JComboBox(); //Pasar listados de clientes registrados
		comboBoxClientes.setBounds(116, 169, 194, 22);
		contentPane.add(comboBoxClientes);
		
		buscarClientes();
		
		//BUSCAR PRODUCTOS
		ProductoRSH rsh = ProductoRSH.getInstance();
		
		final List<Producto> productos = rsh.verProductos();
		
		int y = 232;
		labelProductos = new ArrayList<JLabel>();
		spinProductos = new ArrayList<JSpinner>();
		
		for (Producto producto : productos) { //meter los spinners
			lblProducto = new JLabel("Test");
			System.out.println(producto.getNombre());
			lblProducto.setText(producto.getNombre() + " [medida: " + producto.getMedida() + " Grosor: " + 
			producto.getGrosor() + " Peso: " + producto.getPeso()+"]");
			lblProducto.setBounds(26, 232, 200, 13);
			y += 58;
			labelProductos.add(lblProducto);
			
		}
		
		JPanel p = new JPanel();
		p.setBounds(22, 230, 400, 500);
		
		//En los spinners deben de ir los valores que introducimos al registrar la factura (HashMap??)
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

		//CALCULAR PRECIO
		double precio = factura.getPrecio();
		
		JLabel lblPrecio = new JLabel("Precio: " + precio + " €");
		lblPrecio.setBounds(352, 115, 97, 14);
		contentPane.add(lblPrecio);
		
		final Date fechaActual = new Date();
		
		JLabel lblFecha = new JLabel("Fecha: " + fechaActual);
		lblFecha.setBounds(352, 173, 241, 14);
		contentPane.add(lblFecha);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(462, 75, 89, 23);
		contentPane.add(btnModificar);
		
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Producto> prodctServ = new ArrayList();
				int ii = 0;
				for (JSpinner spin : spinProductos) { //TODO solucionar el asignar el valor del spinner a la cantidad de producto
				System.out.println(spin.getValue());
					for (int i = 0; i < (int)spin.getValue(); i++) {
						prodctServ.add(productos.get(ii));
						
					}
					ii++;
				}
				System.out.println(prodctServ);
				
				//Factura factura = new Factura(seleccionComboBoxEmpleado(), seleccionComboBoxCliente(), prodctServ, fechaActual);
				/*factura.setCliente(seleccionComboBoxCliente());
				factura.setEmpleado(seleccionComboBoxEmpleado());
				factura.setProductos(prodctServ);
				factura.setPrecio(null); //Deberiamos volver a calcularlo o con meter los nuevos productos vale??
				factura.setFcha_factura(fechaActual);
				FacturaRSH.getInstance().modificarFactura(factura);*/
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
		//Obtener el String del combobox y obtener el empleado con los mismos atributos
		String empleadoNombre = (String) comboBoxEmpleados.getSelectedItem();
		
		EmpleadoRSH rsh = EmpleadoRSH.getInstance();
		
		List<Empleado> empleados = rsh.verEmpleados();
		
		Empleado emp = new Empleado();
		
		for (Empleado empleado : empleados) {  
			if(empleadoNombre.equals(empleado.getNombre() + " - " + empleado.getDni())) {
				emp = empleado;
			}
		}
		return emp;
	}
	
	private Cliente seleccionComboBoxCliente() {
		//Obtener el String del combobox y obtener el cliente con los mismos atributos

		String clienteNombre = (String) comboBoxClientes.getSelectedItem();
		
		ClienteRSH rsh = ClienteRSH.getInstance();
		
		List<Cliente> clientes = rsh.verClientes();
		
		Cliente cli = new Cliente();
	
		for (Cliente cliente : clientes) {
			if(clienteNombre.equals(cliente.getNombre() + " " + cliente.getApellidos() + " - " + cliente.getDni())) {
				cli = cliente;
			}
		}
		
		return cli;
	}
}
