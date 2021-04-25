package com.atenea.data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.atenea.data.EnumPuestoEmpleados;

public class EnumPuestoEmpleadosTest {

    public EnumPuestoEmpleadosTest() {
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
     * Test of values method, of class EnumPuestoEmpleados.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        EnumPuestoEmpleados[] expResult = { EnumPuestoEmpleados.DIRECTIVA, EnumPuestoEmpleados.LEGAL,
                EnumPuestoEmpleados.OPERARIO };
        EnumPuestoEmpleados[] result = EnumPuestoEmpleados.values();
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of valueOf method, of class EnumPuestoEmpleados.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "LEGAL";
        EnumPuestoEmpleados expResult = EnumPuestoEmpleados.LEGAL;
        EnumPuestoEmpleados result = EnumPuestoEmpleados.valueOf(name);
        assertEquals(expResult, result);

    }

}
