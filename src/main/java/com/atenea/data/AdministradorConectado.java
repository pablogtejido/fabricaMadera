package com.atenea.data;

import java.util.ArrayList;
import java.util.List;

public class AdministradorConectado {
	private static List<Administrador> users = new ArrayList<Administrador>();

	/**
	 * Get administradores
     * 
     * @return lista de administradores
	 */
	public static List<Administrador> getUserAdmin() {
		return users;
	}
	/**
	 * Insertar administradores
	 * 
	 */
	public static void addUserAdmin(Administrador ad) {
		users.add(ad);
	}
}
