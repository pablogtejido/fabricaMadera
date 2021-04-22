package com.atenea.data;

import java.util.ArrayList;
import java.util.List;

public class ClienteConectado {
	private static List<Cliente> users = new ArrayList<Cliente>();

	public static List<Cliente> getUserCliente() {
		return users;
	}

	public static void addUserCliente(Cliente c) {
		users.add(c);
	}
}
