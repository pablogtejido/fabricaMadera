package com.atenea.db;

import java.io.IOException;

public class DBException extends Exception {
	private static final long serialVersionUID = 1L;

	
	 /**
     * DBException method
     * 
     * @param mensaje -> the first argument
     * @param e -> the second argument
     * 
     */
	
	public DBException(String mensaje, Throwable e) {
		super(mensaje, e);
	}

}
