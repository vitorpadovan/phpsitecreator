package com.br.phpSiteCreator.control.util;

public class Debug {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	private static int contador;

	public Debug() {
		// TODO Auto-generated constructor stub
	}
	
	public static void mensagem(String mensagem)
	{
		System.out.println(Debug.contador++);
		//System.out.println(mensagem);
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		for(StackTraceElement e : stackTraceElements)
		{
			//System.out.println("Executado em: "+e.getClassName()+", linha: "+e.getLineNumber()+" metodo "+e.getMethodName());
		}
		for(int i = 0; i<stackTraceElements.length;i++)
		{
			//StackTraceElement e = stackTraceElements[i];
			//System.out.println(i+". Executado em: "+e.getClassName()+", linha: "+e.getLineNumber()+" metodo "+e.getMethodName());
		}
		
		StackTraceElement e = stackTraceElements[2];
		System.out.println(e.getClassName()+"."+e.getMethodName()+"(), L:"+e.getLineNumber());
		System.out.println(mensagem);
		System.out.println("");
		
	}

}
