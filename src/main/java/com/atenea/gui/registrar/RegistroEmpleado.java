package com.atenea.gui.registrar;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import com.atenea.data.Empleado;
import com.atenea.data.EnumPuestoEmpleados;
import com.atenea.db.DBException;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import com.atenea.db.DBManager;
import com.atenea.gui.LoginEmpleado;
import com.atenea.rsh.EmpleadoRSH;

import javax.swing.JPasswordField;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

public class RegistroEmpleado extends JFrame {
	private JTextField direcciontxt;
	private JTextField telefonotxt;
	private JTextField dnitxt;
	private JTextField nombretxt;
	private JTextField mailtxt;
	private JPasswordField contratxt;
	private JDateChooser fechaNaci;
	private JDateChooser fechaEmpleado;
	private JTextField sueldotxt;
	JComboBox<EnumPuestoEmpleados> comboBoxPuesto;

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

		fechaEmpleado = new JDateChooser();
		fechaEmpleado.setBounds(149, 483, 327, 25);
		getContentPane().add(fechaEmpleado);

		fechaEmpleado.getCalendarButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}

		});

		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setBounds(19, 543, 46, 14);
		getContentPane().add(lblSueldo);

		sueldotxt = new JTextField();
		sueldotxt.setBounds(149, 540, 327, 20);
		getContentPane().add(sueldotxt);
		sueldotxt.setColumns(10);

		comboBoxPuesto = new JComboBox<EnumPuestoEmpleados>();
		comboBoxPuesto.setBounds(149, 368, 327, 22);
		getContentPane().add(comboBoxPuesto);

		for (EnumPuestoEmpleados puesto : EnumPuestoEmpleados.values()) {
			comboBoxPuesto.addItem(puesto);
		}

		JButton btnregistrar = new JButton("Registrarse");
		btnregistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (direcciontxt.getText() == null || direcciontxt.getText().isEmpty()
						|| telefonotxt.getText().isEmpty() || dnitxt.getText().isEmpty()
						|| nombretxt.getText().isEmpty() || mailtxt.getText().isEmpty()
						|| contratxt.getPassword().length == 0 || sueldotxt.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Es necesario rellenar todos los campos para registrarse",
							"REGISTRO INCOMPLETO", JOptionPane.ERROR_MESSAGE);
					System.out.println("ERROR");

				} else if (!empleadoExistente()) {
					Empleado empleado = construirEmpleado();
					EmpleadoRSH rsh = EmpleadoRSH.getInstance();
					rsh.guardarEmpleado(empleado);
					System.out.println(empleado);
				} else {
					JOptionPane.showMessageDialog(null, "Este Empleado ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
					System.out.println("ERROR");
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

	}

	private boolean empleadoExistente() {

		EmpleadoRSH rsh = EmpleadoRSH.getInstance();
		List<Empleado> empleados = rsh.verEmpleados();

		for (Empleado empleado : empleados) {
			if (!empleado.getDni().equals(dnitxt.getText())) {
				return false;
			}
		}

		return true;

	}

	public Empleado construirEmpleado() {
		Empleado e = new Empleado(dnitxt.getText(), nombretxt.getText(), direcciontxt.getText(), mailtxt.getText(),
				telefonotxt.getText(), (EnumPuestoEmpleados) comboBoxPuesto.getSelectedItem(), fechaNaci.getDate(),
				fechaEmpleado.getDate(), Double.parseDouble(sueldotxt.getText()),
				String.valueOf(contratxt.getPassword()));

		return e;
	}
}