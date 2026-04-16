package fundamentos;


import java.util.Scanner;

public class EntradaDatos {
	public static void main (String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("C�lculo Indice de Masa Corporal");
		
		System.out.print("Digite su nombre : ");
		String nombre = entrada.nextLine();	// entrada string

		System.out.print("Digite su peso : ");
		Double peso = entrada.nextDouble();	//entrada numerica double
		
		System.out.print("Digite su altura : ");
		Double altura = entrada.nextDouble();

		Double imc = peso / (altura * altura);
		
		System.out.println("El IMC de "+ nombre + " es "+ imc);
	}
}