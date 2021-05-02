package com.atenea.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atenea.data.Administrador;
import com.atenea.data.Cliente;
import com.atenea.data.Empleado;
import com.atenea.data.EnumPuestoEmpleados;
import com.atenea.data.Factura;
import com.atenea.data.Producto;
import com.atenea.db.DBManager;

public class PrepareData {

	public static void main(String[] args) {

		Empleado empleado1 = new Empleado("68432155O", "Paco Lopez", "Calle Ave del Paraiso, 13, 4Izq",
				"pacolopez@gmail.com", "654321789", EnumPuestoEmpleados.OPERARIO, new Date("12/04/1990 15:30:35"),
				new Date("08/07/2020 00:00:00"), 1200.50, "1234");
		DBManager.getInstance().store(empleado1);

		Empleado empleado2 = new Empleado("35661232P", "Aitor Ruiz Garcia", "Calle Zorroza, 11, 1B",
				"aitoruiz@gmail.com", "621852478", EnumPuestoEmpleados.DIRECTIVA, new Date("10/01/1970 01:09:07"),
				new Date("30/06/2000 18:00:35"), 4300, "1234");
		DBManager.getInstance().store(empleado2);

		/*
		 * Empleado empleado3 = new Empleado("22396365I", "Pablo Gonzalez Tejido",
		 * "Calle Leioa, 42, 3B", "pablogtejido@gmail.com", "753123258",
		 * EnumPuestoEmpleados.DIRECTIVA, new Date("25/12/1983 08:56:21"), new
		 * Date("27/02/2005 11:12:45"), 4300, "1234");
		 * DBManager.getInstance().store(empleado3);
		 * 
		 * Empleado empleado4 = new Empleado("42134567U", "Koldo Moya",
		 * "Calle Sestao, 2, 1B", "koldo.moya@gmail.com", "673258014",
		 * EnumPuestoEmpleados.DIRECTIVA, new Date("27/10/1974 05:04:00"), new
		 * Date("29/01/2007 12:00:00"), 4300, "1234");
		 * DBManager.getInstance().store(empleado4);
		 * 
		 * Empleado empleado5 = new Empleado("75420903Y", "Nerea Diez Landin",
		 * "Calle Portu, 57, 2B", "nereadiez@gmail.com", "658007220",
		 * EnumPuestoEmpleados.DIRECTIVA, new Date("14/05/1988 19:27:31"), new
		 * Date("11/09/2012 10:14:11"), 4300, "1234");
		 * DBManager.getInstance().store(empleado5);
		 */

		Cliente cliente1 = new Cliente("58441139A", "Juan", "Lopez", "1234");
		DBManager.getInstance().store(cliente1);

		/*
		 * Cliente cliente2 = new Cliente("36957841K", "Silvia", "Montejo", "1234");
		 * DBManager.getInstance().store(cliente2);
		 * 
		 * Cliente cliente3 = new Cliente("79845211T", "Luis", "Alonso", "1234");
		 * DBManager.getInstance().store(cliente3);
		 * 
		 * Cliente cliente4 = new Cliente("66558842G", "Silvia", "Montejo", "1234");
		 * DBManager.getInstance().store(cliente4);
		 * 
		 * Cliente cliente5 = new Cliente("45548696P", "Luis", "Alonso", "1234");
		 * DBManager.getInstance().store(cliente5);
		 */

		Producto p1 = new Producto("Tablero madera", 12.70, 32, 24, 4, true);
		DBManager.getInstance().store(p1);

		Producto p2 = new Producto("Listón madera", 7.50, 21, 30, 9, false);
		DBManager.getInstance().store(p2);

		/*
		 * Producto p3 = new Producto("Revestimiento madera", 21.60, 8, 1, 50, 2, true);
		 * DBManager.getInstance().store(p3);
		 * 
		 * Producto p4 = new Producto("Celosía madera", 40, 2, 1, 220, 80, false);
		 * DBManager.getInstance().store(p4);
		 * 
		 * Producto p5 = new Producto("Celosía madera", 40, 2, 1, 220, 80, false);
		 * DBManager.getInstance().store(p5);
		 * 
		 * Administrador a1 = new Administrador("1234", "Paco", "Salas",
		 * "pacosalas@gmail.com", "682548111"); DBManager.getInstance().store(a1);
		 * 
		 * Administrador a2 = new Administrador("1234", "Ane", "Casas",
		 * "anetxu303@gmail.com", "685455236"); DBManager.getInstance().store(a2);
		 * 
		 * Administrador a3 = new Administrador("1234", "Ainhoa", "Velasco",
		 * "ainhoa585@gmail.com", "699958457"); DBManager.getInstance().store(a3);
		 */

		List<Producto> lista1 = new ArrayList<>();

		Producto p1Factura = new Producto("Tablero madera", 12.70, 32, 24, 4, true);

		Producto p2Factura = new Producto("Listón madera", 7.50, 21, 30, 9, false);

		lista1.add(p1Factura);
		lista1.add(p2Factura);

		Empleado empleadoFactura = new Empleado("68432155P", "Emilio Cansino", "Calle Ave del Paraiso, 13, 4Izq",
				"emiliocansino@gmail.com", "654321789", EnumPuestoEmpleados.OPERARIO, new Date("12/04/1980 15:30:35"),
				new Date("08/07/2020 00:00:00"), 1200.50, "1234");

		Cliente clienteFactura = new Cliente("58441139Q", "Paquita", "Salas", "1234");

		Factura f1 = new Factura(empleadoFactura, clienteFactura, lista1, new Date("27/10/1974 05:04:00"));
		DBManager.getInstance().store(f1);
	}
}