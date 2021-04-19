package com.example.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import com.example.Empleado;
import com.example.EmpleadoRSH;
import com.example.Factura;
import com.example.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;




public class DBManager {
    private static DBManager instance = null;
    private PersistenceManagerFactory pmf = null;
    private static Connection conn;
	private final static Logger LOGGER = Logger.getLogger(DBManager.class.getName());


    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }

        return instance;
    }

	public void connect() throws DBException{
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbc = "jdbc:mysql://localhost:3306/fabricaMaderaDB";
			conn = DriverManager.getConnection(jdbc, "spq", "spq");
			System.out.println("Connection" + conn);
			LOGGER.info("Se ha conectado a la base de datos");
		} catch (ClassNotFoundException e) {
			throw new DBException("No se pudo cargar el driver.", e);
		} catch (SQLException e) {
			throw new DBException("No se pudo conectar con la BD.", e);
		}
	}
	
	public void disconnect() throws DBException{
		if(conn != null) {
			try {
				conn.close();
				LOGGER.info("Se ha desconectado de la DB");
			} catch (SQLException e) {
				throw new DBException("No se ha podido cerrar la conexiï¿½n a ala BD", e);
			}
		}
	}
	
	public Empleado getEmpleadoPorEmail(String email) throws DBException{
		Empleado userEmpleado = null;
		String sql = "SELECT DNI, NOMBRE, DIRECCION, EMAIL, TELEFONO, PUESTO, FCHA_NACIMIENTO, FCHA_EMPLEADO, SUELDO, CONTRASENA FROM EMPLEADO WHERE EMAIL = ?";

		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();
			userEmpleado = EmpleadoRSH.toUser(rs);
		} catch (SQLException e) {

			throw new DBException("No se ha podido obtener el usuario", e);
		}
		LOGGER.info("Se ha obtenido el usuario de la BD");
		return userEmpleado;
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
            System.out.println("* Delete an object: " + object);

            pm.deletePersistent(object);

            tx.commit();
        } catch (Exception ex) {
            System.out.println("$ Error deleting an object: " + ex.getMessage());
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
            System.out.println("* Storing an object: " + object);
            pm.makePersistent(object);
            tx.commit();
        } catch (Exception ex) {
            System.out.println("$ Error storing an object: " + ex.getMessage());
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
     * Guardar un factura en la DB
     * 
     * @param factura
     */
    public void store(Factura factura) {
        DBManager.getInstance().storeObjectInDB(factura);
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
                            empleado.getFcha_nacimiento(), empleado.getFcha_empleado(), empleado.getSueldo(), empleado.getContrasena());
                }
            }

            tran.commit();
        } catch (Exception ex) {
            System.out.println("Error obteniendo empleado: " + ex.getMessage());
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
    public Producto getProducto(long id) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        Producto to_return = null;
        try {
            System.out.println("Cogiendo producto con id: " + id);
            tx.begin();

            Extent<Producto> e = pm.getExtent(Producto.class, true);
            Iterator<Producto> iter = e.iterator();
            while (iter.hasNext()) {
                Producto producto = (Producto) iter.next();
                if (producto.getId() == id) {
                    to_return = producto;
                }
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("Error obteniendo empleado: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }

        return to_return;
    }

    /**
     * Actualizar un producto existente
     * 
     * @param producto
     */
    public void updateProducto(Producto producto) {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(4);
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();

            Extent<Producto> e = pm.getExtent(Producto.class, true);
            Iterator<Producto> iter = e.iterator();
            while (iter.hasNext()) {
                Producto producto_a_cambiar = (Producto) iter.next();
                if (producto_a_cambiar.getId() == producto.getId()) {
                    System.out.println("* Updating: " + producto_a_cambiar + "\n* To: " + producto);
                    producto_a_cambiar.setAnyadidos(producto.isAnyadidos());
                    producto_a_cambiar.setCantidad(producto.getCantidad());
                    producto_a_cambiar.setGrosor(producto.getGrosor());
                    producto_a_cambiar.setMedida(producto.getMedida());
                    producto_a_cambiar.setPeso(producto.getPeso());
                    producto_a_cambiar.setPrecio(producto.getPeso());
                }
            }
            tx.commit();
        } catch (Exception ex) {
            System.out.println("$ Error updating: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
    }

    /**
     * Borrar un producto por su id
     * 
     * @param id
     */
    public void deleteProductoById(Long id) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            System.out.println("Eliminando producto con id: " + id);
            tx.begin();

            Extent<Producto> e = pm.getExtent(Producto.class, true);
            Iterator<Producto> iter = e.iterator();
            while (iter.hasNext()) {
                Producto producto = (Producto) iter.next();
                if (producto.getId() == id) {
                    pm.deletePersistent(producto);
                }
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("Error obteniendo empleado: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
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
            System.out.println("* Querying all users");
            tx.begin();

            Extent<Empleado> empleadoExtent = pm.getExtent(Empleado.class, true);

            for (Empleado empleado : empleadoExtent) {
                empleados.add(empleado);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("$ Error querying all users: " + ex.getMessage());
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
            System.out.println("* Viendo todos productos");
            tx.begin();

            Extent<Producto> productoExtent = pm.getExtent(Producto.class, true);

            for (Producto producto : productoExtent) {
                productos.add(producto);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("$ Error viendo todos productos: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
        return productos;

    }

    /**
     * Actualizar un empleado existente
     * 
     * @param producto
     */
    public void updateEmpleado(Empleado empleado) {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(4);
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();

            Extent<Empleado> e = pm.getExtent(Empleado.class, true);
            Iterator<Empleado> iter = e.iterator();
            while (iter.hasNext()) {
                Empleado empleado_a_cambiar = (Empleado) iter.next();
                if (empleado_a_cambiar.getDni() == empleado.getDni()) {
                    System.out.println("* Updating: " + empleado_a_cambiar + "\n* To: " + empleado);
                    empleado_a_cambiar.setDireccion(empleado.getDireccion());
                    empleado_a_cambiar.setEmail(empleado.getEmail());
                    empleado_a_cambiar.setFcha_empleado(empleado.getFcha_empleado());
                    empleado_a_cambiar.setFcha_nacimiento(empleado.getFcha_nacimiento());
                    empleado_a_cambiar.setNombre(empleado.getNombre());
                    empleado_a_cambiar.setPuesto(empleado.getPuesto());
                    empleado_a_cambiar.setSueldo(empleado.getSueldo());
                    empleado_a_cambiar.setTelefono(empleado.getTelefono());
                }
            }
            tx.commit();
        } catch (Exception ex) {
            System.out.println("$ Error updating: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
    }

    /**
     * Borrar un empleado por su id
     * 
     * @param id
     */
    public void deleteEmpleadoById(String id) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            System.out.println("Eliminando empleado con id: " + id);
            tx.begin();

            Extent<Empleado> e = pm.getExtent(Empleado.class, true);
            Iterator<Empleado> iter = e.iterator();
            while (iter.hasNext()) {
                Empleado empleado = (Empleado) iter.next();
                if (empleado.getDni().equals(id)) {
                    pm.deletePersistent(empleado);
                }
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("Error obteniendo empleado: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public List<Factura> getFacturas() {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(4);
        Transaction tx = pm.currentTransaction();

        List<Factura> facturas = new ArrayList<Factura>();

        try {
            System.out.println("* Viendo todos facturas");
            tx.begin();
            Extent e = pm.getExtent(Factura.class, true);
            Iterator iter = e.iterator();
            while (iter.hasNext()) {
                Factura f = (Factura) iter.next();
                // This is just plain dump.
                /*
                 * Tienes que convertir cada elmento en transistenete. Si no se quedan a null.
                 * Si no haces un for y utilizas el makeTransientAll() da error. Un poco mas de
                 * documentación por parte de JDO tampoco vendria mal.
                 * 
                 * time_spent = 3H
                 */
                pm.makeTransient(f.getCliente());
                pm.makeTransient(f.getEmpleado());
                for (Producto p : f.getProductos()) { // Ugh.
                    pm.makeTransient(p);
                }
                pm.makeTransient(f); // 😐
                facturas.add(f);
            }
            tx.commit();
        } catch (Exception ex) {
            System.out.println("$ Error viendo todos facturas: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
        return facturas;
    }

}
