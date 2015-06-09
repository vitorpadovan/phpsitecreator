package com.br.phpsitecreator2.util;

import java.awt.Color;

public class Debug {

	private Debug() {

	}

	private static void show(String mensagem, Color cor) {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		StackTraceElement e = stack[3];
		System.out.println(e.getClassName() + "." + e.getMethodName() + ", L:"
				+ e.getLineNumber() + "\n" + mensagem + "\n");

	}

	/**
	 * Mostra mensagens de debug em preto
	 * 
	 * @param mensagem
	 */
	public static void d(String mensagem) {
		Debug.show(mensagem, Color.BLACK);
	}

	/**
	 * Mostra mensagens de debug nivel error em vermelho
	 * 
	 * @param mensagem
	 */
	public static void e(String mensagem) {
		Debug.show("ERROR: " + mensagem, Color.RED);
	}

	/**
	 * Mostra mensagens de debug nivel alerta em amarelo
	 * 
	 * @param mensagem
	 */
	public static void w(String mensagem) {
		Debug.show("WARNING: " + mensagem, Color.YELLOW);
	}
}
