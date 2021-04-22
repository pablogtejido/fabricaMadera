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
import java.util.List;
import java.awt.event.ActionEvent;
import com.atenea.db.DBManager;


import javax.swing.JPasswordField;

import java.awt.Label;
import com.toedter.calendar.JDateChooser;

public class RegistroEmpleado extends JFrame{
	private static final String EnumPuestoEmpleados = null;
	private JTextField puestotxt;
	private JTextField direcciontxt;
	private JTextField telefonotxt;
	private JTextField dnitxt;
	private JTextField nombretxt;
	private JTextField mailtxt;
	private JPasswordField contratxt;
	private JDateChooser fechaNaci;
	private JPasswordField repetirContratxt;


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
		this.setTitle("Registro");
		setBounds(100, 100, 625, 530);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel titulopanel = new JPanel();
		titulopanel.setBounds(0, 0, 609, 58);
		titulopanel.setBackground(new Color(72, 61, 139));
		getContentPane().add(titulopanel);
		titulopanel.setLayout(null);

		JLabel titulo = new JLabel("REGISTRARSE");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titulo.setBounds(0, 11, 607, 36);
		titulopanel.add(titulo);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		final JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setBounds(19, 285, 46, 25);
		getContentPane().add(lblPuesto);
		
		final JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(18, 237, 72, 25);
		getContentPane().add(lblContrasena);
		
		final JLabel lblDni = new JLabel("Dni:");
		lblDni.setBounds(18, 95, 47, 20);
		getContentPane().add(lblDni);
		
		final JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBounds(330, 97, 58, 17);
		getContentPane().add(lblNombre);
		
		final JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(18, 187, 58, 20);
		getContentPane().add(lblEmail);
		
		final JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccion.setBounds(20, 136, 80, 25);
		getContentPane().add(lblDireccion);
		
		
		puestotxt = new JTextField();
		puestotxt.setBounds(93, 284, 164, 27);
		getContentPane().add(puestotxt);
		puestotxt.setColumns(10);
		
		direcciontxt = new JTextField();
		direcciontxt.setBounds(93, 136, 355, 25);
		getContentPane().add(direcciontxt);
		direcciontxt.setColumns(10);
		
		final JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(340, 187, 58, 20);
		getContentPane().add(lblTelefono);
		
		telefonotxt = new JTextField();
		telefonotxt.setBounds(397, 185, 187, 25);
		getContentPane().add(telefonotxt);
		telefonotxt.setColumns(10);
		
		dnitxt = new JTextField();
		dnitxt.setBounds(63, 93, 164, 25);
		getContentPane().add(dnitxt);
		dnitxt.setColumns(10);
		
		nombretxt = new JTextField();
		nombretxt.setBounds(398, 92, 186, 25);
		getContentPane().add(nombretxt);
		nombretxt.setColumns(10);
		
		mailtxt = new JTextField();
		mailtxt.setBounds(93, 185, 214, 25);
		getContentPane().add(mailtxt);
		mailtxt.setColumns(10);
		telefonotxt.setColumns(10);
		
		contratxt = new JPasswordField();
		contratxt.setBounds(93, 237, 165, 25);
		getContentPane().add(contratxt);
		
		
		fechaNaci = new JDateChooser();
		fechaNaci.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		fechaNaci.setBounds(121, 333, 162, 25);
		getContentPane().add(fechaNaci);
		
		final JLabel lblFechaNac = new JLabel("Fecha nacimiento:");
		lblFechaNac.setBounds(19, 333, 109, 25);
		getContentPane().add(lblFechaNac);
		
		final JLabel lblFechaEmpl = new JLabel("Fecha empleado:");
		lblFechaEmpl.setBounds(303, 333, 109, 25);
		getContentPane().add(lblFechaEmpl);
		
		JDateChooser fechaContratado = new JDateChooser();
		fechaContratado.setBounds(422, 333, 162, 25);
		getContentPane().add(fechaContratado);
		
		JDateChooser dateChooserFechaNac = new JDateChooser();
		dateChooserFechaNac.setBounds(121, 333, 163, 25);
		getContentPane().add(dateChooserFechaNac);
		
		JDateChooser dateChooserFechaEmpleado = new JDateChooser();
		dateChooserFechaEmpleado.setBounds(422, 333, 163, 25);
		getContentPane().add(dateChooserFechaEmpleado);
		
		final JLabel lblrepetircontra = new JLabel("Repetir contraseña:");
		lblrepetircontra.setBounds(282, 237, 126, 25);
		getContentPane().add(lblrepetircontra);
		
		repetirContratxt = new JPasswordField();
		repetirContratxt.setBounds(403, 237, 181, 25);
		getContentPane().add(repetirContratxt);
		
		
		JButton btnregistrar = new JButton("Registrarse");
		btnregistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(empleadoExistente() == true) {
						JOptionPane.showMessageDialog(null, "El empleado con el DNI introducido ya tiene una cuenta creada", "ERROR.", JOptionPane.ERROR_MESSAGE);
					}else if(dnitxt.getText().length() == 0 || nombretxt.getText().length() == 0 
							|| direcciontxt.getText().length() == 0 || telefonotxt.getText().length() == 0 
							|| puestotxt.getText().length() == 0 || mailtxt.getText().length() == 0
							|| contratxt.getPassword().length == 0 || repetirContratxt.getPassword().length == 0
							|| fechaNaci.getDate() == null) {
						
						JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos obligatorios para crear la cuenta", "Error", JOptionPane.ERROR_MESSAGE);
						lblDni.setForeground(Color.RED); lblNombre.setForeground(Color.RED); lblEmail.setForeground(Color.RED); lblContrasena.setForeground(Color.RED); 
						lblrepetircontra.setForeground(Color.RED); lblDireccion.setForeground(Color.RED); lblPuesto.setForeground(Color.RED); lblTelefono.setForeground(Color.RED);
						lblFechaNac.setForeground(Color.RED); lblFechaEmpl.setForeground(Color.RED);
						
					}else {
						addUserEmpleado();		
						
						LoginEmpleado log = new LoginEmpleado();
						log.setVisible(true);
						setVisible(false);
					}					
				} catch (DBException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ya existe un empleado con este DNI");
				}				
			}		
		});
		btnregistrar.setForeground(Color.WHITE);
		btnregistrar.setBackground(new Color(72, 61, 139));
		btnregistrar.setBounds(235, 385, 141, 36);
		getContentPane().add(btnregistrar);

		JButton cerrar = new JButton("Cerrar");
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(72, 61, 139));
		cerrar.setBounds(517, 449, 80, 31);
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
		volver.setBounds(10, 449, 80, 31);
		getContentPane().add(volver);
		
	}


   // EnumPuestoEmpleados empl = Enum.Parse(typeof(EnumPuestoEmpleados), textFieldPuesto.getText());

			private boolean empleadoExistente() {
				DBManager manager = DBManager.getInstance();
				List<Empleado> empleados = manager.getEmpleados();
				try {
					manager.connect();
					for (Empleado empleado : empleados) {
						if(empleado.getDni() == dnitxt.getText()) {
							return true;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				return false;
				
			}
    
			private void addUserEmpleado() throws DBException {
				DBManager manager = DBManager.getInstance();
				try {
					manager.connect();
					
					Empleado e = new Empleado(
							dnitxt.getText(),
							nombretxt.getText(),
							direcciontxt.getText(),
							mailtxt.getText(),
							telefonotxt.getText(),
							String.valueOf(contratxt.getText())					
						
					);
					System.out.println(e);
					manager.store(e);
					manager.disconnect();
				}catch (DBException de) {
					manager.disconnect();
					throw de;
				}
			}
}