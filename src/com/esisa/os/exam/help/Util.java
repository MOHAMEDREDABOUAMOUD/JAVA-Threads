package com.esisa.os.exam.help;

import java.util.Arrays;

public class Util {

	public Util() {
		
	}
	
	public static void log(String msg, Object... data) {
		System.out.println(
				">> "+msg+" : "
				+Arrays.toString(data)
				+", by : "+Thread.currentThread().getName());
	}
	public static void pause(long time) {
		try {
			Thread.sleep(time);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public static String threadname() {
		return Thread.currentThread().getName();
	}
	public static void waitFor(Thread... p) {

		try {
			for (int i = 0; i < p.length; i++) {
				p[i].join();	
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
