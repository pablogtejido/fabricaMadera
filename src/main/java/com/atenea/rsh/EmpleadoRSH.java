package com.atenea.rsh;

import java.util.List;

import com.atenea.data.Empleado;

import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class EmpleadoRSH {

    static EmpleadoRSH instance = null;
    Client client;
    WebTarget target;

    public static EmpleadoRSH getInstance() {
        if (instance == null) {
            instance = new EmpleadoRSH();
        }
        return instance;
    }

    public EmpleadoRSH() {
        client = ClientBuilder.newClient();
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        target = client.target("http://localhost:8080/myapp").path("empleado"); // http://localhost:8080/myapp/empleado
    }

    /**
     * Ver todas los empleado del servidor.
     * 
     * @return <Code>List<Empleados></Code> Lista con empleados
     */
    public List<Empleado> verEmpleados() {
        Invocation.Builder ib = target.request(); // Construir la petición
        Response response = ib.get(); // Realizar una petición GET
        List<Empleado> empleados = response.readEntity(new GenericType<List<Empleado>>() { // Crear una lista de
                                                                                           // empleados
        });
        return empleados;
    }

    /**
     * Hacer una petición PUT al servidor para guardar el empleado.
     * 
     * @param Empleado a guardar.
     * @return <Code>Empleado</Code> Empleado con el dni ya guardado en la DB.
     */
    public Empleado guardarEmpleado(Empleado empleado) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.put(Entity.entity(empleado, MediaType.APPLICATION_JSON));
        Empleado empleadoConId = response.readEntity(Empleado.class);
        return empleadoConId;
    }

    /**
     * Modificar el empleado en el servidor
     * 
     * @param <Code>Empleado</Code> Empleado a modificar
     * @return <Code>Empleado</Code> Empleaco con el dni ya guardado en la BD
     */
    public Empleado modificarEmpleado(Empleado empleado) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.build("PATCH", Entity.json(empleado)).invoke();

        Empleado empleadoConDni = response.readEntity(Empleado.class);
        return empleadoConDni;
    }

    /**
     * Borrar un empleado de la BD
     * 
     * @param <Code>Empleado</Code> Empleado a borrar
     */
    public void borrarEmpleado(Empleado empleado) {
        Invocation.Builder ib = target.path("/ids/" + empleado.getDni()).request();
        ib.delete();
    }
}