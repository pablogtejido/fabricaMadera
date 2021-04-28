package com.atenea.data;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ClienteTest {
	
	Cliente cl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		cl = new Cliente();
	}

	@After
	public void tearDown() throws Exception {
	}


	
	@Test
	public void testGetDni() {
		  System.out.println("getDni");
	      cl.setDni("45894785Z");
	      String expResult = "45894785Z";
	      String result = cl.getDni();
	      assertEquals(expResult, result);

	}
	
	
	 @Test 
	 public void testSetDni() {
		  System.out.println("setDni");
	      String dni = "45748621W";
	      cl.setDni(dni);
		 
	 }
	 
	@Test
	public void testGetNombre() {
		  System.out.println("getNombre");
	      cl.setNombre("Lucas");
	      String expResult = "Lucas";
	      String result = cl.getNombre();
	      assertEquals(expResult, result);
			
	}
		
		
	@Test 
	public void testSetNombre() {
		  System.out.println("setNombre");
	      String nombre = "Marcos";
	      cl.setNombre(nombre);  
			 
	}


	@Test
	public void testGetApellidos() {
		  System.out.println("getApellido");
		  cl.setApellidos("Lopez");
	      String expResult = "Lopez";
	      String result = cl.getApellidos();
	      assertEquals(expResult, result);
			
	}
		
	
	@Test 
	public void testSetApellidos() {
		  System.out.println("setApellido");
		  String apellido = "Perez";
		  cl.setApellidos(apellido);  
			 
	}


}
