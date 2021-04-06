package com.example;

import java.util.List;

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

@Path("/producto")
public class ProductoResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producto> getProductos() {
		List<Producto> productos = DBManager.getInstance().getProductos();
		return productos;
	}

	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String modificarProducto(Producto producto) {
		DBManager.getInstance().updateProducto(producto);
		return "Done";
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String crearProducto(Producto producto) {
		DBManager.getInstance().store(producto);
		return "Done";
	}

	@DELETE
	@Path("/ids/{productId}") // HTTP DELETE no acepta un cuerpo aunque la MDN dice que si.
	@Produces(MediaType.TEXT_PLAIN) // Asi que hay que poner una ruta distita para poder difereciarlo del resto.
	public String eliminarProducto(@PathParam("productId") Long id) {
		DBManager.getInstance().deleteProductoById(id);
		return "Done";
	}
}