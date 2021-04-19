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
    public String getFactura() {
        // TODO: El metodo no esta terminado. Es simplemente ver la creación de la
        // tabla.

        Factura f = new Factura();
        f.setCliente(new Cliente("aaaaaaa", "Aitor", "Ruiz Garcia"));
        f.setEmpleado(new Empleado("aaaaaaa", "Aitor", "Ruiz", "aa", "@", EnumPuestoEmpleados.OPERARIO, new Date(),
                new Date(), 30));
        f.setFcha_factura(new Date());
        f.setPrecio(30.5);
        List<Producto> pr = new ArrayList<Producto>();
        pr.add(new Producto());
        f.setProducto(pr);
        DBManager.getInstance().store(f);
        return "Done";
    }

}
