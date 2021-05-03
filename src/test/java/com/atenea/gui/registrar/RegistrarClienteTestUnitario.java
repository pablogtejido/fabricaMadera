package com.atenea.gui.registrar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.atenea.data.Cliente;

public class RegistrarClienteTestUnitario {
	
	Cliente cliente;
	
	private static JTextField textField1;
	private static JTextField textField2;
	private static JTextField textField3;
	private static JTextField textField4;
	
	@Before
	public void setUp() throws Exception {
		cliente = new Cliente("45695106X", "Alberto", "Rodriguez", "1234");
		
		textField1 = Mockito.mock(JTextField.class);
		textField2 = Mockito.mock(JTextField.class);
		textField3 = Mockito.mock(JTextField.class);
		textField4 = Mockito.mock(JTextField.class);
	}
	
	@Test
	public void testGuardarCliente() {
		System.out.println(
				"================================================Test guardar clientes Unitario================================================");

		Cliente cl = new Cliente();
		when(textField1.getText()).thenReturn("45695106X");
		cl.setDni(textField1.getText());

		when(textField2.getText()).thenReturn("Alberto");
		cl.setNombre(textField2.getText());

		when(textField3.getText()).thenReturn("Rodriguez");
		cl.setApellidos(textField3.getText());

		when(textField4.getText()).thenReturn("1234");
		cl.setContrasena(textField4.getText());
		
		assertEquals(cl, cliente);
	
	}
	

}
