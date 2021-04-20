package com.atenea.gui;

import java.awt.Color;
import com.atenea.data.EnumPuestoEmpleados;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import com.atenea.data.Empleado;
import com.atenea.db.DBException;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.atenea.db.DBManager;


import javax.swing.JPasswordField;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.Label;

public class Registro extends JFrame{
	private static final String EnumPuestoEmpleados = null;
	private JTextField textFieldPuesto;
	private JTextField textFieldPu;
	private JTextField textFieldDireccion;
	private JTextField textFieldTelefono;
	private JTextField textFieldDni;
	private JTextField textFieldNombre;
	private JTextField textFieldEmail;
	private JPasswordField textFieldContrasena;
	private JDateChooser dateChooser;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro window = new Registro();
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
	public Registro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Registro");
		setBounds(100, 100, 610, 679);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel titulopanel = new JPanel();
		titulopanel.setBounds(0, 0, 597, 58);
		titulopanel.setBackground(new Color(72, 61, 139));
		getContentPane().add(titulopanel);
		titulopanel.setLayout(null);

		JLabel titulo = new JLabel("REGISTRARSE");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titulo.setBounds(0, 11, 597, 36);
		titulopanel.add(titulo);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnregistrar = new JButton("Registrarse");
		btnregistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					addUserEmpleado();		
								
					Login log = new Login();
					log.setVisible(true);
					setVisible(false);
					
					
				} catch (DBException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ya existe un empleado con este DNI");
				}
				
			}

		
		});
		btnregistrar.setForeground(Color.WHITE);
		btnregistrar.setBackground(new Color(0, 0, 128));
		btnregistrar.setBounds(231, 497, 134, 36);
		getContentPane().add(btnregistrar);

		JButton cerrar = new JButton("Cerrar");
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(0, 0, 128));
		cerrar.setBounds(504, 555, 80, 31);
		getContentPane().add(cerrar);
		cerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JButton volver = new JButton("Volver");
		volver.setForeground(Color.WHITE);
		volver.setBackground(new Color(0, 0, 128));
		volver.setBounds(10, 555, 80, 31);
		getContentPane().add(volver);
		
		JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setBounds(149, 322, 46, 14);
		getContentPane().add(lblPuesto);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(149, 291, 80, 14);
		getContentPane().add(lblContrasena);
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setBounds(149, 138, 46, 14);
		getContentPane().add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBounds(149, 169, 46, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(149, 260, 46, 14);
		getContentPane().add(lblEmail);
		
		textFieldPu = new JTextField();
		textFieldPu.setBounds(266, 319, 164, 20);
		getContentPane().add(textFieldPu);
		textFieldPu.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccion.setBounds(149, 200, 63, 14);
		getContentPane().add(lblDireccion);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(266, 197, 164, 20);
		getContentPane().add(textFieldDireccion);
		textFieldDireccion.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(149, 228, 46, 14);
		getContentPane().add(lblTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(265, 225, 165, 20);
		getContentPane().add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		textFieldDni = new JTextField();
		textFieldDni.setBounds(266, 135, 164, 20);
		getContentPane().add(textFieldDni);
		textFieldDni.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(266, 166, 164, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(266, 257, 164, 20);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		textFieldTelefono.setColumns(10);
		
		textFieldContrasena = new JPasswordField();
		textFieldContrasena.setBounds(266, 288, 164, 20);
		getContentPane().add(textFieldContrasena);
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooser.setBounds(268, 362, 162, 20);
		getContentPane().add(dateChooser);
		
		JLabel lblFechaNac = new JLabel("Fecha nacimiento:");
		lblFechaNac.setBounds(149, 368, 109, 14);
		getContentPane().add(lblFechaNac);
		
		JLabel lblFechaEmpl = new JLabel("Fecha empleado:");
		lblFechaEmpl.setBounds(149, 404, 109, 14);
		getContentPane().add(lblFechaEmpl);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(268, 398, 162, 20);
		getContentPane().add(dateChooser_1);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Login log = new Login();
				log.setVisible(true);
				setVisible(false);
			}
		});
		
	}


   // EnumPuestoEmpleados empl = Enum.Parse(typeof(EnumPuestoEmpleados), textFieldPuesto.getText());


    
			private void addUserEmpleado() throws DBException {
				DBManager manager = DBManager.getInstance();
				try {
					manager.connect();
					
					Empleado e = new Empleado(
							textFieldDni.getText(),
							textFieldNombre.getText(),
							textFieldDireccion.getText(),
							textFieldEmail.getText(),
							textFieldTelefono.getText(),
							String.valueOf(textFieldContrasena.getText())					
						
					);
					System.out.println(e);
					manager.store(e);
					manager.disconnect();
				}catch (DBException de) {
					manager.disconnect();
					throw de;
				}
			}

			private Object typeof(String enumpuestoempleados2) {
				// TODO Auto-generated method stub
				return null;
			}
}