package com.example.db;

import java.util.Date;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.example.Empleado;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public class DBManager {
    private static DBManager instance = null;
    private PersistenceManagerFactory pmf = null;

    private DBManager() {
        pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
            instance.initializeData();
        }

        return instance;
    }

    private void initializeData() {
        // TODO: Inicializar los datos
    }

    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Empleado> getEmpleados() {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Query<Empleado> q = pm.newQuery(Empleado.class);
		q.setOrdering("nombre desc");
		
		List<Empleado> empleados = q.executeList();

		pm.close();
		
		return empleados;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public int diasEnEmpresa(Empleado empleado) {
		Date fechaActual = new Date();
		Date inicio = empleado.getFcha_empleado();
		
		int dias = (int) ((fechaActual.getTime() - inicio.getTime()) / 86400000);
		
		return dias;
		
	}
}
