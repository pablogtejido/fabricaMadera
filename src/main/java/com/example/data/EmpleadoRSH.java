package com.example.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoRSH {
	public static Empleado toUser(ResultSet rs) {
		Empleado userEmpleado = null;

		try {
			if (rs.next()) {
				userEmpleado = new Empleado();

				userEmpleado.setDni(rs.getString("DNI"));
				userEmpleado.setNombre(rs.getString("NOMBRE"));
				userEmpleado.setDireccion(rs.getString("DIRECCION"));
				userEmpleado.setEmail(rs.getString("EMAIL"));
				userEmpleado.setTelefono(rs.getString("TELEFONO"));
				/*
				 * userEmpleado.setPuesto(); userEmpleado.setFcha_nacimiento();
				 * userEmpleado.setFcha_empleado();
				 */
				userEmpleado.setSueldo(rs.getDouble("SUELDO"));
				userEmpleado.setContrasena(rs.getString("CONTRASENA"));

			}
		} catch (SQLException se) {
			System.out.println("No se ha podido encontrar al usuario");
		}

		return userEmpleado;
	}
}
