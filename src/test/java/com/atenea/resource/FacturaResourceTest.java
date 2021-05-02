package com.atenea.resource;

import categories.IntegrationTest;
import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.atenea.Main;
import com.atenea.data.Cliente;
import com.atenea.data.Empleado;
import com.atenea.data.EnumPuestoEmpleados;
import com.atenea.data.Factura;
import com.atenea.data.Producto;
import com.atenea.rsh.FacturaRSH;

import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class FacturaResourceTest {

	private static HttpServer server;
	private static FacturaRSH rsh;
	private static Factura factura1;
	private static Factura factura2;
	private static Factura factura3;
	private static Factura factura4;
	private static Factura factura5;
	private static Factura factura6;
	private static Cliente cliente1;
	private static Cliente cliente2;
	private static Cliente cliente3;
	private static Cliente cliente4;
	private static Cliente cliente5;
	private static Cliente cliente6;
	private static Producto producto1;
	private static Producto producto2;
	private static Producto producto3;
	private static Producto producto4;
	private static Producto producto5;
	private static Producto producto6;
	private static Empleado empleado1;
	private static Empleado empleado2;
	private static Empleado empleado3;
	private static Empleado empleado4;
	private static Empleado empleado5;
	private static Empleado empleado6;

	private List<Factura> facturasBD;
	private static List<Producto> productos;

	public FacturaResourceTest() {

	}

	@BeforeClass
	public static void setUp() throws Exception {
		// start the server
		server = Main.startServer();
		rsh = FacturaRSH.getInstance();

		// Set Data
		empleado1 = new Empleado("68432155O", "Paco Lopez", "Calle Ave del Paraiso, 13, 4Izq", "pacolopez@gmail.com",
				"654321789", EnumPuestoEmpleados.OPERARIO, new Date("12/04/1990 15:30:35"),
				new Date("08/07/2020 00:00:00"), 1200.50, "1234");
		empleado2 = new Empleado("35661232P", "Aitor Ruiz Garcia", "Calle Zorroza, 11, 1B", "aitoruiz@gmail.com",
				"621852478", EnumPuestoEmpleados.DIRECTIVA, new Date("10/01/1970 01:09:07"),
				new Date("30/06/2000 18:00:35"), 4300, "1234");
		empleado3 = new Empleado("22396365I", "Pablo Gonzalez Tejido", "Calle Leioa, 42, 3B", "pablogtejido@gmail.com",
				"753123258", EnumPuestoEmpleados.DIRECTIVA, new Date("25/12/1983 08:56:21"),
				new Date("27/02/2005 11:12:45"), 4300, "1234");
		empleado4 = new Empleado("42134567U", "Koldo Moya", "Calle Sestao, 2, 1B", "koldo.moya@gmail.com", "673258014",
				EnumPuestoEmpleados.DIRECTIVA, new Date("27/10/1974 05:04:00"), new Date("29/01/2007 12:00:00"), 4300,
				"1234");
		empleado5 = new Empleado("75420903Y", "Nerea Diez Landin", "Calle Portu, 57, 2B", "nereadiez@gmail.com",
				"658007220", EnumPuestoEmpleados.DIRECTIVA, new Date("14/05/1988 19:27:31"),
				new Date("11/09/2012 10:14:11"), 4300, "1234");
		empleado6 = new Empleado("11111111A", "Prueba", "Calle Portu, 57, 2B", "prueba@gmail.com", "658007220",
				EnumPuestoEmpleados.DIRECTIVA, new Date("14/05/1988 19:27:31"), new Date("11/09/2012 10:14:11"), 4300,
				"1234");

		cliente1 = new Cliente("58441139A", "Juan", "Lopez", "1234");
		cliente2 = new Cliente("36957841K", "Silvia", "Montejo", "1234");
		cliente3 = new Cliente("79845211T", "Luis", "Alonso", "1234");
		cliente4 = new Cliente("66558842G", "Silvia", "Montejo", "1234");
		cliente5 = new Cliente("45548696P", "Luis", "Alonso", "1234");
		cliente6 = new Cliente("79067812E", "Jorge", "Casas", "1234");

		producto1 = new Producto("Tablero madera", 12.70, 32, 1, 24, 4, true);
		producto2 = new Producto("Listón madera", 7.50, 21, 1, 30, 9, false);
		producto3 = new Producto("Revestimiento madera", 21.60, 8, 1, 50, 2, true);
		producto4 = new Producto("Celosía madera", 40, 2, 1, 220, 80, false);
		producto5 = new Producto("Mueble madera", 37, 38, 7, 180, 60, true);
		producto6 = new Producto("Balda madera", 14, 21, 1, 150, 30, false);

		productos.add(producto1);
		productos.add(producto2);
		productos.add(producto3);
		productos.add(producto4);
		productos.add(producto5);
		productos.add(producto6);

		factura1 = new Factura(empleado1, cliente1, productos, new Date("27/10/1974 05:04:00"));
		factura2 = new Factura(empleado2, cliente2, productos, new Date("27/10/1974 05:04:00"));
		factura3 = new Factura(empleado3, cliente3, productos, new Date("27/10/1974 05:04:00"));
		factura4 = new Factura(empleado4, cliente4, productos, new Date("27/10/1974 05:04:00"));
		factura5 = new Factura(empleado5, cliente5, productos, new Date("27/10/1974 05:04:00"));
		factura6 = new Factura(empleado6, cliente6, productos, new Date("27/10/1974 05:04:00"));

	}

	@AfterClass
	public static void tearDown() throws Exception {
		server.shutdownNow();
	}

	@Before
	public void PrepareData() {
		// Store test
		System.out.println(
				"================================================Creating data ...================================================");
		rsh.guardarFactura(factura1);
		rsh.guardarFactura(factura2);
		rsh.guardarFactura(factura3);
		rsh.guardarFactura(factura4);
		rsh.guardarFactura(factura5);
	}

	@After
	public void Clean() {
		System.out.println(
				"================================================Cleaning data ...================================================");
		facturasBD = rsh.verFacturas();
		for (Factura fa : facturasBD) {
			rsh.borrarFactura(fa);
		}
	}

	@Test
	public void testVerFacturas() {
		System.out.println(
				"================================================Test ver facturas================================================");
		facturasBD = rsh.verFacturas();
		assertEquals(facturasBD.size(), 5);

		boolean factura1_found = false;
		boolean factura2_found = false;
		boolean factura3_found = false;
		boolean factura4_found = false;
		boolean factura5_found = false;

		for (Factura fa : facturasBD) {
			if (fa.equals(factura1)) {
				factura1_found = true;
			} else if (fa.equals(factura2)) {
				factura2_found = true;
			} else if (fa.equals(factura3)) {
				factura3_found = true;
			} else if (fa.equals(factura4)) {
				factura4_found = true;
			} else if (fa.equals(factura5)) {
				factura5_found = true;
			}
		}
		assertTrue(factura1_found && factura2_found && factura3_found && factura4_found && factura5_found);
	}
	
	@Test
	public void testSubirFacturas() {
		System.out.println(
				"================================================Test subir facturas================================================");
		rsh.guardarFactura(factura6);
		facturasBD = rsh.verFacturas();
		assertEquals(facturasBD.size(), 6);

		boolean factura6_found = false;

		for (Factura fa : facturasBD) {
			if (fa.equals(factura6)) {
				factura6_found = true;
			}
		}
		assertTrue(factura6_found);
	}
	
	@Test
	public void testEliminarFactura() {
		System.out.println(
				"================================================Test eliminar facturas================================================");
		facturasBD = rsh.verFacturas();
		boolean factura_deleted_found = false;
		// Obtener una factura aleatoria.
		Factura f = facturasBD.get((int) Math.floor(Math.random() * 4));
		// Borramos la factura aleatoria.
		rsh.borrarFactura(f);
		// Actualizamos las facturas
		facturasBD = rsh.verFacturas();
		assertEquals(facturasBD.size(), 4);
		// Buscamos la factura eliminada, si no esta significa que ha sido borrada.
		for (Factura fa : facturasBD) {
			if (fa.equals(f)) {
				factura_deleted_found = true;
			}
		}
		// See the ! at the start.
		assertTrue(!factura_deleted_found);

	}

	
}