package orientado_a_objetos;

import java.util.ArrayList;
import java.util.List;

public class Instanciando {

	public static void main(String[] args) {
		Auto miAuto = new Auto();	
				miAuto.fabricante = "Fiat";
		miAuto.modelo = "Palio";
		miAuto.anoDeFabrica = 2011;
		miAuto.color = "Plata";
		
		Auto tuAuto = new Auto();
		tuAuto.fabricante = "Honda";
		tuAuto.modelo = "Civic";
		tuAuto.anoDeFabrica = 2009;
		tuAuto.color = "Preto";
		
		tuAuto.alterarModelo("Fit");
		//cargar jara
		miAuto.jara = new Proprietario();
		
		miAuto.jara.nombre = "Yo mismo";
		miAuto.jara.barrio = "San Juan";
		
		miAuto.arrancar();
		
		tuAuto.arrancar();
		
		System.out.println("Mi auto");
		System.out.println("-----------------------");
		System.out.println("Modelo: " + miAuto.modelo);
		System.out.println("Año: " + miAuto.anoDeFabrica);
		
		System.out.println();
		System.out.println("Tu auto");
		System.out.println("-----------------------");
		System.out.println("Modelo: " + tuAuto.modelo);
		System.out.println("Año: " + tuAuto.anoDeFabrica);
		
	
		//lista de autos
		List<Auto> autos = new ArrayList<>();
		autos.add(miAuto);
		autos.add(tuAuto);
     System.out.println(autos.toString());

		
		System.out.println("Dueño de miAuto " + miAuto.jara.nombre);
	}
}
