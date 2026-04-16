package orientado_a_objetos;

public class TestConstructor {

	public static void main(String[] args) {
		Persona p1 = new Persona("Juan");
		System.out.println("Nombre: \"" + p1.nombre + "\" tiene " + p1.edad + " años.");
		
		Persona p2 = new Persona("Maria", 22);
		System.out.println("Nombre: \"" + p2.nombre + "\" tiene " + p2.edad + " años.");
	}
	
}