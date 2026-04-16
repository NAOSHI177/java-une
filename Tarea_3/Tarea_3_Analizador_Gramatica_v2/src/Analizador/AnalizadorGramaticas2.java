package Analizador;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnalizadorGramaticas2 {

    private static final int MAX_PASOS = 5;
    private static final String RESULTADO_GLC = "Posible Gramática Libre de Contexto";
    private static final String RESULTADO_NO_CLASIFICADA = "No clasificada";

    /**
     * Verifica si la gramática cumple condiciones básicas de una GLC:
     * - Cada regla debe tener exactamente una flecha ->
     * - El lado izquierdo debe ser una sola letra mayúscula
     * - El lado derecho no debe estar vacío
     */
    public String detectarTipo(String textoGramatica) {
        if (obtenerErroresValidacion(textoGramatica).isEmpty()) {
            return RESULTADO_GLC;
        }
        return RESULTADO_NO_CLASIFICADA;
    }

    /**
     * Devuelve una lista con los errores encontrados.
     * Si no hay errores, la gramática se considera válida para esta tarea.
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
     * Genera derivaciones simples usando exactamente la lógica pedida en la guía:
     * - parte del símbolo inicial
     * - busca un no terminal en la cadena
     * - aplica la primera producción disponible
     * - reemplaza solo la primera aparición
     * - máximo 5 pasos
     */
    public List<String> generarDerivaciones(String simboloInicial, String textoGramatica) {
        List<String> errores = validarEntradaParaDerivar(simboloInicial, textoGramatica);

        if (!errores.isEmpty()) {
            return formatearErrores(errores);
        }

        Map<String, List<String>> reglas = construirReglas(textoGramatica);
        List<String> derivaciones = new ArrayList<>();
        String cadenaActual = simboloInicial.trim();

        derivaciones.add("Paso 0: " + cadenaActual);

        for (int paso = 1; paso <= MAX_PASOS; paso++) {
            String noTerminal = buscarPrimerNoTerminal(cadenaActual, reglas);

            if (noTerminal == null) {
                break;
            }

            String primeraProduccion = reglas.get(noTerminal).get(0);
            cadenaActual = reemplazarPrimeraAparicion(cadenaActual, noTerminal, primeraProduccion);
            derivaciones.add("Paso " + paso + ": " + mostrarCadena(cadenaActual));
        }

        return derivaciones;
    }

    private List<String> validarEntradaParaDerivar(String simboloInicial, String textoGramatica) {
        List<String> errores = new ArrayList<>();

        if (simboloInicial == null || simboloInicial.trim().isEmpty()) {
            errores.add("El símbolo inicial no debe estar vacío.");
        } else if (!simboloInicial.trim().matches("[A-Z]")) {
            errores.add("El símbolo inicial debe ser una sola letra mayúscula.");
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
            List<String> producciones = new ArrayList<>();

            for (String alternativa : alternativas) {
                producciones.add(alternativa.trim());
            }

            reglas.put(noTerminal, producciones);
        }

        return reglas;
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
     * Imprime en consola cada paso de la derivación.
     */
    public void mostrarDerivaciones(List<String> derivaciones) {
        for (String paso : derivaciones) {
            System.out.println(paso);
        }
    }
}
