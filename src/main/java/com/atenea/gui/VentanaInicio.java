package com.atenea.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JButton btnCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaInicio() {

		initialize();
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */

	public void initialize() {
		frame = new JFrame();
		frame.setTitle("Inicio Fabrica Maderas");
		frame.setForeground(Color.BLACK);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images/Icon.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(ScreenSize.getXcoordinate(775), ScreenSize.getYcoordinate(475), 775, 475);
		frame.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		// JButton para Iniciar SesiÃ³n
		JButton btnLogInEmpleado = new JButton("Iniciar sesion como empleado");
		btnLogInEmpleado.setForeground(Color.WHITE);
		btnLogInEmpleado.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
		btnLogInEmpleado.setBackground(new Color(72, 61, 139));
		btnLogInEmpleado.setBounds(198, 266, 381, 29);
		contentPane.add(btnLogInEmpleado);

		btnLogInEmpleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginEmpleado em = new LoginEmpleado();
				em.setVisible(true);

			}

		});

		btnCliente = new JButton("Iniciar sesion como cliente");
		btnCliente.setForeground(Color.WHITE);
		btnCliente.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
		btnCliente.setBackground(new Color(72, 61, 139));
		btnCliente.setBounds(198, 382, 381, 29);
		contentPane.add(btnCliente);
		btnCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginCliente cl = new LoginCliente();
				cl.setVisible(true);

			}

		});

		// lblBackground
		JLabel lblBackground = new JLabel("");
		Image imgBackground = new ImageIcon("images/madera.jpeg").getImage();
		ImageIcon imgBackgroundScaled = new ImageIcon(imgBackground.getScaledInstance(775, 440, Image.SCALE_SMOOTH));
		lblBackground.setIcon(imgBackgroundScaled);
		lblBackground.setBounds(0, 0, 775, 440);
		contentPane.add(lblBackground);

	}
}
