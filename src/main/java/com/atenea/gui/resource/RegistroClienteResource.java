package com.atenea.gui.resource;

import javax.swing.JOptionPane;

import com.atenea.data.Cliente;
import com.atenea.db.DBException;
import com.atenea.db.DBManager;


public class RegistroClienteResource {

	public static void registro(String dni, String nombre, String apellidos, String contrasena) {
		DBManager db = DBManager.getInstance();
		
		try {
			Cliente userCliente = db.getCliente(dni);
			System.out.println(userCliente);
			if(userCliente == null) {
				Cliente newCliente = new Cliente(dni, nombre, apellidos, contrasena);
				db.store(newCliente);
			} else {
				JOptionPane.showMessageDialog(null, "Cliente ya existente");
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		
	}
}
