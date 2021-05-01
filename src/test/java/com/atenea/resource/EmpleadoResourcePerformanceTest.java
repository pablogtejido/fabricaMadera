package com.atenea.resource;

import org.databene.contiperf.PerfTest;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.util.Date;

import com.atenea.Main;
import com.atenea.data.Empleado;
import com.atenea.data.EnumPuestoEmpleados;
import com.atenea.rsh.EmpleadoRSH;
import java.util.List;
import org.junit.experimental.categories.Category;

import categories.IntegrationTest;
import java.util.UUID;

@Category(IntegrationTest.class)
public class EmpleadoResourcePerformanceTest {

    private static HttpServer server;
    private static EmpleadoRSH rsh;

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println(
                "================================================Starting performant test================================================");
        // start the server
        server = Main.startServer();
        rsh = EmpleadoRSH.getInstance();
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
    }

    @After
    public void Clean() {
        System.out.println(
                "================================================Cleaning data ...================================================");
        List<Empleado> empleadosBD = rsh.verEmpleados();
        for (Empleado em : empleadosBD) {
            rsh.borrarEmpleado(em);
        }
    }

    @Test
    @PerfTest(invocations = 1000, threads = 40)
    public void testSubirEmpleadosPerformance() {
        /**
         * Crear un empleado de prueba para la bd. El primary key es el DNI. Por eso se
         * hace un UUID aleatorio en lugar del DNI.
         */
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");

        Empleado empleado1 = new Empleado(uuid, "prueba", "prueba", "prueba@prueba.com", "prueba",
                EnumPuestoEmpleados.OPERARIO, new Date("12/04/1990 15:30:35"), new Date("08/07/2020 00:00:00"), 1200.50,
                "prueba");

        rsh.guardarEmpleado(empleado1);

        boolean found_uuid = false;

        List<Empleado> empleadosBD = rsh.verEmpleados();

        for (Empleado empleado : empleadosBD) {
            if (empleado.getDni().equals(uuid)) {
                found_uuid = true;
            }
        }
        assertTrue(found_uuid);

    }

}
