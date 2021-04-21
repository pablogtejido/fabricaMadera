package com.atenea.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.atenea.gui.resource.ClienteResource;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JList;

public class VentanaCliente extends JFrame{
	
	private JPanel contentPane;
	private JTextField textFieldDNI;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	
	public VentanaCliente() {
		
		setTitle("Cliente");
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
		textFieldDNI.setBounds(98, 82, 96, 19);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(21, 85, 57, 13);
		contentPane.add(lblDNI);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(21, 116, 57, 13);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(98, 113, 96, 19);
		contentPane.add(textFieldNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(21, 145, 57, 13);
		contentPane.add(lblApellidos);
		
		textFieldApellidos = new JTextField();
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(98, 142, 96, 19);
		contentPane.add(textFieldApellidos);
		
		JButton btnRegistrar = new JButton("Registrar Cliente");
		btnRegistrar.setBounds(49, 199, 135, 21);
		contentPane.add(btnRegistrar);
		
		btnRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String dni = textFieldDNI.getText();
				String nombre = textFieldNombre.getText();
				String apellidos = textFieldApellidos.getText();
				
				ClienteResource.registro(dni, nombre, apellidos);
				
			}
			
		});
	}
}
