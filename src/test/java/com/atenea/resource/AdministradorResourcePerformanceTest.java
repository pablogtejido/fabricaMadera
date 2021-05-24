package com.atenea.resource;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.atenea.Main;
import com.atenea.data.Administrador;
import com.atenea.data.Empleado;
import com.atenea.data.EnumPuestoEmpleados;
import com.atenea.data.Producto;
import com.atenea.rsh.AdministradorRSH;
import com.atenea.rsh.ProductoRSH;

import categories.IntegrationTest;

@Category(IntegrationTest.class)
public class AdministradorResourcePerformanceTest {

	
	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	private static HttpServer server;
	private static AdministradorRSH rsh;
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		System.out.println(
				"================================================Starting performant test================================================");
		// start the server
		server = Main.startServer();
		rsh = AdministradorRSH.getInstance();
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println(
				"================================================Ending performant test================================================");
		server.shutdownNow();
	}
	
	@Before
	public void PrepareData() {
		System.out.println(
				"================================================Creating data ...================================================");
		String DNI = UUID.randomUUID().toString();
		Administrador admin1 = new Administrador(DNI, "1234", "Paco", "Salas", "pacosalas@gmail.com", "682548111");
		
		rsh.borrarAdministrador(admin1);
		rsh.guardarAdministrador(admin1);
	}
	
	@After
	public void Clean() {
		System.out.println(
				"================================================Cleaning data ...================================================");
		List<Administrador> administradorDB = rsh.verAdministrador();
		for (Administrador admin : administradorDB) {
			rsh.borrarAdministrador(admin);
		}
	}

	
	@Test
	@PerfTest(invocations = 50, threads = 10)
	public void testSubirEmpleadosPerformance() {
		/**
		 * Crear un administrador de prueba para la bd. El primary key es el DNI. Por eso se
		 * hace un UUID aleatorio en lugar del DNI.
		 */

		String DNI = UUID.randomUUID().toString();
		Administrador admin1 = new Administrador(DNI, "1234", "Paco", "Salas", "pacosalas@gmail.com", "682548111");		
		
		rsh.guardarAdministrador(admin1);

		boolean bool_dni = false;

		List<Administrador> administradorDB = rsh.verAdministrador();

		for (Administrador admin : administradorDB) {
			if (admin.getDni().equals(DNI)) {
				bool_dni = true;
			}
		}
		assertTrue(bool_dni);
	}


}

