package strings;

public class AnonimizaTokens {

    public static void main(String[] args) {
        String html = "<a href=\"mailto:jose@gmail.com\">\n   jose@gmail.com  </a>\n" +
                "<a>\n   abc@fp.com</a><a>xyz@fp.com</a>" +
                "<strong>maria@fp.com</strong>";

        
        
        
        // Expresión regular para detectar correos electrónicos
        String regex = "[\\w.-]+@[a-z\\d.-]+\\.[a-z]{2,3}";
//////////////////
        // Reemplazar cada correo con un token
        String nuevoHtml = html.replaceAll(regex, "[EMAIL]");

        System.out.println("Texto con tokens:");
        System.out.println(nuevoHtml);

        // Opcional: mostrar todos los emails detectados (los tokens que encontró)
        System.out.println("\nCorreos detectados:");
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(regex).matcher(html);
        while (matcher.find()) {
            System.out.println("TOKEN: [EMAIL] -> " + matcher.group());
        }
    }
}
