package com.atenea.resource;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.atenea.Main;
import com.atenea.data.Cliente;
import com.atenea.rsh.ClienteRSH;

import categories.IntegrationTest;

@Category(IntegrationTest.class)
public class ClienteResourceTest {
	
    private HttpServer server;
    private ClienteRSH rsh;
    
    private Cliente cliente1;
    private List<Cliente> clientes;
    private List<Cliente> clientesBD;

    public ClienteResourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
    	
    	server = Main.startServer();
        rsh = ClienteRSH.getInstance();
        
        clientes = new ArrayList<>();
        cliente1 = new Cliente("58441139A", "Juan", "Lopez", "1234");
        
    }
    
    @After
    public void tearDown() throws Exception{
    	server.shutdownNow();
 
    }
	@Test
	public void testVerClientes() {
		
		rsh.guardarCliente(cliente1);
		clientes.add(cliente1);
		
		clientesBD = rsh.verClientes();
		
		rsh.borrarCliente(cliente1);
		
		assertEquals(clientesBD.get(0), clientes.get(0));
		
	}

}
