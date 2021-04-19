package com.example;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.atenea.data.Cliente;
import com.atenea.data.Empleado;

public class ClienteTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	
	@Test
	public void testGetDni() {
		  System.out.println("getDni");
	      Cliente instanceCliente = new Cliente();
	      instanceCliente.setDni("45894785Z");
	      String expResult = "45894785Z";
	      String result = instanceCliente.getDni();
	      assertEquals(expResult, result);

	}
	
	
	 @Test 
	 public void testSetDni() {
		  System.out.println("setDni");
		  Empleado instanceCliente = new Empleado();
	      String dni = "45748621W";
	      instanceCliente.setDni(dni);
		 
	 }
	 
	@Test
	public void testGetNombre() {
		  System.out.println("getNombre");
	      Cliente instanceCliente = new Cliente();
	      instanceCliente.setNombre("Lucas");
	      String expResult = "Lucas";
	      String result = instanceCliente.getNombre();
	      assertEquals(expResult, result);
			
	}
		
		
	@Test 
	public void testSetNombre() {
		  System.out.println("setNombre");
		  Cliente instanceCliente = new Cliente();
	      String nombre = "Marcos";
	      instanceCliente.setNombre(nombre);  
			 
	}


	@Test
	public void testGetApellidos() {
		  System.out.println("getApellido");
	      Cliente instanceCliente = new Cliente();
	      instanceCliente.setApellidos("Lopez");
	      String expResult = "Lopez";
	      String result = instanceCliente.getApellidos();
	      assertEquals(expResult, result);
			
	}
		
	
	@Test 
	public void testSetApellidos() {
		  System.out.println("setApellido");
		  Cliente instanceCliente = new Cliente();
	      String apellido = "Perez";
	      instanceCliente.setApellidos(apellido);  
			 
	}


}
