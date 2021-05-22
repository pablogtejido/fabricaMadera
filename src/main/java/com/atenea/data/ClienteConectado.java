package com.atenea.data;

import java.util.ArrayList;
import java.util.List;

public class ClienteConectado {
	private static List<Cliente> users = new ArrayList<Cliente>();

	/**
	 * Get clientes
     * 
     * @return lista de clientes
	 */
	
	public static List<Cliente> getUserCliente() {
		return users;
	}
	/**
	 * Insertar clientes
	 * 
	 */
	public static void addUserCliente(Cliente c) {
		users.add(c);
	}
}
