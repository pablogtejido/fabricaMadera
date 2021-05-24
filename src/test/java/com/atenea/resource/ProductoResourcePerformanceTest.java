package com.atenea.resource;

import static org.junit.Assert.*;

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
import com.atenea.data.Producto;
import com.atenea.rsh.ProductoRSH;

import categories.IntegrationTest;
import java.util.Objects;

@Category(IntegrationTest.class)
public class ProductoResourcePerformanceTest {

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    private static HttpServer server;
    private static ProductoRSH rsh;

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println(
                "================================================Starting performant test================================================");
        // start the server
        server = Main.startServer();
        rsh = ProductoRSH.getInstance();
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
        String nombre = UUID.randomUUID().toString();
        Producto producto1 = new Producto("prueba", 12.70, 32, 24, 4, true);

        rsh.borrarProducto(producto1);
        rsh.guardarProducto(producto1);
    }

    @After
    public void Clean() {
        System.out.println(
                "================================================Cleaning data ...================================================");
        List<Producto> productosDB = rsh.verProductos();
        for (Producto prod : productosDB) {
            rsh.borrarProducto(prod);
        }
    }

    @Test
    @PerfTest(invocations = 50, threads = 10)
    public void testSubirEmpleadosPerformance() {
        /**
         * Crear un producto de prueba para la bd. El primary key es el LONG ID.Pero al
         * ser un autoincremental en la BD no crearemos uno aleatorio como en otros
         * tests sino que crearemos un nombre aleatorio y recogeremos el id que nos
         * devuelve la BD y los comparariamos
         */

        String nombre = UUID.randomUUID().toString();
        Producto producto1 = new Producto(nombre, 12.70, 32, 24, 4, true);

        producto1.setId(rsh.guardarProducto(producto1).getId());

        boolean bool_nombre = false;

        List<Producto> productosDB = rsh.verProductos();

        for (Producto producto : productosDB) {
            if (Objects.equals(producto.getId(), producto1.getId())) {
                bool_nombre = true;
            }
        }
        assertTrue(bool_nombre);
    }

}