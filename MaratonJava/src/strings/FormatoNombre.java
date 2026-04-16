package strings;

public class FormatoNombre {

    public static void main(String[] args) {
        String nombre = "   Pedro de Paredes  ";
        
        System.out.println(nombre.toString()); 
        
                
        
        
        System.out.println(formatearNombre(nombre, "da", "de", "do"));
    }

    public static String formatearNombre(String nombre, String... preposicionesParaExcluir) {
        String nombreFormateado = nombre.toUpperCase().trim();

        for (String preposicion : preposicionesParaExcluir) {
            nombreFormateado = nombreFormateado.replace(" "
                    + preposicion.toUpperCase() + " ", " ");
        }

        return nombreFormateado;
    }

}