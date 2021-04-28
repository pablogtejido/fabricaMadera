package com.atenea.rsh;

import java.util.List;

import com.atenea.data.Producto;

import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class ProductoRSH {

    static ProductoRSH instance = null;
    Client client;
    WebTarget target;

    public static ProductoRSH getInstance() {
        if (instance == null) {
            instance = new ProductoRSH();
        }
        return instance;
    }

    private ProductoRSH() {
        client = ClientBuilder.newClient();
        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        target = client.target("http://localhost:8080/myapp").path("producto"); // http://localhost:8080/myapp/producto
    }

    /**
     * Ver todas las facturas del servidor.
     * 
     * @return <Code>List<Producto></Code> Lista con productos
     */
    public List<Producto> verFacturas() {
        Invocation.Builder ib = target.request(); // Construir la petición
        Response response = ib.get(); // Realizar una petición GET
        List<Producto> productos = response.readEntity(new GenericType<List<Producto>>() { // Crear una lista de
                                                                                           // productos
        });
        return productos;
    }

    /**
     * Hacer una petición PUT al servidor para guardar un producto.
     * 
     * @param <Code>Producto</Code> Producto a guardar.
     * @return <Code>Producto</Code> Producto con el id ya guardado en la DB.
     */
    public Producto guardarFactura(Producto producto) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.put(Entity.entity(producto, MediaType.APPLICATION_JSON));
        Producto productoConID = response.readEntity(Producto.class);
        return productoConID;
    }

    /**
     * Modificar la factura en el servidor
     * 
     * @param <Code>Factura</Code> Factura a modificar.
     * @return <Code>Factura</Code> Factura con el id ya guardado en la DB.
     */
    public Producto modificarFactura(Producto producto) {
        Invocation.Builder ib = target.request(MediaType.APPLICATION_JSON);
        Response response = ib.build("PATCH", Entity.json(producto)).invoke();
        Producto productoConID = response.readEntity(Producto.class);
        return productoConID;
    }

    /**
     * Borrar una factura de la BD.
     * 
     * @param <Code>Factura</Code> Factura a borrar.
     */
    public void borrarFactura(Producto producto) {
        Invocation.Builder ib = target.path("/ids/" + producto.getId()).request();
        ib.delete();
    }
}
