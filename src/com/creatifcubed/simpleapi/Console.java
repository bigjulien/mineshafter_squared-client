package com.creatifcubed.simpleapi;

public class Console {
	
	private Console() {
		return;
	}
	
	public static void print(String msg) {
		System.out.print(msg);
	}
	public static void print(int msg) {
		System.out.print(msg);
	}
	public static void print(double msg) {
		System.out.print(msg);
	}
	public static void print(boolean msg) {
		System.out.print(msg);
	}
	public static void print(char msg) {
		System.out.print(msg);
	}
	public static void print(char[] msg) {
		System.out.print(msg);
	}
	public static void print(Object[] msg) {
		System.out.print("{");
		if (msg != null) {
			for (Object o : msg) {
				System.out.print("{" + (o == null ? "{null}" : o.toString()) + "}, ");
			}
		}
		System.out.print("}");
	}
	public static void print(Object o) {
		System.out.print(o);
	}
	
	public static void println(String msg) {
		System.out.println(msg);
	}
	public static void println() {
		System.out.println();
	}
	public static void println(int msg) {
		System.out.println(msg);
	}
	public static void println(double msg) {
		System.out.println(msg);
	}
	public static void println(boolean msg) {
		System.out.println(msg);
	}
	public static void println(char msg) {
		System.out.println(msg);
	}
	public static void println(char[] msg) {
		System.out.println(msg);
	}
	public static void println(Object[] msg) {
		System.out.println("{");
		if (msg != null) {
			for (Object o : msg) {
				System.out.println("\t{" + (o == null ? "{null}" : o.toString() + "},\n"));
			}
		}
		System.out.println("}");
	}
	public static void println(Object o) {
		System.out.println(o);
	}
	
}
