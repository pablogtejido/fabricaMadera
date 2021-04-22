package com.atenea.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.atenea.data.Cliente;
import com.atenea.data.ClienteConectado;
import com.atenea.data.Empleado;
import com.atenea.data.EmpleadoConectado;
import com.atenea.db.DBException;
import com.atenea.db.DBManager;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textDni;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginCliente frame = new LoginCliente();
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
	public LoginCliente() {
		this.setTitle("Iniciar sesión Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 300);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(new Color(0, 128, 128));
		panel.setBackground(new Color(72, 61, 139));
		panel.setBounds(0, 0, 607, 43);
		contentPane.add(panel);
		
		JLabel login = new JLabel("LOGIN");
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setForeground(Color.WHITE);
		login.setBounds(270, 11, 77, 32);
		panel.add(login);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(170, 88, 63, 20);
		contentPane.add(lblDni);
		
		textDni = new JTextField();
		textDni.setBounds(251, 88, 141, 20);
		contentPane.add(textDni);
		textDni.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(170, 127, 80, 20);
		contentPane.add(lblContrasena);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(251, 127, 141, 20);
		contentPane.add(passwordField);
		
		JButton registrarse = new JButton("Registrarse");
		registrarse.setForeground(Color.WHITE);
		registrarse.setBackground(new Color(72, 61, 139));
		registrarse.setBounds(10, 219, 103, 31);
		contentPane.add(registrarse);
		
		registrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistroCliente rg = new RegistroCliente();
				rg.setVisible(true);
				setVisible(false);
			}
		});
		JButton btniniciar = new JButton("Iniciar");
		btniniciar.setForeground(Color.WHITE);
		btniniciar.setBackground(new Color(72, 61, 139));
		btniniciar.setBounds(263, 174, 104, 37);
		contentPane.add(btniniciar);
		btniniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager db = DBManager.getInstance();

				try {
					Cliente userCliente = db.getCliente(textDni.getText());
					System.out.println(userCliente);
				
					if (userCliente == null)
						JOptionPane.showMessageDialog(null, "Cliente no encontrado");
					else {
						if (!Arrays.equals(userCliente.getContrasena().toCharArray(), passwordField.getPassword()))
							JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
						else {
							if (ClienteConectado.getUserCliente().isEmpty()) {
								ClienteConectado.getUserCliente().add(userCliente);

							} else {
								for (Cliente c : ClienteConectado.getUserCliente()) {
									if (userCliente.equals(c)) {
										JOptionPane.showMessageDialog(null, "Este Cliente ya esta conectado");
										textDni.setText("");
										passwordField.setText("");
									} else {
										ClienteConectado.getUserCliente().add(userCliente);

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
		JButton cerrar = new JButton("Cerrar");
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(72, 61, 139));
		cerrar.setBounds(517, 219, 80, 31);
		contentPane.add(cerrar);
		
		cerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}
}
