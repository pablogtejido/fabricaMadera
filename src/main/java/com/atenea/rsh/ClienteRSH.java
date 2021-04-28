package com.atenea.rsh;

import java.util.List;

import com.atenea.data.Cliente;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class ClienteRSH {

    static ClienteRSH instance = null;
    Client client;
    WebTarget target;

    public static ClienteRSH getInstance() {
        if (instance == null) {
            instance = new ClienteRSH();
        }
        return instance;
    }

    public ClienteRSH() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/myapp").path("cliente"); // http://localhost:8080/myapp/cliente
    }

    /**
     * Ver todas los clientes del servidor.
     * 
     * @return <Code>List<Cliente></Code> Lista con clientes
     */
    public List<Cliente> verClientes() {
        Invocation.Builder ib = target.request(); // Construir la petición
        Response response = ib.get(); // Realizar una petición GET
        List<Cliente> clientes = response.readEntity(new GenericType<List<Cliente>>() { // Crear una lista de clientes
        });
        return clientes;
    }

    /**
     * Hacer una petición PUT al servidor para guardar el cliente.
     * 
     * @param Cliente a guardar.
     * @return <Code>Cliente</Code> Cliente con el dni ya guardado en la DB.
     */
    public Cliente guardarCliente(Cliente cliente) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.put(Entity.entity(cliente, MediaType.APPLICATION_JSON));
        Cliente clienteConDni = response.readEntity(Cliente.class);
        return clienteConDni;
    }

    /**
     * Modificar el cliente en el servidor
     * 
     * @param <Code>Cliente</Code> Cliente a modificar
     * @return <Code>Cliente</Code> Cliente con el dni ya guardado en la BD
     */
    public Cliente modificarCliente(Cliente cliente) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.build("PATH", Entity.json(cliente)).invoke();

        Cliente clienteConDni = response.readEntity(Cliente.class);
        return clienteConDni;
    }

    /**
     * Borrar un cliente de la BD
     * 
     * @param <Code>Cliente</Code> Cliente a borrar
     */
    public void borrarCliente(Cliente cliente) {
        Invocation.Builder ib = target.path("/ids/" + cliente.getDni()).request();
        ib.delete();
    }

}
