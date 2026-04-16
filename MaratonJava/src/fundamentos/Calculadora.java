package fundamentos;

public class Calculadora {

    static double calcularPromedio(int[] numeros) {
        int total = 0;

        // (enhanced for)
        for (int numero : numeros) {
            total += numero;
        }

        // for tradicional
//        for (int i = 0; i < numeros.length; i++) {
//            total += numeros[i];
//        }

        return (double) total / numeros.length;
    }

}