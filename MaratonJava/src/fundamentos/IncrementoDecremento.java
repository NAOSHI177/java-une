package fundamentos;


public class IncrementoDecremento {
	public static void main (String[] args) {
		int edad = 10;
			edad++;
		int edadAumentadoA = ++edad;
		

		System.out.println("edad: "+edad);
		System.out.println("edadAumentadoA: "+edadAumentadoA);
		
		edad = 11;
		int edadAumentadoB = edad++;
		System.out.println("edad: "+edad);
		System.out.println("edadAumentadoB: "+edadAumentadoB);
		
	}
}