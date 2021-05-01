package com.atenea.resource;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Date;

import com.atenea.Main;
import com.atenea.data.Empleado;
import com.atenea.data.EnumPuestoEmpleados;

import org.junit.experimental.categories.Category;

import categories.IntegrationTest;
import com.atenea.rsh.EmpleadoRSH;
import java.util.List;

@Category(IntegrationTest.class)
public class EmpleadoResourceTest {

    private static HttpServer server;
    private static EmpleadoRSH rsh;
    private static Empleado empleado1;
    private static Empleado empleado2;
    private static Empleado empleado3;
    private static Empleado empleado4;
    private static Empleado empleado5;
    private static Empleado empleado6;

    @BeforeClass
    public static void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        rsh = EmpleadoRSH.getInstance();

        // Set Data
        empleado1 = new Empleado("68432155O", "Paco Lopez", "Calle Ave del Paraiso, 13, 4Izq", "pacolopez@gmail.com",
                "654321789", EnumPuestoEmpleados.OPERARIO, new Date("12/04/1990 15:30:35"),
                new Date("08/07/2020 00:00:00"), 1200.50, "1234");
        empleado2 = new Empleado("35661232P", "Aitor Ruiz Garcia", "Calle Zorroza, 11, 1B", "aitoruiz@gmail.com",
                "621852478", EnumPuestoEmpleados.DIRECTIVA, new Date("10/01/1970 01:09:07"),
                new Date("30/06/2000 18:00:35"), 4300, "1234");
        empleado3 = new Empleado("22396365I", "Pablo Gonzalez Tejido", "Calle Leioa, 42, 3B", "pablogtejido@gmail.com",
                "753123258", EnumPuestoEmpleados.DIRECTIVA, new Date("25/12/1983 08:56:21"),
                new Date("27/02/2005 11:12:45"), 4300, "1234");
        empleado4 = new Empleado("42134567U", "Koldo Moya", "Calle Sestao, 2, 1B", "koldo.moya@gmail.com", "673258014",
                EnumPuestoEmpleados.DIRECTIVA, new Date("27/10/1974 05:04:00"), new Date("29/01/2007 12:00:00"), 4300,
                "1234");
        empleado5 = new Empleado("75420903Y", "Nerea Diez Landin", "Calle Portu, 57, 2B", "nereadiez@gmail.com",
                "658007220", EnumPuestoEmpleados.DIRECTIVA, new Date("14/05/1988 19:27:31"),
                new Date("11/09/2012 10:14:11"), 4300, "1234");
        empleado6 = new Empleado("11111111A", "Prueba", "Calle Portu, 57, 2B", "prueba@gmail.com", "658007220",
                EnumPuestoEmpleados.DIRECTIVA, new Date("14/05/1988 19:27:31"), new Date("11/09/2012 10:14:11"), 4300,
                "1234");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        server.shutdownNow();
    }

    @Before
    public void PrepareData() {
        // Store test
        System.out.println(
                "================================================Creating data ...================================================");
        rsh.guardarEmpleado(empleado1);
        rsh.guardarEmpleado(empleado2);
        rsh.guardarEmpleado(empleado3);
        rsh.guardarEmpleado(empleado4);
        rsh.guardarEmpleado(empleado5);
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
    public void testVerEmpleados() {
        System.out.println(
                "================================================Test ver empleados================================================");
        List<Empleado> empleadosBD = rsh.verEmpleados();
        assertEquals(empleadosBD.size(), 5);

        boolean empleado1_found = false;
        boolean empleado2_found = false;
        boolean empleado3_found = false;
        boolean empleado4_found = false;
        boolean empleado5_found = false;

        for (Empleado empleado : empleadosBD) {
            if (empleado.equals(empleado1)) {
                empleado1_found = true;
            } else if (empleado.equals(empleado2)) {
                empleado2_found = true;
            } else if (empleado.equals(empleado3)) {
                empleado3_found = true;
            } else if (empleado.equals(empleado4)) {
                empleado4_found = true;
            } else if (empleado.equals(empleado5)) {
                empleado5_found = true;
            }
        }
        assertTrue(empleado1_found && empleado2_found && empleado3_found && empleado4_found && empleado5_found);
    }

    @Test
    public void testSubirEmpleados() {
        System.out.println(
                "================================================Test subir empleados================================================");
        rsh.guardarEmpleado(empleado6);
        List<Empleado> empleadosBD = rsh.verEmpleados();
        assertEquals(empleadosBD.size(), 6);

        boolean empleado6_found = false;

        for (Empleado empleado : empleadosBD) {
            if (empleado.equals(empleado6)) {
                empleado6_found = true;
            }
        }
        assertTrue(empleado6_found);
    }

    @Test
    public void testEliminarEmpleado() {
        System.out.println(
                "================================================Test eliminar empleados================================================");
        rsh.borrarEmpleado(empleado1);
        List<Empleado> empleadosBD = rsh.verEmpleados();
        assertEquals(empleadosBD.size(), 4);

        boolean empleado1_found = false;
        boolean empleado2_found = false;
        boolean empleado3_found = false;
        boolean empleado4_found = false;
        boolean empleado5_found = false;

        for (Empleado empleado : empleadosBD) {
            if (empleado.equals(empleado1)) {
                empleado1_found = true;
            } else if (empleado.equals(empleado2)) {
                empleado2_found = true;
            } else if (empleado.equals(empleado3)) {
                empleado3_found = true;
            } else if (empleado.equals(empleado4)) {
                empleado4_found = true;
            } else if (empleado.equals(empleado5)) {
                empleado5_found = true;
            }
        }
        // See the ! at the start.
        assertTrue(!empleado1_found && empleado2_found && empleado3_found && empleado4_found && empleado5_found);

    }
}
