package com.atenea.resource;

import categories.IntegrationTest;
import static org.junit.Assert.*;
import java.util.List;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.atenea.Main;
import com.atenea.data.Producto;
import com.atenea.rsh.ProductoRSH;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class ProductoResourceTest {

	private static HttpServer server;
	private static ProductoRSH rsh;
	private static Producto producto1;
	private static Producto producto2;
	private static Producto producto3;
	private static Producto producto4;
	private static Producto producto5;
	private static Producto producto6;

	private List<Producto> productosBD;

	public ProductoResourceTest() {

	}

	@BeforeClass
	public static void setUp() throws Exception {
		// start the server
		server = Main.startServer();
		rsh = ProductoRSH.getInstance();

		// Set Data
		producto1 = new Producto("Tablero madera", 12.70, 32, 1, 24, 4, true);
		producto2 = new Producto("Listón madera", 7.50, 21, 1, 30, 9, false);
		producto3 = new Producto("Revestimiento madera", 21.60, 8, 1, 50, 2, true);
		producto4 = new Producto("Celosía madera", 40, 2, 1, 220, 80, false);
		producto5 = new Producto("Mueble madera", 37, 38, 7, 180, 60, true);
		producto6 = new Producto("Balda madera", 14, 21, 1, 150, 30, false);

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
		rsh.guardarProducto(producto1);
		rsh.guardarProducto(producto2);
		rsh.guardarProducto(producto3);
		rsh.guardarProducto(producto4);
		rsh.guardarProducto(producto5);
	}

	@After
	public void Clean() {
		System.out.println(
				"================================================Cleaning data ...================================================");
		productosBD = rsh.verProductos();
		for (Producto pr : productosBD) {
			rsh.borrarProducto(pr);
		}
	}

	@Test
	public void testVerProductos() {
		System.out.println(
				"================================================Test ver productos================================================");
		productosBD = rsh.verProductos();
		assertEquals(productosBD.size(), 5);

		boolean producto1_found = false;
		boolean producto2_found = false;
		boolean producto3_found = false;
		boolean producto4_found = false;
		boolean producto5_found = false;

		for (Producto prs : productosBD) {
			if (prs.equals(producto1)) {
				producto1_found = true;
			} else if (prs.equals(producto2)) {
				producto2_found = true;
			} else if (prs.equals(producto3)) {
				producto3_found = true;
			} else if (prs.equals(producto4)) {
				producto4_found = true;
			} else if (prs.equals(producto5)) {
				producto5_found = true;
			}
		}
		assertTrue(producto1_found && producto2_found && producto3_found && producto4_found && producto5_found);
	}

	@Test
	public void testSubirProducto() {
		System.out.println(
				"================================================Test subir productos================================================");
		rsh.guardarProducto(producto6);
		productosBD = rsh.verProductos();
		assertEquals(productosBD.size(), 6);

		boolean producto6_found = false;

		for (Producto pr : productosBD) {
			if (pr.equals(producto6)) {
				producto6_found = true;
			}
		}
		assertTrue(producto6_found);
	}

	@Test
	public void testEliminarProducto() {
		System.out.println(
				"================================================Test eliminar productos================================================");
		productosBD = rsh.verProductos();
		boolean producto_deleted_found = false;
		// Obtener un producto aleatorio.
		Producto p = productosBD.get((int) Math.floor(Math.random() * 4));
		// Borramos el producto aleatorio.
		rsh.borrarProducto(p);
		// Actualizamos los productos
		productosBD = rsh.verProductos();
		assertEquals(productosBD.size(), 4);
		// Buscamos el objeto eliminado, si no esta significa que ha sido borrado.
		for (Producto producto : productosBD) {
			if (p.equals(producto)) {
				producto_deleted_found = true;
			}
		}
		// See the ! at the start.
		assertTrue(!producto_deleted_found);

	}
}