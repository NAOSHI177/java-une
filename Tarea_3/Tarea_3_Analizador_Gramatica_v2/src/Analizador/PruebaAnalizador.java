package Analizador;

import java.util.List;

public class PruebaAnalizador {

    public static void main(String[] args) {
        AnalizadorGramaticas2 analizador = new AnalizadorGramaticas2();

        probarGramaticaEjemplo(analizador);
        probarGramaticasIncorrectas(analizador);
        probarNuevaGramatica(analizador);
    }

    private static void probarGramaticaEjemplo(AnalizadorGramaticas2 analizador) {
        String gramatica = "E -> T\n"
                + "T -> F\n"
                + "F -> id";

        imprimirTitulo("Prueba 1: gramática de ejemplo");
        imprimirAnalisis(analizador, gramatica, "E");
    }

    private static void probarGramaticasIncorrectas(AnalizadorGramaticas2 analizador) {
        String[] gramaticasIncorrectas = {
                "AB -> a",
                "A -> ",
                "S a",
                "S -> a |"
        };

        imprimirTitulo("Prueba 2: gramáticas incorrectas");

        for (int i = 0; i < gramaticasIncorrectas.length; i++) {
            String gramatica = gramaticasIncorrectas[i];
            System.out.println("\nCaso incorrecto " + (i + 1) + ":");
            System.out.println(gramatica);
            System.out.println("Tipo detectado: " + analizador.detectarTipo(gramatica));
            imprimirErrores(analizador.obtenerErroresValidacion(gramatica));
        }
    }

    private static void probarNuevaGramatica(AnalizadorGramaticas2 analizador) {
        String gramatica = "S -> aA\n"
                + "A -> b";

        imprimirTitulo("Prueba 3: nueva gramática");
        imprimirAnalisis(analizador, gramatica, "S");
    }

    private static void imprimirAnalisis(AnalizadorGramaticas2 analizador, String gramatica, String simboloInicial) {
        System.out.println("Gramática:");
        System.out.println(gramatica);
        System.out.println("Tipo detectado: " + analizador.detectarTipo(gramatica));

        imprimirErrores(analizador.obtenerErroresValidacion(gramatica));

        System.out.println("\nDerivaciones desde " + simboloInicial + ":");
        List<String> derivaciones = analizador.generarDerivaciones(simboloInicial, gramatica);
        analizador.mostrarDerivaciones(derivaciones);
    }

    private static void imprimirErrores(List<String> errores) {
        if (errores.isEmpty()) {
            System.out.println("Validación: sin errores.");
            return;
        }

        System.out.println("Errores encontrados:");
        for (String error : errores) {
            System.out.println("- " + error);
        }
    }

    private static void imprimirTitulo(String titulo) {
        System.out.println("\n======================================");
        System.out.println(titulo);
        System.out.println("======================================");
    }
}
