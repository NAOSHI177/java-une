package fundamentos;

import java.util.Scanner;

public class OperadoresLogicos {
	public static void main (String[] args) {
		Scanner entrada = new Scanner(System.in);

		System.out.println("Calculo de IMC");
		System.out.print("Ingrese su nombre : ");
		String nombre = entrada.next();

		System.out.print("Sexo 1->Masculino u "
				+ "otra tecla cualquiera para Femenino : ");
		// operador ternario
		char sexo = entrada.nextShort() == 1 ? 'M' : 'F' ;
		

		System.out.print("Ingrese su peso : ");
		double peso = entrada.nextDouble();
		System.out.print("Ingrese su altura : ");
		double altura = entrada.nextDouble();

		double imc = peso / (altura * altura);
		System.out.println("Indice de masa corporal: "+imc+"; sexo: "+sexo);
		
		
	}
}