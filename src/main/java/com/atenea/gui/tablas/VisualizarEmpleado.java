package com.atenea.gui.tablas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.atenea.data.Empleado;
import com.atenea.data.Factura;
import com.atenea.rsh.EmpleadoRSH;
import com.atenea.rsh.FacturaRSH;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VisualizarEmpleado extends JFrame {
	private JTable table;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarEmpleado window = new VisualizarEmpleado();
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
	public VisualizarEmpleado() {
		initialize();
		//System.out.println();
		//System.out.println(calcularTiempoEmpresa());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Visualizar Empleados");

		setBounds(100, 100, 827, 461);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel titulopanel = new JPanel();
		titulopanel.setBounds(0, 0, 811, 58);
		titulopanel.setBackground(new Color(72, 61, 139));
		getContentPane().add(titulopanel);
		titulopanel.setLayout(null);

		JLabel titulo = new JLabel("EMPLEADOS");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 11, 811, 36);
		titulopanel.add(titulo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 69, 751, 266);
		getContentPane().add(scrollPane);

		JPanel panelBoton = new JPanel();
		panelBoton.setBounds(270, 346, 247, 43);
		getContentPane().add(panelBoton);
		panelBoton.setLayout(null);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(new Color(72, 61, 139));
		btnModificar.setBounds(37, 11, 95, 23);
		panelBoton.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(new Color(72, 61, 139));
		btnEliminar.setBounds(142, 11, 95, 23);
		panelBoton.add(btnEliminar);

		table = new JTable();
		modelo = new DefaultTableModel();
		table.setModel(modelo);

		modelo.addColumn("Dni");
		modelo.addColumn("Nombre");
		modelo.addColumn("Direccion");
		modelo.addColumn("Email");
		modelo.addColumn("Telefono");
		modelo.addColumn("Puesto");
		// modelo.addColumn("Fecha de nacimiento");
		// modelo.addColumn("Fecha de empleado");
		// modelo.addColumn("Sueldo");
		// modelo.addColumn("Contraseña");

		table.getTableHeader().setReorderingAllowed(false);

		scrollPane.setViewportView(table);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFacturas = new JMenu("Facturas");
		menuBar.add(menuFacturas);

		JMenu menuClientes = new JMenu("Clientes");
		menuBar.add(menuClientes);
		
		JMenuItem verFacturas = new JMenuItem("Ver Facturas");
		menuFacturas.add(verFacturas);//TODO

		JMenuItem verClientes = new JMenuItem("Ver Clientes");
		menuClientes.add(verClientes);
		
		verFacturas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VisualizarFacturas window2 = new VisualizarFacturas();
				window2.setVisible(true);
				setVisible(false);
			}
		});
		
		verClientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VisualizarClientes window2 = new VisualizarClientes();
				window2.setVisible(true);
				setVisible(false);
			}
		});

		btnEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dni = (String) modelo.getValueAt(table.getSelectedRow(), 0);
				Empleado fa = null;
				EmpleadoRSH rs = EmpleadoRSH.getInstance();
				for (Empleado f : rs.verEmpleados()) {
					if (f.getDni().equals(dni)) {
						System.out.println(f);
						fa = f;
					}
				}
				System.out.println("Borrando factura");
				rs.borrarEmpleado(fa);
				modelo.setRowCount(0);
				EmpleadosJTable();
			}

		});
		EmpleadosJTable();
	}

	private void EmpleadosJTable() {
		EmpleadoRSH rsh = EmpleadoRSH.getInstance();
		List<Empleado> empleados = rsh.verEmpleados();
		String[] fila = new String[6];
		System.out.println(empleados);
	
		for (Empleado empl : empleados) {
			/*
			 * modelo.addColumn("Fecha de nacimiento");
			 * modelo.addColumn("Fecha de empleado"); modelo.addColumn("Sueldo");
			 * modelo.addColumn("Contraseña");
			 */
			fila[0] = empl.getDni();
			fila[1] = empl.getNombre();
			fila[2] = empl.getDireccion();
			fila[3] = empl.getEmail();
			fila[4] = empl.getTelefono();
			fila[5] = String.valueOf(empl.getPuesto());
			/*
			 * Date fecha = empl.getFcha_nacimiento(); System.out.println("/n");
			 * System.out.println(fecha); fila[6] = fecha.toString(); //fila[7] =
			 * String.valueOf(empl.getFcha_empleado()); //fila[8] =
			 * String.valueOf(empl.getSueldo()); //fila[9] =
			 * String.valueOf(empl.getContrasena());
			 */

			fila[5] = String.valueOf(empl.getPuesto());	
			
			//TODO meter campo timepo en empresa calculado
			
			/*Date fecha = empl.getFcha_nacimiento();
			/*System.out.println("/n");
			System.out.println(fecha);
			fila[6] = fecha.toString();
			//fila[7] = String.valueOf(empl.getFcha_empleado());
			//fila[8] = String.valueOf(empl.getSueldo());
			//fila[9] = String.valueOf(empl.getContrasena());*/

			modelo.addRow(fila);

		}
	}
	//TODO no se que falla
	/*public String calcularTiempoEmpresa() {
		Date actual = new Date();
		Date inicio = new Date("28/02/2020 01:09:07");
		String texto;
		
		int mesesRest = inicio.getMonth() - actual.getMonth() ;
		
		int anyos = mesesRest / 12;
		int meses = mesesRest % 12;
		
		if(anyos < 1) {
			texto = meses + " meses.";
		} else if (meses < 1 && anyos < 1){
			texto = "Unos pocos dias";
		} else {
			texto = anyos + " años, " + meses + " meses.";;
		}
		 
		return texto;
		
	}*/

	public boolean isCellEditable(int row, int column) {
		return false;
	}
}