package com.atenea.gui;

import java.awt.Color;
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
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import com.atenea.db.DBManager;

import javax.swing.JPasswordField;

import com.toedter.calendar.JDateChooser;

public class RegistroEmpleado extends JFrame {
	private JTextField puestotxt;
	private JTextField direcciontxt;
	private JTextField telefonotxt;
	private JTextField dnitxt;
	private JTextField nombretxt;
	private JTextField mailtxt;
	private JPasswordField contratxt;
	private JDateChooser fechaNaci;
	private JTextField sueldotxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroEmpleado window = new RegistroEmpleado();
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
	public RegistroEmpleado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Registro Empleado");
		setBounds(100, 100, 625, 824);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel titulopanel = new JPanel();
		titulopanel.setBounds(0, 0, 609, 58);
		titulopanel.setBackground(new Color(72, 61, 139));
		getContentPane().add(titulopanel);
		titulopanel.setLayout(null);

		JLabel titulo = new JLabel("REGISTRO");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titulo.setBounds(0, 11, 607, 36);
		titulopanel.add(titulo);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);

		final JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setBounds(19, 367, 46, 25);
		getContentPane().add(lblPuesto);

		final JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(19, 592, 72, 25);
		getContentPane().add(lblContrasena);

		final JLabel lblDni = new JLabel("Dni:");
		lblDni.setBounds(18, 95, 47, 20);
		getContentPane().add(lblDni);

		final JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBounds(19, 148, 58, 17);
		getContentPane().add(lblNombre);

		final JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(18, 258, 58, 20);
		getContentPane().add(lblEmail);

		final JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccion.setBounds(19, 198, 80, 25);
		getContentPane().add(lblDireccion);

		puestotxt = new JTextField();
		puestotxt.setBounds(149, 366, 327, 27);
		getContentPane().add(puestotxt);
		puestotxt.setColumns(10);

		direcciontxt = new JTextField();
		direcciontxt.setBounds(149, 198, 327, 25);
		getContentPane().add(direcciontxt);
		direcciontxt.setColumns(10);

		final JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(19, 312, 58, 20);
		getContentPane().add(lblTelefono);

		telefonotxt = new JTextField();
		telefonotxt.setBounds(149, 310, 327, 25);
		getContentPane().add(telefonotxt);
		telefonotxt.setColumns(10);

		dnitxt = new JTextField();
		dnitxt.setBounds(149, 93, 327, 25);
		getContentPane().add(dnitxt);
		dnitxt.setColumns(10);

		nombretxt = new JTextField();
		nombretxt.setBounds(149, 144, 327, 25);
		getContentPane().add(nombretxt);
		nombretxt.setColumns(10);

		mailtxt = new JTextField();
		mailtxt.setBounds(149, 256, 327, 25);
		getContentPane().add(mailtxt);
		mailtxt.setColumns(10);
		telefonotxt.setColumns(10);

		contratxt = new JPasswordField();
		contratxt.setBounds(149, 592, 327, 25);
		getContentPane().add(contratxt);

		fechaNaci = new JDateChooser();
		fechaNaci.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		fechaNaci.setBounds(149, 426, 327, 25);
		getContentPane().add(fechaNaci);

		final JLabel lblFechaNac = new JLabel("Fecha nacimiento:");
		lblFechaNac.setBounds(19, 426, 109, 25);
		getContentPane().add(lblFechaNac);

		final JLabel lblFechaEmpl = new JLabel("Fecha empleado:");
		lblFechaEmpl.setBounds(19, 483, 109, 25);
		getContentPane().add(lblFechaEmpl);

		JDateChooser fechaEmpleado = new JDateChooser();
		fechaEmpleado.setBounds(149, 483, 327, 25);
		getContentPane().add(fechaEmpleado);

		JButton btnregistrar = new JButton("Registrarse");
		btnregistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					addUserEmpleado();

					LoginEmpleado log = new LoginEmpleado();
					log.setVisible(true);
					setVisible(false);

				} catch (DBException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ya existe un empleado con este DNI");
				}
			}
		});
		btnregistrar.setForeground(Color.WHITE);
		btnregistrar.setBackground(new Color(72, 61, 139));
		btnregistrar.setBounds(235, 643, 141, 36);
		getContentPane().add(btnregistrar);

		JButton cerrar = new JButton("Cerrar");
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(72, 61, 139));
		cerrar.setBounds(517, 707, 80, 31);
		getContentPane().add(cerrar);
		cerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JButton volver = new JButton("Volver");
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginEmpleado log = new LoginEmpleado();
				log.setVisible(true);
				setVisible(false);
			}
		});
		volver.setForeground(Color.WHITE);
		volver.setBackground(new Color(72, 61, 139));
		volver.setBounds(10, 707, 80, 31);
		getContentPane().add(volver);
		
		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setBounds(19, 543, 46, 14);
		getContentPane().add(lblSueldo);
		
		sueldotxt = new JTextField();
		sueldotxt.setBounds(149, 540, 327, 20);
		getContentPane().add(sueldotxt);
		sueldotxt.setColumns(10);

	}

	// EnumPuestoEmpleados empl = Enum.Parse(typeof(EnumPuestoEmpleados),
	// textFieldPuesto.getText());

	private boolean empleadoExistente() {
		DBManager manager = DBManager.getInstance();
		List<Empleado> empleados = manager.getEmpleados();
		try {
			for (Empleado empleado : empleados) {
				if (empleado.getDni() == dnitxt.getText()) {
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;

	}
	
	/*
	 	this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.email = email;
		this.telefono = telefono;
		this.puesto = puesto;
		this.fcha_nacimiento = fcha_nacimiento;
		this.fcha_empleado = fcha_empleado;
		this.sueldo = sueldo;
		this.contrasena = contrasena;
	 */

	private void addUserEmpleado() throws DBException {
		DBManager manager = DBManager.getInstance();
		try {
			
			Empleado e = new Empleado(dnitxt.getText(), 
									nombretxt.getText(), 
									direcciontxt.getText(),
									mailtxt.getText(),
									telefonotxt.getText(),
									String.valueOf(contratxt.getText()));
			
				
			System.out.println(e);
			manager.store(e);

		} catch (Exception de) {
			throw de;
		}
	}
}