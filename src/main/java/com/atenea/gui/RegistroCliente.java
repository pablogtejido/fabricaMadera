package com.atenea.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.atenea.gui.resource.ClienteResource;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JPasswordField;

public class RegistroCliente extends JFrame{
	
	private JPanel contentPane;
	private JTextField textFieldDNI;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JPasswordField passwordField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroCliente window = new RegistroCliente();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public RegistroCliente() {
		
		setTitle("Registro Cliente");
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
		panel.setBounds(0, 21, 615, 43);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblcliente = new JLabel("CLIENTE");
		lblcliente.setBounds(268, 11, 77, 32);
		lblcliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblcliente.setForeground(SystemColor.text);
		panel.add(lblcliente);
		
		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(285, 82, 96, 19);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(208, 85, 57, 13);
		contentPane.add(lblDNI);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(208, 116, 57, 13);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(285, 113, 96, 19);
		contentPane.add(textFieldNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(208, 145, 57, 13);
		contentPane.add(lblApellidos);
		
		textFieldApellidos = new JTextField();
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(285, 142, 96, 19);
		contentPane.add(textFieldApellidos);
		
		JButton btnRegistrar = new JButton("Registrar Cliente");
		btnRegistrar.setBounds(238, 229, 135, 21);
		contentPane.add(btnRegistrar);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(208, 169, 76, 14);
		contentPane.add(lblContrasena);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(285, 172, 96, 20);
		contentPane.add(passwordField);
		
		btnRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String dni = textFieldDNI.getText();
				String nombre = textFieldNombre.getText();
				String apellidos = textFieldApellidos.getText();
				String contrasena = passwordField.getText();
				
				ClienteResource.registro(dni, nombre, apellidos, contrasena);
				
			}
			
		});
	}
}
