package com.atenea.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdministradorTest {

	Administrador admin;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		admin = new Administrador("45884411L","1234", "Juan", "Lopez", "juanlopez@gmail.com", "658458111");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNombre() {
		System.out.println("getNombre");
		admin.setNombre("Kepa");
		String expResult = "Kepa";
		String result = admin.getNombre();
		assertEquals(expResult, result);

	}

	@Test
	public void testSetNombre() {
		System.out.println("setNombre");
		String nombre = "Peter";
		admin.setNombre(nombre);

	}

	@Test
	public void testGetApellido() {
		System.out.println("getApellido");
		admin.setApellido("Gomez");
		String expResult = "Gomez";
		String result = admin.getApellido();
		assertEquals(expResult, result);

	}

	@Test
	public void testSetApellido() {
		System.out.println("setApellido");
		String apellido = "Gonzalez";
		admin.setApellido(apellido);

	}
	
	@Test
	public void testGetEmail() {
		System.out.println("getEmail");
		admin.setEmail("juanll@gmail.com");
		String expResult = "juanll@gmail.com";
		String result = admin.getEmail();
		assertEquals(expResult, result);

	}

	@Test
	public void testSetEmail() {
		System.out.println("setEmail");
		String email = "juanlopezlo@gmail.com";
		admin.setEmail(email);

	}
	
	@Test
	public void testGetTelefono() {
		System.out.println("getTelefono");
		admin.setTelefono("685412222");
		String expResult = "685412222";
		String result = admin.getTelefono();
		assertEquals(expResult, result);

	}

	@Test
	public void testSetTelefono() {
		System.out.println("setTelefono");
		String telefono = "666999888";
		admin.setEmail(telefono);

	}


}
