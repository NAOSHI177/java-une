package Analizador;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AnalizadorGramaticas2 {

	private static final int MAX_PASOS_POR_DEFECTO = 10;
	private static final int MAX_DERIVACIONES_POR_DEFECTO = 20;
	private static final String RESULTADO_GLC = "Posible Gramática Libre de Contexto";
	private static final String RESULTADO_NO_CLASIFICADA = "No clasificada";

	/**
	 * Verifica si el texto ingresado cumple las condiciones básicas de una Gramática
	 * Libre de Contexto:
	 *
	 * - Cada regla debe tener exactamente una flecha ->
	 * - El lado izquierdo debe ser una sola letra mayúscula
	 * - El lado derecho debe tener una o más producciones no vacías
	 */
	public String detectarTipo(String textoGramatica) {
		if (obtenerErroresValidacion(textoGramatica).isEmpty()) {
			return RESULTADO_GLC;
		}

		return RESULTADO_NO_CLASIFICADA;
	}

	/**
	 * Devuelve una lista con los errores encontrados. Si la lista queda vacía, la
	 * gramática se considera válida para las reglas simples de esta tarea.
	 */
	public List<String> obtenerErroresValidacion(String textoGramatica) {
		List<String> errores = new ArrayList<>();

		if (textoGramatica == null || textoGramatica.trim().isEmpty()) {
			errores.add("La gramática no contiene reglas.");
			return errores;
		}

		String[] lineas = textoGramatica.split("\\R");
		boolean existeRegla = false;

		for (int i = 0; i < lineas.length; i++) {
			String linea = lineas[i].trim();
			int numeroLinea = i + 1;

			if (linea.isEmpty()) {
				continue;
			}

			existeRegla = true;
			validarLinea(linea, numeroLinea, errores);
		}

		if (!existeRegla) {
			errores.add("La gramática no contiene reglas.");
		}

		return errores;
	}

	private void validarLinea(String linea, int numeroLinea, List<String> errores) {
		int primeraFlecha = linea.indexOf("->");
		int ultimaFlecha = linea.lastIndexOf("->");

		if (primeraFlecha == -1) {
			errores.add("Línea " + numeroLinea + ": falta la flecha ->.");
			return;
		}

		if (primeraFlecha != ultimaFlecha) {
			errores.add("Línea " + numeroLinea + ": solo debe existir una flecha ->.");
			return;
		}

		String ladoIzquierdo = linea.substring(0, primeraFlecha).trim();
		String ladoDerecho = linea.substring(primeraFlecha + 2).trim();

		if (!ladoIzquierdo.matches("[A-Z]")) {
			errores.add("Línea " + numeroLinea + ": el lado izquierdo debe ser una sola letra mayúscula.");
		}

		if (ladoDerecho.isEmpty()) {
			errores.add("Línea " + numeroLinea + ": el lado derecho no debe estar vacío.");
			return;
		}

		String[] alternativas = ladoDerecho.split("\\|", -1);

		for (int i = 0; i < alternativas.length; i++) {
			String alternativa = alternativas[i].trim();

			if (alternativa.isEmpty()) {
				errores.add("Línea " + numeroLinea + ": la alternativa " + (i + 1) + " está vacía.");
			}
		}
	}

	/**
	 * Genera una derivación usando un máximo de pasos mayor al original de la guía.
	 */
	public List<String> generarDerivaciones(String simboloInicial, String textoGramatica) {
		return generarDerivaciones(simboloInicial, textoGramatica, MAX_PASOS_POR_DEFECTO);
	}

	/**
	 * Genera una derivación desde el símbolo inicial indicado. Si existen varias
	 * alternativas, se busca una ruta que llegue a una cadena terminal dentro del
	 * límite de pasos.
	 */
	public List<String> generarDerivaciones(String simboloInicial, String textoGramatica, int maxPasos) {
		List<String> errores = validarEntradaParaDerivar(simboloInicial, textoGramatica, maxPasos);

		if (!errores.isEmpty()) {
			return formatearErrores(errores);
		}

		Map<String, List<String>> reglas = construirReglas(textoGramatica);
		List<String> ruta = buscarDerivacionTerminal(simboloInicial.trim(), reglas, maxPasos);

		if (ruta.isEmpty()) {
			List<String> resultado = generarRutaConPrimeraAlternativa(simboloInicial.trim(), reglas, maxPasos);
			resultado.add("No se encontró una derivación terminal en " + maxPasos + " pasos.");
			return resultado;
		}

		return formatearRuta(ruta);
	}

	/**
	 * Genera varias derivaciones posibles cuando una gramática tiene alternativas.
	 * Esto permite comprobar producciones como S -> a | b de forma explícita.
	 */
	public List<String> generarDerivacionesConAlternativas(String simboloInicial, String textoGramatica, int maxPasos) {
		List<String> errores = validarEntradaParaDerivar(simboloInicial, textoGramatica, maxPasos);

		if (!errores.isEmpty()) {
			return formatearErrores(errores);
		}

		Map<String, List<String>> reglas = construirReglas(textoGramatica);
		List<List<String>> rutas = buscarDerivacionesTerminales(simboloInicial.trim(), reglas, maxPasos,
				MAX_DERIVACIONES_POR_DEFECTO);
		List<String> resultado = new ArrayList<>();

		if (rutas.isEmpty()) {
			resultado.add("No se encontraron derivaciones terminales en " + maxPasos + " pasos.");
			return resultado;
		}

		for (int i = 0; i < rutas.size(); i++) {
			resultado.add("Derivación " + (i + 1) + ":");
			resultado.addAll(formatearRuta(rutas.get(i)));
		}

		return resultado;
	}

	private List<String> validarEntradaParaDerivar(String simboloInicial, String textoGramatica, int maxPasos) {
		List<String> errores = new ArrayList<>();

		if (simboloInicial == null || simboloInicial.trim().isEmpty()) {
			errores.add("El símbolo inicial no debe estar vacío.");
		} else if (!simboloInicial.trim().matches("[A-Z]")) {
			errores.add("El símbolo inicial debe ser una sola letra mayúscula.");
		}

		if (maxPasos <= 0) {
			errores.add("El máximo de pasos debe ser mayor que cero.");
		}

		errores.addAll(obtenerErroresValidacion(textoGramatica));
		return errores;
	}

	private Map<String, List<String>> construirReglas(String textoGramatica) {
		Map<String, List<String>> reglas = new LinkedHashMap<>();
		String[] lineas = textoGramatica.split("\\R");

		for (String linea : lineas) {
			linea = linea.trim();

			if (linea.isEmpty()) {
				continue;
			}

			String[] partes = linea.split("->", 2);
			String noTerminal = partes[0].trim();
			String[] alternativas = partes[1].trim().split("\\|");
			List<String> producciones = reglas.computeIfAbsent(noTerminal, clave -> new ArrayList<>());

			for (String alternativa : alternativas) {
				producciones.add(normalizarEpsilon(alternativa.trim()));
			}
		}

		return reglas;
	}

	private String normalizarEpsilon(String produccion) {
		if (produccion.equalsIgnoreCase("epsilon") || produccion.equalsIgnoreCase("lambda")
				|| produccion.equals("ε") || produccion.equals("λ")) {
			return "";
		}

		return produccion;
	}

	private List<String> buscarDerivacionTerminal(String simboloInicial, Map<String, List<String>> reglas,
			int maxPasos) {
		List<List<String>> rutas = buscarDerivacionesTerminales(simboloInicial, reglas, maxPasos, 1);

		if (rutas.isEmpty()) {
			return Collections.emptyList();
		}

		return rutas.get(0);
	}

	private List<List<String>> buscarDerivacionesTerminales(String simboloInicial, Map<String, List<String>> reglas,
			int maxPasos, int maxResultados) {
		List<List<String>> resultados = new ArrayList<>();
		Queue<Derivacion> pendientes = new ArrayDeque<>();
		Set<String> visitados = new HashSet<>();

		pendientes.add(new Derivacion(simboloInicial, Collections.singletonList(simboloInicial)));
		visitados.add(simboloInicial + "|1");

		while (!pendientes.isEmpty() && resultados.size() < maxResultados) {
			Derivacion actual = pendientes.remove();
			int pasosRealizados = actual.ruta.size() - 1;
			String noTerminal = buscarPrimerNoTerminal(actual.cadena, reglas);

			if (noTerminal == null) {
				resultados.add(actual.ruta);
				continue;
			}

			if (pasosRealizados >= maxPasos) {
				continue;
			}

			for (String produccion : reglas.get(noTerminal)) {
				String nuevaCadena = reemplazarPrimeraAparicion(actual.cadena, noTerminal, produccion);
				List<String> nuevaRuta = new ArrayList<>(actual.ruta);
				nuevaRuta.add(nuevaCadena);
				String claveVisitado = nuevaCadena + "|" + nuevaRuta.size();

				if (visitados.add(claveVisitado)) {
					pendientes.add(new Derivacion(nuevaCadena, nuevaRuta));
				}
			}
		}

		return resultados;
	}

	private List<String> generarRutaConPrimeraAlternativa(String simboloInicial, Map<String, List<String>> reglas,
			int maxPasos) {
		List<String> ruta = new ArrayList<>();
		String cadenaActual = simboloInicial;

		ruta.add("Paso 0: " + cadenaActual);

		for (int paso = 1; paso <= maxPasos; paso++) {
			String noTerminal = buscarPrimerNoTerminal(cadenaActual, reglas);

			if (noTerminal == null) {
				break;
			}

			String produccion = reglas.get(noTerminal).get(0);
			cadenaActual = reemplazarPrimeraAparicion(cadenaActual, noTerminal, produccion);
			ruta.add("Paso " + paso + ": " + cadenaActual);
		}

		return ruta;
	}

	private String buscarPrimerNoTerminal(String cadena, Map<String, List<String>> reglas) {
		for (int i = 0; i < cadena.length(); i++) {
			String simbolo = String.valueOf(cadena.charAt(i));

			if (reglas.containsKey(simbolo)) {
				return simbolo;
			}
		}

		return null;
	}

	private String reemplazarPrimeraAparicion(String cadena, String noTerminal, String produccion) {
		int posicion = cadena.indexOf(noTerminal);

		if (posicion == -1) {
			return cadena;
		}

		return cadena.substring(0, posicion) + produccion + cadena.substring(posicion + noTerminal.length());
	}

	private List<String> formatearRuta(List<String> ruta) {
		List<String> resultado = new ArrayList<>();

		for (int i = 0; i < ruta.size(); i++) {
			resultado.add("Paso " + i + ": " + mostrarCadena(ruta.get(i)));
		}

		return resultado;
	}

	private String mostrarCadena(String cadena) {
		if (cadena.isEmpty()) {
			return "ε";
		}

		return cadena;
	}

	private List<String> formatearErrores(List<String> errores) {
		List<String> resultado = new ArrayList<>();
		resultado.add("No se puede generar la derivación porque existen errores:");

		for (String error : errores) {
			resultado.add("- " + error);
		}

		return resultado;
	}

	/**
	 * Muestra en consola la lista de derivaciones.
	 */
	public void mostrarDerivaciones(List<String> derivaciones) {
		for (String paso : derivaciones) {
			System.out.println(paso);
		}
	}

	private static class Derivacion {
		private final String cadena;
		private final List<String> ruta;

		private Derivacion(String cadena, List<String> ruta) {
			this.cadena = cadena;
			this.ruta = ruta;
		}
	}
}
