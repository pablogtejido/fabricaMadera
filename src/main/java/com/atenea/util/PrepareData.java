package com.atenea.util;

import java.util.Date;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import com.atenea.data.Cliente;
import com.atenea.data.Empleado;
import com.atenea.data.EnumPuestoEmpleados;

public class PrepareData {

	public static void main(String[] args) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
	
			
			
			Cliente cliente1 = new Cliente("58441139A", "Juan", "Lopez", "1234");
			pm.makePersistent(cliente1);
			

			Cliente cliente2 = new Cliente("36957841K", "Silvia", "Montejo", "1234");
			pm.makePersistent(cliente2);
			

			Cliente cliente3 = new Cliente("79845211T", "Luis", "Alonso" ,"1234");
			pm.makePersistent(cliente3);
			
			
			Cliente cliente4 = new Cliente("66558842G", "Silvia", "Montejo" , "1234");
			pm.makePersistent(cliente4);
			

			Cliente cliente5= new Cliente("45548696P", "Luis", "Alonso", "1234");
			pm.makePersistent(cliente5);
			

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
}