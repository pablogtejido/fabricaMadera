package com.atenea.rsh;

import java.util.List;

import com.atenea.data.Factura;

import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class FacturaRSH {

    static FacturaRSH instance = null;
    Client client;
    WebTarget target;

    public static FacturaRSH getInstance() {
        if (instance == null) {
            instance = new FacturaRSH();
        }
        return instance;
    }

    private FacturaRSH() {
        client = ClientBuilder.newClient();
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        target = client.target("http://localhost:8080/myapp").path("factura"); // http://localhost:8080/myapp/factura
    }

    /**
     * Ver todas las facturas del servidor.
     * 
     * @return <Code>List<Factura></Code> Lista con facturas
     */
    public List<Factura> verFacturas() {
        Invocation.Builder ib = target.request(); // Construir la petición
        Response response = ib.get(); // Realizar una petición GET
        List<Factura> facturas = response.readEntity(new GenericType<List<Factura>>() { // Crear una lista de facturas
        });
        return facturas;
    }

    /**
     * Hacer una petición PUT al servidor para guardar la factura.
     * 
     * @param <Code>Factura</Code> Factura a guardar.
     * @return <Code>Factura</Code> Factura con el id ya guardado en la DB.
     */
    public Factura guardarFactura(Factura factura) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.put(Entity.entity(factura, MediaType.APPLICATION_JSON));
        Factura facturaConID = response.readEntity(Factura.class);
        return facturaConID;
    }

    /**
     * Modificar la factura en el servidor
     * 
     * @param <Code>Factura</Code> Factura a modificar.
     * @return <Code>Factura</Code> Factura con el id ya guardado en la DB.
     */
    public Factura modificarFactura(Factura factura) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.build("PATCH", Entity.json(factura)).invoke(); // Invocation.Builder no tiene un metodo
                                                                              // para patch. 😐.
        Factura facturaConID = response.readEntity(Factura.class);
        return facturaConID;
    }

    /**
     * Borrar una factura de la BD.
     * 
     * @param <Code>Factura</Code> Factura a borrar.
     */
    public void borrarFactura(Factura factura) {
        Invocation.Builder ib = target.path("/ids/" + factura.getId()).request();
        ib.delete();
    }
}
