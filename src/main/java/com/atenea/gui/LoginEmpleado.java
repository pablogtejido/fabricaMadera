package com.atenea.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.atenea.data.Empleado;
import com.atenea.data.EmpleadoConectado;
import com.atenea.db.DBException;
import com.atenea.db.DBManager;
import com.atenea.gui.tablas.VisualizarFacturas;
import com.atenea.rsh.EmpleadoRSH;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

public class LoginEmpleado extends JFrame {

	private JPanel contentPane;
	private JTextField textEmpleado;
	private JPasswordField contrasenaField;
	private final JLabel lblContrasena = new JLabel("Contraseña:");

	/**
	 * Create the frame.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginEmpleado frame = new LoginEmpleado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginEmpleado() {
		this.setTitle("Iniciar sesión Empleado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 300);
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

		JLabel login = new JLabel("LOGIN");
		login.setBounds(268, 11, 77, 32);
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setForeground(SystemColor.text);
		panel.add(login);

		JLabel lblEmpleado = new JLabel("Email empleado:");
		lblEmpleado.setBounds(107, 107, 103, 14);
		contentPane.add(lblEmpleado);

		textEmpleado = new JTextField();
		textEmpleado.setBounds(220, 104, 197, 20);
		contentPane.add(textEmpleado);
		textEmpleado.setColumns(10);

		contrasenaField = new JPasswordField();
		contrasenaField.setBounds(220, 149, 197, 20);
		contentPane.add(contrasenaField);
		lblContrasena.setBounds(107, 144, 72, 31);
		contentPane.add(lblContrasena);

		JButton btniniciar = new JButton("Iniciar");
		btniniciar.setForeground(new Color(255, 255, 255));
		btniniciar.setBackground(new Color(72, 61, 139));
		btniniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Empleado empleado = null;
					boolean found = false;
					List<Empleado> listadoEmpleado = EmpleadoRSH.getInstance().verEmpleados();
					for (Empleado emp : listadoEmpleado) {
						if (emp.getEmail().equals(textEmpleado.getText())) {
							System.out.println(emp);
							empleado = emp;
							found = true;
						}
					}

					if (!found)
						JOptionPane.showMessageDialog(null, "Empleado no encontrado");
					else {
						if (!Arrays.equals(empleado.getContrasena().toCharArray(), contrasenaField.getPassword()))
							JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
						else {
							if (EmpleadoConectado.getUserEmpleado().isEmpty()) {
								EmpleadoConectado.getUserEmpleado().add(empleado);
								setVisible(false);
								new VisualizarFacturas().setVisible(true);
							} else {
								for (Empleado u : EmpleadoConectado.getUserEmpleado()) {
									if (empleado.equals(u)) {
										JOptionPane.showMessageDialog(null, "Este empleado ya esta conectado");
										textEmpleado.setText("");
										contrasenaField.setText("");
									} else {
										EmpleadoConectado.getUserEmpleado().add(empleado);
										setVisible(false);
										new VisualizarFacturas().setVisible(true);
									}
								}
							}
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btniniciar.setBounds(253, 188, 103, 37);
		contentPane.add(btniniciar);

		JButton cerrar = new JButton("Cerrar");
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(72, 61, 139));
		cerrar.setBounds(525, 219, 80, 31);
		contentPane.add(cerrar);
		cerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JButton registrarse = new JButton("Registrarse");
		registrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistroEmpleado rg = new RegistroEmpleado();
				rg.setVisible(true);
				setVisible(false);
			}
		});
		registrarse.setForeground(Color.WHITE);
		registrarse.setBackground(new Color(72, 61, 139));
		;
		registrarse.setBounds(10, 219, 103, 31);
		contentPane.add(registrarse);
	}
}