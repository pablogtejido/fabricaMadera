package com.example;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.example.db.DBException;
import com.example.db.DBManager;
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(72, 61, 139));
		panel.setForeground(new Color(0, 128, 128));
		panel.setBounds(0, 21, 615, 43);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setBounds(268, 11, 77, 32);
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
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 128));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager db = new DBManager();
				
				try {
					db.connect();
					Empleado userEmpleado = db.getEmpleadoPorEmail(textEmpleado.getText());
					System.out.println(userEmpleado);
					db.disconnect();

					if(userEmpleado == null)
						JOptionPane.showMessageDialog(null, "Empleado no encontrado");
					else {
						if(!Arrays.equals(userEmpleado.getContrasena().toCharArray(), contrasenaField.getPassword()))
							JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
						else {
							if(EmpleadoConectado.getUserEmpleado().isEmpty()) {
								EmpleadoConectado.getUserEmpleado().add(userEmpleado);
							
							}else {
								for(Empleado u: EmpleadoConectado.getUserEmpleado()) {
									if(userEmpleado.equals(u)) {
										JOptionPane.showMessageDialog(null, "Este empleado ya esta conectado");
										textEmpleado.setText("");
										contrasenaField.setText("");
									}else {
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
		
		btnNewButton.setBounds(265, 202, 89, 23);
		contentPane.add(btnNewButton);
	}
}
