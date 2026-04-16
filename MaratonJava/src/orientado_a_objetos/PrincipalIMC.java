package orientado_a_objetos;

public class PrincipalIMC {

	public static void main(String[] args) {
		Paciente p = new Paciente();
		p.peso = 100;
		p.altura = 1.65;
		
		IMC imc = p.calcularIndiceDeMassaCorporal();
		
		System.out.println("Por debajo del peso ideal: " + imc.porDebajoIdeal);
		System.out.println("Peso ideal: " + imc.pesoIdeal);
		System.out.println("Obesidad: " + imc.obesidad);
		System.out.println("Grado de obesidad: " + imc.gradoObesidad);
	}
	
}
