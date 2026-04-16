package fundamentos;


import java.util.Scanner;

public class ControlesIF {
	public static void main (String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Calculadora IMC");
		
		System.out.println("Ingrese su nombre");
		String nombre = entrada.nextLine();
		
		System.out.println("Ingrese su peso");
		double peso = entrada.nextDouble();
		
		System.out.println("Ingrese su altura");
		double altura = entrada.nextDouble();
		
		double imc = peso / (altura * altura);
		System.out.println("Su IMC es : "+ imc);

		if (imc < 18.5) {
			System.out.println("Estas por debajo de su peso ideal");
		} else if (imc >= 18.5 && imc < 25) {
			System.out.println("Estas en tu peso ideal");
		} else if (imc >= 25 && imc < 30) {
			System.out.println("Usted estas con sobrepeso");
		} else if (imc >= 30 && imc < 35) {
			System.out.println("Usted tiene obesidad de grado 1");
		} else if (imc >= 35 && imc < 40) {
			System.out.println("Usted tiene obesidad de grado 2");
		} else if (imc >= 40 && imc < 50) {
			System.out.println("Usted tiene obesidad de grado 3");
		} else {
			System.out.println("Usted tiene obesidad de grado 4 u obesidad extrema");
			System.out.println("Usted debe cuidar su alimentaci�n");
		}

	}
}