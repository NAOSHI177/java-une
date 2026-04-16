package Analizador;

import java.util.List;

public class PruebaAnalizador {

	public static void main(String[] args) {
		AnalizadorGramaticas2 analizador = new AnalizadorGramaticas2();

		probarGramaticaBasica(analizador);
		probarGramaticaConMasDeCincoPasos(analizador);
		probarGramaticaConAlternativas(analizador);
		probarGramaticasIncorrectas(analizador);
	}

	private static void probarGramaticaBasica(AnalizadorGramaticas2 analizador) {
		String gramatica = "E -> T\n"
				+ "T -> F\n"
				+ "F -> id";

		imprimirTitulo("Prueba 1: gramática de ejemplo");
		imprimirAnalisis(analizador, gramatica, "E", 10);
	}

	private static void probarGramaticaConMasDeCincoPasos(AnalizadorGramaticas2 analizador) {
		String gramatica = "S -> A\n"
				+ "A -> B\n"
				+ "B -> C\n"
				+ "C -> D\n"
				+ "D -> E\n"
				+ "E -> F\n"
				+ "F -> g";

		imprimirTitulo("Prueba 2: derivación con más de 5 pasos");
		imprimirAnalisis(analizador, gramatica, "S", 10);
	}

	private static void probarGramaticaConAlternativas(AnalizadorGramaticas2 analizador) {
		String gramatica = "S -> A | B\n"
				+ "A -> a\n"
				+ "B -> b";

		imprimirTitulo("Prueba 3: gramática con múltiples alternativas");
		imprimirAnalisis(analizador, gramatica, "S", 10);

		System.out.println("\nTodas las derivaciones terminales encontradas:");
		List<String> derivaciones = analizador.generarDerivacionesConAlternativas("S", gramatica, 10);
		analizador.mostrarDerivaciones(derivaciones);
	}

	private static void probarGramaticasIncorrectas(AnalizadorGramaticas2 analizador) {
		String[] gramaticasIncorrectas = {
				"AB -> a",
				"A -> ",
				"S a",
				"S -> a |"
		};

		imprimirTitulo("Prueba 4: gramáticas incorrectas");

		for (int i = 0; i < gramaticasIncorrectas.length; i++) {
			String gramatica = gramaticasIncorrectas[i];
			System.out.println("\nCaso incorrecto " + (i + 1) + ":");
			System.out.println(gramatica);
			System.out.println("Tipo detectado: " + analizador.detectarTipo(gramatica));
			imprimirErrores(analizador.obtenerErroresValidacion(gramatica));
		}
	}

	private static void imprimirAnalisis(AnalizadorGramaticas2 analizador, String gramatica, String simboloInicial,
			int maxPasos) {
		System.out.println("Gramática:");
		System.out.println(gramatica);
		System.out.println("Tipo detectado: " + analizador.detectarTipo(gramatica));

		imprimirErrores(analizador.obtenerErroresValidacion(gramatica));

		System.out.println("\nDerivaciones desde " + simboloInicial + ":");
		List<String> derivaciones = analizador.generarDerivaciones(simboloInicial, gramatica, maxPasos);
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
