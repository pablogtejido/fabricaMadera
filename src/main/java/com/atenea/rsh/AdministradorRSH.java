package com.atenea.rsh;

import java.util.List;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import com.atenea.data.Administrador;
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
     * Ver todos los administradores
     * 
     * @return <Code>List<Administrador></Code> Lista con administradores
     */
    public List<Administrador> verAdministrador() {
        Invocation.Builder ib = target.request(); // Construir la petición
        Response response = ib.get(); // Realizar una petición GET
        List<Administrador> admin = response.readEntity(new GenericType<List<Administrador>>() { // Crear una lista de clientes
        });
        return admin;
    }

    /**
     * Hacer una petición PUT al servidor para guardar el administrador
     * 
     * @param Administrador a guardar
     * @return <Code>Administrador</Code> Administrador con el id ya guardado en la DB
     */
    public Administrador guardarAdministrador(Administrador admin) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.put(Entity.entity(admin, MediaType.APPLICATION_JSON));
        Administrador administradorConId = response.readEntity(Administrador.class);
        return administradorConId;
    }

    /**
     * Modificar el administrador
     * 
     * @param <Code>Administrador</Code> Administrador a modificar
     * @return <Code>Administrador</Code> Administrador con el id ya guardado en la BD
     */
    public Administrador modificarAdministrador(Administrador admin) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.build("PATCH", Entity.json(admin)).invoke();

        Administrador administradorConId = response.readEntity(Administrador.class);
        return administradorConId;
    }

    /**
     * Borrar un Administrador de la BD
     * 
     * @param <Code>Administrador</Code> Administrador a borrar
     */
    public void borrarAdministrador(Administrador admin) {
        Invocation.Builder ib = target.path("/ids/" + admin.getDni()).request();
        ib.delete();
    }
}
