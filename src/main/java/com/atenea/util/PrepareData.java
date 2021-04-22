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
	
			Empleado empleado1 = new Empleado("68432155O", "Paco Lopez", "Calle Ave del Paraiso, 13, 4Izq",
	                "pacolopez@gmail.com", "654321789", EnumPuestoEmpleados.OPERARIO,  new Date("12/04/1990 15:30:35"),  new Date("08/07/2020 00:00:00"), 1200.50, "1234");
			pm.makePersistent(empleado1);
			
	        Empleado empleado2 = new Empleado("35661232P", "Aitor Ruiz Garcia", "Calle Zorroza, 11, 1B",
	                "aitoruiz@gmail.com", "621852478", EnumPuestoEmpleados.DIRECTIVA, new Date("10/01/1970 01:09:07"),  new Date("30/06/2000 18:00:35"),  4300, "1234");
	        pm.makePersistent(empleado2);
	        
	        Empleado empleado3 = new Empleado("22396365I", "Pablo Gonzalez Tejido", "Calle Leioa, 42, 3B",
	                "pablogtejido@gmail.com", "753123258", EnumPuestoEmpleados.DIRECTIVA,new Date("25/12/1983 08:56:21"),  new Date("27/02/2005 11:12:45"),   4300, "1234");
	        pm.makePersistent(empleado3);
	        
	        Empleado empleado4 = new Empleado("42134567U", "Koldo Moya", "Calle Sestao, 2, 1B", "koldo.moya@gmail.com",
	                "673258014", EnumPuestoEmpleados.DIRECTIVA,new Date("27/10/1974 05:04:00"),  new Date("29/01/2007 12:00:00"),   4300, "1234");
	        pm.makePersistent(empleado4);
	        
	        Empleado empleado5 = new Empleado("75420903Y", "Nerea Diez Landin", "Calle Portu, 57, 2B",
	                "nereadiez@gmail.com", "658007220", EnumPuestoEmpleados.DIRECTIVA, new Date("14/05/1988 19:27:31"),  new Date("11/09/2012 10:14:11"),  4300, "1234");
	        pm.makePersistent(empleado5);
			
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