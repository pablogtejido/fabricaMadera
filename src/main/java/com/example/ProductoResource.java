package com.example;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.example.db.DBManager;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("producto")
public class ProductoResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producto> getProductos() {
		
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Query<Producto> q = pm.newQuery(Producto.class);
		q.setOrdering("peso asc");
		
		List<Producto> productos = q.executeList();

		pm.close();
		
		return productos;
	}
	
	public void eliminarProducto() {
		List<Producto> productos = DBManager.getInstance().getProductos();
		
		for (Producto producto : productos) {
			DBManager.getInstance().delete(producto);
		}	
		
	}
	
	/*
	 * crearProducto()
	 * modificarProducto()
	 * eliminarProducto()
	 * 
	 */
}