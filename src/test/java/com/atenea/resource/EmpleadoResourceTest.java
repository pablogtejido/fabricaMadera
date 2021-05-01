package com.atenea.resource;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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

    private HttpServer server;
    private EmpleadoRSH rsh;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        rsh = EmpleadoRSH.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    @Test
    public void testVerEmpleados() {
        // Create test data
        List<Empleado> empleados = new ArrayList<>();

        Empleado empleado1 = new Empleado("68432155O", "Paco Lopez", "Calle Ave del Paraiso, 13, 4Izq",
                "pacolopez@gmail.com", "654321789", EnumPuestoEmpleados.OPERARIO, new Date("12/04/1990 15:30:35"),
                new Date("08/07/2020 00:00:00"), 1200.50, "1234");
        rsh.guardarEmpleado(empleado1);
        empleados.add(empleado1);

        List<Empleado> empleadosBD = rsh.verEmpleados();

        rsh.borrarEmpleado(empleado1);

        assertEquals(empleadosBD.get(0), empleados.get(0));

    }

}
