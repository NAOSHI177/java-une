package strings;

import java.util.*;
import java.util.regex.*;

public class MiniLexer {

    // Definimos los patrones de tokens
    private static final Pattern tokenPatterns = Pattern.compile(
        "(?<KEYWORD>\\b(VAR|INT|PRINT)\\b)|" +
        "(?<IDENT>[a-zA-Z_][a-zA-Z0-9_]*)|" +
        "(?<NUMBER>\\d+)|" +
        "(?<STRING>\"[^\"]*\")|" +
        "(?<SYMBOL>[=;])|" +
        "(?<WHITESPACE>\\s+)"
    );

    public static void main(String[] args) {
        String codigo = "VAR nombre = \"Pedro de Paredes\"; PRINT nombre; id = 123;";

        List<String> tokens = analizar(codigo);

        System.out.println("Tokens encontrados:");
        for (String t : tokens) {
            System.out.println(t);
        }
    }

    public static List<String> analizar(String codigo) {
        List<String> tokens = new ArrayList<>();

        Matcher matcher = tokenPatterns.matcher(codigo);

        while (matcher.find()) {
            if (matcher.group("WHITESPACE") != null) {
                // ignoramos espacios (como formatearNombre ignora preposiciones)
                continue;
            } else if (matcher.group("KEYWORD") != null) {
                tokens.add("[KEYWORD:" + matcher.group("KEYWORD") + "]");
            } else if (matcher.group("IDENT") != null) {
                // normalizamos identificadores en mayúsculas (como formatearNombre)
                tokens.add("[IDENT:" + matcher.group("IDENT").toUpperCase() + "]");
            } else if (matcher.group("NUMBER") != null) {
                // normalizamos números a 10 dígitos (como rellenarIzq)
                String numero = rellenarIzq(matcher.group("NUMBER"), '0', 10);
                tokens.add("[NUM:" + numero + "]");
            } else if (matcher.group("STRING") != null) {
                // quitamos comillas y limpiamos cadena
                String str = matcher.group("STRING").replace("\"", "").trim().toUpperCase();
                tokens.add("[STRING:\"" + str + "\"]");
            } else if (matcher.group("SYMBOL") != null) {
                tokens.add("[SYMBOL:" + matcher.group("SYMBOL") + "]");
            }
        }

        return tokens;
    }

    //  función de Java 8 para rellenar izquierda
    public static String rellenarIzq(String texto, char caracter, int tamanhoTotal) {
        if (texto.length() >= tamanhoTotal) {
            return texto;
        }
        int cantidad = tamanhoTotal - texto.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cantidad; i++) {
            sb.append(caracter);
        }
        sb.append(texto);
        return sb.toString();
    }
}
