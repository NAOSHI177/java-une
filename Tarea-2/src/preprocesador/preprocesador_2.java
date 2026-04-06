package preprocesador;

import java.nio.file.Files;
import java.nio.file.Path;

/*
    Este Verifica si en un texto tipo String text = "Hola // este no es un comenterio"
    Pueda identificar que no es un comentario y no elimine ese texto.
*/

public class preprocesador_2 {

    public static void main(String[] args) throws Exception {
        String archivoEntrada = "entrada.txt";
        String archivoSalida = "salida.txt";

        String codigoFuente = leerArchivo(archivoEntrada);
        String resultado = procesar(codigoFuente);
        escribirArchivo(archivoSalida, resultado);

        System.out.println("Preprocesamiento completado correctamente.");
        System.out.println("Archivo generado: " + archivoSalida);
    }

    public static String leerArchivo(String ruta) throws Exception {
        return Files.readString(Path.of(ruta));

    }

    public static void escribirArchivo(String ruta, String contenido) throws Exception {
        Files.writeString(Path.of(ruta), contenido);
    }

    public static String procesar(String codigoFuente) {
        String sinComentarios = eliminarComentarios(codigoFuente);
        return limpiarEspaciosExtremos(sinComentarios);
    }

    public static String eliminarComentarios(String texto) {
        StringBuilder resultado = new StringBuilder();

        boolean enComentarioLinea = false;
        boolean enComentarioBloque = false;
        boolean enCadenaDoble = false;
        boolean enCadenaSimple = false;

        for (int i = 0; i < texto.length(); i++) {
            char actual = texto.charAt(i);
            char siguiente = (i + 1 < texto.length()) ? texto.charAt(i + 1) : '\0';

            if (enComentarioLinea) {
                if (actual == '\n') {
                    enComentarioLinea = false;
                    resultado.append(actual);
                }
                continue;
            }

            if (enComentarioBloque) {
                if (actual == '*' && siguiente == '/') {
                    enComentarioBloque = false;
                    i++;
                }
                continue;
            }

            if (!enCadenaDoble && !enCadenaSimple) {
                if (actual == '/' && siguiente == '/') {
                    enComentarioLinea = true;
                    i++;
                    continue;
                }

                if (actual == '/' && siguiente == '*') {
                    enComentarioBloque = true;
                    i++;
                    continue;
                }
            }

            if (actual == '"' && !enCadenaSimple && !estaEscapado(texto, i)) {
                enCadenaDoble = !enCadenaDoble;
            }

            if (actual == '\'' && !enCadenaDoble && !estaEscapado(texto, i)) {
                enCadenaSimple = !enCadenaSimple;
            }

            resultado.append(actual);
        }

        return resultado.toString();
    }

    public static String limpiarEspaciosExtremos(String texto) {
        String[] lineas = texto.split("\\R", -1);
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < lineas.length; i++) {
            String lineaLimpia = lineas[i].replaceAll("^[\\t ]+|[\\t ]+$", "");
            resultado.append(lineaLimpia);

            if (i < lineas.length - 1) {
                resultado.append(System.lineSeparator());
            }
        }

        return resultado.toString();
    }

    public static boolean estaEscapado(String texto, int indice) {
        int contadorBarras = 0;
        int j = indice - 1;

        while (j >= 0 && texto.charAt(j) == '\\') {
            contadorBarras++;
            j--;
        }

        return contadorBarras % 2 != 0;
    }
}