package fundamentos;

import java.util.Scanner;

public class Switch {
	public static void main (String[] args) {
		Scanner entrada = new Scanner(System.in);

		System.out.println("Ingrese su ultimo nro de Cedula : ");
		int nro = entrada.nextInt();

		switch (nro) {
			case 1:
				System.out.println("Primer vencimiento");
				break;
			case 2:
				System.out.println("Segundo vencimiento");
				break;
			default:
				System.out.println("Tercer vencimiento");
		}

	}
}