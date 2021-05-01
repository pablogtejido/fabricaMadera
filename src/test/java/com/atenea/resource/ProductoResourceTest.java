package com.atenea.resource;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.atenea.Main;
import com.atenea.data.Producto;
import com.atenea.rsh.ProductoRSH;

import categories.IntegrationTest;

@Category(IntegrationTest.class)
public class ProductoResourceTest {
	
    private HttpServer server;
    private ProductoRSH rsh;
    
    private Producto producto1;
    private List<Producto> productos;
    private List<Producto> productosBD;

    public ProductoResourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
    	
    	server = Main.startServer();
        rsh = ProductoRSH.getInstance();
        
        productos = new ArrayList<>();
        producto1 = new Producto("Tablero madera", 12.70, 32, 1, 24, 4, true);
        
    }
    
    @After
    public void tearDown() throws Exception{
    	server.shutdownNow();
 
    }
	@Test
	public void testVerClientes() {
		
		rsh.guardarProducto(producto1);
		productos.add(producto1);
		
		productosBD = rsh.verProductos();
		
		rsh.borrarProducto(producto1);
		
		assertEquals(productosBD.get(0), productos.get(0));
		
	}

}