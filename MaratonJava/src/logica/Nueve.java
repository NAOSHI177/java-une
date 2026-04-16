package logica;


public class Nueve {

	public static void main(String[] args) {
	//	9.	Hacer un algoritmo que calcule la suma de los n�meros impares comprendidos entre el 0 y el 100. 

		
		int suma=0;
		for (int i = 1; i <=100; i=i+2) {
			suma = suma+i;
					}
		System.out.printf("la suma de impares %d", suma);

	}

}
