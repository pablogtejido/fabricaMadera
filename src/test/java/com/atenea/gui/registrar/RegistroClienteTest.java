package com.atenea.gui.registrar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.swing.JTextField;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import categories.GuiTest;

import org.mockito.Mockito;

import com.atenea.Main;
import com.atenea.data.Cliente;
import com.atenea.rsh.ClienteRSH;

@Category(GuiTest.class)
public class RegistroClienteTest {

	private static HttpServer server;
	private static ClienteRSH rsh;
	
	private static JTextField textField1;
	private static JTextField textField2;
	private static JTextField textField3;
	private static JTextField textField4;
	
	@BeforeClass
	public static void setUp() throws Exception {
		server = Main.startServer();
		rsh = ClienteRSH.getInstance();
		
		textField1 = Mockito.mock(JTextField.class);
		textField2 = Mockito.mock(JTextField.class);
		textField3 = Mockito.mock(JTextField.class);
		textField4 = Mockito.mock(JTextField.class);
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		server.shutdownNow();
		
	}
	
	@After
	public void Clean() {
		System.out.println(
                "================================================Cleaning data ...================================================");
        List<Cliente> clienteBD = rsh.verClientes();
        for (Cliente cl : clienteBD) {
			rsh.borrarCliente(cl);
		}
	}
	
	@Test
	public void testGuardarCliente() {
		System.out.println(
                "================================================Test guardar clientes================================================");
        
		Cliente cliente = new Cliente();
		
		when(textField1.getText()).thenReturn("75832142R");
		cliente.setDni(textField1.getText());
		
		when(textField2.getText()).thenReturn("Jose");
		cliente.setApellidos(textField2.getText());
		
		when(textField3.getText()).thenReturn("Perez Reberte");
		cliente.setApellidos(textField3.getText());
		
		when(textField4.getText()).thenReturn("1234");
		cliente.setContrasena(textField4.getText());
		
		rsh.guardarCliente(cliente);
		
		List<Cliente> clientes = rsh.verClientes();
		
		assertEquals(cliente, clientes.get(0));
		
	}

}
