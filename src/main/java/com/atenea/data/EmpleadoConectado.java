package com.atenea.data;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoConectado {

	private static List<Empleado> users = new ArrayList<Empleado>();

	/**
	 * Get empleados
	 * 
	 * @return lista de empleados
	 */

	public static List<Empleado> getUserEmpleado() {
		return users;
	}

	/**
	 * Insertar empleados a la lista
	 * 
	 * @param e
	 */

	public static void addUserEmpleado(Empleado e) {
		users.add(e);
	}
}
