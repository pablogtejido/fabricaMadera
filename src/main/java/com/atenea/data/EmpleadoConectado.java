package com.atenea.data;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoConectado {

	private static List<Empleado> users = new ArrayList<Empleado>();

	/**
	 * Get empleados
     * 
     * @return lista de clientes
	 */
	
	public static List<Empleado> getUserEmpleado() {
		return users;
	}

	public static void addUserEmpleado(Empleado u) {
		users.add(u);
	}
}
