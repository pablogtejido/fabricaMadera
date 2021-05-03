package com.atenea.gui.modificar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.atenea.data.Cliente;

public class ModificarClienteTestUnitario {

	Cliente cliente;
	
	private static JTextField textField1;
	private static JTextField textField2;
	private static JTextField textField3;
	
	@Before
	public void setUp() throws Exception {
		cliente = new Cliente("45695106X", "Alberto", "Rodriguez", "1234");
		
		textField1 = Mockito.mock(JTextField.class);
		textField2 = Mockito.mock(JTextField.class);
		textField3 = Mockito.mock(JTextField.class);
	}
	
	@Test
	public void testModificarCliente() {
		System.out.println(
				"================================================Test modificar clientes Unitario================================================");

		assertEquals(cliente.getDni(), "45695106X");
		assertEquals(cliente.getNombre(), "Alberto");
		assertEquals(cliente.getApellidos(), "Rodriguez");
		assertEquals(cliente.getContrasena(), "1234");
		
		when(textField1.getText()).thenReturn("Pepe");
		cliente.setNombre(textField1.getText());

		when(textField2.getText()).thenReturn("Trujillo");
		cliente.setApellidos(textField2.getText());
		
		when(textField3.getText()).thenReturn("12345");
		cliente.setContrasena(textField3.getText());
		
		
		assertEquals(cliente.getDni(), "45695106X");
		assertEquals(cliente.getNombre(), "Pepe");
		assertEquals(cliente.getApellidos(), "Trujillo");
		assertEquals(cliente.getContrasena(), "12345");
		
	}
}
