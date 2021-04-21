package com.atenea.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRSH {
	public static Cliente toUser(ResultSet rs) {
		Cliente userCliente = null;
	
		try {
			if (rs.next()) {
				userCliente = new Cliente();
	
				userCliente.setDni(rs.getString("DNI"));
				userCliente.setNombre(rs.getString("NOMBRE"));
				userCliente.setApellidos(rs.getString("APELLIDOS"));
	
	
			}
		} catch (SQLException se) {
			System.out.println("No se ha podido encontrar al usuario");
		}

		return userCliente;
	}
}
