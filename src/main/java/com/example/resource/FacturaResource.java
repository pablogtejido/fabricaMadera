package com.example.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.Factura;
import com.example.db.DBManager;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("factura")
public class FacturaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Factura> getFactura() {
        return DBManager.getInstance().getFacturas();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Factura modificarProducto(Factura factura) {
        DBManager.getInstance().updateFactura(factura);
        return factura;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Factura crearProducto(Factura factura) {
        DBManager.getInstance().store(factura);
        return factura;
    }

    @DELETE
    @Path("/ids/{facturaId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String eliminarFactura(@PathParam("facturaId") long id) {
        DBManager.getInstance().deleteFacturaById(id);
        return "Done";
    }

}
