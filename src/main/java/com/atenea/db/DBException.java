package com.atenea.db;

public class DBException extends Exception {
	private static final long serialVersionUID = 1L;

	public DBException(String mensaje, Throwable e) {
		super(mensaje, e);
	}

}
