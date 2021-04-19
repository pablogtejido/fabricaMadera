package com.example.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.atenea.data.Empleado;
import com.atenea.data.EmpleadoConectado;
import com.atenea.db.DBException;
import com.atenea.db.DBManager;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textEmpleado;
	private JPasswordField contrasenaField;
	private final JLabel lblContrasena = new JLabel("Contraseña:");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
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
		panel.setBounds(0, 0, 615, 57);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(0, 11, 615, 32);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(SystemColor.text);
		panel.add(lblNewLabel);

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

		JButton btnlogin = new JButton("Iniciar");
		btnlogin.setForeground(new Color(255, 255, 255));
		btnlogin.setBackground(new Color(0, 0, 128));
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager db = new DBManager();

				try {
					db.connect();
					Empleado userEmpleado = db.getEmpleadoPorEmail(textEmpleado.getText());
					System.out.println(userEmpleado);
					db.disconnect();

					if (userEmpleado == null)
						JOptionPane.showMessageDialog(null, "Empleado no encontrado");
					else {
						if (!Arrays.equals(userEmpleado.getContrasena().toCharArray(), contrasenaField.getPassword()))
							JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
						else {
							if (EmpleadoConectado.getUserEmpleado().isEmpty()) {
								EmpleadoConectado.getUserEmpleado().add(userEmpleado);

							} else {
								for (Empleado u : EmpleadoConectado.getUserEmpleado()) {
									if (userEmpleado.equals(u)) {
										JOptionPane.showMessageDialog(null, "Este empleado ya esta conectado");
										textEmpleado.setText("");
										contrasenaField.setText("");
									} else {
										EmpleadoConectado.getUserEmpleado().add(userEmpleado);

									}
								}
							}
						}
					}
				} catch (DBException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnlogin.setBounds(247, 189, 121, 31);
		contentPane.add(btnlogin);
		
		JButton cerrar = new JButton("Cerrar");
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(0, 0, 128));
		cerrar.setBounds(525, 219, 80, 31);
		contentPane.add(cerrar);
		cerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		JButton registrarse = new JButton("Registrarse");
		registrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registrarse rg = new Registrarse();
				rg.setVisible(true);
				setVisible(false);
			}
		});
		registrarse.setForeground(Color.WHITE);
		registrarse.setBackground(new Color(0, 0, 128));
		registrarse.setBounds(10, 219, 103, 31);
		contentPane.add(registrarse);
	}
}
