package POO;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    // Palabras clave del lenguaje
    private static final Set<String> PALABRAS_CLAVE = new HashSet<>(Arrays.asList(
        "if", "else", "while", "return"
    ));

    // Expresión regular con grupos nombrados para tipos de tokens
    private static final Pattern PATRON = Pattern.compile(
        "(?<PALABRA>[a-zA-Z_][a-zA-Z_0-9]*)|" +
        "(?<NUMERO>\\d+)|" +
        "(?<OPERADOR>>=|<=|==|!=|>|<|[+\\-*/=])|" +
        "(?<DELIMITADOR>[;:(){}])|" +
        "(?<ESPACIO>\\s+)|" +
        "(?<ERROR>.)"
    );

    public List<Token> analizar(String codigoFuente) {
        List<Token> tokens = new ArrayList<>();
        Matcher matcher = PATRON.matcher(codigoFuente);

        while (matcher.find()) {
            if (matcher.group("PALABRA") != null) {
                String palabra = matcher.group();
                if (PALABRAS_CLAVE.contains(palabra)) {
                    tokens.add(new Token("PALABRA_CLAVE", palabra));
                } else {
                    tokens.add(new Token("IDENTIFICADOR", palabra));
                }
            } else if (matcher.group("NUMERO") != null) {
                tokens.add(new Token("NUMERO", matcher.group()));
            } else if (matcher.group("OPERADOR") != null) {
                tokens.add(new Token("OPERADOR", matcher.group()));
            } else if (matcher.group("DELIMITADOR") != null) {
                tokens.add(new Token("DELIMITADOR", matcher.group()));
            } else if (matcher.group("ERROR") != null) {
                tokens.add(new Token("ERROR", matcher.group()));
            }
            // El grupo ESPACIO se ignora para no generar tokens innecesarios
        }

        return tokens;
    }
}
