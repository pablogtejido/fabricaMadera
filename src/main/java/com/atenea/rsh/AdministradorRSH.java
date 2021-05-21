package com.atenea.rsh;

import java.util.List;

import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import com.atenea.data.Administrador;
import com.atenea.data.Cliente;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class AdministradorRSH {
    static AdministradorRSH instance = null;
    Client client;
    WebTarget target;

    public static AdministradorRSH getInstance() {
        if (instance == null) {
            instance = new AdministradorRSH();
        }
        return instance;
    }

    public AdministradorRSH() {
        client = ClientBuilder.newClient();
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        target = client.target("http://localhost:8080/myapp").path("administrador"); // http://localhost:8080/myapp/cliente
    }

    /**
     * Ver todas los clientes del servidor.
     * 
     * @return <Code>List<Cliente></Code> Lista con clientes
     */
    public List<Administrador> verAdministrador() {
        Invocation.Builder ib = target.request(); // Construir la petición
        Response response = ib.get(); // Realizar una petición GET
        List<Administrador> admin = response.readEntity(new GenericType<List<Administrador>>() { // Crear una lista de clientes
        });
        return admin;
    }

    /**
     * Hacer una petición PUT al servidor para guardar el cliente.
     * 
     * @param Cliente a guardar.
     * @return <Code>Cliente</Code> Cliente con el dni ya guardado en la DB.
     */
    public Administrador guardarAdministrador(Administrador admin) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.put(Entity.entity(admin, MediaType.APPLICATION_JSON));
        Administrador administradorConId = response.readEntity(Administrador.class);
        return administradorConId;
    }

    /**
     * Modificar el cliente en el servidor
     * 
     * @param <Code>Cliente</Code> Cliente a modificar
     * @return <Code>Cliente</Code> Cliente con el dni ya guardado en la BD
     */
    public Administrador modificarAdministrador(Administrador admin) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.build("PATCH", Entity.json(admin)).invoke();

        Administrador administradorConId = response.readEntity(Administrador.class);
        return administradorConId;
    }

    /**
     * Borrar un cliente de la BD
     * 
     * @param <Code>Cliente</Code> Cliente a borrar
     */
    public void borrarAdministrador(Administrador admin) {
        Invocation.Builder ib = target.path("/ids/" + admin.getId()).request();
        ib.delete();
    }
}
