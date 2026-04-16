package strings;
public class FormataeaCodigo {

    public static void main(String[] args) {
        int codigo = 1234;

        String codigoFormateado = rellenarIzq(
                String.valueOf(codigo), '0', 10);
        System.out.println(codigoFormateado);
    }

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