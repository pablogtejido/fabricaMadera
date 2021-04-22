package com.atenea.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FacturasCliente extends JFrame {
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacturasCliente window = new FacturasCliente();
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
	public FacturasCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 827, 461);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel titulopanel = new JPanel();
		titulopanel.setBounds(0, 0, 811, 58);
		titulopanel.setBackground(new Color(72, 61, 139));
		getContentPane().add(titulopanel);
		titulopanel.setLayout(null);
		
		JLabel titulo = new JLabel("FACTURAS");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 11, 811, 36);
		titulopanel.add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 69, 751, 266);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFacturas = new JMenu("Facturas");
		menuBar.add(menuFacturas);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		menuBar.add(mnNewMenu_2);
	}
}
