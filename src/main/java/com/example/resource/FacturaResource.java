package com.example.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.Cliente;
import com.example.Empleado;
import com.example.EnumPuestoEmpleados;
import com.example.Factura;
import com.example.Producto;
import com.example.db.DBManager;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("factura")
public class FacturaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Factura> getFactura() {
        return DBManager.getInstance().getFacturas();
    }

}
