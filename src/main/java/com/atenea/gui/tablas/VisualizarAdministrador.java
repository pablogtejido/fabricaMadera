package com.atenea.gui.tablas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.atenea.data.Administrador;
import com.atenea.data.Cliente;
import com.atenea.gui.modificar.ModificarCliente;
import com.atenea.gui.registrar.RegistroCliente;
import com.atenea.gui.registrar.RegistroEmpleado;
import com.atenea.rsh.AdministradorRSH;
import com.atenea.rsh.ClienteRSH;

public class VisualizarAdministrador extends JFrame{

	private JTable table;
	private DefaultTableModel modelo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarClientes window = new VisualizarClientes();
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
	public VisualizarAdministrador() {
		initialize();
	}

	private void initialize() {
		this.setTitle("Visualizar Administradores");
		setBounds(100, 100, 827, 461);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel titulopanel = new JPanel();
		titulopanel.setBounds(0, 0, 811, 58);
		titulopanel.setBackground(new Color(72, 61, 139));
		getContentPane().add(titulopanel);
		titulopanel.setLayout(null);
		
		JLabel titulo = new JLabel("ADMINISTRADORES");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 11, 811, 36);
		titulopanel.add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 69, 751, 266);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		modelo = new DefaultTableModel();
		table.setModel(modelo);
		
		modelo.addColumn("DNI");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Email");
		modelo.addColumn("Telefono");
		table.getTableHeader().setReorderingAllowed(false);
		
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
					
		JMenu menuEmpleados = new JMenu("Empleados");
		menuBar.add(menuEmpleados);
		
		JMenuItem verEmpleados = new JMenuItem("Ver Empleados");
		menuEmpleados.add(verEmpleados);
		JMenuItem registrarEmpleados = new JMenuItem("Registrar Empleados");
		menuEmpleados.add(registrarEmpleados);
		
	
		
		verEmpleados.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarEmpleado window2 = new VisualizarEmpleado();
				window2.setVisible(true);

			}
		});
		
		
		registrarEmpleados.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RegistroEmpleado window2 = new RegistroEmpleado();
				window2.setVisible(true);
			}
		});
		
		AdminJTable();

	}
	
	
	public void AdminJTable() {
		AdministradorRSH rsh = AdministradorRSH.getInstance();
		List<Administrador> ad = rsh.verAdministrador();
		String[] fila = new String [5];
		
		for (Administrador admin : ad) {
			
			fila[0] = admin.getDni();
			fila[1] = admin.getNombre();
			fila[2] = admin.getApellido();
			fila[3] = admin.getEmail();
			fila[4] = admin.getTelefono();
			
			modelo.addRow(fila);
			
		}
				
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
