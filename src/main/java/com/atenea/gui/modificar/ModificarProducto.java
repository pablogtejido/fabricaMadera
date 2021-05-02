package com.atenea.gui.modificar;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.atenea.data.Producto;
import com.atenea.gui.tablas.VisualizarEmpleado;
import com.atenea.rsh.ProductoRSH;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class ModificarProducto extends JFrame{
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtPeso;
	private JTextField txtCantidad;
	private JTextField txtGrosor;
	private JTextField txtMedida;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ModificarProducto(final Producto prod) {
		setBounds(100, 100, 631, 580);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(new Color(0, 128, 128));
		panel.setBackground(new Color(72, 61, 139));
		panel.setBounds(0, 0, 615, 43);
		getContentPane().add(panel);
		
		JLabel titulo = new JLabel("MODIFICAR PRODUCTO");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(0, 0, 615, 43);
		panel.add(titulo);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(130, 99, 57, 13);
		getContentPane().add(lblId);
		
		txtId = new JTextField();
		txtId.setText(String.valueOf(prod.getId()));
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(260, 94, 150, 19);
		getContentPane().add(txtId);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(130, 142, 57, 13);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setText(prod.getNombre());
		txtNombre.setColumns(10);
		txtNombre.setBounds(260, 137, 150, 19);
		getContentPane().add(txtNombre);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(130, 190, 57, 13);
		getContentPane().add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setText(String.valueOf(prod.getPrecio()));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(260, 185, 70, 19);
		getContentPane().add(txtPrecio);
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(130, 236, 76, 14);
		getContentPane().add(lblPeso);
		
		txtPeso = new JTextField();
		txtPeso.setText(String.valueOf(prod.getPeso()));
		txtPeso.setColumns(10);
		txtPeso.setBounds(260, 233, 70, 19);
		getContentPane().add(txtPeso);
				
		JLabel lblMedida = new JLabel("Medida:");
		lblMedida.setBounds(130, 283, 120, 14);
		getContentPane().add(lblMedida);
		
		txtMedida = new JTextField();
		txtMedida.setText(String.valueOf(prod.getMedida()));
		txtMedida.setColumns(10);
		txtMedida.setBounds(260, 280, 70, 19);
		getContentPane().add(txtMedida);
		
		JLabel lblGrosor = new JLabel("Grosor:");
		lblGrosor.setBounds(130, 328, 120, 14);
		getContentPane().add(lblGrosor);
		
		txtGrosor = new JTextField();
		txtGrosor.setText(String.valueOf(prod.getGrosor()));
		txtGrosor.setColumns(10);
		txtGrosor.setBounds(260, 325, 70, 19);
		getContentPane().add(txtGrosor);
		
		JLabel lblAñadidos = new JLabel("Añadido:");
		lblAñadidos.setBounds(130, 373, 76, 14);
		getContentPane().add(lblAñadidos);
		
		final JCheckBox checkBoxAñadido = new JCheckBox("Confirmar Añadido");
		checkBoxAñadido.setSelected(prod.isAnyadidos());
		checkBoxAñadido.setBounds(260, 369, 150, 23);
		getContentPane().add(checkBoxAñadido);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nombre = txtNombre.getText();
				double precio = Double.parseDouble(txtPrecio.getText());
				double peso = Double.parseDouble(txtPeso.getText());
				double medida = Double.parseDouble(txtMedida.getText());
				double grosor = Double.parseDouble(txtGrosor.getText());
				boolean añadido = checkBoxAñadido.isSelected();
				
				
				prod.setNombre(nombre);
				prod.setPrecio(precio);
				prod.setPeso(peso);
				prod.setMedida(medida);
				prod.setGrosor(grosor);
				prod.setAnyadidos(añadido);
				
				ProductoRSH.getInstance().modificarProducto(prod);
			}
		});
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(new Color(72, 61, 139));
		btnModificar.setBounds(235, 437, 144, 36);
		getContentPane().add(btnModificar);
		
		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarEmpleado empleados = new VisualizarEmpleado();
				empleados.setVisible(true);
				setVisible(false);
			}
		});
		volver.setForeground(Color.WHITE);
		volver.setBackground(new Color(72, 61, 139));
		volver.setBounds(10, 501, 80, 31);
		getContentPane().add(volver);
		
		JButton cerrar = new JButton("Cerrar");
		cerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(72, 61, 139));
		cerrar.setBounds(525, 501, 80, 31);
		getContentPane().add(cerrar);
		
		

	}
}
