package com.atenea.gui;

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

import com.atenea.data.Cliente;
import com.atenea.rsh.ClienteRSH;

public class ModificarCliente extends JFrame{
	
	private JPanel contentPane;
	private JTextField textFieldDNI;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JPasswordField passwordField;
	/*private static Cliente clienteMain;
	

	
	public static void main(String[] args) {
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarCliente window = new ModificarCliente(clienteMain);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	public ModificarCliente(final Cliente cliente) {
		setTitle("Modificar Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 375);
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

		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(208, 85, 57, 13);
		contentPane.add(lblDNI);
		
		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(285, 82, 96, 19);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);
		textFieldDNI.setText(cliente.getDni());
		textFieldDNI.setEditable(false);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(208, 116, 57, 13);
		contentPane.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(285, 113, 96, 19);
		contentPane.add(textFieldNombre);
		textFieldNombre.setText(cliente.getNombre());

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(208, 145, 57, 13);
		contentPane.add(lblApellidos);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(285, 142, 96, 19);
		contentPane.add(textFieldApellidos);
		textFieldApellidos.setText(cliente.getApellidos());

		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(208, 175, 76, 14);
		contentPane.add(lblContrasena);

		passwordField = new JPasswordField();
		passwordField.setBounds(285, 172, 96, 20);
		contentPane.add(passwordField);
		passwordField.setText(cliente.getContrasena());
		

		JButton volver = new JButton("Volver");
		volver.setForeground(Color.WHITE);
		volver.setBackground(new Color(72, 61, 139));
		volver.setBounds(10, 294, 80, 31);
		contentPane.add(volver);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClientesEmpleado clientes = new ClientesEmpleado();
				clientes.setVisible(true);
				setVisible(false);
			}
		});

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(new Color(72, 61, 139));
		btnModificar.setBounds(235, 230, 141, 36);
		contentPane.add(btnModificar);

		JButton cerrar = new JButton("Cerrar");
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(72, 61, 139));
		cerrar.setBounds(517, 294, 80, 31);
		contentPane.add(cerrar);
		cerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nombre = textFieldNombre.getText();
				String apellidos = textFieldApellidos.getText();
				String contrasena = passwordField.getText(); // FIXME: Hay que arreglar esto.
				
				cliente.setNombre(nombre);
				cliente.setApellidos(apellidos);
				cliente.setContrasena(contrasena);
				
				ClienteRSH.getInstance().modificarCliente(cliente);
				System.out.println(cliente);

			}

		});
	}

}
