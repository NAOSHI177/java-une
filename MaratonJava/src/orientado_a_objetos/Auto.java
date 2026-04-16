package orientado_a_objetos;

public class Auto {

	String fabricante;
	String modelo;
	String color;
	int anoDeFabrica;
	boolean esElectrico = false;//valor defa
	//
	//----componer 
	Proprietario jara;//null
		
	//Proprietario jara = new Proprietario();
///7 metodos
	
	
		void arrancar() {
			System.out.println("arrancando el auto: " + modelo);
		}
		void alterarModelo(String modelo) {
			if (modelo != null) {
				this.modelo = modelo;
			}
		}
		//reescrita de toString
		@Override
		public String toString() {
			return "Auto [fabricante=" + fabricante + ", modelo=" + modelo + "]";
		}
		
}
