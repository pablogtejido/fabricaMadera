package com.example.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import com.example.Empleado;
import com.example.EnumPuestoEmpleados;
import com.example.Producto;

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
    	//new Date(anyo, mes, dia)
    	//Anyo de la fecha 1900 + int = anyo que queremos
        Empleado empleado1 = new Empleado("78432155O", "Paco Lopez", "Calle Ave del Paraiso, 13, 4Izq", "pacolopez@gmail.com", "654321789", EnumPuestoEmpleados.OPERARIO, new Date(98, 11, 13), new Date(121, 04, 06), 1200.50);
        Empleado empleado2 = new Empleado("45661232P", "Aitor Ruiz Garcia", "Calle Zorroza, 11, 1B", "aitoruiz@gmail.com", "621852478", EnumPuestoEmpleados.DIRECTIVA, new Date(100, 8, 13), new Date(120, 03, 01), 4300);
        Empleado empleado3 = new Empleado("12396365I", "Pablo Gonzalez Tejido", "Calle Leioa, 42, 3B", "pablogtejido@gmail.com", "753123258", EnumPuestoEmpleados.DIRECTIVA, new Date(100, 10, 15), new Date(120, 03, 01), 4300);
        Empleado empleado4 = new Empleado("52134567U", "Koldo Moya", "Calle Sestao, 2, 1B", "koldo.moya@gmail.com", "673258014", EnumPuestoEmpleados.DIRECTIVA, new Date(100, 12, 13), new Date(120, 03, 01), 4300);
        Empleado empleado5 = new Empleado("85420903Y", "Nerea Diez Landin", "Calle Portu, 57, 2B", "nereadiez@gmail.com", "658007220", EnumPuestoEmpleados.DIRECTIVA, new Date(100, 7, 10), new Date(120, 03, 01), 4300);
        Empleado empleado6 = new Empleado("24586533T", "Francisco Perez", "Calle Gran Via, 88, 6Drcha", "franperez@abogados.com", "717654313", EnumPuestoEmpleados.LEGAL, new Date(62, 12, 12), new Date(120, 03, 01), 4300);
    }

    /**
     * Borra un objeto de la DB
     * 
     * @param object
     */
    public void deleteObjectFromDB(Object object) {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(4);
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            System.out.println(" * Delete an object: " + object);

            pm.deletePersistent(object);

            tx.commit();
        } catch (Exception ex) {
            System.out.println(" $ Error deleting an object: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
    }

    public void storeObjectInDB(Object object) {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(4);
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            System.out.println("  * Storing an object: " + object);
            pm.makePersistent(object);
            tx.commit();
        } catch (Exception ex) {
            System.out.println("  $ Error storing an object: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
    }

    /**
     * Guardar un empleado en la DB
     * 
     * @param empleado
     */
    public void store(Empleado empleado) {
        DBManager.getInstance().storeObjectInDB(empleado);
    }

    /**
     * Guardar un producto en la DB
     * 
     * @param producto
     */
    public void store(Producto producto) {
        DBManager.getInstance().storeObjectInDB(producto);
    }

    /**
     * Borrar un empleado de la DB
     * 
     * @param empleado
     */
    public void delete(Empleado empleado) {
        DBManager.getInstance().deleteObjectFromDB(empleado);
    }

    /**
     * Borrar un producto de la DB
     * 
     * @param empleado
     */
    public void delete(Producto producto) {
        DBManager.getInstance().deleteObjectFromDB(producto);
    }

    public Empleado getEmpleado(String DNI) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tran = pm.currentTransaction();

        Empleado empleado = null;
        try {
            System.out.println("Cogiendo usuario con DNI: " + DNI);
            tran.begin();

            Extent<Empleado> extension = pm.getExtent(Empleado.class, true);
            for (Empleado empleadoEXT : extension) {
                if (empleadoEXT.getDni().equals(DNI)) {
                    empleado = new Empleado(empleado.getDni(), empleado.getNombre(), empleado.getDireccion(),
                            empleado.getEmail(), empleado.getTelefono(), empleado.getPuesto(),
                            empleado.getFcha_nacimiento(), empleado.getFcha_empleado(), empleado.getSueldo());
                }
            }

            tran.commit();
        } catch (Exception ex) {
            System.out.println(" Error obteniendo empleado: " + ex.getMessage());
        } finally {
            if (tran != null && tran.isActive()) {
                tran.rollback();
            }

            pm.close();
        }

        return empleado;
    }

    /**
     * Obtener un producto en base a su id
     * 
     * @param id
     * @return
     */
    public Producto getProducto(int id) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tran = pm.currentTransaction();

        Producto producto = null;
        try {
            System.out.println("Cogiendo producto con id: " + id);
            tran.begin();

            Extent<Producto> productos = pm.getExtent(Producto.class, true);
            for (Producto productoEXT : productos) {
                if (productoEXT.getId() == id) {
                    producto = productoEXT;
                }
            }

            tran.commit();
        } catch (Exception ex) {
            System.out.println(" Error obteniendo empleado: " + ex.getMessage());
        } finally {
            if (tran != null && tran.isActive()) {
                tran.rollback();
            }

            pm.close();
        }

        return producto;
    }

    /**
     * Get empleados de la DB
     * 
     * @return lista de empleados
     */
    public List<Empleado> getEmpleados() {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(4);
        Transaction tx = pm.currentTransaction();

        List<Empleado> empleados = new ArrayList<Empleado>();

        try {
            System.out.println("  * Querying all users");
            tx.begin();

            Extent<Empleado> empleadoExtent = pm.getExtent(Empleado.class, true);

            for (Empleado empleado : empleadoExtent) {
                empleados.add(empleado);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("  $ Error querying all users: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }

        return empleados;
    }

    public List<Producto> getProductos() {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(4);
        Transaction tx = pm.currentTransaction();

        List<Producto> productos = new ArrayList<Producto>();

        try {
            System.out.println("  * Querying all users");
            tx.begin();

            Extent<Producto> productoExtent = pm.getExtent(Producto.class, true);

            for (Producto producto : productoExtent) {
                productos.add(producto);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("  $ Error querying all users: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
        return productos;

    }

}
