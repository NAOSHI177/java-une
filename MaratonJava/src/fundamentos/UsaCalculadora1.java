package fundamentos;

import java.util.Arrays;

public class UsaCalculadora1 {

    public static void main(String[] args) {
//      int[] notas = new int[5];
//      int[] notas;
//      notas = new int[]{8, 5, 4, 9, 10};
//      int[] notas = new int[]{8, 5, 4, 9, 10};
    	int[] notas = {8, 5, 4, 9, 10};
        double promedio = Calculadora.calcularPromedio(notas);

        System.out.println(promedio);
        String notasEnString = Arrays.toString(notas);

        System.out.println(notasEnString);
    }

}