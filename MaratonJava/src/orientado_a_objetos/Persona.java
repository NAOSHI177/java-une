package orientado_a_objetos;

public class Persona {

	String nombre;
	int edad;

	Persona(String nombre) {
		this.nombre = nombre;

	}

	Persona(String nombre, int edad) {
		this(nombre);
		this.edad = edad;
	}
}