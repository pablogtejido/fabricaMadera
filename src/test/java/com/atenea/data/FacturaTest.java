package com.atenea.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacturaTest {
    Factura f1;
    Cliente c1;
    Empleado e1;
    Producto p1;
    List<Producto> productos;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        c1 = new Cliente("58441139A", "Juan", "Lopez", "1234");

        e1 = new Empleado("68432155O", "Paco Lopez", "Calle Ave del Paraiso, 13, 4Izq", "pacolopez@gmail.com",
                "654321789", EnumPuestoEmpleados.OPERARIO, new Date("12/04/1990 15:30:35"),
                new Date("08/07/2020 00:00:00"), 1200.50, "1234");

        p1 = new Producto("Tablero madera", 12.70, 32, 24, 4, true);

        productos = new ArrayList<Producto>();
        productos.add(p1);

        f1 = new Factura(e1, c1, productos, new Date("27/04/2021 20:42:35"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetId() {
        System.out.println("getId");
        f1.setId(458);
        long expResult = 458;
        long result = f1.getId();
        assertEquals(expResult, result);

    }

    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 459;
        f1.setId(id);

    }

    @Test
    public void testSetListaProductos() {
        System.out.println("SetListaProductos");

    }

    @Test
    public void testGetEmpleado() {
        System.out.println("getEmpleado");
        Empleado result = f1.getEmpleado();
        assertEquals(e1, result);

    }

    @Test
    public void testSetEmpleado() {
        System.out.println("setEmpleado");
        f1.setEmpleado(e1);

    }

    /*
     * @Test public void testGetPrecio() { System.out.println("getPrecio"); double
     * expResult = 0.0; List<Producto> productos = f1.getProductos();
     * 
     * for (Producto producto : productos) { expResult += producto.getPrecio(); }
     * double result = f1.getPrecio(); assertEquals(expResult, result, 0.0);
     * 
     * }
     */
    @Test
    public void testGetPrecio() {
        System.out.println("getPrecio");
        double expResult = p1.getPrecio();
        double result = f1.calcularPrecio();
        assertEquals(expResult, result, 0.00);

    }

    @Test
    public void testSetPrecio() {
        System.out.println("setPrecio");
        f1.setPrecio(12.70);

    }

    /**
     * Test of getCliente method, of class Factura.
     */
    @Test
    public void testGetCliente() {
        System.out.println("getCliente");
        Cliente result = f1.getCliente();
        assertEquals(c1, result);

    }

    /**
     * Test of setCliente method, of class Factura.
     */
    @Test
    public void testSetCliente() {
        System.out.println("setCliente");
        f1.setCliente(c1);

    }

    /**
     * Test of getProductos method, of class Factura.
     */
    @Test
    public void testGetProductos() {
        System.out.println("getProductos");
        List<Producto> result = f1.getProductos();
        assertEquals(productos, result);

    }

    /**
     * Test of setProducto method, of class Factura.
     */
    @Test
    public void testSetProducto() {
        System.out.println("setProducto");
        f1.setProductos(productos);

    }

    /**
     * Test of getFcha_factura method, of class Factura.
     */
    @Test
    public void testGetFcha_factura() {
        System.out.println("getFcha_factura");
        Date expResult = new Date("27/04/2021 20:42:35");
        Date result = f1.getFcha_factura();
        assertEquals(expResult, result);

    }

    /**
     * Test of setFcha_factura method, of class Factura.
     */
    @Test
    public void testSetFcha_factura() {
        System.out.println("setFcha_factura");
        Date fcha_factura = new Date("27/04/2021 20:42:35");
        f1.setFcha_factura(fcha_factura);

    }

    /**
     * Test of toString method, of class Factura.
     */
    /*
     * @Test public void testToString() { System.out.println("toString"); String
     * expResult = ""; String result = f1.toString(); assertEquals(expResult,
     * result);
     * 
     * }
     */

    /**
     * Test of toStringProductos method, of class Factura.
     */
    /*
     * @Test public void testToStringProductos() {
     * System.out.println("toStringProductos"); String expResult = ""; String result
     * = f1.toStringProductos(); assertEquals(expResult, result);
     * 
     * }
     */

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
