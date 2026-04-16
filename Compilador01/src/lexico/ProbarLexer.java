package lexico;




import java.util.List;


public class ProbarLexer {
    public static void main(String[] args) {
        // Código fuente de prueba
        String codigo = "if (a == 10) return b;";

        // Crear instancia de  Lexer
        Lexer lexer = new Lexer();

        // Analizar y obtener tokens
        List<Token> tokens = lexer.analizar(codigo);

        // Mostrar salida en consola
        System.out.println("=== Resultados del Análisis Léxico ===");
        for (Token t : tokens) {
            System.out.println(t.getTipo() + " -> " + t.getValor());
        }
    }
}
