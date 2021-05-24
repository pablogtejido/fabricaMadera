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

/**
 * Ventana de inicio donde los administradores y empleados accederan a iniciar
 * sesión tan solo pulsando un boton
 */
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// JButton para Iniciar SesiÃ³n
		JButton btnLogInEmpleado = new JButton("Iniciar sesion como empleado o administrador");
		btnLogInEmpleado.setForeground(Color.WHITE);
		btnLogInEmpleado.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
		btnLogInEmpleado.setBackground(new Color(72, 61, 139));
		btnLogInEmpleado.setBounds(130, 265, 490, 29);
		contentPane.add(btnLogInEmpleado);

		btnLogInEmpleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginEmpleado em = new LoginEmpleado();
				em.setVisible(true);
				frame.setVisible(false);

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
