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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private static DBManager instance = null;
    private PersistenceManagerFactory pmf = null;
    private final static Logger LOG = Logger.getLogger(DBManager.class.getName());

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
            LOG.log(Level.INFO, "Cogiendo Empleado con EMAIL: {0}", email);
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
            LOG.log(Level.SEVERE, "Error obteniendo empleado: {0}", ex.getStackTrace());
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
            LOG.log(Level.INFO, "* Delete an object: {0}", object);

            pm.deletePersistent(object);

            tx.commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "$ Error deleting an object: {0}", ex.getMessage());
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
            LOG.log(Level.INFO, "* Storing an object: {0}", object);
            pm.makePersistent(object);
            tx.commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "$ Error storing an object: {0}", ex.getStackTrace());
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
        /**
         * JDO puede llegar a tener una forma de meter un dato con ForeignKeys en la
         * base de datos y comprobar si existe en la base de datos. Pero no se cual
         * puede ser. Para eso lo que vamos a hacer es comprobar si existen Empleados,
         * Clientes y productos iguales en la BD.
         */
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.getFetchPlan().setMaxFetchDepth(4);
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            LOG.log(Level.INFO, "* Storing an object: {0}", factura);

            // Ver todos los clientes. Si existe usarlo, si no descartarlos y usar el
            // cliente que viene con el propio objeto factura.
            Cliente cl = factura.getCliente(); // Ponemos temporalmente el cliente del objeto factura.
            Extent<Cliente> ex_cliente = pm.getExtent(Cliente.class, true);
            Iterator<Cliente> iter_cliente = ex_cliente.iterator();
            while (iter_cliente.hasNext()) {
                Cliente cliente_temporal = (Cliente) iter_cliente.next();
                if (cliente_temporal.getDni().equals(cl.getDni())) {
                    LOG.log(Level.INFO, "* Found a client with the same DNI: {0}", cliente_temporal.getDni());
                    cl = cliente_temporal; // Si existe simplemente usamos el que viene de la base de datos.
                }
            }
            factura.setCliente(cl); // Hacer set del cliente

            // Ver todos los empleados. Si existe usarlo, si no descartarlos y usar el
            // empleado que viene con el propio objeto factura.
            Empleado em = factura.getEmpleado(); // Ponemos temporalmente el cliente del objeto factura.
            Extent<Empleado> ex_empleado = pm.getExtent(Empleado.class, true);
            Iterator<Empleado> iter_empleado = ex_empleado.iterator();
            while (iter_empleado.hasNext()) {
                Empleado empleado_temporal = (Empleado) iter_empleado.next();
                if (empleado_temporal.getDni().equals(em.getDni())) {
                    LOG.log(Level.INFO, "* Found a employee with the same DNI: {0}", empleado_temporal.getDni());
                    em = empleado_temporal; // Si existe simplemente usamos el que viene de la base de datos.
                }
            }
            factura.setEmpleado(em); // Hacer set del empleado

            /**
             * Esto va a ser dificil. Hay que buscar diferentes productos en la BD. Si
             * existe usar el de la DB. Si no existe crearlo. Creo que lo mejor para esta
             * solucion es simplemente hacer una nueva lista, e ir guardando poco a poco.
             * Estoy seguro que la eficiencia de esta solucion es mala. Pero no se me ocurre
             * como mejorarla.
             */

            List<Producto> productos = factura.getProductos();
            List<Producto> final_productos = new ArrayList<>();
            Extent<Producto> ex_producto = pm.getExtent(Producto.class, true);
            Iterator<Producto> iter = ex_producto.iterator();
            Producto p = null;
            for (Producto p_for : productos) {
                boolean found = false;
                while (iter.hasNext()) {
                    Producto p_bd = (Producto) iter.next();
                    if (p_bd.getId().equals(p_for.getId())) {
                        found = true;
                        p = p_bd;
                    }
                }
                if (!found) {
                    p = p_for;
                }
                final_productos.add(p);
            }
            factura.setProductos(final_productos); // Guardar el resultado final.
            factura.setPrecio(factura.calcularPrecio()); // Acturalizar el precio para minimizar errores.
            pm.makePersistent(factura); // Finalmente guardar la factura.
            tx.commit();
            pm.makeTransient(factura);
            pm.makeTransient(factura.getEmpleado());
            pm.makeTransient(factura.getCliente());
            pm.makeTransientAll(factura.getProductos());
            LOG.log(Level.INFO, "* Stored: {0}", factura);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "$ Error storing an object: {0}", ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
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
            LOG.log(Level.INFO, "Cogiendo usuario con DNI: {0}", DNI);
            tran.begin();

            Extent<Empleado> extension = pm.getExtent(Empleado.class, true);
            for (Empleado empleadoEXT : extension) {
                if (empleadoEXT.getDni().equals(DNI)) {
                    empleado = new Empleado(empleadoEXT.getDni(), empleadoEXT.getNombre(), empleadoEXT.getDireccion(),
                            empleadoEXT.getEmail(), empleadoEXT.getTelefono(), empleadoEXT.getPuesto(),
                            empleadoEXT.getFcha_nacimiento(), empleadoEXT.getFcha_empleado(), empleadoEXT.getSueldo(),
                            empleadoEXT.getContrasena());
                }
            }

            tran.commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error obteniendo empleado: {0}", ex.getMessage());
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
            LOG.log(Level.INFO, "Cogiendo Cliente con DNI: {0}", dni);
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
            LOG.log(Level.SEVERE, "Error obteniendo empleado: {0}", ex.getMessage());
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
            LOG.log(Level.INFO, "Cogiendo producto con id: {0}", id);
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
            LOG.log(Level.SEVERE, "Error obteniendo empleado: {0}", ex.getMessage());
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
                if (Objects.equals(producto_a_cambiar.getId(), producto.getId())) {
                    LOG.log(Level.INFO, "* Updating: {0}\n* To: {1}", new Object[] { producto_a_cambiar, producto });
                    producto_a_cambiar.setAnyadidos(producto.isAnyadidos());
                    producto_a_cambiar.setGrosor(producto.getGrosor());
                    producto_a_cambiar.setMedida(producto.getMedida());
                    producto_a_cambiar.setPeso(producto.getPeso());
                    producto_a_cambiar.setPrecio(producto.getPeso());
                }
            }
            tx.commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "$ Error updating: {0}", ex.getMessage());
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
            LOG.log(Level.INFO, "Eliminando producto con id: {0}", id);
            tx.begin();

            Extent<Producto> e = pm.getExtent(Producto.class, true);
            Iterator<Producto> iter = e.iterator();
            while (iter.hasNext()) {
                Producto producto = (Producto) iter.next();
                if (Objects.equals(producto.getId(), id)) {
                    pm.deletePersistent(producto);
                }
            }

            tx.commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error obteniendo empleado: {0}", ex.getMessage());
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

        List<Empleado> empleados = new ArrayList<>();

        try {
            LOG.info("* Querying all users");
            tx.begin();

            Extent<Empleado> empleadoExtent = pm.getExtent(Empleado.class, true);

            for (Empleado empleado : empleadoExtent) {
                pm.makeTransient(empleado);
                empleados.add(empleado);
            }

            tx.commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "$ Error querying all users: {0}", ex.getMessage());
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

        List<Producto> productos = new ArrayList<>();

        try {
            LOG.info("* Viendo todos productos");
            tx.begin();

            Extent<Producto> productoExtent = pm.getExtent(Producto.class, true);

            for (Producto producto : productoExtent) {
                pm.makeTransient(producto);
                productos.add(producto);
            }

            tx.commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "$ Error viendo todos productos: {0}", ex.getMessage());
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
                if (empleado_a_cambiar.getDni() == null ? empleado.getDni() == null
                        : empleado_a_cambiar.getDni().equals(empleado.getDni())) {
                    LOG.log(Level.INFO, "* Updating: {0}\n* To: {1}", new Object[] { empleado_a_cambiar, empleado });
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
            LOG.log(Level.SEVERE, "$ Error updating: {0}", ex.getMessage());
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
            LOG.log(Level.INFO, "Eliminando empleado con id: {0}", id);
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
            LOG.log(Level.SEVERE, "Error obteniendo empleado: {0}", ex.getMessage());
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

        List<Factura> facturas = new ArrayList<>();

        try {
            LOG.info("* Viendo todos facturas");
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
            LOG.log(Level.SEVERE, "$ Error viendo todos facturas: {0}", ex.getMessage());
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
                    LOG.log(Level.INFO, "* Updating: {0}\n* To: {1}", new Object[] { factura_a_cambiar, factura });
                    factura_a_cambiar.setCliente(factura.getCliente());
                    factura_a_cambiar.setEmpleado(factura.getEmpleado());
                    factura_a_cambiar.setFcha_factura(factura.getFcha_factura());
                    factura_a_cambiar.setPrecio(factura.getPrecio());
                    factura_a_cambiar.setProductos(factura.getProductos());
                }
            }
            tx.commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "$ Error updating: {0}", ex.getMessage());
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
            LOG.log(Level.INFO, "Eliminando factura con id: {0}", id);
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
            LOG.log(Level.SEVERE, "Error obteniendo factura: {0}", ex.getMessage());
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

        List<Cliente> clientes = new ArrayList<>();

        try {
            LOG.info("* Viendo todos clientes");
            tx.begin();

            Extent<Cliente> clienteExtent = pm.getExtent(Cliente.class, true);

            for (Cliente cliente : clienteExtent) {
                pm.makeTransient(cliente);
                clientes.add(cliente);
            }

            tx.commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "$ Error viendo todos clientes: {0}", ex.getMessage());
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
                if (cliente_a_cambiar.getDni() == null ? cliente.getDni() == null
                        : cliente_a_cambiar.getDni().equals(cliente.getDni())) {
                    LOG.log(Level.INFO, "* Updating: {0}\n* To: {1}", new Object[] { cliente_a_cambiar, cliente });
                    cliente_a_cambiar.setNombre(cliente.getNombre());
                    cliente_a_cambiar.setApellidos(cliente.getApellidos());
                    cliente_a_cambiar.setContrasena(cliente.getContrasena());
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
            LOG.log(Level.INFO, "Eliminando cliente con dni: {0}", dni);
            tx.begin();

            Extent<Cliente> e = pm.getExtent(Cliente.class, true);
            Iterator<Cliente> iter = e.iterator();
            while (iter.hasNext()) {
                Cliente cliente = (Cliente) iter.next();
                if (cliente.getDni() == null ? dni == null : cliente.getDni().equals(dni)) {
                    pm.deletePersistent(cliente);
                }
            }

            tx.commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error obteniendo cliente: {0}", ex.getMessage());
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

        List<Administrador> administradores = new ArrayList<>();

        try {
            LOG.info("* Viendo todos administradores");
            tx.begin();

            Extent<Administrador> administradorExtent = pm.getExtent(Administrador.class, true);

            for (Administrador admin : administradorExtent) {
                pm.makeTransient(admin);
                administradores.add(admin);
            }

            tx.commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "$ Error viendo todos administradores: {0}", ex.getMessage());
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
                    LOG.log(Level.INFO, "* Updating: {0}\n* To: {1}",
                            new Object[] { administrador_a_cambiar, administadror });
                    administrador_a_cambiar.setContrasena(administadror.getContrasena());
                    administrador_a_cambiar.setNombre(administadror.getNombre());
                    administrador_a_cambiar.setApellido(administadror.getApellido());
                    administrador_a_cambiar.setEmail(administadror.getEmail());
                    administrador_a_cambiar.setTelefono(administadror.getTelefono());
                }
            }
            tx.commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "$ Error updating: {0}", ex.getMessage());
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
            LOG.log(Level.INFO, "Eliminando administrador con id: {0}", id);
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
            LOG.log(Level.SEVERE, "Error obteniendo Administrador: {0}", ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

}
