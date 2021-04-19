package com.example.data;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoConectado {

	private static List<Empleado> users = new ArrayList<Empleado>();

	public static List<Empleado> getUserEmpleado() {
		return users;
	}

	public static void addUserEmpleado(Empleado u) {
		users.add(u);
	}
}
