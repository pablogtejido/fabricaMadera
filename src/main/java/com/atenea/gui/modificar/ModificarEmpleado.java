package com.atenea.gui.modificar;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.atenea.data.Empleado;
import com.atenea.data.EnumPuestoEmpleados;
import com.atenea.gui.tablas.VisualizarEmpleado;
import com.atenea.rsh.EmpleadoRSH;

public class ModificarEmpleado extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDNI;
	private JTextField textFieldNombre;
	private JTextField textFieldDireccion;
	private JPasswordField passwordField;
	private JTextField textFieldEmail;
	private JTextField textFieldSueldo;
	private JTextField textFieldTelefono;
	private JTextField textFieldPuesto;
	private JTextField textFieldNacimiento;
	private JTextField textFieldFechaEmpleado;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */

	public ModificarEmpleado(final Empleado empleado) {
		setTitle("Modificar Empleado");
		setBounds(100, 100, 631, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(72, 61, 139));
		panel.setForeground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 615, 43);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblcliente = new JLabel("MODIFICAR EMPLEADO");
		lblcliente.setBounds(0, 0, 615, 43);
		lblcliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblcliente.setForeground(SystemColor.text);
		panel.add(lblcliente);

		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(193, 95, 57, 13);
		contentPane.add(lblDNI);

		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(323, 90, 150, 19);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);
		textFieldDNI.setText(empleado.getDni());
		textFieldDNI.setEditable(false);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(193, 126, 57, 13);
		contentPane.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(323, 121, 150, 19);
		contentPane.add(textFieldNombre);
		textFieldNombre.setText(empleado.getNombre());

		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setBounds(193, 155, 57, 13);
		contentPane.add(lblDireccion);

		textFieldDireccion = new JTextField();
		textFieldDireccion.setColumns(10);
		textFieldDireccion.setBounds(323, 150, 150, 19);
		contentPane.add(textFieldDireccion);
		textFieldDireccion.setText(empleado.getDireccion());

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(193, 185, 76, 14);
		contentPane.add(lblEmail);

		JButton volver = new JButton("Volver");
		volver.setForeground(Color.WHITE);
		volver.setBackground(new Color(72, 61, 139));
		volver.setBounds(10, 501, 80, 31);
		contentPane.add(volver);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarEmpleado empleados = new VisualizarEmpleado();
				empleados.setVisible(true);
				setVisible(false);
			}
		});

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(new Color(72, 61, 139));
		btnModificar.setBounds(235, 437, 141, 36);
		contentPane.add(btnModificar);

		JButton cerrar = new JButton("Cerrar");
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(72, 61, 139));
		cerrar.setBounds(517, 501, 80, 31);
		contentPane.add(cerrar);

		textFieldEmail = new JTextField();
		textFieldEmail.setText((String) null);
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(323, 180, 150, 19);
		contentPane.add(textFieldEmail);
		textFieldEmail.setText(empleado.getEmail());

		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setBounds(193, 359, 76, 14);
		contentPane.add(lblSueldo);

		JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setBounds(193, 245, 57, 13);
		contentPane.add(lblPuesto);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(193, 216, 57, 13);
		contentPane.add(lblTelefono);

		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(193, 388, 76, 14);
		contentPane.add(lblContrasena);

		textFieldSueldo = new JTextField();
		textFieldSueldo.setText((String) null);
		textFieldSueldo.setColumns(10);
		textFieldSueldo.setBounds(323, 356, 150, 19);
		contentPane.add(textFieldSueldo);
		Double sueldo = empleado.getSueldo();
		textFieldSueldo.setText(sueldo.toString());

		passwordField = new JPasswordField();
		passwordField.setBounds(323, 388, 150, 20);
		contentPane.add(passwordField);
		passwordField.setText(empleado.getContrasena());

		textFieldTelefono = new JTextField();
		textFieldTelefono.setText((String) null);
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(323, 210, 150, 19);
		contentPane.add(textFieldTelefono);
		textFieldTelefono.setText(empleado.getTelefono());

		textFieldPuesto = new JTextField();
		textFieldPuesto.setText((String) null);
		textFieldPuesto.setColumns(10);
		textFieldPuesto.setBounds(323, 239, 150, 19);
		contentPane.add(textFieldPuesto);
		textFieldPuesto.setText(String.valueOf(empleado.getPuesto()));

		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaNacimiento.setBounds(193, 280, 120, 14);
		contentPane.add(lblFechaNacimiento);

		JLabel lblFechaEmpleado = new JLabel("Fecha de empleado:");
		lblFechaEmpleado.setBounds(193, 318, 120, 14);
		contentPane.add(lblFechaEmpleado);

		textFieldNacimiento = new JTextField();
		textFieldNacimiento.setText((String) null);
		textFieldNacimiento.setEditable(false);
		textFieldNacimiento.setColumns(10);
		textFieldNacimiento.setBounds(323, 277, 274, 19);
		contentPane.add(textFieldNacimiento);
		textFieldNacimiento.setText(String.valueOf(empleado.getFcha_empleado()));

		textFieldFechaEmpleado = new JTextField();
		textFieldFechaEmpleado.setText((String) null);
		textFieldFechaEmpleado.setEditable(false);
		textFieldFechaEmpleado.setColumns(10);
		textFieldFechaEmpleado.setBounds(323, 315, 274, 19);
		contentPane.add(textFieldFechaEmpleado);
		textFieldFechaEmpleado.setText(String.valueOf(empleado.getFcha_nacimiento()));

		cerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nombre = textFieldNombre.getText();
				String direccion = textFieldDireccion.getText();
				String email = textFieldEmail.getText();
				String telefono = textFieldTelefono.getText();
				EnumPuestoEmpleados puesto = EnumPuestoEmpleados.valueOf(textFieldPuesto.getText());
				double sueldo = Double.parseDouble(textFieldSueldo.getText());
				String contrasena = passwordField.getText();

				empleado.setNombre(nombre);
				empleado.setDireccion(direccion);
				empleado.setEmail(email);
				empleado.setTelefono(telefono);
				empleado.setPuesto(puesto);
				empleado.setSueldo(sueldo);
				empleado.setContrasena(contrasena);

				EmpleadoRSH.getInstance().modificarEmpleado(empleado);
				System.out.println(empleado);

			}

		});
	}
}
