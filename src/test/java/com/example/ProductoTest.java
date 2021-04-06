package com.example;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ProductoTest {
    
    public ProductoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPrecio method, of class Producto.
     */
    @Test
    public void testGetPrecio() {
        System.out.println("getPrecio");
        Producto instance = new Producto();
        instance.setPrecio(0);
        double expResult = 0.0;
        double result = instance.getPrecio();
        assertEquals(expResult, result, 0.0);
       
    }

    /**
     * Test of setPrecio method, of class Producto.
     */
    @Test
    public void testSetPrecio() {
        System.out.println("setPrecio");
        double precio = 0.0;
        Producto instance = new Producto();
        instance.setPrecio(precio);
      
    }

    /**
     * Test of getPeso method, of class Producto.
     */
    @Test
    public void testGetPeso() {
        System.out.println("getPeso");
        Producto instance = new Producto();
        instance.setPeso(0);
        double expResult = 0.0;
        double result = instance.getPeso();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of setPeso method, of class Producto.
     */
    @Test
    public void testSetPeso() {
        System.out.println("setPeso");
        double peso = 0.0;
        Producto instance = new Producto();
        instance.setPeso(peso);
        
    }

    /**
     * Test of getCantidad method, of class Producto.
     */
    @Test
    public void testGetCantidad() {
        System.out.println("getCantidad");
        Producto instance = new Producto();
        instance.setCantidad(0);
        int expResult = 0;
        int result = instance.getCantidad();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCantidad method, of class Producto.
     */
    @Test
    public void testSetCantidad() {
        System.out.println("setCantidad");
        int cantidad = 0;
        Producto instance = new Producto();
        instance.setCantidad(cantidad);
        
    }

    /**
     * Test of getMedida method, of class Producto.
     */
    @Test
    public void testGetMedida() {
        System.out.println("getMedida");
        Producto instance = new Producto();
        instance.setMedida(0);
        double expResult = 0.0;
        double result = instance.getMedida();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of setMedida method, of class Producto.
     */
    @Test
    public void testSetMedida() {
        System.out.println("setMedida");
        double medida = 0.0;
        Producto instance = new Producto();
        instance.setMedida(medida);
        
    }

    /**
     * Test of getGrosor method, of class Producto.
     */
    @Test
    public void testGetGrosor() {
        System.out.println("getGrosor");
        Producto instance = new Producto();
        double expResult = 0.0;
        double result = instance.getGrosor();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of setGrosor method, of class Producto.
     */
    @Test
    public void testSetGrosor() {
        System.out.println("setGrosor");
        double grosor = 0.0;
        Producto instance = new Producto();
        instance.setGrosor(grosor);
        
    }

    /**
     * Test of isAnyadidos method, of class Producto.
     */
    @Test
    public void testIsAnyadidos() {
        System.out.println("isAnyadidos");
        Producto instance = new Producto();
        instance.setAnyadidos(false);
        boolean expResult = false;
        boolean result = instance.isAnyadidos();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setAnyadidos method, of class Producto.
     */
    @Test
    public void testSetAnyadidos() {
        System.out.println("setAnyadidos");
        boolean anyadidos = false;
        Producto instance = new Producto();
        instance.setAnyadidos(anyadidos);
        
    }
    
}

