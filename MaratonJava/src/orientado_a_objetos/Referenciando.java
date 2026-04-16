package orientado_a_objetos;

public class Referenciando {

	public static void main(String[] args) {
		Proprietario jara1 = new Proprietario();
		jara1.nombre = "Juan Perez";
		
		Auto miAuto = new Auto();
		miAuto.modelo = "Palio";
		
		Auto tuAuto = new Auto();
		tuAuto.modelo = "Civic";
		
		miAuto.jara = jara1;
		//tuAuto.jara = jara1;
		
		System.out.println("Antes del cambio");
		System.out.println(miAuto.jara.nombre);
		//System.out.println(tuAuto.jara.nombre);
		System.out.println(jara1.nombre);
		
		System.out.println();
		
		miAuto.jara.nombre = "Pedro Cano";
		jara1.nombre="dddd";
		System.out.println("Despues...|	");
		System.out.println(miAuto.jara.nombre);
		//System.out.println(tuAuto.jara.nombre);
		System.out.println(jara1.nombre);
		
		//vendamos el palio
		Proprietario comprador = new Proprietario();
		comprador.nombre="Mario Leal";
		miAuto.jara=comprador;
		
		//
		comprador.nombre="Mario Leal xxxs";
		System.out.println("Ahora mi auto pertenece a 	" + miAuto.jara.nombre);
		System.out.println("miAuto.jara.nombre "+ miAuto.jara.nombre);
		
		System.out.println("jara1 "+ jara1.nombre);
		//7 metodos
		
		miAuto.arrancar();
	}
	
}
