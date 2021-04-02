package objetosDeDominio;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.*;

public class EmpleadoTest {
    
    public EmpleadoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDni method, of class Empleado.
     */
    @Test
    public void testGetDni() {
        System.out.println("getDni");
        Empleado instance = new Empleado();
        instance.setDni("");
        String expResult = "";
        String result = instance.getDni();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setDni method, of class Empleado.
     */
    @Test
    public void testSetDni() {
        System.out.println("setDni");
        String dni = "";
        Empleado instance = new Empleado();
        instance.setDni(dni);
       
    }
    


    /**
     * Test of getNombre method, of class Empleado.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Empleado instance = new Empleado();
        instance.setNombre("");
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setNombre method, of class Empleado.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Empleado instance = new Empleado();
        instance.setNombre(nombre);
        
    }

    /**
     * Test of getDireccion method, of class Empleado.
     */
    @Test
    public void testGetDireccion() {
        System.out.println("getDireccion");
        Empleado instance = new Empleado();
        instance.setDireccion("");
        String expResult = "";
        String result = instance.getDireccion();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setDireccion method, of class Empleado.
     */
    @Test
    public void testSetDireccion() {
        System.out.println("setDireccion");
        String direccion = "";
        Empleado instance = new Empleado();
        instance.setDireccion(direccion);
        
    }

    /**
     * Test of getEmail method, of class Empleado.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Empleado instance = new Empleado();
        instance.setEmail("");
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setEmail method, of class Empleado.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Empleado instance = new Empleado();
        instance.setEmail(email);
        
    }

    /**
     * Test of getTelefono method, of class Empleado.
     */
    @Test
    public void testGetTelefono() {
        System.out.println("getTelefono");
        Empleado instance = new Empleado();
        instance.setTelefono("");
        String expResult = "";
        String result = instance.getTelefono();
        assertEquals(expResult, result);

    }

    /**
     * Test of setTelefono method, of class Empleado.
     */
    @Test
    public void testSetTelefono() {
        System.out.println("setTelefono");
        String telefono = "";
        Empleado instance = new Empleado();
        instance.setTelefono(telefono);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPuesto method, of class Empleado.
     */
    @Test
    public void testGetPuesto() {
        System.out.println("getPuesto");
        Empleado instance = new Empleado();
        instance.setPuesto("");
        String expResult = "";
        String result = instance.getPuesto();
        assertEquals(expResult, result);
  
    }

    /**
     * Test of setPuesto method, of class Empleado.
     */
    @Test
    public void testSetPuesto() {
        System.out.println("setPuesto");
        String puesto = "";
        Empleado instance = new Empleado();
        instance.setPuesto(puesto);
 
    }

    /**
     * Test of getFcha_nacimiento method, of class Empleado.
     */
    @Test
    public void testGetFcha_nacimiento() {
        System.out.println("getFcha_nacimiento");
        Empleado instance = new Empleado();
        Date expResult = null;
        Date result = instance.getFcha_nacimiento();
        assertEquals(expResult, result);
  
    }

    /**
     * Test of setFcha_nacimiento method, of class Empleado.
     */
    @Test
    public void testSetFcha_nacimiento() {
        System.out.println("setFcha_nacimiento");
        Date fcha_nacimiento = null;
        Empleado instance = new Empleado();
        instance.setFcha_nacimiento(fcha_nacimiento);
       
    }

    /**
     * Test of getFcha_empleado method, of class Empleado.
     */
    @Test
    public void testGetFcha_empleado() {
        System.out.println("getFcha_empleado");
        Empleado instance = new Empleado();
        Date expResult = null;
        Date result = instance.getFcha_empleado();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setFcha_empleado method, of class Empleado.
     */
    @Test
    public void testSetFcha_empleado() {
        System.out.println("setFcha_empleado");
        Date fcha_empleado = null;
        Empleado instance = new Empleado();
        instance.setFcha_empleado(fcha_empleado);
        
    }

    /**
     * Test of getSueldo method, of class Empleado.
     */
    @Test
    public void testGetSueldo() {
        System.out.println("getSueldo");
        Empleado instance = new Empleado();
        instance.setSueldo(0);
        double expResult = 0.0;
        double result = instance.getSueldo();
        assertEquals(expResult, result, 0.0);
   
    }

    /**
     * Test of setSueldo method, of class Empleado.
     */
    @Test
    public void testSetSueldo() {
        System.out.println("setSueldo");
        double sueldo = 0.0;
        Empleado instance = new Empleado();
        instance.setSueldo(sueldo);
  
    }
    
}