package logica;

import java.util.Scanner;

public class Siete {

	public static void main(String[] args) {
	//	7.	Hacer un algoritmo que imprima todos los n�meros naturales que hay desde la unidad hasta
		//un n�mero que introducimos por teclado. 
		Scanner entrada =new Scanner(System.in);
		System.out.println("Ingrese el limite");
		int limite= entrada.nextInt();;
		
		for (int i = 1; i <=limite; i++) {
			System.out.println(i);
			
		}
		System.out.printf("ingreso como limite %d", limite);

	}

}
