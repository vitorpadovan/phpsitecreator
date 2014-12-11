/**
 * 
 */
package com.br.PHPSiteCreator.util;

import java.awt.Color;

/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class Debug {
	
	private static void mensagem(String mensagem,Color cor)
	{
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		StackTraceElement e = stack[3];
		System.out.println(e.getClassName()+"."+e.getMethodName()+", L:"+e.getLineNumber()+"\n"+mensagem+"\n");
	}
	
	public static void m(String mensagem)
	{
		Debug.mensagem(mensagem, Color.BLACK);
	}
	
	public static void e(String mensagem)
	{
		Debug.mensagem("ERROR: "+mensagem, Color.RED);
	}
}
