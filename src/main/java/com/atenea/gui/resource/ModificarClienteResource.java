package com.atenea.gui.resource;

import com.atenea.data.Cliente;
import com.atenea.db.DBException;
import com.atenea.db.DBManager;

public class ModificarClienteResource {
	
	public static void modificar(String dni, String nombre, String apellidos, String contrasena) {
		try {
			Cliente clienteBD = DBManager.getInstance().getCliente(dni);
			clienteBD.setNombre(nombre);
			clienteBD.setApellidos(apellidos);
			clienteBD.setContrasena(contrasena);
			DBManager.getInstance().updateCliente(clienteBD);
		} catch (DBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
