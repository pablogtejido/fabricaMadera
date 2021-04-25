package com.atenea.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.atenea.data.Factura;

public class FacturaTest {

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
	public void testGetId() {
		System.out.println("getId");
		Factura instanceFactura = new Factura();
		instanceFactura.setId(458);
		long expResult = 458;
		long result = instanceFactura.getId();
		assertEquals(expResult, result);

	}

	@Test
	public void testSetId() {
		System.out.println("setId");
		Factura instanceFactura = new Factura();
		long id = 459;
		instanceFactura.setId(id);

	}

	/*
	 * @Test public void testGetListaProductos() { List<Producto> pr = new
	 * ArrayList<Producto>(); long id = 12; double precio=147; double peso=24.7; int
	 * cantidad=3; double medida=18.5; double grosor = 4; boolean anyadido = false;
	 * Producto instanceProducto = new Producto(id, precio, peso, cantidad, medida,
	 * grosor, anyadido); pr.add(instanceProducto);
	 * 
	 * List<Producto> pr2 = new ArrayList<Producto>();
	 * 
	 * long idExpect = 12; Producto expectResult=new Producto(idExpect, precio
	 * ,peso, cantidad ,medida , grosor, anyadido); pr2.add(expectResult);
	 * assertEquals(expectResult,instanceProducto);
	 * 
	 * 
	 * }
	 */

	@Test
	public void testSetListaProductos() {

	}

	@Test
	public void testGetEmpleado() {

	}

	@Test
	public void testSetEmpleado() {

	}

	@Test
	public void testGetPrecio() {
		System.out.println("getId");
		Factura instanceFactura = new Factura();
		instanceFactura.setPrecio(141.7);
		double expResult = 141.7;
		double result = instanceFactura.getPrecio();
		assertEquals(expResult, result, 0.0);

	}

	@Test
	public void testSetPrecio() {
		System.out.println("setId");
		Factura instanceFactura = new Factura();
		double precio = 142;
		instanceFactura.setPrecio(precio);

	}

}
