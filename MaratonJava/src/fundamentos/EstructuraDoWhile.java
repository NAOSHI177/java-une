package fundamentos;

import java.util.Scanner;

public class EstructuraDoWhile {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);

		int valor = 0;
		int suma = 0;

		do {
			System.out.println("Para salir presione 0");
			valor = entrada.nextInt();

			suma += valor;
			System.out.println("suma: " + suma);

		} while (valor != 0);

		while (valor == 0) {
			System.out.println("FIN");
			valor++;
		}
	}

}
