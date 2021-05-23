package com.atenea.resource;

import static org.junit.Assert.*;

import java.util.List;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.atenea.Main;
import com.atenea.data.Cliente;
import com.atenea.rsh.ClienteRSH;

import categories.IntegrationTest;

@Category(IntegrationTest.class)
public class ClienteResourceTest {

	private static HttpServer server;
	private static ClienteRSH rsh;

	private static Cliente cliente1;
	private static Cliente cliente2;
	private static Cliente cliente3;
	private static Cliente cliente4;
	private static Cliente cliente5;
	private static Cliente cliente6;
	private List<Cliente> clientesBD;

	public ClienteResourceTest() {
	}

	@BeforeClass
	public static void setUp() {
		// start the server
		server = Main.startServer();
		rsh = ClienteRSH.getInstance();

		cliente1 = new Cliente("58441139A", "Juan", "Lopez", "1234");
		cliente2 = new Cliente("36957841K", "Silvia", "Montejo", "1234");
		cliente3 = new Cliente("79845211T", "Luis", "Alonso", "1234");
		cliente4 = new Cliente("66558842G", "Silvia", "Montejo", "1234");
		cliente5 = new Cliente("45548696P", "Luis", "Alonso", "1234");
		cliente6 = new Cliente("79067812E", "Jorge", "Casas", "1234");
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
		rsh.guardarCliente(cliente1);
		rsh.guardarCliente(cliente2);
		rsh.guardarCliente(cliente3);
		rsh.guardarCliente(cliente4);
		rsh.guardarCliente(cliente5);

	}

	@After
	public void Clean() {
		System.out.println(
				"================================================Cleaning data ...================================================");
		clientesBD = rsh.verClientes();
		for (Cliente cl : clientesBD) {
			rsh.borrarCliente(cl);
		}
	}

	@Test
	public void testVerClientes() {
		System.out.println(
				"================================================Test ver clientes================================================");

		clientesBD = rsh.verClientes();
		assertEquals(clientesBD.size(), 5);

		boolean cliente1_found = false;
		boolean cliente2_found = false;
		boolean cliente3_found = false;
		boolean cliente4_found = false;
		boolean cliente5_found = false;

		for (Cliente cliente : clientesBD) {
			if (cliente.equals(cliente1)) {
				cliente1_found = true;
			} else if (cliente.equals(cliente2)) {
				cliente2_found = true;
			} else if (cliente.equals(cliente3)) {
				cliente3_found = true;
			} else if (cliente.equals(cliente4)) {
				cliente4_found = true;
			} else if (cliente.equals(cliente5)) {
				cliente5_found = true;
			}
		}
		assertTrue(cliente1_found && cliente2_found && cliente3_found && cliente4_found && cliente5_found);
	}

	@Test
	public void testSubirClientes() {
		System.out.println(
				"================================================Test subir clientes================================================");
		rsh.guardarCliente(cliente6);
		clientesBD = rsh.verClientes();
		assertEquals(clientesBD.size(), 6);

		boolean cliente6_found = false;

		for (Cliente cliente : clientesBD) {
			if (cliente.equals(cliente6)) {
				cliente6_found = true;
			}
		}
		assertTrue(cliente6_found);

	}

	@Test
	public void testEliminarCliente() {
		System.out.println(
				"================================================Test eliminar clientes================================================");
		rsh.borrarCliente(cliente1);
		clientesBD = rsh.verClientes();
		assertEquals(clientesBD.size(), 4);

		boolean cliente1_found = false;
		boolean cliente2_found = false;
		boolean cliente3_found = false;
		boolean cliente4_found = false;
		boolean cliente5_found = false;

		for (Cliente cliente : clientesBD) {
			if (cliente.equals(cliente1)) {
				cliente1_found = true;
			} else if (cliente.equals(cliente2)) {
				cliente2_found = true;
			} else if (cliente.equals(cliente3)) {
				cliente3_found = true;
			} else if (cliente.equals(cliente4)) {
				cliente4_found = true;
			} else if (cliente.equals(cliente5)) {
				cliente5_found = true;
			}
		}
		assertTrue(!cliente1_found && cliente2_found && cliente3_found && cliente4_found && cliente5_found);
	}

}
