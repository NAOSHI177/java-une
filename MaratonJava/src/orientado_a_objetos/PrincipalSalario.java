package orientado_a_objetos;

public class PrincipalSalario {

	public static void main(String[] args) {
		HojaPago hoja = new HojaPago();
		
		
		double salario = hoja.calcularSalario(160, 12, 32.5, 40.2);
		
		System.out.println("Salario calculado: " + salario);
	}
	
}
