package com.atenea.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registrarse extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrarse window = new Registrarse();
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
	public Registrarse() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 610, 387);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel titulopanel = new JPanel();
		titulopanel.setBounds(0, 0, 597, 58);
		titulopanel.setBackground(new Color(72, 61, 139));
		getContentPane().add(titulopanel);
		titulopanel.setLayout(null);

		JLabel titulo = new JLabel("REGISTRARSE");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titulo.setBounds(0, 11, 597, 36);
		titulopanel.add(titulo);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);

		textField = new JTextField();
		textField.setBounds(83, 81, 164, 31);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel nombre = new JLabel("Nombre:");
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setBounds(10, 81, 74, 31);
		getContentPane().add(nombre);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(379, 81, 164, 31);
		getContentPane().add(textField_1);

		JLabel apellido = new JLabel("Apellido:");
		apellido.setHorizontalAlignment(SwingConstants.CENTER);
		apellido.setBounds(304, 81, 76, 31);
		getContentPane().add(apellido);

		JLabel mail = new JLabel("Email:");
		mail.setHorizontalAlignment(SwingConstants.CENTER);
		mail.setBounds(10, 150, 74, 31);
		getContentPane().add(mail);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(83, 144, 253, 42);
		getContentPane().add(textField_2);

		JLabel contra = new JLabel("Contraseña:");
		contra.setHorizontalAlignment(SwingConstants.CENTER);
		contra.setBounds(10, 214, 74, 31);
		getContentPane().add(contra);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(83, 214, 151, 31);
		getContentPane().add(textField_3);

		JLabel comprobar = new JLabel("Comprobar contraseña:");
		comprobar.setHorizontalAlignment(SwingConstants.CENTER);
		comprobar.setBounds(250, 214, 138, 31);
		getContentPane().add(comprobar);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(392, 214, 151, 31);
		getContentPane().add(textField_4);

		JButton btnregistrar = new JButton("Registrarse");
		btnregistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnregistrar.setForeground(Color.WHITE);
		btnregistrar.setBackground(new Color(0, 0, 128));
		btnregistrar.setBounds(230, 269, 134, 36);
		getContentPane().add(btnregistrar);

		JButton cerrar = new JButton("Cerrar");
		cerrar.setForeground(Color.WHITE);
		cerrar.setBackground(new Color(0, 0, 128));
		cerrar.setBounds(504, 306, 80, 31);
		getContentPane().add(cerrar);
		cerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		JButton volver = new JButton("Volver");
		volver.setForeground(Color.WHITE);
		volver.setBackground(new Color(0, 0, 128));
		volver.setBounds(10, 310, 80, 31);
		getContentPane().add(volver);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Login log = new Login();
				log.setVisible(true);
				setVisible(false);
			}
		});



	}
}
