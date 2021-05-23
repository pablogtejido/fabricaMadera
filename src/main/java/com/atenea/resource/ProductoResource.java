package com.atenea.resource;

import java.util.List;

import com.atenea.data.Producto;
import com.atenea.db.DBManager;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("producto")
public class ProductoResource {

	/**
	 * Obtener productos de la base de datos
	 * 
	 * @return lista de productos
	 * 
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producto> getProductos() {
		List<Producto> productos = DBManager.getInstance().getProductos();
		return productos;
	}

	/**
	 * Modificar un producto de la base de datos
	 * 
	 * @param producto
	 * 
	 * @return producto
	 * 
	 */

	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Producto modificarProducto(Producto producto) {
		DBManager.getInstance().updateProducto(producto);
		return producto;
	}

	/**
	 * Almacenar un producto de la base de datos
	 * 
	 * @param producto
	 * 
	 * @return producto
	 * 
	 */

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Producto crearProducto(Producto producto) {
		DBManager.getInstance().store(producto);
		return producto;
	}

	/**
	 * Eliminar un producto de la base de datos
	 * 
	 * @param id
	 * 
	 * @return "Done"
	 * 
	 */

	@DELETE
	@Path("/ids/{productId}") // HTTP DELETE no acepta un cuerpo aunque la MDN dice que si.
	@Produces(MediaType.TEXT_PLAIN) // Asi que hay que poner una ruta distinta para poder difereciarlo del resto.
	public String eliminarProducto(@PathParam("productId") Long id) {
		DBManager.getInstance().deleteProductoById(id);
		return "Done";
	}
}