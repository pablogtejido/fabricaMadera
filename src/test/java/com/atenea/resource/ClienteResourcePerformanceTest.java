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
import com.atenea.data.Cliente;
import com.atenea.rsh.ClienteRSH;

import categories.IntegrationTest;

@Category(IntegrationTest.class)
public class ClienteResourcePerformanceTest {

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    private static HttpServer server;
    private static ClienteRSH rsh;

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println(
                "================================================Starting performant test================================================");
        // start the server
        server = Main.startServer();
        rsh = ClienteRSH.getInstance();
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
        String uuid = UUID.randomUUID().toString();
        Cliente cliente1 = new Cliente(uuid, "Prueba", "Prueba", "1234");
        rsh.guardarCliente(cliente1);
        rsh.borrarCliente(cliente1);
    }

    @After
    public void Clean() {
        System.out.println(
                "================================================Cleaning data ...================================================");
        List<Cliente> clienteBD = rsh.verClientes();
        for (Cliente cliente : clienteBD) {
            rsh.borrarCliente(cliente);
        }
    }

    @Test
    @PerfTest(invocations = 50, threads = 10)
    public void testSubirClientesPerformance() {
        /**
         * Crear un cliente de prueba para la bd. El primary key es el DNI. Por eso se
         * hace un UUID aleatorio en lugar del DNI.
         */
        String uuid = UUID.randomUUID().toString();

        Cliente cliente1 = new Cliente(uuid, "Prueba", "Prueba", "1234");

        rsh.guardarCliente(cliente1);

        boolean found_uuid = false;

        List<Cliente> clienteBD = rsh.verClientes();

        for (Cliente cliente : clienteBD) {
            if (cliente.getDni().equals(uuid)) {
                found_uuid = true;
            }
        }
        assertTrue(found_uuid);
    }

}
