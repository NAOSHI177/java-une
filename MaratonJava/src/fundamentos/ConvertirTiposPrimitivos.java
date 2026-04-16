package fundamentos;

public class ConvertirTiposPrimitivos {
	public static void main (String[] args) {
		long y = 99665L;
		/* java trata convertir en integer el lado derecho de la igualda
		 * razon por la cual es importante agregarle un L (long)
		*/
		int x = (int) y ;
		// conversion tipo cash 
		System.out.println(x);
		
		double alto = 100.37;
		int altura = (int) alto;
		System.out.println(altura);
	}
}