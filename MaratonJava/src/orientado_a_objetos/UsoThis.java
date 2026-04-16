package orientado_a_objetos;

public class UsoThis {

	public static void main(String[] args) {
		Auto auto = new Auto();
		auto.modelo = "Supra";
		
		System.out.println("Modelo antes: " + auto.modelo);
		
		auto.alterarModelo("Celica");
		
		System.out.println("Modelo despues: " + auto.modelo);		
		
	}
	
}