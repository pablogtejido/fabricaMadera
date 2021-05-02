package com.atenea.gui.registrar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

public class RegistrarProducto extends JFrame{
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtPeso;
	private JTextField txtMedida;
	private JTextField txtGrosor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarProducto window = new RegistrarProducto();
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
	public RegistrarProducto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 626, 635);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel titulopanel = new JPanel();
		titulopanel.setLayout(null);
		titulopanel.setBackground(new Color(72, 61, 139));
		titulopanel.setBounds(0, 0, 609, 58);
		getContentPane().add(titulopanel);
		
		JLabel lblCrearProducto = new JLabel("CREAR PRODUCTO");
		lblCrearProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrearProducto.setForeground(Color.WHITE);
		lblCrearProducto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCrearProducto.setBounds(0, 11, 607, 36);
		titulopanel.add(lblCrearProducto);
		
		
		//Id
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(69, 94, 47, 20);
		getContentPane().add(lblId);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(171, 92, 108, 25);
		txtId.setEditable(false);
		getContentPane().add(txtId);
		
		
		//Nombre
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(69, 143, 71, 20);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(171, 141, 327, 25);
		getContentPane().add(txtNombre);
		
		//Precio
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(69, 196, 47, 20);
		getContentPane().add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(171, 194, 108, 25);
		getContentPane().add(txtPrecio);
		
		
		//Peso
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(69, 252, 47, 20);
		getContentPane().add(lblPeso);
		
		txtPeso = new JTextField();
		txtPeso.setColumns(10);
		txtPeso.setBounds(171, 250, 108, 25);
		getContentPane().add(txtPeso);
		
		
		//Cantidad
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(69, 305, 71, 20);
		getContentPane().add(lblCantidad);
		
		JComboBox comboBoxCantidad = new JComboBox();
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		comboBoxCantidad.setModel(modelo);
		for (int i = 0; i < 100; i++) {
			modelo.addElement(i);
			
		}
		comboBoxCantidad.setBackground(Color.LIGHT_GRAY);
		comboBoxCantidad.setBounds(171, 305, 47, 20);
		getContentPane().add(comboBoxCantidad);
		
		
		//Medida
		JLabel lblMedida = new JLabel("Medida:");
		lblMedida.setBounds(69, 359, 71, 20);
		getContentPane().add(lblMedida);
		
		txtMedida = new JTextField();
		txtMedida.setColumns(10);
		txtMedida.setBounds(171, 357, 108, 25);
		getContentPane().add(txtMedida);
		
		
		//Grosor
		txtGrosor = new JTextField();
		txtGrosor.setColumns(10);
		txtGrosor.setBounds(171, 409, 108, 25);
		getContentPane().add(txtGrosor);
		
		JLabel lblgrosor = new JLabel("Grosor:");
		lblgrosor.setBounds(69, 411, 59, 20);
		getContentPane().add(lblgrosor);
		
		
		//Botones
		JButton cerrar = new JButton("Cerrar");
		cerrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(72, 61, 139));
		cerrar.setBounds(520, 554, 80, 31);
		getContentPane().add(cerrar);
		
		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//futura tabla de productos¿?
			}
		} );
		volver.setForeground(Color.WHITE);
		volver.setBackground(new Color(72, 61, 139));
		volver.setBounds(10, 554, 80, 31);
		getContentPane().add(volver);
		
		JLabel lblAditivos = new JLabel("Añadido:");
		lblAditivos.setBounds(69, 454, 59, 20);
		getContentPane().add(lblAditivos);
		
		JCheckBox checkBoxAditivos = new JCheckBox("Confirmar Añadido");
		checkBoxAditivos.setBackground(Color.LIGHT_GRAY);
		checkBoxAditivos.setBounds(171, 453, 152, 23);
		getContentPane().add(checkBoxAditivos);
		
		JButton btnAñadir = new JButton("Añadir");
		btnAñadir.setForeground(Color.WHITE);
		btnAñadir.setBackground(new Color(72, 61, 139));
		btnAñadir.setBounds(237, 495, 141, 36);
		getContentPane().add(btnAñadir);
	}
}
