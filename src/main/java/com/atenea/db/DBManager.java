package com.atenea.db;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import com.atenea.data.Administrador;
import com.atenea.data.Cliente;
import com.atenea.data.Empleado;
import com.atenea.data.Factura;
import com.atenea.data.Producto;

public class DBManager {
    private static DBManager instance = null;
    private PersistenceManagerFactory pmf = null;

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }

        return instance;
    }

    private DBManager() {
        pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
    }

    public Empleado getEmpleadoPorEmail(String email) throws DBException {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tran = pm.currentTransaction();

        Empleado empleado = null;

        try {
            System.out.println("Cogiendo Empleado con EMAIL: " + email);
            tran.begin();

            Extent<Empleado> extension = pm.getExtent(Empleado.class, true);
            for (Empleado empleadoEXT : extension) {
                if (empleadoEXT.getEmail().equals(email)) {
                    empleado = new Empleado(empleadoEXT.getDni(), empleadoEXT.getNombre(), empleadoEXT.getDireccion(),
                            empleadoEXT.getEmail(), empleadoEXT.getTelefono(), empleadoEXT.getPuesto(),
                            empleadoEXT.getFcha_nacimiento(), empleadoEXT.getFcha_empleado(), empleadoEXT.getSueldo(),
                            empleadoEXT.getContrasena());
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
     * Guardar un cliente en la DB
     * 
     * @param cliente
     */
    public void store(Cliente cliente) {
        DBManager.getInstance().storeObjectInDB(cliente);
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
     * Guardar un administrador en la DB
     * 
     * @param factura
     */

    public void store(Administrador admin) {
        DBManager.getInstance().storeObjectInDB(admin);
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
     * @param cliente
     */
    public void delete(Producto producto) {
        DBManager.getInstance().deleteObjectFromDB(producto);
    }

    /**
     * Borrar un cliente de la DB
     * 
     * @param cliente
     */
    public void delete(Cliente cliente) {
        DBManager.getInstance().deleteObjectFromDB(cliente);
    }

    /**
     * Obtener un empleado en base a su dni
     * 
     * @param dni
     * @return
     */
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
                            empleado.getFcha_nacimiento(), empleado.getFcha_empleado(), empleado.getSueldo(),
                            empleado.getContrasena());
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
     * Obtener un cliente en base a su dni
     * 
     * @param dni
     * @return
     */
    public Cliente getCliente(String dni) throws DBException {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tran = pm.currentTransaction();

        Cliente cliente = null;

        try {
            System.out.println("Cogiendo Cliente con DNI: " + dni);
            tran.begin();

            Extent<Cliente> extension = pm.getExtent(Cliente.class, true);
            for (Cliente clienteEXT : extension) {
                if (clienteEXT.getDni().equals(dni)) {
                    cliente = new Cliente(clienteEXT.getDni(), clienteEXT.getNombre(), clienteEXT.getApellidos(),
                            clienteEXT.getContrasena());
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

        return cliente;
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

    /**
     * Get productos de la DB
     * 
     * @return lista de productos
     */
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
            Extent<Factura> e = pm.getExtent(Factura.class, true);
            Iterator<Factura> iter = e.iterator();
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

    /**
     * Actualizar una factura existente
     * 
     * @param producto
     */
    public void updateFactura(Factura factura) {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(4);
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();

            Extent<Factura> e = pm.getExtent(Factura.class, true);
            Iterator<Factura> iter = e.iterator();
            while (iter.hasNext()) {
                Factura factura_a_cambiar = (Factura) iter.next();
                if (factura_a_cambiar.getId() == factura.getId()) {
                    System.out.println("* Updating: " + factura_a_cambiar + "\n* To: " + factura);
                    factura_a_cambiar.setCliente(factura.getCliente());
                    factura_a_cambiar.setEmpleado(factura.getEmpleado());
                    factura_a_cambiar.setFcha_factura(factura.getFcha_factura());
                    factura_a_cambiar.setPrecio(factura.getPrecio());
                    factura_a_cambiar.setProducto(factura.getProductos());
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
     * Borrar una factura por su id
     * 
     * @param id
     */
    public void deleteFacturaById(long id) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            System.out.println("Eliminando factura con id: " + id);
            tx.begin();

            Extent<Factura> e = pm.getExtent(Factura.class, true);
            Iterator<Factura> iter = e.iterator();
            while (iter.hasNext()) {
                Factura factura = (Factura) iter.next();
                if (factura.getId() == id) {
                    pm.deletePersistent(factura);
                }
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("Error obteniendo factura: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    /**
     * Get clientes de la DB
     * 
     * @return lista de clientes
     */
    public List<Cliente> getClientes() {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(4);
        Transaction tx = pm.currentTransaction();

        List<Cliente> clientes = new ArrayList<Cliente>();

        try {
            System.out.println("* Viendo todos clientes");
            tx.begin();

            Extent<Cliente> clienteExtent = pm.getExtent(Cliente.class, true);

            for (Cliente cliente : clienteExtent) {
                clientes.add(cliente);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("$ Error viendo todos clientes: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
        return clientes;

    }

    /**
     * Actualizar un cliente existente
     * 
     * @param cliente
     */
    public void updateCliente(Cliente cliente) {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(4);
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();

            Extent<Cliente> e = pm.getExtent(Cliente.class, true);
            Iterator<Cliente> iter = e.iterator();
            while (iter.hasNext()) {
                Cliente cliente_a_cambiar = (Cliente) iter.next();
                if (cliente_a_cambiar.getDni() == cliente.getDni()) {
                    System.out.println("* Updating: " + cliente_a_cambiar + "\n* To: " + cliente);
                    cliente_a_cambiar.setNombre(cliente.getNombre());
                    cliente_a_cambiar.setApellidos(cliente.getApellidos());
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
     * Borrar una cliente por su dni
     * 
     * @param dni
     */
    public void deleteClienteByDNI(String dni) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            System.out.println("Eliminando cliente con dni: " + dni);
            tx.begin();

            Extent<Cliente> e = pm.getExtent(Cliente.class, true);
            Iterator<Cliente> iter = e.iterator();
            while (iter.hasNext()) {
                Cliente cliente = (Cliente) iter.next();
                if (cliente.getDni() == dni) {
                    pm.deletePersistent(cliente);
                }
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("Error obteniendo cliente: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public List<Administrador> getAdministrador() {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(4);
        Transaction tx = pm.currentTransaction();

        List<Administrador> administradores = new ArrayList<Administrador>();

        try {
            System.out.println("* Viendo todos administradores");
            tx.begin();

            Extent<Administrador> administradorExtent = pm.getExtent(Administrador.class, true);

            for (Administrador admin : administradorExtent) {
                administradores.add(admin);
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("$ Error viendo todos administradores: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }

        return administradores;

    }

    public void updateAdministrador(Administrador administadror) {
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(4);
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();

            Extent<Administrador> e = pm.getExtent(Administrador.class, true);
            Iterator<Administrador> iter = e.iterator();
            while (iter.hasNext()) {
                Administrador administrador_a_cambiar = (Administrador) iter.next();
                if (administrador_a_cambiar.getId() == administadror.getId()) {
                    System.out.println("* Updating: " + administrador_a_cambiar + "\n* To: " + administadror);
                    administrador_a_cambiar.setContrasena(administadror.getContrasena());
                    administrador_a_cambiar.setNombre(administadror.getNombre());
                    administrador_a_cambiar.setApellido(administadror.getApellido());
                    administrador_a_cambiar.setEmail(administadror.getEmail());
                    administrador_a_cambiar.setTelefono(administadror.getTelefono());
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

    public void deleteAdministradorById(int id) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            System.out.println("Eliminando administrador con id: " + id);
            tx.begin();

            Extent<Administrador> e = pm.getExtent(Administrador.class, true);
            Iterator<Administrador> iter = e.iterator();
            while (iter.hasNext()) {
                Administrador administrador = (Administrador) iter.next();
                if (administrador.getId() == id) {
                    pm.deletePersistent(administrador);
                }
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println("Error obteniendo Administrador: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

}
