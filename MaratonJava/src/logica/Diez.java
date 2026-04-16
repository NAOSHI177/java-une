package logica;


public class Diez {

	public static void main(String[] args) {
	//	10.	Hacer un algoritmo que imprima y cuente los m�ltiplos de 5 que hay entre 1 y 500. 

		
		int suma=0;
		for (int i = 1; i <=500; i++) {
			
			System.out.println(i);
			suma = suma+i;
					}
		System.out.printf("la suma delos m�ltiplos de 5 que hay entre 1 y 500 :  %d", suma);

	}

}
