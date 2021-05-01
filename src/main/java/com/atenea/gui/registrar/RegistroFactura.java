package com.atenea.gui.registrar;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.atenea.data.Cliente;
import com.atenea.data.Empleado;
import com.atenea.data.Producto;
import com.atenea.rsh.ClienteRSH;
import com.atenea.rsh.EmpleadoRSH;
import com.atenea.rsh.ProductoRSH;

import javax.swing.JComboBox;

public class RegistroFactura extends JFrame{

	private JPanel contentPane;
	private JComboBox<String> comboBoxClientes;
	private JComboBox<String> comboBoxEmpleados;
	private ArrayList<JLabel> labelProductos;
	private JLabel lblProducto;
	
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
	
	public RegistroFactura() {
		
		setTitle("Registro Factura");
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

		JLabel lblfactura = new JLabel("FACTURA");
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
		
		buscarProductos();
		buscarEmpleados();
		
		
		JLabel lblCliente = new JLabel("Cliente: ");
		lblCliente.setBounds(26, 174, 57, 13);
		contentPane.add(lblCliente);
		
		comboBoxClientes = new JComboBox(); //Pasar listados de clientes registrados
		comboBoxClientes.setBounds(116, 169, 194, 22);
		contentPane.add(comboBoxClientes);
		
		buscarClientes();
		
		
		
		//Los productos se mostrara el listado de todos los productos registrados 
		//y con un spinner para la cantidad de cada producto
		
		
		
	}
	private void buscarEmpleados() {
		EmpleadoRSH rsh = EmpleadoRSH.getInstance();
		
		List<Empleado> empleados = rsh.verEmpleados();

		for (Empleado empleado : empleados) {
			comboBoxEmpleados.addItem(empleado.getNombre());
		}	
	}
	
	private void buscarClientes() {
		ClienteRSH rsh = ClienteRSH.getInstance();
		
		List<Cliente> clientes = rsh.verClientes();
		
		
		for (Cliente cliente : clientes) {
			String nombreApellidos = cliente.getNombre() + " " + cliente.getApellidos();
			comboBoxClientes.addItem(nombreApellidos);
		}
		
	}
	
	private void buscarProductos() {
		ProductoRSH rsh = ProductoRSH.getInstance();
		
		List<Producto> productos = rsh.verProductos();
		
		int y = 232;
		labelProductos = new ArrayList<JLabel>();
		
		for (Producto producto : productos) {
			lblProducto = new JLabel("Test");
			System.out.println(producto.getNombre());
			lblProducto.setText(producto.getNombre());
			lblProducto.setBounds(26, 232, 200, 13);
			y += 58;
			labelProductos.add(lblProducto);
			/*for (JLabel label : labelProductos) {
				label.setText(producto.getNombre());
				label.setBounds(26, y, 200, 13);
				y += 58;
				contentPane.add(label);
			}*/
		}
		JPanel p = new JPanel();
		p.setBounds(22, 230, 100, 500);
		//p.setLayout(new GridLayout(2,2));
		for (JLabel j : labelProductos) {
			System.out.println(2);
			p.add(j);
		}
		contentPane.add(p);
	}
}
