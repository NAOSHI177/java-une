package fundamentos;

public class PromocionAritmetica {
	public static void main (String[] args) {
		//suma con promocion float
		int x = 10;
		int y = 3;
		float z = x / (float) y ;
		System.out.println(z);
		
		//sin promocionar
		float xx = 10;
		float yy = 3;
		float zz = xx /  yy ;
		System.out.println(zz);
	}
}