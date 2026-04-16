package orientado_a_objetos;

public class TesAireEmcap {

		public static void main(String[] args) {
			AireAcondicionado ac = new AireAcondicionado(); // 17 - 25
			
			ac.mudarTemperatura(21);
			System.out.println("Temperatura : " + ac.obtenerTemperatura() + "º");
			
			ac.mudarTemperatura(20);// 10 20 26
			System.out.println("Temperatura : " + ac.obtenerTemperatura() + "º");
		}
		
	}

