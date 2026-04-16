package logica;

public class Seis {

	public static void main(String[] args) {
	//	6.	Hacer un algoritmo que imprima los n�meros impares desde el 100 hasta la unidad y que calcule su suma. 
 
		int suma= 0;
		for (int i = 1; i <=100; i=i+2) {
			System.out.println(i);
			suma=suma+i;
		}
		System.out.println("la suma de impares :"+ suma);

	}

}
