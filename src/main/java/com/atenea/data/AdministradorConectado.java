package com.atenea.data;

import java.util.ArrayList;
import java.util.List;

public class AdministradorConectado {
	private static List<Administrador> users = new ArrayList<Administrador>();

	public static List<Administrador> getUserAdmin() {
		return users;
	}

	public static void addUserAdmin(Administrador ad) {
		users.add(ad);
	}
}
