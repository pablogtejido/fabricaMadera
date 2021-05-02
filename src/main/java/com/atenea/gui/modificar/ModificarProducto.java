package com.atenea.gui.modificar;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.atenea.data.Producto;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ModificarProducto extends JFrame{


	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ModificarProducto(final Producto prod) {
		setBounds(100, 100, 631, 575);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(new Color(0, 128, 128));
		panel.setBackground(new Color(72, 61, 139));
		panel.setBounds(0, 0, 615, 43);
		getContentPane().add(panel);
		
		JLabel titulo = new JLabel("MODIFICAR PRODUCTO");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(0, 0, 615, 43);
		panel.add(titulo);
		
		

	}
}
