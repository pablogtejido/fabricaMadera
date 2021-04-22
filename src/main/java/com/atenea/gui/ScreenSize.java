package com.atenea.gui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class ScreenSize {

	public static int windowWidth;
	public static int windowHeight;
	public static int x;
	public static int y;
	public static int cte;
	
	static //GET THE SCREEN SIZE
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

	/**
	 * 
	 * @param cte
	 */
	public static int setWindowWidth(double cte) {
		windowWidth = (int) (gd.getDisplayMode().getWidth() * cte);
		return windowWidth;
	}
	
	public static int getWindowWidth() {
		return windowWidth;	
	}
	
	public static int setWindowHeight(double cte) {
		windowHeight = (int) (gd.getDisplayMode().getHeight() * cte);
		return windowHeight;
	}
	
	public static int getWindowHeight() {
		return windowHeight;	
	}
	
	/**
	 * Coordena X para centrar la ventana
	 * Obtiene el ancho de de la pantalla y el ancho de una vetana y lo divide entre 2
	 * @param i Window Width
	 * @return X coordinate for the window top-left corner (centered window)
	 */
	public static int getXcoordinate(int i) {
		x = (int) ((gd.getDisplayMode().getWidth() - i) / 2);
		return x;
	}
	
	/**
	 * Coordena Y para centrar la ventana
	 * Obtiene el alto de de la pantalla y el alto de una vetana y lo divide entre 2
	 * @param i Window Height
	 * @return Y coordinate for the window top-left corner (centered window)
	 */
	public static int getYcoordinate(int i) {
		y = (int) ((gd.getDisplayMode().getHeight() - i) / 2);
		return y;
	}
}