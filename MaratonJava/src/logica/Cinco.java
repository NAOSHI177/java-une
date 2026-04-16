package logica;

public class Cinco {

	public static void main(String[] args) {
		//5.	Hacer un algoritmo que imprima los n�meros impares hasta el 100 y
		//que imprima cuantos impares hay. 
		int c= 0;
		for (int i = 1; i <=100; i=i+2) {
			System.out.println(i);
			c=c+1;
		}
		System.out.println("cantidad de impares :"+ c);

	}

}
