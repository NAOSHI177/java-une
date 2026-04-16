package fundamentos;


import java.util.Scanner;

public class EstructuraFor {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Ultimo numero: ");
		int ultimoNumero = entrada.nextInt();
		//ciclo for
		for (int i = 1; i < ultimoNumero; i++) {
			System.out.println(i);
			
		}

	}

}
