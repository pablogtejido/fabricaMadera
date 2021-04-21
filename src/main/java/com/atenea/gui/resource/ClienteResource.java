package com.atenea.gui.resource;

import javax.swing.JOptionPane;

import com.atenea.data.Cliente;
import com.atenea.db.DBException;
import com.atenea.db.DBManager;


public class ClienteResource {

	public static void registro(String dni, String nombre, String apellidos) {
		DBManager db = DBManager.getInstance();
		
		try {
			db.connect();
			Cliente userCliente = db.getClientePorDNI(dni);
			System.out.println(userCliente);
			db.disconnect();
			
			if(userCliente == null) {
				Cliente newCliente = new Cliente(dni, nombre, apellidos);
				db.store(newCliente);
			} else {
				JOptionPane.showMessageDialog(null, "Cliente ya existente");
			}
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
