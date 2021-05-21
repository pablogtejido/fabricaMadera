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
import com.atenea.data.Administrador;
import com.atenea.rsh.AdministradorRSH;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class AdministradorResourceTest {

	private static HttpServer server;
	private static AdministradorRSH rsh;
	private static Administrador administrador1;
	private static Administrador administrador2;
	private static Administrador administrador3;
	private static Administrador administrador4;
	private static Administrador administrador5;
	private static Administrador administrador6;

	private List<Administrador> adminBD;

	public AdministradorResourceTest() {

	}

	@BeforeClass
	public static void setUp() throws Exception {
		// start the server
		server = Main.startServer();
		rsh = AdministradorRSH.getInstance();

		// Set Data
		administrador1 =new Administrador("1234", "Paco", "Salas",
				  "pacosalas2@gmail.com", "682548111");
		
		administrador2 =new Administrador("1234", "Juan", "Lopez",
				  "juanlopez@gmail.com", "669584412");
		
		administrador3 =new Administrador("1234", "Aimar", "Gomez",
				  "aimargomez@gmail.com", "748512300");
		
		administrador4 =new Administrador("1234", "Asier", "Muñoz",
				  "asierMuñoz@gmail.com", "695412111");
		
		administrador5 =new Administrador("1234", "Carlos", "Suso",
				  "carlosuso@gmail.com", "663332211");
		
		administrador6 =new Administrador("1234", "Luis", "Gonzalez",
				  "luizgonzalez@gmail.com", "665412287");

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
		administrador1.setId(rsh.guardarAdministrador(administrador1).getId());
		administrador2.setId(rsh.guardarAdministrador(administrador2).getId());
		administrador3.setId(rsh.guardarAdministrador(administrador3).getId());
		administrador4.setId(rsh.guardarAdministrador(administrador4).getId());
		administrador5.setId(rsh.guardarAdministrador(administrador5).getId());
		
	}

	@After
	public void Clean() {
		System.out.println(
				"================================================Cleaning data ...================================================");
		adminBD = rsh.verAdministrador();
		for (Administrador ad : adminBD) {
			rsh.borrarAdministrador(ad);
		}
	}

	@Test
	public void testVerAdministrador() {
		System.out.println(
				"================================================Test ver administrador================================================");
		adminBD = rsh.verAdministrador();
		assertEquals(adminBD.size(), 5);

		boolean administrador1_found = false;
		boolean administrador2_found = false;
		boolean administrador3_found = false;
		boolean administrador4_found = false;
		boolean administrador5_found = false;

		for (Administrador adm : adminBD) {
			if (adm.equals(administrador1)) {
				administrador1_found = true;
			} else if (adm.equals(administrador2)) {
				administrador2_found = true;
			} else if (adm.equals(administrador3)) {
				administrador3_found = true;
			} else if (adm.equals(administrador4)) {
				administrador4_found = true;
			} else if (adm.equals(administrador5)) {
				administrador5_found = true;
			}
		}
		assertTrue(administrador1_found && administrador2_found && administrador3_found && administrador4_found && administrador5_found);
	}

	@Test
	public void testSubirAdministrador() {
		System.out.println(
				"================================================Test subir administrador================================================");
		administrador6.setId(rsh.guardarAdministrador(administrador6).getId());
		adminBD = rsh.verAdministrador();
		assertEquals(adminBD.size(), 6);

		boolean administrador6_found = false;

		for (Administrador ad : adminBD) {
			if (ad.equals(administrador6_found)) {
				administrador6_found = true;
			}
		}
		assertTrue(administrador6_found);
	}

	@Test
	public void testEliminarAdministrador() {
		System.out.println(
				"================================================Test eliminar administrador================================================");
		adminBD = rsh.verAdministrador();
		boolean administrador_deleted_found = false;
		// Obtener un admin aleatorio.
		Administrador a = adminBD.get((int) Math.floor(Math.random() * 4));
		// Borramos el admin aleatorio.
		rsh.borrarAdministrador(a);
		// Actualizamos los administradores
		adminBD = rsh.verAdministrador();
		assertEquals(adminBD.size(), 4);
		// Buscamos el admin eliminado, si no esta significa que ha sido borrado.
		for (Administrador adminis : adminBD) {
			if (a.equals(adminis)) {
				administrador_deleted_found = true;
			}
		}
		// See the ! at the start.
		assertTrue(!administrador_deleted_found);

	}
}