package logica;


public class Ocho {

	public static void main(String[] args) {
	//	8.	Hacer un algoritmo que imprima y cuente los m�ltiplos de 3 que hay entre 1 y 100. 

		
		int cont=0;
		for (int i = 1; i <=100; i++) {
			
			if (i%3 ==0) {
				System.out.println(i);
				cont = cont+1;
			}
			
		}
		System.out.printf("cantidad de multiplos de 3 %d", cont);

	}

}
